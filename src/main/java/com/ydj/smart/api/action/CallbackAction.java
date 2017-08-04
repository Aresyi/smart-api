/** **/
package com.ydj.smart.api.action;

import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.SysDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.web.BaseAction;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("callback")
public class CallbackAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private SysDao sysDao;
	
	/**
	 * 微信授权
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("wxAuth")
	public String login(String email,String openId) throws Exception{
		JSONObject userByEmail = userDao.findUserByEmail(email);
		userByEmail.put("openId",openId);
		userDao.saveOrUpdateUserOpenId(userByEmail.optString("id"),openId);
		return "wxAuth";
	}
	

	
}
