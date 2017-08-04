/** **/
package com.ydj.smart.api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.tranb.ocr.utils.Pagination;
import com.ydj.smart.common.nosql.BaseMongoDao;
import com.ydj.smart.common.tools.CommonUtils;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:13:42
 * @version : 1.0
 * @description :
 *
 */
@Repository("apiDao")
public class ApiDao extends BaseMongoDao {

	private DBCollection getModuleCollection() {
		return this.getCollection("module");
	}
	
	private DBCollection getAPICollection() {
		return this.getCollection("api");
	}
	
	private DBCollection getAPIHistoryCollection() {
		return this.getCollection("api_history");
	}
	
	private DBCollection getItemCollection() {
		return this.getCollection("item");
	}
	
	
	/**
	 * 保存项目
	 * @param item
	 */
	public void saveItem(JSONObject item){
		DBCollection itemCollection = this.getItemCollection();
		this.insert(item,itemCollection);
	}
	
	/**
	 * 保存项目模块
	 * @param module
	 */
	public void saveModule(JSONObject module){
		DBCollection moduleCollection = this.getModuleCollection();
		this.insert(module,moduleCollection);
	}
	
	/**
	 * 保存模块API
	 * @param api
	 */
	public void saveAPI(JSONObject api){
		DBCollection apiCollection = this.getAPICollection();
		this.insert(api,apiCollection);
	}
	
	/**
	 * 修改模块API
	 * @param api
	 */
	public void updateAPI(JSONObject api,String id){
		DBCollection apiCollection = this.getAPICollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject obj = new BasicDBObject();
		for(Object key : api.keySet()){
			String _key = key.toString();
			obj.put(_key, api.get(_key));
		}
		apiCollection.update(query,obj);
	}
	
	
	
	/**
	 * 保存模块API历史
	 * @param api
	 */
	public void saveAPIHitory(JSONObject api){
		DBCollection apiHistoryCollection = this.getAPIHistoryCollection();
		this.insert(api,apiHistoryCollection);
	}
	
	/**
	 * 获取指定模块
	 * @return
	 */
	public List<JSONObject> findModuleByItemId(String itemId){
		DBCollection moduleCollection = this.getModuleCollection();
		DBObject query = new BasicDBObject();
		query.put("itemId", itemId);
		
		return this.change2ListJSONObject(moduleCollection.find(query));
	}
	
	/**
	 * 获取指定模块
	 * @return
	 */
	public JSONObject findModuleByItemNameAndName(String companyId,String itemName,String name){
		DBCollection moduleCollection = this.getModuleCollection();
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("itemName", itemName);
		query.put("name", name);
		
		DBObject obj = moduleCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	/**
	 * 获取指定模块
	 * @return
	 */
	public JSONObject findModuleById(String id){
		DBCollection moduleCollection = this.getModuleCollection();
		return this.findById(id, moduleCollection);
	}
	
	/**
	 * 获取指定模块
	 * @return
	 */
	public JSONObject findModuleByItemIdAndName(String companyId,String itemId,String name){
		DBCollection moduleCollection = this.getModuleCollection();
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("itemId", itemId);
		query.put("name", name);
		
		DBObject obj = moduleCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	/**
	 * 查询模块API
	 * @param moduleName
	 * @param limit
	 * @return
	 */
	public List<JSONObject> findAPIByModuleName(String companyId,String moduleName,int limit){
		DBCollection apiCollection = this.getAPICollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("belongModule", moduleName);
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("modifyTime", -1);
		
		return this.change2ListJSONObject(apiCollection.find(query).limit(limit));
	}
	
	/**
	 * 查询项目API
	 * @param itemName
	 * @param limit
	 * @return
	 */
	public List<JSONObject> findAPIByItemName(String companyId,String itemName,int limit){
		DBCollection apiCollection = this.getAPICollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("belongItem", itemName);
		
		return this.change2ListJSONObject(apiCollection.find(query).limit(limit));
	}
	
	/**
	 * 查询指定API
	 * @param id
	 * @return
	 */
	public JSONObject findAPIById(String id){
		DBCollection apiCollection = this.getAPICollection();
		return this.findById(id, apiCollection);
	}
	
	
	/**
	 * 查询指定API
	 * @param id
	 * @return
	 */
	public JSONObject findHistoryAPIById(String id){
		DBCollection apiHistoryCollection = this.getAPIHistoryCollection();
		
		return this.findById(id, apiHistoryCollection);
	}
	
	
	/**
	 * 查询指定API
	 * @param name
	 * @param time
	 * @return
	 */
	public JSONObject findAPIByNameAndCreateTime(String companyId,String name,long time){
		DBCollection apiCollection = this.getAPICollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("name", name);
		query.put("createTime", time);
		
		DBObject obj = apiCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	
	
	
	
	
	
	/**
	 * 查询所有项目
	 * @param moduleName
	 * @param limit
	 * @return
	 */
	public List<JSONObject> getAllItem(String companyId){
		DBCollection itemCollection = this.getItemCollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		
		return this.change2ListJSONObject(itemCollection.find(query));
	}
	
	/**
	 * 查询指定项目
	 * @param moduleName
	 * @param limit
	 * @return
	 */
	public List<JSONObject> getItemByIds(JSONArray ids){
		DBCollection itemCollection = this.getItemCollection();
		
		BasicDBList values = new BasicDBList();
		
		for(int i=0;i<ids.size();i++){
			values.add(new ObjectId(ids.get(i).toString()));
		}
		
		DBObject query = new BasicDBObject();
		query.put("_id", new BasicDBObject("$in", values));
		
		return this.change2ListJSONObject(itemCollection.find(query));
	}
	
	
	/**
	 * 通过名称查询项目
	 * @param name
	 * @return
	 */
	public JSONObject findItemByName(String companyId,String name){
		DBCollection itemCollection = this.getItemCollection();
		DBObject query = new BasicDBObject();
		query.put("name", name);
		query.put("companyId", companyId);
		
		DBObject obj = itemCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	/**
	 * 通过ID查询项目
	 * @param name
	 * @return
	 */
	public JSONObject findItemById(String id){
		DBCollection itemCollection = this.getItemCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject obj = itemCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	/**
	 * API翻页
	 * @param belongItemId
	 * @param belongModuleId
	 * @param isDel
	 * @param keyword
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Pagination<JSONObject> findApiByPage(String companyId,String belongItemId,String belongModuleId,String isDel,String keyword,int page,int pageSize){
		
		DBObject cond = new BasicDBObject();
		cond.put("companyId", companyId);
		if(!CommonUtils.isEmptyString(belongItemId)){
			cond.put("belongItemId", belongItemId);
		}
		if(!CommonUtils.isEmptyString(belongModuleId)){
			cond.put("belongModuleId", belongModuleId);
		}
		if(!CommonUtils.isEmptyString(isDel)){
			cond.put("isDel", isDel);
		}else{
			cond.put("isDel", new BasicDBObject("$eq", null));
		}
		
		if(!CommonUtils.isEmptyString(keyword)){
			Pattern pattern = Pattern.compile("^.*"+keyword+".*$");
			
			DBObject one = new BasicDBObject();
			one.put("name", pattern);
			
			DBObject two = new BasicDBObject();
			two.put("url", pattern);
			
			DBObject three = new BasicDBObject();
			three.put("parameter.para_caseVal", pattern);
			
			ArrayList<DBObject> list = new ArrayList<DBObject>();
			list.add(one);
			list.add(two);
			list.add(three);
			
	        cond.put("$or", list);
		}
		DBCollection apiCollection = this.getAPICollection();
		int count = (int) apiCollection.count(cond);
		
		final Pagination<JSONObject> pagination = new Pagination<JSONObject>();
		List<JSONObject> lists = new ArrayList<JSONObject>();
		
		
		if (count < 1) {
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("modifyTime", -1);
		
		lists = this.change2ListJSONObject( apiCollection.find(cond).sort(orderBy).skip((page-1)*pageSize).limit(pageSize) );

		pagination.setTotalRecords(count);
		pagination.setPage(page);
		pagination.setPageSize(pageSize);
		pagination.setRecords(lists);
		return pagination;
	}
	
	
	/**
	 * API翻页
	 * @param belongItem
	 * @param belongModule
	 * @param keyword
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Pagination<JSONObject> findApiByPage(String companyId,JSONArray items,String keyword,int page,int pageSize){
		
		final Pagination<JSONObject> pagination = new Pagination<JSONObject>();
		List<JSONObject> lists = new ArrayList<JSONObject>();
		
		if(items == null){
			pagination.setTotalRecords(0);
			pagination.setPage(page);
			pagination.setPageSize(pageSize);
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject cond = new BasicDBObject();
		cond.put("companyId",  companyId);
		if(items != null && items.size() > 0){
			cond.put("belongItem",  new BasicDBObject("$in", items));
		}
		
		if(!CommonUtils.isEmptyString(keyword)){
			Pattern pattern = Pattern.compile("^.*"+keyword+".*$");
			
			DBObject one = new BasicDBObject();
			one.put("name", pattern);
			
			DBObject two = new BasicDBObject();
			two.put("url", pattern);
			
			DBObject three = new BasicDBObject();
			three.put("parameter.para_caseVal", pattern);
			
			ArrayList<DBObject> list = new ArrayList<DBObject>();
			list.add(one);
			list.add(two);
			list.add(three);
			
	        cond.put("$or", list);
		}
		DBCollection apiCollection = this.getAPICollection();
		int count = (int) apiCollection.count(cond);
		
		
		if (count < 1) {
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", -1);
		
		lists = this.change2ListJSONObject( apiCollection.find(cond).sort(orderBy).skip((page-1)*pageSize).limit(pageSize) );

		pagination.setTotalRecords(count);
		pagination.setPage(page);
		pagination.setPageSize(pageSize);
		pagination.setRecords(lists);
		return pagination;
	}
	
	
	/**
	 * 按模块翻页查询
	 * @param itemName
	 * @param moduleName
	 * @param page
	 * @param pageSize
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2014-11-21 下午03:53:35
	 */
	public Pagination<JSONObject> findApiByModulePage(String companyId,String itemName,String moduleName,int page,int pageSize){
		
		final Pagination<JSONObject> pagination = new Pagination<JSONObject>();
		List<JSONObject> lists = new ArrayList<JSONObject>();
		
		DBObject cond = new BasicDBObject();
		cond.put("companyId",  companyId);
		cond.put("belongItem",  itemName);
		cond.put("belongModule",  moduleName);
		
		DBCollection apiCollection = this.getAPICollection();
		int count = (int) apiCollection.count(cond);
		
		
		if (count < 1) {
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", -1);
		
		lists = this.change2ListJSONObject( apiCollection.find(cond).sort(orderBy).skip((page-1)*pageSize).limit(pageSize) );

		pagination.setTotalRecords(count);
		pagination.setPage(page);
		pagination.setPageSize(pageSize);
		pagination.setRecords(lists);
		return pagination;
	}
	
	
	/**
	 * API历史翻页
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Pagination<JSONObject> findApiHistoryByPage(String id,int page,int pageSize){
		
		DBObject cond = new BasicDBObject();
		cond.put("hisVersonId", id);
		
		DBCollection apiHistoryCollection = this.getAPIHistoryCollection();
		int count = (int) apiHistoryCollection.count(cond);
		
		final Pagination<JSONObject> pagination = new Pagination<JSONObject>();
		List<JSONObject> lists = new ArrayList<JSONObject>();
		
		
		if (count < 1) {
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("modifyTime", -1);
		
		lists = this.change2ListJSONObject( apiHistoryCollection.find(cond).sort(orderBy).skip((page-1)*pageSize).limit(pageSize) );

		pagination.setTotalRecords(count);
		pagination.setPage(page);
		pagination.setPageSize(pageSize);
		pagination.setRecords(lists);
		return pagination;
	}
	
	
}
