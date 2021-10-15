<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<style type="text/css">

table {
	text-align: center;
}

</style>

<div class="container">

<h2>리뷰 목록</h2>
<hr>

<table class="table table-hover table-condensed">

<thead>
<tr>
	<th style="text-align: center; width: 8%">리뷰 번호</th>
	<th style="text-align: center; width: 59%">제목</th>
	<th style="text-align: center; width: 13%">작성자</th>
	<th style="text-align: center; width: 12%">작성 날짜</th>
	<th style="text-align: center; width: 8%">조회수</th>
</tr>
</thead>

<c:forEach items="${reviewList }" var="review">
</tbody>
<tr>

	<td>${review.reviewNo }</td>
	<td><a href="<%=request.getContextPath() %>/admin/review/detail?reviewno=${review.reviewNo }">${review.reviewTitle }</a></td>
	<td>${review.memId }</td>
	<td>${review.reviewDate }</td>
	<td>${review.reviewHit }</td>
</tr>
</tbody>
</c:forEach>


</table>

</div>
<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />
