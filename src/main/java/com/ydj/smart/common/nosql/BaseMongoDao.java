/** **/
package com.ydj.smart.common.nosql;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import net.sf.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2013-4-22 上午11:47:54
 * @version : 1.0
 * @description :
 *
 */
@Repository
public  class BaseMongoDao{

	@Resource
	private DB mongoDB;

	public DB getMongoDB() {
		return mongoDB;
	}

	public void setMongoDB(DB mongoDB) {
		this.mongoDB = mongoDB;
	}
	
	/**
	 * 保存
	 * @param json
	 * @param dbc
	 */
	public void insert(JSONObject json,DBCollection dbc){
		DBObject obj = new BasicDBObject();
		for(Object key : json.keySet()){
			String _key = key.toString();
			obj.put(_key, json.get(_key));
		}
		dbc.insert(obj);
	}
	
	
	/**
	 * 查询指定记录
	 * @param id
	 * @return
	 */
	public JSONObject findById(String id,DBCollection dbc){
		
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject obj = dbc.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	
	/**
	 * 获取自增长ID
	 * @param idValue
	 * @return
	 */
	public int getNextId(String idValue) {
	    String sequenceCollectionName = "seq";
	    String sequenceFieldName = "seq"; 

	    DBCollection seq = mongoDB.getCollection(sequenceCollectionName);

	    DBObject query = new BasicDBObject();
	    query.put("_id", idValue);

	    DBObject change = new BasicDBObject(sequenceFieldName, 1);
	    DBObject update = new BasicDBObject("$inc", change); 

	    synchronized (BaseMongoDao.class) {//更安全点吧，尽管话说findAndModify操作是线程安全的~~
	    	DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
	 	    
	 	    String _nextId = res.get(sequenceFieldName).toString();
	 	    int nextId = (int)(Double.valueOf(_nextId).doubleValue());
	 	    
	 	    return nextId;
		}
	   
	}
	
	/**
	 * 获取指定集合
	 * @param collectionName
	 * @return
	 */
	public  DBCollection getCollection(String collectionName){
		return this.getMongoDB().getCollection(collectionName);
	}
	
	/**
	 * 转为List<T>
	 * 
	 * @param <T>
	 * @param dbCursor
	 * @return
	 */
	public <T> List<T> change2ListBean(DBCursor dbCursor,Class<T> clazz){
		List<T> res  = new LinkedList<T>();
		if(dbCursor != null){
			List<DBObject> objList = dbCursor.toArray();
			for(DBObject o : objList){
				T bean = (T)change2Bean(o,clazz);
				res.add(bean);
			}
			dbCursor.close();
		}
		return res;
	}
	
	/**
	 * 转为List<JSONObject>
	 * 
	 * @param dbCursor
	 * @return
	 */
	public List<JSONObject> change2ListJSONObject(DBCursor dbCursor){
		List<JSONObject> res  = new LinkedList<JSONObject>();
		if(dbCursor != null){
//			List<DBObject> objList = dbCursor.toArray();
//			for(DBObject o : objList){
//				JSONObject json = JSONObject.fromObject(o);
//				res.add(json);
//			}
			
			while(dbCursor.hasNext()){
				DBObject o = dbCursor.next();
				Object _obj = o.get("_id");
				o.put("id", _obj.toString());
				JSONObject json = JSONObject.fromObject(o);
				res.add(json);
			}
			
			dbCursor.close();
		}
		return res;
	}
	
	/**
	 * 转为List<Integer>
	 * 
	 * @param dbCursor
	 * @param field
	 * @return
	 */
	public List<Integer> change2ListInteger(DBCursor dbCursor,String field){
		List<Integer> res  = new LinkedList<Integer>();
		if(dbCursor != null){
			List<DBObject> objList = dbCursor.toArray();
			for(DBObject o : objList){
				if(!o.containsField(field)){
					throw new IllegalArgumentException(" can't find field :'"+field+"' !");
				}
				res.add(Integer.valueOf(o.get(field).toString()));
			}
			dbCursor.close();
		}
		return res;
	}
	
	/**
	 * 转为Bean
	 * @param <T>
	 * @param obj
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T change2Bean(DBObject obj,Class<T> clazz){
		if(obj == null){
			return null;
		}
		Object _obj = obj.get("_id");
		obj.put("id", _obj.toString());
		JSONObject json = JSONObject.fromObject(obj);
		return (T) JSONObject.toBean(json, clazz);
	}
	
	
	/**
	 * 修复老数据
	 * 
	 * @param collectionName
	 * @param companyId
	 *
	 * @author : Ares.yi
	 * @createTime : 2017年5月4日 上午10:54:02
	 */
	public void updateSetAllCompanyId(String collectionName,String companyId){
		
		DBCollection collection = this.getCollection(collectionName);
		
		DBObject query = new BasicDBObject();
		
		DBObject res = new BasicDBObject();
		res.put("companyId", companyId);
		
		BasicDBObject doc = new BasicDBObject();  
		doc.put("$set", res);
		
		int n = collection.updateMulti(query, doc).getN();
		System.out.println("updateSetAllCompanyId("+collectionName+"):"+n);
	}
	
}
