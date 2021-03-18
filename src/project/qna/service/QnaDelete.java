package project.qna.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.qna.action.QnaAction;
import project.qna.command.QnaActionCommand;
import project.qna.dao.QnaDAO;


public class QnaDelete implements QnaAction{

	@Override
	public QnaActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaActionCommand actionCommand = new QnaActionCommand();
		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(request.getParameter("num"));
		
		QnaDAO qnaDAO = new QnaDAO();
		
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		
		usercheck = qnaDAO.isQnaWriter(num, m_id);

		if (usercheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("location.href='./QnaList.qa';");
			//out.println("hestory.back( )");
			out.println("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제하였습니다.');");
			out.println("location.href='./QnaList.qa';");
			out.println("</script>");
			out.close();
		}

		result = qnaDAO.QnaDeletet(num);
		if (result == false) {
			System.out.println("게시글 삭제 실패");
			return null;
		} 
		System.out.println("게시글 삭제 성공");
		actionCommand.setRedirect(true);
		actionCommand.setPath("./QnaList.qa");
		//return actionCommand;
		return null;
	}

}
