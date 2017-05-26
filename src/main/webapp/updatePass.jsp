<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>


<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        

	<%@ include file="/inc-menu-head.jsp" %>


<div class="container workspace">
    <div class="page">
	
	   <div class="page-inner page-member-settings" id="page-member-settings-password" data-page-name="更改密码">
			<h3>更改密码</h3>

			<form class="form form-member-setting-password" action="user/savePass" method="post" >
				<div class="form-item">
					<div class="form-label">
						<label for="txt-old-password">现有密码</label>
					</div>
					<div class="form-field">
						<input type="password" id="txt-old-password" name="old_password" autocomplete="off" data-validate="required;length:6"
							data-validate-msg="还没有填写当前登录密码;登录密码需要至少有6位" />
					</div>
				</div>
				<div class="form-item">
					<div class="form-label">
						<label for="txt-password">新密码</label>
					</div>
					<div class="form-field">
						<input type="password" id="txt-password" name="password" autocomplete="off" data-validate="required;length:6"
							data-validate-msg="还没有填写新的密码;登录密码需要至少有6位" />
						<p class="desc">强烈建议密码同时包含字母、数字和标点符号。</p>
					</div>
				</div>
				<div class="form-buttons">
					<button class="btn btn-large" id="btn-save" data-disable-with="正在保存..."
						data-success-text="保存成功">保存</button>${message }
				</div>
			</form>
		</div>
</div>
	<%@ include file="/inc-content-foot.jsp" %>


    </div>

   <%@ include file="/inc-foot.jsp" %>

    
</body>
</html>
