package com.ydj.smart.api.push;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.ydj.smart.api.constant.Constant;
import com.ydj.smart.common.tools.CommonUtils;

/**
*
* @author : Ares.yi
* @createTime : 2014-11-10 上午11:13:42 
* @version : 1.0 
* @description : 
*
*/
@Component("webSocketHandshakeInterceptor")
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		System.out.println("==================WebSocketHandshakeInterceptor--->beforeHandshake()=============================");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);

			if (session != null) {
				String userId = (String) session.getAttribute(Constant.WEBSOCKET_USERID);
				
				if(CommonUtils.isEmptyString(userId)){
					return false;
				}
				
				attributes.put(Constant.WEBSOCKET_USERID, userId);
			}
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		System.out.println("==================WebSocketHandshakeInterceptor--->afterHandshake()=============================");
	}
}