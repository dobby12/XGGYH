<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
//특수문자(<, >, \) 입력 방지 네이버SE2에는 적용 안 되므로 글 내용 작성에는 영향 없음
function characterCheck(obj){
 	var regExp = /[<>\\]/gi; 
	if( regExp.test(obj.value) ){
		alert("일부 특수문자는 입력하실수 없습니다.");
		obj.value = obj.value.substring( 0 , obj.value.length - 1 );
		}
}
</script>
<script type="text/javascript">
function submitContents(elClickedObj){
	
	//#reviewContent에 반영
	oEditors.getById["reviewContent"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그 submit
		elClickedObj.form.submit();
		} catch(e) {}
	}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnWrite").click(function() {
		submitContents( $("btnWrite") )
		$("form").submit();
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});
</script>

<div class="container text-center">

<h3>리뷰 작성하기</h3>
<hr>

<div>
<form action="/review/write" method="post" enctype="multipart/form-data">

<input type="hidden" name="showNo" value="${showDetail.showNo }" />

<table class="table table-condensed">
<tr>
	<td class="item" colspan="1">닉네임</td><td colspan="2">${memnick }</td>
	<td class="item" colspan="1">공연이름</td><td style="width: 55%;" colspan="3">${showDetail.showTitle }</td>
	<td class="item" colspan ="1">평점</td>
	<td colspan="1">
		<select name="reviewScore" style="text-align: center;">
			<option disabled>평점 선택</option>
			<option value="1">★</option>
			<option value="2">★★</option>
			<option value="3">★★★</option>
			<option value="4">★★★★</option>
			<option value="5">★★★★★</option>
		</select>
	</td>
</tr>

<tr>
	<td class="item" colspan="9"><input type="text" name="reviewTitle" style="width:100%" placeholder="제목" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)"/></td>
</tr>

<tr>
	<td colspan="9" style="width: 100%; padding: 10px; vertical-align: middle;">
	<textarea id="reviewContent" name="reviewContent" style="width:100%; height:400px;"></textarea></td>
</tr>
</table>

<div>
<input style="padding: 10px;" type="file" name="file" />
</div>

</form>
</div>

<div class="text-center">	
	<button type="button" id="btnWrite">작성</button>
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












