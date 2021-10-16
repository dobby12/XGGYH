<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<head>
<title>Insert title here</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<style type="text/css">
.posterSuggest {
    width: 175px;
    height: 230px;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    border:1px solid black;
}

</style>

</head>
<body>

<h1>${memid} 님을 위한 추천공연</h1>

<div class="main_list">
		<div class="list_start">
			<c:forEach items="${suggestshowList }" var="suggestshowList">
			
			<div class="list_detail">
			<a href="<%=request.getContextPath() %>/show/memgenre?showNo=${suggestshowList.showNo }"> 
			<img class="posterSuggest" src='http://drive.google.com/uc?export=view&id=1UCDamPPObCPN9BY8Iz2WjsgiY8m80K2b' /><br>
			${suggestshowList.showTitle} <br> ${suggestshowList.showDirector }
			</a>
			</div>
			
			</c:forEach>
		</div>
</div>

<hr>

</body>