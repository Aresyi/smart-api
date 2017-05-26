<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp"%>

<body>
	<div id="page-loading-mask"></div>

	<div class="wrapper">


		<%@ include file="/inc-menu-head.jsp"%>



		<div class="container workspace">
			<div class="page">
				<div class="page-inner" id="page-404"
					data-page-name="404 - 你访问的页面不存在">
					<div class="content">
						<div class="error-404-gif" alt="404错误"></div>
						<p class="error-title">
							<b>“</b>喔嚄，我想它已经飞走了...吧？<b>”</b>
						</p>
						<p class="error-desc">
							错误404: 你访问的页面不存在，<a href="javascript:history.back();">返回上页</a>
						</p>
					</div>
				</div>

			</div>
		</div>

		<%@ include file="/inc-content-foot.jsp"%>

	</div>

	<%@ include file="/inc-foot.jsp"%>

</body>
</html>