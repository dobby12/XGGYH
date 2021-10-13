<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

</script>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnUpdate").click(function() {
		$("form").submit();
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	

	//파일이 있을 경우
	if(${not empty boardFile }) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 없을 경우
	if(${empty boardFile }) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//파일 삭제 버튼(X) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
	})

});
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<div class="container">

<h3 class="pull-left">리뷰 수정</h3>
<hr>

<div>
<form action="/review/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="reviewno" value="${viewFile.reviewNo }" />

<table class="table table-bordered">
<tr><td class="info">아이디</td><td>${updateReview.memId }</td></tr>
<tr><td class="info">닉네임</td><td>${updateReview.memNick }</td></tr>
<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%" value="${updateReview.reviewTitle }"/></td></tr>
<tr><td class="info" colspan="2">본문</td></tr>
<tr><td colspan="2">${updateReview.reviewContent }</td></tr>
</table>

<!-- 첨부파일 -->
<div>
	<div id="beforeFile">
		기존 첨부파일: 
		<a href="/upload/${reviewFile.fileStoredName }" download="${reviewFile.fileOriginName }">${reviewFile.fileOriginName }</a>
		<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
	</div>

	<div id="afterFile">
		새 첨부파일:
		<input type="file" name="file" />
	</div>
</div>

<br>
</form>
</div>

<div class="text-center">	
	<button type="button" id="btnUpdate">수정</button>
	<button type="button" id="btnCancel">취소</button>
</div>

</div>

</body>
</html>