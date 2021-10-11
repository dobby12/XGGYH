<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 3 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">
.detailInfo {
	width: 500px;
	height: 550px;
	border: 1px solid;
	float: right;
}

.photoEx {
	width: 550px;
	height: 610px;
	border: 1px solid;
	float: left;
}

.content {
	width: 550px;
	height: 200px;
	border: 1px solid;
	clear: left;
	margin: 0 auto;
}
</style>

</head>
<body>

	<c:import url="/WEB-INF/views/layout/header.jsp" />

	<div class="container">

		<h1>공연 상세 정보( ${showDetail.showTitle} )</h1>
		<hr>

		<div class="photoEx">사진 사진</div>

		<div class="detailInfo">
			공연 번호 : ${showDetail.showNo} <br>
			작성자(관리자 아이디) : ${showDetail.adminId} <br> 
			카테고리 : ${showKindName} <br> 
			장르 : ${showGenreName} <br>
			
			공연장 : <a href="<%=request.getContextPath() %>/hall/detail?hallNo=${showDetail.hallNo }"> 
			${showHallName} </a> <br>
			
			작성일 : ${showDetail.showDate} <br>
			연령 제한 : ${showDetail.showAge} <br> 
			감독 : ${showDetail.showDirector} <br> 
			출연 배우 : ${showDetail.showActor} <br> 
			상연 시작일 : ${showDetail.showStart} <br> 
			상연 종료일 : ${showDetail.showEnd} <br>
		</div>

		<div class="content">${showDetail.showContent }</div>


		<div class="text-center">
			<button style="margin: auto 0"
				onclick="location.href='<%=request.getContextPath()%>/show';">공연
				목록</button>
		</div>
	</div>
</body>
</html>