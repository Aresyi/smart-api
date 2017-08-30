<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/inc-common.jsp"%>

<body>
<div id="page-loading-mask"></div>

<div class="wrapper">




	<%@ include file="/inc-menu-head.jsp"%>



	<div class="container workspace">
		<div class="page">


			<div class="page-inner" data-since="2016-07-06 09:05:53 UTC"
				 data-creator-guid="${one.id }"
				 data-project-creator="d585890abe7a411ea75c9135f52598ef"
				 data-page-name="${one.belongItem }->${one.belongModule }->${one.name }" id="page-api-test">

				<h3>API平台接口调试工具</h3>

				<pre>此工具旨在帮助开发者检测调用API时发送的请求参数是否正确，提交相关信息后可获得服务器的验证结果<br>（1）选择合适的接口。<br>（2）系统会生成该接口的参数表，您可以直接在文本框内填入对应的参数值。（红色星号表示该字段必填）<br>（3）点击检查问题按钮，即可得到相应的调试信息。</pre>

				<br><br>


				<B>一：接口说明</B>
				<pre>[${one.belongItem }]->[${one.belongModule }]<br>${one.name }<br>${one.des }</pre>
				<br>
				<B>二：服务地址</B><br>
				<table cellpadding="1" cellspacing="0" class="Requestparameters" style="width: 100%">
					<tr>
						<td width="15%">
							<font color='red'>*</font>
							服务地址
						</td>
						<td >
							<script type="text/javascript">
                                function toChange(obj){
                                    var val = obj.value;
                                    document.getElementById("host").value = val;
                                }
							</script>
							<select  id="selectHost" onchange="toChange(this);" >
								<option value=''>请选择服务器地址</option>
								<c:forEach var="obj" items="${confInf.testSerList }" varStatus="status">
									<option value="${obj.hostUrl }" <c:if test="${status.index == 0}">selected</c:if> >${obj.hostName }</option>
								</c:forEach>
							</select>
							<br/><br/>
							<input type='text' style="width: 80%;" id='host' name='host' value='' size="45"><font color='red'>请确保该地址服务已正常开启</font>
							<script>
                                document.getElementById("host").value = document.getElementById("selectHost").value;
							</script>
						</td>
					</tr>
				</table>
				<br>

				<script type="text/javascript">
                    function myCheck(obj){
                        var host = document.getElementById("host").value;
                        if(host == null || host == "" || host.indexOf("http://") == -1){
                            alert("请填写正确的服务器地址");
                            return false;
                        }

                        var uri = document.getElementById("uri").value;
                        //alert(uri);

                        obj.setAttribute("action", host+uri)

                        //alert(obj.attributes['action'].value);
                        return true;
                    }
				</script>

				<B>三：参数列表</B><br>
				<input type='hidden' id = 'uri' name='uri' value = '${one.url }'>
				<form id ='myTestForm' name = 'myTestForm' method='get' action='' onsubmit="return myCheck(this);" target="_blank">
					<table cellpadding="1" cellspacing="0" class="Requestparameters" style="width: 100%">
						<c:if test="${one.needCookie == '是'}">
							<tr>
								<td width="15%">
									<font color='red'>*</font>
									TOKEN
								</td>
								<td >
									<input type='text' style="width: 80%;" name="${confInf.tokenName}" value="${confInf.tokenDefValue}" size="70">
									<font color='gray'>请求TOKEN</font>(<font color='red'>上面为默认值，请自行按需修改此值</font>)
								</td>
							</tr>
						</c:if>
						<c:forEach var = "para" items="${one.parameter }">
							<tr>
								<td width="15%">
										${para.para_isNeed == "是" ? "<font color='red'>*</font>" :"&nbsp;" }
										${para.para_name }
								</td>
								<td >
									<input type='text' name="${para.para_name == 'method' ? 'method' : para.para_name}" value='${para.para_caseVal }' style="width: 20%;" size="70">
									<font color='gray'>${para.para_des }</font>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="2" align="center">
								<input id="submitBtn" type='submit' class="btn" name='submit' value='检查问题'/>
								<c:if test="${not empty one.result }">
									<input type='button' class="btn" name='submit' value='Mock Test' onclick="mock('${one.id}');"/>
									<script type="text/javascript">
                                        function mock(id){
                                            window.open("/smart-api/api/"+id+"/mockTest/");
                                        }
									</script>
								</c:if>
							</td>
						</tr>
					</table>
				</form>

			</div>
		</div>

		<%@ include file="/inc-content-foot.jsp"%>

	</div>
</div>

<%@ include file="/inc-foot.jsp"%>

</body>
</html>