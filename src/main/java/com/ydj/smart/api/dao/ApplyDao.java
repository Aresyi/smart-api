/** **/
package com.ydj.smart.api.dao;

import net.sf.json.JSONObject;

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
@Repository("applyDao")
public class ApplyDao extends BaseMongoDao {

	private DBCollection getApplyInfoCollection() {
		return this.getCollection("apply_info");
	}
	
	/**
	 * 按公司名称查询
	 * 
	 * @param companyName
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2017年5月4日 下午12:35:21
	 */
	public JSONObject findApplyByCompanyName(String companyName){
		DBCollection applyInfoCollection = this.getApplyInfoCollection();
		
		DBObject query = new BasicDBObject();
		query.put("companyName", companyName);
		
		DBObject obj = applyInfoCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	/**
	 * 按email查找
	 * @param email
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2017年5月8日 下午3:40:42
	 */
	public JSONObject findApplyByEmail(String email){
		DBCollection applyInfoCollection = this.getApplyInfoCollection();
		
		DBObject query = new BasicDBObject();
		query.put("email", email);
		
		DBObject obj = applyInfoCollection.findOne(query);
		
		return this.change2Bean(obj,JSONObject.class);
	}
	
	
	/**
	 * 保存申请信息
	 * @param applyInfo
	 */
	public void saveApplyInfo(JSONObject applyInfo){
		DBCollection applyInfoCollection = this.getApplyInfoCollection();
		this.insert(applyInfo,applyInfoCollection);
	}
	
}
