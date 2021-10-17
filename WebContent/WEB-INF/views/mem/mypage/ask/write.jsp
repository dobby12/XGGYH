<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">

function submitContents(elClickedObj){
	
	//에디터의 내용을 #content에 반영
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그의 submit 수행
		elClickedObj.form.submit();
		
	} catch(e) {}
}

</script>


<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnWrite").click(function() {
		
		var answer = confirm("문의를 작성하시겠습니까?");
		
		if( answer == true ){
			submitContents( $("#btnWrite") );
			
			$("form").submit();			
		} else {
			
			return false;
		}
		
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});
</script>

<style type="text/css">
#content {
/* 	width: 100%; */
	width: 98%;
}
</style>

<div class="container">

<h3>1:1문의</h3>
<br>

<div>
<form action="<%=request.getContextPath() %>/mypage/myask/write" method="post">

<table class="table table-condensed table-bordered">

<tr>
	<td class="item" style="width: 10%">제목</td>
	<td colspan="3">
		<input type="text" id="title" name="title" style="width: 100%; height: 30px;" placeholder="문의 제목을 입력해주세요."/>
	</td>
</tr>
<tr>
	<td class="item">작성자</td>
	<td style="width: 40%">${memnick }</td>
	<td class="item" style="width: 10%">문의유형</td>
	<td style="width: 40%;">
		<select id="kind" name="kind" class="kind" >
			<option value="회원정보">회원정보</option>
			<option value="공연정보">공연정보</option>
			<option value="기타문의">기타문의</option>
		</select>
	</td>
</tr>

<tr>
	<td colspan="4"><textarea id="content" name="content" style="height: 400px;"></textarea></td>
</tr>
</table>

</form>
</div>

<div>	
	<button type="button" id="btnWrite" class="btnSubmit">작성</button>
	<button type="button" id="btnCancel" class="btnBack">취소</button>
</div>

<!-- .container -->
</div>

<script type="text/javascript">

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
});

</script>

<c:import url="/WEB-INF/views/layout/footer.jsp" />