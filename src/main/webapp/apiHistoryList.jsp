<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@page import="com.ydj.smart.common.tools.LocalCache"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>

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
						
						<a>API历史列表[<font color='red'><a href="/smart-api/api/${id }/apiDetail/" target="_black">查看此接口当前最新</a></font>]</a>

		<table border="1" cellpadding="0" cellspacing="0" width="100%"
			bordercolor="gray">
			<tr align="center" height="30px" bgcolor="#F8F8FF">
				<th width="5%">
					编号
				</th>
				<th width="10%">
					所在项目
				</th>
				<th width="10%">
					所在模块
				</th>
				<th width="10%">
					接口名称
				</th>
				<th width="10%">
					URL
				</th>
				<th width="10%">
					TOKEN
				</th>
				<th width="10%">
					录入时间
				</th>
				<th width="5%">
					录入人
				</th>
				<th width="10%">
					修改时间
				</th>
				<th width="10%">
					修改人
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
			
			<c:forEach var="obj" items="${pagerecords.records}" varStatus="v">
				<tr id="${v.count }" align="center" style="top: 3; bottom: 3;background-color:${v.count%2==0?'#FFFFFF':'#F8F8F8'}" onmouseover="this.style.backgroundColor='#00FF00';" onmouseout="if(this.id % 2==0){this.style.backgroundColor='#EFEFEF';}else{this.style.backgroundColor='#F8F8F8';}">
					<td>
						${v.count }
					</td>
					<td>
						${obj.belongItem }
					</td>
					<td>
						${obj.belongModule }
					</td>
					<td>
						${obj.name }
					</td>
					<td>
						${obj.url }
					</td>
					<td>
						${obj.needCookie }
					</td>
					<td>
						${tranb:longToDateString(obj.createTime) }
					</td>
					<td>
						${obj.creater }
					</td>
					<td>
						${tranb:longToDateString(obj.modifyTime) }
					</td>
					<td>
						${obj.modifyer }
					</td>
					<td>
						<a href="/smart-api/api/${obj.id }/apiDetail/?history=1" target="_black">历史详情</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:url var="paginationUrlPattern" value="/admin.do?method=searchApi">
			<c:param name="page" value="_page_" />
			<c:param name="id" value="${id}" />
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