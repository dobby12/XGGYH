<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">

//<form>태그에 submit이 수행되면 스마트에디터에 작성한 내용을 <textarea>에 반영
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
	$("#btnUpdate").click(function() {
		
		var answer = confirm("공연 정보를 수정하시겠습니까?")
		
		if( answer == true ){

			submitContents( $("#btnUpdate") )
			
			$("form").submit();
		
		} else {
			return false;
		}
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	if(${not empty file}) {
		$("#beforFile").show();
		$("#afterFile").hide();
	}
	
	//파일 삭제 버튼(x) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
	})
	
});
</script>

<script type="text/javascript">
    $(document).ready(function () {
            $.datepicker.setDefaults($.datepicker.regional['ko']); 
            $( "#startDate" ).datepicker({
                 showOn: "button",
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
            	 showOn: "button",
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

table, input, textarea {
	text-align: center;
}

</style>

<div class="container">

<h3>공연 정보 등록</h3>
<hr>

<div>
<form action="/admin/show/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="showNo" value="${xshow.showNo }" />
<table class="table table-striped table-condensed">

<tr>
	<td colspan="10"><h4><input type="text" id="showTitle" name="showTitle" autocomplete="off" style="width: 100%; padding: 5px;" value="${xshow.showTitle }"/></h4></td>
</tr>
<tr>
	<td>관리자 아이디</td>
	<td>${adminid }<input type="hidden" name="adminId" value="${adminid }" /></td>
	
	<td>공연 종류</td>
	<td>
		<select id="kindNo" name="kindNo">
			<option value="1" <c:if test="${xshow.kindNo == 1 }">selected</c:if>>뮤지컬</option>
			<option value="2" <c:if test="${xshow.kindNo == 2 }">selected</c:if>>연극</option>
			<option value="3" <c:if test="${xshow.kindNo == 3 }">selected</c:if>>콘서트</option>
			<option value="4" <c:if test="${xshow.kindNo == 4 }">selected</c:if>>오페라</option>
		</select>
	</td>
	
	<td>장르</td>
	<td>
		<select id="genreNo" name="genreNo">
			<option value="1" <c:if test="${xshow.genreNo == 1 }">selected</c:if>>코미디</option>
			<option value="2" <c:if test="${xshow.genreNo == 2 }">selected</c:if>>호러</option>
			<option value="3" <c:if test="${xshow.genreNo == 3 }">selected</c:if>>가족</option>
			<option value="4" <c:if test="${xshow.genreNo == 4 }">selected</c:if>>로맨스</option>
			<option value="5" <c:if test="${xshow.genreNo == 5 }">selected</c:if>>스포츠</option>
			<option value="6" <c:if test="${xshow.genreNo == 6 }">selected</c:if>>드라마</option>
			<option value="7" <c:if test="${xshow.genreNo == 7 }">selected</c:if>>어드벤처</option>
			<option value="8" <c:if test="${xshow.genreNo == 8 }">selected</c:if>>미스테리</option>
		</select>
	</td>
	
	<td>관람등급</td>
	<td>
		<select id="showAge" name="showAge">
			<option value="7세이하관람불가" <c:if test="'7세이하관람불가'.equals(${xshow.showAge })">selected</c:if>>7세이하관람불가</option>
			<option value="12세이하관람불가" <c:if test="'12세이하관람불가'.equals(${xshow.showAge })">selected</c:if>>12세이하관람불가</option>
			<option value="15세이하관람불가" <c:if test="'15세이하관람불가'.equals(${xshow.showAge })">selected</c:if>>15세이하관람불가</option>
			<option value="19세이하관람불가" <c:if test="'19세이하관람불가'.equals(${xshow.showAge })">selected</c:if>>19세이하관람불가</option>
		</select>
	</td>
	<td>공연장</td>
	<td>
		<select id="hallNo" name="hallNo">
			<option value="1" <c:if test="${xshow.hallNo == 1 }">selected</c:if>>LG아트센터</option>
			<option value="2" <c:if test="${xshow.hallNo == 2 }">selected</c:if>>광림아트센터 BBCH홀</option>
			<option value="3" <c:if test="${xshow.hallNo == 3 }">selected</c:if>>예술의전당 오페라극장</option>
			<option value="4" <c:if test="${xshow.hallNo == 4 }">selected</c:if>>금정문화회관</option>
			<option value="5" <c:if test="${xshow.hallNo == 5 }">selected</c:if>>부산광역시문화회관대극장</option>
			<option value="6" <c:if test="${xshow.hallNo == 6 }">selected</c:if>>백령아트센터</option>
			<option value="7" <c:if test="${xshow.hallNo == 7 }">selected</c:if>>천안예술의전당 대공연장</option>
			<option value="8" <c:if test="${xshow.hallNo == 8 }">selected</c:if>>구미문화예술회관</option>
			<option value="9" <c:if test="${xshow.hallNo == 9 }">selected</c:if>>여수 예울마루</option>
			<option value="10" <c:if test="${xshow.hallNo == 10 }">selected</c:if>>제주아트센터</option>
		</select>
	</td>
</tr>

<tr>
	<td>감독</td>
	<td colspan="2"><input type="text" id="showDirector" name="showDirector" value="${xshow.showDirector }"  autocomplete="off" /></td>
	
	<td>배우</td>
	<td colspan="2"><input type="text" id="showActor" name="showActor" value="${xshow.showActor }"  autocomplete="off" /></td>

	<td>공연 시작일</td>
	<td><input type="text" id="startDate" name="startDate" style="cursor: default;" value="${xshow.showEnd }" autocomplete="off" readonly/></td>
	
	<td>공연 종료일</td>
	<td><input type="text" id="endDate" name="endDate" style="cursor: default;" value="${xshow.showStart }" autocomplete="off" readonly/></td>
</tr>

<tr>
	<td colspan="10"><textarea id="showContent" name="showContent" style="width: 100%; height: 300px; padding: 10px;">${xshow.showContent }</textarea></td>
</tr>

</table>
<div>
	<div id="beforeFile">
		기존 파일 : 
		<a href="/upload/${file.fileStoredName }" download="${file.fileOriginName }">${file.fileOriginName }</a>
		<span id="delFile" style="color: red; font: bold; cursor: pointer;">x</span>
	</div>
	
	<div id="afterFile">
		첨부 파일 : 
		<input type="file" name="file" />	
	</div>
</div>

</form>
</div>

<div class="text-center" style="margin: 0 0 25px 0;">	
	<button type="button" id="btnUpdate">수정</button>
	<button type="button" id="btnCancel">취소</button>
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