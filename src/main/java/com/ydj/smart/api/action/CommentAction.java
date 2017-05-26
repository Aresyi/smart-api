/** **/
package com.ydj.smart.api.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.ydj.smart.api.constant.CommentType;
import com.ydj.smart.api.constant.Constant;
import com.ydj.smart.api.constant.NotifyType;
import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.CommentDao;
import com.ydj.smart.api.dao.MessageDao;
import com.ydj.smart.api.dao.NotifyDao;
import com.ydj.smart.api.dao.SysDao;
import com.ydj.smart.api.dao.TrackDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.push.SystemWebSocketHandler;
import com.ydj.smart.api.push.WebSocketService;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CommonUtils;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("comment")
public class CommentAction extends BaseAction {
	
	@Resource
	private MessageDao messageDao;
	
	@Resource
	private CommentDao commentDao;
	
	@Resource
	private NotifyDao notifyDao;
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private TrackDao trackDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private SysDao sysDao;
	
	@Resource
	private SystemWebSocketHandler systemWebSocketHandler;
	
	@Resource
	private WebSocketService webSocketService;
	
	
	@Resource
    private VelocityEngine velocityEngine;
	
	/**
	 * 文档评论
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{id}/docs/")
	@ResponseBody
	public JSONObject docsComment (@PathVariable String id,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String userId = this.getUserId(request, response);
		
		String content = this.getAndSetAttribute("comment_content", request);
	
		JSONObject message = this.messageDao.findMessageById(id);
		
		long now = System.currentTimeMillis();
		
		JSONObject comment = new JSONObject();
		comment.put("userId", userId);
		comment.put("type", CommentType.doc.ordinal());
		comment.put("targetId", id);
		comment.put("content", content);
		comment.put("createTime", now);
		comment.put("creater", opreater);
		
		this.commentDao.saveComment(comment);
		
		String cc_guids = this.getAndSetAttribute("cc_guids", request);
		
		JSONArray ccGuids = new JSONArray();
		
		JSONObject obj = this.commentDao.findCommentByOnly(CommentType.doc, id, userId, now);
		
		if(CommonUtils.isNotEmptyString(cc_guids)){
			for(String uid : cc_guids.split(",")){
				if(userId.equals(uid)){
					continue;
				}
				
				JSONObject one = new JSONObject();
				one.put("companyId", companyId);
				one.put("userId", uid);
				one.put("actorId", userId);
				one.put("type", NotifyType.doc.ordinal());
		    	one.put("actorMember", opreater);
		    	one.put("action", "评论了");
		    	one.put("target", message.optString("title"));
		    	one.put("targetId", id);
		    	one.put("commentId", obj.getString("id"));
		    	one.put("content", content);
		    	one.put("unRead", 1);
		    	one.put("readTime", 0);
		    	one.put("createTime",System.currentTimeMillis());
		    	
		    	this.notifyDao.saveNotify(one);
		    	
		    	String noticeTitle = opreater + " 评论了  " + message.optString("title");
		    	String s = this.webSocketService.getNotifyContent(noticeTitle,uid);
		    	
		    	systemWebSocketHandler.sendMessageToUser(uid, new TextMessage(s));
		    	
		    	ccGuids.add(uid);
			}
		}
		
		Map<String,Object> model = new HashMap<String,Object>();

		
		obj.put("createTime", CommonUtils.getDateString("yyyy-MM-dd HH:mm:ss", now));
		obj.put("user", this.userDao.findUserById(userId));
		
        model.put("obj", obj);
		
		String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/comment.vm", "utf-8", model);
		
		JSONObject res = new JSONObject();
		res.put("guid", obj.getString("id"));
		res.put("cc_guids", ccGuids);
		res.put("html", html);
		
		
		JSONObject msg = this.messageDao.findMessageById(id);
		String href = Constant.WEB_ROOT +  "message/"+id+"/messageDetail#"+obj.getString("id");
		this.sysDao.saveSysLog(companyId,opreater, "评论了["+msg.getString("title")+"]"+content,href);
		
		return res;
	}
	
	
	/**
	 * API评论
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{id}/api/")
	@ResponseBody
	public JSONObject apiComment (@PathVariable String id,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String userId = this.getUserId(request, response);
		
		String content = this.getAndSetAttribute("comment_content", request);
		
		JSONObject api = this.apiDao.findAPIById(id);
		
		long now = System.currentTimeMillis();
		JSONObject comment = new JSONObject();
		comment.put("companyId", companyId);
		comment.put("userId", userId);
		comment.put("type", CommentType.api.ordinal());
		comment.put("targetId", id);
		comment.put("content", content);
		comment.put("createTime", now);
		comment.put("creater", opreater);
		
		this.commentDao.saveComment(comment);
		
		String createUserId = api.optString("createUserId");
		
		if(CommonUtils.isEmptyString(createUserId)){//为了兼容修复老数据
			JSONObject user = this.userDao.findUserByName(companyId,api.getString("creater"));
			
			createUserId = user.getString("id");
			this.userDao.updateAPIAddCreateUid(createUserId, id);
		}
		
		String cc_guids = this.getAndSetAttribute("cc_guids", request);
		
		JSONArray ccGuids = new JSONArray();
		
		
		JSONObject obj = this.commentDao.findCommentByOnly(CommentType.api, id, userId, now);
		
		if(CommonUtils.isNotEmptyString(cc_guids)){
			for(String uid : cc_guids.split(",")){
				System.out.println(uid);
				if(userId.equals(uid)){
					continue;
				}
				
				JSONObject one = new JSONObject();
				one.put("companyId", companyId);
				one.put("userId", uid);
				one.put("actorId", userId);
				one.put("type", NotifyType.api.ordinal());
		    	one.put("actorMember", opreater);
		    	one.put("action", "回复了");
		    	one.put("target", api.optString("name"));
		    	one.put("targetId", id);
		    	one.put("commentId", obj.getString("id"));
		    	one.put("content", content);
		    	one.put("unRead", 1);
		    	one.put("readTime", 0);
		    	one.put("createTime",System.currentTimeMillis());
		    	
		    	this.notifyDao.saveNotify(one);
		    	
		    	String noticeTitle = opreater + " 回复了  " + api.optString("name");
		    	
		    	String s = this.webSocketService.getNotifyContent(noticeTitle,uid);
		    	
		    	systemWebSocketHandler.sendMessageToUser(uid, new TextMessage(s));
		    	
		    	ccGuids.add(uid);
			}
		}
		
		Map<String,Object> model = new HashMap<String,Object>();

		
		obj.put("createTime", CommonUtils.getDateString("yyyy-MM-dd HH:mm:ss", now));
		obj.put("user", this.userDao.findUserById(userId));
		
        model.put("obj", obj);
		
		String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/comment.vm", "utf-8", model);
		
		JSONObject res = new JSONObject();
		res.put("guid", obj.getString("id"));
		res.put("cc_guids", ccGuids);
		res.put("html", html);
		
		String href = Constant.WEB_ROOT +  "api/"+id+"/apiDetail#"+obj.getString("id");
		this.sysDao.saveSysLog(this.getCompanyId(request, response),opreater, "评论了["+api.getString("name")+"]"+content,href);
		
		return res;
	}
	
	
	/**
	 * 足迹评论
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{id}/track/")
	@ResponseBody
	public JSONObject trackComment (@PathVariable String id,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String userId = this.getUserId(request, response);
		
		String content = this.getAndSetAttribute("comment_content", request);
		
		JSONObject track = this.trackDao.findTrackById(id);
		
		long now = System.currentTimeMillis();
		JSONObject comment = new JSONObject();
		comment.put("companyId", companyId);
		comment.put("userId", userId);
		comment.put("type", CommentType.track.ordinal());
		comment.put("targetId", id);
		comment.put("content", content);
		comment.put("createTime", now);
		comment.put("creater", opreater);
		
		this.commentDao.saveComment(comment);
		
		String createUserId = track.optString("createUserId");
		
		if(CommonUtils.isEmptyString(createUserId)){//为了兼容修复老数据
			JSONObject user = this.userDao.findUserByName(companyId,track.getString("creater"));
			
			createUserId = user.getString("id");
			this.userDao.updateAPIAddCreateUid(createUserId, id);
		}
		
		String cc_guids = this.getAndSetAttribute("cc_guids", request);
		
		JSONArray ccGuids = new JSONArray();
		
		
		JSONObject obj = this.commentDao.findCommentByOnly(CommentType.track, id, userId, now);
		
		String title = track.optString("belongItem")+"("+track.optString("version")+")";
		
		if(CommonUtils.isNotEmptyString(cc_guids)){
			for(String uid : cc_guids.split(",")){
				System.out.println(uid);
				if(userId.equals(uid)){
					continue;
				}
				
				JSONObject one = new JSONObject();
				one.put("companyId", companyId);
				one.put("userId", uid);
				one.put("actorId", userId);
				one.put("type", NotifyType.track.ordinal());
		    	one.put("actorMember", opreater);
		    	one.put("action", "回复了");
		    	one.put("target", title);
		    	one.put("targetId", id);
		    	one.put("commentId", obj.getString("id"));
		    	one.put("content", content);
		    	one.put("unRead", 1);
		    	one.put("readTime", 0);
		    	one.put("createTime",System.currentTimeMillis());
		    	
		    	this.notifyDao.saveNotify(one);
		    	
		    	String noticeTitle = opreater + " 回复了  " + title;
		    	
		    	String s = this.webSocketService.getNotifyContent(noticeTitle,uid);
		    	
		    	systemWebSocketHandler.sendMessageToUser(uid, new TextMessage(s));
		    	
		    	ccGuids.add(uid);
			}
		}
		
		Map<String,Object> model = new HashMap<String,Object>();

		
		obj.put("createTime", CommonUtils.getDateString("yyyy-MM-dd HH:mm:ss", now));
		obj.put("user", this.userDao.findUserById(userId));
		
        model.put("obj", obj);
		
		String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/comment.vm", "utf-8", model);
		
		JSONObject res = new JSONObject();
		res.put("guid", obj.getString("id"));
		res.put("cc_guids", ccGuids);
		res.put("html", html);
		
		String href = Constant.WEB_ROOT +  "track/"+id+"/trackDetail#"+obj.getString("id");
		this.sysDao.saveSysLog(companyId,opreater, "评论了["+title+"]"+content,href);
		
		return res;
	}
}
