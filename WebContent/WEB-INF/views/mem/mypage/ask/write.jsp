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
/* 	width: 100%; */
	width: 98%;
}
</style>

<div class="container">

<h3>1:1문의</h3>
<hr>

<div>
<form action="/mypage/myask/write" method="post" enctype="multipart/form-data">

<table class="table table-bordered">
<tr>
	<td class="info">작성자</td>
	<td>${memnick }</td>
</tr>
<tr>
	<td class="info">제목</td>
	<td><input type="text" name="title" style="width:100%"/></td>
</tr>
<tr>
	<td class="info">문의유형</td>
	<td>
		<select id="kind" name="kind" class="kind" >
			<option value="회원정보">회원정보</option>
			<option value="공연정보">공연정보</option>
			<option value="기타문의">기타문의</option>
		</select>
	</td>
</tr>
<tr>
	<td class="info" colspan="2">본문</td>
</tr>
<tr>
	<td colspan="2"><textarea id="content" name="content"></textarea></td>
</tr>
</table>

</form>
</div>

<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-info">작성</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

<!-- .container -->
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />