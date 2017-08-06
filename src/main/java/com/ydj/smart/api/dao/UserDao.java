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
 * @createTime : 2014-2-13 下午03:13:42
 * @version : 1.0
 * @description :
 *
 */
@Repository("userDao")
public class UserDao extends BaseMongoDao {

	private DBCollection getUserCollection() {
		return this.getCollection("user");
	}
	
	private DBCollection getUserGroupCollection() {
		return this.getCollection("user_group");
	}
	
	
	/**
	 * 保存用户组
	 * @param item
	 */
	public void saveUserGroup(JSONObject userGroup){
		DBCollection userGroupCollection = this.getUserGroupCollection();
		this.insert(userGroup,userGroupCollection);
	}
	
	/**
	 * 获取指定组
	 * @return
	 */
	public JSONObject findUserGroupById(String id){
		DBCollection userGroupCollection = this.getUserGroupCollection();
		return this.findById(id, userGroupCollection);
	}

	
	/**
	 * 查询所有用户组
	 * @return
	 */
	public List<JSONObject> getAllUserGroup(String companyId){
		DBCollection userGroupCollection = this.getUserGroupCollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		
		return this.change2ListJSONObject(userGroupCollection.find(query));
	}
	
	
	/**
	 * 增加创建者UID
	 * @param createUserId
	 * @param id
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月16日 下午6:24:53
	 */
	public void updateAPIAddCreateUid(String createUserId,String id){
		DBCollection userGroupCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject res = userGroupCollection.findOne(query);
		if (res != null) {
			res.put("createUserId", createUserId);
			userGroupCollection.update(query, res);
		}
	}
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void saveUser(JSONObject user){
		DBCollection userCollection = this.getUserCollection();
		this.insert(user,userCollection);
	}
	
	
	/**
	 * 修改用户
	 * @param user
	 * @param id
	 *
	 * @author : Ares.yi
	 * @createTime : 2015年10月10日 下午12:07:41
	 */
	public void updateUser(JSONObject user,String id){
		DBCollection userCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject obj = new BasicDBObject();
		for(Object key : user.keySet()){
			String _key = key.toString();
			obj.put(_key, user.get(_key));
		}
		userCollection.update(query,obj);
	}
	
	public void updateUser(String id,String groupName,String groupId,String roleId,String isEdite,String permissionAPI[],String opreater){
		DBCollection userCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject res = new BasicDBObject();
		res.put("group", groupName);
		res.put("groupId", groupId);
		res.put("roleId", roleId);
		res.put("isEdite", isEdite);
		res.put("permissionAPI", permissionAPI);
		res.put("modifyTime", System.currentTimeMillis());
		res.put("modifyer", opreater);
		
		BasicDBObject doc = new BasicDBObject();  
		doc.put("$set", res);
		
		userCollection.update(query, doc,false,false);
	}
	
	
	/**
	 * 查询用户
	 * @param name
	 * @return
	 */
	public JSONObject findUserByName(String companyId,String name){
		DBCollection userCollection = this.getUserCollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("name", name);
		
		DBObject obj = userCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	/**
	 * 查询用户
	 * @param email
	 * @return
	 */
	public JSONObject findUserByEmail(String email){
		DBCollection userCollection = this.getUserCollection();
		
		DBObject query = new BasicDBObject();
		query.put("email", email);
		
		DBObject obj = userCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}

	/**
	 * 查询用户 根据微信openId查询
	 * @param openId
	 * @return
	 */
	public JSONObject findUserByOpenId(String openId){
		DBCollection userCollection = this.getUserCollection();

		DBObject query = new BasicDBObject();
		query.put("openId", openId);

		DBObject obj = userCollection.findOne(query);

		return this.change2Bean(obj,JSONObject.class);
	}
	
	/**
	 * 查询指定用户
	 * @param id
	 * @return
	 */
	public JSONObject findUserById(String id){
		DBCollection userCollection = this.getUserCollection();
		return this.findById(id, userCollection);
	}
	
	/**
	 * 删除用户
	 * @param name
	 * @return
	 */
	public int deleteUserById(String id){
		DBCollection userCollection = this.getUserCollection();
		
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		return userCollection.remove(query).getN();
	}
	
	/**
	 * 逻辑删除用户
	 * @param id
	 * @param opreater
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月26日 下午3:07:30
	 */
	public void updateUser4Destroy(String id,String opreater){
		DBCollection userCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject res = new BasicDBObject();
		res.put("destroy", 1);
		res.put("modifyTime", System.currentTimeMillis());
		res.put("modifyer", opreater);
		
		BasicDBObject doc = new BasicDBObject();  
		doc.put("$set", res);
		
		userCollection.update(query, doc,false,false);
	}
	
	/**
	 * 删除用户
	 * @param name
	 * @return
	 */
	public int deleteUserByName(String companyId,String name){
		DBCollection userCollection = this.getUserCollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("name", name);
		
		return userCollection.remove(query).getN();
	}
	
	/**
	 * 修改用户名
	 * @param userName
	 * @param id
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:41:34
	 */
	public void updateUserName(String companyId, String userName,String id){
		DBCollection userCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("_id", new ObjectId(id));
		
		DBObject res = userCollection.findOne(query);
		if (res != null) {
			res.put("name", userName);
			userCollection.update(query, res);
		}
	}
	
	/**
	 * 修改用户头像
	 * @param avatar
	 * @param id
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:41:34
	 */
	public void updateUserAvatar(String avatar,String id){
		DBCollection userCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject res = userCollection.findOne(query);
		if (res != null) {
			res.put("avatar", avatar);
			userCollection.update(query, res);
		}
	}
	
	
	/**
	 * 修改密码
	 * @param password
	 * @param id
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午3:41:34
	 */
	public void updatePass(String password,String id){
		DBCollection userCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		DBObject res = userCollection.findOne(query);
		if (res != null) {
			res.put("password", password);
			userCollection.update(query, res);
		}
	}
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<JSONObject> getAllUser(String companyId){
		DBCollection userCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("destroy", new BasicDBObject("$eq", null));

		return this.change2ListJSONObject(userCollection.find(query));
	}
	
	/**
	 * 获取所有用户(不包含被逻辑删除的用户)
	 * @return
	 */
	public List<JSONObject> getAllNormalUser(String companyId){
		DBCollection userCollection = this.getUserCollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyId", companyId);
		query.put("destroy", new BasicDBObject("$eq", null));
		
		return this.change2ListJSONObject(userCollection.find(query));
	}

	/**
	 * 添加/更新用户openId
	 * @param id
	 * @param openId
	 */
	public void saveOrUpdateUserOpenId(String id, String openId,String openIdItemId){
		DBCollection userCollection = this.getUserCollection();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));

		DBObject res = new BasicDBObject();
		res.put("openId", openId);
		res.put("openIdItemId", openIdItemId);

		BasicDBObject doc = new BasicDBObject();
		doc.put("$set", res);

		userCollection.update(query, doc,false,false);
	}
	
}
