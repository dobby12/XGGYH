<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/adminheader.jsp" />

<!-- 스마트에디터 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">

function submitContents(elClickedObj){
	
	//에디터의 내용을 #content에 반영
	oEditors.getById["showContent"].exec("UPDATE_CONTENTS_FIELD", []);
	
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
		
		var answer = confirm("공연 정보를 등록하시겠습니까?");
		
		if( answer == true ){
			
			var fileCheck = document.getElementById("input-file").value;
			
			if( !fileCheck ){
				
				alert("공연 파일을 업로드해주세요!");
				return false;
			} else {
				
			submitContents( $("#btnWrite") )
			
			$("form").submit();					
			}
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

<script type="text/javascript">
    $(document).ready(function () {
            $.datepicker.setDefaults($.datepicker.regional['ko']); 
            $( "#startDate" ).datepicker({
                 showOn: "focus",
            	 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yy-mm-dd"
 
            });
            $( "#endDate" ).datepicker({
            	 showOn: "focus",
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yy-mm-dd"
 
            });    
    });
</script>

<style type="text/css">

input[type=text] {
	border: 1px solid rgba(217, 151, 113, 0.5);
	height: 24.48px;
}
</style>

<div class="container">

<h3>공연 정보 등록</h3>
<br>

<div>
<form action="<%=request.getContextPath() %>/admin/show/write" method="post" enctype="multipart/form-data">

<table class="table table-condensed table-striped table-bordered">

<tr>
	<td colspan="10"><h4><input type="text" id="showTitle" name="showTitle" style="width: 100%; padding: 5px; height: 50px;" autocomplete="off" placeholder="공연 제목"/></h4></td>
</tr>
<tr>
	<td>관리자 아이디</td>
	<td>${adminid }<input type="hidden" name="adminId" value="${adminid }" /></td>
	
	<td>공연 종류</td>
	<td>
		<select id="kindNo" name="kindNo">
			<option value="1">뮤지컬</option>
			<option value="2">연극</option>
			<option value="3">콘서트</option>
			<option value="4">오페라</option>
		</select>
	</td>
	
	<td>장르</td>
	<td>
		<select id="genreNo" name="genreNo">
			<option value="1">코미디</option>
			<option value="2">호러</option>
			<option value="3">가족</option>
			<option value="4">로맨스</option>
			<option value="5">스포츠</option>
			<option value="6">드라마</option>
			<option value="7">어드벤처</option>
			<option value="8">미스테리</option>
		</select>
	</td>
	
	<td>관람등급</td>
	<td>
		<select id="showAge" name="showAge">
			<option value="7세이하관람불가">7세이하관람불가</option>
			<option value="12세이하관람불가">12세이하관람불가</option>
			<option value="15세이하관람불가">15세이하관람불가</option>
			<option value="19세이하관람불가">19세이하관람불가</option>
		</select>
	</td>
	<td>공연장</td>
	<td>
		<select id="hallNo" name="hallNo">
			<option value="1">LG아트센터</option>
			<option value="2">광림아트센터 BBCH홀</option>
			<option value="3">예술의전당 오페라극장</option>
			<option value="4">금정문화회관</option>
			<option value="5">부산광역시문화회관대극장</option>
			<option value="6">백령아트센터</option>
			<option value="7">천안예술의전당 대공연장</option>
			<option value="8">구미문화예술회관</option>
			<option value="9">여수 예울마루</option>
			<option value="10">제주아트센터</option>
		</select>
	</td>
</tr>

<tr>
	<td>감독</td>
	<td colspan="2"><input type="text" id="showDirector" name="showDirector" autocomplete="off" placeholder="감독 이름"/></td>
	
	<td>배우</td>
	<td colspan="2"><input type="text" id="showActor" name="showActor" autocomplete="off" placeholder="배우 이름"/></td>

	<td>공연 시작일</td>
	<td><input type="text" id="startDate" name="startDate" style="cursor: default;" autocomplete="off" readonly/></td>
	
	<td>공연 종료일</td>
	<td><input type="text" id="endDate" name="endDate" style="cursor: default;" autocomplete="off" readonly/></td>
</tr>

<tr>
	<td colspan="10"><textarea id="showContent" name="showContent" style="width: 100%; height: 300px; padding: 10px;"></textarea></td>
</tr>

</table>

<div style="text-align: left">
첨부파일 <input type="file" name="file" id="input-file" />
</div>

</form>
</div>

<div style="margin: 0 0 25px 0;">	
	<button type="button" id="btnWrite" class="btnSubmit">등록</button>
	<button type="button" id="btnCancel" class="btnBack">취소</button>
</div>

<!-- .container -->
</div>

<script type="text/javascript">

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "showContent",
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
});

</script>


<c:import url="/WEB-INF/views/layout/footer.jsp" />