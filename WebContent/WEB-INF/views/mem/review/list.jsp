<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script>
function sortTable(n) {
	var table, rows, switching, o, x, y, shouldSwitch, dir, switchcount = 0;
		table = document.getElementById("inventory");
		switching = true;
		dir = "asc";
		
	while (switching) {
		switching = false;
		rows = table.getElementsByTagName("TR");
		
		for (o = 1; o < (rows.length - 1); o++){
			shouldSwitch = false;
			x = rows[o].getElementsByTagName("TD")[n];
			y = rows[o + 1].getElementsByTagName("TD")[n];
			
			if(dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
				shouldSwitch = true;
				break;
				}
			} else if (dir == "desc") {
				if(x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					shouldSwitch = true;
					break;
				}
			}
		}
		if(shouldSwitch) {
			rows[o].parentNode.insertBefore(rows[o + 1], rows[o]);
			switching = true;
			switchcount ++;
		} else {
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}
</script>


<div class="container" >
<h3 class="pull-left">Review</h3>
<hr>
<br>

<div class="text-right" id="umm..">
			<p>
			<a id="latest" >최신순</a>&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
			<a id="hit" >조회순</a>
</div>

<table id="inventory" class="table table-hover table-condensed table-center">

<thead>
<tr style="cursor: pointer;">
	<th style="width: 15%">게시글 번호</th>
	<th style="width: 40%">제목</th>
	<th style="width: 15%">작성자</th>
	<th style="width: 15%", onclick="sortTable(0)">조회수</th>
	<th style="width: 15%", onclick="sortTable(1)">작성일</th>
</tr>
</thead>

<tbody>
<c:forEach items="${reviewList }" var="review">
<tr>
	<td>${review.reviewNo }</td>
	<td>
		<a href="<%=request.getContextPath() %>/review/detail?reviewno=${review.reviewNo }">${review.reviewTitle }</a>
	</td>
	<td>${review.memId }</td>
	<td>${review.reviewHit }</td>
	<td>${review.reviewDate }</td>
</tr>
</c:forEach>
</tbody>
</table>

</div><br>


<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/review/search" method="get">
	<select id="searchtype" name="searchtype">
		<option value="reviewTitle">제목</option> 
	</select>
	<input type="text" id="keyword" name="keyword" placeholder="공연후기를 검색해보세요!"/>
	<button>검색</button>
</form>
</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />