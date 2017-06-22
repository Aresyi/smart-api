<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<script type="text/javascript" src="https://ga.tower.im/piwik.js"></script>

<script src="/smart-api/htdocs/mdeditor/js/jquery.min.js"></script>
 <script src="/smart-api/htdocs/mdeditor/lib/marked.min.js"></script>
 <script src="/smart-api/htdocs/mdeditor/lib/prettify.min.js"></script>
 <script src="/smart-api/htdocs/mdeditor/lib/raphael.min.js"></script>
 <script src="/smart-api/htdocs/mdeditor/lib/underscore.min.js"></script>
 <script src="/smart-api/htdocs/mdeditor/lib/sequence-diagram.min.js"></script>
 <script src="/smart-api/htdocs/mdeditor/lib/flowchart.min.js"></script>
 <script src="/smart-api/htdocs/mdeditor/lib/jquery.flowchart.min.js"></script>
 <script src="/smart-api/htdocs/mdeditor/js/editormd.min.js"></script>




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



				<div class="page-inner" data-since="2016-07-06 09:05:53 UTC"
					data-creator-guid="cf99cd6d64594a03bf758436dd69a217"
					data-project-creator="d585890abe7a411ea75c9135f52598ef"
					data-page-name="${not empty message.title ? message.title : 'Smart-API'}" id="page-doc" nav="doc">



					<div class="doc-wrap gallery-wrap">
						<div class="project-info">
							<!-- 
							<span> 所属板块： <a
								href="/projects/dbe017efd44044619c565f3e38a48ba5" data-stack
								data-stack-replace data-stack-root>人脉通</a>
							</span>
							 -->
						</div>
						<div class="doc printable"
							data-created-at="${tranb:longToDateString(message.createTime) }"
							data-updated-at="${tranb:longToDateString(message.modifyTime) }">
							<h3 class="doc-title">
								<span class="document-rest">${message.title }</span>
							</h3>
							<div class="doc-info">
								<p>
									<span class="doc-creator">${message.creater }</span> <span
										class="doc-update-time"
										data-readable-time="${tranb:longToDateString(message.createTime) }"></span>保存
								</p>

								<!-- 
								<p class="doc-control">
									<a
										href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985/revisions"
										class="link-doc-revisions" data-stack>查看编辑历史</a> <span
										class="doc-diff"> 或 <a href="javascript:;"
										class="link-doc-diff">对比历史记录</a>
									</span>
								</p>
								 -->
							</div>
						
							<div id="doc-content" class="doc-content editor-style gallery-wrap ${message.markdown eq 1 ? 'doc-markdown' : ''}">
								<c:if test="${message.markdown eq 1 }">
									<textarea style="display:none;">${message.detail }</textarea>
								</c:if>
								<c:if test="${message.markdown == 0 || empty message.markdown }">
									${message.detail }
								</c:if>	
							</div>

					</div>

					<div class="comments streams">
						<div class="event event-common event-document-add"
							id="event-46586231"
							data-ancestor-guid="dbe017efd44044619c565f3e38a48ba5"
							data-ancestor-name="人脉通"
							data-ancestor-url="/projects/dbe017efd44044619c565f3e38a48ba5">

							<a href="/members/cf99cd6d64594a03bf758436dd69a217" class="from"
								target="_blank"><img alt="${message.creater }" class="avatar"
								src="https://avatar.tower.im/28a1a5070c494a48adf19921db540e82" /></a>
							<i class="icon-event"></i>

							<div class="event-main">
								<div class="event-head">
									<a href="#event-46586231"
										data-created-at="${tranb:longToDateString(message.createTime) }"
										class="event-created-at"> ${tranb:longToDateString(message.createTime) } </a> <span
										class="event-actor"> <a
										href="/members/cf99cd6d64594a03bf758436dd69a217"
										class="link-member" target="_blank">${message.creater }</a>
									</span> <span class="event-action"> 创建了文档 </span> <span
										class="event-text"> <span class="emphasize"> <a
											href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985"
											class="document-rest" data-stack="true">${message.title }</a>
									</span>
									</span>
								</div>
							</div>
						</div>

						<div class="event event-common event-document-edit"
							id="event-46610457"
							data-ancestor-guid="dbe017efd44044619c565f3e38a48ba5"
							data-ancestor-name="人脉通"
							data-ancestor-url="/projects/dbe017efd44044619c565f3e38a48ba5">

							<a href="/members/cf99cd6d64594a03bf758436dd69a217" class="from"
								target="_blank"><img alt="易德军" class="avatar"
								src="https://avatar.tower.im/28a1a5070c494a48adf19921db540e82" /></a>
							<i class="icon-event"></i>

							<div class="event-main">
								<div class="event-head">
									<a href="#event-46610457"
										data-created-at="${tranb:longToDateString(message.modifyTime) }"
										class="event-created-at"> ${tranb:longToDateString(message.modifyTime) } </a> <span
										class="event-actor"> <a
										href="/members/cf99cd6d64594a03bf758436dd69a217"
										class="link-member" target="_blank">${message.creater }</a>
									</span> <span class="event-action"> 编辑了文档 </span> <span
										class="event-text"> <span class="emphasize"> <a
											href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985"
											class="document-rest" data-stack="true">${message.title }</a>
									</span>
									</span>
								</div>
							</div>
						</div>




						<c:forEach var="obj" items="${commentList }">
						
							
							<div class="comment" id="${obj.id }" data-creator-guid="${obj.user.groupId }">
								<a class="avatar-wrap" href="/members/${obj.userId }/" target="_blank"> 
									<img class="avatar" src="/smart-api/assets/default_avatars/${obj.user.avatar}" width="50" height="50" />
								</a>
	
								<div class="comment-actions ">
									<div class="actions">
										<a href="javascript:;" class="reply"> 
											<i class="twr twr-reply"></i>
										</a> 
										<a href="/comments/${obj.id }/like" class="like" data-remote="true" data-loading="true" data-method="post"> 
											<i class="twr twr-thumbs-o-up"></i> 
											<span class="count"></span>
										</a> 
										<a href="javascript:;" class="more" data-visible-to="creator,admin"> 
											<i class="twr twr-bars"></i>
										</a>
									</div>
								</div>
	
								<div class="comment-main">
									<div class="info">
										<a class="author" href="/members/${obj.userId }/" data-stack data-stack-root>${obj.creater }</a> 
										<a class="create-time" href="#${obj.id }" title="${tranb:longToDateString(obj.createTime) }" data-readable-time="${tranb:longToDateString(obj.createTime) }"></a>
									</div>
	
									<div class="comment-content editor-style">
										${obj.content }
									</div>
	
								</div>
	
								<div class="tpl-comment-actions-menu">
									<a href="/comments/${obj.id }/edit" class="edit" data-visible-to="creator" data-remote="true" data-loading="true" data-method="get"> 编辑 </a> 
									<a href="/comments/${obj.id }/destroy" class="del" data-visible-to="creator,admin" data-remote="true" data-method="post" data-confirm="确定要删除这条回复吗？"> 删除 </a>
								</div>
							</div>
						
						</c:forEach>



					</div>

					<script type="text/html" id="tpl-fold-comment">
    <div class="event event-common event-fold-comment" id="event-fold">
        <i class="icon-event"></i>

        <div class="event-main">
            <div class="event-head">
                <a href="javascript:;" class="link-fold-comment">查看更早的 {{ comments_num }} 条讨论</a>
            </div>
        </div>
    </div>
	</script>







					<div class="detail-star-action">
						<a
							href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985/star?muid=33bed9f148144cb4a2b704d96ab74985"
							class="detail-action detail-action-star" data-itemid="518391"
							data-itemtype="Document" data-loading="true" data-method="post"
							data-remote="true" rel="nofollow" title="关注">关注</a>
					</div>

					<div class="detail-actions">
						<div class="item">
							<a href="javascript:window.print()">打印</a>
						</div>

						<c:if test="${cookie.isEdite.value eq 1 || message.createUserId eq cookie.id.value}">
							<div class="item">
								<span class="detail-action detail-action-edit edit-locked hide" data-tooltip="${userName } 正在编辑" data-url="/smart-api/message/${message.id }/editeMsg#is_locked">编辑
									<i class="twr twr-lock"></i>
								</span> 
								<a href="/smart-api/message/${message.id }/editeMsg/" class="detail-action detail-action-edit-real " data-url="/smart-api/message/${message.id }/editeMsg/" >编辑</a>
							</div>
						</c:if>

						<div class="item detail-action-move"
							data-visible-to="admin,creator">
							<a href="javascript:;" class="detail-action">移动</a>
							<div class="confirm">
								<form class="form form-move"
									action="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985/move"
									method="post" data-remote="true">
									<p class="title">移动文档到项目</p>
									<p>
										<select data-project="dbe017efd44044619c565f3e38a48ba5"
											class="choose-projects loading"></select> <input
											type="hidden" name="tpuid">
									</p>
									<p>
										<button type="submit" class="btn btn-mini" disabled
											data-disable-with="正在移动...">移动</button>
										<button type="button" class="btn btn-x cancel">取消</button>
									</p>
								</form>
							</div>
						</div>
						
						
						<div class="item">
							<a href="/smart-api/message/newMsg">新建</a>
						</div>
						
						<c:if test="${cookie.isEdite.value eq 1 || message.createUserId eq cookie.id.value}">	
							<div class="item" data-visible-to="admin,creator">
								<a href="/smart-api/message/${message.id }/delMsg/" class="detail-action detail-action-del" data-confirm="确定要删除这篇文档吗？" data-method="delete" >删除</a>
							</div>
						</c:if>
					</div>


					<script type="text/html" id="tpl-dir-popover">
            <div class="change-dir-popover">
    <h3>选择文件夹</h3>
    <div class="dirs" data-url="/projects/dbe017efd44044619c565f3e38a48ba5/folders">
        <div class="node create-folder" data-url="">
            <i class="icon-folder twr twr-folder"></i>
            <form class="form" action="/projects/dbe017efd44044619c565f3e38a48ba5/dirs" method="post" data-remote="true">
                <div class="form-item">
                    <div class="form-field">
                        <input type="text" name="name" class="label no-border" data-validate="required;length:0,255" data-validate-msg="文件夹名称不能为空;文件夹名称最长255个字符" id="txt-dir-name" placeholder="创建文件夹">
                        <a href="javascript:;" class="link-submit-dir disabled">
                            <i class="twr twr-check-circle"></i>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="remove-dir">
        <a href="javascript:;" class="link-remove-dir">移出文件夹</a>
    </div>
</div>


        </script>

					<script type="text/html" id="tpl-doc-diff-popover">
            <div class="popover-diff">
                <ul class="doc-versions">
                        <li>
                            <a href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985/diff?v1=3&amp;v2=2" class="link-diff version" data-method="get" data-remote="true" data-version-editor="易德军" data-version-time="2016-02-25 14:52:47">
                                <span class="info">
                                    <span class="version-time">2016-02-25 14:52:47</span>
                                    <span class="version-editor">
                                        易德军
                                    </span>
                                </span>
</a>                        </li>
                        <li>
                            <a href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985/diff?v1=3&amp;v2=1" class="link-diff version" data-method="get" data-remote="true" data-version-editor="易德军" data-version-time="2016-02-25 12:31:52">
                                <span class="info">
                                    <span class="version-time">2016-02-25 12:31:52</span>
                                    <span class="version-editor">
                                        易德军
                                    </span>
                                </span>
</a>                        </li>
                </ul>
            </div>
        </script>








					<div class="comment comment-form new">
						<form class="form form-editor form-new-comment" method="post"
							data-remote="true"
							action="comment/${message.id }/docs/">

							<a class="avatar-wrap" target="_blank"> <img class="avatar"
								width="50" height="50" />
							</a>

							<div class="comment-main">
								<div class="form-item">
									<div class="form-field">
										<div class="fake-textarea" data-droppable>点击发表评论</div>
										<textarea id="txt-new-comment" tabindex="1" autofocus
											data-validate="custom" data-autosave="new-comment-content"
											data-mention-group="dbe017efd44044619c565f3e38a48ba5"
											data-mention-type="project" class="comment-content hide"
											name="comment_content"></textarea>
									</div>
								</div>

								<div class="form-item notify hide">
									<div class="notify-title">
										<div class="notify-title-title">发送通知给：</div>
										<div class="notify-title-summary">
											<span class="receiver"></span> 
											<span class="change-notify">
												[ <a href="javascript:;" class="link-change-notify">更改</a> ]
											</span>
										</div>
										<div class="notify-title-select hide">
											<span unselectable="on" data-subgroup="-1" class="group-select">所有人</span> 
											<c:forEach var="obj" items="${allUserGroup }">
												<span data-subgroup="${obj.id }" unselectable="on" class="group-select">${obj.groupName }</span> 
											</c:forEach>
										</div>
										
									</div>

									<div class="form-field hide">
										<ul class="member-list">
											<c:forEach var="obj" items="${allUser }">
												<li>
													<label> 
														<input type="checkbox" tabIndex="-1" value="${obj.id }" data-subgroup="${obj.groupId }" /> <span title="${obj.name }">${obj.name }</span>
													</label>
												</li>
											</c:forEach>
										</ul>
									</div>

								</div>

								<div class="hide form-buttons">
									<button tabindex="1" type="submit"
										class="btn btn-primary btn-create-comment"
										data-disable-with="正在发送...">发表评论</button>
									<button tabindex="2" type="button"
										class="btn btn-x btn-cancel-create-comment">取消</button>
								</div>
							</div>
						</form>
					</div>

					<div class="zoom-meeting">
						<p>
							不想打字？试试<a href="javascript:;"
								data-url="/teams/2c17c1fc24584c0497dd0a6ac8a3ed18/zoom/create"
								id="link-create-zoom">召开视频会议</a>。
						</p>

						

						<script type="text/html" id="tpl-zoom-content">
        <p>点击下面的链接，加入我的视频会议吧：</p>
        <p>
            <a href="{{ zoom_url }}" target="_blank">{{ zoom_url }}</a>
        </p>
    </script>
					</div>



					<script type="text/html" id="comments-liked-list">
        
    				</script>

				</div>
			</div>
		</div>


		<%@ include file="/inc-content-foot.jsp"%>



	</div>

	<%@ include file="/inc-foot.jsp"%>
	
	 <script type="text/javascript">
    var testEditor;
    $(function () {
        testEditor = editormd.markdownToHTML("doc-content", {
            htmlDecode: "style,script,iframe",  // you can filter tags decode
            emoji: true,
            taskList: true,
            tex: true,  // 默认不解析
            flowChart: true,  // 默认不解析
            sequenceDiagram: true,  // 默认不解析
            codeFold: true,
    });});
 </script>

</body>
</html>