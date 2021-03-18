<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
<form action="./LoginService.me" method="post">
	<table>
	
	<tr>
	<td width="70">
	아이디
	</td>
	<td width="35">
	<input type="text" name="m_id">
	</td>
	<td rowspan="2" >
	<input type="submit" value="로그인" style="height: 50px">
	</td>
	</tr>
	<tr>
	<td>
	비밀번호
	</td>
	<td>
	<input type="password" name="m_password">
	</td>
	</tr>
	</table>
	
	<button type="button" value="button" onclick="location.href='./main.jsp'">초기 화면</button>
	</form>
	
</body>
</html>