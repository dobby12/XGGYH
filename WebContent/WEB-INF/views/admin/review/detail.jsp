<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<script>
$(document).ready(function(){
	$("#btnDelete").click(function(){
		
		var answer = confirm("리뷰를 정말 삭제하시겠습니까?")
		
		if( answer == true ){
			location.href = "<%=request.getContextPath() %>/admin/review/delete?reviewno=${viewReview.reviewNo }";
		} else {
			return false;
		}
	})
	
	$("#btnList").click(function(){
		
		history.back();
		
	})

})

</script>

<style type="text/css">

table {
	text-align: center;
}

</style>

<div class="container">

<h2>리뷰 상세보기</h2>
<hr>

<table class="table table-condensed table-bordered " >

<tr>
<td colspan="20">${viewReview.reviewTitle }</td>
</tr>

<tr>
<td colspan="4" >회원 아이디</td><td colspan="4">${viewReview.memId }</td>
<td colspan="4">회원 닉네임</td><td colspan="4">${memNick }</td>
<td colspan="2">조회수</td><td colspan="2">${viewReview.reviewHit }</td>
</tr>

<tr>
<td colspan="3">작성일</td><td colspan="3">${viewReview.reviewDate }</td>
<td class="3">공연 제목</td><td colspan="5">${showTitle }</td>
<td colspan="2">별점</td><td colspan="2">${viewReview.reviewScore }</td>

<tr><td colspan="20">${viewReview.reviewContent }</td></tr>

</table>

<!-- 첨부파일 -->
<div>
<c:if test="${not empty reviewFile }">
첨부파일 : ${reviewFile.fileOriginName }
</c:if>
</div>

<div class="button">
	<a href="<%=request.getContextPath() %>/admin/review/delete?reviewno=${viewReview.reviewNo }"><button id="btnDelete" class="btnDelete">삭제</button></a>
	<button id="btnList">목록으로 돌아가기</button>
	
</div>


</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />

