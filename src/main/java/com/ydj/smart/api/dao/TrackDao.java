/** **/
package com.ydj.smart.api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

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
@Repository("trackDao")
public class TrackDao extends BaseMongoDao {

	private DBCollection getTrackCollection() {
		return this.getCollection("track");
	}
	
	/**
	 * 保存足迹
	 * @param track
	 */
	public void saveTrack(JSONObject track){
		DBCollection trackCollection = this.getTrackCollection();
		this.insert(track,trackCollection);
	}
	
	/**
	 * 修改足迹
	 * @param track
	 */
	public void updateTrack(JSONObject track,String id){
		DBCollection trackCollection = this.getTrackCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject obj = new BasicDBObject();
		for(Object key : track.keySet()){
			String _key = key.toString();
			obj.put(_key, track.get(_key));
		}
		trackCollection.update(query,obj);
	}
	
	/**
	 * 查询指定足迹
	 * @param id
	 * @return
	 */
	public JSONObject findTrackById(String id){
		DBCollection trackCollection = this.getTrackCollection();
		return this.findById(id, trackCollection);
	}
	
	
	/**
	 * 查询指定足迹
	 * @param belongItem
	 * @param time
	 * @return
	 */
	public JSONObject findTrackByItemAndCreateTime(String companyId,String belongItem,long time){
		DBCollection trackCollection = this.getTrackCollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("belongItemId", belongItem);
		query.put("createTime", time);
		
		DBObject obj = trackCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	
	/**
	 * 足迹翻页
	 * @param belongItemId
	 * @param keyword
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Pagination<JSONObject> findTrackByPage(String companyId,String belongItemId,String keyword,int page,int pageSize){
		
		
		DBObject cond = new BasicDBObject();
		
		cond.put("companyId", companyId);
		
		if(!CommonUtils.isEmptyString(belongItemId)){
			cond.put("belongItemId", belongItemId);
		}
		
		String isDel = "";
		
		if(!CommonUtils.isEmptyString(isDel)){
			cond.put("isDel", isDel);
		}else{
			cond.put("isDel", new BasicDBObject("$eq", null));
		}
		
		if(!CommonUtils.isEmptyString(keyword)){
			Pattern pattern = Pattern.compile("^.*"+keyword+".*$");
			
			DBObject one = new BasicDBObject();
			one.put("reqDes", pattern);
			
			DBObject two = new BasicDBObject();
			two.put("target", pattern);
			
			DBObject three = new BasicDBObject();
			three.put("upgradeDes", pattern);
			
			DBObject four = new BasicDBObject();
			three.put("targetResult", pattern);
			
			
			ArrayList<DBObject> list = new ArrayList<DBObject>();
			list.add(one);
			list.add(two);
			list.add(three);
			list.add(four);
			
	        cond.put("$or", list);
		}
		DBCollection trackCollection = this.getTrackCollection();
		int count = (int) trackCollection.count(cond);
		
		final Pagination<JSONObject> pagination = new Pagination<JSONObject>();
		List<JSONObject> lists = new ArrayList<JSONObject>();
		
		
		if (count < 1) {
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", -1);
		
		lists = this.change2ListJSONObject( trackCollection.find(cond).sort(orderBy).skip((page-1)*pageSize).limit(pageSize) );

		pagination.setTotalRecords(count);
		pagination.setPage(page);
		pagination.setPageSize(pageSize);
		pagination.setRecords(lists);
		return pagination;
	}
	
	
}
