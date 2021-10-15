<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">

table {
	text-align: center;
}

</style>

<div class="container">

<h2>내가 찜한 콘텐츠</h2>
<hr>

<table class="table table-hover table-condensed">

<thead>
<tr>
	<th style="text-align: center; width: 10%">번호</th>
	<th style="text-align: center; width: 10%">제목</th>
	<th style="text-align: center; width: 10%">감독</th>
</tr>
</thead>

<c:forEach items="${showList }" var="show">
</tbody>
<tr>
	<td>${show.showNo }</td>
	<td><a href="<%=request.getContextPath() %>/show/detail?showNo=${show.showNo }">${show.showTitle }</a></td>
	<td>${show.showDirector }</td>
</tr>
</tbody>
</c:forEach>


</table>

</div>
<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />