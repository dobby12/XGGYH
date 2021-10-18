<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공연의 모든 것, 공공연히</title>

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
    width: 240px;
    height: 300px;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
}

.search {
	width: 240px;
	margin: 0 auto;
}

</style>

<script>
function imgSrc(){
    var imgSrc;
    var kind = '${kindName}'
    var i = Math.floor(Math.random()*8)+1
    console.log(i)
    if(kind == '연극') {
        imgSrc = '/resources/file/show_poster/ac'+ i + '.jpg';
    } else if(kind == '뮤지컬') {
        imgSrc = '/resources/file/show_poster/mu'+ i + '.jpg';
    } else if(kind == '콘서트') {
        imgSrc = '/resources/file/show_poster/co'+ i + '.jpg';
    } else if(kind == '오페라') {
        imgSrc = '/resources/file/show_poster/op'+ i + '.jpg';
    }
    return imgSrc;
}

function putImg(){
    document.getElementById('poster').src=imgSrc()
}

var imgList = [];
$(document).ready(function(){
	<c:forEach items="${showList}" var="item">
// 	putImg();
	imgList.push(imgSrc)
	console.log(imgList)
	</c:forEach>
})
</script>

</head>
<body>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h1>공연 정보 게시판( ${kindName} )</h1>
<hr>

<c:if test="${ login ne null }">
	<c:import url="/WEB-INF/views/mem/show/suggest.jsp" />
</c:if>

<h2> ${kindName} 게시판 </h2>
<div class="main_list">
		<div class="list_start">
			<c:forEach items="${showList }" var="showList" varStatus="status">
			<div class="list_detail">
			
			<a href="<%=request.getContextPath() %>/show/detail?showNo=${showList.showNo }">
			<c:set var="ran"><%= java.lang.Math.round(java.lang.Math.random() * 7+1) %></c:set>
			<c:set var="ranAll"><%= java.lang.Math.round(java.lang.Math.random() * 31+1) %></c:set>
			<c:if test="${not empty imgKind }">
			<img class="poster" id="poster" src="/resources/file/show_poster/${imgKind }${ran }.jpg"/><br>
			</c:if>
			<c:if test="${empty imgKind }">
			<img class="poster" id="poster" src="/resources/file/show_poster/${ranAll }.jpg"/><br>
			</c:if>
			${showList.showTitle} <br> ${showList.showDirector }
			</a>
			
			</div>
			</c:forEach>
		</div>
</div>

</div>

<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/show/search" method="get">
	<select id="searchtype" name="searchtype">
		<option value="showTitle">제목</option>
		<option value="hallName">공연장 이름</option>
	</select>
	<input type="text" id="keyword" name="keyword" placeholder="공연 제목을 입력하세요."/>
	<input type="hidden" id="kind" name="kind" value="${kindNo}"/>
	<button>검색</button>
</form>
</div>



<c:if test="${param.kindNo ne 0}">
<a href="<%=request.getContextPath() %>/show/memgenre?suggestkind=${kindNo}">asdasd</a>
</c:if>



<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />

</body>
</html>