<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp"%>

<body>
	<div id="page-loading-mask"></div>

	<div class="wrapper">

		<%@ include file="/inc-menu-head.jsp"%>



		<div class="container workspace">
			<!-- 
			<div class="page">
				<a href="/teams/13cc793de0fe4059b21209d3d28bbfea/members"
					class="link-page-behind" data-stack>团队</a>
			</div>
			<div class="page">
				<a href="/teams/13cc793de0fe4059b21209d3d28bbfea/invite/new"
					class="link-page-behind" data-stack>添加新成员</a>
			</div>
			 -->
			<div class="page">

				<div class="page-inner" id="page-invite" data-page-name="邮件邀请">
					<h3>邮件邀请</h3>
					<form class="form form-invite"
						action="/smart-api/user/addUser"
						method="post"  novalidate>
						<div class="form-item">
							<div class="form-field">
								<div class="invite-item">
									<div class="invite-field">
										<input type="email" name="email" class="invite-email no-border"
											placeholder="请输入新成员的邮箱" />

										<div class="invite-role-field">
											<select class="invite-role" id="choose-role" name="role_id" tabindex="-1">
												<option value="0" selected>成员</option>
												<option value="1">管理员</option>
												<option value="3">访客</option>
											</select>
										</div>

										<div class="invite-subgroup-field">
											<select class="invite-subgroup" id="choose-subgroup"
												name="subgroup_id" tabindex="-1">
												
												<option value="0">小组</option>
												<option disabled>-----</option>
												<c:forEach var="obj" items="${allUserGroup }">
													<option value="${obj.id }">${obj.groupName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="invite-item">
									<div class="invite-field">
										<input type="email" name="email" class="invite-email no-border"
											placeholder="请输入新成员的邮箱" />

										<div class="invite-role-field">
											<select class="invite-role" id="choose-role" name="role_id" tabindex="-1">
												<option value="0" selected>成员</option>
												<option value="1">管理员</option>
												<option value="3">访客</option>
											</select>
										</div>

										<div class="invite-subgroup-field">
											<select class="invite-subgroup" id="choose-subgroup"
												name="subgroup_id" tabindex="-1">
												<option value="0">小组</option>
												<option disabled>-----</option>
												<c:forEach var="obj" items="${allUserGroup }">
													<option value="${obj.id }">${obj.groupName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="invite-item">
									<div class="invite-field">
										<input type="email" name="email" class="invite-email no-border"
											placeholder="请输入新成员的邮箱" />

										<div class="invite-role-field">
											<select class="invite-role" id="choose-role" name="role_id" tabindex="-1">
												<option value="0" selected>成员</option>
												<option value="1">管理员</option>
												<option value="3">访客</option>
											</select>
										</div>

										<div class="invite-subgroup-field">
											<select class="invite-subgroup" id="choose-subgroup"
												name="subgroup_id" tabindex="-1">
												<option value="0">小组</option>
												<option disabled>-----</option>
												<c:forEach var="obj" items="${allUserGroup }">
													<option value="${obj.id}">${obj.groupName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
							<p class="add-invite-wrap">
								<a href="javascript:;" id="add-invite-item">再加一个</a>
							</p>
						</div>

						<div class="form-item proj-in">
							<label class="form-label">选择他们能够参与的项目<span
								class="select-all">[ <a href="#" class="select-all-proj">全选</a>
									| <a class="select-none-proj" href="#">全不选</a> ]
							</span></label>
							<div class="form-field">
								<c:forEach var="obj" items="#{allItem }">
									<label title="${obj.name }" class=welcome><input
									type="checkbox" name="init-projects[]"
									value=${obj.id } /> <span>${obj.name }</span></label>
								</c:forEach>
							</div>
						</div>


						<div class="invite-message-wrap">
							<div class="form-item">
								<label class="form-label" for="txt-welcome">邀请邮件附言</label>
								<div class="form-field">
									<textarea name="welcome_words" id="txt-welcome"
										placeholder="请把附言写在这里（可选）"></textarea>
								</div>
								<p class="desc">
									发送邀请后，被邀请的成员会收到一封邀请邮件，这封邮件会引导他们加入团队。 
									<a href="javaScript:void(0);" onclick="javaScript:void(0);" class="view-mail" >查看邀请邮件的示例
									 <i class="twr twr-caret-right"></i>
									</a>
								</p>
							</div>
						</div>

						<div class="form-buttons">
							<button type="submit" class="btn btn-primary" id="btn-invite"
								data-disable-with="正在邀请..." data-success-text="邀请成功"
								data-goto-root
								data-goto="/teams/13cc793de0fe4059b21209d3d28bbfea/members">发送邀请</button>
							<!--<a class="btn btn-x" href="/teams/13cc793de0fe4059b21209d3d28bbfea/invite/new" data-stack data-stack-bare data-stack-root>取消，并返回上一页</a>-->
						</div>
					</form>
				</div>

			</div>
		</div>
		<%@ include file="/inc-content-foot.jsp"%>


	</div>

	<%@ include file="/inc-foot.jsp"%>


</body>
</html>


