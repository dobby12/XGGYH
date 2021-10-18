<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        

<c:import url="/WEB-INF/views/layout/header.jsp" />

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
    height: 360px;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
}

.search {
	width: 240px;
	margin: 0 auto;
}

</style>


<div class="container">

<h2>${kindName}</h2>
<hr>

<%-- 이 아래 있는 영역은 지태가 작성한 코드인데, 로그인한 사용자의 genreno에 해당하는 공연만 보여주는 곳입니다. 로그인 하지 않았을 땐 보여지지 않습니다. --%>
<c:if test="${not empty loginIdGenreno }">
<div class="main_list">
	<div class="list_start">
		<c:forEach items="${fiveShowList }" var="showList" varStatus="status">
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
			<br><strong>${showList.showTitle}</strong><br><br>
			</a>
		</div>
		</c:forEach>
	</div>
</div>
<hr>
</c:if>
<%-- 이 위에 있는 영역은 지태가 작성한 코드인데, 로그인한 사용자의 genreno에 해당하는 공연만 보여주는 곳입니다. 로그인 하지 않았을 땐 보여지지 않습니다. --%>

<h2> ${kindName} 게시판 </h2>
<div class="main_list">
		<div class="list_start">
			<c:forEach items="${showList }" var="showList" varStatus="status">
			<div class="list_detail" style="padding: 0 0 10px 0;">
			
			<a href="<%=request.getContextPath() %>/show/detail?showNo=${showList.showNo }">
			<c:set var="ran"><%= java.lang.Math.round(java.lang.Math.random() * 7+1) %></c:set>
			<c:set var="ranAll"><%= java.lang.Math.round(java.lang.Math.random() * 31+1) %></c:set>
			<c:if test="${not empty imgKind }">
			<img class="poster" id="poster" src="/resources/file/show_poster/${imgKind }${ran }.jpg"/><br>
			</c:if>
			<c:if test="${empty imgKind }">
			<img class="poster" id="poster" src="/resources/file/show_poster/${ranAll }.jpg"/><br>
			</c:if>
			<br><strong>${showList.showTitle}</strong><br><br>
			</a>
			
			</div>
			</c:forEach>
		</div>
</div>

</div>

<div style="text-align: center; margin: 25px 0 0 0;" >
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
