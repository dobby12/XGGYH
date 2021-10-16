<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script>
//8b6ff5cc45fa05d18d5d5a27810f38f8

</script>

<div class="container">

<h1>공연장 정보 페이지</h1>
<hr>

<table class="table table-bordered">

<tr>
<td class="info">공연장 이름</td><td >${hallInfo.hallName }</td>
</tr>

<tr>
<td class="info">공연장 주소</td><td >${hallInfo.hallAddress }</td>
</tr>

<tr>
<td class="info">공연장 전화번호</td><td >${hallInfo.hallCall }</td>
</tr>

<tr>
<td class="info">공연장 링크</td><td > <a href="${hallInfo.hallLink}">${hallInfo.hallLink}</a> </td>
</tr>

<tr>
<td class="info">공연장 위치</td><td >${hallInfo.hallLocation }</td>
</tr>

<tr>
<td class="info">공연장 오시는길</td><td >${hallInfo.hallNavigation }</td>
</tr>

</table>

<%-- 지도 영역입니다. 좋은 자리 찾아서 넣어주세요. --%>
<div id="map" style="width:500px;height:400px;"></div>

</div>
<%-- 카카오 지도 api --%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8b6ff5cc45fa05d18d5d5a27810f38f8&libraries=services,clusterer,drawing"></script>

<script>
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

//마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

</script>
<%-- 카카오 지도 api --%>

</body>
</html>