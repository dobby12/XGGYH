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

<h2>게시글 목록</h2>
<hr>

<table class="table table-hover table-condensed">

<thead>
<tr>
	<th style="text-align: center; width: 8%">리뷰 번호</th>
	<th style="text-align: center; width: 8%">공연 번호</th>
	<th style="text-align: center; width: 10%">작성자</th>
	<th style="text-align: center; width: 51%">제목</th>
	<th style="text-align: center; width: 10%">작성 날짜</th>
	<th style="text-align: center; width: 5%">평점</th>
	<th style="text-align: center; width: 8%">조회수</th>
</tr>
</thead>

<c:forEach items="${reviewList }" var="review">
</tbody>
<tr>

	<td>${review.review_no }</td>
	<td>${review.show_no }</td>
	<td>${review.mem_id }</td>
	<td><a href="<%=request.getContextPath() %>/admin/review/detail?review_no=${review.review_no }">${review.review_title }</a></td>
	<td>${review.review_date }</td>
	<td>${review.review_score }</td>
	<td>${review.review_hit }</td>
</tr>
</tbody>
</c:forEach>


</table>

</div>
<c:import url="/WEB-INF/views/layout/paging.jsp" />

</body>
</html>