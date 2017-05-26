/** **/
package com.ydj.smart.api.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.ydj.smart.common.nosql.BaseMongoDao;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-11-8 下午04:38:02
 * @version : 1.0
 * @description :
 *
 */
@Repository("messageDao")
public class MessageDao extends BaseMongoDao {

	private DBCollection getMessageCollection() {
		return this.getCollection("message");
	}
	
	/**
	 * 保存消息信息
	 * @param message
	 */
	public void saveMessage(JSONObject message){
		DBCollection messageCollection = this.getMessageCollection();
		this.insert(message,messageCollection);
	}
	
	/**
	 * 查询所有消息信息
	 * @param moduleName
	 * @param limit
	 * @return
	 */
	public List<JSONObject> getAllMessage(String companyId){
		DBCollection messageCollection = this.getMessageCollection();
		
		DBObject cond = new BasicDBObject();
		cond.put("isDel", new BasicDBObject("$eq", null));
		cond.put("companyId", companyId);
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("modifyTime", -1);
		
		return this.change2ListJSONObject(messageCollection.find(cond).sort(orderBy));
	}
	
	/**
	 * 查询指定信息
	 * @param id
	 * @return
	 */
	public JSONObject findMessageById(String id){
		DBCollection messageCollection = this.getMessageCollection();
		
		return this.findById(id, messageCollection);
	}
	
	/**
	 * 查询指定信息
	 * @param createUid
	 * @param createTime
	 * @return
	 */
	public JSONObject findMessageBy(String createUid,long createTime){
		DBCollection messageCollection = this.getMessageCollection();
		DBObject query = new BasicDBObject();
		query.put("createUserId", createUid);
		query.put("createTime", createTime);
		
		DBObject obj = messageCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	
	/**
	 * 修改信息
	 * @param message
	 * @param id
	 */
	public void updateMessage(JSONObject message,String id){
		DBCollection messageCollection = this.getMessageCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject obj = new BasicDBObject();
		for(Object key : message.keySet()){
			String _key = key.toString();
			obj.put(_key, message.get(_key));
		}
		messageCollection.update(query,obj);
	}
}
