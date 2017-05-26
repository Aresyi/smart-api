package com.ydj.smart.api.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-15 上午11:32:19
 * @version : 1.0
 * @description :
 *
 */
public class Constant {
	
	private static String webRoot;
	private static String cookieDomain;
	private static String webSocket;
	
	private static Properties p ;
	static{
		System.out.println("Constant init ...");
		
		InputStream in = Constant.class.getResourceAsStream("/sysConfig.properties");
		p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		webRoot = p.getProperty("webRoot");
		cookieDomain = p.getProperty("cookieDomain");
		webSocket = p.getProperty("webSocket");

		if(in != null){
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** WEB应用根目录 **/
	public static final String WEB_ROOT = webRoot;
	
	/** COOKIE DOMAIN **/
	public static final String COOKIE_DOMAIN = cookieDomain;
	
	/** WEB_SOCKET **/
	public static final String WEB_SOCKET = webSocket;

	/** web socket name **/
	public static final String WEBSOCKET_USERID = "WEBSOCKET_USERID";
			
	public static String getPro(String key){
		return p.getProperty(key);
	}
}
