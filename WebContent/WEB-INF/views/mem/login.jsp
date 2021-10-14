<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>

<div class="container">

<h1>LOGIN</h1>

<!------------------------로그인 안 했을 때 보여지는 영역------------------------->
<c:if test="${empty login }">
<c:if test="${not empty loginfail }">
로그인에 실패하였습니다. ID와 PW를 확인하세요.
</c:if>


<form action="<%=request.getContextPath() %>/login" method="post">
<input type="hidden" name="ref" value="${header.referer }" /><%-- 로그인을 요청한 페이지 == 로그인 후 돌아갈 페이지 --%>
<div>
	<label for="memid">ID</label>
	<div>
		<input type="text" id="memid" name="memid"/><br>
	</div>
</div>
<div>
	<label for="mempw">PW</label>
	<div>
		<input type="text" id="mempw" name="mempw"/><br><%-- @@@type="password" --%>
	</div>
</div>

<br>
<button type="submit">LOGIN</button>
</form>

<hr>

<a href="javascript:kakaoLogin();">KAKAO</a>

</c:if>
<!-------------------------@@@로그인 상태에선 아래 페이지 보여짐니당----------------------------->

<c:if test="${login }">
로그인 했는데 /login으로 접속했을 때 보여지는 영역
</c:if>

<!------------------------------------------------------>

<script src="http://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
window.Kakao.init("8b6ff5cc45fa05d18d5d5a27810f38f8");
function kakaoLogin() {
	window.Kakao.Auth.login({
		scope:'profile_nickname, account_email',
		success: function(authObj){
			console.log(authObj);
			window.Kakao.API.request({
				url:'/v2/user/me',
				success: res => {
					const kakao_account = res.kakao_account;
					console.log(kakao_account);
				}
			});
		}
	});
}
</script>

<!------------------------------------------------------>

</div><!-- .container end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
