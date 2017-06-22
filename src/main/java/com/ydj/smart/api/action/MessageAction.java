/** **/
package com.ydj.smart.api.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydj.smart.api.constant.CommentType;
import com.ydj.smart.api.constant.Constant;
import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.CommentDao;
import com.ydj.smart.api.dao.MessageDao;
import com.ydj.smart.api.dao.NotifyDao;
import com.ydj.smart.api.dao.SysDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CommonUtils;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-11-8 下午04:36:40
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("message")
public class MessageAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private MessageDao messageDao;
	
	@Resource
	private CommentDao commentDao;
	
	@Resource
	private NotifyDao notifyDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private SysDao sysDao;
	
	
	/**
	 * 创建文档
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月28日 上午10:45:57
	 */
	@RequestMapping("newMsg")
	public String newMsg(
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		this.getAndSetAttribute("markdown", request);
		return "createMsg";
	}

	/**
	 * 添加信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addMsg")
	public String addMsg (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String createUserId = this.getUserId(request, response);
		
		String title = this.getAndSetAttribute("doc_title", request);
		String detail = this.getAndSetAttribute("my-editormd-html-code", request);
		String markdown = this.getAndSetAttribute("markdown", request);
		String isHtml = this.getAndSetAttribute("is_html", request);
		
		long createTime = System.currentTimeMillis();
		
		JSONObject message = new JSONObject();
		message.put("companyId", companyId);
		message.put("title", title);
		message.put("detail", detail);
		message.put("markdown", markdown);
		message.put("isHtml", isHtml);
		message.put("creater", opreater);
		message.put("createUserId", createUserId);
		message.put("createTime", createTime);
		message.put("modifyer", opreater);
		message.put("modifyTime", createTime);
		
		this.messageDao.saveMessage(message);
		
		
		JSONObject msg = this.messageDao.findMessageBy(createUserId, createTime);
		
		String href = Constant.WEB_ROOT +  "message/"+msg.optString("id")+"/messageDetail/" ;
		
		this.sysDao.saveSysLog(companyId,opreater, "发布["+title+"]新信息",href);
		
//		return this.messageList(request, response);
//		return this.messageDetail(msg.optString("id"), request, response);
		return "redirect:/message/"+msg.optString("id")+"/messageDetail";
	}
	
	
	/**
	 * 信息列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("messageList")
	public String messageList (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		String companyId = this.getCompanyId(request, response);
		
		List<JSONObject> messageList = this.messageDao.getAllMessage(companyId);
		
		for(JSONObject one : messageList){
			String s = one.optString("detail", "").replace("<a", "<p>").replace("</a>", "</p>").replace("<div", "<p>").replace("</div>", "</p>");
//			if(s.length() > 1000){
//				s = s.substring(0, 1000);
//			}
			one.put("detail", s);
		}
		
		request.setAttribute("messageList", messageList);
		
		return "messageList";
	}
	
	
	/**
	 * 修改信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/editeMsg")
	public String editeMsg (@PathVariable String id,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		JSONObject obj = this.messageDao.findMessageById(id);
		
		request.setAttribute("message", obj);
		
		if( obj.optInt("markdown", -1) == 1 ){
			return "editeMsgMD";
		}
			
		return "editeMsg";
		
	}
	
	
	/**
	 * 保存修改信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/saveEditeMsg")
	public String saveEditeMsg (@PathVariable String id,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String createUserId = this.getUserId(request, response);
		
		String title = this.getAndSetAttribute("doc_title", request);
		String detail = this.getAndSetAttribute("my-editormd-html-code", request);
		String markdown = this.getAndSetAttribute("markdown", request);
		String isHtml = this.getAndSetAttribute("is_html", request);
		
		long now = System.currentTimeMillis();
		
		JSONObject oldMessage = this.messageDao.findMessageById(id);

		JSONObject message = new JSONObject();
		message.put("companyId", companyId);
		message.put("title", title);
		message.put("detail", detail);
		message.put("markdown", markdown);
		message.put("isHtml", isHtml);
		message.put("creater", oldMessage.containsKey("creater") ? oldMessage.getString("creater") : opreater);
		message.put("createUserId", oldMessage.containsKey("createUserId") ? oldMessage.getString("createUserId") : createUserId);
		message.put("createTime",  oldMessage.containsKey("createTime") ? oldMessage.getLong("createTime") : now);
		message.put("modifyer", opreater);
		message.put("modifyTime", now);
		
		this.messageDao.updateMessage(message,id);
		
		String href = Constant.WEB_ROOT +  "message/"+id+"/messageDetail/" ;
		this.sysDao.saveSysLog(companyId,opreater, "修改["+title+"]信息 ",href);
		
//		return this.messageDetail(id, request, response);
		
		return "redirect:/message/"+id+"/messageDetail";
	}
	
	
	/**
	 * 查看信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{id}/messageDetail")
	public String messageDetail (@PathVariable String id,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String userId = this.getUserId(request, response);
		
		request.setAttribute("message", this.messageDao.findMessageById(id));
		
		List<JSONObject> commentList = this.commentDao.findCommentById(CommentType.doc, id);

		
		List<JSONObject> allUserGroup = this.userDao.getAllUserGroup(companyId);


		List<JSONObject> allUser = this.userDao.getAllUser(companyId);
		
		Map<String,JSONObject> tempUserMap = new HashMap<String,JSONObject>();
		
		for(JSONObject user : allUser){
			String uid = user.getString("id");
			tempUserMap.put(uid, user);
		}
		
		if(commentList != null){
			for(JSONObject comment : commentList){
				String uid = comment.getString("userId");
				
				comment.put("user", tempUserMap.get(uid));
			}
		}
		
		String notifyId = this.getAndSetAttribute("notify", request);
		
		if(CommonUtils.isNotEmptyString(notifyId)){
			this.notifyDao.update4Read(userId, notifyId);
		}
		
		request.setAttribute("commentList", commentList);
		request.setAttribute("allUserGroup", allUserGroup);
		request.setAttribute("allUser", allUser);
		
		return "messageDetail";
	}
	
	/**
	 * 删除消息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/delMsg")
	public String delMsg(@PathVariable String id,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		
		JSONObject message = this.messageDao.findMessageById(id);
		
		String title =  new StringBuilder()
		.append(message.getString("title")).toString();
		
		message.put("isDel", "1");
		message.put("modifyTime", System.currentTimeMillis());
		message.put("modifyer", opreater);
		
		message.remove("_id");
		
		this.messageDao.updateMessage(message,id);
		 
		String href = Constant.WEB_ROOT +  "message/"+id+"/messageDetail/" ;
		this.sysDao.saveSysLog(companyId,opreater, "删除["+title+"]消息",href);
		
		return this.messageList(request, response);
	}
	
}
