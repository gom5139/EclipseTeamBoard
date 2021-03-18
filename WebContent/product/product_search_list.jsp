<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html> <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 검색 목록</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon"> 
<link rel="stylesheet" type="text/css" href="./css/jboard.css"> 
</head>
<body>
<div id="contentsArea">  
<section id="titlename" class="qnaBoard">  
<h1>제품 검색 결과</h1>  
<p class="allPost">    검색 된 제품: &nbsp; <strong><c:out value="${searchlistcount}" /></strong>개  </p>  
<table class="boardTable" style="text-align: center">  
<caption >선택하신 "<c:out value="${category}"/>"의 제품입니다. </caption>  
<tr/>
<c:choose>  
<c:when test="${searchlistcount>0}">    
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
<tbody>
<c:forEach var="productSL" items="${searchBoardlist}">
							<tbody>
								<tr>
								<td></td>
									<%--글 번호를 표시한다.--%>
									<td><c:out value="${productSL.products_num}" /></td>
									<%-- 	<td><c:out value="${product.image}" /></td> --%>
									<td><img src="./productimage/${productSL.image}" width="150"
										height="150"></td>
									<td>
										<%--답변 글을 표시한다.--%> <%-- <c:if test="${!empty board.answer_lev}">
											<c:forEach var="j" begin="0" end="${board.answer_lev*2}"
												step="1">&nbsp;             
</c:forEach>
										</c:if> 글 제목을 클릭했을 때 글 내용 보기 요청한다. --%> <a
										href="./ProductDetail.pr?products_num=<c:out value="${productSL.products_num}" />">
											<c:out value="${productSL.products_name}" />
									</a>
									</td>
									<td><c:out value="${productSL.category}" /></td>
									<td><c:out value="${productSL.energy_efcnc}" /></td>
									<td><c:out value="${productSL.price}" /></td>
									<td><c:out value="${productSL.liter}" /></td>
								</tr>
							</tbody>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>


  <div align="center">  
  <table id="boardTableNe" class="boardTableNe">    
  <tbody>      
  <c:if test="${searchlistcount==0}">      
  <tr>        
  <td colspan="4"></td>        
  <td>검색된 글이 없습니다</td>      
  </tr>      
  </c:if>      
  <tr>        
  <td colspan="5">        
  <c:choose> <c:when test="${page <= 1}"> [이전]&nbsp; 
  </c:when> 
  <c:otherwise>  
  <a href="./BoardSearchList.qa?page=${page-1}&category=${category}">[이전]</a>&nbsp; 
  </c:otherwise>       
  </c:choose> 
  <c:forEach var="start" begin="${startpage}" end="${endpage}" step="1"> 
  <c:choose>   
  <c:when test="${start eq page}">[<c:out value="${start}" />]</c:when>   
  <c:otherwise> <a href="./BoardSearchList.qa?page=${start}&category=${category}">[<c:out value= "${start}" />]</a>&nbsp;    
  </c:otherwise>
   </c:choose> 
   </c:forEach>         
   <c:choose>         
    <c:when test="${page >= maxpage}">[다음] 
    </c:when>          
    <c:otherwise>     
    <a href="./BoardSearchList.qa?page=${page+1}&category=${category}">[다음]</a>           
    </c:otherwise>        
    </c:choose>        
    </td>      
    </tr>    
    </tbody>  
    </table>
   <div class="btnJoinAreb"> 
   <button type="button" value="button" onclick="location.href='./ProductList.pr'" class= "btnOk">      목록    </button>  
   </div>  
   </div>  
   </section> 
   </div> 
</body>
</html>