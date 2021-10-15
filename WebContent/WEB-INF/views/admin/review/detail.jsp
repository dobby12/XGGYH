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

<table class="table table-condensed table-bordered" >

<tr>
<td colspan="8" style="font-size: 30px; font-weight: bold; width: 100%; padding: 5px; height: 50px;">${viewReview.reviewTitle }</td>
</tr>

<tr>
	<td class="item" colspan="2" >회원 아이디</td><td colspan="3">${viewReview.memId }</td>
	<td class="item" colspan="1">조회수</td><td colspan="2">${viewReview.reviewHit }</td>
</tr>

<tr>
	<td class="item" colspan="2">회원 닉네임</td><td colspan="3">${memNick }</td>
	<td class="item" colspan="1">작성일</td><td colspan="1">${viewReview.reviewDate }</td>

<tr>
	<td class="item" colspan="2">공연 제목</td><td colspan="3">${showTitle }</td>
	<td class="item" colspan="1">평점</td><td colspan="1">${viewReview.reviewScore }</td>
</tr>

<tr>
	<td colspan="8" style="width: 100%; height: 300px; padding: 10px; vertical-align: middle;">${viewReview.reviewContent }</td>
</tr>

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

