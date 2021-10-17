<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<style type="text/css">

table {
	text-align: center;
}

</style>

<script>

function selects(){  
    var ele=document.getElementsByName('chk');  
    for(var i=0; i<ele.length; i++){  
        if(ele[i].type=='checkbox')  
            ele[i].checked=true;  
    }  
} 

function deSelect(){  
    var ele=document.getElementsByName('chk');  
    for(var i=0; i<ele.length; i++){  
        if(ele[i].type=='checkbox')  
            ele[i].checked=false;  
          
    }  
}             

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
		document.getElementById('result').value=marshalResult;
	}

</script>

<div class="container">

<h2>메일 보내기 화면</h2>
<hr>

<table class="table table-hover table-condensed">

<thead>
<tr>
	<th style="text-align: center; width: 6%">전체 <input type="checkbox" id="checkAll" name="checkAll"></th>
	<th style="text-align: center; width: 13%">회원 아이디</th>
	<th style="text-align: center; width: 13%">회원 닉네임</th>
	<th style="text-align: center; width: 18%">회원 메일 주소</th>
	<th style="text-align: center; width: 10%">메일 수신 여부</th>
	<th style="text-align: center; width: 15%">가입 날짜</th>
	<th style="text-align: center; width: 15%">　　　</th>

</tr>
</thead>

<tbody>
<c:forEach items="${memList }" var="mem">
<tr>
	<td><input type='checkbox' name='memmem' value='${mem.memMail }' onclick='getCheckboxValue(event)'/></td>
	<td>${mem.memId }</td>
	<td>${mem.memNick }</td>
	<td>${mem.memMail }</td>
	<td>${mem.mailState }</td>
	<td>${mem.memDate }</td>
	
	<c:if test="${mem.mailState == 'y' }">
	<td><a href="<%=request.getContextPath() %>/admin/mail/mem/write?memmail=${mem.memMail }"><button>메일 보내기</button></a></td>	
	</c:if>
	
	<c:if test="${mem.mailState =='n' }">
	<td><input type="button" disabled value="메일 수신거부" /></td>
	</c:if>
	
</tr>
</c:forEach>
</tbody>


</table>


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



<form action="<%=request.getContextPath() %>/admin/mail/mem/write" method="post">
<input type="text" id="result" name="result" value="${marshalResult }"/>
<button>send</button>
</form>



<c:import url="/WEB-INF/views/layout/footer.jsp" />