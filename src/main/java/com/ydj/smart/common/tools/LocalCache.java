/** **/
package com.ydj.smart.common.tools;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.web.context.ContextLoader;

import com.ydj.smart.api.dao.ApiDao;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-15 下午04:08:25
 * @version : 1.0
 * @description :
 *
 */
//@Component
public class LocalCache {
	
//	@Resource
//	private static ApiDao apiDao;
	
	public static List<JSONObject> allItem = new ArrayList<JSONObject>();
	public static List<JSONObject> allModule = new ArrayList<JSONObject>();
	public static List<JSONObject> allUser = new ArrayList<JSONObject>();
	

	
	static {
		ApiDao apiDao = ContextLoader.getCurrentWebApplicationContext().getBean(
			"apiDao", ApiDao.class);
		
//		allItem = apiDao.getAllItem();
//		allModule = apiDao.getAllModule();
//		allUser = apiDao.getAllUser();
	}

	public static void flushItemCache(List<JSONObject> items){
		allItem.clear();
		allItem.addAll(items);
	}
	
	public static void flushModuleCache(List<JSONObject> modules){
		allModule.clear();
		allModule.addAll(modules);
	}
	
	public static void flushUserCache(List<JSONObject> users){
		allUser.clear();
		allUser.addAll(users);
	}
	
	public static JSONObject getUser(String name){
		
		for(JSONObject one : allUser){
			if(name.equals(one.getString("name"))){
				return one;
			}
		}
		
		return new JSONObject();
	}
}
