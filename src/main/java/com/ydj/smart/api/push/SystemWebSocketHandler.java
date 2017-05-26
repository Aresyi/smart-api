package com.ydj.smart.api.push;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.ydj.smart.api.constant.Constant;

/**
*
* @author : Ares.yi
* @createTime : 2014-11-10 上午11:13:42 
* @version : 1.0 
* @description : 
*
*/
@Component("systemWebSocketHandler")
public class SystemWebSocketHandler implements WebSocketHandler {

//    private static final ArrayList<WebSocketSession> users  = new ArrayList<>();
    
    private static final Map<String,WebSocketSession> USERS = new HashMap<String,WebSocketSession>();

    @Resource
    private WebSocketService webSocketService;

    private void log(String info){
    	System.out.println("=====================SystemWebSocketHandler-----"+info+"==========================");
    }
    
    
    private String getUserId(WebSocketSession session){
    	String userId = (String) session.getAttributes().get(Constant.WEBSOCKET_USERID);
    	return userId;
    }
    
    private void removeUser(WebSocketSession session){
    	String userId = getUserId(session);
    	 USERS.remove(userId);
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log("afterConnectionEstablished()");
//    	users.add(session);
        
    	String userId = getUserId(session);
        
        if( userId != null){
            //查询未读消息
            int unReadCount = webSocketService.getUnReadNews(userId);
            String content = webSocketService.getNotifyContent("您有"+unReadCount+"条未读新通知",userId,unReadCount);
            
            session.sendMessage(new TextMessage(content));
            
            USERS.put(userId, session);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	 log("handleMessage()");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    	 log("handleTransportError()");
        if(session.isOpen()){
            session.close();
        }
//        users.remove(session);
        this.removeUser(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    	 log("afterConnectionClosed()");
//        users.remove(session);
    	 this.removeUser(session);
    }

    @Override
    public boolean supportsPartialMessages() {
    	 log("supportsPartialMessages()");
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessage2AllUser(TextMessage message) {
    	log("sendMessageToUsers()");
    	
    	for(Map.Entry<String, WebSocketSession> entry : USERS.entrySet()){
    		WebSocketSession user = entry.getValue();
    		try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
    	
//        for (WebSocketSession user : users) {
//            try {
//                if (user.isOpen()) {
//                    user.sendMessage(message);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(String userId, TextMessage message) {
    	log("sendMessageToUser()");
    	
    	WebSocketSession user = USERS.get(userId);
    	
    	if(user == null){
    		return ;
    	}
    	
    	try {
             if ( user.isOpen() ) {
                 user.sendMessage(message);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	
//        for (WebSocketSession user : users) {
//            if (user.getAttributes().get(Constant.WEBSOCKET_USERID).equals(userId)) {
//                try {
//                    if (user.isOpen()) {
//                        user.sendMessage(message);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
        
        
    }
}