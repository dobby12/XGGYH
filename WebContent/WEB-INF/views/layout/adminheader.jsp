<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

<!-- 웹 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">

<style type="text/css">

body, ul.nav > li > a  {
	font-family: 'IBM Plex Sans KR', sans-serif;
}

a {
	color: black;
}

a:hover {
	color: #D96459;
}

#header {
	background: #f2f2f2;
}

/* 메인 메뉴 - 1depth */
ul.nav {
	/* ul태그의 기본 리스트스타일 없애기 */
	list-style-type: none;
	
	/* 기본 여백 제거 */
	padding: 0;
	margin: 0;	
}

/* ------------------------------------------------ */

/* 메인 메뉴의 항목 */
ul.nav > li {

	/* 수평으로 일렬 배치하기 */
	float: left;

	/* 줄간격을 이용한 높이 조절 */
	line-height: 40px;
	
	/* 테두리를 이용한 메뉴 항목 구분선 만들기 */
	border-right: 1px solid #f2f2f2;
}

/* 메인 메뉴의 항목 텍스트 */
ul.nav > li > a {
	/* 글자 색상 */
	color: black;
	
	/* 글자 꾸밈선 제거(underline, 밑줄 제거) */
	text-decoration: none;
	
	/* 글자 스타일 지정 */
	font-size: 15px; /* 크기 */
	font-weight: bold; /* 굵게 */
	
	/* 내부여백 */
	/* 	-> a태그의 내부여백은 링크 클릭 가능한 영역 */
	padding: 10px 15px;
	
	/* 외부여백 */
	/* 	-> a태그의 외부여백은 링크 클릭 불가능한 영역 */
	margin: 0 3px;

}

ul.nav > li > a:hover {
	text-decoration:none; color:#D96459;
	background-color: #f2f2f2;
}

</style>

</head>
<body>

<div id="header">
<ul class="nav">
	<li><a href="#">공지사항</a>
	<li><a href="#">1:1문의</a></li>
	<li><a href="#">회원 관리</a></li>
	<li><a href="#">리뷰 관리</a></li>
	<li><a href="#">공연 관리</a></li>
	<li><a href="#">메일 관리</a></li>
</ul>
</div>