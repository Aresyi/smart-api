/** **/
package com.ydj.smart.api.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydj.smart.common.tools.WebUtils;


/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-18 下午06:16:22
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("go")
public class BaseAction{

	
	/**
	 * 前往一个JSP页面
	 * @param pageName
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年8月5日 上午10:51:49
	 */
	@RequestMapping("{pageName}/page")
	public String gotoPage(@PathVariable String pageName) throws Exception{
		return pageName;
	}
	
	
	public String getAndSetAttribute(String name,HttpServletRequest request){
		String value = request.getParameter(name);
		request.setAttribute(name, value);
		return value;
	}
	
	public String[] getAndSetAttribute2(String name,HttpServletRequest request){
		String value[] = request.getParameterValues(name);
		request.setAttribute(name, value);
		return value;
	}
	
	public String getUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		String name = StringUtils.base64Decode(CookieUtils.getCookieValue(request, "name"));
		
		Object name = request.getSession().getAttribute("userName");
		
		if(name == null){
			String fromUrl = WebUtils.getRequestURI(request);//WebUtils.getFromUrl(request);
			request.getSession().setAttribute("Referer", fromUrl);
			throw new Exception();
		}
		
		return name.toString();
	}
	
	public String getUserId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		try {
//			String id = CookieUtils.getCookieValue(request, "id");
			String id = request.getSession().getAttribute("userId").toString();
			
			return id;
		} catch (Exception e) {
			throw new Exception();
		}

	}
	
	
	public String getCompanyId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		try {
			String id = request.getSession().getAttribute("companyId").toString();
			return id;
		} catch (Exception e) {
			throw new Exception();
		}

	}
}
