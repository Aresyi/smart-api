package com.ydj.smart.api.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tranb.ocr.utils.Pagination;
import com.ydj.smart.api.constant.CommentType;
import com.ydj.smart.api.constant.Constant;
import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.CommentDao;
import com.ydj.smart.api.dao.NotifyDao;
import com.ydj.smart.api.dao.SysConfDao;
import com.ydj.smart.api.dao.SysDao;
import com.ydj.smart.api.dao.TrackDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.push.SystemWebSocketHandler;
import com.ydj.smart.api.push.WebSocketService;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CommonUtils;
import com.ydj.smart.common.tools.StringUtils;

/**  
 *
 * @author : Ares.yi
 * @createTime : 2014-11-10 上午11:13:42 
 * @version : 1.0 
 * @description : 
 *
 */
@Controller
@RequestMapping("track")
public class TrackAction extends BaseAction {
	
	@Resource
	private TrackDao trackDao;
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private NotifyDao notifyDao;
	
	@Resource
	private CommentDao commentDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private SysDao sysDao;
	
	@Resource
	private SysConfDao sysConfDao;
	
	@Resource
	private SystemWebSocketHandler systemWebSocketHandler;
	
	@Resource
	private WebSocketService webSocketService;
	
	@Resource
    private VelocityEngine velocityEngine;

	@RequestMapping("create")
	public String createTrack (
	            HttpServletRequest request) throws Exception{
		
		return "createTrack";
	}
	
	@RequestMapping("searchTrack")
	public String searchTrack (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String belongItemId = this.getAndSetAttribute("belongItemId",request);
		String keyword = this.getAndSetAttribute("keyword",request);
		
		String page_str = request.getParameter("page");
		int page = CommonUtils.parseInt(page_str, 1);
		
		Pagination<JSONObject> pagerecords =this.trackDao.findTrackByPage(companyId,belongItemId,keyword,page,30);
		request.setAttribute("pagerecords", pagerecords);
		
		List<JSONObject> list = pagerecords.getRecords();
		
		JSONArray ary = new JSONArray();
		for(JSONObject one : list){
			String goal = one.getString("reqDes");
			String target = one.getString("target");

			JSONObject a = new JSONObject();
			a.put("日期", one.getString("releaseDate")+" 至  " + one.getString("checkDate") );
			a.put("目标",  goal.substring(0,Math.min(25, goal.length())));
			a.put("版本", one.getString("version") );
			a.put("负责人", one.getString("dutyPerson") );
			a.put("结果", target.substring(0,Math.min(25, target.length())) );
			ary.add(a);
		}
		
		request.setAttribute("data", ary);
		
		
		return "trackList";
	}
	
	
	/**
	 * 添加新足迹
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addTrack")
	public String addTrack (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String userId = this.getUserId(request, response);
		
		String message = null;
		
		String belongItemId = getAndSetAttribute("belongItemId",request);
		String version = getAndSetAttribute("version",request);
		String reqDes = getAndSetAttribute("reqDes",request);
		String target = getAndSetAttribute("target",request);
		String upgradeDes = getAndSetAttribute("upgradeDes",request);
		String releaseDate = getAndSetAttribute("releaseDate",request);
		String checkDate = getAndSetAttribute("checkDate",request);
		String targetResult = getAndSetAttribute("targetResult",request);
		String dutyPerson = getAndSetAttribute("dutyPerson",request);
		
		if(StringUtils.isEmpty(belongItemId)){
			message = "请选择项目名称";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		if(StringUtils.isEmpty(version)){
			message = "请填写版本";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		if(StringUtils.isEmpty(reqDes)){
			message = "请填写需求简述";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		if(StringUtils.isEmpty(target)){
			message = "请填写版本目标 ";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		if(StringUtils.isEmpty(dutyPerson)){
			message = "请填写相关负责人 ";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		JSONObject item = this.apiDao.findItemById(belongItemId);
		
		String belongItem = item.optString("name");
		
		JSONObject track = new JSONObject();
		track.put("companyId", companyId);
		track.put("belongItemId", belongItemId);
		track.put("belongItem", belongItem);
		track.put("version", version);
		track.put("reqDes", reqDes);
		track.put("target", target);
		track.put("upgradeDes", upgradeDes);
		track.put("releaseDate", releaseDate);
		track.put("checkDate", checkDate);
		track.put("targetResult", targetResult);
		track.put("dutyPerson", dutyPerson);
		
		long now = System.currentTimeMillis();
		
		track.put("createTime", now);
		track.put("modifyTime", now);
		track.put("isUse", 1);
		track.put("creater", opreater);
		track.put("createUserId", userId);
		
		this.trackDao.saveTrack(track);
		
		
		JSONObject one = this.trackDao.findTrackByItemAndCreateTime(companyId,belongItemId,now);
		
		String href = Constant.WEB_ROOT +  "track/"+one.getString("id")+"/trackDetail/" ;
		
		this.sysDao.saveSysLog(companyId,opreater, "新建["+belongItem+"("+version+")]足迹 ",href);
		
		return "redirect:/track/"+one.getString("id")+"/trackDetail/";
	}
	
	
	/**
	 * 查看具体足迹
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/trackDetail")
	public String trackDetail(@PathVariable String id,
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String userId = this.getUserId(request, response);
		
		JSONObject one =  this.trackDao.findTrackById(id);
		
		request.setAttribute("one", one);
		
		List<JSONObject> commentList = this.commentDao.findCommentById(CommentType.track, id);

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
		request.setAttribute("allUserGroup", companyId);
		request.setAttribute("allUser", allUser);
		
		return  "trackDetail";
	}
	
	
	/**
	 * 修改足迹
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/toEditeTrack")
	public String toEditeTrack(@PathVariable String id,
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		JSONObject one = this.trackDao.findTrackById(id);
		request.setAttribute("one", one);
		
		return  "editeTrack";
	}
	
	
	/**
	 * 废弃足迹
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delTrack")
	public String delTrack(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		
		String id = request.getParameter("id");
		
		JSONObject track = this.trackDao.findTrackById(id);
		
		String title =  new StringBuilder()
		.append(track.getString("belongItem"))
		.append("(")
		.append(track.getString("version"))
		.append(")")
		.toString();
		
		track.put("companyId", companyId);
		track.put("isDel", "1");
		track.put("modifyTime", System.currentTimeMillis());
		track.put("modifyer", opreater);
		
		track.remove("_id");
		
		this.trackDao.updateTrack(track,id);
		 
		this.sysDao.saveSysLog(companyId,opreater, "废弃["+title+"]足迹");
		
		JSONObject res = new JSONObject();
		res.put("result", 1);
		
		PrintWriter out = response.getWriter(); 
		out.print(res);
		out.flush();
		out.close(); 
		
		return null;
	}
	
	/**
	 * 修改足迹
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editeTrack")
	public String editeTrack (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String userId = this.getUserId(request, response);
		
		String message = null;
		
		String id = getAndSetAttribute("id",request);
		String belongItemId = getAndSetAttribute("belongItemId",request);
		String version = getAndSetAttribute("version",request);
		String reqDes = getAndSetAttribute("reqDes",request);
		String target = getAndSetAttribute("target",request);
		String upgradeDes = getAndSetAttribute("upgradeDes",request);
		String releaseDate = getAndSetAttribute("releaseDate",request);
		String checkDate = getAndSetAttribute("checkDate",request);
		String targetResult = getAndSetAttribute("targetResult",request);
		String dutyPerson = getAndSetAttribute("dutyPerson",request);
		
		
		if(StringUtils.isEmpty(belongItemId)){
			message = "请选择项目名称";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		if(StringUtils.isEmpty(version)){
			message = "请填写版本";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		if(StringUtils.isEmpty(reqDes)){
			message = "请填写需求简述";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		if(StringUtils.isEmpty(target)){
			message = "请填写版本目标 ";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		if(StringUtils.isEmpty(dutyPerson)){
			message = "请填写相关负责人 ";
			request.setAttribute("message", message);
			return  this.createTrack(request);
		}
		
		JSONObject item = this.apiDao.findItemById(belongItemId);
		
		String belongItem = item.optString("name");
		
		JSONObject oldTrack = this.trackDao.findTrackById(id);
		
		JSONObject track = new JSONObject();
		track.put("companyId", companyId);
		track.put("belongItemId", belongItemId);
		track.put("belongItem", belongItem);
		track.put("version", version);
		track.put("reqDes", reqDes);
		track.put("target", target);
		track.put("upgradeDes", upgradeDes);
		track.put("releaseDate", releaseDate);
		track.put("checkDate", checkDate);
		track.put("targetResult", targetResult);
		track.put("dutyPerson", dutyPerson);
		
		long now = System.currentTimeMillis();
		
		track.put("createTime", oldTrack.get("createTime"));
		track.put("isUse", 1);
		track.put("creater", oldTrack.get("creater"));
		track.put("modifyTime", now);
		track.put("modifyer", opreater);
		track.put("createUserId", oldTrack.containsKey("createUserId") ? oldTrack.optString("createUserId") : userId);
		
		this.trackDao.updateTrack(track,id);
		
		String href = Constant.WEB_ROOT +  "track/"+id+"/trackDetail/" ;
		
		this.sysDao.saveSysLog(companyId,opreater, "修改["+belongItem+"("+version+")]足迹 ",href);
		
		return "redirect:/track/"+id+"/trackDetail/";
	}
}
