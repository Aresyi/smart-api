<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp"%>

<style>
<!--

.member-info {
    position: relative;
    padding: 20px 40px;
    border-bottom: 1px solid #e6e6e6;
}

.member-card {
    margin-left: 100px;
    min-height: 80px;
    text-align: left;
}

.member-card .member-comment {
    padding-top: 3px;
    font-size: 14px;
    color: #999;
}

.member-card b {
    margin: 0;
    color: #333;
    font-size: 28px;
    font-weight: normal;
    line-height: 36px;
}

.member-info .member-control {
    position: absolute;
    right: 40px;
    top: 25px;
}

.member-nav {
    border-bottom: 1px solid #e6e6e6;
    padding: 0 20px;
}
-->
</style>

<body>
	<div id="page-loading-mask"></div>

	<div class="wrapper">


		<%@ include file="/inc-menu-head.jsp"%>



		<div class="container workspace">
			
			<div class="page">

				<div class="page-inner page-member" id="page-member"
					data-guid="${user.id }"
					data-since="2017-06-20 04:14:19 UTC" data-page-name="${user.name }">

					<div class="member-info">
						<img class="avatar" alt="${user.name }" src="/smart-api/assets/default_avatars/${empty user.avatar ? '2.jpg' : user.avatar}" />
						<div class="member-card">
							<p>
								<b>${user.name }</b>
							</p>

							<p class="member-comment">${user.email }</p>
							<p class="member-comment"></p>
						</div>

						<div class="member-control">
							<a href="${(cookie.roleId.value eq 8686 || cookie.roleId.value eq 1) ? '/smart-api/user/update/' : 'javaScript:void(0);' }${user.id }" >
								${user.roleId eq 0 ? "成员" : user.roleId eq 1 ? "管理员" :  user.roleId eq 8686 ? "Support" :"访客" }
							</a>
						</div>
					</div>

					<div class="member-nav">
						<ul>
							<li class="active"><a href="javascript:;" onclick="javaScript:void(0);">任务</a></li>


							<li class=""><a href="javascript:;" onclick="javaScript:void(0);">作品</a></li>

							<li class=""><a href="javascript:;" onclick="javaScript:void(0);">日报</a></li>
							<li class=""><a href="javascript:;" onclick="javaScript:void(0);">贡献</a></li>

							<li class="member-nav-signin-logs"><a
								href="#"
								class="signin-logs" data-stack data-tower-pro=security>
									上次登录: <span data-readable-time="${time }"></span>
							</a>
							</li>
						</ul>
					</div>


					<p class="page-tips moveout inbox-moveout"></p>


					<div class="member-section member-todos">
						<!-- 
						<select id="project-filter">
							<option value="-1">所有项目</option>
						</select>
 						-->

						<div class="boxes"
							data-sort-url="/members/cf99cd6d64594a03bf758436dd69a217/sort_todo">
							<div class="box box-new" data-box="0">
								<h5 class="box-title">
									<i class="twr twr-inbox"></i>新任务
								</h5>
								<p class="box-empty-desc">当前没有新任务</p>
								<div class="todolist">
									
								</div>

							</div>

							<div class="box box-droppable box-today" data-box="1">
								<h5 class="box-title">
									<i class="twr twr-crosshairs"></i>今天
								</h5>
								<p class="box-empty-desc">没有今日任务</p>
								<div class="todolist">
									
								</div>

							</div>

							<div class="box box-droppable box-next" data-box="2">
								<h5 class="box-title">
									<i class="twr twr-tasks"></i>接下来
								</h5>
								<p class="box-empty-desc">没有确定的任务</p>
								<div class="todolist">
									
								</div>

							</div>

							<div class="box box-droppable box-later" data-box="3">
								<h5 class="box-title">
									<i class="twr twr-archive"></i>以后
								</h5>
								<p class="box-empty-desc">没有待考虑的任务</p>
								<div class="todolist">
									
								</div>

							</div>
						</div>



						<div class="more">
							<a href="javascript:;" onclick="javaScript:void(0);">更多</a> 
							<a href="javascript:;" onclick="javaScript:void(0);">MORE</a>
						</div>
					</div>
					</div>
					</div>
					<%@ include file="/inc-content-foot.jsp"%>


				</div>

				<%@ include file="/inc-foot.jsp"%>
</body>
</html>
