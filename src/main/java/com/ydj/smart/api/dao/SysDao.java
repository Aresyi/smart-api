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
import com.ydj.smart.common.tools.BearyChatUtils;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:13:42
 * @version : 1.0
 * @description :
 *
 */
@Repository("sysDao")
public class SysDao extends BaseMongoDao {

	private DBCollection getSysLogCollection() {
		return this.getCollection("sys_log");
	}
	
	
	/**
	 * 保存Log
	 * @param item
	 */
	public void saveSysLog(JSONObject log){
		DBCollection sysLogCollection = this.getSysLogCollection();
		this.insert(log,sysLogCollection);
	}
	
	/**
	 * 保存Log
	 * @param opreation
	 * @param desc
	 */
	public void saveSysLog(String companyId,String opreation,String desc){
		this.saveSysLog(companyId,opreation, desc, "");
	}
	
	
	/**
	 * 保存Log
	 * @param opreation
	 * @param desc
	 */
	public void saveSysLog(String companyId,String opreation,String desc,String href){
		
		href =  BearyChatUtils.baiduShortUrl(href);
		
		boolean isLogin = false;
		
		if(desc.contains("登录")){
			isLogin = true ;
		}
		
		JSONObject log = new JSONObject();
		log.put("companyId", companyId);
		log.put("opreation", opreation);
		log.put("desc", desc);
		log.put("href", href);
		if(isLogin){
			log.put("opType", 0);
		}
		log.put("createTime", System.currentTimeMillis());
		this.saveSysLog(log);
		
		if(isLogin){
			return ;
		}
		
		BearyChatUtils.bearyChatSyc(opreation+" "+desc+" "+href);
	}
	
	/**
	 * 系统日志查询
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Pagination<JSONObject> findSysLogByPage(String companyId,int page,int pageSize){
		
		DBObject cond = new BasicDBObject();
		cond.put("opType", new BasicDBObject("$eq", null));
		cond.put("companyId", companyId);
		
		DBCollection sysLogCollection = this.getSysLogCollection();
		int count = (int) sysLogCollection.count(cond);
		
		final Pagination<JSONObject> pagination = new Pagination<JSONObject>();
		List<JSONObject> lists = new ArrayList<JSONObject>();
		
		
		if (count < 1) {
			pagination.setRecords(lists);
			return pagination;
		}
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put("createTime", -1);
		
		lists = this.change2ListJSONObject( sysLogCollection.find(cond).sort(orderBy).skip((page-1)*pageSize).limit(pageSize) );

		pagination.setTotalRecords(count);
		pagination.setPage(page);
		pagination.setPageSize(pageSize);
		pagination.setRecords(lists);
		return pagination;
	}
	
}
