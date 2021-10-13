<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 이전화면에서 전달받은 데이터를 받습니다.
	// 화면에 미리 보여지도록 처리를 하세요~
	request.setCharacterEncoding("UTF-8");

	String mempw = (String)request.getAttribute("mempw");
	String memmail = (String)request.getAttribute("memmail");
	String memstate = (String)request.getAttribute("memstate");
	String genreno = (String)request.getAttribute("genreno");
	
%> 
  
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
<style type="text/css">
form {
	width: 400px;
	margin: 0 auto;
}
.btn {
	height:40px;
}
#aside {
	width:300px;
	height:auto;
	float: left;
}
#section {
	width:800px;
	height:auto;
	float: left;
}
</style>
</head>
<body>

<div id="aside">
<table style = none>
	<tr>
	<td><h4>회원정보</h4>
	<button type="button" id="myinfo" class="btn">계정 관리</button><br>
	<button type="button" id="userdel" class="btn">회원 탈퇴</button><br>
	</td>
	</tr>
	<tr>
	<td><h4>회원활동</h4>
	<button type="button" id="myrev" class="btn">내가 리뷰한 공연</button><br>
	<button type="button" id="myjim" class="btn">내가 찜한 공연</button><br>
	<button type="button" id="mycal" class="btn">마이 캘린더</button><br>
	</td>
	</tr>
	<tr>
	<td><h4>고객센터</h4>
	<button type="button" id="notice" class="btn">공지사항</button><br>
	<button type="button" id="askwrite" class="btn">1:1문의</button><br>
	<button type="button" id="asklist" class="btn">문의내역</button><br>
	</tr>
</table>
</div>

<!-- 회원 정보 수정 구현 -->
<div id="section">

<h3>회원정보 수정</h3>
<hr>

<form action="update_ok.jsp" method="post" class="form-horizontal">
	<div class="form-group">
		<label for="mempw" class="control-label">비밀번호</label>
		<input type="text" id="mempw" name="mempw" placeholder="비밀번호" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="mempw2" class="control-label">비밀번호 확인</label>
		<input type="text" id="mempw2" name="mempw2" placeholder="비밀번호 확인" class="form-control"/>
	</div>
	<hr>
	<div class="form-group">
		<label for="memnick" class="control-label">닉네임</label>
		<input type="text" id="memnick" name="memnick" placeholder="닉네임" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="memmail" class="control-label">이메일</label>
		<input type="text" id="memmail" name="memmail" placeholder="이메일" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="memstate" class="control-label">이메일수신여부</label>
		<label><input type="radio" placeholder="이메일수신여부" name="memstate" value="y" checked>수신</label>
		<label><input type="radio" placeholder="이메일수신여부" name="memstate" value="n">거부</label>
	</div>
	<hr>
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
			<input type="submit" value="수정" class="btn btn-primary">
			<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>
</form>

</div>
</body>
</html>