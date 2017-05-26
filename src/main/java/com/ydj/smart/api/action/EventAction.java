package com.ydj.smart.api.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tranb.ocr.utils.Pagination;
import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.SysDao;
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
@RequestMapping("event")
public class EventAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private SysDao sysDao;

	/**
	 * 日志列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysLog")
	public String sysLogList(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String page_str = request.getParameter("page");
		int page = CommonUtils.parseInt(page_str, 1);

		Pagination<JSONObject> pagerecords =this.sysDao.findSysLogByPage(companyId,page,30);
		request.setAttribute("pagerecords", pagerecords);
		
		List<JSONObject> allItem = this.apiDao.getAllItem(companyId);
		
		request.setAttribute("allItem", allItem);
		
		return  "sysLogList";
	}
}
