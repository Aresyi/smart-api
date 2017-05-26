<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>

<link type="text/css" rel="stylesheet" href="/smart-api/htdocs/js/My97DatePicker/skin/WdatePicker.css">

<script type="text/javascript" src="/smart-api/htdocs/js/My97DatePicker/WdatePicker.js"></script>
<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        

	<%@ include file="/inc-menu-head.jsp" %>


<div class="container workspace">
 <!--
    <div class="page">
       <a href="/smart-api/index/home/" class="link-page-behind" >所有项目</a>   
    </div>
 -->
    <div class="page">
    
			
	
    <div class="page-inner" id="page-new-project" data-page-name="足迹" nav="track">
        <h3>版本迭代记录</h3>
        <span><font color="red">${message }</font>
		</span>
		<form action="/smart-api/track/editeTrack/" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" name="id" value="${one.id }" />
			<table cellpadding="0" border="1" cellpadding="0" cellspacing="0"
				width="100%" bordercolor="gray">
				
				<tr>
					<td align="left">
						项&nbsp;&nbsp;&nbsp;&nbsp;目
					</td>
					<td style='text-align: left;'>
						<select name="belongItemId" onchange="toChangeModule(this);">
							<option value=''></option>
							<c:forEach var="obj" items="${allItem }">
								<option value="${obj.id }" ${obj.id == one.belongItemId ? 'selected' : ''}>${obj.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr >
					<td align="left">
						迭代版本
					</td>
					<td>
						<input type="text" id="version" name="version" value="${one.version }" size="114" maxlength="20"/>
					</td>
				</tr>

				<tr>
					<td align="left">
						需求简述
					</td>
					<td>
						<textarea id="reqDes" name="reqDes" style='height: 50px;width: 800px;'>${one.reqDes }</textarea>
					</td>
				</tr>

				<tr>
					<td align="left">
						版本目标
					</td>
					<td>
						<textarea id="target" name="target" style='height: 100px;width: 800px;'>${one.target }</textarea>
					</td>
				</tr>

				
				<tr>
					<td align="left">
						升级说明
					</td>
					<td>
						<textarea id="upgradeDes" name="upgradeDes" style='height: 80px;width: 800px;'>${one.upgradeDes }</textarea>
					</td>
				</tr>
				 
				
				<tr >
					<td align="left">
						发版日期
					</td>
					<td>
						<input type="text" id="releaseDate" name="releaseDate" value="${one.releaseDate }" size="114" maxlength="20" readonly="readonly" class="Wdate" onfocus="WdatePicker()"/>
					</td>
				</tr>
				
				<tr >
					<td align="left">
						检测日期
					</td>
					<td>
						<input type="text" id="checkDate" name="checkDate" value="${one.checkDate }" size="114" maxlength="20" readonly="readonly" class="Wdate" onfocus="WdatePicker()"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">
						目标结果
					</td>
					<td>
						<textarea id="targetResult" name="targetResult" style='height: 100px;width: 800px;'>${one.targetResult }</textarea>
					</td>
				</tr>
				
				<tr >
					<td align="left">
						责任人
					</td>
					<td>
						<input type="text" id="dutyPerson" name="dutyPerson" value="${one.dutyPerson }" size="114" maxlength="20"/>
					</td>
				</tr>

				<tr align="center">
					<td colspan="2">
						<input type="submit" class="btn" value="保 存" />
					</td>
				</tr>
			</table>
		</form>
    </div>

    </div>
</div>
	<%@ include file="/inc-content-foot.jsp" %>


    </div>

   <%@ include file="/inc-foot.jsp" %>

    
</body>
</html>
