<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script type="text/javascript">
function MemberCheck() {
	id = document.getElementById("id").value;
	if (id == "") {
		alert("아이디를 적어주세요.");
		document.getElementById("id").focus();
		return false;
	}
	
	name = document.getElementById("name").value;
	if (name == "") {
		alert("이름을 적어주세요.");
		document.getElementById("name").focus();
		return false;
	}
	
	password = document.getElementById("password").value;
	if (password == "") {
		alert("비밀번호를 적어주세요.");
		document.getElementById("password").focus();
		return false;
	}
	
	passwordcheck = document.getElementById("passwordcheck").value;
	if (passwordcheck == "") {
		alert("비밀번호 확인을 적어주세요.");
		document.getElementById("passwordcheck").focus();
		return false;
	} else if(passwordcheck != password){
		alert("비밀번호가 같지 않습니다.");
		document.getElementById("passwordcheck").focus();
		return false;
	}
	
	email = document.getElementById("email").value;
	atpos = email.indexOf("@");
	dotpos = email.indexOf(".");
	if (atpos < 1 || atpos+2 > dotpos || dotpos+2 > email.length) {
		alert("이메일을 적어주세요.");
		document.getElementById("email").focus();
		return false;
	}
	
	phonenumber= document.getElementById("phonenumber").value;
	if (phonenumber == "") {
		alert("전화번호를 적어주세요.");
		document.getElementById("phonenumber").focus();
		return false;
	}
	
	address = document.getElementById("address").value;
	if (address == "") {
		alert("주소를 적어주세요.");
		document.getElementById("address").focus();
		return false;
	}
	
}
</script>
</head>
<body>
<h1>회원가입</h1>
		<fieldset>
			<legend>회원가입</legend>
			<form action="./IdCheck.me" method="post">
				<table>
				<tr>
				<td width="100">아이디</td>
					<td><input type="text" name="id" id="id" value="${checkId}"></td>
					<td><button type="submit">중복체크</button></td>
					<td><c:choose>
						<c:when test="${check.equals('false')}">이미 사용중인 아이디입니다.</c:when>
						<c:when test="${check.equals('true')}">사용가능한 아이디입니다.</c:when>
					</c:choose>
					</td>
					</tr>
					</table>
				</form>
				
				
			<form action="./Member.me" method="post">
				<input type="hidden" name="id" id="id" value="${checkId}">
				<table>
				<tr>
					<td>성명</td>
					<td><input type="text" name="name" id="name"></td>
					<td></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" id="password"></td>
					<td></td>
				</tr>
				<tr>
					<td>비밀번호 체크</td>
					<td><input type="password" name="passwordcheck" id="passwordcheck"></td>
					<td></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" id="email"></td>
					<td></td>
				</tr>
				<tr>
					<td>핸드폰번호</td>
					<td><input type="text" name="phonenumber" id="phonenumber"></td>
					<td></td>
				</tr>

				<tr>
					<td>주소</td>
					<td><input type="text" name="address" id="address"></td>
					<td></td>
				</tr>
			</table>
			
			<div>
				<button type="submit" onclick="return MemberCheck()">회원가입</button>
				<button type="reset">초기화</button>
				 <button type="button" value="button" onclick="location.href='./main.jsp'">초기 화면</button>
			</div>
		</form>
	</fieldset>
</body>
</html>