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

<h2>메일 보내기 - 검색된 회원들</h2>
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

</tr>
</thead>

<c:forEach items="${searchMemList }" var="mem">
</tbody>
<tr>
	<td>${mem.memId }</td>
	<td>${mem.memNick }</td>
	<td>${mem.memMail }</td>
	<td>${mem.mailState }</td>
	<td>${mem.memDate }</td>
	<td><a href="<%=request.getContextPath() %>/admin/mail/mem/write?memmail=${mem.memMail }"><button>메일 보내기</button></a></td>	

</tr>
</tbody>
</c:forEach>



</table>

</div>
<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />