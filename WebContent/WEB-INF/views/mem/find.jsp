<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnFindid").click(function(){$("form").submit()})
	$("#btnFindpw").click(function(){$("form").submit()})
})
</script>

<style>

</style>




<div class="container" id="container">
<h3>아이디/비밀번호 찾기</h3> <br>

<hr>


<form action="<%=request.getContextPath() %>/member/find" method="post">
<!-- js로 둘 중에 하나만 전송할 수 있게 합시다요<br> -->
<h4>아이디/비밀번호는 가입시 등록한 메일 주소로 알려드립니다.</h4> <br>



<h4>아이디 찾기</h4>

<h6>가입할 때 등록한 메일 주소를 입력하고 ID찾기 버튼을 클릭해주세요.</h6>

<input type="text" name="mailForId" placeholder="이메일 주소를 입력하세요.">
<button type="button" id="btnFindid">ID찾기</button>
<br>
<br>
<br>




<h4>비밀번호 찾기</h4>

<h6>회원 정보에 입력한 이메일로 임시 비밀번호를 발급 받을 수 있습니다.</h6>


<input type="text" name="mailForPw" placeholder="이메일 주소를 입력하세요.">
<button type="button" id="btnFindpw">PW찾기</button>
</form>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />