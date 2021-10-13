<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnFindid").click(function(){$("form").submit()})
	$("#btnFindpw").click(function(){$("form").submit()})
})
</script>

<h1>FIND ID/PW</h1>


<form action="<%=request.getContextPath() %>/member/find" method="post">
<div>
@@@영문 입력 검사 합시다용@@@<br>


ID 찾기 : <input type="text" name="memmail" placeholder="이메일 주소를 입력하세요.">
<button type="button" id="btnFindid">ID찾기</button>
<br><br><br>

PW 찾기 : <input type="text" name="memid" placeholder="아이디를 입력하세요.">
<button type="button" id="btnFindpw">PW찾기</button>
</div>
</form>


</body>
</html>