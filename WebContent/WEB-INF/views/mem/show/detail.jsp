<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	if('${alert}'!=''){
		alert('${alert}')
		location.href='/show/detail?showNo=${showDetail.showNo}'
	}
	
	if(${isJjim == true}){
		$("#insertJjim").show();
		$("#deleteJjim").hide();
	} else {
		$("#insertJjim").hide();
		$("#deleteJjim").show();
	}

})

function imgSrc(){
	var imgSrc;
	var kind = '${showKindName}'
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


$(document).ready(function(){
	putImg(); //first image
})
</script>


<script type="text/javascript">
	function insertJjim() {
		var isJjimed = ${isJjim};

		if (<%=session.getAttribute("login")%> == null) {
			alert("로그인 후 이용 가능한 서비스입니다. 로그인 해주세요.");
			location.href = '/login';
		} else {
			if (isJjimed == true) {
				
				alert('찜 목록에 추가 되었습니다.');
				location.href = '/mem/jjim?showNo=' + '${showDetail.showNo}'
						+ '&memId=' + '${memid}';
			} else {
				alert('이미 찜한 공연입니다.');
			}
		}
	}

	function deleteJjim() {
		var isJjimed = ${isJjim};
		if (<%=session.getAttribute("login")%> == null) {
			alert("로그인 후 이용 가능한 서비스입니다. 로그인 해주세요.");
			location.href = '/login';
		}

		else {
			if (isJjimed == false) {
				alert('찜 목록에서 삭제 되었습니다.');
				location.href = '/mem/jjim/delete?showNo='
						+ '${showDetail.showNo}' + '&memId=' + '${memid}' + '&from=detail';

			} else {
				alert('찜하지 않은 공연입니다.');
			}
		}
	}
</script>


<style type="text/css">

#info {
	width: 800px;
	height: 100%;
	margin: 0 auto;
	padding: 20px;
}

#detailInfo1 {
	width: 450px;
	height: 450px;
	float: right;
	background: #f2f2f2;
	padding: 20px;
	overflow: auto;
}

table {
	width:400px;
	height: 400px;
}


#content {
	width: 100%;
	height: 250px;
 	clear: both;
	background: #f2f2f2;
	
	overflow: auto;
	padding: 20px;
	vertical-align: middle;
	text-align: center;
}

#detailInfo2 {
	width: 360px;
 	height: 205px;
 	overflow: auto;
 	float:left;
 	border-right: 1px solid #ccc;
}

#reviews {
	width: 350px;
	height: 180px;
	overflow: auto;
	float: right;
}

#rev {
	width: 90%;
	height: 50px;
	background: #fff;
}

#link {
	width: 350px;
	height: 20px;
	margin-top: 5px;
	float: right;
}

#link > a {
	color: #d96459;
}

.photoEx {
	width: 300px;
	height: 450px;
	float: left;
}

.poster {
	width: 300px;
	height: 450px;
}

</style>

<div class="container">

 	<h2>공연 상세 정보( ${showDetail.showTitle} )</h2>
	<hr>

<div id="info"><!-- 폭 줄여주는 용도 -->

<!-- 포스터 -->
<div class="photoEx">
		<img class="poster" id="poster" src="" />
</div>

<!-- 공연 정보 -->
<div id="detailInfo1">
	<table>
		<tr>
			<td>공연 번호 : </td><td style="text-align: left;">${showDetail.showNo}</td>
		</tr>
		<tr>
			<td>카테고리 : </td><td style="text-align: left;">${showKindName}</td>				
		</tr>
		<tr>
			<td>장르 : </td><td style="text-align: left;">${showGenreName}</td> 		
		</tr>
		<tr>
			<td>공연장 : </td><td style="text-align: left;"><a href="<%=request.getContextPath() %>/hall/detail?hallNo=${showDetail.hallNo }" style="color: #d96459;">${showHallName} </a></td>
		</tr>
		<tr>
			<td>연령 제한 : </td><td style="text-align: left;">${showDetail.showAge }</td>
		</tr>
		<tr>
			<td>감독 : </td><td style="text-align: left;">${showDetail.showDirector }</td>
		</tr>
		<tr>
			<td>출연 배우 : </td><td style="text-align: left;">${showDetail.showActor }</td>
		</tr>
		<tr>
			<td>공연 기간 : </td><td style="text-align: left;">${showDetail.showStart } ~ ${showDetail.showEnd }</td>
		</tr>
	</table>
</div>
<div style="width: 100%; height: 7px; background: #fff; clear:both;" ></div><!-- 간격 넓힘 -->

<div id="content">
<div id="detailInfo2">
	<strong><span class="glyphicon glyphicon-star" aria-hidden="true"></span>${showScoreAvg }</strong>
	<hr>
	<br>
	${showDetail.showContent }
</div><!-- detailInfo2 end -->


<div id="reviews">
<c:forEach items="${reviewList}" var="review">
<table id="rev" class="table table-condensed">
	<tr>
		<td><a href="<%=request.getContextPath() %>/review/detail?reviewno=${review.reviewNo }">${review.reviewTitle }</a></td>
		<td>${review.memId }</td>
	</tr>
	<tr>
		<td colspan="2">
		<c:if test="${review.reviewScore == 1 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if>
		<c:if test="${review.reviewScore == 2 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if>
		<c:if test="${review.reviewScore == 3 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if>
		<c:if test="${review.reviewScore == 4 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if>
		<c:if test="${review.reviewScore == 5 }">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</c:if></td>
	</tr>
</table>
</c:forEach>

</div><!-- #reviews end -->
<div id="link"><a href="<%=request.getContextPath()%>/review/list">공연 리뷰 더보기</a></div>

</div><!-- #content end -->

</div><!-- #info end -->

<div class="detailButton">
	<button class="btnBack" onclick="location.href='<%=request.getContextPath()%>/show?kindNo=${showDetail.kindNo}';">공연목록</button>
	<button onclick="location.href='<%=request.getContextPath()%>/review/write?showNo=${showDetail.showNo}';">리뷰 작성</button>
	
	
	<button class="btnDelete" id="insertJjim" onclick="insertJjim();">공연 찜하기</button>
	<button class="btnDelete" id="deleteJjim" onclick="deleteJjim();">찜 삭제하기</button>
</div><!-- .detailButton end -->

</div><!-- .container end -->


<c:import url="/WEB-INF/views/layout/footer.jsp" />
