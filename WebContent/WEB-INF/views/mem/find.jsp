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

<h1>FIND ID/PW</h1>


<form action="<%=request.getContextPath() %>/member/find" method="post">

<div class="col-md-6 mb-3">
	<label class="mem" for="memmail">아이디 찾기</label>
	<input type="email" class="form-control" id="memmail" name="mailForId" placeholder="you@example.com" oninput="checkEmail()">
</div>
<button type="button" id="btnFindid">아이디 찾기</button>

<br><br><br>

<div class="col-md-6 mb-3">
	<label class="mem" for="memmail">패스워드 재설정</label>
	<input type="email" class="form-control" id="memmail" name="mailForPw" placeholder="you@example.com" oninput="checkEmail()">
</div>
<button type="button" id="btnFindpw">패스워드 재설정</button>

</form>


</body>
</html>