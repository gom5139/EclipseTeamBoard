<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 등록</title>
</head>
<body>
<div id="contentsArea"> 
 <section id="titlename">  
 <h1> 상품 등록 페이지 </h1>    
 <p class="formSign">      
 <strong class="require"> 필수 </strong> 는 반드시 입력하여야 하는 항목입니다.    </p>
<%--파일 업로드 할 수 있도록 설정한다.--%> 
<form action="./ProductAdd.pr" method="post" id="joinForm" name="boardform" enctype="multipart/form-data">    
<fieldset>    
<legend> 상품등록</legend>
      <p>        
      <label for="godds_name">냉장고 이름 <strong class="require">필수</strong></label>        
      <input type="text" id="products_name" name="products_name" required placeholder="LG전자 디오스 xxxxxxxx"/>     
      <%-- <%String id = session.getAttribute("id") %>  --%>
      </p>        

      <p>        
      <label for="category">냉장고 종류 <strong class="require">필수</strong></label> 
      <select name="category" id="category" required>
      <option value="" >=======</option>
      <option value="일반 냉장고" >일반 냉장고</option>
      <option value="정수기 냉장고" >정수기 냉장고</option>
      <option value="양문형 냉장고" >양문형 냉장고</option>
      <option value="4도어 냉장고" >4도어 냉장고</option>
      </select>     
      </p> 
      
        <p>        
      <label for="energy_efcnc">에너지 등급 <strong class="require">필수</strong></label> 
      <select name="energy_efcnc" id="energy_efcnc" required>
      <option value="" >=======</option>
      <option value="1등급" >1등급</option>
      <option value="2등급" >2등급</option>
      <option value="3등급" >3등급</option>
      <option value="4등급" >4등급</option>
      <option value="5등급" >5등급</option>
      </select>     
      </p>
      <p>        
      <label for="price">가격 <strong class="require">필수</strong></label>        
      <input type="text" id="price" name="price" required placeholder="8,000,000 = 800"/>     
      </p>
      <p>        
      <label for="liter">용량 <strong class="require">필수</strong></label>        
      <input type="text" id="liter" name="liter" required placeholder="870"/>     
      </p>                    
	<p>
      <label for="image">이미지 첨부 </label> 
      <input type="file" id="image" name="image" placeholder="이미지 첨부"/>      
      </p>      
      <div class="btnJoinArea">        
      <button type="submit" class="btnOk">글 등록</button>        
      <button type="reset" class="btnCancel">글 취소</button> 
      <button  type="button" value="button" onclick="location.href='./ProductList.pr'" class= "btnOk">   목록 </button>      
      </div>    
      </fieldset>  
      </form>  
      </section>  
      </div> 
</body>
</html>