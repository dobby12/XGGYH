<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
function submitContents(elClickedObj){
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	try {elClickedObj.form.submit();} catch(e) {}
	}
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnWrite").click(function(){
		submitContents($("#btnWrite"))
		$("form").submit();
	});
	$("#btnCancel").click(function(){
		history.go(-1);
	});
});
</script>

<!------------------------------------------------------>

<h1>ADMIN UPDATE</h1>
<hr>

<form action="<%=request.getContextPath() %>/admin/notice/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="${notice.noticeNo }">

<table>
<thead>
<tr>
	<td>no</td>
	<td>${notice.noticeNo }</td>
</tr>
<tr>
	<td>title</td>
	<td><input id="title" name="title" value="${notice.noticeTitle }"></td>
</tr>
<tr>
	<td>content</td>
	<td><textarea id="content" name="content">${notice.noticeContent }</textarea></td>
</tr>
<tr>
	<td>id</td>
	<td>${notice.adminId }</td>
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
<button type="button" id="btnWrite">수정</button>
<button type="button" id="btnCancel">취소</button>

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
</body>
</html>