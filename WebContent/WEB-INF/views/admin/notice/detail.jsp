<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/adminheader.jsp" />
<!------------------------------------------------------>

<style>

#content {
	vertical-align:middle;
}

</style>

<div class="container">


<h2>공지사항 세부 조회</h2>
<hr>

<table class ="table table-bordered table-condensed" style="">
<thead>
<tr>
	<td colspan="6" style="height: 40px; font-size: 30px;">${notice.noticeTitle }</td>
</tr>
<tr>
	<td style="background-color: #D99771">번호</td>
	<td>${notice.noticeNo }</td>
	<td style="background-color: #D99771">작성자</td>
	<td>${notice.adminId }</td>
	<td style="background-color: #D99771">작성일</td>
	<td>${notice.noticeDate }</td>
</tr>
<tr>
	<td id="content" colspan="6" style="height: 300px;">${notice.noticeContent }</td>
</tr>
</thead>
</table>

<c:if test="${notice.fileNo ne 0 }">
@@@첨부 파일 있을 때 보여질 영역<br>
<a href="/upload/${file.fileStoredName }" download="${file.fileOriginName }">${file.fileOriginName }</a>
</c:if>
<c:if test="${notice.fileNo eq 0 }">
@@@첨부 파일 없을 때 보여질 영역<br>
@@@첨부파일이 없습니다.
</c:if>

<br>
<a href="<%=request.getContextPath() %>/admin/notice/list"><button class="btnBack">목록</button></a>
<a href="<%=request.getContextPath() %>/admin/notice/update?noticeno=${notice.noticeNo }"><button class="btnUpdate">수정</button></a>
<a href="<%=request.getContextPath() %>/admin/notice/delete?noticeno=${notice.noticeNo }"><button class="btnDelete">삭제</button></a>

<!------------------------------------------------------>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />