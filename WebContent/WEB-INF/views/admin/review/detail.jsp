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
<td colspan="6" style="font-size: 30px; font-weight: bold; width: 100%; padding: 5px; height: 50px;">${viewReview.reviewTitle }</td>
</tr>

<tr>
	<td class="item">회원 아이디</td><td>${viewReview.memId }</td>
	<td class="item">조회수</td><td>${viewReview.reviewHit }</td>
	<td class="item">회원 닉네임</td><td>${memNick }</td>
</tr>	
<tr>	
	<td class="item">작성일</td><td>${viewReview.reviewDate }</td>
	<td class="item">공연 제목</td><td>${showTitle }</td>
	<td class="item">평점</td><td>${viewReview.reviewScore }</td>
</tr>
<c:if test="${not empty reviewFile }">
<tr>
	<td class="item">첨부파일</td>
	<td colspan="5"><a href="/upload/${reviewFile.fileStoredName }" >${reviewFile.fileOriginName }</a></td>
</tr>
</c:if>

<tr>
	<td colspan="6" style="width: 100%; height: 300px; padding: 10px; vertical-align: middle;">${viewReview.reviewContent }</td>
</tr>

</table>

<div class="button">
	<button id="btnList" class="btnBack">목록</button>
	<a href="<%=request.getContextPath() %>/admin/review/delete?reviewno=${viewReview.reviewNo }"><button id="btnDelete" class="btnDelete">삭제</button></a>
	
</div>


</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />

