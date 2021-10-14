<%@page import="dto.XShow"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%
	List<XShow> list = (List) request.getAttribute("list");
%>

<c:import url="/WEB-INF/views/layout/adminheader.jsp"></c:import>

<style type="text/css">

table {
	text-align: center;
}
#flex-end {
	justify-content: flex-end;
}

</style>

<div class="container" style="text-align: center;">

<h2>공연 목록</h2>
<button id="btnWrite" onclick="location.href='/admin/show/write';" style="float: right; margin: -20px 0 10px 0;">작성</button>

<hr style="clear: both;">

<table style="clear: both;" class="table table-hover table-condensed">
<thead>
<tr>
	<th style="text-align: center; width: 7.5%">공연번호</th>
	<th style="text-align: center; width: 15%">관리자</th>
	<th style="text-align: center; width: 47.5%">제목</th>
	<th style="text-align: center; width: 10%">작성일</th>
	<th style="text-align: center; width: 10%">시작일</th>
	<th style="text-align: center; width: 10%">종료일</th>
</tr>
</thead>

<tbody>
<% for(int i=0 ; i<list.size(); i++) { %>
<tr>
	<td><%= list.get(i).getShowNo() %></td>
	<td><%= list.get(i).getAdminId() %></td>
	<td><a href="<%=request.getContextPath() %>/admin/show/detail?showno=<%=list.get(i).getShowNo() %>">
			<%= list.get(i).getShowTitle() %>
		</a>
	</td>
	<td><%= list.get(i).getShowDate() %></td>
	<td><%= list.get(i).getShowStart() %></td>
	<td><%= list.get(i).getShowEnd() %></td>
</tr>
<%} %>
</tbody>
</table>


</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/admin/show/search" method="get">
	<input type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요"/>
	<button>검색</button>
</form>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />

