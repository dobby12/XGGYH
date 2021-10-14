<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">

function checked() {
	
	var mempw = document.getElementById("mempw");
	var mempw2 = document.getElementById("mempw2");
	var memnick = document.getElementById("memnick");
	var memmail = document.getElementById("memmail");
	
	if (mempw.value != mempw2.value) {
		alert("비밀번호가 일치하지 않습니다.")
		mempw2.focus();
		return false;
	}
	
	if (memnick.value == "") {
		alert("닉네임을 입력하세요.");
		memnick.focus();
		return false;
	}
	
	if (memmail.value == "") {
		alert("이메일을 입력하세요.");
		memmail.focus();
		return false;
	}
	
	return true
}

$(function(){
	//수정 전 이메일수신여부 선택
	var mailState = '<c:out value="${updateMem.mailState}"/>';
	if (mailState == "y") $("#mailstate_y").prop("checked", true);
	else $("#mailstate_n").prop("checked", true);
	
	//수정 전 장르 선택
	var genreNo = '<c:out value="${updateMem.genreNo}"/>';
	genreBtnId = "#genreno_" + genreNo;
	$(genreBtnId).prop("checked", true);
	
	//취소 버튼
	$("#btnBack").click(function() {
		history.go(-1);
	});
});

//수정 버튼
$("#btnUpdate").click(function() {
	$("form").submit();
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
form {
	width: 400px;
	margin: 0 auto;
}
</style>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<!-- 회원 정보 수정 구현 -->
<div id="section">

<h3>회원정보 수정</h3>
<hr>

<form action="/mypage/myinfo/update" method="post" onsubmit="return checked()" class="form-horizontal">
		<input type="hidden" id="memid" name="memid" value="${updateMem.memId }" />
	<div class="form-group">
		<label for="mempw" class="control-label">비밀번호</label>
		<input type="password" id="mempw" name="mempw" placeholder="비밀번호" value="" />
	</div>
	<div class="form-group">
		<label for="mempw2" class="control-label">비밀번호 확인</label>
		<input type="password" id="mempw2" name="mempw2" placeholder="비밀번호 확인" value=""/>
	</div>
	<hr>
	<div class="form-group">
		<label for="memnick" class="control-label">닉네임</label>
		<input type="text" id="memnick" name="memnick" placeholder="닉네임" value="${updateMem.memNick }"/>
	</div>
	<div class="form-group">
		<label for="memmail" class="control-label">이메일</label>
		<input type="text" id="memmail" name="memmail" placeholder="이메일" value="${updateMem.memMail }"/>
	</div>
	<div class="form-group">
		<label for="mailstate" class="control-label">이메일수신여부</label>
		<label><input type="radio" id="mailstate_y" name="mailstate" value="y">수신</label>
		<label><input type="radio" id="mailstate_n" name="mailstate" value="n">거부</label>
	</div>
	<hr>
	<div class="form-group">
		<label>관심사</label>
		<table style = none>
			<tr>
			<td><label><input type="checkbox" id = "genreno_1" name="genreno" value="1" onclick='checkOnlyOne(this)'>코미디</label></td>
			<td><label><input type="checkbox" id = "genreno_2" name="genreno" value="2" onclick='checkOnlyOne(this)'>호러</label></td>
			<td><label><input type="checkbox" id = "genreno_3" name="genreno" value="3" onclick='checkOnlyOne(this)'>가족</label></td>
			<td><label><input type="checkbox" id = "genreno_4" name="genreno" value="4" onclick='checkOnlyOne(this)'>로맨스</label></td>
			</tr>
			<tr>
			<td><label><input type="checkbox" id = "genreno_5" name="genreno" value="5" onclick='checkOnlyOne(this)'>스포츠</label></td>
			<td><label><input type="checkbox" id = "genreno_6" name="genreno" value="6" onclick='checkOnlyOne(this)'>드라마</label></td>
			<td><label><input type="checkbox" id = "genreno_7" name="genreno" value="7" onclick='checkOnlyOne(this)'>어드벤처</label></td>
			<td><label><input type="checkbox" id = "genreno_8" name="genreno" value="8" onclick='checkOnlyOne(this)'>미스테리</label></td>
			</tr>
		</table>
		</div>
		<div class="text-center">
			<input type="submit" id="btnUpdate" value="수정">
			<button type="button" id="btnBack">취소</button>
	</div>
</form>

</div>
</body>
</html>