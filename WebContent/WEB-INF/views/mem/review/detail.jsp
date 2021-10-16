<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<div class="container">

<h3>리뷰 상세보기</h3>
<hr>

<table class="table table-bordered">
	<tr>
		<td class="item" style="width: 10%;">번호</td>
		<td rowspan="2" colspan="6" style="width: 80%;"><h4>${viewReview.reviewTitle }</h4></td>
		<td class="item">조회수</td>
	</tr>
	<tr>
		<td>${viewReview.reviewNo }</td>
		<td>${viewReview.reviewHit }</td>
	</tr>
	
	<tr>
		<td class="item">닉네임</td>
		<td style="width: 20%">${memNick }</td>
		<td class="item" style="width: 7.5%%">공연 제목</td>
		<td style="width: 20%"><a href="<%=request.getContextPath()%>/show/detail?showNo=${viewReview.showNo }" >${showTitle }</a></td>
		<td class="item" style="width: 5%">평점</td>
		<td style="width: 20%"><c:if test="${viewReview.reviewScore == 1 }">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			</c:if>
			<c:if test="${viewReview.reviewScore == 2 }">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			</c:if>
			<c:if test="${viewReview.reviewScore == 3 }">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			</c:if>
			<c:if test="${viewReview.reviewScore == 4 }">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			</c:if>
			<c:if test="${viewReview.reviewScore == 5 }">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			</c:if></td>
		<td class="item" style="width: 7.5%">작성일</td>
		<td>${viewReview.reviewDate }</td>
	</tr>

	<c:if test="${not empty reviewFile }">
		<tr>
			<td class="item">첨부파일</td>
			<td colspan="6">${reviewFile.fileOriginName }</td>
			<td><a href="/upload/${reviewFile.fileStoredName }" ><button class="btnSubmit">다운로드</button></a></td>
		</tr>
	</c:if>

	<tr>
		<td colspan="8" style="height: 400px;">${viewReview.reviewContent }</td>
	</tr>
	
</table>

<div>
</div><br><br>

<div class="button">
	<a href="<%=request.getContextPath() %>/review/list"><button id="btnList" class="btnBack">목록</button></a>
	<c:if test="${memid eq viewReview.memId }">
		<a href="<%=request.getContextPath() %>/review/update?reviewno=${viewReview.reviewNo }"><button id="btnUpdate" class="btnUpdate">수정</button></a>
		<a href="<%=request.getContextPath() %>/review/delete?reviewno=${viewReview.reviewNo }"><button id="btnDelete" class="btnDelete">삭제</button></a>
	</c:if>
</div>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />