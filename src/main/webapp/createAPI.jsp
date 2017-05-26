<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>

<script type="text/javascript" src="/smart-api/htdocs/js/comm.js"></script>
<script type="text/javascript" src="/smart-api/assets/dataDesc.js"></script>

<body>
    <!-- <div id="page-loading-mask"></div>  -->

    <div class="wrapper">
        

	<%@ include file="/inc-menu-head.jsp" %>
	


<div class="container workspace">
 <!--
    <div class="page">
       <a href="/smart-api/index/home/" class="link-page-behind" >所有项目</a>   
    </div>
 -->
    <div class="page">
    
			
	<script language="javascript" type="text/javascript">
		//表单操作 
		function addRow() {
			var root = document.getElementById("para_table");
		    var allRows = root.getElementsByTagName('tr');
		    var cRow = allRows[1].cloneNode(true)
		    var cCol = cRow.getElementsByTagName('td');
		    cCol.innerHTML = "";
		    root.appendChild(cRow);
		}
		
		
		function addRow2(name,value){
		     var root = document.getElementById("para_table")
		     var newRow = root.insertRow();
		     var newCell0 = newRow.insertCell();
		     var newCell1 = newRow.insertCell();
		     var newCell2 = newRow.insertCell();
		     var newCell3 = newRow.insertCell();
		     var newCell4 = newRow.insertCell();
		     var newCell5 = newRow.insertCell();
		     
		     var str = "";
		     var num = "";
		     if(isNaN(value)){
		    	 str = "selected";
		     }else{
		    	 num = "selected";
		     }
		     
		     var des = "";
		     if("method"==name){
		     	des = "固定值";
		     }
		     
		     newCell0.innerHTML = "<input style='width: 80px;' name='para_name' value='"+name+"' />";
		     newCell1.innerHTML = "<select style='width: 120px;' name='para_type'><option value='String' "+str+">String</option><option value='JSONArray'>JSONArray</option><option value='JSONObject'>JSONObject</option><option value='Number' "+num+">Number</option></select>";
		     newCell2.innerHTML = "<select style='width: 40px;' name='para_isNeed'><option value='是'>是</option><option value='否'>否</option></select>";
		     newCell3.innerHTML = "<input style='width: 120px;' name='para_des' value='"+des+"' />";
		     newCell4.innerHTML = "<input style='width: 120px;' name='para_caseVal' value='"+value+"' />";
		     newCell5.innerHTML = "<a href='javaScript:void(0);' onclick='removeRow(this.parentNode.parentNode);'>删除</a>";
	
		  }
	
		
		function removeRow(r) {
			var root = r.parentNode;
		    var allRows = root.getElementsByTagName('tr')
		    if(allRows.length>1){
		        root.removeChild(r);
		    }
		}
		
		
		function checkForm(obj){
			var para_name_array = document.getElementsByName("para_name");
			for(var i=0 ;i<para_name_array.length ;i++){
				if(para_name_array[i].value == null || para_name_array[i].value == ""){
					alert("请确保所有参数都有名称");
					return false;
				}
			}
			
			return true;
		}
		
		function toChangeModule(obj){
			var item = obj.value;
			
			
			if(item != ""){
				
				$.ajax({
				    url: '/smart-api/api/ajaxChangeModule',
				    type: 'GET',
				    data:{item: item},
				    dataType:"json",
				    error: function(){
				        alert('Error loading json data document');
				    },
				    success: function(resData){
				    	
			            var o = eval(resData);//将json字符串转换成js对象
			            var sel = document.getElementById("belongModuleId");
			            sel.options.length=0;
			            sel.options.add(new Option("",""));
						for (var i in o) { //循环json对象数组
							//alert(i);
							sel.options.add(new Option(o[i],i));
						}
				    }
				});
				
			}else{
				 var sel = document.getElementById("belongModuleId");
		         sel.options.length=0;
			}
		}
		
	
		
		function parseURL(){
			var url = document.getElementById("url").value;
			//alert(urlRegEx(url));
			
			if(url == null || url == ""){
				alert("请填写URL");
				return ;
			}
			
			var paras = url.substr(url.indexOf("?")+1);
			//alert(paras);
			
			var paraArry = paras.split("&");
			//alert(paraArry.length);
			
			 var tab = document.getElementById("para_table")
			 var len = tab.rows.length;   
			 for(var i=1; i<len; i++){ 
				 tab.deleteRow(i);  
				 len=len-1;
		         i=i-1;
			 }  
			for(var i=0 ; i < paraArry.length ; i++){
				var para = paraArry[i];
				var name = para.split("=")[0];
				var value = para.split("=")[1];
				
				addRow2(name,value);
			}
			
			
			if(url != ""){
				$.ajax({
				    url: '/smart-api/api/ajaxParesURLAndGetResult',
				    type: 'GET',
				    data:{url: url},
				    dataType:"json",
				    error: function(){
				        alert("请自行填写此接口返回的结果示例");
				    },
				    success: function(resData){
				    	 var o = eval(resData);//将json字符串转换成js对象
				    	 //alert(resData);
			             for (var i in o) { //循环json对象数组
							//alert(i);
			             	if(i == "result"){
			             		document.getElementById("result").innerHTML="";
			             		//document.getElementById("result").innerHTML=JsonUti.convertToString(o[i]);
			             		var json = format(o[i]);
			             		if(json == 0 || json==1){
			             			alert('请自行填写此接口返回的结果示例.数据源语法错误,格式化失败!');   
			             		}else{
				             		document.getElementById("result").innerHTML=json;
			             		}
			             	}
						 }
				    }
				});
				
			}else{
				alert("请填写URL");
			}
		}
		
		
		
		
	</script>

    <div class="page-inner" id="page-new-project" data-page-name="创建新项目" >
        <h3>创建API</h3>
        <span><font color="red">${message }</font>
		</span>
		<form action="api/addAPI" method="post" onsubmit="return checkForm(this);">
			<table cellpadding="0" border="1" cellpadding="0" cellspacing="0"
				width="100%" bordercolor="gray">
				
				<tr>
					<td align="left" width="80">
						请求URL
					</td>
					<td>
						<input type="text" style="display: inline-block; width: 50%;" id="url" name="url" value="${url }" size="114" placeholder="如:http://www.baidu.com/?a=abc&b=123"/> <a href="javaScript:void(0);" onclick="parseURL();">自动产生参数和结果</a>(需开启此URL相应服务)
					</td>
				</tr>
				
				<tr >
					<td align="left">
						接口名称
					</td>
					<td>
						<input type="text" id="name" name="name" value="${name }" style="display: inline-block; width: 50%;" size="114" maxlength="20"/>
					</td>
				</tr>

				<tr>
					<td align="left">
						接口描述
					</td>
					<td>
						<textarea id="des" name="des" style='height: 50px;width: 80%;'>${des }</textarea>
					</td>
				</tr>
				<tr>
					<td align="left">
						接口所在
					</td>
					
					<td style='text-align: left;'>
						<select name="belongItemId" onchange="toChangeModule(this);">
							<option value=''></option>
							<c:forEach var="obj" items="${allItem }">
								<option value="${obj.id }" ${obj.id == belongItemId ? 'selected' : ''}>${obj.name }</option>
							</c:forEach>
						</select>
						
						<select id="belongModuleId" name="belongModuleId">
							<option disabled>--------------------------------------------------</option>
							<c:forEach var="obj" items="${moduleList }">
								<option value="${obj.id }" ${obj.id == belongModuleId ? 'selected' : ''}>${obj.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td align="left">
						TOKEN
					</td>
					<td style='text-align: left;'>
						<select id="needCookie" name="needCookie">
							<option value="是" ${needCookie eq "是" ? "selected" : "" }>
								是
							</option>
							<option value="否" ${needCookie eq "否" ? "selected" : "" }>
								否
							</option>
						</select>
					</td>
				</tr>

				<tr>
					<td align="left">
						请求参数
					</td>
					<td>
						<table id="para_table" cellpadding="0" border="1" cellpadding="0" cellspacing="0"
							width="80%" bordercolor="gray" style="text-align: center;">
							<tr>
								<th>
									名称
								</th>
								<th>
									类型
								</th>
								<th>
									是否必须
								</th>
								<th>
									描述
								</th>
								<th>
									示例值
								</th>
								<th>
									<a href="javaScript:void(0);" onclick="addRow2('','');">添加参数</a>
								</th>
							</tr>
							<c:if test="${not empty para_name }">
								<%
									String para_name[] = (String[])request.getAttribute("para_name");
									String para_type[] = (String[])request.getAttribute("para_type");
									String para_isNeed[] = (String[])request.getAttribute("para_isNeed");
									String para_des[] = (String[])request.getAttribute("para_des");
									String para_caseVal[] = (String[])request.getAttribute("para_caseVal");
									for(int i =0 ; i< para_name.length ; i++){
										%>
										<tr>
											<td><input style='width: 80px;' name='para_name' value='<%=para_name[i] %>' /></td>
											<td><select  style='width: 120px;' name='para_type'><option value='String' <%= "String".equals(para_type[i]) ? "selected" :""  %>>String</option><option value='JSONArray' <%= "JSONArray".equals(para_type[i]) ? "selected" :""  %>>JSONArray</option><option value='JSONObject' <%= "JSONObject".equals(para_type[i]) ? "selected" :""  %>>JSONObject</option><option value='Number' <%= "Number".equals(para_type[i]) ? "selected" :""  %>>Number</option></select></td>
											<td><select style='width: 40px;' name='para_isNeed'><option value='是' <%= "是".equals(para_isNeed[i]) ? "selected" :""  %>>是</option><option value='否' <%= "否".equals(para_isNeed[i]) ? "selected" :""  %>>否</option></select></td>
											<td><input  style='width: 120px;' name='para_des' value='<%=para_des[i] %>' /></td>
											<td><input  style='width: 120px;' name='para_caseVal' value='<%=para_caseVal[i] %>' /></td>
											<td><a href='javaScript:void(0);' onclick='removeRow(this.parentNode.parentNode);'>删除</a></td>
										</tr>
										<%
									}
								%>
							</c:if>
						</table>
					</td>
				</tr>

				<tr>
					<td align="left">
						返回结果
					</td>
					<td>
						<textarea id="result" name="result" style='height: 100px;width: 80%;'>${result }</textarea>
					</td>
				</tr>

				<tr>
					<td align="left">
						字段描述
					</td>
					<td>
						<select id="itemId" name="itemId" onchange="toChangeItem(this);">
										<option value=''></option>
										<c:forEach var="obj" items="${allItem }">
											<option value="${obj.id }">${obj.name }</option>
										</c:forEach>
						</select>
						&nbsp;&nbsp;
						<select id='db' name='db' onchange="toChangeDB(this);">
										<option disabled>--------------------------------------------------</option>
						</select>
						&nbsp;&nbsp;
						<select id='table' name='table' onchange="toChangeTable(this);">
										<option disabled>--------------------------------------------------</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td align="left">
					</td>
					<td>
						<div id="doc-content" class="doc-content editor-style gallery-wrap " style="width:805px;"><span id='tr_span'></span></div>
					</td>
				</tr>
								
				
				<tr>
					<td align="left">
						修改说明
					</td>
					<td>
						<textarea id="other" name="other" style='height: 80px;width: 80%;'>${other }</textarea>
					</td>
				</tr>
				 
				<tr>
					<td align="left">
						Email通知
					</td>
					<td>
						<script>
							function toChange(obj){
								var noticEmail = document.getElementsByName("noticEmail");
								if(obj.checked){
									for(var i=0 ; i<noticEmail.length ; i++){
										noticEmail[i].checked = true;
									}
								}else{
									for(var i=0 ; i<noticEmail.length ; i++){
										noticEmail[i].checked = false;
									}
								}
							}
							
							function toChangeGroup(obj,group){
								var noticEmail = document.getElementsByClassName(group);
								if(obj.checked){
									for(var i=0 ; i<noticEmail.length ; i++){
										noticEmail[i].checked = true;
									}
								}else{
									for(var i=0 ; i<noticEmail.length ; i++){
										noticEmail[i].checked = false;
									}
								}
							}
						</script>
						
						<table>
							<tr>
								<td colspan="2" style='text-align: left;'>
									<input name="noticEmailAll" type="checkbox" style='vertical-align: middle' onchange="toChange(this);">&nbsp;所有人员&nbsp;&nbsp;&nbsp;&nbsp;<br> 
								</td>
							</tr>
							<c:forEach var="obj" items="${allUser}" varStatus="v">
								<tr >
									<td style='text-align: left;'>
										<input name="noticEmailAll" type="checkbox"  style='vertical-align: middle' onchange="toChangeGroup(this,'${obj.key}');">&nbsp;${obj.key}组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td style='text-align: left;'>
										<c:forEach var="one" items="${obj.value}" varStatus="vv">
											<input class="${obj.key}" name="noticEmail" style='vertical-align: middle' type="checkbox" value = "${one.email }"><span>&nbsp;${one.name }</span> &nbsp;&nbsp;
										</c:forEach>
									</td>
								</tr>
							</c:forEach>
						</table>
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
