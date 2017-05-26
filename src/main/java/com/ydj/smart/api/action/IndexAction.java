/** **/
package com.ydj.smart.api.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydj.smart.api.constant.Constant;
import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.SysDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CookieUtils;
import com.ydj.smart.common.tools.StringUtils;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("index")
public class IndexAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private SysDao sysDao;
	
	/**
	 * 登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	public String login() throws Exception{
		return "login";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("signIn")
	public String signIn (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		JSONObject user = null ;
		
		
		if(Constant.getPro("admin.email").equals(email) ){
			user = new JSONObject();
			user.put("id", "5799bf52975a1224e9f06648");
			user.put("name", "超级管理员");
			user.put("email", email);
			user.put("password", Constant.getPro("admin.pass"));
			user.put("avatar", "1.jpg");
			user.put("companyId", "5909aafee89739466bf0da0c");
		}else{
			user = this.userDao.findUserByEmail(email);
		}
		
		
		
		if(user == null || !user.getString("password").equals(pass) || (user.containsKey("destroy") && user.getInt("destroy") == 1)){
			request.setAttribute("message", "用户名密码错误");
			return "login";
		}
	
		String name = user.getString("name");
		String userId = user.getString("id");
		String companyId = user.getString("companyId");
		
		HttpSession session = request.getSession();
		
		response.addCookie(CookieUtils.newCookie("name",StringUtils.base64Encode(name)));
		response.addCookie(CookieUtils.newCookie("avatar",user.optString("avatar")));
		response.addCookie(CookieUtils.newCookie("id",userId));
		response.addCookie(CookieUtils.newCookie("email",StringUtils.base64Encode(email)));
		response.addCookie(CookieUtils.newCookie("roleId",user.optString("roleId")));
		response.addCookie(CookieUtils.newCookie("isEdite",user.optString("isEdite")));
		
		this.sysDao.saveSysLog(companyId,name, "登录["+Constant.getPro("sysName")+"]");
		
		session.setAttribute("userName",name);
		session.setAttribute("userId",userId);
		session.setAttribute("companyId",companyId);
		session.setAttribute(Constant.WEBSOCKET_USERID,userId);
		
		Object referer = session.getAttribute("Referer");
		if(referer != null  ){
			String s = referer.toString();
			if(!"".equals(s) && !s.contains("login") && !s.contains("signOut")){
				response.sendRedirect(s);
				return null;
			}
		}
		
		
//		String[] ary = {"api","api_history","comment","item","message","module","notify","user_group"};
		
//		for(String s : ary){
//			this.userDao.updateSetAllCompanyId(s,companyId);
//		}
		
	
//		return "redirect:/index/home";
		
		return "redirect:/api/searchApi/";

	}
	
	/**
	 * 主页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月4日 下午2:58:47
	 */
	@RequestMapping("home")
	public String home (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String name = this.getUser(request, response);
		
		JSONObject user = null ;
		
		
		if("超级管理员".equals(name) ){
			user = new JSONObject();
			user.put("name", "超级管理员");
			user.put("avatar", "1.jpg");
		}else{
			user = this.userDao.findUserByName(companyId,name);
		}
		
		request.setAttribute("user", user);
		
		return "home";

	}
	
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("signOut")
	public String signOut(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		CookieUtils.removeCookie(response, "adminuser");
		request.getSession().invalidate();
		
		return  "login";

	}
	
}
