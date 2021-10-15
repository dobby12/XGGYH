<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<c:import url="/WEB-INF/views/layout/header.jsp" />
<!------------------------------------------------------>
<div class="container">

<h1>LOGIN</h1>

<c:if test="${loginfail }"><%-- 로그인 실패를 전달 받았을 때 --%>
@@@로그인에 실패하였습니다. 아이디와 패스워드를 확인하세요.
<button id="btnBack">뒤로가기</button>
</c:if>

<c:if test="${empty login }"><%-- 로그인이 아닐 때 --%>
<form action="<%=request.getContextPath() %>/login" method="post">
<input type="hidden" name="kakaoemail" value="" />
<input type="hidden" name="ref" value="${header.referer }" /><%-- 로그인을 요청한 페이지 == 로그인 후 돌아갈 페이지 --%>
<div>
	<label for="memid">@@@아이디</label>
	<div>
		<input type="text" id="memid" name="memid"/><br>
	</div>
</div>
<div>
	<label for="mempw">@@@패스워드</label>
	<div>
		<input type="text" id="mempw" name="mempw"/><br><%-- @@@type="password" --%>
	</div>
</div>
	<button>@@@로그인</button>
</form>
</c:if>

<%-- 카카오 로그인 --%>
<div>
<a href="javascript:void(0)"><img onclick="kakaoLogin();" id="btnSubmit" src="https://developers.kakao.com/tool/resource/static/img/button/login/simple/ko/kakao_login_small.png"></a>
</div>

<%-- 로그인 상태일 때 /login 입력해서 접근 시 메인으로 리디렉션 --%>
<c:if test="${login }"><script>location.href="/";</script></c:if>

</div><!-- .container end -->

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('8b6ff5cc45fa05d18d5d5a27810f38f8'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
        	  var kakaoemail = response.kakao_account.email;
        	  console.log(kakaoemail);
        	  $('input[name=kakaoemail]').attr('value', kakaoemail);
        	  $("form").submit();
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
<%-- 카카오 로그아웃 (일반 로그아웃을 사용하므로 필요 없을 듯)
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        	location.href="/";
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }
--%>
</script>
<c:import url="/WEB-INF/views/layout/footer.jsp" />