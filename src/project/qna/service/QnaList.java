package project.qna.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.qna.action.QnaAction;
import project.qna.command.QnaActionCommand;
import project.qna.dao.QnaDAO;

public class QnaList implements QnaAction{

	@Override
	public QnaActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaDAO qnaDAO = new QnaDAO();

		List<?> qnaList = new ArrayList<Object>();

		int page = 1;
		int limit = 10;

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
	}
		int listcount = qnaDAO.getListCount();
		qnaList = qnaDAO.getQnaList(page, limit);
		
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = (((int) ((double) page / 10 +0.9)) - 1) *10 +1;
		int endpage = startpage + 10 -1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("qnaList", qnaList);
		QnaActionCommand qnaActionCommand = new QnaActionCommand();
		System.out.println("보드리스트서비스 입장");
		qnaActionCommand.setRedirect(false);
		
		qnaActionCommand.setPath("./qnaboard/qna_list.jsp");
		return qnaActionCommand;
	}

}
