<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/header.jsp" />



<div class="container" >
<h3 class="pull-left">리뷰 게시판</h3>
<hr>
<br>

<div class="text-right" id="umm..">
			<p>
			<a href="<%=request.getContextPath() %>/review/list">최신순</a>&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
			<a href="umm..">조회순</a>
</div>

<table class="table table-hover table-condensed">

<thead>
<tr>
	<th style="text-align: center; width: 10%">게시글 번호</th>
	<th style="text-align: center; width: 20%">공연</th>
	<th style="text-align: center; width: 42%">제목</th>
	<th style="text-align: center; width: 10%">작성자</th>
	<th style="text-align: center; width: 5%">조회수</th>
	<th style="text-align: center; width: 10%">작성일</th>
</tr>
</thead>


<c:forEach items="${reviewList }" var="review">
<tr>
	<td>${review.reviewNo }</td>
	<td>${showTitle }</td>
	<td>
		<a href="<%=request.getContextPath() %>/review/detail?reviewno=${review.reviewNo }">${review.reviewTitle }</a>
	</td>
	<td>${review.memId }</td>
	<td>${review.reviewHit }</td>
	<td>${review.reviewDate }</td>
</tr>
</c:forEach>
</table>

</div><br>


<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/review/search" method="get">
	<select type="hidden" id="searchtype" name="searchtype">
		<option value="reviewTitle">제목</option> 
	</select>
	<input type="text" id="keyword" name="keyword" placeholder="공연후기를 검색해보세요!"/>
	<button>검색</button>
</form>
</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />