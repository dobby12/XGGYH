<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">
#header, #footer {
	text-align: center;
	background: #CCC;
}
</style>

</head>
<body>

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

<c:forEach items="${reviewList }" var="reivewList">
<tr>
	<td>${reviewList.show_no }</td>
	<td>
		<a href="/review/detail?review_no=${reviewList.review_no }">
		${reviewList.review_title }
		</a>
	</td>
	<td>${reviewList.mem_id }</td>
	<td>${reviewList.review_hit }</td>
	<td>${reviewList.review_date }</td>
</tr>
</c:forEach>

</table>

<div id="btnBox" class="pull-left">
	<button id="btnWrite" class="btn btn-primary">리뷰작성</button>
</div>

</div>

<c:import url="/WEB-INF/views/layout/reviewpaging.jsp" />

</body>
</html>