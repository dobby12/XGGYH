<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />


<h1>게시글 상세보기</h1>
<hr>

<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${viewReview.review_no }</td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3">${viewReview.review_title }</td>
</tr>

<tr>
<td class="info">아이디</td><td>${viewReview.mem_id }</td>
<td class="info">닉네임</td><td>${memnick }</td>
</tr>

<tr>
<td class="info">조회수</td><td>${viewReview.review_hit }</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3">${viewReview.review_date }</td>
</tr>

<tr>
<td class="info">공연 제목</td><td colspan="3">${showtitle }</td>
</tr>

<tr>
<td class="info">별점</td><td colspan="3">${viewReview.review_score }</td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>

<tr><td colspan="4">${viewReview.review_content }</td></tr>

</table>

<!-- 첨부파일 -->
<div>
<c:if test="${not empty reviewFile }">
첨부파일 : ${reviewFile.file_origin_name }
</c:if>
</div>

<div class="button">
	<a href="<%=request.getContextPath() %>/admin/board/delete?boardno=${viewReview.review_no }"><button id="btnDelete">삭제</button></a>
</div>

</body>
</html>

