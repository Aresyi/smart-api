<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>


<script type="text/javascript">

		$(function () {
			setTimeout(function(){
                $(".container").addClass("simple-stack-fluid");
			},100);
        });

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
                        $("#belongModuleId").val('${belongModuleId}');
				    }
				});

                //获取配置信息
				$(".tempOption").remove();
                $.ajax({
                    url: '/smart-api/data/ajaxGetItemConf',
                    type: 'GET',
                    data:{itemId: item},
                    dataType:"json",
                    error: function(){
                        alert('Error loading json data document');
                    },
                    success: function(resData){

                        var o = eval(resData);//将json字符串转换成js对象
                        if(!o.versionList && o.versionList.length == 0){
                            $("#isDel").append("<option class='tempOption' value='-1' >废弃</option>");
                            return;
                        }
                        for (var i = 0;i< o.versionList.length;i++) { //循环json对象数组
//                            alert(o.versionList[i].version);
                            var version = o.versionList[i].version;
//                            sel.options.add(new Option(version,version));
                            $("#isDel").append("<option class='tempOption' value='"+version+"'>"+version+"</option>");
                        }
                        $("#isDel").append("<option class='tempOption' value='-1' >废弃</option>");
                        $("#isDel").val(${isDel});

                    }
                });
				
			}else{
				 var sel = document.getElementById("belongModuleId");
		         sel.options.length=0;
			}
		}
		
		function del(obj,id){
			if(window.confirm("确认废弃此接口？")){
				$.ajax({
				    url: '/smart-api/api/delApi',
				    type: 'GET',
				    data:{id: id},
				    dataType:"json",
				    error: function(){
				        alert('Error loading json data document');
				    },
				    success: function(resData){
			            removeRow(obj.parentNode.parentNode);
				    }
				});
			}
		}
		
		function removeRow(r) {
				var root = r.parentNode;
			    var allRows = root.getElementsByTagName('tr')
			    if(allRows.length>1){
			        root.removeChild(r);
			    }
		}

		$(function () {
            $('#belongItemId').trigger('change');
        });
		
		</script>


<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        



 	<%@ include file="/inc-menu-head.jsp" %>



		<div class="container workspace" data-stack-fluid>
		    <div class="page">
		
		        <div class="page-inner" id="page-projects" data-page-name="${empty itemName ? '所有' : itemName }的API">
		                <div class="tools">
		                    <div class="switch-view">
		                        <a href="javascript:;" class="link-view link-grid-view active" title="网格视图">
		                            <i class="twr twr-grid-view"></i>
		                        </a>
		                        <a href="javascript:;" class="link-view link-list-view" title="列表视图">
		                            <i class="twr twr-list-view"></i>
		                        </a>
		                    </div>
		
		                    <a class="link-create-project new "
		                        href="/smart-api/api/createAPI" data-stack-root data-nocache>
		                        	新建API
		                    </a>
		                </div>
						
						
						<div style=" padding: 10px 10px; ">
						<form action="api/searchApi" name="form" id="form" method="post">
						<a>搜索API</a>
							<span style="color:rgba(85, 85, 85, 0.5)">项目:</span>
							<select id="belongItemId" name="belongItemId" onchange="toChangeModule(this);">
								<option disabled="disabled">-------------------</option>
								<option value=""></option>
								<c:forEach var="obj" items="${allItem }">
									<option value="${obj.id }" ${obj.id == belongItemId ? 'selected' : ''}>${obj.name }</option>
								</c:forEach>
							</select>
							&nbsp;&nbsp;
							<span style="color:rgba(85, 85, 85, 0.5)">模块:</span>
							<select id="belongModuleId" name="belongModuleId">
								<option disabled="disabled">-------------------</option>
								<option value=""></option>
								<c:forEach var="obj" items="${moduleList }">
									<option value="${obj.id }" ${obj.id == belongModuleId ? 'selected' : ''}>${obj.name }</option>
								</c:forEach>
							</select>
							&nbsp;&nbsp;

							<span style="color:rgba(85, 85, 85, 0.5)">版本:</span>
							<!-- (>) 大于 - $gt
							(<) 小于 - $lt
							(>=) 大于等于 - $gte
							(<= ) 小于等于 - $lte -->
							<select style="width:80px;" id="versionOperater" name="versionOperater">
								<option disabled="disabled">-------------------</option>
								<option value='' ></option>
								<option value='$lte' ${versionOperater=='$lte' ? 'selected' : '' }> 小于等于 </option>
								<option value='$lt' ${versionOperater=='$lt' ? 'selected' : '' }> 小于 </option>
								<option value='$eq' ${versionOperater=='$eq' ? 'selected' : '' }> 等于 </option>
								<option value='$gt' ${versionOperater=='$gt' ? 'selected' : '' }> 大于 </option>
								<option value='$gte' ${versionOperater=='$gte' ? 'selected' : '' }> 大于等于 </option>

							</select>
							<select style="width:80px;" id="isDel" name="isDel">
										<option disabled="disabled">-------------------</option>
										<option value='' ></option>
										<%--<option value='1' ${isDel==1 ? 'selected' : '' }>废弃</option>--%>
							</select>
							&nbsp;&nbsp;
							<input type="text" id="keyword" name="keyword" value="${ keyword}" style='display: inline-block;   padding: 0;  height: 28px;'/>
							&nbsp;&nbsp;

							<input type="submit" value="查&nbsp&nbsp询" /> <font color="red">${message }</font>
						</form>
					</div>
				<table border="1" cellpadding="0" cellspacing="0" width="100%"
					bordercolor="gray">
					<tr align="center" height="30px" bgcolor="#F8F8FF">
						<th width="5%">
							编号
						</th>
						<th width="8%">
							所在项目
						</th>
						<th width="8%">
							所在模块
						</th>
						<th width="15%">
							接口名称
						</th>
						<th width="5%">
							历史版本
						</th>
						<th width="25%">
							URL
						</th>
						<th width="5%">
							TOKEN
						</th>
						<th width="10%">
							更新时间
						</th>
						<th width="5%">
							录入人
						</th>
						<th width="15%">
							操作
						</th>
					</tr>
					
					<c:forEach var="obj" items="${pagerecords.records}" varStatus="v">
						<tr id="${v.count }" align="center" style="top: 3; bottom: 3;background-color:${v.count%2==0?'#FFFFFF':'#F8F8F8'}" onmouseover="this.style.backgroundColor='#00FF00';" onmouseout="if(this.id % 2==0){this.style.backgroundColor='#EFEFEF';}else{this.style.backgroundColor='#F8F8F8';}">
							<td>
								${v.count }&nbsp;
							</td>
							<td>
								${obj.belongItem }&nbsp;
							</td>
							<td>
								${obj.belongModule }&nbsp;
							</td>
							<td title="${obj.des }">
								${obj.name }&nbsp;
							</td>
							<td>
								<c:if test="${not empty obj.isHasHistory }">
									<c:if test="${obj.isHasHistory  eq 1}">
										<a href="/smart-api/api/${obj.id }/apiHistory/">有</a>
									</c:if>
									<c:if test="${obj.isHasHistory  eq 0}">
										--
									</c:if>
								</c:if>
								<c:if test="${ empty obj.isHasHistory }">
								--
								</c:if>
							</td>
							<td style="text-align: left;">
								${obj.url }<c:forEach var = "para" items="${obj.parameter }"><c:if test="${para.para_name == 'method' }">?method=${para.para_caseVal}</c:if></c:forEach>&nbsp;
							</td>
							<td>
								${obj.needCookie }&nbsp;
							</td>
							<td>
								${tranb:longToDateString(obj.modifyTime) }
							</td>
							<td>
								${obj.creater }&nbsp;
							</td>
							
							<td>
								<c:if test="${empty obj.isDel && (cookie.roleId.value eq 8686 || cookie.isEdite.value eq 1 || obj.createUserId eq cookie.id.value)}">
									<a href="/smart-api/api/${obj.id }/toEditeAPI/">修改</a>
									<a href="javaScript:void(0);" onclick="del(this,'${obj.id}');">废弃</a>
								</c:if>
								<a href="/smart-api/api/${obj.id }/apiDetail/" target="_blank">查看</a>
								<a href='/smart-api/api/${obj.id }/apiTest/' target='_blank'>调试</a>
								<a href='/smart-api/api/${obj.id }/mockTest/' target='_blank'>MOCK</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:url var="paginationUrlPattern" value="/api/searchApi?">
				<c:param name="page" value="_page_" />
				<c:param name="belongItemId" value="${belongItemId }" />
				<c:param name="belongModuleId" value="${belongModuleId }" />
				<c:param name="isDel" value="${isDel }" />
				<c:param name="keyword" value="${keyword }" />
				</c:url>
				<tranb:pagination pagination="${pagerecords}" urlPattern="${paginationUrlPattern}" />
		
		
		
		        </div>
		
		    </div>
		</div>
	
	
		<%@ include file="/inc-content-foot.jsp" %>



    </div>

    <%@ include file="/inc-foot.jsp" %>

</body>
</html>