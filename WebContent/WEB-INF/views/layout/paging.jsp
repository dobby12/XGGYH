<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-center">

<ul class="pagination pagination-sm">

<!-- 첫 페이지로 -->
<c:if test="${paging.curPage ne 1 }">
	<li><a href="/board/list">&laquo; 첫 페이지로</a></li>
</c:if>




<!-- 이전 페이징 리스트로 이동 -->
<c:choose>
<c:when test="${paging.startPage ne 1 }">
	<li><a href="/board/list?curPage=${paging.startPage - paging.pageCount }"> &lt; </a></li>
</c:when>
<c:when test="${paging.startPage eq 1 }">
	<li class="disalbled"><a>&lt;</a>
</c:when>
</c:choose>


<!-- 이전 페이지로 가기 -->
<c:if test="${paging.curPage > 1 }">
	<li><a href="/board/list?curPage=${paging.curPage - 1 }">&larr;</a></li>
</c:if>

<!-- 페이징 리스트 -->
<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
<c:if test="${paging.curPage eq i }">
	<li class="active"><a href="/board/list?curPage=${i }">${i }</a></li>
</c:if>
<c:if test="${paging.curPage ne i }">
	<li><a href="/board/list?curPage=${i }">${i }</a></li>
</c:if>
</c:forEach>	


<!-- 다음 페이지로 가기 -->
<c:if test="${paging.curPage < paging.totalPage }">
	<li><a href="/board/list?curPage=${paging.curPage + 1 }">&rarr;</a></li>
</c:if>



<!-- 다음 페이징 리스트로 이동 -->
<c:choose>
<c:when test="${paging.endPage ne paging.totalPage }">
	<li><a href="/board/list?curPage=${paging.startPage + paging.pageCount }"> &gt; </a></li>
</c:when>
<c:when test="${paging.endPage eq paging.totalPage }">
	<li class="disalbled"><a>&gt;</a>
</c:when>
</c:choose>


<!-- 마지막 페이지로 -->
<c:if test="${paging.curPage ne paging.totalPage }">
	<li><a href="/board/list?curPage=${paging.totalPage }">마지막 페이지로 &raquo;</a></li>
</c:if>


</ul>

</div>