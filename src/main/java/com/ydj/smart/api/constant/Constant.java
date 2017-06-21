package com.ydj.smart.api.constant;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

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
	
	private static WatchService watchService ;
	
	private static Properties properties ;
	
	static{
		System.out.println("Constant init ...");
	
		final String fileName = "sysConfig.properties";
		final Resource resource = new ClassPathResource(fileName);;
		
		try {
			
			watchService = FileSystems.getDefault().newWatchService();
			
			Path path = Paths.get(resource.getFile().getParent());
			
			path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_CREATE);
			
			properties = PropertiesLoaderUtils.loadProperties(resource);
			
			/**此三个参数不随sysConfig.properties而改变*/
			webRoot = properties.getProperty("webRoot");
			cookieDomain = properties.getProperty("cookieDomain");
			webSocket = properties.getProperty("webSocket");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		Thread watchThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					
					try {
						WatchKey watchKey = watchService.take();
						
						for( WatchEvent<?> event : watchKey.pollEvents() ){
							if( fileName.equals(event.context().toString()) ){
								properties = PropertiesLoaderUtils.loadProperties(resource);
								break;
							}
						}
						
						watchKey.reset();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
		});
		
		watchThread.setDaemon(true);
		watchThread.start();
		
		
		Thread hook = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					watchService.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		Runtime.getRuntime().addShutdownHook(hook);
		
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
		return properties.getProperty(key);
	}
}
