<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<tr>
		<td>문의 번호</td>
		<td>${comment.askNo }</td>
		<td>관리자 아이디</td>
		<td>${comment.adminId }</td>
	</tr>
	<tr>
		<td colspan="4">${comment.commentContent }</td>
	</tr>
	
</table>