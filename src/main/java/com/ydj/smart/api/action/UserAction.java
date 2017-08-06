/** **/
package com.ydj.smart.api.action;

import com.ydj.smart.api.constant.Constant;
import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.SysConfDao;
import com.ydj.smart.api.dao.SysDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CommonUtils;
import com.ydj.smart.common.tools.MailUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("user")
public class UserAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private SysDao sysDao;

	@Resource
	private SysConfDao sysConfDao;
	/**
	 * 邀请添加用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月13日 下午6:30:53
	 */
	@RequestMapping("createMember")
	public String createMember (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		List<JSONObject> allUserGroup = this.userDao.getAllUserGroup(companyId);
		List<JSONObject> allItem = this.apiDao.getAllItem(companyId);
		request.setAttribute("allItem", allItem);
		request.setAttribute("allUserGroup", allUserGroup);
		
		return "createMember";
	}
	
	/**
	 * 成员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月13日 下午6:31:42
	 */
	@RequestMapping("member")
	public String member (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		List<JSONObject> allUser = this.userDao.getAllNormalUser(companyId);
		
		Map<String,List<JSONObject>> groupUser = new HashMap<String,List<JSONObject>>();
		
		List<JSONObject> supportList = new ArrayList<JSONObject>(); 
		
		for(JSONObject one : allUser){
			String groupName = one.optString("group");
			
			if(!one.containsKey("avatar")){
				one.put("avatar", CommonUtils.getRandomNumber(1, 21)+".jpg");
			}
			
			if( one.getInt("roleId") == 8686 ){
				supportList.add(one);
				continue;
			}
			
			if(groupUser.containsKey(groupName)){
				groupUser.get(groupName).add(one);
			}else{
				List<JSONObject> list = new ArrayList<JSONObject>();
				
				list.add(one);
				
				groupUser.put(groupName, list);
			}
		}
		
		request.setAttribute("groupUser", groupUser);
		request.setAttribute("supportList", supportList);
		
		return "member";

	}
	
	
	/**
	 * 创建分组
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月13日 下午6:32:48
	 */
	@RequestMapping("createGroup")
	public String createGroup (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		
		String groupName = getAndSetAttribute("subgroup_name",request);
		
		JSONObject userGroup = new JSONObject();
		userGroup.put("companyId", companyId);
		userGroup.put("groupName", groupName);
		userGroup.put("createTime", System.currentTimeMillis());
		userGroup.put("creater", opreater);
		
		this.userDao.saveUserGroup(userGroup);
		
		this.sysDao.saveSysLog(companyId,opreater, "新建["+groupName+"]用户组");
		
		return "redirect:/user/member";
	}
		
	
	/**
	 * 添加用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addUser")
	public String addUser(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		
		String emails[] = getAndSetAttribute2("email",request);
		String roleIds[] = getAndSetAttribute2("role_id",request);
		String subgroupIds[] = getAndSetAttribute2("subgroup_id",request);
		String permissionAPI[] =getAndSetAttribute2("init-projects[]",request);
		
		Map<String,JSONObject> userGroupTempMap = new HashMap<String,JSONObject>();
		
		for(int i = 0 ; i < emails.length ; i++){
			String email = emails[i];
			
			if(!CommonUtils.isEmail(email)){
				continue ;
			}
			
			if(this.userDao.findUserByEmail(email) != null){
//				request.setAttribute("message", "用户名'"+name+"'已存在");
//				return  "userList";
				
				continue;
			}
			
			String groupId = subgroupIds[i];
			String groupName= "";
			
			if(userGroupTempMap.containsKey(groupId)){
				groupName = userGroupTempMap.get(groupId).optString("groupName");
			}else{
				JSONObject userGroup = this.userDao.findUserGroupById(groupId);
				if(userGroup != null){
					groupName = userGroup.optString("groupName");
					userGroupTempMap.put(groupId, userGroup);
				}
			}
			
			String password = email.substring(0,6);//CommonUtils.getRandomNumber(100000,999999)+"";
			JSONObject user = new JSONObject();
			user.put("companyId", companyId);
			user.put("email", email);
			user.put("name", email.substring(0, email.indexOf("@")));
			user.put("roleId", roleIds[i]);
			user.put("password", password);
			user.put("group", groupName);
			user.put("groupId", groupId);
			user.put("permissionAPI", permissionAPI);
			user.put("avatar", CommonUtils.getRandomNumber(1, 19)+".jpg");
			user.put("createTime", System.currentTimeMillis());
			user.put("creater", opreater);
			
			this.userDao.saveUser(user);
			
			String sysName = Constant.getPro("sysName");
			String mailContent = new StringBuilder()
			.append("你在").append(sysName).append("系统使用信息如下:<br>")
			.append("邮箱:"+email)
			.append("<br>")
			.append("密码:"+password)
			.append("<br><br>")
//			.append( ( permissionAPI != null && permissionAPI.length > 0 ) ? "你暂可查看"+user.getJSONArray("permissionAPI").toString()+"系统相关API" : ""  )
//			.append("<br><br>")
			.append("访问地址：<a href='").append(Constant.WEB_ROOT).append("'>").append(Constant.WEB_ROOT).append("</href>")
			.toString();
			MailUtils.asynSend(MailUtils.REG_NOREPLY, email,sysName+"系统使用信息", mailContent);
			
			
			this.sysDao.saveSysLog(companyId,opreater, "添加["+email+"]新用户");
			
		}
		
//		List<JSONObject> allUser = this.apiDao.getAllUser();
//		LocalCache.flushUserCache(allUser);
		
		
		return  "redirect:/user/member";
	}
	
	
	/**
	 * 管理修改用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update/{id}")
	public String userUpdate(@PathVariable String id,
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		JSONObject one = this.userDao.findUserById(id);
		request.setAttribute("one", one);

		List<JSONObject> allUserGroup = this.userDao.getAllUserGroup(companyId);
		
		List<JSONObject> allItem = this.apiDao.getAllItem(companyId);
		request.setAttribute("allItem", allItem);
		request.setAttribute("allUserGroup", allUserGroup);
		
		return  "editeUser";
	}
	
	/**
	 * 编辑用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/settingsOther")
	public String settingsOther(@PathVariable String id,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		
		String roleId = getAndSetAttribute("role",request);
		String groupId = getAndSetAttribute("subgroup_id",request);
		String isEdite = getAndSetAttribute("isEdite",request);
		String permissionAPI[] =getAndSetAttribute2("access-projects[]",request);
		
		
		JSONObject oldUser = this.userDao.findUserById(id);
		
		if(oldUser == null){
			request.setAttribute("message", "用户不存在");
			return  "editeUser";
		}
		
		String groupName = "";
		JSONObject userGroup = this.userDao.findUserGroupById(groupId);
		if(userGroup != null){
			groupName = userGroup.optString("groupName");
		}
		
		this.userDao.updateUser(id, groupName, groupId, roleId, isEdite, permissionAPI, opreater);

//		List<JSONObject> allUser = this.userDao.getAllUser();
//		 
//		LocalCache.flushUserCache(allUser);
		
		this.sysDao.saveSysLog(companyId,opreater, "修改["+oldUser.getString("name")+"]用户");
		
		return  this.member(request, response);
	}
	
	/**
	 * 删除用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("{id}/destroy")
	public String destroyUser(@PathVariable String id,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		
		JSONObject oldUser = this.userDao.findUserById(id);
		
		if(oldUser == null){
			request.setAttribute("message", "用户不存在");
			return  "editeUser";
		}
		
		String name = oldUser.optString("name");
		this.userDao.updateUser4Destroy(id, opreater);
		
//		List<JSONObject> allUser = this.userDao.getAllUser();
//		LocalCache.flushUserCache(allUser);
		
		this.sysDao.saveSysLog(companyId,opreater, "删除["+name+"]用户");
		
		return  this.member(request, response);
	}
	
	/**
	 * 个人设置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:51:46
	 */
	@RequestMapping("settings")
	public String settings(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userId = this.getUserId(request,response);
		
		JSONObject user = this.userDao.findUserById(userId);
		
		request.setAttribute("user", user);
		
		return  "mySetting";
	}

	/**
	 * 个人设置 获取微信绑定地址
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:51:46
	 */
	@RequestMapping("getWxAuthUrl")
	@ResponseBody
	public JSONObject getWxAuthUrl(
			HttpServletRequest request,HttpServletResponse response,String itemId) throws Exception{
		String userId = this.getUserId(request,response);

		JSONObject result = new JSONObject();
		JSONObject user = this.userDao.findUserById(userId);
		JSONObject sysConf = sysConfDao.findBasicInfo4ItemId(user.optString("companyId"), itemId);
		String wxAppId = sysConf.optString("wxAppId");
		String wxAuthCallackUrl = sysConf.optString("wxAuthCallackUrl");

		if(CommonUtils.isEmptyString(wxAppId)){
			result.put("errorMsg","未配置微信不能绑定");
			return result;
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxAppId+"&redirect_uri="+wxAuthCallackUrl+"%3fitemId%3d"+itemId+"%26email%3d"+user.optString("email")+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		String encodeUrl = URLEncoder.encode(url);
		request.setAttribute("url",url);
		request.setAttribute("encodeUrl",encodeUrl);

		result.put("url",url);
		result.put("encodeUrl",encodeUrl);

		return  result;
	}
	
	/**
	 * 保存个人设置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:51:58
	 */
	@RequestMapping("saveSettings")
	public String saveSettings(
			HttpServletRequest request,HttpServletResponse response) throws Exception{

		String companyId = this.getCompanyId(request, response);
		String userId = this.getUserId(request,response);
		
		String nickname = this.getAndSetAttribute("nickname", request);
		
		this.userDao.updateUserName(companyId,nickname, userId);
		
		return  this.settings(request, response);
	}
	
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:54:47
	 */
	@RequestMapping("updatePass")
	public String updatePass(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		return  "updatePass";
	}
	
	/**
	 * 保存新密码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:51:58
	 */
	@RequestMapping("savePass")
	public String savePass(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userId = this.getUserId(request,response);
		
		
		String oldPassword = this.getAndSetAttribute("old_password", request);
		String password = this.getAndSetAttribute("password", request);
		
		JSONObject user = this.userDao.findUserById(userId);

		if(!user.getString("password").equals(oldPassword)){
			request.setAttribute("message", "原密码错误");
			return "updatePass";
		}
		
		this.userDao.updatePass(password, userId);
		
		request.setAttribute("message", "密码修改成功");
		
		return  "updatePass";
	}
}
