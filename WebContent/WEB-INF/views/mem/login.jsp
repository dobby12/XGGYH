<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://apis.google.com/js/platform.js" async defer></script>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>공연의 모든 것, 공공연히</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 3 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<!-- 웹 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">

<style type="text/css">

body {
	min-height: 100vh;
	background-color: #FFFFFF;
	text-align: center;
	font-family: 'IBM Plex Sans KR', sans-serif;
}

.logoA {
	width: 150px;
	margin: 10 auto 30 0;
}

div.mb-3 {
	text-align: left;
}

.input-form {
	max-width: 500px;
	height:450px; 
	position:absolute;
	top:0; left:0; bottom:0; right:0;
	margin:10% auto;
	padding: 32px;
	
	border: 1px solid #ddd;
	background-color: #fafafa;
}

.form-control {
	width: 300px;
	margin: auto;
}

.kakaologin {
	width: 300px;
	margin: auto;
}

.find-join {
	margin: 10 auto 10;
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

</style>

</head>
<body>

<!------------------------------------------------------>
<div class="container">
	<div class="input-form-backgroud row">
	<div class="input-form col-md-6 mx-auto">

<%-- 로그인 실패를 전달 받았을 때 --%>
<%-- <c:if test="${loginfail }">
@@@로그인에 실패하였습니다. 아이디와 패스워드를 확인하세요.
</c:if> --%>

<%-- 로그인이 아닐 때 --%>
<c:if test="${empty login }">
<a href="<%=request.getContextPath() %>/main"><img id="logo_header" class="logoA" src="/resources/file/logo_line.png" /></a>
<p>
<form action="<%=request.getContextPath() %>/login" method="post">
<input type="hidden" name="kakaoemail" value="" />

	
<%-- 로그인을 요청한 페이지 == 로그인 후 돌아갈 페이지 --%>
	<input type="hidden" name="ref" value="${header.referer }" />
	<div class="mb-3">
		<input type="text" class="form-control" id="memid" name="memid" placeholder="ID" />
	</div>
	<div class="mb-3">
		<input type="password" class="form-control" id="mempw" name="mempw" placeholder="PASSWORD" />
	</div>
	
	<div>
	<button style="width: 300px; height: 45px; margin: 0 auto;">로그인</button>
	</div>
	<%-- 카카오 로그인 --%>
	<div>
	<a href="javascript:void(0)"><img onclick="kakaoLogin();" id="btnSubmit" class="kakaologin" src="/resources/file/kakao_login_medium_wide.png" ></a> 
	</div>
	
	<%-- 아이디 비밀번호 찾기 --%>
	<div class="find-join">
	<a href="<%=request.getContextPath() %>/member/find"><span style="color:#888;">아이디 찾기</span></a>ㅣ
	<a href="<%=request.getContextPath() %>/member/find"><span style="color:#888;">비밀번호 찾기</span></a>ㅣ
	<a href="<%=request.getContextPath() %>/join"><span style="color:#888;">회원가입</span></a>
	</div>
</form>
</c:if>
<%-- 로그인 상태일 때 /login 입력해서 접근 시 메인으로 리디렉션 --%>
<c:if test="${login }"><script>location.href="/";</script></c:if>
	</div>
	</div>
</div>

<!-- .container end -->




<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('8b6ff5cc45fa05d18d5d5a27810f38f8'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
        	  var kakaoemail = response.kakao_account.email;
        	  console.log(kakaoemail);
        	  $('input[name=kakaoemail]').attr('value', kakaoemail);
        	  $("form").submit();
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
<%-- 카카오 로그아웃 (일반 로그아웃을 사용하므로 필요 없을 듯)
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        	location.href="/";
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }
--%>
</script>

</body>
</html>