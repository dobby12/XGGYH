<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnWrite").click(function() {
		location.href="/review/write";
	});
	
});
</script>

<div class="container">

<h1>리뷰 게시판</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr>
	<th>공연제목</th>
	<th>제목</th>
	<th>작성자</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>

<%-- <c:forEach items="${reviewList }" var="reivewList"> --%>
<!-- <tr> -->
<%-- 	<td>${reviewList.showNo }</td> --%>
<!-- 	<td> -->
<%-- 		<a href="/review/detail?reviewNo=${reviewList.reviewNo }"> --%>
<%-- 		${reviewList.reviewTitle } --%>
<!-- 		</a> -->
<!-- 	</td> -->
<%-- 	<td>${reviewList.memId }</td> --%>
<%-- 	<td>${reviewList.reviewHit }</td> --%>
<%-- 	<td>${reviewList.reviewDate }</td> --%>
<!-- </tr> -->
<%-- </c:forEach> --%>
</table>

<div id="btnBox" class="pull-left">
	<button id="btnWrite" class="btn btn-primary">리뷰작성</button>
</div>

</div>

<c:import url="/WEB-INF/views/layout/reviewpaging.jsp" />

</body>
</html>