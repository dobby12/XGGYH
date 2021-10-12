<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/adminheader.jsp" />
<!------------------------------------------------------>

<h1>ADMIN DETAIL</h1>
<hr>

<table>
<thead>
<tr>
	<td>no</td>
	<td>${notice.noticeNo }</td>
</tr>
<tr>
	<td>title</td>
	<td>${notice.noticeTitle }</td>
</tr>
<tr>
	<td>content</td>
	<td>${notice.noticeContent }</td>
</tr>
<tr>
	<td>id</td>
	<td>${notice.adminId }</td>
</tr>
<tr>
	<td>date</td>
	<td>${notice.noticeDate }</td>
</tr>
</thead>
</table>

<c:if test="${notice.fileNo ne 0 }">
@@@첨부 파일 있을 때 : <a href="/${file.fileStoredName }" download="${file.fileOriginName }">${file.fileOriginName }</a>
</c:if>
<c:if test="${notice.fileNo eq 0 }">
@@@첨부 파일 없습...
</c:if>

<br>
<a href="<%=request.getContextPath() %>/admin/notice/list"><button>LIST</button></a>
<a href="<%=request.getContextPath() %>/admin/notice/upadate?noticeno=${notice.noticeNo }"><button>MODIFY</button></a>
<a href="<%=request.getContextPath() %>/admin/notice/delete?noticeno=${notice.noticeNo }"><button>DELETE</button></a>

<!------------------------------------------------------>
</body>
</html>