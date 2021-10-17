<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<script type="text/javascript">

$(document).ready(function(){
	
	$("#btnList").click(function(){
		location.href="<%=request.getContextPath() %>/admin/show/list";
	})
	
	$("#btnUpdate").click(function(){
		
		var answer = confirm("공연 정보를 수정하시겠습니까?")
		
		if(answer == true){
			location.href="<%=request.getContextPath() %>/admin/show/update?showno=${viewShow.showNo }";
		} else {
			return false;
		}
		
	})
	
	$("#btnDelete").click(function() {
		
		var answer = confirm("공연 정보를 삭제하시겠습니까?")
		
		if( answer == true ){
			
			location.href = "<%=request.getContextPath() %>/admin/show/delete?showno=${viewShow.showNo}";
			
		} else {
			return false;
		}
	});
	
})

</script>

<style type="text/css">

input[type=text] {
	border: 1px solid rgba(217, 151, 113, 0.5);
	height: 24.48px;
}

</style>

<div class="container">

<h1>공연정보 상세보기</h1>
<hr>

<table class="table table-bordered">

<tr>
	<td colspan="10" style="font-size: 30px; font-weight: bold; width: 100%; padding: 5px; height: 50px;">${viewShow.showTitle }</td>
</tr>
<tr>
	<td class="item">작성자</td><td>${viewShow.adminId }</td>
	
	<td class="item">공연 종류</td><td>${showKind }</td>
	
	<td class="item">공연 장르</td><td>${showGenre }</td>
	
	<td class="item">관람등급</td><td>${viewShow.showAge }</td>
	
	<td class="item">작성일</td><td>${viewShow.showDate }</td>
</tr>
<tr>
	<td class="item">공연장</td><td>${showHall }</td>
	
	<td class="item">감독</td><td>${viewShow.showDirector }</td>
	
	<td class="item">배우</td><td>${viewShow.showActor }</td>
	
	<td class="item">시작일</td><td>${viewShow.showStart }</td>
	
	<td class="item">종료일</td><td>${viewShow.showEnd }</td>


</tr>
<c:if test="${not empty showFile }">
<tr>
		<td class="item">첨부파일</td>
		<td colspan="8">${showFile.fileOriginName }</td>
		<td><a href="/upload/${showFile.fileStoredName }" ><button>다운로드</button></a></td>
	</tr>
</c:if>
<tr>
<td colspan="10" style="width: 100%; height: 300px; padding: 10px; vertical-align: middle;">${viewShow.showContent }</td>
</tr>


</table>

<!-- 첨부파일 -->

<br>
<div class="button">	
	<button id="btnList" class="btnBack">목록</button>
	<button id="btnUpdate" class="btnUpdate">수정</button>
	<button id="btnDelete" class="btnDelete">삭제</button>
</div>



</div>
<c:import url="/WEB-INF/views/layout/footer.jsp" />

