<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ attribute name="pagination" required="true" type="com.jongo.power.utils.Pagination" %>
<%@ attribute name="urlPattern" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pagePlaceHolder" value="_page_"/>
<div class="pagenum">
	显示结果  ${pagination.firstRow}-${pagination.lastRow}/${pagination.totalRecords}&nbsp;&nbsp;
		<c:if test="${pagination.block > 1}">
		<a href="${fn:replace(urlPattern, pagePlaceHolder, 1)}" title="首页">首页</a>
		<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.pageOfPrevBlock)}" title="前 ${pagination.numbersPerBlock} 页">前${pagination.numbersPerBlock}页</a>
		</c:if>
		<c:if test="${pagination.page > 1}">
		<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.previousPage)}" title="前页">&lt;</a>
		</c:if>
		<c:forEach var="pos" begin="1" end="${pagination.numbersPerBlock}" >
		<c:set var="page" value="${pos+(pagination.block -1) * pagination.numbersPerBlock}"/>
		<c:if test="${page <= pagination.pageNumber}">
		<a href="${fn:replace(urlPattern, pagePlaceHolder, page)}" ${page == pagination.page ? 'class="on"' : ''}>${page}</a>
		</c:if>
		</c:forEach>
		<c:if test="${pagination.page < pagination.pageNumber}">
		<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.nextPage)}" title="后页">&gt;</a>
		</c:if>
		<c:if test="${pagination.block < pagination.blocks}">
		<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.pageOfNextBlock)}" title="后${pagination.numbersPerBlock}页">后${pagination.numbersPerBlock}页</a>
		<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.pageNumber)}" title="尾页">尾页</a>
		</c:if>
</div>
