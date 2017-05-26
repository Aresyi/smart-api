<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>


<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        

	<%@ include file="/inc-menu-head.jsp" %>


<div class="container workspace">
    <div class="page">
	
	    <div class="page-inner page-member-settings" id="page-member-settings" data-page-name="我自己" nav="me">
            <p class="page-tip moveout" style="display:none;"></p>
            <h3>个人设置</h3>

            <form class="form settings-form" action="user/saveSettings" method="post" >
                <div class="form-item upload-avatar" data-droppable>
                    <div class="avatar-wrapper">
                        <img class="avatar" src="/smart-api/assets/default_avatars/${empty user.avatar ? '2.jpg' : user.avatar}" />
                        <div class="loading"></div>
                    </div>
                    <div class="link-upload" data-url="upload/avatars/">
                      <a id="btn-upload" href="javascript:void(0);">选择新头像</a>
                      <input type="file" title="选择新头像" name="upload_file" tabindex="-1" style="position: absolute; right: 0px; top: 0px; font-family: Arial; font-size: 118px; margin: 0px; padding: 0px; cursor: pointer; opacity: 0;">
                    </div>
                    <p class="desc">你可以选择 png/jpg 图片作为头像</p>
                </div>
                <div class="form-item">
                    <div class="form-label">
                        <label for="txt-email">邮箱</label>
                    </div>
                    <div class="form-field">
                        <input type="text" name="email" id="txt-email" value="${user.email }" disabled />
                    </div>
                </div>


                <div class="form-item">
                    <div class="form-label">
                        <label for="txt-nickname">名字</label>
                    </div>
                    <div class="form-field">
                        <input type="text" name="nickname" id="txt-nickname" autocomplete="off" value="${user.name }"
                            data-validate="required;length:1,30" data-validate-msg="好像还没有输入名字呢;名字最长30个字符" />
                    </div>
                </div>

                <div class="form-item">
                    <div class="form-label">
                        <label>密码</label>
                    </div>
                    <div class="form-field form-text-field">
                        <a href="/smart-api/user/updatePass" >修改密码</a>
                    </div>
                </div>

				<div class="form-item">
                    <div class="form-label">
                        <label for="link-notify-settings#">通知设置</label>
                    </div>
                    <div class="form-field form-text-field notification-field">
                        <a href="javaScript:void();" class="link-notify-settings">修改通知设置</a>
                        <p class="desc">
				                            桌面通知：关闭<br />
				                            邮件通知：开启<br />
				                            智能提醒：网页在线时，不发送邮件通知和客户端推送<br />
				                            延期任务通知邮件：开启
                        </p>
                    </div>
                </div>


                <div class="form-buttons">
                    <button class="btn" id="btn-save" data-disable-with="正在保存..."
                        data-success-text="保存成功">保存</button>
                </div>
            </form>


    </div>
</div>
	<%@ include file="/inc-content-foot.jsp" %>


    </div>

   <%@ include file="/inc-foot.jsp" %>

    
</body>
</html>
