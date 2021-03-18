package project.qna.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.qna.action.QnaAction;
import project.qna.command.QnaActionCommand;
import project.qna.dao.QnaDAO;


public class QnaSearchList implements QnaAction{

	@Override
	public QnaActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyword = null;
		
		String keyfield = null;
		keyword = (String) request.getParameter("keyword");
		keyfield = (String) request.getParameter("keyfield");
		System.out.println(keyword + keyfield);
		
		QnaDAO qnaDAO = new QnaDAO();
		List<?> searchBoardList = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		  int searchlistcount = qnaDAO.getSearchListConut(keyword, keyfield);    
		  searchBoardList = qnaDAO.getSearchList(keyword, keyfield, page, limit);    
		  int maxpage = (int) ((double) searchlistcount / limit + 0.95);    
		  int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;    
		  int endpage = startpage + 10 - 1;      
		  if (endpage > maxpage) {        
			  endpage = maxpage;      
			  }    
		  request.setAttribute("page", page);    
		  request.setAttribute("maxpage", maxpage);    
		  request.setAttribute("startpage", startpage);    
		  request.setAttribute("endpage", endpage);    
		  request.setAttribute("searchlistcount", searchlistcount);    
		  request.setAttribute("searchBoardlist", searchBoardList);    
		  request.setAttribute("keyword", keyword);    
		  request.setAttribute("keyfield", keyfield);    
		  QnaActionCommand qnaActionCommand = new QnaActionCommand( );    
		  qnaActionCommand.setRedirect(false);    
		  qnaActionCommand.setPath("./qnaboard/qnaboard_search_list.jsp");    
		  return qnaActionCommand; 
	}

}
