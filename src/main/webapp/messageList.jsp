<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@page import="com.ydj.smart.common.tools.LocalCache"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp"%>


<body>
	<div id="page-loading-mask"></div>

	<div class="wrapper">




		<%@ include file="/inc-menu-head.jsp"%>



		<div class="container workspace">
			<!-- 
			<div class="page "
				data-url="/projects/dbe017efd44044619c565f3e38a48ba5">
				<a href="/projects/dbe017efd44044619c565f3e38a48ba5"
					class="link-page-behind" data-stack>人脉通</a>
			</div>
			 -->
			<div class="page">



				<div class="page-inner"
					data-project-creator="d585890abe7a411ea75c9135f52598ef"
					data-page-name="所有的文档" id="page-docs"  nav="doc">

					<h3>
						<span class="title">所有的文档</span>
						<div class="btn-group">
							<a href="/smart-api/message/newMsg?markdown=1"
								class="btn btn-mini btn-new-doc" target="_blank">创建文档</a>
							<button class="btn btn-mini btn-dropdown-toggle">
								<i class="twr twr-caret-down"></i>
							</button>
							<ul class="btn-dropdown-menu">
								<li><a
									href="/smart-api/message/newMsg"
									class="btn-create-normal-doc" target="_blank">普通文档</a></li>
								<li><a
									href="/smart-api/message/newMsg?markdown=1"
									class="btn-create-md-doc" target="_blank">Markdown 文档</a></li>
								<li class="separator"></li>
								<li class="btn-create-doc-dir"
									data-guid="dbe017efd44044619c565f3e38a48ba5"><a
									href="javascript:;">创建文档夹</a></li>
							</ul>
						</div>
					</h3>

					<div class="docs-view grid-view ">
						<div class="switch-view">
							<a href="javascript:;" class="link-view link-grid-view active">
								<i class="twr twr-grid-view"></i>
							</a> <a href="javascript:;" class="link-view link-list-view"> <i
								class="twr twr-list-view"></i>
							</a>
						</div>
<!-- 
						<div class="doc-selected-info">
							<span class="doc-selected-count">选择了<em>0</em>项
							</span> <a href="javascript:;" class="link-move-doc-dir"
								data-project-guid="dbe017efd44044619c565f3e38a48ba5"
								data-move-url="/projects/dbe017efd44044619c565f3e38a48ba5/doc_dirs/move_in">移动</a>
							<a href="javascript:;" class="link-cancel-select"><i
								class="twr twr-times"></i></a>
						</div>
 -->
						<div class="doc-headers">
							<div class="doc-header name-header">
								<span>名称</span>
							</div>
							<div class="doc-header saver-header">
								<span>修改者</span>
							</div>
							<div class="doc-header update-time-header">
								<span>最后修改时间</span>
							</div>
						</div>
						<div class="doc-list">
							<c:forEach var="obj" items="${messageList}" varStatus="v">
								<div class="doc-item doc-or-dir"
									data-guid="7228060285454620aa842818365b9258"
									data-creator-guid="cf99cd6d64594a03bf758436dd69a217">
									<a href="/smart-api/message/${obj.id}/messageDetail"
										class="document" target="_black">
										<div class="doc-name">
											<span class="link"> <span class="document-rest">${obj.title }</span>
											</span>
										</div>
										<div class="doc-desc editor-style" style='overflow: hidden;text-overflow: ellipsis;white-space: nowrap;'>
											${obj.detail }
									    </div>
										<div class="truncated"></div>

										<div class="doc-saver doc-info">
											<span>${obj.creater }</span>
										</div>

										<div class="doc-update-time doc-info">
											<span
												data-readable-time="${tranb:longToDateString(obj.modifyTime) }"
												title="${obj.creater } 在 ${tranb:longToDateString(obj.modifyTime) } 保存"></span>
										</div>
									</a>

									<div class="doc-links">
										<a href="javascript:;" class="link-doc-dir-move"
											data-project-guid="dbe017efd44044619c565f3e38a48ba5"
											data-name="${obj.title }" data-visible-to="admin,creator"
											data-move-url="/projects/dbe017efd44044619c565f3e38a48ba5/docs/7228060285454620aa842818365b9258/move_to_dir">移动</a>
										<c:if test="${cookie.isEdite.value eq 1 || obj.createUserId eq cookie.id.value}">	
											<a href="/smart-api/message/${obj.id }/delMsg/"
												class="doc-delete" data-method="delete"  data-confirm="确定要删除这篇文档吗？" data-visible-to="admin,creator">删除</a>
										</c:if>	
									</div>
								</div>
							</c:forEach>






						</div>
					</div>



					<script type="text/html" id="tpl-create-doc-dir">
        <div class="doc-dir-item doc-or-dir new">
    <div class="doc-dir-name">
        <input type="text" class="txt-doc-dir-name no-border" value="新的文档夹" placeholder="" />
    </div>

    <div class="doc-dir-links">
        <a href="javascript:;" class="link-submit-doc-dir">创建</a>
        <a href="javascript:;" class="link-cancel-doc-dir">取消</a>
    </div>
</div>
    </script>
					<script type="text/html" id="tpl-doc-dir-dialog">
        <div class="change-doc-dir-dialog">
    <form class="form form-change" action="" method="post" data-remote="true">
        <h3>移动 <span class="name"></span> 到</h3>
        <div class="doc-dirs"></div>
        <input type="hidden" class="target-doc-dir-guid" name="target_doc_dir_guid">
        <div class="simple-dialog-buttons">
            <button type="submit" class="btn" disabled data-disable-with="正在移动...">移动</button>
            <button type="button" class="btn btn-x btn-cancel-move">取消</button>
        </div>
    </form>
</div>
    </script>

				</div>

			</div>
		</div>


		<%@ include file="/inc-content-foot.jsp"%>



	</div>

	<%@ include file="/inc-foot.jsp"%>

</body>
</html>