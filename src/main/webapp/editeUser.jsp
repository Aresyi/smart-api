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
				<a href="/members/5d3b77064ecb43579cbfc5666f6f78bb" class="link-page-behind" data-stack>马龙</a>
			</div>
			 -->
			<div class="page">
		
				<div class="page-inner page-member" id="page-member" data-page-name="${one.name } 的设置" >
		            <div class="member-section other-settings">
		                <h3>${one.name } 的设置</h3>
		
		                <div class="sets-item">
		                    <em>从团队中移除 ${one.name }</em><br/>
		                    	被移除的成员，将不能再访问该系统，但跟他相关的数据不会被删除。<br/>
		                    <a href="/smart-api/user/${one.id }/destroy" class="link-delete" data-confirm="&lt;h3&gt;确定要移除 ${one.name } 吗？&lt;/h3&gt;&lt;p&gt;跟 ${one.name } 相关的数据不会被移除，你还可以重新邀请 TA 加入团队。&lt;/p&gt;" data-method="post"  id="link-remove-member" rel="nofollow">了解，从团队中移除${one.name }</a>
		                    <br/>
		                   	 在 ${tranb:longToDateString(one.createTime) }， 由管理员 ${one.creater } 审核加入团队 
		                </div>
		
		                <form class="form form-change-role" action="/smart-api/user/${one.id }/settingsOther" method="post" >
		
		                    <div class="sets-item">
		                        <em>权限</em>
		                        <br>
		                        <div class="form-item choose-roles">
		                            <div>
		                                <label><input type="radio" name="role" value="1" ${one.roleId eq 1 ? 'checked' :'' }/>管理员</label>
		                                <span>可以创建，修改和删除项目。也可以设置其他人为管理员;</span>
		                            </div>
		                            <div>
		                                <label><input type="radio" name="role" value="0" ${one.roleId eq 0 ? 'checked' :'' }/>成员</label>
		                                <span>普通成员，可以看到团队中所有成员以及他参与的内容；</span>
		                            </div>
		                            <div>
		                                <label><input type="radio" name="role" value="3" ${one.roleId eq 3 ? 'checked' :'' }/>访客</label>
		                                <span>只能看到和他在同一个项目中的成员。</span>
		                            </div>
		                        </div>
		                    </div>
		                    
		                    <div class="sets-item">
		                        <em>是否可编辑</em>
		                        <br>
		                         <div class="form-item">
		                        		   <select class="isEdite" id="isEdite"
												name="isEdite" tabindex="-1">
												<option value="0" ${one.isEdite eq 0 ? "selected='selected'" :"" }>否</option>
												<option value="1" ${one.isEdite eq 1 ? "selected='selected'" :"" }>是</option>
											</select>（是否可以编辑他人所创建的内容）
								</div>	
		                    </div>
		                    
		                    <div class="sets-item">
		                        <em>所在组</em>
		                        <br>
		                         <div class="form-item">
		                        		   <select class="invite-subgroup" id="choose-subgroup"
												name="subgroup_id" tabindex="-1">
												<option value="0">小组</option>
												<option disabled>-----</option>
												<c:forEach var="obj" items="${allUserGroup }">
													<option value="${obj.id}" ${one.groupId eq obj.id ? "selected='selected'" :"" }>${obj.groupName }</option>
												</c:forEach>
											</select>
								</div>			
		                    </div>
		
		                    <div class="sets-item">
		                        <em>参与的项目</em>
		                        <span class="select-all"> [ <a href="#" class="select-all-proj">全选</a> | <a class="select-none-proj" href="#">全不选</a> ]</span>
		
		                            <div class="form-item proj-in">
		                                <div class="form-field">
		                                	<c:forEach var="obj" items="#{allItem }">
												<label title="${obj.name }" ><input type="checkbox" name="access-projects[]" <c:if test="${fn:contains(one.permissionAPI,obj.id)}">checked="true"</c:if>   value=${obj.id } />${obj.name }</label>
											</c:forEach>
		                                </div>
		                            </div>
		
		                    </div>
		                    
		
		                    <div class="sets-item">
		                      <button type="submit" class="btn btn-primary" data-success-text="保存成功" data-disable-with="正在保存...">保存设置</button>
		                    </div>
		                </form>
		            </div>
				</div>
		
		    </div>
		</div>

		<%@ include file="/inc-content-foot.jsp"%>

	</div>

	<%@ include file="/inc-foot.jsp"%>

</body>
</html>