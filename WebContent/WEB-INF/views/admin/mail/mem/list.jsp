<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<style type="text/css">

table {
	text-align: center;
}

#btnFloat {
	float: left;
	margin: 0 0 0 400px;
	
}

</style>

<script>       

//java로 check한 대상의 메일 주소 jsonText로 담아 보내기
var result = new Array();
function getCheckboxValue(event)  {
	  if(event.target.checked)  {
	    result.push(event.target.value);
	  } else {
		for(let i=0; i<result.length; i++){
			if(result[i]===event.target.value){
				result.splice(i,1);	
			}
		}
	  }
		var marshalResult = JSON.stringify(result);
		document.getElementById('marshalResult').value=marshalResult;
	}

</script>

<div class="container">

<h2>메일 보내기 화면</h2>
<hr>

<table class="table table-hover table-condensed">

<thead>
<tr>
	<th style="text-align: center; width: 6%">선택</th>
	<th style="text-align: center; width: 13%">회원 아이디</th>
	<th style="text-align: center; width: 13%">회원 닉네임</th>
	<th style="text-align: center; width: 18%">회원 메일 주소</th>
	<th style="text-align: center; width: 10%">메일 수신 여부</th>
	<th style="text-align: center; width: 15%">가입 날짜</th>

</tr>
</thead>

<tbody>
<c:forEach items="${memList }" var="mem">
<tr>
	<c:if test="${mem.mailState == 'y' }">
	<td><input id="${mem.memId }" type='checkbox' name='memmem' value='${mem.memMail }' onclick='getCheckboxValue(event)'/></td>
	</c:if>
	
	<c:if test="${mem.mailState =='n' }">
	<td></td>
	</c:if>
	
	<td><label for="${mem.memId }">${mem.memId }</label></td>
	<td><label for="${mem.memId }">${mem.memNick }</label></td>
	<td><label for="${mem.memId }">${mem.memMail }</label></td>
	<td><label for="${mem.memId }">${mem.mailState }</label></td>
	<td><label for="${mem.memId }">${mem.memDate }</label></td>
	
	
</tr>
</c:forEach>
</tbody>

</table>
</div>

<div class="btnZone">
<form action="<%=request.getContextPath() %>/admin/mail/mem/list" method="post">
<input type="hidden" id="marshalResult" name="marshalResult" value="${marshalResult }"/>
<button id="btnFloat">메일 보내기</button>
</form>
</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<div style="text-align: center; margin: 0 0 25px 0;" >
<form action="<%=request.getContextPath() %>/admin/mail/mem/search" method="get">
	<select id="searchtype" name="searchtype">
		<option value="memid">회원 아이디</option> 
		<option value="memnick">회원 닉네임</option>
	</select>
	<input type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요"/>
	<button id="buttonSearch">검색</button>
</form>
</div>







<c:import url="/WEB-INF/views/layout/footer.jsp" />