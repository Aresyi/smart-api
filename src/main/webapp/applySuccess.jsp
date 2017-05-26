<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="/inc-common.jsp" %>

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
            <form class="form" action=""  method="post" novalidate>
                <div class="signin-title" style="font-size: 25px;">
                	<a href="javascript:;" class="link-normal-signin"><font color='green'>申请成功，请查收邮件</font></a>
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
                            	公司名称: ${companyName }
                        </div>
                    </div>
                    

                    <div class="form-item">
                        <div class="form-field">
                            	联系人: ${linkman }
                        </div>
                    </div>
                    
                    
                    <div class="form-item">
                        <div class="form-field">
                           	        联系人邮箱: ${email}
                        </div>
                    </div>

                    <div class="form-item form-buttons">
                    </div>
                </div>


            </form>
        </div>
    </div>

    <%@ include file="/inc-content-foot.jsp" %>

</div>




    </div>



    
</body>
</html>