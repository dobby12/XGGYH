<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        

<c:import url="/WEB-INF/views/layout/header.jsp" />
<script type="text/javascript">
$(document).ready(function(){
	var $slider_list = $("#slider li")
	console.log($slider_list)
	$slider_list.css("left", $("#sliderbox").css("width"))
	
	$slider_list.eq(0).css("left", 0)
	
	//--------------------------------------------------
	var sliderClick = function(){
		//#sliderbox에 클릭 이벤트 발생 시키기
		$("#sliderbox").click()
	}
	
	//3초마다 #sliderbox에 클릭 이벤트 발생 시키기
	var tid = setInterval(sliderClick, 3000)
	
	//--------------------------------------------------
	
	
	//현재 보여지는 슬라이드의 인덱스
	var curSlide = 0;
	$("#sliderbox").click(function(){
		
		//초기화하고 다시 3초 걸어주기
		clearInterval(tid)
		tid = setInterval(sliderClick, 3000)
		
		console.log("slider clicked")
		
		//다음에 보여지는 슬라이드의 인덱스
		var nextSlide = curSlide + 1
		nextSlide %= $slider_list.length
		
		console.log(curSlide, nextSlide)
		
		//현재 슬라이드 보여주기
		$slider_list.eq(curSlide).animate({"left" : "-=" + $("#sliderbox").css("width")})
		
		//다음 슬라이드 "준비하기"
		$slider_list.eq(nextSlide).css("left", $("#sliderbox").css("width"))
		
		//다음 슬라이드 보여주기
		$slider_list.eq(nextSlide).animate({"left" : "-=" + $("#sliderbox").css("width")})
		
		
		curSlide++
		curSlide %= $slider_list.length
	})

})
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
    height: 400px;
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

#posterSlider {
    width: 300px;
    height: 450px;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
}

.search {
	width: 240px;
	margin: 0 auto;
}

#sliderbox{
	width: 340px;
	height: 400px;
/* 	border: 1px solid red; */
	
	/* 외부 여백 */
	margin: 0 auto;
	
	/* div 영역을 벗어난 부분을 안 보이게 처리 */
	/* overflow: visible; /* 일단 보이게 해서 작업 후에 hidden으로 변경 */
	overflow: hidden; 
}

#slider{
	padding: 0;
	margin: 0;
	list-style-type: none;
	
	position: relative;
}
#slider li{
	position: absolute;
}


</style>


<div class="container">

<h2>${kindName}</h2>
<hr>

<%-- 이 아래 있는 영역은 지태가 작성한 코드인데, 로그인한 사용자의 genreno에 해당하는 공연만 보여주는 곳입니다. 로그인 하지 않았을 땐 보여지지 않습니다. --%>
<c:if test="${not empty loginIdGenreno }">

<h3><strong>${memnick}님을 위한 추천 공연</strong></h3><br>

<div class="main_list">
	<div class="list_start" id="sliderbox">
		<ul id="slider">
		<c:forEach items="${fiveShowList }" var="showList" varStatus="status"><li>
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
			<br><strong>${showList.showTitle}</strong><br>
			</a>
		</div>
		</li></c:forEach>
		</ul>
	</div>
</div>
<hr>
</c:if>
<%-- 이 위에 있는 영역은 지태가 작성한 코드인데, 로그인한 사용자의 genreno에 해당하는 공연만 보여주는 곳입니다. 로그인 하지 않았을 땐 보여지지 않습니다. --%>

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
			<br><strong>${showList.showTitle}</strong><br>
			${showList.showStart } ~ ${showList.showEnd }<br>
			</a>
			
			</div>
			</c:forEach>
		</div>
</div>

</div>

<div style="text-align: center; margin: 25px 0 0 0;" >
<form action="<%=request.getContextPath() %>/show/search" method="get">
	<input type="text" id="keyword" name="keyword" placeholder="공연 제목을 입력하세요."/>
	<input type="hidden" id="kindNo" name="kindNo" value="${kindNoToSearch}"/>
	<input type="submit" value="검색">
</form>
</div>

<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />
