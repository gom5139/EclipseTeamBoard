<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 정보</title>
</head>
<body>
<table border="1" style="text-align: center">
	<tr>
	<th width="50">Name</th><th width="50">ID</th><th width="200">E-MAIL</th><th width="50">PHONE</th><th width="100">ADDRESS</th>
	</tr>

	<c:forEach var="admin" items="${arrayList}">
	<c:if test="${!admin.m_id.equals('admin')}">
		<tr>
		<td>
		<c:out value="${admin.m_name}"></c:out>
		</td>
		<td>
		<c:out value="${admin.m_id}"></c:out>
		</td>
		<td>
		<c:out value="${admin.m_email}"></c:out>
		</td>
		<td>
		<c:out value="${admin.m_phonenumber}"></c:out>
		</td>
		<td>
		<c:out value="${admin.m_address}"></c:out>
		</td>
		</tr>
		</c:if>
	</c:forEach>
	</table>
	<button type="button" value="button" onclick="location.href='./ProductList.pr'" class="btnRT">   돌아가기 </button> 
</body>
</html>