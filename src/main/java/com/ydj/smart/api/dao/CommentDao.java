/** **/
package com.ydj.smart.api.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.ydj.smart.api.constant.CommentType;
import com.ydj.smart.common.nosql.BaseMongoDao;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:13:42
 * @version : 1.0
 * @description :
 *
 */
@Repository("commentDao")
public class CommentDao extends BaseMongoDao {

	private DBCollection getCommentCollection() {
		return this.getCollection("comment");
	}
	
	
	/**
	 * 保存用户评论
	 * 
	 * @param item
	 */
	public void saveComment(JSONObject comment){
		DBCollection commentCollection = this.getCommentCollection();
		this.insert(comment,commentCollection);
	}
	
	/**
	 * 评论查询
	 * @param commentType
	 * @param targetId
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月7日 上午10:29:57
	 */
	public List<JSONObject> findCommentById(CommentType commentType,String targetId){
		DBCollection commentCollection = this.getCommentCollection();
		
		DBObject query = new BasicDBObject();
		query.put("type", commentType.ordinal());
		query.put("targetId", targetId);
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", 1);
		
		return this.change2ListJSONObject(commentCollection.find(query).sort(orderBy));
	}
	
	/**
	 * 唯一查询
	 * @param commentType
	 * @param targetId
	 * @param userId
	 * @param now
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月18日 下午2:45:52
	 */
	public JSONObject findCommentByOnly(CommentType commentType,String targetId,String userId,long now){
		DBCollection commentCollection = this.getCommentCollection();
		
		DBObject query = new BasicDBObject();
		query.put("type", commentType.ordinal());
		query.put("targetId", targetId);
		query.put("userId", userId);
		query.put("createTime", now);
		
		DBObject obj = commentCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
}
