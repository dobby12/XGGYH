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
	

	//파일이 있을 경우
	if(${not empty file }) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 없을 경우
	if(${empty file }) {
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
	<td colspan="6" style="width: 100%; padding: 5px; height: 30px;"><input type="text" id="title" name="title" style="width: 100%; padding: 5px;" value="${notice.noticeTitle }"/></td>
</tr>
<tr>
	<td class="item" style="width:25%;">관리자 아이디</td>
	<td style="width:25%;">${adminid }<input type="hidden" name="adminId" value="${adminid }" /></td>
	<td class="item" style="width:25%;">관리자 이름</td>
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

<div style="text-align: left;">
	<div id="beforeFile">
		<c:if test="${notice.fileNo ne 0 }">
		기존 파일 : 
		<a href="/upload/${file.fileStoredName }" download="${file.fileOriginName }">${file.fileOriginName }</a>
		<span id="delFile" style="color: red; font: bold; cursor: pointer;">x</span>
		</c:if>
		<c:if test="${notice.fileNo eq 0 }">
		첨부파일이 없습니다.
		</c:if>
	</div>
	<br>
	<div id="afterFile">
		<input type="file" name="file" />
		<input type="hidden" name="fileDelete" id="fileDelete" value=""/>
	</div>
</div>

</form>

<br>
<%-- <a href="<%=request.getContextPath() %>/admin/notice/update?noticeno=${notice.noticeNo }"> --%>
<button type="button" id="btnSubmit" class="btnSubmit">수정</button>
<button type="button" id="btnBack" class="btnBack">취소</button>
</div><!-- .container end -->

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