/** **/
package com.ydj.smart.api.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.socket.TextMessage;

import com.tranb.ocr.utils.Pagination;
import com.ydj.smart.api.constant.CommentType;
import com.ydj.smart.api.constant.Constant;
import com.ydj.smart.api.constant.NotifyType;
import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.CommentDao;
import com.ydj.smart.api.dao.DBTableDao;
import com.ydj.smart.api.dao.NotifyDao;
import com.ydj.smart.api.dao.SysConfDao;
import com.ydj.smart.api.dao.SysDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.push.SystemWebSocketHandler;
import com.ydj.smart.api.push.WebSocketService;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CommonUtils;
import com.ydj.smart.common.tools.MailUtils;
import com.ydj.smart.common.tools.StringUtils;
import com.ydj.smart.common.tools.WebUtils;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("api")
public class APIAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private CommentDao commentDao;
	
	@Resource
	private NotifyDao notifyDao;
	
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
	
	private DBTableDao demo = new DBTableDao();
	
	/**
	 * 添加新接口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("createAPI")
	public String createAPI (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		this.getAndSetAllUser(request,response);
		
		return "createAPI";
	}
			
	
	/**
	 * 添加新接口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addAPI")
	public String addAPI (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String userId = this.getUserId(request, response);
		
		String message = null;
		
		String name = getAndSetAttribute("name",request);
		String des = getAndSetAttribute("des",request);
		String belongItemId = getAndSetAttribute("belongItemId",request);
		String belongModuleId = getAndSetAttribute("belongModuleId",request);
		String url = getAndSetAttribute("url",request);
		String needCookie = getAndSetAttribute("needCookie",request);
		String result = getAndSetAttribute("result",request);
		String other = getAndSetAttribute("other",request);
		
		String para_name[] =getAndSetAttribute2("para_name",request);
		String para_type[] = getAndSetAttribute2("para_type",request);
		String para_isNeed[] = getAndSetAttribute2("para_isNeed",request);
		String para_des[] = getAndSetAttribute2("para_des",request);
		String para_caseVal[] = getAndSetAttribute2("para_caseVal",request);
		
		
		String itemId = getAndSetAttribute("itemId",request);
		String db = getAndSetAttribute("db",request);
		String table = getAndSetAttribute("table",request);
		
		if(StringUtils.isEmpty(url)){
			message = "请填写请求URL";
			request.setAttribute("message", message);
			return  this.createAPI(request,response);
		}
		
		if(StringUtils.isEmpty(name)){
			message = "请填写接口名称";
			request.setAttribute("message", message);
			return  this.createAPI(request,response);
		}
		
		if(StringUtils.isEmpty(des)){
			message = "请填写接口说明";
			request.setAttribute("message", message);
			return  this.createAPI(request,response);
		}
		
		if(StringUtils.isEmpty(belongItemId)){
			message = "请选择接口所在项目 ";
			request.setAttribute("message", message);
			return  this.createAPI(request,response);
		}
		
		if(StringUtils.isEmpty(belongModuleId)){
			message = "请选择接口所在模块 ";
			request.setAttribute("message", message);
			return  this.createAPI(request,response);
		}
		
		
		url = url.replace("http://", "").replace("://", "");
		
		try {
			url = url.substring(url.indexOf("/"),url.indexOf("?"));
		} catch (Exception e) {
		}
		
		JSONObject module = this.apiDao.findModuleById(belongModuleId);
		
		String belongItem = module.optString("itemName");
		String belongModule = module.optString("name");
		
		
		JSONObject api = new JSONObject();
		api.put("companyId", companyId);
		api.put("name", name);
		api.put("des", des);
		api.put("belongItemId", belongItemId);
		api.put("belongModuleId", belongModuleId);
		api.put("belongItem", belongItem);
		api.put("belongModule", belongModule);
		api.put("url", url);
		api.put("needCookie", needCookie);
		api.put("result", result);
		api.put("other", other);
		
		if(CommonUtils.isNotEmptyString(table)){
			JSONObject resDataDes = new JSONObject();
			resDataDes.put("itemId", itemId);
			resDataDes.put("db", db);
			resDataDes.put("table", table);
			
			api.put("resDataDes", resDataDes);
		}
		
		JSONArray parameter = new JSONArray();
		if(para_name != null && para_name.length > 0){
			for(int i=0 ; i < para_name.length ; i++){
				JSONObject one = new JSONObject();
				one.put("para_name", para_name[i]);
				one.put("para_type", para_type[i]);
				one.put("para_isNeed", para_isNeed[i]);
				one.put("para_des", para_des[i]);
				one.put("para_caseVal", para_caseVal[i]);
				
				parameter.add(one);
			}
		}
		
		long now = System.currentTimeMillis();
		
		api.put("parameter", parameter);
		api.put("createTime", now);
		api.put("modifyTime", now);
		api.put("isUse", 1);
		api.put("isHasHistory", 0);
		api.put("creater", opreater);
		api.put("createUserId", userId);
		
		this.apiDao.saveAPI(api);
		
		String noticEmail[] = request.getParameterValues("noticEmail");
		
		
		String href = "";
		String title =  new StringBuilder()
				.append(belongItem)
				.append("-->")
				.append(belongModule)
				.append("-->")
				.append(name).toString();
		
		JSONObject one = this.apiDao.findAPIByNameAndCreateTime(companyId,name, now);
		
		href = Constant.WEB_ROOT +  "api/"+one.getString("id")+"/apiDetail/" ;
		
		if(noticEmail != null && noticEmail.length > 0){
			
			
			String mailContent = new StringBuilder()
			.append(title)
			.append("  接口信息请访问：")
			.append("<br>")
			.append("<a href='"+href+"'>"+href+"</a>")
			.append("<br><br>")
			.toString();
			for(String email : noticEmail){
				MailUtils.asynSend(MailUtils.REG_NOREPLY, email,"新API["+title+"]使用信息", mailContent);
				
				JSONObject user = this.userDao.findUserByEmail(email);
				
				this.notify(companyId,user.getString("id"), userId, opreater, "新建API", name, one.getString("id"), title);
			}
		}
		
		this.sysDao.saveSysLog(companyId,opreater, "新建["+title+"]新接口 ",href);
		
//		response.sendRedirect("/admin.do?method=searchApi");
		
//		return  this.searchApi(request, response);
		
//		return this.apiDetail(one.getString("id"), "", request, response);
		
		return "redirect:/api/"+one.getString("id")+"/apiDetail/";
	}
	
	
	private void notify(String companyId,String userId,String actorId,String opreater,String action,String target,String targetId,String content){
		JSONObject one = new JSONObject();
		one.put("companyId", companyId);
		one.put("userId", userId);
		one.put("actorId", actorId);
		one.put("type", NotifyType.api.ordinal());
    	one.put("actorMember", opreater);
    	one.put("action", action);
    	one.put("target", target);
    	one.put("targetId", targetId);
    	one.put("commentId", 0);
    	one.put("content", content);
    	one.put("unRead", 1);
    	one.put("readTime", 0);
    	one.put("createTime",System.currentTimeMillis());
    	
    	this.notifyDao.saveNotify(one);
    	
    	String noticeTitle = opreater + " "+action+"  " + target;
    	
    	String s = this.webSocketService.getNotifyContent(noticeTitle,userId);
    	
    	systemWebSocketHandler.sendMessageToUser(userId, new TextMessage(s));
	}
	
	
	/**
	 * 搜索API
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchApi")
	public String searchApi(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String belongItemId = this.getAndSetAttribute("belongItemId",request);
		String belongModuleId = this.getAndSetAttribute("belongModuleId",request);
		String isDel = this.getAndSetAttribute("isDel",request);
		String keyword = this.getAndSetAttribute("keyword",request);
		
		String page_str = request.getParameter("page");
		int page = CommonUtils.parseInt(page_str, 1);
		

		Pagination<JSONObject> pagerecords =this.apiDao.findApiByPage(companyId,belongItemId,belongModuleId,isDel,keyword,page,30);
		request.setAttribute("pagerecords", pagerecords);
		
		if(CommonUtils.isNotEmptyString(belongItemId)){
			JSONObject item = this.apiDao.findItemById(belongItemId);
			List<JSONObject> moduleList = this.apiDao.findModuleByItemId(belongItemId);
			
			request.setAttribute("moduleList", moduleList);
			request.getSession().setAttribute("itemName", item.getString("name"));
		}
		
		return  "apiList";
	}
	
	private void getResDataDesc(String companyId,JSONObject api){
		
		if(api == null ||  !api.containsKey("resDataDes")){
			return ;
		}
		
		JSONObject resDataDes = api.getJSONObject("resDataDes");
		String itemId = resDataDes.getString("itemId");
		String db = resDataDes.getString("db");
		String table = resDataDes.getString("table");
		
		JSONObject dbInfo = sysConfDao.findDBByItemIdAndDBName(companyId,itemId, db);
		
		String host = dbInfo.optString("host");
		String port = dbInfo.optString("port");
		String userName = dbInfo.optString("testName");
		String password = dbInfo.optString("testPassword");
		
		List<JSONObject>  resDataDescList = demo.getTableFileds(host,port,userName,password,db, table);
		
		api.put("resDataDesList", resDataDescList);
	}
	
	
	/**
	 * 查看具体API
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/apiDetail")
	public String apiDetail(@PathVariable String id,String history,
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String userId = this.getUserId(request, response);
		
		JSONObject one = new JSONObject();
		
		if("1".equals(history)){
			one = this.apiDao.findHistoryAPIById(id);
		}else{
			one = this.apiDao.findAPIById(id);
			if(one.containsKey("isHasHistory") && one.get("isHasHistory") != null && one.getInt("isHasHistory") == 1){
				List<JSONObject> historyList =  this.apiDao.findApiHistoryByPage(id, 1, 5).getRecords() ;
				one.put("historyTop5", historyList);
			}
		}
		
		this.getResDataDesc(companyId,one);
		
		request.setAttribute("one", one);
		
		List<JSONObject> commentList = this.commentDao.findCommentById(CommentType.api, id);

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
		request.setAttribute("allUserGroup", this.userDao.getAllUserGroup(companyId));
		request.setAttribute("allUser", allUser);
		
		return  "apiDetail";
	}
	
	
	private void getAndSetAllUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		List<JSONObject> allUser = this.userDao.getAllUser(companyId);
		
		Map<String,List<JSONObject>> groupMap = new HashMap<String,List<JSONObject>>();
		
		for(JSONObject one : allUser){
			String group = one.getString("group");
			
			List<JSONObject> temp = null;
			if(groupMap.containsKey(group)){
				temp = groupMap.get(group);
			}else{
				temp = new ArrayList<JSONObject>();
			}
			temp.add(one);
			groupMap.put(group, temp);								
		}
		
		request.setAttribute("allUser",groupMap);
	}
	
	/**
	 * 修改API
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/toEditeAPI")
	public String toEditeAPI(@PathVariable String id,
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		JSONObject one = this.apiDao.findAPIById(id);
		request.setAttribute("one", one);
		request.setAttribute("belongItemId", one.getString("belongItemId"));
		request.setAttribute("belongModuleId", one.getString("belongModuleId"));
		
		List<JSONObject> moduleList = this.apiDao.findModuleByItemId(one.getString("belongItemId"));
		
		request.setAttribute("moduleList", moduleList);
		
		this.getAndSetAllUser(request,response);
		
		return  "editeAPI";
	}
	
	
	/**
	 * 废弃接口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delApi")
	public String delApi(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String opreater = this.getUser(request,response);
		
		String id = request.getParameter("id");
		
		JSONObject api = this.apiDao.findAPIById(id);
		
		String title =  new StringBuilder()
		.append(api.getString("belongItem"))
		.append("-->")
		.append(api.getString("belongModule"))
		.append("-->")
		.append(api.getString("name")).toString();
		
		api.put("isDel", "1");
		api.put("modifyTime", System.currentTimeMillis());
		api.put("modifyer", opreater);
		
		api.remove("_id");
		
		String companyId = this.getCompanyId(request, response);
		
		this.apiDao.updateAPI(api,id);
		 
		this.sysDao.saveSysLog(companyId,opreater, "废弃["+title+"]接口");
		
		JSONObject res = new JSONObject();
		res.put("result", 1);
		
		PrintWriter out = response.getWriter(); 
		out.print(res);
		out.flush();
		out.close(); 
		
		return null;
	}
	
	/**
	 * 修改接口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editeAPI")
	public String editeAPI (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		String userId = this.getUserId(request, response);
		
		String message = null;
		
		String id = getAndSetAttribute("id",request);
		String name = getAndSetAttribute("name",request);
		String des = getAndSetAttribute("des",request);
		String belongItemId = getAndSetAttribute("belongItemId",request);
		String belongModuleId = getAndSetAttribute("belongModuleId",request);
		String url = getAndSetAttribute("url",request);
		String needCookie = getAndSetAttribute("needCookie",request);
		String result = getAndSetAttribute("result",request);
		String other = getAndSetAttribute("other",request);
		
		String para_name[] =getAndSetAttribute2("para_name",request);
		String para_type[] = getAndSetAttribute2("para_type",request);
		String para_isNeed[] = getAndSetAttribute2("para_isNeed",request);
		String para_des[] = getAndSetAttribute2("para_des",request);
		String para_caseVal[] = getAndSetAttribute2("para_caseVal",request);
		
		String itemId = getAndSetAttribute("itemId",request);
		String db = getAndSetAttribute("db",request);
		String table = getAndSetAttribute("table",request);
		
		
		JSONObject oldApi = this.apiDao.findAPIById(id);
		
		if(StringUtils.isEmpty(url)){
			message = "请填写请求URL";
			request.setAttribute("message", message);
			return  this.toEditeAPI(id, request, response);
		}
		
		if(StringUtils.isEmpty(name)){
			message = "请填写接口名称";
			request.setAttribute("message", message);
			return  this.toEditeAPI(id, request, response);
		}
		
		if(StringUtils.isEmpty(des)){
			message = "请填写接口说明";
			request.setAttribute("message", message);
			return  this.toEditeAPI(id, request, response);
		}
		
		if(StringUtils.isEmpty(belongItemId)){
			message = "请选择接口所在项目 ";
			request.setAttribute("message", message);
			return  this.toEditeAPI(id, request, response);
		}
		
		if(StringUtils.isEmpty(belongModuleId)){
			message = "请选择接口所在模块 ";
			request.setAttribute("message", message);
			return  this.toEditeAPI(id, request, response);
		}
		
		
		url = url.replace("http://", "").replace("://", "");
		try {
			url = url.substring(url.indexOf("/"),url.indexOf("?"));
		} catch (Exception e) {
		}
		
		
		JSONObject module = this.apiDao.findModuleById(belongModuleId);
		
		String belongItem = module.optString("itemName");
		String belongModule = module.optString("name");
		
		
		JSONObject api = new JSONObject();
		api.put("companyId", companyId);
		api.put("name", name);
		api.put("des", des);
		api.put("belongItemId", belongItemId);
		api.put("belongModuleId", belongModuleId);
		api.put("belongItem", belongItem);
		api.put("belongModule", belongModule);
		api.put("url", url);
		api.put("needCookie", needCookie);
		api.put("result", result);
		api.put("other", other);
		
		if(CommonUtils.isNotEmptyString(table)){
			JSONObject resDataDes = new JSONObject();
			resDataDes.put("itemId", itemId);
			resDataDes.put("db", db);
			resDataDes.put("table", table);
			
			api.put("resDataDes", resDataDes);
		}else if(oldApi.containsKey("resDataDes")){
			api.put("resDataDes", oldApi.getJSONObject("resDataDes"));
		}
		
		JSONArray parameter = new JSONArray();
		if(para_name != null && para_name.length > 0){
			for(int i=0 ; i < para_name.length ; i++){
				JSONObject one = new JSONObject();
				one.put("para_name", para_name[i]);
				one.put("para_type", para_type[i]);
				one.put("para_isNeed", para_isNeed[i]);
				one.put("para_des", para_des[i]);
				one.put("para_caseVal", para_caseVal[i]);
				
				parameter.add(one);
			}
		}
		
		long now = System.currentTimeMillis();
		
		api.put("parameter", parameter);
		api.put("createTime", oldApi.get("createTime"));
		api.put("isUse", 1);
		api.put("creater", oldApi.get("creater"));
		api.put("modifyTime", now);
		api.put("modifyer", opreater);
		api.put("createUserId", oldApi.containsKey("createUserId") ? oldApi.optString("createUserId") : userId);
		
		if(oldApi.containsKey("isHasHistory")){
			api.put("isHasHistory", 1);
		}
		
		String history = getAndSetAttribute("history",request);
		if("是".equals(history)){
			
			oldApi.put("companyId", companyId);
			oldApi.put("hisVersonId", oldApi.getString("id"));
			oldApi.put("modifyTime", now);
			oldApi.put("modifyer", opreater);
			if(oldApi.containsKey("id")){
				oldApi.remove("id");
			}
			if(oldApi.containsKey("_id")){
				oldApi.remove("_id");
			}
			this.apiDao.saveAPIHitory(oldApi);
			
			api.put("isHasHistory", 1);
		}
		
		this.apiDao.updateAPI(api,id);
		
		String noticEmail[] = request.getParameterValues("noticEmail");
		
		String href = "";
		String title =  new StringBuilder()
				.append(belongItem)
				.append("-->")
				.append(belongModule)
				.append("-->")
				.append(name).toString();
		href = Constant.WEB_ROOT +  "api/"+id+"/apiDetail/" ;
		
		
		if(noticEmail != null && noticEmail.length > 0){
			
			//JSONObject one = this.apiDao.findAPIByNameAndCreateTime(name, now);
			
			String mailContent = new StringBuilder()
			.append(title)
			.append("  接口信息请访问：")
			.append("<br>")
			.append("<a href='"+href+"'>"+href+"</a>")
			.append("<br><br>")
			.toString();
			for(String email : noticEmail){
				MailUtils.asynSend(MailUtils.REG_NOREPLY, email,"["+title+"]新API使用信息", mailContent);
				
				JSONObject user = this.userDao.findUserByEmail(email);
				
				this.notify(companyId,user.getString("id"), userId, opreater, "编辑了API", name, id, title);
			}
		}
		
		this.sysDao.saveSysLog(companyId,opreater, "修改["+title+"]接口 ",href);
		
//		response.sendRedirect("/admin.do?method=searchApi&belongItem="+belongItem+"&belongModule="+belongModule);
//		return  null;
		
//		return this.apiDetail(id, "", request, response);
		
		return "redirect:/api/"+id+"/apiDetail/";
	}
	
	/**
	 * API历史列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/apiHistory")
	public String apiHistory(@PathVariable String id,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String page_str = request.getParameter("page");
		int page = CommonUtils.parseInt(page_str, 1);

		Pagination<JSONObject> pagerecords =this.apiDao.findApiHistoryByPage(id,page,20);
		request.setAttribute("pagerecords", pagerecords);
		
		return  "apiHistoryList";
	}
	
	/**
	 * 切换模块
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月11日 下午6:47:17
	 */
	@RequestMapping("ajaxChangeModule")
	public String ajaxChangeModule(
            HttpServletRequest request,HttpServletResponse response) throws Exception {

		response.setContentType("application/json;charset=UTF-8");
		
		String item = request.getParameter("item");
		
		List<JSONObject> moduleList = this.apiDao.findModuleByItemId(item);
		
		JSONObject res = new JSONObject();
		
		for(JSONObject one : moduleList){
			res.put(one.getString("id"), one.getString("name"));
		}
		
		PrintWriter out = response.getWriter(); 
		out.print(res);
		out.flush();
		out.close(); 
		
		return null;
	}
	
	/**
	 * 获取请求结果
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月12日 下午3:52:54
	 */
	@RequestMapping("ajaxParesURLAndGetResult")
	public String ajaxParesURLAndGetResult(
            HttpServletRequest request,HttpServletResponse response) throws Exception {

		response.setContentType("application/json;charset=UTF-8");
		
		String url = request.getParameter("url");
		
		JSONObject res = new JSONObject();
		res.put("result", WebUtils.getHtmlContent(url));
		
		PrintWriter out = response.getWriter(); 
		out.print(res);
		out.flush();
		out.close(); 
		
		return null;
	}
	
	
	/**
	 * 调试API
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/apiTest")
	public String apiTest(@PathVariable String id,
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		JSONObject one = new JSONObject();
		
		one = this.apiDao.findAPIById(id);
		
		String href = Constant.WEB_ROOT +  "api/"+id+"/apiDetail/" ;
		
		String opreater = this.getUser(request,response);
		this.sysDao.saveSysLog(companyId,opreater, "调试["+one.getString("belongItem")+"->"+one.getString("belongModule")+"->"+one.getString("name")+"]接口  ",href);
		
		JSONObject confInf = sysConfDao.findBasicInfo4ItemId(companyId,one.getString("belongItemId"));
		
		request.setAttribute("one", one);
		request.setAttribute("confInf", confInf);
		return "apiTest";
	}
	
	
	
	
}
