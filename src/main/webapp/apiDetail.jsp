<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>


<script src="/smart-api/htdocs/json/c.js" type="text/javascript"></script>
<link href="/smart-api/htdocs/json/s.css" type="text/css" rel="stylesheet"></link>


<script type="text/javascript">
<!--
function showTocToggle() {
	if (document.createTextNode) {
		// Uses DOM calls to avoid document.write + XHTML issues

		var linkHolder = document.getElementById('toctitle');
		if (!linkHolder) {
			return;
		}

		var outerSpan = document.createElement('span');
		outerSpan.className = 'toctoggle';

		var toggleLink = document.createElement('a');
		toggleLink.id = 'togglelink';
		toggleLink.className = 'internal';
		toggleLink.href = 'javascript:toggleToc()';
		toggleLink.appendChild(document.createTextNode(tocHideText));

		outerSpan.appendChild(document.createTextNode('['));
		outerSpan.appendChild(toggleLink);
		outerSpan.appendChild(document.createTextNode(']'));

		linkHolder.appendChild(document.createTextNode(' '));
		linkHolder.appendChild(outerSpan);

		var cookiePos = document.cookie.indexOf("hidetoc=");
		if (cookiePos > -1 && document.cookie.charAt(cookiePos + 8) == 1) {
			toggleToc();
		}
	}
}

function changeText(el, newText) {
	// Safari work around
	if (el.innerText) {
		el.innerText = newText;
	} else if (el.firstChild && el.firstChild.nodeValue) {
		el.firstChild.nodeValue = newText;
	}
}

function toggleToc() {
	var toc = document.getElementById('toc').getElementsByTagName('ul')[0];
	var toggleLink = document.getElementById('togglelink');

	if (toc && toggleLink && toc.style.display == 'none') {
		changeText(toggleLink, tocHideText);
		toc.style.display = 'block';
		document.cookie = "hidetoc=0";
	} else {
		changeText(toggleLink, tocShowText);
		toc.style.display = 'none';
		document.cookie = "hidetoc=1";
	}
}

function go(id){
	document.getElementById(id).scrollIntoView();
}

//-->
</script>


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
					data-creator-guid="${one.id }"
					data-project-creator="d585890abe7a411ea75c9135f52598ef"
					data-page-name="${one.belongItem }->${one.belongModule }->${one.name }" id="page-api">



					<div class="doc-wrap gallery-wrap">
						<div id="doc-content"
								class="doc-content editor-style gallery-wrap ">

					<table id="toc" style="  width: 250px; margin-top: 34px !important;" summary="目录">
						<tr>
							<td>
								<div id="toctitle" style="padding: 0 20px;">
									<h2>
										目录
									</h2>
								</div>
								<ul>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('jiekou');"><span class="tocnumber">1</span> <span
											class="toctext">接口</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('URL');"><span class="tocnumber">2</span> <span
											class="toctext">URL</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('geshi');"><span class="tocnumber">3</span> <span
											class="toctext">支持格式</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('request');"><span class="tocnumber">4</span> <span
											class="toctext">HTTP请求方式</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('sigin');"><span class="tocnumber">5</span> <span
											class="toctext">是否需要登录</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('para');"><span class="tocnumber">6</span> <span
											class="toctext">请求参数</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('result');"><span class="tocnumber">7</span> <span
											class="toctext">返回结果</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('other');"><span class="tocnumber">8</span> <span
											class="toctext">迭代修改说明</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('create');"><span class="tocnumber">9</span> <span
											class="toctext">创建人和时间</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('history');"><span class="tocnumber">10</span> <span
											class="toctext">历史版本</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('test');"><span class="tocnumber">11</span> <span
											class="toctext">调试接口</span> </a>
									</li>
								</ul>
							</td>
						</tr>
					</table>
					<script type="text/javascript">
						if (window.showTocToggle) {
							var tocShowText = "显示";
							var tocHideText = "隐藏";
							showTocToggle();
						}
					</script>
					
					<a  name="jiekou" id="jiekou"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						接口
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>[${one.belongItem }]->[${one.belongModule }]<br>${one.name }<br>${one.des }</pre>
					
					<a name="URL" id="URL"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						URL
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.url }</pre>
							
							
					<a name="geshi" id="geshi"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						支持格式
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>JSON</pre>
					
					
					<a name="request"id="request"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						HTTP请求方式
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>POST/GET</pre>
					
					
					<a name="sigin" id="sigin"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						是否需要登录
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.needCookie }</pre>
					
					
					<a name="para" id="para"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						请求参数
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>
							<table cellpadding="1" cellspacing="0" class="Requestparameters" style="width: 100%">
								<tr>
									<td>
										名称
									</td>
									<td>
										类型
									</td>
									<td>
										是否必须
									</td>
									<td>
										描述
									</td>
									<td>
										示例值
									</td>
								</tr>
							<c:forEach var = "para" items="${one.parameter }">
								<tr>
									<td>
										${para.para_name }
									</td>
									<td>
										${para.para_type }
									</td>
									<td>
										${para.para_isNeed }
									</td>
									<td>
										${para.para_des }
									</td>
									<td>
										${para.para_caseVal }
									</td>
								</tr>
							</c:forEach>
						</table>
					</pre>
					
					<a name="result" id="result"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						返回结果&nbsp;&nbsp;(<a href='/smart-api/api/${one.id }/apiTest/' target='_blank'>调试该接口</a>)
					</div>
					<p>
						JSON示例
					</p>
					<!-- 
					<pre style='padding-left: 15px;padding-bottom: 15px;'>
						${one.result }
					</pre>
					 -->
					<div class="HeadersRow">
					<!-- 
					  <div id="HeaderTitle"> JSON格式化及高亮 &nbsp;&copy;&nbsp;2012&nbsp; &nbsp;<span style='color:#aaa;font-weight:bold;font-style:italic'>Random_Coder</span> </div>
					  <h3 id="HeaderSubTitle">JSON格式化及高亮:</h3>
					 -->  
					  <textarea id="RawJson">${one.result }
					  </textarea>
					</div>
					<div id="ControlsRow">
					  <input type="Button" value="格式化" onclick="Process()"/>
					  <span id="TabSizeHolder">
					    缩进量
					    <select id="TabSize" onchange="TabSizeChanged()">
					      <option value="1">1</option>
					      <option value="2" selected="true">2</option>
					      <option value="3">3</option>
					      <option value="4">4</option>
					      <option value="5">5</option>
					      <option value="6">6</option>
					    </select>
					  </span>
					  <label for="QuoteKeys">
					    <input type="checkbox" id="QuoteKeys" onclick="QuoteKeysClicked()" checked="true" /> 
					    引号
					  </label>&nbsp; 
					  <a href="javascript:void(0);" onclick="SelectAllClicked()">全选</a>
					  &nbsp;
					  <span id="CollapsibleViewHolder" >
					      <label for="CollapsibleView">
					        <input type="checkbox" id="CollapsibleView" onclick="CollapsibleViewClicked()" checked="true" /> 
					        显示控制
					      </label>
					  </span>
					  <span id="CollapsibleViewDetail">
					    <a href="javascript:void(0);" onclick="ExpandAllClicked()">展开</a>
					    <a href="javascript:void(0);" onclick="CollapseAllClicked()">叠起</a>
					    <a href="javascript:void(0);" onclick="CollapseLevel(3)">2级</a>
					    <a href="javascript:void(0);" onclick="CollapseLevel(4)">3级</a>
					    <a href="javascript:void(0);" onclick="CollapseLevel(5)">4级</a>
					    <a href="javascript:void(0);" onclick="CollapseLevel(6)">5级</a>
					    <a href="javascript:void(0);" onclick="CollapseLevel(7)">6级</a>
					    <a href="javascript:void(0);" onclick="CollapseLevel(8)">7级</a>
					    <a href="javascript:void(0);" onclick="CollapseLevel(9)">8级</a>
					  </span>
					</div>
					<div id="Canvas" class="Canvas"></div>

					
					
					<c:if test="${not empty one.resDataDes }">
					<a name="other" id="other"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						字段说明&nbsp;&nbsp;(<a href='/smart-api/data/find/' target='_blank'>更多参考</a>)
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>
						<table cellpadding='1' cellspacing='0' class='Requestparameters' style='width: 100%'>
			           		<tr style='text-align: center; font-weight: bold;'>
			            		<td width='18%'>名称</td>
			           			<td width='10%'>类型</td>
			            		<td width='8%'>长度</td>
			            		<td width='65%'>描述</td>
			            	</tr>
			            	<c:forEach var = "obj" items="${one.resDataDesList }">
			            		<tr>
			            			<td>${obj.column }</td>
			           				<td>${obj.type }</td>
			            			<td>${obj.length }</td>
			            			<td>${obj.comment }</td>
			            		</tr>
			            	</c:forEach>	
			            </table>	
					</pre>
					</c:if>
					
					<a name="other" id="other"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						迭代修改说明
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${not empty one.other ? one.other : "无" }</pre>
					
					
					<a name="create" id="create"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						创建人和时间
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.creater }  ${tranb:longToDateString(one.createTime) } 创建 <c:if test="${not empty one.modifyer }"> [${one.modifyer } ${tranb:longToDateString(one.modifyTime) } 最后修改]</c:if></pre>
					

					<a name="history" id="history"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						历史版本
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'><c:if test="${not empty one.historyTop5}"><c:forEach var="his" items="${one.historyTop5 }" varStatus="v">${v.count}、${tranb:longToDateString(his.modifyTime) } ${his.modifyer } <a href="/smart-api/api/${his.id }/apiDetail/?history=1" target="_black">历史详情</a><br></c:forEach></c:if><c:if test="${one.isHasHistory eq 0 || empty one.isHasHistory}">无</c:if></pre>
					
					
					<a name="test" id="test"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						调试接口
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'><a href='/smart-api/api/${one.id }/apiTest/' target='_blank'>使用网页调试工具调试该接口</a></pre>

					<a name="dafen" id="dafen"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						评价打分
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>
							<table style='border:none;margin:none; '>
								<tr>
									<td>
									兼容性:&nbsp;&nbsp;<select><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select>&nbsp;&nbsp;&nbsp;&nbsp;
									接口规范:&nbsp;&nbsp;<select><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select>&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
							</table>
				   </pre>
			</div>
			<!-- end of MBODY div -->


			<div class="comments streams">
						<div class="event event-common event-document-add"
							id="event-46586231"
							data-ancestor-guid="dbe017efd44044619c565f3e38a48ba5"
							data-ancestor-name=""
							data-ancestor-url="/projects/dbe017efd44044619c565f3e38a48ba5">

							<a href="/members/${one.createUserId }" class="from"
								target="_blank"><img alt="${one.creater }" class="avatar"
								src="https://avatar.tower.im/28a1a5070c494a48adf19921db540e82" /></a>
							<i class="icon-event"></i>

							<div class="event-main">
								<div class="event-head">
									<a href="#event-46586231"
										data-created-at="${tranb:longToDateString(one.createTime) }"
										class="event-created-at">${tranb:longToDateString(one.createTime) }</a> <span
										class="event-actor"> <a
										href="/members/cf99cd6d64594a03bf758436dd69a217"
										class="link-member" target="_blank">${one.creater }</a>
									</span> <span class="event-action"> 创建了接口 </span> <span
										class="event-text"> <span class="emphasize"> <a
											href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985"
											class="document-rest" data-stack="true">${one.name }</a>
									</span>
									</span>
								</div>
							</div>
						</div>

						<c:if test="${not empty one.modifyer }">
						<div class="event event-common event-document-edit"
							id="event-46610457"
							data-ancestor-guid="dbe017efd44044619c565f3e38a48ba5"
							data-ancestor-name=""
							data-ancestor-url="/projects/dbe017efd44044619c565f3e38a48ba5">

							<a href="/members/${one.createUserId }" class="from"
								target="_blank"><img alt="${one.modifyer }" class="avatar"
								src="https://avatar.tower.im/28a1a5070c494a48adf19921db540e82" /></a>
							<i class="icon-event"></i>

							<div class="event-main">
								<div class="event-head">
									<a href="#event-46610457"
										data-created-at="${tranb:longToDateString(one.modifyTime) }"
										class="event-created-at"> ${tranb:longToDateString(one.modifyTime) }</a> <span
										class="event-actor"> <a
										href="/members/cf99cd6d64594a03bf758436dd69a217"
										class="link-member" target="_blank">${one.modifyer }</a>
									</span> <span class="event-action"> 最后编辑 </span> <span
										class="event-text"> <span class="emphasize"> <a
											href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985"
											class="document-rest" data-stack="true">${one.name }</a>
									</span>
									</span>
								</div>
							</div>
						</div>
						</c:if>
						

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
	
									<div class="comment-content editor-style" style='width: 80%;'>
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

						<c:if test="${empty one.isDel && (cookie.isEdite.value eq 1 || one.createUserId eq cookie.id.value)}">
							<div class="item">
								<span class="detail-action detail-action-edit edit-locked hide"
									data-tooltip="${userName } 正在编辑"
									data-url="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985/is_locked">编辑<i
									class="twr twr-lock"></i></span> 
									<a href="/smart-api/api/${one.id }/toEditeAPI/" class="detail-action detail-action-edit-real " data-url="/smart-api/api/${one.id }/toEditeAPI/">编辑</a>
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
							<a href="/smart-api/api/createAPI/">新建</a>
						</div>

						<div class="item" data-visible-to="admin,creator">
							<a
								href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985"
								class="detail-action detail-action-del"
								data-confirm="确定要删除这篇文档吗？" data-method="delete"
								data-remote="true" data-stack-replace="true">删除</a>
						</div>
						
						
					</div>





					<div class="comment comment-form new">
						<form class="form form-editor form-new-comment" method="post" data-remote="true" action="comment/${one.id }/api/">

							<a class="avatar-wrap" target="_blank"> 
								<img class="avatar" src="/smart-api/assets/default_avatars/${empty cookie.avatar.value ? '2.jpg' : cookie.avatar.value}" alt="${name }"  width="50" height="50" />
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
										<div class="notify-title-select hide" >
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
									<button tabindex="1" type="submit" class="btn btn-primary btn-create-comment" data-disable-with="正在发送...">发表评论</button>
									<button tabindex="2" type="button" class="btn btn-x btn-cancel-create-comment">取消</button>
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

						

					<script type="text/html" id="comments-liked-list">
        
    				</script>

				</div>
			</div>
		</div>


		<%@ include file="/inc-content-foot.jsp"%>



	</div>

	<%@ include file="/inc-foot.jsp"%>

</body>

<style>
<!--
.simditor .simditor-body pre, .editor-style pre {
border-left: 0;
}
-->
</style>
</html>