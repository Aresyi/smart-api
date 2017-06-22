/** **/
package com.ydj.smart.api.action;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.CommonUtils;
import com.ydj.smart.common.tools.CookieUtils;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("upload")
public class UploadAction extends BaseAction {
	
	@Resource
	private UserDao userDao;
	
	
	/**
	 * 上传修改头像
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月14日 下午5:44:57
	 */
	@RequestMapping("avatars")
	@ResponseBody
	public JSONObject avatars (@RequestParam(value = "upload_file", required = true) MultipartFile file,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String userId = this.getUserId(request, response);
		
//		String fileName = file.getOriginalFilename();  
        String fileName = userId+".jpg";  
	   
        String path = request.getSession().getServletContext().getRealPath("/assets/default_avatars/");
		System.out.println(path);  
		 
		File targetFile = new File(path, fileName);  
		if(!targetFile.exists()){  
           targetFile.mkdirs();  
		}  
 
       //保存  
        try {  
           file.transferTo(targetFile);  
        } catch (Exception e) {  
           e.printStackTrace();  
        }  
		
        this.userDao.updateUserAvatar(fileName, userId);
        
        response.addCookie(CookieUtils.newCookie("avatar",fileName));
        
		JSONObject res = new JSONObject();
		res.put("avatar", "/smart-api/assets/default_avatars/"+fileName);
		res.put("success", true);
		res.put("userId", userId);
		
		return res;

	}
	
	/**
	 * 上传头像
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月27日 下午9:27:43
	 */
	@RequestMapping("pic")
	@ResponseBody
	public JSONObject pic (@RequestParam(value = "upload_file", required = true) MultipartFile file,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String userId = this.getUserId(request, response);
		
        String fileName = System.currentTimeMillis()+"_"+CommonUtils.getRandomNumber(100, 999)+".jpg";  
	   
        String path = request.getSession().getServletContext().getRealPath("/assets/userPic/");
		System.out.println(path);  
		 
		File targetFile = new File(path, fileName);  
		if(!targetFile.exists()){  
           targetFile.mkdirs();  
		}  
 
       //保存  
        try {  
           file.transferTo(targetFile);  
        } catch (Exception e) {  
           e.printStackTrace();  
        }  
		
        
		JSONObject res = new JSONObject();
		res.put("avatar", "/smart-api/assets/userPic/"+fileName);
		res.put("success", true);
		res.put("attach", userId);
		
		return res;

	}
	
	
	/**
	 * 新建文档上传图片(原TOWER中Markdown格式要求)
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月27日 下午9:27:11
	 */
	@RequestMapping("attachments")
	@ResponseBody
	public JSONObject attachments (@RequestParam(value = "upload_file", required = true) MultipartFile file,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String userId = this.getUserId(request, response);
		
		String trueFileName = file.getOriginalFilename();  
		
		String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
		
        String fileName = System.currentTimeMillis()+"_"+CommonUtils.getRandomNumber(100, 999)+suffix;  
	   
        String path = request.getSession().getServletContext().getRealPath("/assets/msg/upload/");
		System.out.println(path);  
		 
		File targetFile = new File(path, fileName);  
		if(!targetFile.exists()){  
           targetFile.mkdirs();  
		}  
 
       //保存  
        try {  
           file.transferTo(targetFile);  
        } catch (Exception e) {  
           e.printStackTrace();  
        }  
		
        
		JSONObject res = new JSONObject();
		res.put("file-path", "/smart-api/assets/userPic/"+fileName);
		res.put("success", true);
		res.put("attach", userId);
		
		return res;

	}
	
	
	/**
	 * 新建文档上传图片(原editormd中格式要求)
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author : Ares.yi
	 * @createTime : 2016年7月27日 下午9:27:11
	 */
	@RequestMapping("editormdPic")
	@ResponseBody
	public JSONObject editormdPic (@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
	            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		String trueFileName = file.getOriginalFilename();  
		
		String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
		
        String fileName = System.currentTimeMillis()+"_"+CommonUtils.getRandomNumber(100, 999)+suffix;  
	   
        String path = request.getSession().getServletContext().getRealPath("/assets/msg/upload/");
		System.out.println(path);  
		 
		File targetFile = new File(path, fileName);  
		if(!targetFile.exists()){  
           targetFile.mkdirs();  
		}  
 
       //保存  
        try {  
           file.transferTo(targetFile);  
        } catch (Exception e) {  
           e.printStackTrace();  
        }  
		
        
		JSONObject res = new JSONObject();
		res.put("url", "/smart-api/assets/userPic/"+fileName);
		res.put("success", 1);
		res.put("message", "upload success!");
		
		return res;

	}
	
	
	
	
}
