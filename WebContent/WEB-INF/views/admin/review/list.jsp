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

<h1>게시글 목록</h1>
<hr>

<table>
<c:forEach items="${reviewList }" var="review">

<tr>
	<td>리뷰 번호</td>
	<td>공연 번호</td>
	<td>파일 번호</td>
	<td>작성자</td>
	<td>제목</td>
	<td>내용</td>
	<td>작성 날짜</td>
	<td>평점</td>
	<td>조회수</td>

</tr>


<tr>
	<td>${review.review_no }</td>
	<td>${review.show_no }</td>
	<td>${review.file_no }</td>
	<td>${review.mem_id }</td>
	<td>${review.review_title }</td>
	<td>${review.review_content }</td>
	<td>${review.review_date }</td>
	<td>${review.review_score }</td>
	<td>${review.review_hit }</td>
</tr>
</c:forEach>


</table>



</body>
</html>