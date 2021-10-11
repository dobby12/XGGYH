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

<style>
.main_list {
    width: 1100px;
    margin: 0 auto;
}

.list_start {
    text-align: center;
}

.list_detail {
    display: inline-block;
    width: 300px;
    height: 350px;
	border: 1px solid;
    margin-bottom: 40px;
    margin-right: 20px;
    margin-left: 20px;
    text-align: center;
}

.paging_start{
	text-align: center;
}

.photoEx {
	width: 250px;
    height: 280px;
    border: 1px solid;
    text-align: center;
    margin: 0 auto;
    margin-top: 25px;
}

</style>

</head>
<body>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h1>공연 정보 게시판(전체 리스트)</h1>
<hr>

<div class="main_list">
		<div class="list_start">
			<c:forEach items="${showList }" var="showList">
			<div class="list_detail">
			<div class="photoEx">사진 들어갈 자리</div>
			<a href="<%=request.getContextPath() %>/show/detail?showNo=${showList.showNo }"> 
			<p>${showList.showTitle} <br> ${showList.showDirector }</p> </a>
			</div>
			</c:forEach>
		</div>
</div>


<c:import url="/WEB-INF/views/layout/paging.jsp" />

</div>

</body>
</html>