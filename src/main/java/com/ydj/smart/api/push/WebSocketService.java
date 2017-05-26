package com.ydj.smart.api.push;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ydj.smart.api.dao.NotifyDao;

/**  
 *
 * @author : Ares.yi
 * @createTime : 2014-11-10 上午11:13:42 
 * @version : 1.0 
 * @description : 
 *
 */
@Service
public class WebSocketService {
	
	@Resource
    private VelocityEngine velocityEngine;
	
	@Resource
	private NotifyDao notifyDao;

	/**
	 * 未读消息数
	 * @param userId
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月15日 上午10:41:11
	 */
	public int getUnReadNews(String userId) {
		return notifyDao.getUnReadNews(userId);
	}
	
	public String getNotifyContent(String noticeTitle,String userId){
		int unReadCount = getUnReadNews(userId);
		return this.getNotifyContent(noticeTitle,userId, unReadCount);
	}

	/**
	 * 通知内容
	 * @param noticeTitle
	 * @param userId
	 * @param unReadCount
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月15日 下午4:33:46
	 */
	public String getNotifyContent(String noticeTitle,String userId,int unReadCount){
		String content = "";
		if(unReadCount > 0){
        	
        	List<JSONObject> notifyList = this.notifyDao.findUnReadNotifyByUserId(userId);
        	
//        	for(int i = 0 ;i<unReadCount;i++){
//        		JSONObject one = new JSONObject();
//        		one.put("id", i);
//            	one.put("actorMember", "张三"+i);
//            	one.put("action", "创建了讨论"+i);
//            	one.put("target", "1.80 选择商品报价逻辑、购物车结算判断");
//            	one.put("content", "@易德军&nbsp;XZ");
//            	one.put("createTime", CommonUtils.getDateString("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()));
//            	
//            	notifyList.add(one);
//        	}
        	
        	Map<String,Object> model = new HashMap<String,Object>();
            model.put("notifyList", notifyList);
            
            content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/notice.vm", "utf-8", model);
        }
        
        JSONObject msg = new JSONObject();
        msg.put("noticeTitle", noticeTitle);
        msg.put("count", unReadCount);
        msg.put("content", content);
        
        return msg.toString();
	}
}
