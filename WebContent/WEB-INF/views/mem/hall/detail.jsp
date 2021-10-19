<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script>
//8b6ff5cc45fa05d18d5d5a27810f38f8

$(document).ready(function(){
	
	$("#btnBack").click(){
		
		history.go(-1);
	}
})

</script>

<style type="text/css">

.item {
	width: 15%;
}

#info {
	width: 600px;
	height: 400px;
	float: right;
	margin: 0 auto;
}

table {
	width: 100%;
	height: 100%;
}

</style>

<div class="container">

<h2>공연장 정보 페이지</h2>
<hr>
<div id="info">
<table class="table table-bordered table-condensed">

<tr>
	<td class="item">공연장 이름</td>
	<td>${hallInfo.hallName }</td>
</tr>

<tr>
	<td class="item">주소</td>
	<td>${hallInfo.hallAddress }</td>
</tr>

<tr>
	<td class="item">전화번호</td>
	<td >${hallInfo.hallCall }</td>
</tr>

<tr>
	<td class="item">홈페이지</td>
	<td ><a href="${hallInfo.hallLink}">${hallInfo.hallLink}</a></td>
</tr>

<tr>
	<td class="item">지역</td>
	<td>${hallInfo.hallLocation }</td>
</tr>

</table>
</div>

<%-- 지도 영역입니다. 좋은 자리 찾아서 넣어주세요. --%>
<div id="map" style="width:500px; height:400px; float: left;"></div>

<div style="width: 100%; height: 30px; background: #fff; clear: both;"></div>
<button class="btnBack" id="btnBack" onclick="history.go(-1)">이전으로 돌아가기</button>
</div><!-- .container end -->
<%-- 카카오 지도 api --%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8b6ff5cc45fa05d18d5d5a27810f38f8&libraries=services,clusterer,drawing"></script>

<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//주소로 좌표를 검색합니다
geocoder.addressSearch('${hallInfo.hallAddress }', function(result, status) {

// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });

    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">${hallInfo.hallName }</div>'
    });
    infowindow.open(map, marker);

    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
} 
});

</script>
<%-- 카카오 지도 api --%>

<c:import url="/WEB-INF/views/layout/footer.jsp" />