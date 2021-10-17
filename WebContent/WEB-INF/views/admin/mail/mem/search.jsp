<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<style type="text/css">

table {
	text-align: center;
}

#searchtype {
	border: solid 1px #d96459; 
    border-radius: 5px;
    height: 30px;
    vertical-align: middle;
    text-align: center;
    width: 120px;
    padding: 3px 5px 3px 10px;
    -webkit-appearance: none;                                 /* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
}

#searchtype:focus {
    outline: none;

</style>

<div class="container">

<h2>메일 보내기 - 검색 결과</h2>
<hr>

<table class="table table-hover table-condensed">


<c:if test="${empty searchMemList }">
	<h3>검색 결과가 없습니다.</h3>
</c:if>

<c:if test="${not empty searchMemList }">
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

<tbody>
<c:forEach items="${searchMemList }" var="mem">
<tr>
	<td>${mem.memId }</td>
	<td>${mem.memNick }</td>
	<td>${mem.memMail }</td>
	<td>${mem.mailState }</td>
	<td>${mem.memDate }</td>
	<c:if test="${mem.mailState == 'y' }">
	<td><a href="<%=request.getContextPath() %>/admin/mail/mem/write?memmail=${mem.memMail }"><button>메일 보내기</button></a></td>	
	</c:if>
	
	<c:if test="${mem.mailState =='n' }">
	<td><input type="button" disabled value="메일 수신거부" /></td>
	</c:if>	

</tr>
</c:forEach>
</tbody>
</c:if>



</table>

</div>

<c:if test="${not empty searchMemList }">
<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />
</c:if>

<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/admin/mail/mem/search" method="get">
	<select id="searchtype" name="searchtype">
		<option value="memid">회원 아이디</option> 
		<option value="memnick">회원 닉네임</option>
	</select>
	<input type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요"/>
	<button id="buttonSearch">검색</button>
</form>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />