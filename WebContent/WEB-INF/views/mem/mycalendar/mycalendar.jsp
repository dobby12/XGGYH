<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- fullcalendar CDN -->
<link href='/resources/fullcalendar/lib/main.css' rel='stylesheet' />
<script src='/resources/fullcalendar/lib/main.js'></script>

<script>

document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView : 'dayGridMonth' ,
		timeZone: 'UTC',
		
		events: [
            <c:forEach items="${showList }" var="item">
            {
              id: '${item.showNo}',
              title: '${item.showTitle}',
              start: '${item.showStart}',
              end: '${item.showEnd}',
              allDay: true
            },
            </c:forEach>
      	]

	});
	calendar.render();
	
});

</script>

<script>
$(document).ready(function () {
	
	$('buttonDelete').on('click', function () {
	
	
	})
	
})

</script>


<style>
.fc-event-title-container {
	background-color: #D96459;
	
}

.fc-h-event {
	border: 0px;
}




.first {
    float: left;
}

.second{
    float: right;
    height: 500px;
    overflow: auto;
  
}


</style>

<body>


<div class="container">

<div class="first" style ="width: 600px; height: 1200px;" id="calendar"></div>

<div class="second">


<c:if test="${not empty showList }">
<h3>내가 찜한 공연</h3>
<br>
<table class="table table-hover table-condensed table-bordered" style="width: 500px;">
<thead>
	<tr>
		<td>공연 제목</td>
		<td>공연기간</td>
		<td></td>
	</tr>
</thead>

<c:forEach items="${showList }" var="show">
</tbody>
	<tr>
		<td>${show.showTitle }</td>
		<td>${show.showStart } ~ ${show.showEnd }</td>
		
		<td>
		<a href="<%=request.getContextPath() %>/mem/jjim/delete?showNo=${show.showNo }&memId=${memid }&from=mycalendar">
		<button id="btnDelete" class="btnDelete">찜 해제</button></a>
		</td>
		
	</tr>
</tbody>



</c:forEach>

</table>
<c:import url="/WEB-INF/views/layout/parameterPaging.jsp" />
</c:if>

<c:if test="${empty showList}">
<div class="third>" style="width: 500px">
<br><br><br><br><br><br>
<h3>찜한 공연이 없습니다.</h3>
<br>
<a href="<%=request.getContextPath() %>/main"><button class="btnSubmit">공연 찜하러 가기</button></a>

</div>
</c:if>

</div> <!-- div second end -->
</div> <!-- div container end -->



<c:import url="/WEB-INF/views/layout/footer.jsp" />

