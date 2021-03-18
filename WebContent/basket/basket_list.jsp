<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장바구니 목록</title>
</head>
<body>
<fieldset>
<div style="float: right;">
		&nbsp;<button type="button" value="button"
			onclick="location.href='./Logout
			.me'">로그아웃</button>
	</div>
	<div style="float: right;">${m_id}님환영 합니다.</div>
	</fieldset>
	<br/>
<section>
	<table border="1" style="text-align: center">
		<thead>
			<tr>
				<th scope="col" class="bbsNumber" width="200">장바구니 번호</th>
				<th scope="col" class="bbsImage" width="200">사진</th>
				<th scope="col" class="bbsName" width="200">제품명</th>
				<th scope="col" class="bbsCategory" width="200">제품번호</th>
				<th scope="col" class="bbsPrice" width="200">가격</th>
			</tr>
		</thead>
		<c:forEach var="Basket" items="${BasketList}">
			<tbody>
				<tr>
					<%--글 번호를 표시한다.--%>
					<td><c:out value="${Basket.basketID}" /></td>
					<%-- 	<td><c:out value="${product.image}" /></td> --%>
					<td><img src="./productimage/${Basket.image}" width="150"
						height="150"></td>
					<td>
<a
						href="./ProductDetail.pr?goods_num=<c:out value="${Basket.goods_num}" />">
							<c:out value="${Basket.goods_name}" />
					</a>
					</td>

					<td><c:out value="${Basket.goods_num}" /></td>
					<td><c:out value="${Basket.price}" /></td>
					  
					<td>
					<a href="./BasketDeiete.bs?basketID=<c:out value="${Basket.basketID}"/>">          
 							<button type="button" class="btnOk"> 삭제 </button>        </a></td> 
				</tr>
			</tbody>
		</c:forEach>
	</table>
	</section>
	<button type="button" value="button" onclick="location.href='./ProductList.pr'" class="btnRT">   돌아가기 </button>
</body>
</html>