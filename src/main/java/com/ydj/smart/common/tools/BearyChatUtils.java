/**
 * 
 */
package com.ydj.smart.common.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.ydj.smart.api.constant.Constant;

/**  
 *
 * @author : Ares.yi
 * @createTime : 2014-11-10 上午11:13:42 
 * @version : 1.0 
 * @description : 
 *
 */
public class BearyChatUtils {

	/**
	 * 同步动态到bearyChat，JSON消息体格式如下：
	 * {
	 *	    text: "text, this field may accept markdown",
	 *	    markdown: true,
	 *	    channel: "bearychat-dev",
	 *	    attachments: [
	 *	        {
	 *	            title: "title_1",
	 *	            text: "attachment_text",
	 *	            color: "#ffffff",
	 *	            images: [
	 *	                {"url": "http://example.com/index.jpg"}
	 *	                ]
	 *	        }]
	 *	}
	 *
	 *
	 *	顶层字段
	 *	    text. 必须字段。支持 inline md 的文本内容。
	 *	    markdown. 可选字段。用于控制 text 是否解析为 markdown。默认为 false
	 *	    channel. 可选字段。如果有这个字段，消息会发送到指定讨论组。如果没有，消息会发送到创建机器人时默认的讨论组。
	 *	    attachments. 可选。一系列附件
	 *	
	 *	attachments
	 *	    title. 可选。
	 *	    text. 可选。
	 *	    color. 可选。用于控制 attachment 在排版时左侧的竖线分隔符颜色
	 *	    title和text字段必须有一个。其他的随意组合。
	 *	    images. 可选。用于在推送中推送图片，可以最多同时推送3个图片。使用这个字段需要注意，服务器在收到带images的请求时会主动抓取一次图片内容并缓存，这个过程会比较慢，可能造成请求响应时间增加。另外如果两次推送的图片地址都一样，那么第二次的响应时间会显著降低，因为服务器会对请求进行缓存至少一天，所以如果需要不同的图片请使用不同地址。
	 *
	 *
	 * @param json
	 * @return
	 * @throws IOException
	 *
	 * @author : Ares.yi
	 * @createTime : 2015年6月25日 下午4:11:55
	 */
	public static String bearyChatSyc(String text){
		
		if(!Constant.getPro("bearyChat.notify").equals("yes")){
			return "";
		}
		
		HttpClient client = new HttpClient();
		
		JSONObject json = new JSONObject();
		json.put("text", text);
		
		NameValuePair[] nameValuePairs = new NameValuePair[1];
		nameValuePairs[0] = new NameValuePair("payload", json.toString());
		
		PostMethod method = new PostMethod("https://hook.bearychat.com/=bw6l2/incoming/bcbf4872dc123f4b9a1a257c901ea9fb");
		method.setRequestBody(nameValuePairs);
		
		HttpMethodParams param = method.getParams();
		param.setContentCharset("UTF-8");
		
		try {
			client.executeMethod(method);
			return method.getResponseBodyAsString();
		} catch (Exception e) {
		}
		
		return "";
	}
	
	/**
	 * 生成百度短地址<br>
	 * 参考：http://dwz.cn/
	 * @param url
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2015年6月25日 下午6:00:04
	 */
	public static String baiduShortUrl(String url){
		if(url == null || "".equals(url.trim())){
			return "";
		}
		
		String longURL = url;
		
		HttpClient client = new HttpClient();
		
		NameValuePair[] nameValuePairs = new NameValuePair[1];
		nameValuePairs[0] = new NameValuePair("url", url);
		
		PostMethod method = new PostMethod("http://dwz.cn/create.php");
		method.setRequestBody(nameValuePairs);
		
		HttpMethodParams param = method.getParams();
		param.setContentCharset("UTF-8");
		
		try {
			client.executeMethod(method);
			String res = method.getResponseBodyAsString();
			JSONObject json = JSONObject.fromObject(res);
			if(json.containsKey("status") && json.getInt("status") == 0){
				url = json.getString("tinyurl");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(longURL.equals(url)){
			url = so985(url);
		}
		
		return url;
	}
	
	/**
	 * http://985.so/page/apidoc.php
	 * 
	 * @param url
	 * @return
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年1月6日 下午4:30:49
	 */
	public static String so985(String url){
		HttpClient client = new HttpClient();
		
		try {
			url = java.net.URLEncoder.encode(url,"utf-8");
		} catch (UnsupportedEncodingException e1) {
		}
		
//		GetMethod getMethod = new GetMethod("http://985.so/api.php?format=json&url="+url);
//		GetMethod getMethod = new GetMethod("http://985.so/api.php?url="+url);
		GetMethod getMethod = new GetMethod("http://suo.im/api.php?url="+url);
		
		HttpMethodParams param = getMethod.getParams();
		param.setContentCharset("UTF-8");
		
		try {
			client.executeMethod(getMethod);
			String res = getMethod.getResponseBodyAsString();
			if(res.startsWith("http://") || res.startsWith("https://")){
				url = res;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	
	private static String test(String text){
		HttpClient client = new HttpClient();
		
		JSONObject json = new JSONObject();
		json.put("text", text);
		
		NameValuePair[] nameValuePairs = new NameValuePair[1];
		nameValuePairs[0] = new NameValuePair("payload", json.toString());
		
		PostMethod method = new PostMethod("http://jira.9tong.com/rest/webhooks/1.0/webhook/2?os_authType=basic&os_username=yidejun&os_password=jt123456");
		method.setRequestBody(nameValuePairs);
		
		HttpMethodParams param = method.getParams();
		param.setContentCharset("UTF-8");
		
		try {
			client.executeMethod(method);
			return method.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
			
			return e.getMessage();
		}
		
		
	}
	
	
	/**
	 * @param args
	 *
	 * @author : Ares.yi
	 * @throws IOException 
	 * @createTime : 2015年6月25日 下午3:46:55
	 */
	public static void main(String[] args) throws IOException {
		
		//System.out.println(bearyChatSyc("愿原力与你同在https://hook.bearychat.com/=bw6l2/incoming/bcbf4872dc123f4b9a1a257c901ea9fb"));
//		System.out.println(baiduShortUrl("http://192.168.254.120:8090/api.do?method=apiDetail&id=558bc827975ae9ba05b1ac87"));
		
		System.out.println(WebUtils.getHtmlContent("http://jira.9tong.com/rest/webhooks/1.0/webhook/2?os_authType=basic&os_username=yidejun&os_password=jt123456"));
		
		System.out.println(test("ssss"));
	}

}
