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
		$("#fileDelete").attr('value', 'fileDelete');
	})

});
//특수문자(<, >, \) 입력 방지 네이버SE2에는 적용 안 되므로 글 내용 작성에는 영향 없음
function characterCheck(obj){
 	var regExp = /[<>\\]/gi; 
	if( regExp.test(obj.value) ){
		alert("일부 특수문자는 입력하실수 없습니다.");
		obj.value = obj.value.substring( 0 , obj.value.length - 1 );
		}
}
</script>

<style type="text/css">
#content {
	width: 98%;
}

</style>

<div class="container">

<h3>글 수정</h3>
<hr>

<div>
<form action="/review/update" method="post" enctype="multipart/form-data">

<input type="hidden" name="reviewNo" value="${updateReview.reviewNo }" />

<table class="table table-condensed table-bordered">

<tr>
	<td>No. ${updateReview.reviewNo }</td>
	<td colspan="9"><input style="width: 100%;" type="text" name="reviewTitle" value="${updateReview.reviewTitle }" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)"/></td>
</tr>

<tr>
	<td class="item">닉네임</td><td>${memNick }</td>
	<td class="item">공연 제목</td><td>${showTitle }</td>

	<td class="item">평점</td>
	<td>
		<select name="reviewScore">
			<c:if test="${updateReview.reviewScore == 1 }">
				<option value="1" selected>★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
			</c:if>
			<c:if test="${updateReview.reviewScore == 2 }">
				<option value="1">★</option>
				<option value="2" selected>★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
			</c:if>
			<c:if test="${updateReview.reviewScore == 3 }">
				<option value="1">★</option>
				<option value="2">★★</option>
				<option value="3" selected>★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
			</c:if>
			<c:if test="${updateReview.reviewScore == 4 }">
				<option value="1">★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4" selected>★★★★</option>
				<option value="5">★★★★★</option>
			</c:if>
			<c:if test="${updateReview.reviewScore == 5 }">
				<option value="1" style="">★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5" selected>★★★★★</option>
			</c:if>
		</select>
	</td>
	
	<td class="item">조회수</td><td>${updateReview.reviewHit }</td>
	<td class="item">작성일</td><td>${updateReview.reviewDate }</td>
</tr>

<tr>
	<td colspan="10"><textarea id="reviewContent" name="reviewContent" style="width:100%; height: 400px;" >${updateReview.reviewContent }</textarea></td></tr>
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
		<input type="hidden" name="fileDelete" id="fileDelete" value=""/>
	</div>
</div>

<br>
</form>
</div>

<div class="text-center">	
	<button type="button" id="btnUpdate" class="btnSubmit">수정</button>
	<button type="button" id="btnCancel" class="btnBack">취소</button>
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