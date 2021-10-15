<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/adminheader.jsp" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">

function submitContents(elClickedObj){
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	try {elClickedObj.form.submit();} catch(e) {}
	}
</script>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#btnSubmit").click(function(){
		
		var answer = confirm("공지사항을 등록하시겠습니까?")
		
		if(answer == true) {
			submitContents($("#btnSubmit"))
		
			$("form").submit();
		} else {
			return false;
		}
	});
	$("#btnCancel").click(function(){
		history.go(-1);
	});
});
</script>

<!------------------------------------------------------>

<style>
table {
	text-align: center;
}

</style>

<div class="container">

<h1>공지사항 작성</h1>
<hr>

<form action="<%=request.getContextPath() %>/admin/notice/write" method="post" enctype="multipart/form-data">
<table class="table table-hover table-condensed">
<!-- <tr> -->
<!-- 	<td>no</td> -->
<!-- 	<td class=>자동생성</td> -->
<!-- </tr> -->

<tr>
	<td colspan="6"><h4><input type="text" id="title" name="title" style="width: 100%; padding: 5px;" autocomplete="off" placeholder="공지사항 제목"/></h4></td>
</tr>
<tr>
	<td class="item">관리자 아이디</td>
	<td style="width:25%;">${adminid }<input type="hidden" name="adminId" value="${adminid }" /></td>
	<td class="item">관리자 이름</td>
	<td style="width:25%;">${adminname }</td>
</tr>
<tr>
	<td colspan="6"><textarea id="content" name="content" style="width: 100%; height: 300px; padding: 10px;"></textarea></td>
</tr>

<!-- <tr> -->
<!-- 	<td>date</td> -->
<!-- 	<td>자동생성</td> -->
<!-- </tr> -->
</table>


<input type="file" name="file" />
<!-- 	<td>파일 첨부</td> -->
<!-- 	<td><input type="file" name="file"/></td> -->


</form>
<div class="text-center">	
	<button type="button" id="btnSubmit" class="btnSubmit" >작성</button>
	<button type="button" id="btnCancel" class="btnCancel" >취소</button>
</div>
<!-- <div class="text-center"> -->
<!-- 	<button type="button" id="btnWrite" class="btn btn-info">SUBMIT</button> -->
<!-- 	<button type="button" id="btnCancel" class="btn btn-info">CANCEL</button> -->
<!-- </div> -->
 
</div>

<!------------------------------------------------------>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "content",
 sSkinURI: "<%=request.getContextPath()%>/resources/se2/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});
</script>
<c:import url="/WEB-INF/views/layout/footer.jsp" />