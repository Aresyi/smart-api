package com.ydj.smart.api.push;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
*
* @author : Ares.yi
* @createTime : 2014-11-10 上午11:13:42 
* @version : 1.0 
* @description : 
*
*/
@EnableWebSocket
@Configuration
@EnableWebMvc
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
		WebSocketConfigurer {
	
	public WebSocketConfig(){
		System.out.println("==========================WebSocketConfig================================");
	}
	
	@Resource
	private WebSocketHandshakeInterceptor webSocketHandshakeInterceptor;
	
	@Resource
	private SystemWebSocketHandler systemWebSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		System.out.println("==========================WebSocketConfig--->registerWebSocketHandlers()================================");
		
		registry.addHandler(systemWebSocketHandler, "/webSocketServer")
				.addInterceptors(webSocketHandshakeInterceptor);

		registry.addHandler(systemWebSocketHandler, "/sockjs/webSocketServer")
				.addInterceptors(webSocketHandshakeInterceptor)
				.withSockJS();
	}


}