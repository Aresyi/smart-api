package com.ydj.smart.api.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tranb.ocr.utils.Pagination;
import com.ydj.smart.api.dao.PresentDao;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CommonUtils;

/**  
 *
 * @author : Ares.yi
 * @createTime : 2014-11-10 上午11:13:42 
 * @version : 1.0 
 * @description : 
 *
 */
@Controller
@RequestMapping("present")
public class PresentAction extends BaseAction {
	
	@Resource
	private PresentDao presentDao;
	

	@RequestMapping("thanks")
	public String thanks (
	            HttpServletRequest request) throws Exception{
		
		String page_str = request.getParameter("page");
		int page = CommonUtils.parseInt(page_str, 1);
		
		Pagination<JSONObject> pagerecords =this.presentDao.findPresentByPage(page,20);
		request.setAttribute("pagerecords", pagerecords);
		
		
		return "thanks";
	}
	
	
	/**
	 * 保存捐赠记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addPresent")
	public String addPresent (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		//TODO
		return "";	
		}
	
	
	
	
	
	
}
