<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보</title>
</head>
<body>
<div style="float: right;">
		&nbsp;<button type="button" value="button"
			onclick="location.href='./Logout.me'">로그아웃</button>
	</div>
	<div style="float: right;">${m_id}님환영 합니다.</div>
	<div id="countentsArea">
		<section id="titlename" class="iuserinforBoard">
			<h1>회원 정보</h1>
			<div id="infoArea">
			</div>
			</section>
			</div>
	<table style="text-align: center" border="1">
		<c:forEach var="member" items="${member}">


			<tr>
				<td colspan="2">${m_id}님의 회원정보</td>
			</tr>
			<tr>
				<td>사용자 이름</td>
				<td width="400"><c:out value="${member.m_name}"></c:out></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><c:out value="${member.m_email}"></c:out></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><c:out value="${member.m_phonenumber}"></c:out></td>
			</tr>
			<tr>
				<td>주소</td>
				<td width="50"><c:out value="${member.m_address}"></c:out></td>
			</tr>
		</c:forEach>

	</table>
	<div>
	<button type="button" value="button"
		onclick="location.href='./Modify.me'">회원정보 수정</button>
	<button type="button" value="button"
		onclick="location.href='./Delete.me'">회원정보 삭제</button>
	<button type="button" value="button" onclick="location.href='./ProductList.pr'" class="btnOk">   돌아가기 </button>  

		</div>
</body>
</html>