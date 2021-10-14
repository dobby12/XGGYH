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
		submitContents($("#btnSubmit"))
		$("form").submit();
	});
	$("#btnBack").click(function(){
		history.go(-1);
	});
});
</script>

<!------------------------------------------------------>
<div class="container">

<h1>공지사항 수정</h1>
<hr>

<form action="<%=request.getContextPath() %>/admin/notice/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="${notice.noticeNo }">

<table class="table table-condensed table-bordered">

<thead>

<tr>
	<td colspan="6"><h4><input type="text" id="showTitle" name="showTitle" style="width: 100%; padding: 5px;" value="${notice.noticeTitle }"/></h4></td>
</tr>
<tr>
	<td style="background-color: #D99771; width:25%;">관리자 아이디</td>
	<td style="width:25%;">${adminid }<input type="hidden" name="adminId" value="${adminid }" /></td>
	<td style="background-color: #D99771">관리자 이름</td>
	<td style="width:25%;">${adminname }</td>
</tr>
<tr>
	<td colspan="6"><textarea id="content" name="content" style="width: 100%; height: 300px; padding: 10px;">${notice.noticeContent }</textarea></td>
</tr>

<!-- <tr> -->
<!-- 	<td>date</td> -->
<%-- 	<td>${notice.noticeDate }</td> --%>
<!-- </tr> -->
</thead>
</table>

<br><br><br><!-- @@@ -->
<div>
	<div id="beforeFile">
		<c:if test="${notice.fileNo ne 0 }">
		기존 첨부파일 : 
		<a href="/upload/${file.fileStoredName }" download="${file.fileOriginName }">${file.fileOriginName }</a>
		</c:if>
		<c:if test="${notice.fileNo eq 0 }">
		첨부파일이 없습니다.
		</c:if>
	</div>
<br><br><br><!-- @@@ -->
	<div id="afterFile">
		새 첨부파일 : <input type="file" name="file">새 파일 첨부 시 기존 파일은 삭제됩니다.
	</div>
</div>
</form>

<br>
<%-- <a href="<%=request.getContextPath() %>/admin/notice/update?noticeno=${notice.noticeNo }"> --%>
<button type="button" id="btnSubmit" class="btnSubmit">수정</button>
<button type="button" id="btnBack" class="btnBack">취소</button>
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