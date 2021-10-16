<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>
<div class="container">

<h1>KAKAO</h1>

<c:if test="${socialagree }"><%-- 일반 가입자 + 소셜 미동의  --%>
${kakao }<br>
가입되어 있으나 카카오 계정과 연동하지 않으셨습니다.<br>
<form action="<%=request.getContextPath() %>/kakao" method="post">
<input type="hidden" name="kakaoagree" value="${kakao }"/>
<input type="hidden" name="ref" value="${header.referer }" /><%-- 로그인을 요청한 페이지 == 로그인 후 돌아갈 페이지 --%>
로그인을 위해 카카오 계정 로그인 상태 정보를 사용하게 되며 탈퇴 시까지 연동이 유지됩니다.<br><br>
<button>카카오 계정 연동 동의하기</button>
</form>
</c:if>

<c:if test="${socialjoin }"><%-- 미가입자 --%>
<form action="<%=request.getContextPath() %>/kakao" method="post">
<input type="hidden" name="kakaojoin" value="${kakao }"/>
${kakao }<br>
가입 정보가 없습니다. 카카오 계정 연동을 통해 공공연희를 이용하실 수 있도록 가입하시겠습니까?<br>
로그인을 위해 카카오 계정 로그인 상태 정보를 사용하게 되며 탈퇴 시까지 연동이 유지됩니다.<br><br>
<button>카카오 계정 연동 동의와 가입하기</button>
</form>
</c:if>

<%-- 로그인 상태일 때 주소 입력해서 접근 시 메인으로 리디렉션 --%>
<c:if test="${login }"><script>location.href="/";</script></c:if>

</div><!-- .container end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />