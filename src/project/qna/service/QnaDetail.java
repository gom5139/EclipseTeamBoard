package project.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.qna.action.QnaAction;
import project.qna.command.QnaActionCommand;
import project.qna.dao.QnaDAO;
import project.qna.model.QnaVO;

public class QnaDetail implements QnaAction{

	@Override
	public QnaActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaDAO qnaDAO = new QnaDAO();
		QnaVO qnaVO = new QnaVO();
		
	
		
		int num = Integer.parseInt(request.getParameter("num"));
		qnaDAO.setReadCountUpdate(num);
		qnaVO = qnaDAO.getDetail(num);
		if(qnaVO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("qnaVO", qnaVO);
		QnaActionCommand qnaActionCommand = new QnaActionCommand();
		qnaActionCommand.setPath("./qnaboard/qna_view.jsp");
		return qnaActionCommand;
	}

}
