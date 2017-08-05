package com.ydj.smart.api.action;

import com.ydj.smart.api.dao.DBTableDao;
import com.ydj.smart.api.dao.SysConfDao;
import com.ydj.smart.api.web.BaseAction;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**  
 *
 * @author : Ares.yi
 * @createTime : 2014-11-10 上午11:13:42 
 * @version : 1.0 
 * @description : 
 *
 */
@Controller
@RequestMapping("data")
public class DataAction extends BaseAction {
	
	@Resource
	private SysConfDao sysConfDao;
	
	private DBTableDao demo = new DBTableDao();

	@RequestMapping("find")
	public String find (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		return "dataDesc";
	}
	
	
	
	/**
	 * 数据库加载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2014-11-23 下午03:49:03
	 */
	@RequestMapping("ajaxChangeItem")
	public String ajaxChangeItem(
            HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String companyId = this.getCompanyId(request, response);

		response.setContentType("application/json;charset=UTF-8");
		
		String itemId = request.getParameter("itemId");
		
		PrintWriter out = response.getWriter(); 
		out.print(sysConfDao.findDBListByItemId(companyId,itemId));
		out.flush();
		out.close(); 
		
		return null;
	}
	
	/**
	 * 数据表加载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2014-11-23 下午03:49:03
	 */
	@RequestMapping("ajaxChangeDB")
	public String ajaxChangeDB(
            HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String companyId = this.getCompanyId(request, response);

		response.setContentType("application/json;charset=UTF-8");
		
		String itemId = request.getParameter("itemId");
		String dbName = request.getParameter("db");
		
		JSONObject dbInfo = sysConfDao.findDBByItemIdAndDBName(companyId,itemId, dbName);
		
		String host = dbInfo.optString("host");
		String port = dbInfo.optString("port");
		String userName = dbInfo.optString("testName");
		String password = dbInfo.optString("testPassword");
		
		PrintWriter out = response.getWriter(); 
		out.print(demo.getAllTables(host,port,userName,password,dbName));
		out.flush();
		out.close(); 
		
		return null;
	}
	
	
	/**
	 * 数据表详情加载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2014-11-23 下午03:49:03
	 */
	@RequestMapping("ajaxChangeTable")
	public String ajaxChangeTable(
            HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String companyId = this.getCompanyId(request, response);

		response.setContentType("application/json;charset=UTF-8");
		
		String itemId = request.getParameter("itemId");
		String dbName = request.getParameter("db");
		String table = request.getParameter("table");
		
		JSONObject dbInfo = sysConfDao.findDBByItemIdAndDBName(companyId,itemId, dbName);
		
		String host = dbInfo.optString("host");
		String port = dbInfo.optString("port");
		String userName = dbInfo.optString("testName");
		String password = dbInfo.optString("testPassword");
		
		PrintWriter out = response.getWriter(); 
		out.print(demo.getTableFileds(host,port,userName,password,dbName, table));
		out.flush();
		out.close(); 
		
		return null;
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ajaxGetItemConf")
	public String ajaxGetVersionList(
			HttpServletRequest request,HttpServletResponse response) throws Exception {

		String companyId = this.getCompanyId(request, response);

		response.setContentType("application/json;charset=UTF-8");

		String itemId = request.getParameter("itemId");

		PrintWriter out = response.getWriter();
		out.print(sysConfDao.findBasicInfo4ItemId(companyId,itemId));
		out.flush();
		out.close();

		return null;
	}
}
