<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp"%>


<script type="text/javascript" src="/smart-api/assets/dataDesc.js"></script>

<body>
	<div id="page-loading-mask"></div>

	<div class="wrapper">




		<%@ include file="/inc-menu-head.jsp"%>



		<div class="container workspace">
			<div class="page">
				
				
				<div class="page-inner"  data-page-name="数据" id="page-projects" nav="datadesc">
					
					<div class="tools">
		                    <div class="switch-view">
		                        <a href="javascript:;" class="link-view link-grid-view active" title="网格视图">
		                            <i class="twr twr-grid-view"></i>
		                        </a>
		                        <a href="javascript:;" class="link-view link-list-view" title="列表视图">
		                            <i class="twr twr-list-view"></i>
		                        </a>
		                    </div>
							<c:if test="${cookie.roleId.value eq 8686 || cookie.roleId.value eq 1}">
			                    <a class="link-create-project new "
			                        href="/smart-api/sys/settings" data-stack-root data-nocache>
			                        	配置数据源
			                    </a>
		                    </c:if>
		                </div>
					
					
					<div class="doc-wrap gallery-wrap">
					
						<div id="doc-content"
								class="doc-content editor-style gallery-wrap ">
								
				
								<div style='text-align: center; margin-left: 50px;margin-right:50px; font-size: 12px;'>
									<B>数据字典:</B>
									<select id="itemId" name="itemId" onchange="toChangeItem(this);">
										<option value=''></option>
										<c:forEach var="obj" items="${allItem }">
											<option value="${obj.id }" ${obj.id == belongItemId ? 'selected' : ''}>${obj.name }</option>
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
									<span id='tr_span'></span>
								</div>
						
							
				</div>
		</div>
		</div>
		</div>
		
			<%@ include file="/inc-content-foot.jsp"%>

		</div>
	</div>
	
	<%@ include file="/inc-foot.jsp"%>

</body>
</html>