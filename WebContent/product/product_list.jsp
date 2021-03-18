<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 목록</title>
</head>
<body>
<div style="float: right;">
		&nbsp;<button type="button" value="button"
			onclick="location.href='./Logout.me'">로그아웃</button>
	</div>
	<div style="float: right;">${m_id}님환영 합니다.</div>
	<div id="countentsArea">
		<section id="titlename" class="qnaBoard">
			<h1>LG DIOS</h1>
			<div id="infoArea">
				<section class="search">

					<form name="search" action="./ProductSearchList.pr" method="post">
						<fieldset>

							<legend>카테고리 </legend>
							<select name="category" id="category" required>
								<option value="">=======</option>
								<option value="일반 냉장고">일반 냉장고</option>
								<option value="정수기 냉장고">정수기 냉장고</option>
								<option value="양문형 냉장고">양문형 냉장고</option>
								<option value="4도어 냉장고">4도어 냉장고</option>
							</select>
							<!-- 	<input type="search" id="keyword" name="keyword" required="required" placeholder="검색어 입력"> -->
							<button type="submit">선택</button>

			&nbsp; <input type="button" value="MY PAGE" style="float: right; width: 70pt; height: 20pt;"  onclick="location.href='./MyPage.me'"> 
			&nbsp;<input type="button" value="QnA 게시판" style="float: right; width: 70pt; height: 20pt; margin-right: 10px" onclick="location.href='./QnaList.qa'"> 
			      <input type="button" value="내 장바구니" style="float: right; width: 70pt; height: 20pt; margin-right: 10px"onclick="location.href='./BasketList.bs'">
						</fieldset>
					</form>
</section>
</div>
					<%-- <c:set var="m_id" value="${m_id}" /> --%>
					<span style="line-height: 20%"><br/></span>
					<c:if test="${m_id eq 'admin'}">
                         <fieldset>
							<input type="button" value="상품 추가"style="float: right; width: 70pt; height: 20pt;" onclick="location.href='./ProductWrite.pr'"> 
							<input type="button" value="관리자 회원관리" style="float: right; width: 100pt; height: 20pt; margin-right: 10px"onclick="location.href='./AdminMember.ad'">
						</fieldset>
					</c:if>
				
				
			
			<p class="allPost">
				<%--전체 글의 개수를 호출한다.--%>
				전체 글: &nbsp; <strong><c:out value="${listcount}" /></strong>개
			</p>
			<table class="boardTable" style="text-align: center">
				<caption>D I O S</caption>
				<%--게시글이 존재할 조건을 지정한다.--%>
				<c:choose>
					<c:when test="${listcount>0}">
						<thead>
							<tr>
							<th width="100"></th>
								<th scope="col" class="bbsNumber" width="50">번호</th>
								<th scope="col" class="bbsImage" width="200">사진</th>
								<th scope="col" class="bbsName" width="200">제품명</th>
								<th scope="col" class="bbsCategory" width="200">분류</th>
								<th scope="col" class="bbsEng" width="100">에너지 등급</th>
								<th scope="col" class="bbsPrice" width="200">가격</th>
								<th scope="col" class="bbsLiter" width="70">용량 (L)</th>
							</tr>
						</thead>

						
						<c:forEach var="product" items="${productList}">
							<tbody>
							
								<tr><td></td><td><c:out value="${product.products_num}" /></td>
									                     <td><img src="./productimage/${product.image}" width="150"height="150"></td>
									<td>
									 <a href="./ProductDetail.pr?products_num=<c:out value="${product.products_num}" />">
									<c:out value="${product.products_name}" /></a>
									</td>
									<td><c:out value="${product.category}" /></td>
									<td><c:out value="${product.energy_efcnc}" /></td>
									<td><c:out value="${product.price}" /></td>
									<td><c:out value="${product.liter}" /></td>
								</tr>
							</tbody>
						</c:forEach>
					</c:when>
				</c:choose>
				
			</table>
			<div align="center">
				<table id="boardTableNe" class="boardTableNe">
					<tbody>
						<%--등록된 글이 없을 때 출력한다.--%>
						<c:if test="${searchlistcount==0}">
							<tr>
								<td colspan="4"></td>
								<td>등록된 글이 없습니다.</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="5">
								<%--페이지 이동 처리를 한다.--%> <c:choose>
									<c:when test="${page <= 1}"> [이전]&nbsp; </c:when>
									<c:otherwise>
										<a href="./ProductList.pr?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp; 
</c:otherwise>
								</c:choose> <c:forEach var="start" begin="${startpage}" end="${endpage}"
									step="1">
									<c:choose>
										<c:when test="${start eq page}"> [<c:out
												value="${start}" />] 
</c:when>
										<c:otherwise>
											<a href="./ProductList.pr?page=<c:out value="${start}" />">[<c:out
													value="${start}" />]
											</a>&nbsp; 
</c:otherwise>
									</c:choose>
								</c:forEach> <c:choose>
									<c:when test="${page >= maxpage}">[다음] </c:when>
									<c:otherwise>
										<a href="./ProductList.pr?page=<c:out value="${page+1}" />">[다음]</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- <div class="btnJoinAreb">
					<button type="button" value="button"
						onclick="location.href='./BoardWrite.pr'" class="btnOk">
						글쓰기</button>
				</div> -->
			</div>
		</section>
	</div>
</body>
</html>