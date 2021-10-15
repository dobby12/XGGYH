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

/* 기본 css */

html, body { 
	margin: 0; 
	padding: 0;
	height: 100%;
}

#body-wrapper { min-height: 90%; position: relative; }

#body-content { margin-top: 0px; padding-bottom: 210px; /* footer의 높이 */ }

#footer {
	width: 100%;
	height: 210px; /* footer의 높이 */
	position: absolute;
	bottom: 0;
	left: 0;
	padding: 30px;
	display: block;
	text-align: center;
	background-color: #F2F2F2;
	margin: 25px 0 0 0;
}

body, ul.nav > li > a  {
	font-family: 'IBM Plex Sans KR', sans-serif;
}

#header {

	postion: absolute;
	
	background: #f2f2f2;
	height: 60px;
	width: 1920x;
	display: block;
	text-align: center;
	margin: 0 0 25px 0;
}

#logo_header {
	width: 90px;
	height: 30px;
}


#logo_footer {
	height: 120px;
	width: 120px;
	
	float: left;
	margin: 10px 0 0 300px;
}


/* ------------------------------------------------ */
/* 네비게이션 (header-menu) */

/* 메인 메뉴 - 1depth */
ul.nav {
	/* ul태그의 기본 리스트스타일 없애기 */
	list-style-type: none;
	
	/* 기본 여백 제거 */
	padding: 0;
	margin: 0 0 0 200px;	
}


/* 메인 메뉴의 항목 */
ul.nav > li {

	/* 서브메뉴(absolute)의 위치 기준점 설정하기 */
	position: relative;
	
	/* 수평으로 일렬 배치하기 */
	float: left;

	/* 줄간격을 이용한 높이 조절 */
	line-height: 35px;
	
	/* 테두리를 이용한 메뉴 항목 구분선 만들기 */
/* 	border-right: 1px solid #f2f2f2; */
	
	text-align: center;
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
	
	text-align: center;

}


/* 서브 메뉴 */
ul.nav > li > ul {
	/* HTML계층구조(레이아웃)에서 빼내는 설정 */
	/*  -> 부모요소인 <li>태그의 컨텐츠 영역에서 빠져나옴 */
	position: absolute;
	
	/* ul태그 기본 리스트스타일 제거하기 */
	list-style-type: none;
	
	/* ul태그의 기본 여백 제거 */
	padding: 0;
	margin: 0;
	
	/* 서브메뉴영역의 너비 */
	width: 100px;
	
	text-align: center;
}

/*  */
ul.nav > li > a:hover {
	text-decoration: none;
	color:#D96459;
	background-color: #f2f2f2;
	text-decoration:none;
 	border-bottom: 2px solid #D96459; 
 	padding-bottom:3px; 

}

#logoA > a:hover, #logoA> a:before,#logoA> a:after {
 	border-bottom: 0px; 
 	padding-bottom:3px; 
}

#logoA {
	margin-right: 30px;
}


ul.nav > li > a:before {
	content: '';
	border-bottom: 2px solid #D96459;
	padding-bottom:3px;
	bottom: 0; left: 0;
	width: 100%;
	
	opacity: 0;
}

ul.nav > li > a:hover:before {
	opacity: 1.0;
}

ul.nav > li > a, ul.nav > li > a:before {
	transition: all 0.2s ease;
	

}

/* 서브 메뉴의 항목 */
ul.nav > li > ul > li {
	/* 배경색 지정 */
	background: #f2f2f2;
	
	/* 항목의 크기 키우기 */
/* 	padding: 10px; */

	/* 왼쪽 여백 - 들여쓰기 효과주기 */
/* 	padding-left: 10px; */
	
	/* 평소에 화면에서 안보이도록 설정하기 */
	height: 0; /* 컨텐츠영역 높이 제거 */
	font-size: 0; /* 글자 크기 제거 */
	line-height: 0; /* 줄간격 제거 */
	
	text-align: center;
	
	list-style-type: none;
	
}

/* 메인메뉴 항목에 마우스 올릴 경우 다시 보이도록 설정 */
ul.nav > li:hover > ul > li {
	height: 30px; /* 컨텐츠영역 높이 되돌리기 */
	font-size: 12px; /* 글자 크기 되돌리기 */
	line-height: 40px; /* 줄간격 되돌리기 */
	
	
}

ul.nav > li > ul > li > a {
	/* 배경색 지정 */
	background: #f2f2f2;
	
	/* 글자색 지정 */
	color: #333;
	
	/* 글자 꾸밈선 제거(underline) */
	text-decoration: none;
	
	/* 부모요소 <li>항목만큼 영역을 차지하는 태그로 만들기 */
	/*  -> <a>태그가 부모<li>태그로 가득 채워짐 */
	display: block;

}

ul.nav > li > ul > li > a:hover {
	text-decoration:none; color:#D96459;
	background-color: #f2f2f2;
}


/* ------------------------------------------------ */
/* 버튼 */

button { 
	background:#D96459; 
	color:#fff; 
	border:none; 
	position:relative; 
	height:30px; 
	font-size: 1em; 
	padding:0 1em; 
	cursor:pointer; 
	transition:800ms ease all; 
	outline:none;
	border-radius: 5px;
} 
button:hover{ 
   background:#f2f2f2; 
   color:#D96459; 
} 
button:before,button:after{ 
   content:''; 
   position:absolute; 
   top:0; 
   right:0; 
   height:2px; 
   width:0; 
   background: #d96459; 
   transition:400ms ease all; 
}
  
button:after { 
   right:inherit; 
   top:inherit; 
   left:0; 
   bottom:0; 
} 

button:hover:before,button:hover:after { 
   width:100%; 
   transition:800ms ease all;
}
 
 
/* ------------------------------------------------ */
/* body */

.container {
	text-align: center;
}

input[type=text] {
	border: solid 1px #d96459; 
    border-radius: 5px;
    height: 30px;
    vertical-align: middle;
    text-align: center;
}

/* 테이블 */

table {
	text-align: center;
	border-collapse: collapse;
	margin: 0 auto;
	width: 1000px;
}

td, th, textarea {
	text-align: center;
	vertical-align: middle;
}

a {
	color: black;
}

a:hover {
	color: #D96459;
}

</style>

</head>
<body class="pt-5">

<div id="header" class="fixed-top">
<ul class="nav">
	<li id="logoA"><a href="<%=request.getContextPath() %>/admin"><img id="logo_header" src="/resources/file/logo_line.png" /></a></li>
	<li><a href="<%=request.getContextPath() %>/admin/notice/list">공지사항</a>
	<li><a href="<%=request.getContextPath() %>/admin/ask/list">1:1문의</a></li>
	<li><a href="<%=request.getContextPath() %>/admin/mem/list">회원 관리</a></li>
	<li><a href="<%=request.getContextPath() %>/admin/review/list">리뷰 관리</a></li>
	<li><a href="<%=request.getContextPath() %>/admin/show/list">공연 관리</a></li>
	<li>
		<a href="#">메일 관리</a>
		<ul>
			<li><a href="<%=request.getContextPath() %>/admin/mail/write">메일 발송</a></li>
			<li><a href="<%=request.getContextPath() %>/admin/mail/mem/list">회원 메일 발송</a></li>
		</ul>
	</li>
</ul>
</div>

<div id="body-wrapper">
<div id="body-content">