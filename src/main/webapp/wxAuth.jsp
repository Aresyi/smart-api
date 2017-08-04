<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<title>绑定信息</title>
	<link href="/smart-api/assets/wxAuth.css" rel="stylesheet" style="text/css">


	<style type="text/css">
		.weizhang {
			color: #333;
			font-family: 'Microsoft Yahei';
			font-size: 2.5em;
			text-align: center;
		}

		.weizhang p {
			text-align: center;
			margin: 0px auto;
		}

		.red-txt {
			color: #ff0000;
		}

		.tx_title {
			font-size: 2em;
			text-align: center;
			margin: 10px auto;
		}
		.Login_bottom td a.dl_login_bak {
			background: #ccc;
			height: 36px;
			line-height: 36px;
			color: #fff;
			font-size: 20px;
			border: 1px #ccc solid;
			-webkit-border-radius: 3px;
			-moz-border-radius: 3px;
			-ms-border-radius: 3px;
			-o-border-radius: 3px;
			border-radius: 3px;
			margin: 0 auto;
			width: 40%;
			display: block;
			text-align: center;
		}

		.logo{
			width: 190px;
			height: 56px;
			margin: 30px auto 50px
		}
		.logo a{
			display: block;
			height: 100%;
			background-image: url(/smart-api/assets/logo-sign-dff6b8806ae5cfa80c84502bb5e07c17.png);
			background-repeat: no-repeat;
			background-size: 190px auto;
			background-position: 0 0;
			text-indent: -9999px;
			overflow: hidden;
		}
	</style>
</head>
<body>
<div class="logo"><a href="/smart-api/">tower.im</a></div>
<div class="box-tab">
	<ul id="pagenavi1" class="page">
		<li><a id="a1" href="#" class="active">微信绑定</a>
		</li>

	</ul>
	<div id="slider1" class="swipe">
		<ul class="box01_list">
			<li class="li_list">
				<div class="Login_bottom">


					<a  style=" background: #44B549;
								height: 36px;
								line-height: 36px;
								color: #fff;
								font-size: 20px;
								border: 1px #34AF39 solid;
								-webkit-border-radius: 3px;
								-moz-border-radius: 3px;
								-ms-border-radius: 3px;
								-o-border-radius: 3px;
								border-radius: 3px;
								margin: 0 auto;
								width: 100%;
								display: block;
								text-align: center;">绑定成功</a>


					<div style="text-align: center;height: 80px;font-size:12px;">
						<br /> © 17Smart
					</div>
				</div></li>

		</ul>
	</div>
</div>

</body>

</html>