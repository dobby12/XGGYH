<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.XMem"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$("#btnSubmit").click(function() {
		var answer = confirm("정말 탈퇴하시겠습니까?ㅠㅠ")
		
		if(answer == true) {
			$("form").submit();
		} else {
			return false;
		}
	});
	
	$("#btnBack").click(function() {
		history.go(-1);
	});
});
</script>

<style type="text/css">
div.id-email {
	max-width: 400px;
	text-aglin:center;
	margin: auto;
	padding: 20px;
	border: 1px solid #ddd;
	background-color: #f2f2f2;
}
</style>

<!-- 회원 정보 탈퇴 구현 -->
<div class="container">

<h2>회원탈퇴</h2>
<hr>

<form action="/mypage/myinfo/delete" method="post" enctype="multipart/form-data">
 	
 	<div class="id-email">
	 	<div>
	 		<label class="mem" for="memId">아이디 : </label> ${mem.memId }
	 	</div>
	 	<div>
	 		<label class="mem" for="memMail">이메일 : </label> ${mem.memMail }
	 	</div>
 	 </div>
 	<div>
 		<h3>공공연히에서 탈퇴하시겠습니까?</h3>
 		탈퇴 시 작성된 리뷰는 삭제되지 않습니다.
 	</div>
 	<br>
 	<div>
		<button type="button" id="btnSubmit" class="btnSubmit">탈퇴</button>
		<button type="button" id="btnBack" class="btnBack">취소</button>
 	</div>

</form>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />