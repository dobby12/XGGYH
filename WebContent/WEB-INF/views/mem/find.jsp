<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>
<script>
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
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnFindid").click(function(){$("form").submit()})
	$("#btnFindpw").click(function(){$("form").submit()})
})
</script>

<style>

.container {
	width: 850px;
}

.findid {
	float: left;
	width: 400px;
	height: 340px;
	border-right: 1px solid #f2f2f2;
}

.findpw {
	float: right;
	width: 400px;
	height: 340px;
}


</style>

<div class="container">
<h2>아이디/비밀번호 찾기</h2>


<form action="<%=request.getContextPath() %>/member/find" method="post">

<!-- js로 둘 중에 하나만 전송할 수 있게 합시다요<br> -->
<h3>아이디/비밀번호는 가입시 등록한 메일 주소로 알려드립니다.</h3> <br>
<hr>
<div>

<div class="findid">
<br><br><br><br>
<h4><strong>아이디 찾기</strong></h4>
<h6>가입할 때 등록한 메일 주소를 입력하고 아이디찾기 버튼을 클릭해주세요.</h6>
<input type="email" id="memidmail" name="mailForId" placeholder="you@example.com" oninput="checkEmail()" />
<button id="btnFindid">아이디 찾기</button>
</div>

<div class="findpw">
<br><br><br><br>
<h4><strong>비밀번호 재설정</strong></h4>
<h6>회원 정보에 입력한 이메일로 임시 비밀번호를 발급 받을 수 있습니다.</h6>
<input type="email" id="mempwmail" name="mailForPw" placeholder="you@example.com" oninput="checkEmail()">
<button id="btnFindpw">비밀번호 재설정</button>
</div>

</div>
</form>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />