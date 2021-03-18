<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 수정</title>
</head>
<body>
<form action="./MemberModify.me" method="post">
		<h1>회원정보 수정</h1>
		<fieldset>
			<legend>회원정보 수정</legend>
			<c:forEach var="member" items="${arrayList}">
			<div>
				성명<input type="text" name="name" value="${member.m_name}" required>
			</div>
			<div>
				비밀번호 <input type="password" name="password" value="${member.m_password}" required>
			</div>
			<div>
				이메일 <input type="text" name="email" value="${member.m_email}" required>
			</div>
			<div>
				핸드폰번호 <input type="text" name="phonenumber" value="${member.m_phonenumber}" required>
			</div>
			<div>
				주소 <input type="text" name="address" value="${member.m_address}" required>
			</div>
			</c:forEach>

			<div>
				<button type="submit">회원정보 수정</button>
			</div>
		</fieldset>
	</form>
</body>
</html>