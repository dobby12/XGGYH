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
<h3><strong>${keyword }</strong> 에 대한 공연 제목 검색 결과</h3>

<hr>
<c:if test="${empty searchShowList }">
	<h3>검색 결과가 없습니다.</h3>
	<br>

</c:if>

<c:if test="${not empty searchShowList }">
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
</c:if>
<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/admin/show/search" method="get">
	<input type="text" id="keyword" name="keyword" placeholder="공연 제목을 입력하세요"/>
	<button>검색</button>
</form>
</div>


</div>

<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />
<c:import url="/WEB-INF/views/layout/footer.jsp" />

