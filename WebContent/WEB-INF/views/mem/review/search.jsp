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

<h3 class="pull-left">리뷰 게시글 검색 결과</h3>
<hr>
<br>

<div class="text-right">
			<p>
			<a href="<%=request.getContextPath() %>/review/list">전체 보기</a>
</div>

<table class="table table-hover table-condensed table-center">

<thead>
<tr>
	<th style="width: 15%">게시글 번호</th>
	<th style="width: 40%">제목</th>
	<th style="width: 15%">작성자</th>
	<th style="width: 15%">조회수</th>
	<th style="width: 15%">작성일</th>
</tr>
</thead>

<c:forEach items="${searchReviewList }" var="review">
</tbody>
<tr>
	<td>${review.reviewNo }</td>
	<td>
		<a href="<%=request.getContextPath() %>/review/detail?reviewno=${review.reviewNo }">${review.reviewTitle }</a>
	</td>
	<td>${review.memId }</td>
	<td>${review.reviewHit }</td>
	<td>${review.reviewDate }</td>
</tr>
</tbody>
</c:forEach>


</table>

<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />