<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script>
$(document).ready(function() {
	
	//가입 버튼 클릭 시 form submit
	$("#btnSubmit").click(function() {
		$(this).parents("form").submit();
	})
	
	//취소 버튼 누르면 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
	})
});

//유효성 검사
function checked() {
	
	//변수에 담아주기
	var memid = $("#memid");
	var mempw = $("#mempw");
	var mempw2 = $("#mempw2");
	var memnick = $("#memnick");
	var memmail = $("#memmail");
	
	var isvalid = true;
	
	if (memmail.val() == "") {
		memmail.addClass("is-invalid");
		memmail.focus();
		isvalid = false;
	} else {
		memmail.removeClass("is-invalid");
		memmail.addClass("is-valid");
	}
	
	if (mempw.val() != mempw2.val()) {
		mempw2.addClass("is-invalid");
		mempw2.focus();
		isvalid = false;
	} else {
		if (mempw2.val() == "") {
			mempw2.removeClass("is-invalid");
		} else {
			mempw2.removeClass("is-invalid");
			mempw2.addClass("is-valid");
		}
	}
	
	if (mempw.val() == "") {
		mempw.addClass("is-invalid");
		mempw.focus();
		isvalid = false;
	} else {
		mempw.removeClass("is-invalid");
		mempw.addClass("is-valid");
	}
	
	if (memnick.val() == "") {
		memnick.addClass("is-invalid");
		memnick.focus();
		isvalid = false;
	} else {
		memnick.removeClass("is-invalid");
		memnick.addClass("is-valid");
	}
	
	if (memid.val() == "") {
		memid.addClass("is-invalid");
		memid.focus();
		isvalid = false;
	} else {
		memid.removeClass("is-invalid");
		memid.addClass("is-valid");
	}
	
	return isvalid;
}

//관심사 체크박스 하나만 선택
function checkOnlyOne(element) {
	const checkboxes = document.getElementsByName("genreno");
	checkboxes.forEach((cb) => {cb.checked = false;
	})
	element.checked = true;
}
</script>

<style>
body {
	min-height: 100vh;
	background-color: f2f2f2;
}

div.mb-3 {
	text-align: left;
}

input[type=text] {
	border: solid 1px #cccccc; 
	text-align: left;
}

.input-form {
	max-width: 680px;

	margin-top: 80px;
	padding: 32px;

	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}

</style>
</head>

<body>
<div class="container">
	<div class="input-form-backgroud row">
	<div class="input-form col-md-6 mx-auto">
		<h4 class="mb-3">회원가입</h4>
		<form action="<%=request.getContextPath() %>/join" method="post" onsubmit="return checked()">
			<div class="mb-3">
				<label for="memid">아이디</label>
				<input type="text" class="form-control" id="memid" name="memid">
				<div class="invalid-feedback">아이디를 입력해주세요.</div>
			</div>
			
			<div class="mb-3">
				<label for="memnick">닉네임</label>
				<input type="text" class="form-control" id="memnick" name="memnick">
				<div class="invalid-feedback">닉네임을 입력해주세요.</div>
			</div>
			
			<hr>
			
			<div class="mb-3">
				<label for="mempw">비밀번호</label>
				<input type=password class="form-control" id="mempw" name="mempw">
				<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
			</div>
			
			<div class="mb-3">
				<label for="mempw2">비밀번호 확인</label>
				<input type=password class="form-control" id="mempw2" name="mempw2">
				<div id="mempw2-invalid-feedback" class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
			</div>
			
			<hr>

			<div class="mb-3">
				<label for="memmail">이메일</label>
				<input type="email" class="form-control" id="memmail" name="memmail" placeholder="you@example.com">
				<div class="invalid-feedback">이메일을 입력해주세요.</div>
			</div>

			<div class="mb-3">
				<label for="memstate">이메일 수신 여부</label>
				<div class="row" >
					<div class="col-md-3"><input type="radio" name="memstate" value="y" checked><span class="text-muted">&nbsp;수신</span></div>
					<div class="col-md-3"><input type="radio" name="memstate" value="n"><span class="text-muted">&nbsp;거부</span></div>
				</div>
			</div>

			<hr>

			<div class="mb-3">
				<label for="genreno">관심사<span class="text-muted">&nbsp;(좋아하는 장르를 선택해주세요.)</span></label>
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
            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          </div>
          
			<div class="mb-4"></div>
			<button type="submit" id="btnJoin" >가입</button>
			<button type="button" id="btnCancel">취소</button>
		</form>
	</div>
	</div>
</div>

</body>
</html>