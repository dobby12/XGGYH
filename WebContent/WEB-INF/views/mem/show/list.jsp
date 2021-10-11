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

</head>
<body>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h1>공연 정보 게시판</h1>
<hr>

<table class="table table-hover table-condensed">

<thead>
<tr>
	<th style="text-align: center; width: 5%">공연 번호</th>
	<th style="text-align: center; width: 5%">작성자</th>
	<th style="text-align: center; width: 10%">카테고리</th>
	<th style="text-align: center; width: 10%">장르</th>
	<th style="text-align: center; width: 15%">공연장</th>
	<th style="text-align: center; width: 20%">공연 제목</th>
	<th style="text-align: center; width: 30%">내용</th>
	<th style="text-align: center; width: 10%">작성일</th>
	<th style="text-align: center; width: 30%">연령 제한</th>
	<th style="text-align: center; width: 10%">감독</th>
	<th style="text-align: center; width: 30%">출연 배우</th>
	<th style="text-align: center; width: 10%">공연 시작</th>
	<th style="text-align: center; width: 10%">공연 종료</th>
</tr>
</thead>

<c:forEach items="${showList}" var="show">
</tbody>
<tr>

	<td>${show.show_no }</td>
	<td>${show.admin_id }</td>
	<td>${show.kind_no }</td>
	<td>${show.genre_no }</td>
	<td>${show.hall_no }</td>
	<td><a href="<%=request.getContextPath() %>/show/detail">${show.show_title }</a></td>
	<td>${show.show_content }</td>
	<td>${show.show_date }</td>
	<td>${show.show_age }</td>
	<td>${show.show_director }</td>
	<td>${show.show_actor }</td>
	<td>${show.show_start }</td>
	<td>${show.show_end }</td>

</tr>
</tbody>
</c:forEach>
</table>

</div>

</body>
</html>