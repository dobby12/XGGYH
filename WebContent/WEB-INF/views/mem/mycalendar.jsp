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
		    {
		      id: 'gongyeon1',
		      title: 'my event',
		      start: '2021-10-15',
		      end: '2021-10-18'
		    }, 
		    {
		    	id: 'gongyeon2',
		    	title: 'gongyeon2',
		    	start: '2021-10-13',
		    	end: '2021-10-16'
		    	
		    }
		  ]

	});
	calendar.render();
	
	

});

</script>


<body>

<div id="container" class="container">

<div style ="width: 1000px; height: 800px;" id="calendar"></div>


<table>
<c:forEach items="${showList }" var="show">
<tr>
	<td>${show.showTitle }</td>
	<td>${show.showStart }</td>
	<td>${show.showEnd }</td>
	
</tr>
</c:forEach>
</table>

</div>
</body>
</html>