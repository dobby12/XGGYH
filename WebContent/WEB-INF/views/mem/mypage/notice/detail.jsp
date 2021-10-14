<%@page import="dto.XNotice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/notice");
	});
});
</script>

<div class="container">

<h1>공지사항 상세보기</h1>
<hr>

<table class="table table-bordered">
<tr>
<td>번호</td><td colspan="3">${viewNotice.noticeNo }</td>
</tr>

<tr>
<td>제목</td><td colspan="3">${viewNotice.noticeTitle }</td>
</tr>

<tr>
<td>작성일</td><td colspan="3">${viewNotice.noticeDate }</td>
</tr>

<tr><td colspan="4">본문</td></tr>

<tr><td colspan="4">${viewNotice.noticeContent }</td></tr>

</table>

<!-- 첨부파일 -->
<div>
<c:if test="${not empty noticeFile }">
<a href="/upload/${noticeFile.fileStoredName }" download="${noticeFile.fileOriginName }">${noticeFile.fileOriginName }</a>
</c:if>
</div>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
</div>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />
