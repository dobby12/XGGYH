<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

//관심사 체크박스 하나만 선택
function checkOnlyOne(element) {
	const checkboxes = document.getElementsByName("genreno");
	checkboxes.forEach((cb) => {cb.checked = false;
	})
	element.checked = true;
}

</script>

<script>

//이메일 선택
$(document).ready(function() {
	$('#select').change(function() {
		if ($('#select').val() == 'directly') {
			$('#email2').attr("disabled", false);
			$('#email2').val("");
			$('#email2').focus();
		} else {
			$('#email2').val($('#select').val());
		}
	})

	//가입 버튼 클릭 시 form submit
	$("#btnJoin").click(function() {
		$(this).parents("form").submit();
	})
	
	//취소 버튼 누르면 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
	})
	
});

//이메일 유효성 검사

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

<form action="<%=request.getContextPath() %>/join" method="post" class="form-horizontal">
	<div class="form-group">
		<label for="userid" class="control-label">아이디</label>
		<input type="text" id="userid" name="userid" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="userpw1" class="control-label">비밀번호</label>
		<input type="text" id="userpw1" name="userpw1" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="userpw2" class="control-label">비밀번호 확인</label>
		<input type="text" id="userpw2" name="userpw2" class="form-control"/>
		<span id="alert-success" style="display: none;">비밀번호가 일치합니다.</span>
		<span id="alert-danger" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span>
	</div>
	<div class="form-group">
		<label for="usernick" class="control-label">닉네임</label>
		<input type="text" id="usernick" name="usernick" class="form-control"/>
	</div>
	<div class="form-group">
	<table>
	<tr>
	<th>이메일</th>
	<td>
	<input type="text" id="email1" name="email1" class="control-label" value="" maxlength="18" > @ 
	<input type="text" id="email2" name="email2" class="control-label" value="" placeholder="이메일을 선택하세요."> 
	<select id="select" title="이메일 도메인 주소 선택">
	    <option value="" disabled selected>-선택-</option>
	    <option value="naver.com">naver.com</option>
	    <option value="gmail.com">gmail.com</option>
	    <option value="hanmail.net">hanmail.net</option>
	    <option value="kakao.com">kakao.com</option>
	</select>
	</td>
	</tr>
	</table>
	</div>
	<div class="form-group">
		<label>이메일수신여부</label>
		<label><input type="radio" class="control-label" placeholder="이메일수신여부" name="mailstate" value="수신">수신</label>
		<label><input type="radio" class="control-label" placeholder="이메일수신여부" name="mailstate" value="거부">거부</label>
	</div>
	<div class="form-group">
	<label>관심사</label>
	<table style = none>
		<tr>
		<td><label><input type="checkbox" class="control-label" name="genreno" value="1" onclick='checkOnlyOne(this)'>코미디</label></td>
		<td><label><input type="checkbox" class="control-label" name="genreno" value="2" onclick='checkOnlyOne(this)'>호러</label></td>
		<td><label><input type="checkbox" class="control-label" name="genreno" value="3" onclick='checkOnlyOne(this)'>가족</label></td>
		<td><label><input type="checkbox" class="control-label" name="genreno" value="4" onclick='checkOnlyOne(this)'>로맨스</label></td>
		</tr>
		<tr>
		<td><label><input type="checkbox" class="control-label" name="genreno" value="5" onclick='checkOnlyOne(this)'>스포츠</label></td>
		<td><label><input type="checkbox" class="control-label" name="genreno" value="6" onclick='checkOnlyOne(this)'>드라마</label></td>
		<td><label><input type="checkbox" class="control-label" name="genreno" value="7" onclick='checkOnlyOne(this)'>어드벤처</label></td>
		<td><label><input type="checkbox" class="control-label" name="genreno" value="8" onclick='checkOnlyOne(this)'>미스테리</label></td>
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
