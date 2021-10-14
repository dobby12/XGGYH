<%@page import="dto.XShow"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%
	List<XShow> searchShowList = (List) request.getAttribute("searchShowList");
%>

<c:import url="/WEB-INF/views/layout/adminheader.jsp"></c:import>

<style type="text/css">

table {
	text-align: center;
}

</style>

<div class="container" style="text-align: center;">

<h2>공연 목록 검색 결과</h2>
<button id="btnWrite" onclick="location.href='/admin/show/write';"style="margin: -25px 0 15px 0; float: right;">
	작성하기
</button>
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
<% for(int i=0 ; i<searchShowList.size(); i++) { %>
<tr>
	<td><%= searchShowList.get(i).getShowNo() %></td>
	<td><%= searchShowList.get(i).getAdminId() %></td>
	<td><a href="<%=request.getContextPath() %>/admin/show/detail?showno=<%=searchShowList.get(i).getShowNo() %>">
			<%= searchShowList.get(i).getShowTitle() %>
		</a>
	</td>
	<td><%= searchShowList.get(i).getShowDate() %></td>
	<td><%= searchShowList.get(i).getShowStart() %></td>
	<td><%= searchShowList.get(i).getShowEnd() %></td>
</tr>
<%} %>
</tbody>
</table>


</div>

<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />
<c:import url="/WEB-INF/views/layout/footer.jsp" />

