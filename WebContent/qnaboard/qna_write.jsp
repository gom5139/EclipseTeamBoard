<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qna 게시판 게시글 등록</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./css/jboard.css">
</head>
<body>
<div id="contentsArea">
 <section id="titlename">
 <h1> 문의 하기 </h1>
 <p class="formSign">
 <strong class="require"> 필수 </strong> 는 반드시 입력하여야 하는 항목입니다. </p>
<%--파일 업로드 할 수 있도록 설정한다.--%>
<form action="./QnaAdd.qa" method="post" id="joinForm" name="Qnaform" enctype="multipart/form-data">
 <fieldset>
 <legend> 게시판 글쓰기 </legend>
<!--  <p>
 <label for="name">글쓴이 <strong class="require">필수</strong></label>
 <input type="text" id="name" name="name" required placeholder="홍길동"/>
 </p> -->
<input type="hidden" name="m_id" value="${m_id}">
      <p>        
      <label for="category"> 문의 종류 <strong class="require">필수</strong></label> 
      <select name="category" id="category" required>
      <option value="" >=======</option>
      <option value="상품 문의  >>" >상품 문의 </option>
      <option value="하자 문의 >>" >하자 문의</option>
      <option value="기타문의 >>" >기타문의</option>
      </select>     
      </p> 
 <p>
 <label for="subject">제목 <strong class="require">필수</strong></label>
<input type="text" id="subject" name="subject" required placeholder="글의 제목을 입력하세요."/>
 </p>
 <p>
 <label for="content">내용 <strong class="require">필수</strong></label>
<textarea id="content" name="content" cols="74" rows="15" required placeholder="글의 내용을 입력하세요.">
</textarea>
 </p>
 <p>
 <label for="attached_file">파일 첨부 </label>
<input type="file" id="attached_file" name="attached_file" placeholder="파일 첨부"/>
 </p>
 <div class="btnJoinArea">
 <button type="submit" class="btnOk">글 등록</button>
 <button type="reset" class="btnCancel">글 취소</button>
<button type="button" value="button" onclick="location.href='./QnaList.qa'" class="btnOk">
 목록
</button>
 </div>
 </fieldset>
 </form>
 </section>
 </div>
</body>
</html>