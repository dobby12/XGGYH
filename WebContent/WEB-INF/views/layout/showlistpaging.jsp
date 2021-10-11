<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.pagination > li > a
{
    background-color: white;
    color: #D96459;
}

.pagination > li > a:focus,
.pagination > li > a:hover,
.pagination > li > span:focus,
.pagination > li > span:hover
{
    color: #5a5a5a;
    background-color: #f2f2f2;
    border-color: #ddd;
}

.pagination > .active > a
{
    color: white;
    background-color: #D96459 !Important;
    border: solid 1px #D96459 !Important;
}

.pagination > .active > a:hover
{
    background-color: #F2A099 !Important;
    border: solid 1px;
    border-color: #F2A099 !Important;
}
</style>

<div class="text-center">

<ul class="pagination pagination-sm">

<!-- 첫 페이지로 -->
<c:if test="${paging.curPage ne 1 }">
	<li><a href="${linkUrl }">&laquo; 첫 페이지로</a></li>
</c:if>




<!-- 이전 페이징 리스트로 이동 -->
<c:choose>
<c:when test="${paging.startPage ne 1 }">
	<li><a href="<%=request.getContextPath() %>${linkUrl }?curPage=${paging.startPage - paging.pageCount }"> &lt; </a></li>
</c:when>
<c:when test="${paging.startPage eq 1 }">
	<li class="disabled"><a>&lt;</a>
</c:when>
</c:choose>


<!-- 이전 페이지로 가기 -->
<c:if test="${paging.curPage > 1 }">
	<li><a href="<%=request.getContextPath() %>${linkUrl }?curPage=${paging.curPage - 1 }">&larr;</a></li>
</c:if>

<!-- 페이징 리스트 -->
<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
<c:if test="${paging.curPage eq i }">
	<li class="active"><a href="<%=request.getContextPath() %>${linkUrl }?curPage=${i }">${i }</a></li>
</c:if>
<c:if test="${paging.curPage ne i }">
	<li><a href="<%=request.getContextPath() %>${linkUrl }?curPage=${i }">${i }</a></li>
</c:if>
</c:forEach>


<!-- 다음 페이지로 가기 -->
<c:if test="${paging.curPage < paging.totalPage }">
	<li><a href="<%=request.getContextPath() %>${linkUrl }?curPage=${paging.curPage + 1 }">&rarr;</a></li>
</c:if>



<!-- 다음 페이징 리스트로 이동 -->
<c:choose>
<c:when test="${paging.endPage ne paging.totalPage }">
	<li><a href="<%=request.getContextPath() %>${linkUrl }?curPage=${paging.startPage + paging.pageCount }"> &gt; </a></li>
</c:when>
<c:when test="${paging.endPage eq paging.totalPage }">
	<li class="disabled"><a>&gt;</a>
</c:when>
</c:choose>


<!-- 마지막 페이지로 -->
<c:if test="${paging.curPage ne paging.totalPage }">
	<li><a href="<%=request.getContextPath() %>${linkUrl }?curPage=${paging.totalPage }">마지막 페이지로 &raquo;</a></li>
</c:if>


</ul>

</div>