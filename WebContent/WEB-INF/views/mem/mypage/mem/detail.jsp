<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style>

.container {
	width: 30%
}

</style>

<div class="container">

<h2>${myinfo.memNick } 님의 회원정보</h2>
<hr>

<table class="table table-bordered">
	<tr>
		<td class="item" style="width: 20%"><label for="memid">아이디</label></td>
		<td style="width: 20%">${myinfo.memId }</td>
	<tr>
	
	<tr>
		<td class="item" style="width: 20%"><label for="memnick">닉네임</label></td>
		<td style="width: 20%">${myinfo.memNick }</td>
	</tr>
	
 	<tr>
 		<td class="item" style="width: 20%"><label for="memmail">이메일</label></td>
		<td style="width: 20%">${myinfo.memMail }</td>
	</tr>
	
	<tr>
		<td class="item" style="width: 20%"><label for="memstate">이메일 수신여부</label></td>
		<td style="width: 20%"><c:if test="${myinfo.mailState eq 'y'}">수신</c:if>
		<c:if test="${myinfo.mailState eq 'n'}">거부</c:if></td>
	</tr>
	<tr>
		<td class="item" style="width: 20%"><label for="memkind">관심사</label></td>
		<td style="width: 20%"><c:if test="${myinfo.genreNo eq '1'}">코미디</c:if>
		<c:if test="${myinfo.genreNo eq '2'}">호러</c:if>
		<c:if test="${myinfo.genreNo eq '3'}">가족</c:if>
		<c:if test="${myinfo.genreNo eq '4'}">로맨스</c:if>
		<c:if test="${myinfo.genreNo eq '5'}">스포츠</c:if>
		<c:if test="${myinfo.genreNo eq '6'}">드라마</c:if>
		<c:if test="${myinfo.genreNo eq '7'}">어드벤처</c:if>
		<c:if test="${myinfo.genreNo eq '8'}">미스테리</c:if></td>
	</tr>
</table>

	<div>
		<button type="button" id="btnSubmit" class="btnSubmit" onclick="location.href='<%=request.getContextPath()%>/mypage/myinfo/update'">수정</button>
		<button type="button" id="btnBack" class="btnBack" onclick="location.href='<%=request.getContextPath()%>/mypage'">취소</button>
	</div>
	
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />