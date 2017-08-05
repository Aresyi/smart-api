/** **/
package com.ydj.smart.api.action;

import com.ydj.smart.api.dao.SysConfDao;
import com.ydj.smart.api.web.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("sys")
public class SysConfigAction extends BaseAction {
	
	@Resource
	private SysConfDao sysConfDao;
	
	/**
	 * 配置中心 选择项目
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:51:46
	 */
	@RequestMapping("settings")
	public String settings(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		return  "sysSetting";
	}

	/**
	 * 配置中心
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:51:46
	 */
	@RequestMapping("settingsByItemId")
	public String settings(
			HttpServletRequest request,HttpServletResponse response,String itemId) throws Exception{
		String companyId = this.getCompanyId(request, response);
		JSONObject confInf = sysConfDao.findBasicInfo4ItemId(companyId,itemId);
		request.setAttribute("confInf", confInf);
		return  "sysSettingItem";
	}



	@RequestMapping("saveSettings")
	public String saveSettings(
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String companyId = this.getCompanyId(request, response);
		
		String itemId = this.getAndSetAttribute("itemId", request);
		String tokenName = this.getAndSetAttribute("tokenName", request);
		String tokenDefValue = this.getAndSetAttribute("tokenDefValue", request);
		
		
		String dbChineseName[] =getAndSetAttribute2("dbChineseName",request);
		String host[] = getAndSetAttribute2("host",request);
		String port[] = getAndSetAttribute2("port",request);
		String dbName[] = getAndSetAttribute2("dbName",request);
		String testName[] = getAndSetAttribute2("testName",request);
		String testPassword[] = getAndSetAttribute2("testPassword",request);
		
		String hostName[] = getAndSetAttribute2("hostName",request);
		String hostUrl[] = getAndSetAttribute2("hostUrl",request);

		String wxAppId = this.getAndSetAttribute("wxAppId", request);
		String wxAppSecret = this.getAndSetAttribute("wxAppSecret", request);

		String version[] = getAndSetAttribute2("version",request);

		JSONArray dbList = new JSONArray();
		if(dbChineseName != null && dbChineseName.length > 0){
			for(int i=0;i<dbChineseName.length;i++){
				JSONObject one = new JSONObject();
				one.put("dbChineseName", dbChineseName[i]);
				one.put("host", host[i]);
				one.put("port", port[i]);
				one.put("dbName", dbName[i]);
				one.put("testName", testName[i]);
				one.put("testPassword", testPassword[i]);
				
				dbList.add(one);
			}
			
		}
		
		JSONArray testSerList = new JSONArray();
		if(hostName != null && hostName.length > 0){
			for(int i=0;i<hostName.length;i++){
				JSONObject one = new JSONObject();
				one.put("hostName", hostName[i]);
				one.put("hostUrl", hostUrl[i]);
				
				testSerList.add(one);
			}
		}

		JSONArray versionList = new JSONArray();
		if(version != null && version.length > 0){
			for(int i=0;i<version.length;i++){
				JSONObject one = new JSONObject();
				try {
					one.put("version", Double.parseDouble(version[i]));
				} catch (NumberFormatException e) {
//					e.printStackTrace();
				}
				versionList.add(one);
			}
		}
		
		this.sysConfDao.saveSysConf4Item(companyId,itemId, tokenName,tokenDefValue,dbList,testSerList,wxAppId,wxAppSecret,versionList);
		
		return  "sysSetting";
	}
}
