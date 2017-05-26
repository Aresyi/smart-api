<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
		<input type="hidden" id="d18n-enabled" value="false" />
    	<input type="hidden" id="server-time" value="2016-07-03 17:21:33" />
        <input type="hidden" id="team-guid" value="2c17c1fc24584c0497dd0a6ac8a3ed18" />
        <input type="hidden" id="team-name" value="Smart API" />
        <input type="hidden" id="team-enable-pusher" value="true" />
        <input type="hidden" id="user-visit-welcome-project-before" value="true" />
        <input type="hidden" id="user-email" value="dejun.yi@ydj.com" />
        <input type="hidden" id="unused-bubbles" value="" />
        <input type="hidden" id="member-id" value="462201" />
        <input type="hidden" id="member-guid" value="cf99cd6d64594a03bf758436dd69a217" />
        <input type="hidden" id="member-nickname" value="${userName }" />
        <input type="hidden" id="member-avatar" value="/smart-api/assets/default_avatars/${empty cookie.avatar.value ? '2.jpg' : cookie.avatar.value}" />
        <input type="hidden" id="member-timezone" value="Asia/Shanghai" />
        <input type="hidden" id="conn-guid" value="7ea2d21b93a69ec4154c840a0f54f544" />
        <input type="hidden" id="beta" value="false" />
        <input type="hidden" id="member-admin" />


  <a href="javaScript:void(0);" target="_blank" id="link-feedback">
    <i class="twr twr-dingtalk"></i>帮助
  </a>
  
  <script type="text/html" id="tpl-help-qrcode-popover">
    <h5>钉钉在线客服群</h5>
    <p><img src="https://s.tower.im/dingtalkqrcode.png"></p>
    <p class="desc">扫码获取帮助</p>
  </script>

  
  <a class="back-to-top" title="返回顶部"><i class="twr twr-chevron-up"></i></a>
  <script type="text/javascript">
  $(function(){
  	var $backtop = $(".back-to-top").hide();
  	$(window).scroll(function() {
  		if( $(window).scrollTop() > 200) {
  			$backtop.show();
  		} else {
  			$backtop.hide();
  		}
  	});
  });
  
    var tag = $(".page-inner").attr("nav");
    if(tag != "home"){
	    $("#nav-"+tag).attr("class","active");
	    $("#nav-home").attr("class","");
    }
  </script>
  
