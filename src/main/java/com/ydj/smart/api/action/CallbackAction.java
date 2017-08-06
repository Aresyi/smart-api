/** **/
package com.ydj.smart.api.action;

import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.SysConfDao;
import com.ydj.smart.api.dao.SysDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.HttpUtils;
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

	@Resource
	private SysConfDao sysConfDao;

	/**
	 * 微信授权 直接
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("wxAuthDefault")
	public String wxAuthDefault(String email,String code,String itemId) throws Exception{
		JSONObject userByEmail = userDao.findUserByEmail(email);
		JSONObject conf = sysConfDao.findBasicInfo4ItemId(userByEmail.optString("companyId"), itemId);
		String wxAppId = conf.optString("wxAppId");
		String wxAppSecret = conf.optString("wxAppSecret");

		String res = HttpUtils.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ wxAppId+"&secret="+wxAppSecret+"&code="+code+"&grant_type=authorization_code");
		JSONObject jsonObject = JSONObject.fromObject(res.toString());
		String  openId = jsonObject.optString("openid");
		userByEmail.put("openId",openId);
		userDao.saveOrUpdateUserOpenId(userByEmail.optString("id"),openId,itemId);
		return "wxAuth";
	}


	/**
	 * 微信授权
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("wxAuth")
	public String wxAuth(String email,String openId,String itemId) throws Exception{
		JSONObject userByEmail = userDao.findUserByEmail(email);
		userByEmail.put("openId",openId);
		userDao.saveOrUpdateUserOpenId(userByEmail.optString("id"),openId,itemId);
		return "wxAuth";
	}
	

	
}
