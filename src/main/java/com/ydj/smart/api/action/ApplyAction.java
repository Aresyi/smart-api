/** **/
package com.ydj.smart.api.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydj.smart.api.dao.ApplyDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CommonUtils;
import com.ydj.smart.common.tools.MailUtils;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("apply")
public class ApplyAction extends BaseAction {
	
	@Resource
	private ApplyDao applyDao;
	
	@Resource
	private UserDao userDao;
	
	/**
	 * 申请
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("submit")
	public String submit (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyName = getAndSetAttribute("companyName", request);
//		String webSite = getAndSetAttribute("webSite", request);
		String linkman = getAndSetAttribute("linkman", request);
//		String job = getAndSetAttribute("job", request);
		String email = getAndSetAttribute("email", request);
//		String qq = getAndSetAttribute("qq", request);
		
		if(CommonUtils.isEmptyString(companyName)){
			request.setAttribute("message", "请填写公司名称");
			
			return "apply";
		}
		
		
		if(!CommonUtils.isEmail(email)){
			request.setAttribute("message", "请填写正确的邮箱");
			
			return "apply";
		}
		
		
		JSONObject one = this.applyDao.findApplyByCompanyName(companyName) ;
		
		if(one != null){
			request.setAttribute("message", "请勿重复申请");
			
			return "apply";
		}
		
		JSONObject one2 = this.applyDao.findApplyByEmail(email) ;
		
		if(one2 != null){
			request.setAttribute("message", "邮箱已注册，不可使用");
			
			return "apply";
		}
		
		JSONObject applyInfo = new JSONObject();
		applyInfo.put("companyName", companyName);
//		applyInfo.put("webSite", webSite);
		applyInfo.put("linkman", linkman);
//		applyInfo.put("job", job);
		applyInfo.put("email", email);
//		applyInfo.put("qq", qq);
		applyInfo.put("createTime", System.currentTimeMillis());
		
		
		this.applyDao.saveApplyInfo(applyInfo);
		
		String password = CommonUtils.getRandomNumber(10000000,99999999)+"";
		
	
		MailUtils.asynSend(MailUtils.REG_NOREPLY, email,"申请17Smart成功", "欢迎您的使用，登录账号："+email+" 密码："+password+"(登录后请尽快修改密码)" );
		
		MailUtils.asynSend(MailUtils.REG_NOREPLY, "369415359@qq.com","["+companyName+"]申请17Smart", "companyName:"+companyName+"\n\r linkman:"+linkman+"\n\r email:"+email);
		
		
		String companyId  = this.applyDao.findApplyByCompanyName(companyName).getString("id");
		
		this.addDefAdmin(companyId, email, companyName,password);
		
		
		return "applySuccess";

	}
	
	
	private void addDefAdmin(String companyId,String email,String companyName,String password){
		
		JSONObject user = new JSONObject();
		user.put("companyId", companyId);
		user.put("email", email);
		user.put("name", companyName);
		user.put("roleId", 8686);
		user.put("isAdmin","可以");
		user.put("editeAPI", "可以");
		user.put("password", password);
		user.put("group", "Support");
		user.put("groupId", "583bf3df975a02a14f431691");
		user.put("permissionAPI", "{}");
		user.put("avatar", CommonUtils.getRandomNumber(1, 21)+".jpg");
		user.put("createTime", System.currentTimeMillis());
		user.put("creater", "System");
		
		this.userDao.saveUser(user);
	}
	
}
