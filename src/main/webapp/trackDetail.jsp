<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>


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
					data-page-name="${one.belongItem }->${one.version }" id="page-track">



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
											class="toctext">项目</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('type');"><span class="tocnumber">2</span> <span
											class="toctext">足迹类型</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('version');"><span class="tocnumber">4</span> <span
											class="toctext">足迹版本</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('reqDes');"><span class="tocnumber">5</span> <span
											class="toctext">运作简述</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('target');"><span class="tocnumber">5</span> <span
											class="toctext">运作目标</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('start');"><span class="tocnumber">6</span> <span
											class="toctext">开始日期</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('end');"><span class="tocnumber">7</span> <span
											class="toctext">验收日期</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('result');"><span class="tocnumber">8</span> <span
											class="toctext">验收结果</span> </a>
									</li>
									<li class="toclevel-1">
										<a href="javaScript:();" onclick="go('dutyPerson');"><span class="tocnumber">10</span> <span
											class="toctext">责任人</span> </a>
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
						项目
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.belongItem }</pre>
					
					<a name="type" id="type"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						足迹类型
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.belongType }</pre>
					
					<a name="version" id="version"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						足迹版本
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.version }</pre>
							
							
					<a name="reqDes" id="reqDes"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						运作简述
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.reqDes }</pre>
					
					
					<a name="target"id="target"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						运作目标
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.target }</pre>
					
					
					<a name="start" id="start"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						发版日期
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.releaseDate }</pre>
					
					<a name="end" id="end"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						检测日期
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.checkDate }</pre>
					
					<a name="result" id="result"></a>
					<div style="text-align: left;font-size: 25;padding-bottom: 5px;">
						验收结果
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.targetResult}</pre>
					
					
					<a name="dutyPerson" id="dutyPerson"></a>
					<div style='text-align: left;font-size: 25;padding-bottom: 5px;'>
						责任人
					</div>
					<pre style='padding-left: 15px;padding-bottom: 15px;'>${one.dutyPerson}</pre>
					
			</div>
			<!-- end of MBODY div -->


			


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
									<a href="/smart-api/track/${one.id }/toEditeTrack/" class="detail-action detail-action-edit-real " data-url="/smart-api/track/${one.id }/toEditeTrack/">编辑</a>
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
							<a href="/smart-api/track/create/">新建</a>
						</div>

						<div class="item" data-visible-to="admin,creator">
							<a
								href="/projects/dbe017efd44044619c565f3e38a48ba5/docs/33bed9f148144cb4a2b704d96ab74985"
								class="detail-action detail-action-del"
								data-confirm="确定要删除这篇文档吗？" data-method="delete"
								data-remote="true" data-stack-replace="true">删除</a>
						</div>
						
						
					</div>





				
			</div>
		</div>


		<%@ include file="/inc-content-foot.jsp"%>



	</div>

	<%@ include file="/inc-foot.jsp"%>

</body>
</html>