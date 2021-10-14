<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">

//유효성 검사
function checked() {
	
	//변수에 담아주기
	var memid = document.getElementById("memid");
	var mempw = document.getElementById("mempw");
	var mempw2 = document.getElementById("mempw2");
	var memnick = document.getElementById("memnick");
	var memmail = document.getElementById("memmail");
	
	if (memid.value == "") {
		alert("아이디를 입력하세요.");
		memid.focus();
		return false;
	}
	
	if (mempw.value == "") {
		alert("비밀번호를 입력하세요.");
		mempw.focus();
		return false;
	}
	
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

//관심사 체크박스 하나만 선택
function checkOnlyOne(element) {
	const checkboxes = document.getElementsByName("genreno");
	checkboxes.forEach((cb) => {cb.checked = false;
	})
	element.checked = true;
}

</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//가입 버튼 클릭 시 form submit
	$("#btnJoin").click(function() {
		$(this).parents("form").submit();
	})
	
	//취소 버튼 누르면 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
	})

//이메일 유효성 검사
});
</script>

<style type="text/css">
form {
	width: 400px;
	margin: 0 auto;
}
</style>

<div class="container">

<h3>회원가입</h3>
<hr>

<form action="<%=request.getContextPath() %>/join" method="post" onsubmit="return checked()" class="form-horizontal">
	<div class="form-group">
		<label for="memid" class="control-label">아이디</label>
		<input type="text" id="memid" name="memid" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="mempw" class="control-label">비밀번호</label>
		<input type="text" id="mempw" name="mempw" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="mempw2" class="control-label">비밀번호 확인</label>
		<input type="text" id="mempw2" name="mempw2" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="memnick" class="control-label">닉네임</label>
		<input type="text" id="memnick" name="memnick" class="form-control"/>
	</div>
 	<div class="form-group">
 		<label for="memmail" class="control-label">이메일</label>
		<input type="text" id="memmail" name="memmail" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="memstate" class="control-label">이메일수신여부</label>
		<label><input type="radio" name="memstate" value="y" checked>수신</label>
		<label><input type="radio" name="memstate" value="n">거부</label>
	</div>
	<div class="form-group">
	<label>관심사</label>
	<table style = none>
		<tr>
		<td><label><input type="checkbox" name="genreno" value="1" onclick='checkOnlyOne(this)' checked>코미디</label></td>
		<td><label><input type="checkbox" name="genreno" value="2" onclick='checkOnlyOne(this)'>호러</label></td>
		<td><label><input type="checkbox" name="genreno" value="3" onclick='checkOnlyOne(this)'>가족</label></td>
		<td><label><input type="checkbox" name="genreno" value="4" onclick='checkOnlyOne(this)'>로맨스</label></td>
		</tr>
		<tr>
		<td><label><input type="checkbox" name="genreno" value="5" onclick='checkOnlyOne(this)'>스포츠</label></td>
		<td><label><input type="checkbox" name="genreno" value="6" onclick='checkOnlyOne(this)'>드라마</label></td>
		<td><label><input type="checkbox" name="genreno" value="7" onclick='checkOnlyOne(this)'>어드벤처</label></td>
		<td><label><input type="checkbox" name="genreno" value="8" onclick='checkOnlyOne(this)'>미스테리</label></td>
		</tr>
	</table>
	</div>
	<div class="text-center">
		<button type="button" id="btnJoin" class="btn btn-primary">가입</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>
</form>

<!-- .container -->
</div>
