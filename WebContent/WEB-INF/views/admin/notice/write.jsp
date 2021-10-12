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

<div class="container text-center">

<h1>NOTICE WRITE</h1>
<hr>

<form action="<%=request.getContextPath() %>/admin/notice/write" method="post" enctype="multipart/form-data">
<table class="table table-striped table-hover table-condensed">
<!-- <tr> -->
<!-- 	<td>no</td> -->
<!-- 	<td class=>자동생성</td> -->
<!-- </tr> -->
<tr>
	<td>title</td>
	<td><input type="text" id="title" name="title"/></td>
</tr>
<tr>
	<td>adminid</td>
	<td>${adminid }</td>
</tr>
<tr>
	<td>adminname</td>
	<td>${adminname }</td>
</tr>
<tr>
	<td>content</td>
	<td><textarea id="content" name="content"></textarea></td>
</tr>

<!-- <tr> -->
<!-- 	<td>date</td> -->
<!-- 	<td>자동생성</td> -->
<!-- </tr> -->
<tr>
	<td>fileupload</td>
	<td><input type="file" name="file"/></td>
</tr>
</table>
<button>SUBMIT(SE없이테스트용)</button>
</form>
<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-info">SUBMIT</button>
	<button type="button" id="btnCancel" class="btn btn-danger">CANCEL</button>
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
</body>
</html>