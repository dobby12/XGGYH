<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnWrite").click(function() {
		$("form").submit();
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<div class="container">

<h3>리뷰 작성</h3>
<hr>

<div>
<form action="/review/write" method="post" enctype="multipart/form-data">

<table class="table table-bordered">
<tr><td class="info">아이디</td><td>${memId }</td></tr>
<tr><td class="info">닉네임</td><td>${memNick }</td></tr>
<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%"/></td></tr>
<tr><td class="info" colspan="12">본문</td></tr>
<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
</table>

첨부파일 <input type="file" name="file" />

</form>
</div>

<div class="text-center">	
	<button type="button" id="btnWrite">작성</button>
	<button type="button" id="btnCancel">취소</button>
</div>

</div>

</body>
</html>












