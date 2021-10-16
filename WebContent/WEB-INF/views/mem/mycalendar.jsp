<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

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
              end: '${item.showEnd}'
            },
            </c:forEach>
      	]

	});
	calendar.render();
	
	

});

</script>

<body>

<div id="container" class="container">

<div style ="width: 1000px; height: 800px;" id="calendar"></div>

</div>
</body>
</html>