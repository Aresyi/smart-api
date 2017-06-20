package com.ydj.smart.api.web;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ydj.smart.api.constant.Constant;
import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.common.tools.CommonUtils;
import com.ydj.smart.common.tools.WebUtils;

/**
*
* @author : Ares.yi
* @createTime : 2014-11-10 上午11:13:42 
* @version : 1.0 
* @description : 
*
 */
public class CommonInterceptor extends BaseAction implements HandlerInterceptor {
	
	private List<String> excludedUrls;

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    public CommonInterceptor() {
    }
    
    @Resource
	private ApiDao apiDao;
    
    @Resource
    private UserDao userDao;
    

    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     *  
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */ 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String url = request.getRequestURL().toString();
    	
    	System.out.println(new Date()+"\t"+WebUtils.getNgigxAddress(request)+"\t"+url);
    	
    	for(String excludedUrl : excludedUrls){
            if(url.contains(excludedUrl)){
                return true;
            }
        }
    	
    	try {
    		this.getUser(request, response);
		} catch (Exception e) {
			response.sendRedirect("/smart-api/index/login");
			return false;
		}
    	
    	HttpSession session = request.getSession();
    	String userId = (String) session.getAttribute(Constant.WEBSOCKET_USERID);
		
    	if(CommonUtils.isEmptyString(userId)){
    		userId = this.getUserId(request, response);
    		session.setAttribute(Constant.WEBSOCKET_USERID,userId);
    	}
    	
    	String uid = this.getUserId(request, response);
    	
    	if(CommonUtils.isNotEmptyString(uid)){
    		JSONObject user = this.userDao.findUserById(uid);
    		List<JSONObject> allItem = new ArrayList<JSONObject>();
    		
    		if(user != null && user.containsKey("permissionAPI")){
    			if(user.getInt("roleId") == 8686){//超级管理员
    				allItem = this.apiDao.getAllItem(user.getString("companyId"));
    			}else{
    				JSONArray permissionAPI = user.getJSONArray("permissionAPI");
        			allItem = this.apiDao.getItemByIds(permissionAPI);
    			}
    			
    		}
        	
    		request.setAttribute("allItem", allItem);
    	}
    	
        return true;   
    }
    

  //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
            throws Exception {
    	
//    	System.out.println(request.getRequestURL());
		
    }
    
    /** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex)
            throws Exception {

    	
    	if(ex != null){
    		ex.printStackTrace();
//    		request.getRequestDispatcher("404.jsp").forward(request, response);
    		this.gotoPage("404");
    	}
    }
    
	
}
