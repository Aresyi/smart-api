<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ attribute name="pagination" required="true" type="com.jongo.power.utils.Pagination" %>
<%@ attribute name="urlPattern" required="true" %>
<%@ attribute name="onComplete" required="false" %>
<%@ attribute name="onInit" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pagePlaceHolder" value="_page_"/>
<c:if test="${pagination.pageNumber > 1}">
<div class="pagenum">
显示结果  ${pagination.firstRow}-${pagination.lastRow}/${pagination.totalRecords}&nbsp;&nbsp;
<c:if test="${pagination.block > 1}">
<a href="javascript:void(0)" onclick="<c:if test="${!empty onInit}">${onInit};</c:if>new Ajax.Updater('${pagination.containerId}', '${fn:replace(urlPattern, pagePlaceHolder, 1)}', {method: 'get', parameters: 'containerId=${pagination.containerId}'<c:if test="${!empty onComplete}">, onComplete: function(){${onComplete};}</c:if>});" title="首页">首页</a>
<a href="javascript:void(0)" onclick="<c:if test="${!empty onInit}">${onInit};</c:if>new Ajax.Updater('${pagination.containerId}', '${fn:replace(urlPattern, pagePlaceHolder, pagination.pageOfPrevBlock)}', {method: 'get', parameters: 'containerId=${pagination.containerId}'<c:if test="${!empty onComplete}">, onComplete: function(){${onComplete};}</c:if>});" title="前 ${pagination.numbersPerBlock} 页">前${pagination.numbersPerBlock}页</a>
</c:if>
<c:if test="${pagination.page > 1}">
<a href="javascript:void(0)" onclick="<c:if test="${!empty onInit}">${onInit};</c:if>new Ajax.Updater('${pagination.containerId}', '${fn:replace(urlPattern, pagePlaceHolder, pagination.previousPage)}', {method: 'get', parameters: 'containerId=${pagination.containerId}'<c:if test="${!empty onComplete}">, onComplete: function(){${onComplete};}</c:if>});" title="前页">&lt;</a>
</c:if>
<c:forEach var="pos" begin="1" end="${pagination.numbersPerBlock}" >
<c:set var="page" value="${pos+(pagination.block -1) * pagination.numbersPerBlock}"/>
<c:if test="${page <= pagination.pageNumber}">
<c:choose>
<c:when test="${page == pagination.page}">
<a href="#" class="on" onclick="return false;"}>${page}</a>
</c:when>
<c:otherwise>
<a href="javascript:void(0)" onclick="<c:if test="${!empty onInit}">${onInit};</c:if>new Ajax.Updater('${pagination.containerId}', '${fn:replace(urlPattern, pagePlaceHolder, page)}', {method: 'get', parameters: 'containerId=${pagination.containerId}'<c:if test="${!empty onComplete}">, onComplete: function(){${onComplete}}</c:if>});">${page}</a>
</c:otherwise>
</c:choose>
</c:if>
</c:forEach>
<c:if test="${pagination.page < pagination.pageNumber}">
<a href="javascript:void(0)" onclick="<c:if test="${!empty onInit}">${onInit};</c:if>new Ajax.Updater('${pagination.containerId}', '${fn:replace(urlPattern, pagePlaceHolder, pagination.nextPage)}', {method: 'get', parameters: 'containerId=${pagination.containerId}'<c:if test="${!empty onComplete}">, onComplete: function(){${onComplete};}</c:if>});" title="后页">&gt;</a>
</c:if>
<c:if test="${pagination.block < pagination.blocks}">
<a href="javascript:void(0)" onclick="<c:if test="${!empty onInit}">${onInit};</c:if>new Ajax.Updater('${pagination.containerId}', '${fn:replace(urlPattern, pagePlaceHolder, pagination.pageOfNextBlock)}', {method: 'get', parameters: 'containerId=${pagination.containerId}'<c:if test="${!empty onComplete}">, onComplete: function(){${onComplete};}</c:if>});" title="后${pagination.numbersPerBlock}页">后${pagination.numbersPerBlock}页</a>
<a href="javascript:void(0)" onclick="<c:if test="${!empty onInit}">${onInit};</c:if>new Ajax.Updater('${pagination.containerId}', '${fn:replace(urlPattern, pagePlaceHolder, pagination.pageNumber)}', {method: 'get', parameters: 'containerId=${pagination.containerId}'<c:if test="${!empty onComplete}">, onComplete: function(){${onComplete};}</c:if>});" title="尾页">尾页</a>
</c:if>
</div>
</c:if>