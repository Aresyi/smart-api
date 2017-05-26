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
import com.ydj.smart.api.web.BaseAction;
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
@RequestMapping("item")
public class ItemAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private SysDao sysDao;
	
	
	@RequestMapping("createItem")
	public String createItem (
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		return "createItem";

	}
	
	
	@RequestMapping("/{itemId}/createModule")
	public String createModule (@PathVariable String itemId,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<JSONObject> moduleList = this.apiDao.findModuleByItemId(itemId);
		
		request.setAttribute("moduleList", moduleList);
		request.setAttribute("itemId", itemId);
		
		return "createModule";

	}
	
	
	/**
	 * 添加新新项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addItem")
	public String addItem (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String opreater = this.getUser(request,response);
		
		String message = null;
		
		String name = getAndSetAttribute("project_name",request);
		String des = getAndSetAttribute("project_desc",request);
		
		if(StringUtils.isEmpty(name)){
			message = "请填写接口名称";
			request.setAttribute("message", message);
			return  "createItem";
		}
		
		if(this.apiDao.findItemByName(companyId,name) != null){
			message = "项目名称‘"+name+"’已经存在";
			request.setAttribute("message", message);
			return  "createItem";
		}
		
		
		if(StringUtils.isEmpty(des)){
			message = "请填写接口说明";
			request.setAttribute("message", message);
			return  "createItem";
		}
		
		
		
		JSONObject item = new JSONObject();
		item.put("companyId", companyId);
		item.put("name", name);
		item.put("des", des);
		item.put("creater", opreater);
		item.put("createTime", System.currentTimeMillis());
		
		this.apiDao.saveItem(item);
		
		this.sysDao.saveSysLog(companyId,opreater, "添加["+name+"]新项目");
		
		
		
		return  "createItem";
	}
	
	
	/**
	 * 添加项目模块
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addModule")
	public String addModule (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		String opreater = this.getUser(request,response);
		
		String name = getAndSetAttribute("name",request);
		String itemId = getAndSetAttribute("itemId",request);
		
		if(StringUtils.isEmpty(itemId)){
			request.setAttribute("message", "请选择项目");
			return  "createModule";
		}
		
		if(StringUtils.isEmpty(name)){
			request.setAttribute("message", "请填写模块名称");
			return  "createModule";
		}
		
		if(this.apiDao.findModuleByItemIdAndName(companyId,itemId, name) != null){
			List<JSONObject> moduleList =  this.apiDao.findModuleByItemId(itemId);
			
			request.setAttribute("moduleList",moduleList);
			request.setAttribute("message", "名称‘"+name+"’已存在");
			return  "createModule";
		}
		
		JSONObject item = this.apiDao.findItemById(itemId);
		
		JSONObject module = new JSONObject();
		module.put("companyId",companyId );
		module.put("name", name);
		module.put("itemId", itemId);
		module.put("itemName", item.getString("name"));
		module.put("creater", opreater);
		module.put("createTime", System.currentTimeMillis());
			
		this.apiDao.saveModule(module);
			
		List<JSONObject> moduleList =  this.apiDao.findModuleByItemId(itemId);
		
		request.setAttribute("moduleList",moduleList);
		
		this.sysDao.saveSysLog(companyId,opreater, "添加["+name+"]新项目模块");
		
		return  "createModule";

	}
	
}
