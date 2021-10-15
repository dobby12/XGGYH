<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="dto.XNotice"%>
<%@page import="dao.face.MemberDao"%>    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$("#btnSubmit").click(function() {
		$("form").submit();
	});
	
	$("#btnBack").click(function() {
		history.go(-1);
	});
});
</script>

</head>
<body>

<!-- 회원 정보 탈퇴 구현 -->
<div id="section">

<h3>회원정보 탈퇴</h3>
<hr>

<form action="/mypage/myinfo/delete" method="post" enctype="multipart/form-data">
 	
 	<div>
 		<label>아이디</label>
 		<label>${myinfo.memId }</label>
 	</div>
 	<div>
 		<label>공공연히에서 탈퇴하시겠습니까?</label>
 	</div>
 	<div>
		<button type="button" id="btnSubmit">탈퇴</button>
		<button type="button" id="btnBack">취소</button>
 	</div>
 	
</form>

</div>
</body>
</html>