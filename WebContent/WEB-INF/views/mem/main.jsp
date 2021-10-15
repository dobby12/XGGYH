<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>

<div class="container">

<h1>MAIN</h1>


<c:if test="${empty login || not login }">
로그인 상태 아님
<hr>
	<strong>로그인 하지 않았을 때 보여질 영역</strong><br><br>
	<a href="<%=request.getContextPath() %>/join"><button>JOIN</button></a>
	<a href="<%=request.getContextPath() %>/login"><button>LOGIN</button></a>
</c:if>

<c:if test="${login }">
로그인 된 아이디 : ${memid }<br>
로그인 된 닉네임 : ${memnick }<br>
<hr>
	<strong>로그인 한 상태일 때 보여질 영역</strong><br><br>
	<a href="<%=request.getContextPath() %>/logout"><button>LOGOUT</button></a>
</c:if>

</div><!-- .container end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
