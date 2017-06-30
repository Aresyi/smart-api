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
        <h3>创建足迹</h3>
        <span><font color="red">${message }</font>
		</span>
		<form action="/smart-api/track/addTrack/" method="post" onsubmit="return checkForm(this);">
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
								<option value="${obj.id }" ${obj.id == belongItemId ? 'selected' : ''}>${obj.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td align="left">
						类&nbsp;&nbsp;&nbsp;&nbsp;型
					</td>
					<td style='text-align: left;'>
						<select name="belongItemId" onchange="toChangeModule(this);">
							<option value='-1'>-</option>
							<option value='0'>产品迭代发版</option>
							<option value='1'>运营事件</option>
							<option value='2'>市场活动</option>
						</select>
					</td>
				</tr>
				
				<tr >
					<td align="left">
						版&nbsp;&nbsp;&nbsp;&nbsp;本
					</td>
					<td>
						<input type="text" id="version" name="version" value="" size="114" maxlength="20"/>
					</td>
				</tr>

				<tr>
					<td align="left">
						运作简述
					</td>
					<td>
						<textarea id="reqDes" name="reqDes" style='height: 50px;width: 800px;'>${reqDes }</textarea>
					</td>
				</tr>

				<tr>
					<td align="left">
						目&nbsp;&nbsp;&nbsp;&nbsp;标
					</td>
					<td>
						<textarea id="target" name="target" style='height: 100px;width: 800px;'>${target }</textarea>
					</td>
				</tr>

				
				<tr >
					<td align="left">
						开始日期
					</td>
					<td>
						<input type="text" id="releaseDate" name="releaseDate" value="${releaseDate }" size="114" maxlength="20" readonly="readonly" class="Wdate" onfocus="WdatePicker()"/>
					</td>
				</tr>
				
				<tr >
					<td align="left">
						验收日期
					</td>
					<td>
						<input type="text" id="checkDate" name="checkDate" value="${checkDate }" size="114" maxlength="20" readonly="readonly" class="Wdate" onfocus="WdatePicker()"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">
						验收结果
					</td>
					<td>
						<textarea id="targetResult" name="targetResult" style='height: 100px;width: 800px;'>${targetResult }</textarea>
					</td>
				</tr>
				
				<tr >
					<td align="left">
						责任人
					</td>
					<td>
						<input type="text" id="dutyPerson" name="dutyPerson" value="${dutyPerson }" size="114" maxlength="20"/>
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
