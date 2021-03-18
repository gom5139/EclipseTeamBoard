<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qna 게시판</title>
</head>
<body>
<div style="float: right;">
		&nbsp;<button type="button" value="button" onclick="location.href='./Logout.me'">로그아웃</button>
	</div>
	<div style="float: right;">${m_id}님환영 합니다.</div>
	<div id="countentsArea">
		<section id="titlename" class="qnaBoard">
			<h1>Q n A게시판</h1>
			<div id="infoArea">
				<section class="search">
					<form name="search" action="./QnaSearchList.qa" method="post">
						<fieldset>
							<legend> 검색 </legend>
							<label for="keyword"></label> <select name="keyfield"
								class="b_search">
								<%--해당 항목을 기본 선택으로 지정하여 검색한다.--%>
								<option value="all" selected="selected">전체 검색</option>
								<option value="subject"
									<c:if test="${'subject' == keyfield}">selected="selected"</c:if>>
									제목</option>
								<option value="m_id"
									<c:if test="${'m_id' == keyfield}">selected="selected"</c:if>>
									글쓴이</option>
								<option value="content"
									<c:if test="${'content' == keyfield}">selected="selected"</c:if>>
									내용</option>
							</select> <input type="search" id="keyword" name="keyword" required="required" placeholder="검색어 입력">
							<button type="submit">검색</button>
							 <button type="button" value="button" onclick="location.href='./ProductList.pr'" class="btnOk"style="float: right; width: 100pt; height: 20pt;">   상품 페이지 </button>    
						</fieldset>
					</form>
				</section>
			</div>
			<p class="allPost">
				<%--전체 글의 개수를 호출한다.--%>
				전체 글: &nbsp; <strong><c:out value="${listcount}" /></strong>개
			</p>
			<table class="QnaTable">
				<caption>질문 답변 게시판 리스트</caption>
				<%--게시글이 존재할 조건을 지정한다.--%>
				<c:choose>
					<c:when test="${listcount>0}">
						<thead>
							<tr>
								<th scope="col" class="bbsNumber">번호</th>
								<th scope="col" class="bbsTitle">제목</th>
								<th scope="col" class="bbsAuthor">글쓴이</th>
								<th scope="col" class="bbsDate">등록일</th>
								<th scope="col" class="bbsHit">조회수</th>
							</tr>
						</thead>
						<%--해당 페이지에 저장된 글을 호출한다. --%>
						<c:forEach var="qna" items="${qnaList}">
							<tbody>
								<tr>
									<%--글 번호를 표시한다.--%>
									<td><c:out value="${qna.num}" /></td>
									<td>
										 <c:if test="${!empty qna.answer_lev}">
											<c:forEach var="j" begin="0" end="${qna.answer_lev*2}" step="1">&nbsp; </c:forEach>
										</c:if> 
										<a href="./QnaDetail.qa?num=<c:out value="${qna.num}" />">
										<c:out value="${qna.subject}" />
									</a>
									</td>
									<td><c:out value="${qna.m_id}" /></td>
									<td><c:out value="${qna.write_date}" /></td>
									<td><c:out value="${qna.read_count}" /></td>
								</tr>
							</tbody>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
			<div align="center">
				<table id="QnaTableNe" class="QnaTableNe">
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
										<a href="./QnaList.qa?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp; 
</c:otherwise>
								</c:choose> <c:forEach var="start" begin="${startpage}" end="${endpage}"
									step="1">
									<c:choose>
										<c:when test="${start eq page}"> [<c:out
												value="${start}" />] 
</c:when>
										<c:otherwise>
											<a href="./QnaList.qa?page=<c:out value="${start}" />">[<c:out value="${start}" />]
											</a>&nbsp; 
</c:otherwise>
									</c:choose>
								</c:forEach> <c:choose>
									<c:when test="${page >= maxpage}">[다음] </c:when>
									<c:otherwise>
										<a href="./QnaList.qa?page=<c:out value="${page+1}" />">[다음]</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="btnJoinAreb">
					<button type="button" value="button"
						onclick="location.href='./QnaWrite.qa'" class="btnOk">
						글쓰기</button>
				</div>
			</div>
		</section>
	</div>
</body>
</html>