<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/adminheader.jsp" />
<!------------------------------------------------------>

<style type="text/css">

table {
	text-align: center;
}

tr, th, td {
	text-align: center;
}

</style>

<div class="container" style="text-align: center;">

<h2>공지사항 관리</h2>
<button id="btnWrite" onclick="location.href='/admin/notice/write';"style="margin: -25px 0 15px 0; float: right;">작성</button>

<hr style="clear: both;">

<table style="clear: both;" class="table table-hover table-condensed">
<thead>
<tr>
		<th style="width: 10%">공지 번호</th>
		<th style="width: 60%">제목</th>
<%-- 		<th>content</th> --%>
		<th style="width: 15%">작성자</th>
		<th style="width: 20%">작성일</th>
</tr>
</thead>

<c:forEach items="${noticeList }" var="noticeList">
<tbody>
<tr>
	<td>${noticeList.noticeNo }</td>
	<td><a href="<%=request.getContextPath() %>/admin/notice/detail?noticeno=${noticeList.noticeNo }">${noticeList.noticeTitle }</a><c:if test="${noticeList.fileNo ne 0 }">&nbsp; &nbsp;<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span></c:if></td>
<%-- 	<td>${noticeList.noticeContent }</td> --%>
	<td>${noticeList.adminId }</td>
	<td>${noticeList.noticeDate }</td>
</tr>
</tbody>
</c:forEach>
</table>

</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />