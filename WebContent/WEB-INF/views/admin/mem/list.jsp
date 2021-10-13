<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<style type="text/css">

table {
	text-align: center;
}

</style>

<div class="container">

<h2>회원정보</h2>
<hr>

<table class="table table-hover table-condensed">

<thead>
<tr>	
	<th style="text-align: center; width: 15%">회원 아이디</th>
	<th style="text-align: center; width: 15%">회원 닉네임</th>
	<th style="text-align: center; width: 20%">회원 메일 주소</th>
	<th style="text-align: center; width: 10%">메일 수신 여부</th>
	<th style="text-align: center; width: 15%">가입 날짜</th>
	<th style="text-align: center; width: 15%">　　　</th>
	<th style="text-align: center; width: 7%">　　　</th>
</tr>
</thead>

<c:forEach items="${memList }" var="mem">
</tbody>
<tr>
	<td>${mem.memId }</td>
	<td>${mem.memNick }</td>
	<td>${mem.memMail }</td>
	<td>${mem.mailState }</td>
	<td>${mem.memDate }</td>
	<td><a href="<%=request.getContextPath() %>/admin/mem/review?memid=${mem.memId }"><button>작성한 리뷰 보기</button></a></td>	
	<td><a href="<%=request.getContextPath() %>/admin/mem/delete?memid=${mem.memId }"><button>삭제</button></a></td>	
</tr>
</tbody>
</c:forEach>


</table>

</div>
<c:import url="/WEB-INF/views/layout/paging.jsp" />

<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/admin/mem/search" method="get">
	<select id="searchtype" name="searchtype">
		<option value="memid">회원 아이디</option> 
		<option value="memnick">회원 닉네임</option>
	</select>
	<input type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요"/>
	<button>검색</button>
</form>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />