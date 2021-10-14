<%@page import="dto.XAsk"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<style type="text/css">

table {
	text-align: center;
}

</style>

<div class="container">

<h2>나의 문의내역</h2>
<hr>

<table class="table table-hover table-condensed">

<thead>
<tr>
	<th style="text-align: center; width: 10%">번호</th>
	<th style="text-align: center; width: 60%">제목</th>
	<th style="text-align: center; width: 10%">문의종류</th>
	<th style="text-align: center; width: 10%">작성일</th>
	<th style="text-align: center; width: 10%">답변여부</th>
</tr>
</thead>

<c:forEach items="${memidAskList }" var="ask">
</tbody>
<tr>
	<td>${ask.askNo }</td>
	<td><a href="<%=request.getContextPath() %>/mypage/myask/detail?askno=${ask.askNo }">${ask.askTitle }</a></td>
	<td>${ask.askKind }</td>
	<td>${ask.askDate }</td>
	<td><c:if test="${ask.askState eq 'y'}"> 답변완료 </c:if>
		<c:if test="${ask.askState eq 'n'}"> 접수중 </c:if></td>
</tr>
</tbody>
</c:forEach>

</table>

</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />

