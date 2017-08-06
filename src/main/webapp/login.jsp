<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="/inc-common.jsp" %>

<!-- 自动登录 -->
<script>
    $(function () {
        var email = cookie.get("emailRe");
        var password = cookie.get("passwordRe");
        if(email && password){
            if(email.indexOf("%40")>-1){
                email = email.replace("%40","@");
            }
            $("#email").val(email);
            $("#password").val(password);
            $("#btn-signin").click();
        }
    });
</script>

<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        


<div class="page" id="page-signin">
    <div class="sign-page">
        <div class="hd">
            <h1 class="logo">
                <a href="/smart-api/">tower.im</a>
            </h1>
        </div>

        <div class="bd">
            <form class="form" action="index/signIn"  method="post" novalidate>
                <div class="signin-title">
                    <a href="javascript:;" class="link-normal-signin">账户登录</a>
                    <span>或</span>
                    <a href="/smart-api/go/apply/page" class="link-dingtalk-signin">
                        <i class="twr twr-dingtalk"></i>账户注册
                    </a>
                </div>

                <div class="normal-signin sigin-section">
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
                            <input type="email" name="email" id="email" placeholder="登录邮箱" data-validate="required;email" data-validate-msg="请填写你的登录邮箱" autofocus value=''/>
                        </div>
                    </div>

                    <div class="form-item">
                        <div class="form-field">
                            <input type="password" name="password" id="password" placeholder="密码" data-validate="required;length:6" data-validate-msg="请填写你的登录密码" value=''/>
                        </div>
                        <div class="desc">
                            <p class="left">
                                <label for="cb-remember"><input type="checkbox" name="remember_me" id="cb-remember" checked  value="1"/> 下次自动登录</label>
                            </p>
                            <p class="right">
                                <span class="forgot-pw"><a href="#">忘记密码了？</a></span>
                            </p>
                        </div>
                    </div>

                    <div class="form-item form-buttons">
                        <button type="submit" id="btn-signin" class="btn btn-primary btn-submit" data-disable-with="正在登录..." data-goto="/launchpad/">登录</button>
                        <div class="desc">
                            没有账户？<a href="/smart-api/go/apply/page">立即注册 →</a>
                        </div>
                    </div>
                </div>

                <div class="dingtalk-signin sigin-section">
                    <div class="signin-arrow">
                        <i class="arrow arrow-shadow-1"></i>
                        <i class="arrow arrow-shadow-0"></i>
                        <i class="arrow arrow-border"></i>
                        <i class="arrow arrow-basic"></i>
                    </div>

                    <div id="dingtalk-login"></div>
                </div>

                <div class="wechat-signin sigin-section">
                    <div class="signin-arrow">
                        <i class="arrow arrow-shadow-1"></i>
                        <i class="arrow arrow-shadow-0"></i>
                        <i class="arrow arrow-border"></i>
                        <i class="arrow arrow-basic"></i>
                    </div>

                    <div id="wechat-login"></div>
                </div>

                <!--  <a href="javascript:;" class="link-wechat-signin"><i class="twr twr-weixin"></i>微信登录</a> -->
            </form>
        </div>
    </div>

    <%@ include file="/inc-content-foot.jsp" %>

</div>


<script type="text/javascript" src="//res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js">
</script>
<script type="text/javascript">
(function() {
    new WxLogin({
        id: 'wechat-login',
        appid: 'wxa2402e6aefdd4747',
        scope: 'snsapi_login',
        redirect_uri: 'https://tower.im/auth/open_wechat/callback',
        href: '//tower.im/wechat-signin.css',
        state: '46be517d383943c7be6e5479ec65d7d7c14edad047fd7e8d'
    });
    $('#wechat-login iframe').height(300);
})();
</script>

<script type="text/javascript" src="//g.alicdn.com/dingding/dinglogin/0.0.2/ddLogin.js">
</script>
<input type="hidden" id="dingtalk-sns-url" value="https%3A%2F%2Foapi.dingtalk.com%2Fconnect%2Foauth2%2Fsns_authorize%3Fappid%3Ddingoacew1vv45xrvaorzl%26response_type%3Dcode%26scope%3Dsnsapi_login%26redirect_uri%3Dhttps%3A%2F%2Ftower.im%2Fdingtalk%2Fqr">

    </div>

    <input type="hidden" id="d18n-enabled" value="false" />
    <input type="hidden" id="server-time" value="2016-07-01 10:54:06" />



    
</body>
</html>