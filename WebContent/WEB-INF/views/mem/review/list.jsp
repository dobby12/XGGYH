<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnWrite").click(function() {
		location.href="/XGGYH/review/write";
	});
	
});
</script>

<div class="container">

<h1>리뷰 게시판</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr>
	<th>리뷰번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>

<c:forEach items="${reviewList }" var="review">
<tr>
	<td>${review.reviewNo }</td>
	<td>
		<a href="<%=request.getContextPath() %>/review/detail?reviewno=${review.reviewNo }">${review.reviewTitle }</a>
	</td>
	<td>${review.memId }</td>
	<td>${review.reviewHit }</td>
	<td>${review.reviewDate }</td>
</tr>
</c:forEach>
</table>

<div id="btnBox" class="pull-left">
	<button id="btnWrite" >리뷰작성</button>
</div>

</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

</body>
</html>