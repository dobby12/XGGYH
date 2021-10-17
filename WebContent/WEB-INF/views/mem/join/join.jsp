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
	margin: auto;
}

.mem {
	font-weight: bold;
}

div.mb-3 {
	text-align: left;
}

.input-form {
	max-width: 680px;
	margin: 40px;
	padding: 32px;
	
	border: 1px solid #ddd;
	background-color: #fafafa;
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

button.btnBack {
	background: #F2F2F2;
	color: #595959;
}

button.btnSubmit {
	background: #608C5E;
	color: #f2f2f2;
}

button.btnDelete {
	background: #d99771;
	color: #f2f2f2;
}

button:hover { 
   background:#f2f2f2; 
   color:#D96459; 
} 

button.btnBack:hover {
	background: #595959;
	color: #F2F2F2;
}

button.btnSubmit:hover { 
   background:#f2f2f2; 
   color:#608C5E; 
} 

button.btnDelete:hover { 
   background:#f2f2f2; 
   color:#d99771;
} 

button:before,button:after { 
   content:''; 
   position:absolute; 
   top:0; 
   right:0; 
   height:2px; 
   width:0; 
   background: #d96459; 
   transition:400ms ease all; 
}

button.btnBack:before, button.btnBack:after {
  	background: #f2f2f2;
} 

button.btnSubmit:before, button.btnSubmit:after {
  	background: #608C5E; 
}
 
button.btnDelete:before, button.btnDelete:after {
  	background: #d99771;
} 

button:after, button.btnBack:after, button.btnDelete:after, button.btnSubmit:after { 
   right:inherit; 
   top:inherit; 
   left:0; 
   bottom:0; 
} 

button:hover:before,button:hover:after,
button.btnBack:hover:before, button.btnBack:hover:after,
button.btnDelete:hover:before, button.btnDelete:hover:after,
button.btnSubmit:hover:before, button.btnSubmit:hover:after { 
   width:100%; 
   transition:800ms ease all;
}

 
/* ------------------------------------------------ */

</style>

<script>
//아이디 형식 검사
function checkId() {
	$("#valid-feedback-id").css("display", "none");
	var memid = $("#memid");
	const idFormCheck = RegExp(/^[a-z0-9-_]{4,12}$/);
	
	if (idFormCheck.test(memid.val()) == false) {
		memid.addClass("is-invalid");
		memid.removeClass("is-valid");
		// memid.focus();
		
		$("#invalid-feedback-idcheck").css("display", "none");
		$("#invalid-feedback-id").css("display", "block");
		
		return false;
	} else {
		memid.addClass("is-valid");
		memid.removeClass("is-invalid");
		
		$("#invalid-feedback-idcheck").css("display", "none");
		$("#invalid-feedback-id").css("display", "none");
		
		return true;
	}
}

async function ajaxPost(url, data) {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: 'post',
			url: url,
			dataType: 'text',
			data: data,
			success: (result) => {
				resolve(result);
			},
			error: (error) => {
				reject(error);
			}
		})
	})
}

//아이디 중복 검사
async function checkIdExist() {
	var memid = $("#memid");
	
	if (checkId() == false) {
		return;
	}
	
	try {
		const result = await ajaxPost('/join/idcheck', {memid: memid.val()});
		
		if (result == "false") {
			//중복되는 아이디 없음
			memid.removeClass("is-invalid");
			memid.addClass("is-valid");
			
			$("#valid-feedback-id").css("display", "block");
			$("#invalid-feedback-idcheck").css("display", "none");
			$("#invalid-feedback-id").css("display", "none");
			
			//아이디 체크 패스
			return true;
		} else {
			// 중복되는 아이디 있음
			memid.removeClass("is-valid");
			memid.addClass("is-invalid");
			
			$("#valid-feedback-id").css("display", "none");
			$("#invalid-feedback-idcheck").css("display", "block");
			$("#invalid-feedback-id").css("display", "none");
			
			//아이디 체크 패스못함
			return false;
		}
	} catch (e) {
		console.log(e);
		alert("에러가 발생했습니다.");
	}
}

//이메일 형식 검사
function checkEmail() {
	$("#valid-feedback-email").css("display", "none");
	var memmail = $("#memmail");
	const emailFormCheck = RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*[.][a-zA-Z]{2,3}$/);
	
	if (emailFormCheck.test(memmail.val()) == false) {
		memmail.addClass("is-invalid");
		memmail.removeClass("is-valid");
		// memmail.focus();
		
		$("#invalid-feedback-emailcheck").css("display", "none");
		$("#invalid-feedback-email").css("display", "block");
		
		return false;
	} else {
		memmail.addClass("is-valid");
		memmail.removeClass("is-invalid");
		
		$("#invalid-feedback-emailcheck").css("display", "none");
		$("#invalid-feedback-email").css("display", "none");
		
		return true;
	}
}

//이메일 중복 검사
async function checkEmailExist() {
	var memmail = $("#memmail");
	
	if (checkEmail() == false) {
		return;
	}
	
	try {
		const result = await ajaxPost('/join/emailcheck', {memmail: memmail.val()});
		
		if (result == "false") {
			//중복되는 이메일 없음
			memmail.removeClass("is-invalid");
			memmail.addClass("is-valid");
			
			$("#valid-feedback-email").css("display", "block");
			$("#invalid-feedback-emailcheck").css("display", "none");
			$("#invalid-feedback-email").css("display", "none");
			
			//이메일 체크 패스
			return true;
		} else {
			// 중복되는 이메일 있음
			memmail.removeClass("is-valid");
			memmail.addClass("is-invalid");
			
			$("#valid-feedback-email").css("display", "none");
			$("#invalid-feedback-emailcheck").css("display", "block");
			$("#invalid-feedback-email").css("display", "none");
			
			//이메일 체크 패스못함
			return false;
		}
	} catch (e) {
		console.log(e);
		alert("에러가 발생했습니다.");
	}
}

//닉네임 공백 검사
function checkNick() {
	var memnick = $("#memnick");
	
	if (memnick.val() == "") {
		memnick.addClass("is-invalid");
		memnick.removeClass("is-valid");
		memnick.focus();
		
		return false;
	} else {
		memnick.addClass("is-valid");
		memnick.removeClass("is-invalid");
		
		return true;
	}
}

//비밀번호 공백 검사
function checkPw() {
	var mempw = $("#mempw");
	
	if (mempw.val() == "") {
		mempw.addClass("is-invalid");
		mempw.removeClass("is-valid");
		mempw.focus();
		
		return false;
	} else {
		mempw.addClass("is-valid");
		mempw.removeClass("is-invalid");
		
		return true;
	}
}

//비밀번호 확인 검사
function checkPw2() {
	var mempw = $("#mempw");
	var mempw2 = $("#mempw2");
	
	if (mempw.val() != mempw2.val()) {
		mempw2.addClass("is-invalid");
		mempw2.focus();

		return false;
	} else {
		if (mempw2.val() == "") {
			mempw2.removeClass("is-valid");
			mempw2.removeClass("is-invalid");
			
			return false;
		} else {
			mempw2.removeClass("is-invalid");
			mempw2.addClass("is-valid");
			
			return true;
		}
	}
}

//가입 버튼 눌렀을 때
async function checked() {
	var isvalid = true;
	
 	if (checkEmail() == false) isvalid = false;
	else if (await checkEmailExist() == false) isvalid = false;
	else $("#valid-feedback-email").css("display", "block");
	
	if (checkPw2() == false) isvalid = false;
	
	if (checkPw() == false) isvalid = false;
	
	if (checkNick() == false) isvalid = false;
	
	if (checkId() == false) isvalid = false;
 	else if (await checkIdExist() == false) isvalid = false;
	else $("#valid-feedback-id").css("display", "block");
	
	if($("input:checkbox[id='aggrement']").is(":checked") != true){
		  alert('개인정보 수집 및 이용에 동의하셔야 회원 가입이 완료됩니다.');
		  return;
	}
	
	return isvalid;
}

async function submit() {
	if (await checked() == true) {
		var formData = $("#join-form").serialize();
		
		$.ajax({
			type: 'post',
			url: '/join',
			dataType: 'text',
			data: formData,
			success: function(result) {
				// 회원가입 성공
				if (result == "true") {
					alert("회원가입에 성공했습니다!");
					
					// 리다이렉션
					window.location.replace("/main");
				} else {
					// 회원가입 실패
					alert("회원가입에 실패했습니다.");
				}
			},
			error: function(xhr, status, error) {
				alert(error);
			}
		})
	}
}

$(document).ready(function() {
	//취소 버튼 누르면 뒤로가기
	$("#btnBack").click(function() {
		history.go(-1);
	})
	
	//아이디 중복체크
	$("#memid").blur(function() {
		checkIdExist();
	})
	
	//이메일 중복체크
	$("#memmail").blur(function() {
		checkEmailExist();
	})
	
	//가입 버튼 클릭 시 form submit
	$("#btnSubmit").click(function() {
		//가입 버튼 눌렀을 때
		submit();
	})
});

//관심사 체크박스 하나만 선택
function checkOnlyOne(element) {
	const checkboxes = document.getElementsByName("genreno");
	checkboxes.forEach((cb) => {cb.checked = false;
	})
	element.checked = true;
}
</script>

</head>
<body>

<div class="container">
	<div class="input-form-backgroud row">
	<div class="input-form col-md-6 mx-auto">
		<a href="<%=request.getContextPath() %>/main"><img id="logo_header" class="logoA" src="/resources/file/logo_line.png" /></a>
		<p></p>
		<!-- <h5 style="text-align: center;"><label>회원가입</label></h5>  -->
		<!--  <form action="<%=request.getContextPath() %>/join" method="post" onsubmit="return checked()">-->
		<form id="join-form">
			<div class="mb-3">
				<label class="mem" for="memid">아이디</label><span class="text-muted">&nbsp;(4~12자리의 영문 소문자, 숫자, 특수문자 [_,-]만 입력 가능합니다.)</span>
				<input type="text" class="form-control" id="memid" name="memid" oninput="checkId()">
				<div class="valid-feedback" id="valid-feedback-id" style="display: none;">사용 가능한 아이디입니다.</div>
				<div class="invalid-feedback" id="invalid-feedback-id">아이디를 형식에 맞춰서 입력해주세요.</div>
				<div class="invalid-feedback" id="invalid-feedback-idcheck" style="display: none;">이미 존재하는 아이디입니다.</div>
			</div>
			
			<div class="mb-3">
				<label class="mem" for="memnick">닉네임</label>
				<input type="text" class="form-control" id="memnick" name="memnick" oninput="checkNick()">
				<div class="invalid-feedback">닉네임을 입력해주세요.</div>
			</div>
			
			<hr>
			
			<div class="mb-3">
				<label class="mem" for="mempw">비밀번호</label>
				<input type=password class="form-control" id="mempw" name="mempw" oninput="checkPw()">
				<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
			</div>
			
			<div class="mb-3">
				<label class="mem" for="mempw2">비밀번호 확인</label>
				<input type=password class="form-control" id="mempw2" name="mempw2" oninput="checkPw2()">
				<div id="mempw2-invalid-feedback" class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
			</div>
			
			<hr>
			
			<div class="row">
			<div class="col-md-6 mb-3">
				<label class="mem" for="memmail">이메일</label>
				<input type="email" class="form-control" id="memmail" name="memmail" placeholder="you@example.com" oninput="checkEmail()">
				<div class="valid-feedback" id="valid-feedback-email" style="display: none;">사용 가능한 이메일입니다.</div>
				<div class="invalid-feedback" id="invalid-feedback-email">이메일을 형식에 맞춰서 입력해주세요.</div>
				<div class="invalid-feedback" id="invalid-feedback-emailcheck">이미 존재하는 이메일입니다.</div>
			</div>

			<div class="col-md-6 mb-3">
				<label class="mem" for="memstate">이메일 수신 여부</label>
					<div class="row" style="position: absolute; top: 40px;">
					<div class="col-md-6 mb-3"><input type="radio" name="memstate" value="y" checked><span class="text-muted">&nbsp;수신</span></div>
					<div class="col-md-6 mb-3"><input type="radio" name="memstate" value="n"><span class="text-muted">&nbsp;거부</span></div>
					</div>
			</div>
			</div>
			
			<hr>

			<div class="mb-3">
				<label class="mem" for="genreno">관심사</label><span class="text-muted">&nbsp;(좋아하는 장르를 선택해주세요.)</span>
					<div class="row" >
					<div class="col-md-3"><input type="checkbox" name="genreno" value="1" onclick='checkOnlyOne(this)' checked><span class="text-muted">&nbsp;코미디</span></div>
					<div class="col-md-3"><input type="checkbox" name="genreno" value="2" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;호러</span></div>
					<div class="col-md-3"><input type="checkbox" name="genreno" value="3" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;가족</span></div>
					<div class="col-md-3"><input type="checkbox" name="genreno" value="4" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;로맨스</span></div>
					</div>
					<div class="row" >
					<div class="col-md-3"><input type="checkbox" name="genreno" value="5" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;스포츠</span></div>
					<div class="col-md-3"><input type="checkbox" name="genreno" value="6" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;드라마</span></div>
					<div class="col-md-3"><input type="checkbox" name="genreno" value="7" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;어드벤처</span></div>
					<div class="col-md-3"><input type="checkbox" name="genreno" value="8" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;미스테리</span></div>
					</div>
			</div>
			
			<hr class="mb-4">
			<div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" style="font-weight: bold;" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          </div>
          
			<div class="mb-4"></div>
			<button type="button" id="btnSubmit" class="btnSubmit">가입</button>
			<button type="button" id="btnBack" class="btnBack">취소</button>
		</form>
	</div>
	</div>
</div>

</body>
</html>