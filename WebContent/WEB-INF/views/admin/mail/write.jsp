<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>


<style type ="text/css">

table {
	text-align: center;
	vertical-align:middle;
	
}

</style>
<script>
//특수문자(<, >, \) 입력 방지 네이버SE2에는 적용 안 되므로 글 내용 작성에는 영향 없음
function characterCheck(obj){
 	var regExp = /[<>\\]/gi; 
	if( regExp.test(obj.value) ){
		alert("일부 특수문자는 입력하실수 없습니다.");
		obj.value = obj.value.substring( 0 , obj.value.length - 1 );
		}
}
</script>
<script type="text/javascript">

//<form>태그에 submit이 수행되면 스마트에디터에 작성한 내용을 <textarea>에 반영
function submitContents(elClickedObj){
	
	//에디터의 내용을 #content에 반영
	oEditors.getById["mailcontent"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		
		//<form>태그의 submit 수행
		elClickedObj.form.submit();
		
	} catch(e) {}
}

</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		var answer = confirm("메일을 발송하시겠습니까?");
		
		if( answer == true ){
			submitContents( $("#btnWrite") )
			
			$("form").submit();	
		} else {
			return false;
		}
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});

</script>

<div class="container">

<h2>메일 보내기</h2>

	<form action="<%=request.getContextPath() %>/admin/mail/write" method="post">
		<table class="table table-condensed table-bordered">
			
			<tr>
				<td class="item">보내는 이</td>
				<td style="font-weight: bold;">${adminMail }(공공연히)</td>
			</tr>
			<tr>
				<td class="item">받는 이</td>
				<td style="font-weight: bold;"><input type="text" name="memmail" autocomplete="off" style="width:100%;"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="mailtitle" placeholder="제목" style="width:100%;" /></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="mailcontent" id="mailcontent"
						style="width: 100%; height: 400px;"></textarea></td>
			 </tr>
		</table>
	</form>

<button id="btnWrite" class="btnSubmit">발송</button>
<button id="btnCancel" class="btnBack">취소</button>
</div>

<script type="text/javascript">

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "mailcontent",
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
});

</script>


<c:import url="/WEB-INF/views/layout/footer.jsp" />