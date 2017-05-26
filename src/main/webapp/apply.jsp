<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="/inc-common.jsp" %>

<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
   
   <script type="text/javascript">
   	function change(){
   		document.getElementById("wechatDiv").style.display="block";
   		document.getElementById("normalDiv").style.display="none";
   	}
   </script>     


<div class="page" id="page-signin">
    <div class="sign-page">
        <div class="hd">
            <h1 class="logo">
                <a href="/smart-api/">tower.im</a>
            </h1>
        </div>

        <div class="bd">
            <form class="form" action="apply/submit"  method="post" novalidate>
                <div class="signin-title">
                    <a href="javascript:;" class="link-normal-signin">注册</a>
                    <span>或</span>
                    <a href="/smart-api/index/login" class="link-dingtalk-signin" >
                        <i class="twr twr-dingtalk"></i>登录
                    </a>
                </div>

                <div id = "normalDiv" class="normal-signin sigin-section">
                    <div class="signin-arrow">
                        <i class="arrow arrow-shadow-1"></i>
                        <i class="arrow arrow-shadow-0"></i>
                        <i class="arrow arrow-border"></i>
                        <i class="arrow arrow-basic"></i>
                    </div>
					<div>
						<font color='red'>${message }</font>
					</div>
                    <div class="form-item">
                        <div class="form-field">
                            <input  name="companyName" id="companyName" placeholder="公司名称" data-validate="required;companyName" data-validate-msg="请填写公司名称" autofocus value=''/>
                        </div>
                    </div>

                    <div class="form-item">
                        <div class="form-field">
                            <input  name="linkman" placeholder="联系人" data-validate="required;linkman" data-validate-msg="请填写联系人" autofocus value=''/>
                        </div>
                    </div>
                    

                    
                    <div class="form-item">
                        <div class="form-field">
                            <input  name="email" placeholder="邮箱（即系统管理员账号）" data-validate="required;email" data-validate-msg="请填写联系人邮箱（即系统管理员账号）" autofocus value=''/>
                        </div>
                    </div>
                    

                    <div class="form-item form-buttons">
                        <button type="submit" id="btn-signin" class="btn btn-primary btn-submit" data-disable-with="正在提交..." data-goto="/launchpad/">注 册</button>
                        <div class="desc">
                            	<!-- 没有账户？<a href="/users/sign_up">立即注册 →</a> -->
                        </div>
                    </div>
                </div>


                <div id = "wechatDiv" class="wechat-signin sigin-section">
                    <div class="signin-arrow">
                        <i class="arrow arrow-shadow-1"></i>
                        <i class="arrow arrow-shadow-0"></i>
                        <i class="arrow arrow-border"></i>
                        <i class="arrow arrow-basic"></i>
                    </div>

                    <div id="wechat-login"><img width="380px" height="300px" src="/smart-api/assets/20170503173143.jpg"></div>
                </div>

                <a href="javascript:;" class="link-wechat-signin" onclick="change();"><i class="twr twr-weixin"></i>加微信申请</a>
            </form>
        </div>
    </div>

    <%@ include file="/inc-content-foot.jsp" %>

</div>




    </div>



    
</body>
</html>