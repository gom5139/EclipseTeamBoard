<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 탈퇴</title>
</head>
<body>
<div>
			<h1>회원 탈퇴</h1>
			<form action="./MemberDelete.me?id" method="post">
				<fieldset>
					<legend>회원 탈퇴</legend>
					<div>
						비밀번호
						<input type="password" name="password" required placeholder="비밀번호 입력"/>
					</div>
					<div>
						<button type="submit">회원탈퇴</button>
						
					</div>
				</fieldset>
			</form>
	</div>
</body>
</html>