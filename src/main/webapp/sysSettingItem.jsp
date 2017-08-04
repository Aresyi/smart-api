<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-item sub" >
	<div class="form-label">
		<label for="txt-nickname">数据库</label>
	</div>
	<div class="form-field" >
		<table id='db_table'>
			<tr align="center">
				<td><a href="javaScript:void(0);" onclick="addRow2('','');">添加</a></td>
			</tr>
			<c:forEach items="${confInf.dbList}" var="item">
				<tr>
					<td><input type='text' style='width:150px;' placeholder='数据库中文名' autofocus='' data-validate='required;length:1,255' data-validate-msg='请填写数据库中文名;数据库中文名最长255个字符' name='dbChineseName' id='dbChineseName' autocomplete='off' value='${item.dbChineseName}' /></td>
					<td><input type='text' style='width:140px;' placeholder='主机IP' autofocus='' data-validate='required;length:1,15' data-validate-msg='请填写主机IP;主机IP最长15个字符' name='host' id='host' autocomplete='off' value='${item.host}' /></td>
					<td><input type='text' style='width:80px;'  placeholder='端口' autofocus='' data-validate='required;length:1,10' data-validate-msg='请填写数据库端口;数据库端口最长10个数字' name='port' id='port' autocomplete='off' value='${item.port}' /></td>
					<td><input type='text' style='width:140px;' placeholder='数据库' autofocus='' data-validate='required;length:1,50' data-validate-msg='请填写数据库;数据库最长50个字符' name='dbName' id='dbName' autocomplete='off' value='${item.dbName}' /></td>
					<td><input type='text' style='width:140px;' placeholder='用户名' autofocus='' data-validate='required;length:1,50' data-validate-msg='请填写用户名;用户名最长50个字符' name='testName' id='testName' autocomplete='off' value='${item.testName}' /></td>
					<td><input type='text' style='width:135px;' placeholder='密码' autofocus='' data-validate='required;length:1,50' data-validate-msg='请填写密码;密码最长50个字符' name='testPassword' id='testPassword' autocomplete='off' value='${item.testPassword}' /></td>
					<td><a href='javaScript:void(0);' onclick='removeRow(this.parentNode.parentNode);'>删除</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</div>

<div class="form-item sub">
	<div class="form-label">
		<label for="txt-email">TOKEN</label>
	</div>
	<div class="form-field">
		<table>
			<tr>
				<td><input type='text' style='width:150px;' placeholder="token名称" autofocus='' name='tokenName' id='tokenName' autocomplete='off' value='${confInf.tokenName}' /></td>
				<td><input type='text' style='width:650px;' placeholder="token默认值" autofocus='' name='tokenDefValue' id='tokenDefValue' autocomplete='off' value='${confInf.tokenDefValue}' /></td>
			</tr>
		</table>
	</div>
</div>

<div class="form-item sub">
	<div class="form-label">
		<label for="txt-nickname">测试服务</label>
	</div>
	<div class="form-field" >
		<table id='test_table'>
			<tr align="center">
				<td><a href="javaScript:void(0);" onclick="addRow3('','');">添加</a></td>
			</tr>
			<c:forEach items="${confInf.testSerList}" var="item">
				<tr>
					<td><input type='text' style='width:150px;' placeholder="服务名称,如：谷歌测试服务器" autofocus='' name='hostName' id='dbChineseName' autocomplete='off' value='${item.hostName}' data-validate='required;length:1,255' data-validate-msg='请填写服务名称;服务名称最长255个字符'/></td>
					<td><input type='text' style='width:650px;' placeholder="服务地址,如:http://www.google.com/" autofocus='' name='hostUrl' id='host' autocomplete='off' value='${item.hostUrl}' data-validate='required;length:1,255' data-validate-msg='请填写服务地址;服务地址最长255个字符'/></td>
					<td><a href='javaScript:void(0);' onclick='removeRow(this.parentNode.parentNode);'>删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<div class="form-item sub">
	<div class="form-label">
		<label for="txt-email">微信配置</label>
	</div>
	<div class="form-field">
		<table>
			<tr>
				<td><input type='text' style='width:150px;' placeholder="appId" autofocus='' name='wxAppId' id='wxAppId' autocomplete='off' value='${confInf.wxAppId}' /></td>
				<td><input type='text' style='width:650px;' placeholder="appSecret" autofocus='' name='wxAppSecret' id='wxAppSecret' autocomplete='off' value='${confInf.wxAppSecret}' /></td>
			</tr>
		</table>
	</div>
</div>