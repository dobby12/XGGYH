<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>상세보기</h1>

<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${viewReview.review_no }</td>
</tr>

<tr>
<td class="info">후기 제목</td><td colspan="3">${viewReview.review_title }</td>

</tr>

<tr>
<td class="info">아이디</td><td>${viewReview.mem_id }</td>
<td class="info">닉네임</td><td>${mem_nick }</td>
</tr>

<tr>
<td class="info">공연 이름</td><td>${show_title }</td>
<td class="info">평점</td><td>${viewReview.review_score }</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3">${viewReview.review_date }</td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>

<tr><td colspan="4">${viewReview.review_content }</td></tr>

</table>

</body>
</html>