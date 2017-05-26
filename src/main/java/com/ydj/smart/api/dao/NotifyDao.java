/** **/
package com.ydj.smart.api.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.tranb.ocr.utils.Pagination;
import com.ydj.smart.common.nosql.BaseMongoDao;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:13:42
 * @version : 1.0
 * @description :
 *
 */
@Repository("notifyDao")
public class NotifyDao extends BaseMongoDao {

	private DBCollection getNotifyCollection() {
		return this.getCollection("notify");
	}
	
	
	/**
	 * 保存用户通知
	 * 
	 * @param notify
	 */
	public void saveNotify(JSONObject notify){
		DBCollection notifyCollection = this.getNotifyCollection();
		this.insert(notify,notifyCollection);
	}
	
	/**
	 * 查询通知
	 * @param userId
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月7日 上午10:29:57
	 */
	public List<JSONObject> findUnReadNotifyByUserId(String userId){
		DBCollection notifyCollection = this.getNotifyCollection();
		DBObject query = new BasicDBObject();
		query.put("userId", userId);
		query.put("unRead", 1);
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", -1);
		
		return this.change2ListJSONObject(notifyCollection.find(query).sort(orderBy).limit(5));
	}
	
	/**
	 * 通知查询
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Pagination<JSONObject> findNotifyByPage(String userId,int page,int pageSize){
		DBCollection notifyCollection = this.getNotifyCollection();
		
		DBObject query = new BasicDBObject();
		query.put("userId", userId);
		
		int count = (int) notifyCollection.count(query);
		
		final Pagination<JSONObject> pagination = new Pagination<JSONObject>();
		List<JSONObject> lists = new ArrayList<JSONObject>();
		
		
		if (count < 1) {
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", -1);
		
		lists = this.change2ListJSONObject( notifyCollection.find(query).sort(orderBy).skip((page-1)*pageSize).limit(pageSize) );

		pagination.setTotalRecords(count);
		pagination.setPage(page);
		pagination.setPageSize(pageSize);
		pagination.setRecords(lists);
		return pagination;
	}
	
	/**
	 * 获取未读消息数
	 * @param userId
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月18日 下午6:01:19
	 */
	public int getUnReadNews(String userId){
		DBCollection notifyCollection = this.getNotifyCollection();
		
		DBObject query = new BasicDBObject();
		query.put("userId", userId);
		query.put("unRead", 1);
		
		int count = (int) notifyCollection.count(query);
		
		return count;
	}
	
	
	/**
	 * 查询通知
	 * @param userId
	 * @param lastTime
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月7日 上午10:29:57
	 */
	public List<JSONObject> findNotifyByLastTime(String userId,long lastTime){
		DBCollection notifyCollection = this.getNotifyCollection();
		DBObject query = new BasicDBObject();
		query.put("userId", userId);
		query.put("createTime", new BasicDBObject("$lt", lastTime));
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", -1);
		
		return this.change2ListJSONObject(notifyCollection.find(query).sort(orderBy).limit(20));
	}
	
	
	/**
	 * 全读
	 * @param userId
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月18日 下午10:02:08
	 */
	public void updateReadAll(String userId){
		DBCollection notifyCollection = this.getNotifyCollection();
		DBObject query = new BasicDBObject();
		query.put("userId", userId);
		
		DBObject res = new BasicDBObject();
		res.put("unRead", 0);
		res.put("readTime", System.currentTimeMillis());
		
		BasicDBObject doc = new BasicDBObject();  
		doc.put("$set", res);
		
		notifyCollection.update(query, doc,false,true);
	}
	
	/**
	 * 读取通知
	 * @param userId
	 * @param id
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月19日 上午10:14:59
	 */
	public void update4Read(String userId,String id){
		DBCollection notifyCollection = this.getNotifyCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		query.put("userId", userId);
		
		DBObject res = new BasicDBObject();
		res.put("unRead", 0);
		res.put("readTime", System.currentTimeMillis());
		
		BasicDBObject doc = new BasicDBObject();  
		doc.put("$set", res);
		
		notifyCollection.update(query, doc,false,true);
	}
}
