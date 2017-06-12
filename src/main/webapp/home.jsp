<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>

<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        


        
	   <%@ include file="/inc-menu-head.jsp" %>


    






		<div class="container workspace">
		    <div class="page">
		
		        <div class="page-inner" id="page-projects" data-page-name="首页" nav="home">
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
		                        href="/smart-api/item/createItem" data-stack-root data-nocache>
		                        	新建项目
		                    </a>
		                </div>
						
						<!--  
						<div class="projects grid-view">
						1.我最近浏览API（浏览历史）
						<hr>
						a.................
						<br>
						b.................
						<br>
						c.................
						<br>
						
						</div>
						
						<div class="projects grid-view">
						2.最新API（与我相关）
						<hr>
						a.................
						<br>
						b.................
						<br>
						c.................
						<br>
						</div>
		
						<div class="projects grid-view">
						3.权限范围内可见的项目列表
						<hr>
						</div>
						-->
		                <div class="projects grid-view">
		                
							
							<c:forEach var="obj" items="${allItem }">
									 <a class="project c${tranb:getRandomNumber(1,9) } i${tranb:getRandomNumber(1,26) }"
					                        href="/smart-api/api/searchApi?belongItemId=${obj.id }"  data-access-id="${obj.id }" data-stack-root >
					
					                        <span class="badge">${obj.name }</span>
					                        <span class="edit-badge" title="点击修改项目图标和颜色"></span>
					                        <span class="name">${obj.belongItem }</span>
					                        <span class="progress list-item">
					                                                                       待处理任务 <em>${tranb:getRandomNumber(1,26) }</em>
					                        </span>
					                    </a>
							</c:forEach>
							
		                </div>
		                <!-- 
		                <div class="projects grid-view" style="text-align:center;">
							<img alt="" src="/smart-api/assets/132226ovzio7v60yy0ir46.gif">
						</div>
		 -->
		            <div class="projects-footer">
		               <!--  <a href="/teams/2c17c1fc24584c0497dd0a6ac8a3ed18/project_templates" data-stack data-visible-to="member">管理项目模板</a>  -->
		
		            </div>
		
		            <script type="text/html" id="tpl-badge">
                <div class="project-badge badge-settings">
                    <ul class="color-sets">
                        <li class="c1"></li>
                        <li class="c2"></li>
                        <li class="c3"></li>
                        <li class="c4"></li>
                        <li class="c5"></li>
                        <li class="c6"></li>
                        <li class="c7"></li>
                        <li class="c8"></li>
                    </ul>
                    <ul class="icons">
                            <li class="i1">A</li>
                            <li class="i2">B</li>
                            <li class="i3">C</li>
                            <li class="i4">D</li>
                            <li class="i5">E</li>
                            <li class="i6">F</li>
                            <li class="i7">G</li>
                            <li class="i8">H</li>
                            <li class="i9">I</li>
                            <li class="i10">J</li>
                            <li class="i11">K</li>
                            <li class="i12">L</li>
                            <li class="i13">M</li>
                            <li class="i14">N</li>
                            <li class="i15">O</li>
                            <li class="i16">P</li>
                            <li class="i17">Q</li>
                            <li class="i18">R</li>
                            <li class="i19">S</li>
                            <li class="i20">T</li>
                            <li class="i21">U</li>
                            <li class="i22">V</li>
                            <li class="i23">W</li>
                            <li class="i24">X</li>
                            <li class="i25">Y</li>
                    </ul>
                </div>
            </script>
		
		            <input type="hidden" id="select-plan-path" value="/teams/2c17c1fc24584c0497dd0a6ac8a3ed18/plans" />
		
		
		
		        </div>
		
		    </div>
		</div>
		
		<%@ include file="/inc-content-foot.jsp" %>

    </div>

   <%@ include file="/inc-foot.jsp" %>

</body>
</html>