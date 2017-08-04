<%@page import="com.ydj.smart.api.constant.Constant"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>

    <script>
            var websocket;
            
            if ('WebSocket' in window) {
                websocket = new WebSocket("<%=Constant.WEB_SOCKET%>webSocketServer");
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("<%=Constant.WEB_SOCKET%>webSocketServer");
            } else {
                websocket = new SockJS("<%=Constant.WEB_ROOT%>sockjs/webSocketServer");
            }
            
            websocket.onopen = function (evnt) {
            };
            
            websocket.onmessage = function (evnt) {
            	//alert(evnt.data);
            	
            	var json = JSON.parse(evnt.data);
            	
            	//alert(json.count);
            	
            	var count = json.count;
            	
                if(count > 0){
                	$("#notification-count").attr("class","label unread");
                	$("#notification-count").attr("data-unread-count",count);
                	document.getElementById("notification-num").innerHTML= count;
                	$("#noti-pop-empty").attr("class","noti-pop-empty hide");
                	
                	$(".noti-pop-list.notification-list").empty();
                	$(".noti-pop-list.notification-list").append(json.content);
                	
                	notifyShow(json.noticeTitle);
                }else{
                	$("#notification-count").attr("class","label");
                	$("#notification-count").attr("data-unread-count",0);
                	document.getElementById("notification-num").innerHTML= '';
                	$("#noti-pop-empty").attr("class","noti-pop-empty");
                	$(".noti-pop-list.notification-list").empty();
                }
            };
            
            websocket.onerror = function (evnt) {
            };
            
            websocket.onclose = function (evnt) {
            }

        </script>


<script>  
       function notifyShow(msg) {   
           //var date = new Date();   
           //var time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();   
           Notification.requestPermission(function (perm) {   
               if (perm == "granted") {   
                   var notification = new Notification("Smart-API系统新通知:", {   
                       dir: "auto",   
                       lang: "hi",   
                       tag: "testTag",   
                       icon: "/smart-api/assets/20131231094532_5781.gif",//"http://www.html5party.com/bbs/static/image/common/logo.png",   
                       body: msg   
                   });   
               }   
           })   
       }   
</script>  

<div class="header" style="width: 60%">
    <h1 class="logo">
        <a href="javascript:;" class="link-team-menu">
            <span class="name">${empty itemName ? '产品' : itemName }</span>
            <i class="twr twr-caret-down"></i>
        </a>
    </h1>

    <ul class="nav">
        <li id="nav-home" class="active">
            <a href="/smart-api/index/home/"  data-stack-root>主页</a>
        </li>
        
        <li id="nav-events">
            <a href="/smart-api/event/sysLog/"  data-stack-root>动态</a>
        </li>
        
        <li id="nav-datadesc">
            <a href="/smart-api/data/find/"  data-stack-root>数据</a>
        </li>
        
        <li id="nav-members">
            <a href="/smart-api/user/member/"  data-stack-root>团队</a>
        </li>

        <li id="nav-doc">
            <a href="/smart-api/message/messageList/"  data-stack-root >文档</a>
        </li>
        
        <li id="nav-track">
            <a href="/smart-api/track/searchTrack/"   data-stack-root>足迹</a>
        </li>
        <!-- 
         <li id="nav-present">
            <a href="/smart-api/present/thanks/"   data-stack-root>捐赠</a>
        </li>
 		-->
        <li id="nav-json">
            <a href="http://tool.oschina.net/codeformat/json" target="_black" data-stack-root>JSON工具</a>
        </li>
        
        <li id="nav-me">
            <a href="/smart-api/user/settings"  data-stack-root>我自己</a>
        </li>
    </ul>

    <div class="command-bar">
        
        <div class="search-wrap">
            <a href="javascript:;" class="link-search" title="搜索"><i class="twr twr-search"></i></a>
            <form id="form-search" class="form" method="post" action="/smart-api/api/searchApi">
                <input id="txt-search" type="text" class="keyword no-border" name="keyword" placeholder="搜索" autocomplete="off" />
            </form>
        </div>
        

        <div class="notification-info">
            <a href="javascript:;" id="notification-count" class="label" title="新的通知" data-unread-count="0" data-url="/teams/2c17c1fc24584c0497dd0a6ac8a3ed18/notifications/unread_counts">
                <span class="twr twr-bell-o bell"></span>
                <span id="notification-num" class="num">0</span>
            </a>
            <div class="noti-pop">
                <div class="noti-pop-hd">
                    <span class="title">通知</span>
                    <a class="noti-settings" href="/smart-api/user/settings" title="设置通知发送方式"  data-stack-root>
                        <i class="twr twr-cog"></i>
                    </a>
                    <a id="noti-mark-read" class="mark-as-read" href="javascript:;" title="全部标记为已读">
                        <i class="twr twr-check"></i>
                    </a>
                </div>
                <div class="noti-pop-list notification-list"></div>
                
                <div id="noti-pop-empty" class="noti-pop-empty hide">- 没有新通知 -</div>
                
                <div class="noti-pop-action">
                    <a class="noti-all-link" href="/smart-api/notify/1/notifications/" data-stack data-stack-root>查看全部</a>
                </div>
                
            </div>
        </div>
       
       
       
        <div class="account-info">
            <div class="member-settings">
                <a class="link-member-menu" href="javascript:;" title="${userName }">
                    <img class="avatar" src="/smart-api/assets/default_avatars/${empty cookie.avatar.value ? '2.jpg' : cookie.avatar.value}" alt="${userName }" />
                    <span class="twr twr-caret-down"></span>
                </a>
            </div>
        </div>
        
        
    </div>
</div>

<script id="tpl-member-menu" type="text/html">
    <ul class="menu">
        <li>
            <a href="/smart-api/user/settings" data-stack-root="true" >个人设置</a>
        </li>
		<c:if test="${cookie.roleId.value eq 8686 || cookie.roleId.value eq 1}">
       		 <li>
            	<a href="/smart-api/sys/settings" data-stack-root="true" >配置中心</a>
        	</li>
		</c:if>
        <li class="part-line"></li>
        <li><a href="javascript:void();" target="_blank">最新功能</a></li>
        <li><a href="/smart-api/index/signOut" data-method="DELETE" rel="nofollow">退出</a></li>
    </ul>
</script>



<script id="tpl-team-menu" type="text/html">
    <ul class="menu">
			<c:forEach var="obj" items="${allItem }">
				<li><a href="/smart-api/api/searchApi?belongItemId=${obj.id }"  data-stack-root>${obj.name}</a></li>
			</c:forEach>

    
            <li class="part-line"></li>

        	<li class="small"><a href="/smart-api/item/createItem/">创建项目</a></li>
			<li class="small"><a href="/smart-api/item/0/createModule/">创建模块</a></li>
			<li class="small"><a href="/smart-api/api/createAPI/">创建API</a></li>
			
			<li class="part-line"></li>

			<li class="small"><a href="/smart-api/event/sysLog/">系统日志</a></li>
    </ul>
</script>

