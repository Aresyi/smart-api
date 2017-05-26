<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ attribute name="pagination" required="true" type="com.jongo.power.utils.Pagination" %>
<%@ attribute name="urlPattern" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pagePlaceHolder" value="_page_"/>
<c:if test="${pagination.pageNumber > 1}">
<c:if test="${pagination.block > 1}">
<a href="${fn:replace(urlPattern, pagePlaceHolder, 1)}">1</a>
<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.pageOfPrevBlock)}">${pagination.pageOfPrevBlock-pagination.numbersPerBlock+1}-${pagination.pageOfPrevBlock}</a>
</c:if>
<c:if test="${pagination.page > 1}">
<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.previousPage)}">&lt;</a>
</c:if>
<c:forEach var="pos" begin="1" end="${pagination.numbersPerBlock}" >
<c:set var="page" value="${pos+(pagination.block -1) * pagination.numbersPerBlock}"/>
<c:if test="${page <= pagination.pageNumber}">
<c:choose>
<c:when test="${page == pagination.page}">
<span class="on"}>${page}</span>
</c:when>
<c:otherwise>
<a href="${fn:replace(urlPattern, pagePlaceHolder, page)}">${page}</a>
</c:otherwise>
</c:choose>
</c:if>
</c:forEach>
<c:if test="${pagination.page < pagination.pageNumber}">
<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.nextPage)}">&gt;</a>
</c:if>
<c:if test="${pagination.block < pagination.blocks}">
<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.pageOfNextBlock)}">${pagination.pageOfNextBlock}-${pagination.pageOfNextBlock+pagination.numbersPerBlock-1}</a>
<a href="${fn:replace(urlPattern, pagePlaceHolder, pagination.pageNumber)}">${pagination.pageNumber}</a>
</c:if>
</c:if>