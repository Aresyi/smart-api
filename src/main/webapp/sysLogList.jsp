<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@page import="com.ydj.smart.common.tools.LocalCache"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>

<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        



 	<%@ include file="/inc-menu-head.jsp" %>



		<div class="container workspace" >
		    <div class="page">
		
		        <div class="page-inner" id="page-log" data-page-name="动态" nav="events">
		                
						<table border="1" cellpadding="0" cellspacing="0" width="100%"
							bordercolor="gray">
							<tr align="center" height="30px" bgcolor="#F8F8FF">
								<th width="5%">
									编号
								</th>
								<th width="10%">
									操作者
								</th>
								<th >
									描述
								</th>
								<th width="20%">
									操作时间
								</th>
							</tr>
							
							<c:forEach var="obj" items="${pagerecords.records}" varStatus="v">
								<tr id="${v.count }" align="center" style="top: 3; bottom: 3;background-color:${v.count%2==0?'#FFFFFF':'#F8F8F8'}" onmouseover="this.style.backgroundColor='#00FF00';" onmouseout="if(this.id % 2==0){this.style.backgroundColor='#EFEFEF';}else{this.style.backgroundColor='#F8F8F8';}">
									<td>
										${v.count }
									</td>
									<td>
										${obj.opreation }
									</td>
									<td>
										${obj.desc } &nbsp;&nbsp;
											<c:if test="${fn:startsWith(obj.href, 'http')}">
												<a href="${obj.href }" target="_black" >
													<img width="12px" height="12px" src='/smart-api/assets/href.jpg'/>
												</a>
											</c:if>
									</td>
									<td>
										${tranb:longToDateString(obj.createTime) }
									</td>
								</tr>
							</c:forEach>
						</table>
						<c:url var="paginationUrlPattern" value="/event/sysLog/">
							<c:param name="page" value="_page_" />
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