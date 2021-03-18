<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 수정</title>
<link rel="shortcut icon"href="./img/favicon.ico"type="image/x-icon">
<link rel="stylesheet"type="text/css"href="./css/jboard.css">
</head>
<body>
<div id="contentsArea">
<section id="titlename">
<h1>상품 수정</h1>
<p class="formSign">
<strong class="require"> 필수</strong> 는 반드시 힙역하여야 하는 항목입니다.
</p>
<form action="./ProductModifyService.pr" method="post" id="joinForm" name="updateform"enctype="multipart/form-data">
<fieldset><legend>상품 수정</legend>
<input type="hidden" name="products_num"value="<c:out value='${VO.products_num}'/>"/>
<p>        
      <label for="products_name">냉장고 이름 <strong class="require">필수</strong></label>
      <c:out value='${VO.products_name}'/>        
      <input type="text" id="products_name" name="products_name" required value="<c:out value='${VO.products_name}'/>"/>     
      </p>   
<p><label for="category">냉장고 종류 <strong class="require">필수</strong></label> 
      <c:out value='${VO.category}'/>
      <select name="category" id="category" required >
      <option value="" >=======</option>
      <option value="일반 냉장고" >일반 냉장고</option>
      <option value="정수기 냉장고" >정수기 냉장고</option>
      <option value="양문형 냉장고" >양문형 냉장고</option>
      <option value="4도어 냉장고" >4도어 냉장고</option>
      </select></p> 
<p><label for="energy_efcnc">에너지 등급 <strong class="require">필수</strong></label> 
      <c:out value='${VO.energy_efcnc}'/>
      <select name="energy_efcnc" id="energy_efcnc"required >
      <option value="" >=======</option>
      <option value="1등급" >1등급</option>
      <option value="2등급" >2등급</option>
      <option value="3등급" >3등급</option>
      <option value="4등급" >4등급</option>
      <option value="5등급" >5등급</option>
      </select></p>
<p><label for="price">가격 <strong class="require">필수</strong></label>
      <c:out value='${VO.price}'/>           
      <input type="text" id="price"name="price" required value="<c:out value='${VO.price}'/>"/>     
      </p>
<p><label for="liter">용량 <strong class="require">필수</strong></label>  
       <c:out value='${VO.liter}'/> 
      <input type="text" id="liter" name="liter" required value="<c:out value='${VO.liter}'/> "/>     
      </p>                    
	
<c:if test="${!empty VO.image}">
<p>
<label for="image">파일 첨부</label><br/>
<c:out value="${VO.image}"/> &nbsp;&nbsp;&nbsp;

<input type="hidden" name="old_file" value="<c:out value='${VO.image}'/>"/>
</p>
</c:if>
<p>
<label for="old_file">이미지 첨부</label>
<input type="file" id="old_file" name="old_file">
</p>



<div class="btnJoinArea">
<button type="submit" class="btnOk">수정</button>
<button type="reset" class="btnCancle">취소</button>
<button type="button" class="btnOk" value="button" onclick="location.href='./ProductList.pr'">목록</button>
</div>
</fieldset>
</form>
</section>
</div>
</body>
</html>