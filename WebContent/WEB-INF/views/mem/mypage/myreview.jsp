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

<h2>내가 작성한 리뷰</h2>
<hr>

<table class="table table-hover table-condensed">

<c:if test="${empty memidReviewList }">
	<h3>작성한 리뷰가 없습니다. 리뷰를 작성해주세요.</h3>
</c:if>

<c:if test="${not empty memidReviewList }">
<thead>
<tr>
	<th style="text-align: center; width: 10%">번호</th>	
	<th style="text-align: center; width: 20%">공연제목</th>
	<th style="text-align: center; width: 30%">제목</th>
	<th style="text-align: center; width: 20%">별점</th>
	<th style="text-align: center; width: 10%">작성일</th>
	<th style="text-align: center; width: 10%">조회수</th>
</tr>
</thead>

<c:forEach items="${memidReviewList }" var="review" varStatus="status">
</tbody>
<tr>

	<td>${review.reviewNo }</td>
	<td>${showTitle[status.index] }</td>
	<td><a href="<%=request.getContextPath() %>/review/detail?reviewno=${review.reviewNo }">${review.reviewTitle }</a></td>
	<td>
		<c:if test="${review.reviewScore == 1 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if>
		<c:if test="${review.reviewScore == 2 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if>
		<c:if test="${review.reviewScore == 3 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if>
		<c:if test="${review.reviewScore == 4 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if>
		<c:if test="${review.reviewScore == 5 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if></td>
		<td>${review.reviewDate }</td>
		<td>${review.reviewHit }</td>
</tr>
</tbody>
</c:forEach>
</c:if>

</table>

</div>
<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />