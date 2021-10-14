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
	<th style="text-align: center; width: 10%">평점</th>
</tr>
</thead>

<c:forEach items="${memidJjimList }" var="jjim">
</tbody>
<tr>

	<td>${memidJjimList.ShowNo }</td>
	<td><a href="<%=request.getContextPath() %>/show/detail?showNo=${memidJjimList.showNo }">${memidJjimList.showTitle }</a></td>
	<td>${memidJjimList.showDirector }</td>
</tr>
</tbody>
</c:forEach>


</table>

</div>
<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />