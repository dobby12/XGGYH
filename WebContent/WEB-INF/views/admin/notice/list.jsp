<%@page import="dto.XNotice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>

<h1>ADMIN NOTICE</h1>
<hr>

<table>
<thead>
<tr>
		<th>no</th>
		<th>title</th>
		<th>content</th>
		<th>id</th>
		<th>file</th>
		<th>date</th>
</tr>
</thead>

<c:forEach items="${noticeList }" var="noticeList">
<tr>
	<td>${noticeList.noticeNo }</td>
	<td><a href="<%=request.getContextPath() %>/admin/notice/detail?noticeno=${noticeList.noticeNo }">${noticeList.noticeTitle }</a></td>
	<td>${noticeList.noticeContent }</td>
	<td>${noticeList.adminId }</td>
	<td><c:if test="${noticeList.fileNo ne 0 }">첨부됨</c:if></td>	
	<td>${noticeList.noticeDate }</td>
</tr>
</c:forEach>
</table>

<c:import url="/WEB-INF/views/layout/paging.jsp" />	<%-- @@@urlLink 수정하긔 --%>

<!------------------------------------------------------>
</body>
</html>