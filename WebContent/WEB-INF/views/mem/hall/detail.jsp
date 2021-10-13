<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h1>공연장 정보 페이지</h1>
<hr>

<table class="table table-bordered">

<tr>
<td class="info">공연장 이름</td><td >${hallInfo.hallName }</td>
</tr>

<tr>
<td class="info">공연장 주소</td><td >${hallInfo.hallAddress }</td>
</tr>

<tr>
<td class="info">공연장 전화번호</td><td >${hallInfo.hallCall }</td>
</tr>

<tr>
<td class="info">공연장 링크</td><td > <a href="${hallInfo.hallLink}">${hallInfo.hallLink}</a> </td>
</tr>

<tr>
<td class="info">공연장 위치</td><td >${hallInfo.hallLocation }</td>
</tr>

<tr>
<td class="info">공연장 오시는길</td><td >${hallInfo.hallNavigation }</td>
</tr>

</table>

</div>

</body>
</html>