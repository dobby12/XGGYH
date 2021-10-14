<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<h2>${myinfo.memNick }님의 회원정보</h2>
<hr>

	<div>
		<label for="memid" class="control-label">아이디</label>
		<label>${myinfo.memId }</label>
	</div>
	<div>
		<label for="memnick" class="control-label">닉네임</label>
		<label>${myinfo.memNick }</label>
	</div>
 	<div>
 		<label for="memmail" class="control-label">이메일</label>
		<label>${myinfo.memMail }</label>
	</div>
	<div>
		<label for="memstate" class="control-label">이메일수신여부</label>
		<label><c:if test="${myinfo.mailState eq 'y'}">수신</c:if></label>
		<label><c:if test="${myinfo.mailState eq 'n'}">거부</c:if></label>
	</div>
	<div>
		<label for="memkind" class="control-label">관심사</label>
		<label><c:if test="${myinfo.genreNo eq '1'}">코미디</c:if></label>
		<label><c:if test="${myinfo.genreNo eq '2'}">호러</c:if></label>
		<label><c:if test="${myinfo.genreNo eq '3'}">가족</c:if></label>
		<label><c:if test="${myinfo.genreNo eq '4'}">로맨스</c:if></label>
		<label><c:if test="${myinfo.genreNo eq '5'}">스포츠</c:if></label>
		<label><c:if test="${myinfo.genreNo eq '6'}">드라마</c:if></label>
		<label><c:if test="${myinfo.genreNo eq '7'}">어드벤처</c:if></label>
		<label><c:if test="${myinfo.genreNo eq '8'}">미스테리</c:if></label>
	</div>

	<div>
		<button type="button" id="btnJoin" onclick="location.href='<%=request.getContextPath()%>/mypage/myinfo/update'">수정</button>
		<button type="button" id="btnBack" >취소</button>
	</div>