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
					<td><input type='text' style='width:150px;' placeholder='数据库中文名'  data-validate='required;length:1,255' data-validate-msg='请填写数据库中文名;数据库中文名最长255个字符' name='dbChineseName' id='dbChineseName' autocomplete='off' value='${item.dbChineseName}' /></td>
					<td><input type='text' style='width:140px;' placeholder='主机IP'  data-validate='required;length:1,15' data-validate-msg='请填写主机IP;主机IP最长15个字符' name='host' id='host' autocomplete='off' value='${item.host}' /></td>
					<td><input type='text' style='width:80px;'  placeholder='端口'  data-validate='required;length:1,10' data-validate-msg='请填写数据库端口;数据库端口最长10个数字' name='port' id='port' autocomplete='off' value='${item.port}' /></td>
					<td><input type='text' style='width:140px;' placeholder='数据库'  data-validate='required;length:1,50' data-validate-msg='请填写数据库;数据库最长50个字符' name='dbName' id='dbName' autocomplete='off' value='${item.dbName}' /></td>
					<td><input type='text' style='width:140px;' placeholder='用户名'  data-validate='required;length:1,50' data-validate-msg='请填写用户名;用户名最长50个字符' name='testName' id='testName' autocomplete='off' value='${item.testName}' /></td>
					<td><input type='text' style='width:135px;' placeholder='密码'  data-validate='required;length:1,50' data-validate-msg='请填写密码;密码最长50个字符' name='testPassword' id='testPassword' autocomplete='off' value='${item.testPassword}' /></td>
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
				<td><input type='text' style='width:150px;' placeholder="token名称"  name='tokenName' id='tokenName' autocomplete='off' value='${confInf.tokenName}' /></td>
				<td><input type='text' style='width:650px;' placeholder="token默认值"  name='tokenDefValue' id='tokenDefValue' autocomplete='off' value='${confInf.tokenDefValue}' /></td>
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
					<td><input type='text' style='width:150px;' placeholder="服务名称,如：谷歌测试服务器"  name='hostName' id='dbChineseName' autocomplete='off' value='${item.hostName}' data-validate='required;length:1,255' data-validate-msg='请填写服务名称;服务名称最长255个字符'/></td>
					<td><input type='text' style='width:650px;' placeholder="服务地址,如:http://www.google.com/"  name='hostUrl' id='host' autocomplete='off' value='${item.hostUrl}' data-validate='required;length:1,255' data-validate-msg='请填写服务地址;服务地址最长255个字符'/></td>
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
				<td>公众号AppId：</td>
				<td colspan="1" ><input style="display: inline" type='text' style='width:150px;' placeholder="appId"  name='wxAppId' id='wxAppId' autocomplete='off' value='${confInf.wxAppId}' /></td>
			</tr>
			<tr>
				<td>公众号Secret：</td>
				<td colspan="1"><input style="display: inline" type='text' style='width:650px;' placeholder="appSecret"  name='wxAppSecret' id='wxAppSecret' autocomplete='off' value='${confInf.wxAppSecret}' /></td>
			</tr>
			<tr>
				<td >授权登录回调：</td>
				<td><input type='text' style='width:650px;' placeholder="请输入自己服务器的授权回调接口或复制本系统提供的接口"  name='wxAuthCallackUrl' id='wxAuthCallackUrl' autocomplete='off' value='${confInf.wxAuthCallackUrl}' /></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="1" style="color:gray">本系统提供的回调地址：http://www.179smart.com/smart-api/callack/wxAuthDefault</td>
			</tr>
			<tr>
				<td >消息模板ID：</td>
				<td><input type='text' style='width:650px;' placeholder="微信模板消息id，形如：_m9XhMv6M0VOCwCX6BnKmTB0OlYg21crH_MeKkD7s2A"  name='wxTemplateId' id='wxTemplateId' autocomplete='off' value='${confInf.wxTemplateId}' /></td>
			</tr>
			<tr>
				<td >模板数据格式：</td>
				<td><textarea type='text' style='width:650px;height: 150px' rows="5"  placeholder='微信模板数据：形如：{
				first:{value:"$title",color:"#173177"},
				keyword1:{value:"$itemName",color:"#173177"},
				keyword2:{value:"已更新",color:"#173177"},
				remark:{value:"$updateInfo",color:"#173177"}
				}'  name='wxTemplateData' id='wxTemplateData' autocomplete='off' value='${confInf.wxTemplateData}' >${confInf.wxTemplateData}</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="1" style="color:gray">可以预置参数：$itemName(项目名),$title(消息标题),$updateInfo(修改内容)</td>
			</tr>
			<tr style="color:gray">
				<td colspan="2">配置帮助：<br>
					1.公众号需要是认证服务号，且有模板消息权限;<br>
					2.我们会向回调地址追加请求参数：email、itemId 用于唯一标识用户及项目;<br>
					<a href="javascript:void(0)" onclick="$('#more').show();$(this).hide();">展开更多...</a>
					<span id="more" style="display: none">
					3.尽管使用了自己的服务器回调接口，在接口处理最终还是要调用本系统的hook接口：http://www.179smart.com/smart-api/callack/wxAuth;<br>
					4.调用hook接口时需要保留参数email、itemId，此外还需要带上openId;<br>
					5.<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842" target="_blank">微信公众号授权文档</a>;<br>
					6.<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277" target="_blank">微信公众号模板消息文档</a>;
					</span>
				</td>
			</tr>
		</table>
	</div>
</div>


<div class="form-item sub">
	<div class="form-label">
		<label for="txt-email">版本管理</label>
	</div>
	<div class="form-field">
		<table id='version_table'>
			<tr align="center">
				<td><a href="javaScript:void(0);" onclick="addRowVersion('','');">添加</a></td>
			</tr>
			<c:forEach items="${confInf.versionList}" var="item">
				<tr>
					<td><input onchange="changeFloatInput(this)" type='text' style='width:150px;' name='version' id='version' autocomplete='off' value='${item.version}' data-validate='required;length:1,255;' data-validate-msg='请填写版本;最长10个字符'/></td>
					<td><a href='javaScript:void(0);' onclick='removeRow(this.parentNode.parentNode);'>删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>