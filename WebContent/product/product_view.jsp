<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세보기</title>
<link rel="shortcut icon"href="./img/favicon.ico"type="image/x-icon">
<link rel="stylesheet"type="text/css"href="./css/jboard.css">
</head>
<body>
<div id="contentsArea">  
<section id="titlename">  
<h1> 제품 상세보기 </h1>  
	<div style="float:right;">
		${m_id}님 환영 합니다.
	</div> 
<p class="formSign">게시판의 글 내용입니다.</p>    
<div id="joinForm"> 
<input type="hidden" name="products_num" value="<c:out value='${productVO.products_num}'/>">   

 
<fieldset>    
<legend> 제품 상세 정보 내용</legend>
<table>
<tr>
<td>  <label for="category"> 구분 </label> </td>
<td>  <c:out value="${productVO.category}" /> </td> 
</tr> 
<tr>
<td>  <label for="products_name">  제품명 </label> </td>
<td>  <c:out value="${productVO.products_name}" /> </td> 
</tr>  
<tr>
<td>  <label for="energy_efcnc"> 에너지 등급  </label> </td>
<td>  <c:out value="${productVO.energy_efcnc}" /> </td> 
</tr>      
<tr>
<td>  <label for="image"> 제품 사진  </label> </td>
<td>  <img src="./productimage/${productVO.image}"width="200"  height="200"> </td> 
</tr>    
 <tr>
<td>  <label for="price"> 가격  </label> </td>
<td>  <c:out value="${productVO.price}" /></td> 
</tr>  
<tr>
<td>  <label for="energy_efcnc"> 용량 </label> </td>
<td>  <c:out value="${productVO.liter}" /> </td> 
</tr>  
 
 </table>
 

 <c:if test ="${m_id eq 'admin'}" >
  <a href="./ProductModify.pr?products_num=<c:out value="${productVO.products_num}"/>">          
 <button type="button" class="btnOk"> 수정 </button>        </a>  
 
  <a href="./ProductDeleteService.pr?products_num=<c:out value="${productVO.products_num}"/>">          
 <button type="button" class="btnOk"> 삭제 </button>        </a>  
  </c:if> 

	  <a href="./Basketadd.bs?products_num=<c:out value="${productVO.products_num}"/>">          
 <button type="button" class="basketadd"> 장바구니 담기 </button>        </a>  
  

 <button type="button" value="button" onclick="location.href='./ProductList.pr'" class="btnOk">   돌아가기 </button>      
 
 </fieldset>    
 </div>  
 </section>  
 </div> 
</body>
</html>