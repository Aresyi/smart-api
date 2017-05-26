/** **/
package com.ydj.smart.api.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydj.smart.api.dao.ApiDao;
import com.ydj.smart.api.dao.UserDao;
import com.ydj.smart.api.web.BaseAction;
import com.ydj.smart.common.tools.Pinyin4j;
import com.ydj.smart.common.tools.PinyinUtil;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2014-2-13 下午03:35:03
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping("projects")
public class ProjectsAction extends BaseAction {
	
	@Resource
	private ApiDao apiDao;
	
	@Resource
	private UserDao userDao;
	
	@RequestMapping("{tid}/mates.json")
	@ResponseBody
	public List<JSONObject> mates (
            HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		String companyId = this.getCompanyId(request, response);
		
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		List<JSONObject> allUser = this.userDao.getAllUser(companyId);
		
		PinyinUtil obj = new PinyinUtil();
		
		for(JSONObject one : allUser){
			
			String name = one.getString("name");
			
			String pinyin = Pinyin4j.getPinyin2(name);
			
			String pinyin_abbr = obj.String2AlphaFirst2(name, "B");
			
			JSONObject res = new JSONObject();
			res.put("guid", one.getString("id"));
			res.put("nickname", name);
			res.put("nickname_pinyin", pinyin);
			res.put("nickname_pinyin_abbr", pinyin_abbr);
			res.put("gavatar", "assets/default_avatars/"+one.getString("avatar"));
			res.put("visitor", false);
			
			list.add(res);
		}
		
		return list;
	}
	
	
	
	
}
