<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>


<script type="text/javascript">
		
		function del(obj,id){
			if(window.confirm("确认删除此足迹？")){
				$.ajax({
				    url: '/smart-api/track/delTrack',
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
		
		</script>


<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        



 	<%@ include file="/inc-menu-head.jsp" %>



		<div class="container workspace">
		    <div class="page">
		
		        <div class="page-inner" id="page-projects" data-page-name="${empty itemName ? '所有' : itemName }的足迹" nav="track">
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
		                        href="/smart-api/track/create/" data-stack-root data-nocache>
		                        	新建足迹
		                    </a>
		                </div>
						
						
						<div style=" padding: 10px 10px; ">
						<form action="track/searchTrack" name="form" id="form" method="post">
						<a>搜索足迹</a>
							<select name="belongItemId" >
								<option disabled="disabled">-------------------</option>
								<option value=""></option>
								<c:forEach var="obj" items="${allItem }">
									<option value="${obj.id }" ${obj.id == belongItemId ? 'selected' : ''}>${obj.name }</option>
								</c:forEach>	
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
						<th width="10%">
							所在项目
						</th>
						<th width="5%">
							迭代版本
						</th>
						<th width="20%">
							需求简述
						</th>
						<th width="20%">
							版本目标 
						</th>
						<th width="5%">
							发版日期
						</th>
						<th width="5%">
							检测日期 
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
								${obj.version }&nbsp;
							</td>
							<td>
								${obj.reqDes }&nbsp;
							</td>
							<td>
								${obj.target }&nbsp;
							</td>
							<td>
								${obj.releaseDate }&nbsp;
							</td>
							<td>
								${obj.checkDate}
							</td>
							<td>
								${obj.creater }&nbsp;
							</td>
							
							<td>
								<c:if test="${empty obj.isDel && (cookie.isEdite.value eq 1 || obj.createUserId eq cookie.id.value)}">
									<a href="/smart-api/track/${obj.id }/toEditeTrack/">修改</a>
									<a href="javaScript:void(0);" onclick="del(this,'${obj.id}');">废弃</a>
								</c:if>
								<a href="/smart-api/track/${obj.id }/trackDetail/" target="_blank">查看</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:url var="paginationUrlPattern" value="/track/searchTrack?">
				<c:param name="page" value="_page_" />
				<c:param name="belongItemId" value="${belongItemId }" />
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