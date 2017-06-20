/** **/
package com.ydj.smart.api.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.SysDao;
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
@RequestMapping("members")
public class MembersAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private SysDao sysDao;
	
	/**
	 * 查看其他成员信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月13日 下午6:30:53
	 */
	@RequestMapping("/{memberId}")
	public String memberProfile (@PathVariable String memberId,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		JSONObject user = this.userDao.findUserById(memberId);
		
		long time = System.currentTimeMillis() - 3 * 3600 * 1000L;
		
		request.setAttribute("user", user);
		request.setAttribute("time", CommonUtils.getDateString("yyyy-MM-dd HH:mm:ss+08:00", time));
		
		
		return "memberProfile";
	}
	
	
}
