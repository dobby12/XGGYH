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

<table class="table table-condensed table-bordered">

<tr>
	<td class="item" style="width: 10%">번호</td>
	<td rowspan="2" style="vertical-align: middle; font-weight: bold;">${viewNotice.noticeTitle }</td>
	<td class="item" style="width: 10%">작성일</td>
</tr>

<tr>
	<td>${viewNotice.noticeNo }</td>
	<td>${viewNotice.noticeDate }</td>	
</tr>

<!-- 첨부파일 있을 시 tr 하나 추가 -->
<c:if test="${not empty noticeFile }">
<tr>
	<td class="item" style="vertical-align: middle;">첨부파일</td>
	<td style="vertical-align: middle;">${noticeFile.fileOriginName }</td>
	<td><a href="/upload/${noticeFile.fileStoredName }" download="${noticeFile.fileOriginName }"><button class="btnSubmit">다운로드</button></a></td>
</tr>
</c:if>

<tr><td colspan="3" style="height: 400px; vertical-align: middle;">${viewNotice.noticeContent }</td></tr>

</table>

<button id="btnList" class="btnBack">목록</button>

</div><!-- .container end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
