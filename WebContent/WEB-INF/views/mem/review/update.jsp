<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
function submitContents(elClickedObj){
	oEditors.getById["reviewContent"].exec("UPDATE_CONTENTS_FIELD", []);
	try {
		elClickedObj.form.submit();
		} catch(e) {}
	}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnUpdate").click(function() {
		submitContents( $("btnWrite") )
		$("form").submit();
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	

	//파일이 있을 경우
	if(${not empty xFile }) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 없을 경우
	if(${empty xFile }) {
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

<h3 class="pull-left">글 수정</h3>
<hr>

<div>
<form action="/review/update" method="post" enctype="multipart/form-data">

<input type="hidden" name="reviewNo" value="${updateReview.reviewNo }" />

<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${updateReview.reviewNo }</td>
</tr>
<tr>
<td class="info">제목</td><td colspan="3"><input type="text" name="reviewTitle" style="width:100%" value="${updateReview.reviewTitle }"/></td>
</tr>
<tr>
<td class="info">아이디</td><td>${updateReview.memId }</td>
<td class="info">닉네임</td><td>${memNick }</td>
</tr>
<tr>
<td class="info">조회수</td><td>${updateReview.reviewHit }</td>
</tr>
<tr>
<td class="info">작성일</td><td colspan="3">${updateReview.reviewDate }</td>
</tr>
<tr>
<td class="info">공연 제목</td><td colspan="3">${showTitle }</td>
</tr>
<tr>
	<td class="info">별점</td>
	<td colspan="3">
		<select name="reviewScore" style="text-align: center;">
			<option>${updateReview.reviewScore }</option>
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select>
	</td>
</tr>
<tr><td class="info"  colspan="4">본문</td></tr>
<tr><td colspan="4"><textarea id="reviewContent" name="reviewContent" style="width:100%" >${updateReview.reviewContent }</textarea></td></tr>
</table>

<!-- 첨부파일 -->
<div>
	<div id="beforeFile">
		기존 첨부파일: 
		<a href="/upload/${xFile.fileStoredName }" >${xFile.fileOriginName }</a>
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

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "reviewContent",
 sSkinURI: "<%=request.getContextPath()%>/resources/se2/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});
</script>

</body>
</html>