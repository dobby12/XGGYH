<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h1>공지사항</h1>
<hr>

<table class="table table-hover table-condensed">
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성일</th>
</tr>

<c:forEach items="${noticeList }" var="notice" varStatus="status">
<tr>
	<td>${notice.noticeNo }</td>
	<td style="width: 80%"><a href="<%=request.getContextPath() %>/notice/detail?noticeno=${notice.noticeNo }">
		${notice.noticeTitle }
		</a><c:if test="${notice.fileNo ne 0 }">&nbsp; &nbsp;<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span></c:if>
	</td>
	<td>${notice.noticeDate }</td>
</tr>
</c:forEach>

</table>

</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />