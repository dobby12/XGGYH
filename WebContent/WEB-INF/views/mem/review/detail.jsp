<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h3 class="pull-left">Review</h3>
<hr>

<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${viewReview.reviewNo }</td>
</tr>
<tr>
<td class="info">제목</td><td colspan="3">${viewReview.reviewTitle }</td>
</tr>
<tr>
<td class="info">아이디</td><td>${viewReview.memId }</td>
<td class="info">닉네임</td><td>${memNick }</td>
</tr>
<tr>
<td class="info">조회수</td><td>${viewReview.reviewHit }</td>
</tr>
<tr>
<td class="info">작성일</td><td colspan="3">${viewReview.reviewDate }</td>
</tr>
<tr>
<td class="info">공연 제목</td><td colspan="3">${showTitle }</td>
</tr>
<tr>
<td class="info">별점 평균</td><td colspan="3">${AvgReview }</td>
</tr>
<tr>
<td class="info">별점</td><td colspan="3">${viewReview.reviewScore }</td>
</tr>
<tr><td class="info"  colspan="4">본문</td></tr>
<tr><td colspan="4">${viewReview.reviewContent }</td></tr>
</table>

<div>
<c:if test="${not empty reviewFile }">
첨부파일 : <a href="/upload/${reviewFile.fileStoredName }" >${reviewFile.fileOriginName }</a>
</c:if>
</div><br><br><br><br>

<div class="button">
	<a href="<%=request.getContextPath() %>/review/list"><button id="btnList">목록</button></a>
	<c:if test="${memid eq viewReview.memId }">
		<a href="<%=request.getContextPath() %>/review/update?reviewno=${viewReview.reviewNo }"><button id="btnUpdate">수정</button></a>
		<a href="<%=request.getContextPath() %>/review/delete?reviewno=${viewReview.reviewNo }"><button id="btnDelete">삭제</button></a>
	</c:if>
</div>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />