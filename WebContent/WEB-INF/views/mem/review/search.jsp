<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">

table {
	text-align: center;
}

#searchtype {
	border: solid 1px #d96459; 
    border-radius: 5px;
    height: 30px;
    vertical-align: middle;
    text-align: center;
    width: 120px;
    padding: 3px 5px 3px 10px;
    -webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}

#searchtype:focus {
    outline: none;
}


</style>

<div class="container">

<h3><strong>${keyword }</strong> 에 대한 검색 결과</h3>
<hr>
<br>

<div class="text-right">
			<p>
			<a href="<%=request.getContextPath() %>/review/list">전체 보기</a>
</div>

<table class="table table-hover table-condensed table-center">

<thead>
<tr>
	<th style="width: 10%">게시글 번호</th>
	<th style="width: 60%">제목</th>
	<th style="width: 10%">작성자</th>
	<th style="width: 10%">조회수</th>
	<th style="width: 10%">작성일</th>
</tr>
</thead>

<c:forEach items="${searchReviewList }" var="review">
</tbody>
<tr>
	<td>${review.reviewNo }</td>
	<td>
		<a href="<%=request.getContextPath() %>/review/detail?reviewno=${review.reviewNo }">${review.reviewTitle }</a>
	</td>
	<td>${review.memId }</td>
	<td>${review.reviewHit }</td>
	<td>${review.reviewDate }</td>
</tr>
</tbody>
</c:forEach>


</table>
<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/review/search" method="get">
	<select id="searchtype" name="searchtype">
		<option id="search" class="search" value="reviewTitle">제목</option> 
	</select>
	<input type="text" id="keyword" name="keyword" placeholder="공연후기를 검색해보세요!"/>
	<button>검색</button>
</form>
</div>
</div>
<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />