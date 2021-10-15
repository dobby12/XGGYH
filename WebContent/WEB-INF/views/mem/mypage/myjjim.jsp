<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    margin-bottom: 40px;
    margin-right: 20px;
    margin-left: 20px;
    text-align: center;
}

.paging_start{
	text-align: center;
}

.poster {
    width: 250px;
    height: 280px;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    border:1px solid black;
}

</style>

<body>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h2>내가 찜한 콘텐츠</h2>
<hr>

<div class="main_list">
		<div class="list_start">
			<c:forEach items="${showList }" var="show">
			<div class="list_detail">
			
			<a href="<%=request.getContextPath() %>/show/detail?showNo=${show.showNo }"> 
			<img class="poster" src='http://drive.google.com/uc?export=view&id=1UCDamPPObCPN9BY8Iz2WjsgiY8m80K2b' /><br>
			${show.showTitle} <br> ${show.showDate }
			</a>
			</div>
			</c:forEach>
		</div>
</div>

</div>
<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />

</body>