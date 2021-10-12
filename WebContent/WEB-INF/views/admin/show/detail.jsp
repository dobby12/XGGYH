<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<div class="container">

<h1>공연정보 상세보기</h1>
<hr>

<table class="table table-bordered">

<tr>
<td class="info">공연 제목</td><td>${viewShow.showTitle }</td>
</tr>
<tr>
<td class="info">작성자</td><td>${viewShow.adminId }</td>
</tr>
<tr>
<td class="info">공연 종류</td><td>${showKind }</td>
</tr>
<tr>
<td class="info">공연 장르</td><td>${showGenre }</td>
</tr>
<tr>
<td class="info">작성일</td><td>${viewShow.showDate }</td>
</tr>
<tr>
<td class="info">공연 설명</td><td>${viewShow.showContent }</td>
</tr>
<tr>
<td class="info">공연장</td><td>${showHall }</td>
</tr>
<tr>
<td class="info">관람등급</td><td>${viewShow.showAge }</td>
</tr>
<tr>
<td class="info">감독</td><td>${viewShow.showDirector }</td>
</tr>
<tr>
<td class="info">배우</td><td>${viewShow.showActor }</td>
</tr>
<tr>
<td class="info">기간</td><td>${viewShow.showStart } ${viewShow.showEnd }</td>
</tr>



</table>

<!-- 첨부파일 -->
<div>
<c:if test="${not empty showFile }">
첨부파일 : ${showFile.fileOriginName }
</c:if>
</div>

<div class="button">
	<a href="<%=request.getContextPath() %>/admin/review/delete?showno=${viewShow.showNo }"><button id="btnDelete">삭제</button></a>
</div>



</div>
<c:import url="/WEB-INF/views/layout/footer.jsp" />

