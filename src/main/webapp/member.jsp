<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp"%>

<body>
	<div id="page-loading-mask"></div>

	<div class="wrapper">

		<%@ include file="/inc-menu-head.jsp"%>



		<div class="container workspace">
			<div class="page">

				<div class="page-inner" id="page-members" data-page-name="团队" nav="members">

					<div class="group group-default" data-guid="0">
						<ul class="members">
							<li class="member member-invite"><a
								href="/smart-api/user/createMember"
								class="member-link new" > <img alt="邀请新成员"
									class="avatar"
									src="https://tower.im/assets/new-member-fdfef3ec63713f17a363e92f74b70ad4.png" />
									<span class="name">添加新成员</span>
							</a></li>


						</ul>

					</div>

					<div class="grouplists">
					
						<c:forEach var="group" items="${ groupUser}">
								<div class="group" data-guid="2e701a0ff0df4802b0e0b2fd39fcbe14">
									
									<div class="group-hd">
										<h3>
											<span class="group-name">${group.key }[${fn:length(group.value)}]</span> <a
												href="/teams/2c17c1fc24584c0497dd0a6ac8a3ed18/subgroups/2e701a0ff0df4802b0e0b2fd39fcbe14/edit"
												class="edit" data-loaindg="true" data-method="get"
												data-remote="true">编辑</a>
										</h3>
									</div>
									
									<div class="group-bd">
										<ul class="members">
											<c:forEach var="user" items="${ group.value}">
												<li class="member" data-guid="${user.id }"
													data-team-guid="2c17c1fc24584c0497dd0a6ac8a3ed18">
													<a href="${(cookie.roleId.value eq 8686 || cookie.roleId.value eq 1) ? '/smart-api/user/update/' : 'javaScript:void(0);' }${user.id }" title="${user.name }" class="member-link" >
														<img class="avatar" src="assets/default_avatars/${user.avatar }" alt="${user.name }" /> 
														<span class="name">${user.name }</span> 
														<span class="role">${user.roleId eq 0 ? "成员" : user.roleId eq 1 ? "管理员" : "访客" }</span>
													</a>
												</li>
											</c:forEach>
		
										</ul>
									</div>
									
								</div>
							
						</c:forEach>


						<div class="group" data-guid="e4d697077c6e4467a99fddad0eaaf80e">
							<div class="group-hd">
								<h3>
									<span class="group-name">Support</span> <a
										href="/teams/2c17c1fc24584c0497dd0a6ac8a3ed18/subgroups/e4d697077c6e4467a99fddad0eaaf80e/edit"
										class="edit" data-loaindg="true" data-method="get"
										data-remote="true">编辑</a>
								</h3>
							</div>
							<div class="group-bd">
								<ul class="members">

									<c:forEach var="user" items="${ supportList}">
												<li class="member" data-guid="${user.id }"
													data-team-guid="2c17c1fc24584c0497dd0a6ac8a3ed18">
													<a href="javaScript:void(0);" title="${user.name }" class="member-link" > 
														<img class="avatar" src="assets/default_avatars/${user.avatar }" alt="${user.name }" /> 
														<span class="name">${user.name }</span> 
														<span class="role">系统管理员</span>
													</a>
												</li>
									</c:forEach>


								</ul>


							</div>
						</div>


						<div class="group group-new">
							<div class="group-hd">
								<a class="group-new-action" href="javascript:;" title="点击这里创建小组">+
									新建小组</a>
								<div class="group-form hide">
									<form class="form form-create-group"
										action="user/createGroup"
										method="post" >
										<input name="subgroup_name" class="group-name" type="text"
											placeholder="例如：技术部、客服小组" data-validate="custom"
											data-blur-validate="false" data-validate-msg="" />
										<button class="btn btn-primary group-edit-save" type="submit"
											data-disabled-with="正在保存...">保存</button>
										<button type="button" class="btn btn-x cancel">取消</button>
									</form>
								</div>

							</div>
						</div>
					</div>

				</div>

			</div>
		</div>
		<%@ include file="/inc-content-foot.jsp"%>


	</div>

	<%@ include file="/inc-foot.jsp"%>


</body>
</html>


