/** **/
package com.ydj.smart.api.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.NotifyDao;
import com.ydj.smart.api.dao.UserDao;
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
@RequestMapping("notify")
public class NotifyAction extends BaseAction {
	
	@Resource
    private VelocityEngine velocityEngine;
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private NotifyDao notifyDao;
	
	@Resource
	private UserDao userDao;
	
	/**
	 * 全部通知
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月18日 下午5:22:10
	 */
	@RequestMapping(value="{tid}/notifications",produces = "application/html; charset=UTF-8")
	@ResponseBody
	public String notifications (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=utf-8");  
		
		String companyId = this.getCompanyId(request, response);
		
		String userId = this.getUserId(request,response);
		
		String till = this.getAndSetAttribute("till", request);
		
		@SuppressWarnings("unused")
		String till_id = this.getAndSetAttribute("till_id", request);
		
		long lastTime = System.currentTimeMillis() ;
		boolean isFirest = true;
		if(CommonUtils.isNotEmptyString(till)){
			lastTime = CommonUtils.parseDateString("yyyy-MM-dd HH:mm:ss", till);
			isFirest = false;
		}
		
		List<JSONObject> notifyList = this.notifyDao.findNotifyByLastTime(userId, lastTime);
		
		if(notifyList != null){
			
			List<JSONObject> allUser = this.userDao.getAllUser(companyId);
			
			Map<String,JSONObject> tempUserMap = new HashMap<String,JSONObject>();
			
			for(JSONObject user : allUser){
				String uid = user.getString("id");
				tempUserMap.put(uid, user);
			}
			
			for(JSONObject one : notifyList){
				String uid = one.getString("actorId");
				
				one.put("user", tempUserMap.get(uid));
				one.put("createTime", CommonUtils.getDateString("yyyy-MM-dd HH:mm:ss", one.getLong("createTime")));
			}
		}
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("notifyList", notifyList);
		model.put("isFirest", isFirest);
		
		String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/notifyAll.vm", "utf-8", model);
		
		return  html;
	}
	
	/**
	 * 全读
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月18日 下午9:48:15
	 */
	@RequestMapping("{tid}/notifications/readAll")
	@ResponseBody
	public JSONObject readAll (HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String userId = this.getUserId(request, response);
		
		this.notifyDao.updateReadAll(userId);
		
		JSONObject res = new JSONObject();
		res.put("success", true);
		
		return res;
	}
	
}
