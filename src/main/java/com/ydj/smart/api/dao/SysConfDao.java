/** **/
package com.ydj.smart.api.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.ydj.smart.common.nosql.BaseMongoDao;
import com.ydj.smart.common.tools.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:13:42
 * @version : 1.0
 * @description :
 *
 */
@Repository("sysConfDao")
public class SysConfDao extends BaseMongoDao {

	private DBCollection getSysConf4ItemCollection() {
		return this.getCollection("sys_conf_item");
	}
	
	
	/**
	 * 保存sysConf4Item
	 * 
	 * @param companyId
	 * @param itemId
	 * @param sysConf4Item
	 */
	public void saveSysConf4Item(String companyId,String itemId,JSONObject sysConf4Item){
		DBCollection dbc = this.getSysConf4ItemCollection();
		
		DBObject q = new BasicDBObject();
		q.put("companyId", companyId);
		q.put("itemId", itemId);
		
		DBObject res = new BasicDBObject();
		for(Object key : sysConf4Item.keySet()){
			String _key = key.toString();
			res.put(_key, sysConf4Item.get(_key));
		}
		
		BasicDBObject doc = new BasicDBObject();  
		doc.put("$set", res);
		
		dbc.update(q, doc, true, false);
//		this.insert(sysConf4Item,dbc);
	}
	
	/**
	 * 保存sysConf4Item
	 * 
	 * @param companyId
	 * @param itemId
	 * @param tokenName
	 * @param tokenDefValue
	 * @param dbList
	 * @param testSerList
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月27日 下午5:23:02
	 */
	public void saveSysConf4Item(String companyId,String itemId, String tokenName,String tokenDefValue,JSONArray dbList,JSONArray testSerList,
								 String wxAppId,String wxAppSecret,String wxAuthCallackUrl,String wxTemplateId,String wxTemplateData,
								 JSONArray versionList){
		DBCollection dbc = this.getSysConf4ItemCollection();
		
		DBObject q = new BasicDBObject();
		q.put("companyId", companyId);
		q.put("itemId", itemId);

		{
			//清空list list类型 set 失败 故先删除在add
			DBObject res = new BasicDBObject();
			res.put("dbList", 1);
			res.put("testSerList", 1);
			res.put("versionList", 1);
			BasicDBObject doc = new BasicDBObject();
			doc.put("$unset", res);
			dbc.update(q, doc, true, false);
		}

		if(CommonUtils.isNotEmptyString(tokenName)){
			DBObject res = new BasicDBObject();

			res.put("tokenName", tokenName);
			res.put("tokenDefValue", tokenDefValue);

			BasicDBObject doc = new BasicDBObject();
			doc.put("$set", res);

			dbc.update(q, doc, true, false);
		}
		
		if(dbList != null && dbList.size()>0){
			DBObject res = new BasicDBObject();
			
			DBObject o = new BasicDBObject();
			o.put("$each", dbList);
			res.put("dbList", o);
			
			BasicDBObject doc = new BasicDBObject();  
			doc.put("$addToSet", res);
			
			dbc.update(q, doc, true, false);
		}
		

		if(testSerList != null && testSerList.size()>0){
			DBObject res = new BasicDBObject();
			
			DBObject o = new BasicDBObject();
			o.put("$each", testSerList);
			res.put("testSerList", o);
			
			BasicDBObject doc = new BasicDBObject();  
			doc.put("$addToSet", res);
			
			dbc.update(q, doc, true, false);
		}

		if(CommonUtils.isNotEmptyString(wxAppId)){
			DBObject res = new BasicDBObject();

			res.put("wxAppId", wxAppId);
			res.put("wxAppSecret", wxAppSecret);
			res.put("wxAuthCallackUrl", wxAuthCallackUrl);
			res.put("wxTemplateId", wxTemplateId);
			res.put("wxTemplateData", wxTemplateData);

			BasicDBObject doc = new BasicDBObject();
			doc.put("$set", res);

			dbc.update(q, doc, true, false);
		}

		if(versionList != null && versionList.size()>0){
			DBObject res = new BasicDBObject();

			DBObject o = new BasicDBObject();
			o.put("$each", versionList);
			res.put("versionList", o);

			BasicDBObject doc = new BasicDBObject();
			doc.put("$addToSet", res);

			dbc.update(q, doc, true, false);
		}
		

	}

	public void addSysConfVersionList4Item(String companyId,String itemId,JSONArray versionList){
		DBCollection dbc = this.getSysConf4ItemCollection();

		DBObject q = new BasicDBObject();
		q.put("companyId", companyId);
		q.put("itemId", itemId);

		if(versionList != null && versionList.size()>0){
			DBObject res = new BasicDBObject();

			DBObject o = new BasicDBObject();
			o.put("$each", versionList);
			res.put("versionList", o);

			BasicDBObject doc = new BasicDBObject();
			doc.put("$addToSet", res);

			dbc.update(q, doc, true, false);
		}

	}

	public void updateSysConfVersionList4Item(String companyId,String itemId,JSONArray versionList){
		DBCollection dbc = this.getSysConf4ItemCollection();

		DBObject q = new BasicDBObject();
		q.put("companyId", companyId);
		q.put("itemId", itemId);

		{
			//清空list list类型 set 失败 故先删除在add
			DBObject res = new BasicDBObject();
			res.put("versionList", 1);
			BasicDBObject doc = new BasicDBObject();
			doc.put("$unset", res);
			dbc.update(q, doc, true, false);
		}

		if(versionList != null && versionList.size()>0){
			DBObject res = new BasicDBObject();

			DBObject o = new BasicDBObject();
			o.put("$each", versionList);
			res.put("versionList", o);

			BasicDBObject doc = new BasicDBObject();
			doc.put("$addToSet", res);

			dbc.update(q, doc, true, false);
		}

	}
	
	
	/**
	 * 查询指定项目的所有数据库
	 * 
	 * @param companyId
	 * @param itemId
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月27日 下午6:06:31
	 */
	@SuppressWarnings("unchecked")
	public List<JSONObject> findDBListByItemId(String companyId,String itemId){
		DBCollection dbc = this.getSysConf4ItemCollection();
		
		DBObject q = new BasicDBObject();
		q.put("companyId", companyId);
		q.put("itemId", itemId);
		
		DBObject fields =  new BasicDBObject();
		fields.put("dbList", 1);
		
		DBObject res = dbc.findOne(q, fields);
		
		if(res != null && res.containsField("dbList")){
			List<JSONObject> list = (List<JSONObject>)res.get("dbList");
			return list;
		}
		
		return new ArrayList<JSONObject>();
	}
	
	
	/**
	 * 获取项目配置基本信息
	 * 
	 * @param companyId
	 * @param itemId
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月27日 下午6:55:56
	 */
	public JSONObject findBasicInfo4ItemId(String companyId,String itemId){
		DBCollection dbc = this.getSysConf4ItemCollection();
		
		DBObject q = new BasicDBObject();
		q.put("companyId", companyId);
		q.put("itemId", itemId);
		
		DBObject fields =  new BasicDBObject();
//		fields.put("dbList", 0);
		
		DBObject res = dbc.findOne(q, fields);

		JSONObject jsonObject = this.change2Bean(res, JSONObject.class);
		if(jsonObject == null){
			return null;
		}
		//特殊字段排序 版本list排序
		JSONArray versionList = jsonObject.optJSONArray("versionList");
		if(versionList!=null && !versionList.isEmpty()){
			Collections.sort(versionList, new Comparator() {
				@Override
				public int compare(Object o1, Object o2) {
					if(o1 instanceof JSONObject && o2 instanceof JSONObject){
						double v = ((JSONObject) o2).optDouble("version") - ((JSONObject) o1).optDouble("version");
						return (int)Math.floor(v*10000);
					}
					return 0;
				}
			});
		}

		return jsonObject;
	}
	
	/**
	 * 获取数据库信息
	 * 
	 * @param companyId
	 * @param itemId
	 * @param dbName
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月27日 下午6:33:06
	 */
	public JSONObject findDBByItemIdAndDBName(String companyId,String itemId,String dbName){
		DBCollection dbc = this.getSysConf4ItemCollection();
		
		DBObject q = new BasicDBObject();
		q.put("companyId", companyId);
		q.put("itemId", itemId);
		q.put("dbList.dbName", dbName);
		
		DBObject fields =  new BasicDBObject();
		fields.put("dbList.$", 1);//仅返回符合条件的那个数组节点
		
		DBObject res = dbc.findOne(q, fields);
		JSONObject json = this.change2Bean(res,JSONObject.class);
		
		return json.getJSONArray("dbList").getJSONObject(0);
	}
}
