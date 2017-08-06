/************************************************************************************
 * Create at 2017/1/4
 *
 * Author:song.ty
 *
 *************************************************************************************/

package com.ydj.smart.api.push;

import com.ydj.smart.common.tools.HttpUtils;
import net.sf.json.JSONObject;


/**
 * 微信公众号服务
 * @author song.ty
 * @create 2017-08-04-16:54
 **/
public class WXService {

    /**
     * 发送api微信模板消息
     * @param appId 微信服务号appId
     * @param secret secret
     * @param openId 用户openId，在个人设置中扫码绑定获取
     * @param itemName 项目名称
     * @param title 标题
     * @param updateInfo 内容
     * @param clickUrl 点击跳转url
     * @return
     * @throws Exception
     */
    public static boolean sendTemplateMessage(String appId,
                                        String secret,
                                        String openId,
                                        String itemName,
                                        String title,
                                        String updateInfo,
                                        String templateId,
                                        String templateData,
                                        String clickUrl) throws Exception {

        updateInfo = updateInfo.replaceAll("\n","");
        updateInfo = updateInfo.replaceAll("\r","");

        String postUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+getAccessToken(appId,secret);
        JSONObject json = new JSONObject();
        json.put("touser",openId);
        json.put("template_id",templateId);
//        json.put("template_id","_m9XhMv9M0VOCwCX6BnKmTB0OlYg21crH_MeKkD7s2A");
        json.put("url",clickUrl);
        templateData = templateData.trim();
        templateData = templateData.replace("\n","");
        templateData = templateData.replace("\n","");
        templateData = templateData.replace("$itemName",itemName);
        templateData = templateData.replace("$title",title);
        templateData = templateData.replace("$updateInfo",updateInfo);
//        JSONObject data = new JSONObject();
//        data.put("first", JSONObject.fromObject("{value:\""+title+"\",colr:\"#173177\"}"));
//        data.put("keyword1", JSONObject.fromObject("{value:\""+itemName+"项目\",colr:\"#173177\"}"));
//        data.put("keyword2", JSONObject.fromObject("{value:\""+"已更新"+"\",colr:\"#173177\"}"));
//        data.put("remark", JSONObject.fromObject("{value:\"修改说明："+updateInfo.trim()+"\",colr:\"#173177\"}"));
        json.put("data",JSONObject.fromObject(templateData));
        String result = HttpUtils.postJSON(postUrl, json.toString());
        JSONObject jsonObject = JSONObject.fromObject(result);
        int errcode = jsonObject.optInt("errcode");
        if(errcode == 0 && jsonObject.optString("errmsg").equals("ok")){
            return true;
        }
        return false;
    }

    public static String getAccessToken(String appId,String secret){
        String accessToken = null;
        try {
            String result = HttpUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret + "");
            //{"access_token":"xE3SkwsdI6VOaJNrUU9YCtLsJsL24Mj4f-j1Dl6nEgWxALXqnszER2IBfzijLZNtW6GGQ2Xnq42RNvjFtc9qxBU33IMVQCZ6hOG9xksQ8-oUefXOzR9l7S9HOR8ECnHvFRZaACAVHP","expires_in":7200}
            JSONObject jsonObject = JSONObject.fromObject(result);
            accessToken = jsonObject.optString("access_token");
        }catch (Exception e){
            e.printStackTrace();
        }
        return accessToken;
    }
}
