<%@page import="dto.XAsk"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%
	List<XAsk> list = (List) request.getAttribute("list");
%>

<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<style type="text/css">

table {
	text-align: center;
	font-family: D2Coding;
}

</style>

<div class="container">

<h2>문의 목록</h2>

<hr>

<table class="table table-hover table-condensed">
<thead>
<tr>
	<th style="text-align: center; width: 7.5%">번호</th>
	<th style="text-align: center; width: 15%">작성자</th>
	<th style="text-align: center; width: 50%">제목</th>
	<th style="text-align: center; width: 7.5%">문의종류</th>
	<th style="text-align: center; width: 12.5%">작성일</th>
	<th style="text-align: center; width: 7.5%">답변여부</th>
</tr>
</thead>

<tbody>
<% for(int i=0 ; i<list.size(); i++) { %>
<tr>
	<td><%= list.get(i).getAsk_no() %></td>
	<td><%= list.get(i).getMem_id() %></td>
	<td><a href="<%=request.getContextPath() %>/admin/ask/detail?ask_no=<%=list.get(i).getAsk_no() %>">
			<%= list.get(i).getAsk_title() %>
		</a>
	</td>
	<td><%= list.get(i).getAsk_kind() %></td>
	<td><%= list.get(i).getAsk_date() %></td>
	<td><%= list.get(i).getAsk_state() %></td>
</tr>
<%} %>
</tbody>
</table>


</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />




</body>
</html>