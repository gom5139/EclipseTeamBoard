<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html> <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qna게시판 검색 목록</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon"> 
<link rel="stylesheet" type="text/css" href="./css/jboard.css"> 
</head>
<body>
<div id="contentsArea">  
<section id="titlename" class="qnaBoard">  
<h1>게시판 검색 결과</h1>  
<p class="allPost">    검색 글: &nbsp; <strong><c:out value="${searchlistcount}" /></strong>개  </p>  
<table class="QnaTable">  
<caption>게시판 검색</caption>  
<c:choose>  
<c:when test="${searchlistcount>0}">    
<thead>      
<tr>        
<th scope="col" class="bbsNumber">번호</th>        
<th scope="col" class="bbsTitle">제목</th>        
<th scope="col" class="bbsAuthor">글쓴이</th>        
<th scope="col" class="bbsDate">등록일</th>        
<th scope="col" class="bbsHit">조회수</th>      
</tr>    
</thead>    
<tbody>

<c:forEach var="search" items="${searchQnalist}">      
<tr>        
<td><c:out value="${search.num}"/> </td>        
<td>        
<c:if test="${!empty search.answer_lev}"> 
<c:forEach var="j" begin="0" end="${search.answer_lev*2}" step="1">  &nbsp; </c:forEach>        
</c:if>  <a href="./QnaDetail.do?num=<c:out value="${search.num}"/>">        
<c:out value="${search.subject}"/></a>        
</td>
 <td><c:out value="${search.m_id}"/></td>        
 <td><c:out value="${search.write_date}"/></td>        
 <td><c:out value="${search.read_count}"/></td>      
 </tr>      
 </c:forEach>   
  </tbody>  
  </c:when>  
  </c:choose>  
  </table>  
  <div align="center">  
  <table id="QnaTableNe" class="QnaTableNe">    
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
  <a href="./QnaSearchList.qa?page=${page-1}&keyword=${keyword}&keyfield=${keyfield}">[이전]</a>&nbsp; 
  </c:otherwise>       
  </c:choose> 
  <c:forEach var="start" begin="${startpage}" end="${endpage}" step="1"> 
  <c:choose>   
  <c:when test="${start eq page}">[<c:out value="${start}" />]</c:when>   
  <c:otherwise> <a href="./QnaSearchList.qa?page=${start}&keyword=${keyword}&keyfield=${keyfield}">[<c:out value= "${start}" />]</a>&nbsp;    
  </c:otherwise>
   </c:choose> 
   </c:forEach>         
   <c:choose>         
    <c:when test="${page >= maxpage}">[다음] 
    </c:when>          
    <c:otherwise>     
    <a href="./QnaSearchList.qa?page=${page+1}&keyword=${keyword}&keyfield=${keyfield}">[다음]</a>           
    </c:otherwise>        
    </c:choose>        
    </td>      
    </tr>    
    </tbody>  
    </table>
   <div class="btnJoinAreb"> 
   <button type="button" value="button" onclick="location.href='./QnaList.qa'" class= "btnOk">      목록    </button>  
   </div>  
   </div>  
   </section> 
   </div> 
</body>
</html>