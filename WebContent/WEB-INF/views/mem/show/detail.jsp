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

.poster {
	width: 550px;
	height: 610px;
	border: 1px solid;
	float: left;
}

.detailButton {
	width: 200px;
	height: 200px;
	float: right;
	clear: both;
}
</style>

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
		
		else{
			if (isJjimed == false) {
				alert('찜 목록에서 삭제 되었습니다.');
				location.href = '/mem/jjim/delete?showNo=' + '${showDetail.showNo}'
						+ '&memId=' + '${memid}';
				//${isJjim}.setAttribute(true);
				window.history.back();
			} else {
				alert('찜하지 않은 공연입니다.');
			}
		}
	}
</script>

</head>
<body>

	<c:import url="/WEB-INF/views/layout/header.jsp" />

	<div class="detailButton">
		<a href="<%=request.getContextPath()%>/review/list">공연 리뷰 더보기</a>
		<button
			onclick="location.href='<%=request.getContextPath()%>/show?kindNo=${showDetail.kindNo}';">공연목록</button>
		<button
			onclick="location.href='<%=request.getContextPath()%>/review/write?showNo=${showDetail.showNo}';">리뷰
			작성</button>
		<button onclick="insertJjim();">이 공연 찜하기</button>
		<button onclick="deleteJjim();">찜한 공연 삭제하기</button>
	</div>

	<div class="container">

		<h1>공연 상세 정보( ${showDetail.showTitle} )</h1>
		<hr>

		<div class="photoEx">
			<img class="poster"
				src='http://drive.google.com/uc?export=view&id=1UCDamPPObCPN9BY8Iz2WjsgiY8m80K2b' />
			<br>
		</div>

		<div class="detailInfo">
			공연 번호 : ${showDetail.showNo} <br> 카테고리 : ${showKindName} <br>
			장르 : ${showGenreName} <br> 공연장 : <a
				href="<%=request.getContextPath() %>/hall/detail?hallNo=${showDetail.hallNo }">
				${showHallName} </a> <br> 작성일 : ${showDetail.showDate} <br> 연령
			제한 : ${showDetail.showAge} <br> 감독 : ${showDetail.showDirector}
			<br> 출연 배우 : ${showDetail.showActor} <br> 상연 시작일 :
			${showDetail.showStart} <br> 상연 종료일 : ${showDetail.showEnd} <br>
		</div>

		<div class="content">${showDetail.showContent }</div>
	</div>

	<c:import url="/WEB-INF/views/layout/footer.jsp" />

</body>

</html>