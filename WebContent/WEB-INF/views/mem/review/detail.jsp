<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/review/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/review/update?reviewno=${viewReview.reviewNo }");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/review/delete?reviewno=${viewReview.reviewNo }");
		}
	});
});

</script>

<div class="container">

<h1>리뷰 상세보기</h1>
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
<td class="info">별점</td><td colspan="3">${viewReview.reviewScore }</td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>

<tr><td colspan="4">${viewReview.reviewContent }</td></tr>

</table>

<div>
<c:if test="${not empty reviewFile }">
<a href="/upload/${reviewFile.fileStoredName }" download="${reviewFile.fileOriginName }">${reviewFile.fileOriginName }</a>
</c:if>
</div>

<div class="text-center">	
	<button id="btnList">목록</button>
	<button id="btnUpdate">수정</button>
	<button id="btnDelete">삭제</button>
</div>

</div>

</body>
</html>