/** **/
package com.ydj.smart.api.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

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
@Repository("presentDao")
public class PresentDao extends BaseMongoDao {

	private DBCollection getPresentCollection() {
		return this.getCollection("present");
	}
	
	/**
	 * 保存捐赠记录
	 * @param present
	 */
	public void savePresent(JSONObject present){
		DBCollection presentCollection = this.getPresentCollection();
		this.insert(present,presentCollection);
	}
	
	
	/**
	 * 捐赠记录翻页
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Pagination<JSONObject> findPresentByPage(int page,int pageSize){
		
		DBCollection presentCollection = this.getPresentCollection();
		int count = (int) presentCollection.count();
		
		final Pagination<JSONObject> pagination = new Pagination<JSONObject>();
		List<JSONObject> lists = new ArrayList<JSONObject>();
		
		
		if (count < 1) {
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", -1);
		
		lists = this.change2ListJSONObject( presentCollection.find().sort(orderBy).skip((page-1)*pageSize).limit(pageSize) );

		pagination.setTotalRecords(count);
		pagination.setPage(page);
		pagination.setPageSize(pageSize);
		pagination.setRecords(lists);
		return pagination;
	}
	
	
}
