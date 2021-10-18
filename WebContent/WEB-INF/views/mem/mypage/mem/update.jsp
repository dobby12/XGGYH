<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- 부트스트랩 3 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script type="text/javascript">
var memmail_prior = "";

//요청 보내기
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

//닉네임 공백 검사
function checkNick() {
	var memnick = $("#memnick");
	
	if (memnick.val() == "") {
		memnick.addClass("is-invalid");
		memnick.removeClass("is-valid");
		
		return false;
	} else {
		memnick.addClass("is-valid");
		memnick.removeClass("is-invalid");
		
		return true;
	}
}

//닉네임 중복 검사
async function checkNickExist() {
	var memnick = $("#memnick");
	
	if (checkNick() == false) {
		return;
	}
	
	//현재 사용하는 닉네임도 가능하게 해주기
	if (memnick_prior == memnick.val()) {
		//중복되는 닉네임 없음
		memnick.removeClass("is-invalid");
		memnick.addClass("is-valid");
		
		$("#valid-feedback-nick").css("display", "block");
		$("#invalid-feedback-nickcheck").css("display", "none");
		$("#invalid-feedback-nick").css("display", "none");
		
		return true;
	}
	
	try {
		const result = await ajaxPost('/join/nickcheck', {memnick: memnick.val()});
		
		if (result == "false") {
			//중복되는 닉네임 없음
			memnick.removeClass("is-invalid");
			memnick.addClass("is-valid");
			
			$("#valid-feedback-nick").css("display", "block");
			$("#invalid-feedback-nickcheck").css("display", "none");
			$("#invalid-feedback-nick").css("display", "none");
			
			//닉네임 체크 패스
			return true;
		} else {
			// 중복되는 닉네임 있음
			memnick.removeClass("is-valid");
			memnick.addClass("is-invalid");
			
			$("#valid-feedback-nick").css("display", "none");
			$("#invalid-feedback-nickcheck").css("display", "block");
			$("#invalid-feedback-nick").css("display", "none");
			
			//이메일 체크 패스못함
			return false;
		}
	} catch (e) {
		console.log(e);
		alert("에러가 발생했습니다.");
	}
}

//비밀번호 공백 검사
function checkPw() {
	var mempw = $("#mempw");
	
	if (mempw.val() == "") {
		mempw.addClass("is-invalid");
		mempw.removeClass("is-valid");
		
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

//수정 버튼 눌렀을 때
async function checked() {
	var isvalid = true;
	
	if (checkPw2() == false) isvalid = false;
	
	if (checkPw() == false) isvalid = false;
	
	if (checkNick() == false) isvalid = false;
	else if (await checkNickExist() == false) isvalid = false;
	else $("#valid-feedback-nick").css("display", "block");
	
	return isvalid;
}

async function submit() {
	if (await checked() == true) {
		var formData = $("#update-form").serialize();
		
		$.ajax({
			type: 'post',
			url: '/mypage/myinfo/update',
			dataType: 'text',
			data: formData,
			success: function(result) {
				//수정 완료
				if (result == "true") {
					alert("수정이 완료되었습니다.");
					
					//리다이렉션
					window.location.replace("/mypage/myinfo");
				} else {
					//수정 실패
					alert("[ERROR] 다시 수정해주세요.");
				}
			},
			error: function(xhr, status, error) {
				alert(error);
			}
		})
	}
}

$(document).ready(function(){
	memnick_prior = '<c:out value="${updateMem.memNick}"/>';
	
	//수정 전 이메일수신여부 선택
	var mailState = '<c:out value="${updateMem.mailState}"/>';
	if (mailState == "y") $("#mailstate_y").prop("checked", true);
	else $("#mailstate_n").prop("checked", true);
	
	//수정 전 장르 선택
	var genreNo = '<c:out value="${updateMem.genreNo}"/>';
	genreBtnId = "#genreno_" + genreNo;
	$(genreBtnId).prop("checked", true);
	
	//닉네임 중복체크
	$("#memnick").blur(function() {
		checkNickExist();
	})
	
	//수정 버튼 클릭 시 form submit
	$("#btnSumbit").click(function() {
		//수정 버튼 눌렀을 때
		submit();
	})
	
	//취소 버튼 누르면 뒤로 가기
	$("#btnBack").click(function() {
		history.go(-1);
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

<style type="text/css">

body {
	min-height: 100vh;
	background-color: #FFFFFF;
	font-size: 16px;
	text-align: center;
	font-family: 'IBM Plex Sans KR', sans-serif;
}

.mem {
	font-weight: bold;
	margin: 0 0 8
	color: #212529;
}

div.mb-3 {
	text-align: left;
}

.input-form {
	width: 400px;
	margin: 60px;
	padding: 32px;
}

input[type=text],input[type=password],input[type=email] {
    vertical-align: middle;
    padding: 6 36 6 12;
    text-align: left;
    color: #6c757d;
   	font-size: 16px;
    width: 100%;
	height: 38px;
	border: 1px solid #ced4da;
	border-radius: .25rem;
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

<div class="container">
	<div class="input-form-backgroud row">
	<div class="input-form col-md-6 mx-auto">

		<h2 style="font-size: 30px;">회원정보 수정</h2>
		<hr>
		<p></p>
		<br>
		<form id="update-form">
			<div class="mb-3">
				<label class="mem" for="memid">아이디</label><span class="text-muted">&nbsp;(4~12자리의 영문 소문자, 숫자, 특수문자 [_,-]만 입력 가능합니다.)</span>
				<input type="text" class="form-control" id="memid" name="memid" value="${updateMem.memId }" readonly>
			</div>
			<div class="mb-3">
				<label class="mem" for="memnick">닉네임</label>
				<input type="text" class="form-control" id="memnick" name="memnick" value="${updateMem.memNick }"oninput="checkNick()">
				<div class="valid-feedback" id="valid-feedback-nick" style="display: none;">사용 가능한 닉네임입니다.</div>
				<div class="invalid-feedback" id="invalid-feedback-nick">닉네임을 입력해주세요.</div>
				<div class="invalid-feedback" id="invalid-feedback-nickcheck" style="display: none;">이미 존재하는 닉네임입니다.</div>
			</div>
			
			<hr>
			
			<div class="mb-3">
				<label class="mem" for="mempw">비밀번호</label>
				<input type=password class="form-control" id="mempw" name="mempw" value="${updateMem.memPw }" oninput="checkPw()">
				<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
			</div>
			
			<div class="mb-3">
				<label class="mem" for="mempw2">비밀번호 확인</label>
				<input type=password class="form-control" id="mempw2" name="mempw2" value="${updateMem.memPw }" oninput="checkPw2()">
				<div id="mempw2-invalid-feedback" class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
			</div>
			
			<hr>
			
			<div class="row">
			<div class="col-md-6 mb-3">
				<label class="mem" for="memmail">이메일</label>
				<input type="email" class="form-control" id="memmail" name="memmail" value="${updateMem.memMail }" readonly>
			</div>

			<div class="col-md-6 mb-3">
				<label class="mem" for="mailstate">이메일 수신 여부</label>
					<div class="row">
					<div class="col-md-6 mb-3"><input type="radio" id="mailstate_y" name="mailstate" value="y"><span class="text-muted">&nbsp;수신</span></div>
					<div class="col-md-6 mb-3"><input type="radio" id="mailstate_n" name="mailstate" value="n"><span class="text-muted">&nbsp;거부</span></div>
					</div>
			</div>
			</div>
			
			<hr>

			<div class="mb-3">
				<label class="mem" for="genreno">관심사</label><span class="text-muted">&nbsp;(좋아하는 장르를 선택해주세요.)</span>
					<div class="row" >
					<div class="col-md-3"><input type="checkbox" id="genreno_1" name="genreno" value="1" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;코미디</span></div>
					<div class="col-md-3"><input type="checkbox" id="genreno_2" name="genreno" value="2" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;호러</span></div>
					<div class="col-md-3"><input type="checkbox" id="genreno_3" name="genreno" value="3" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;가족</span></div>
					<div class="col-md-3"><input type="checkbox" id="genreno_4" name="genreno" value="4" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;로맨스</span></div>
					</div>
					<div class="row" >
					<div class="col-md-3"><input type="checkbox" id="genreno_5" name="genreno" value="5" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;스포츠</span></div>
					<div class="col-md-3"><input type="checkbox" id="genreno_6" name="genreno" value="6" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;드라마</span></div>
					<div class="col-md-3"><input type="checkbox" id="genreno_7" name="genreno" value="7" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;어드벤처</span></div>
					<div class="col-md-3"><input type="checkbox" id="genreno_8" name="genreno" value="8" onclick='checkOnlyOne(this)'><span class="text-muted">&nbsp;미스테리</span></div>
					</div>
			</div>
			
			<div class="mb-4"></div>
			<br>
			<button type="button" id="btnSumbit" class="btnSumbit">수정</button>
			<button type="button" id="btnBack" class="btnBack">취소</button>
		</form>
	</div>
	</div>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />