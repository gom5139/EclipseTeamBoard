package project.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.qna.action.QnaAction;
import project.qna.command.QnaActionCommand;
import project.qna.dao.QnaDAO;
import project.qna.model.QnaVO;


public class QnaReply implements QnaAction{

	@Override
	public QnaActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QnaActionCommand qnaActionCommand = new QnaActionCommand();
		QnaDAO qnaDAO = new QnaDAO();
		QnaVO qnaVO = new QnaVO();
		int result = 0;
		String realFolder = "";
		String saveFolder = "./qnaboardUpload";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		//System.out.println("보드리플 서비스");
		try {
			MultipartRequest multiReques = new MultipartRequest(request, realFolder, fileSize, "UTF-8",	new DefaultFileRenamePolicy());
			qnaVO.setNum(Integer.parseInt(multiReques.getParameter("num")));
			qnaVO.setM_id(multiReques.getParameter("m_id"));
			qnaVO.setSubject(multiReques.getParameter("subject"));
			qnaVO.setContent(multiReques.getParameter("content"));
			qnaVO.setAnswer_num(Integer.parseInt(multiReques.getParameter("answer_num")));
			qnaVO.setAnswer_lev(Integer.parseInt(multiReques.getParameter("answer_lev")));
			qnaVO.setAnswer_seq(Integer.parseInt(multiReques.getParameter("answer_seq")));
			qnaVO.setAttached_file(multiReques.getFilesystemName((String) multiReques.getFileNames().nextElement()));

			result = qnaDAO.QnaReply(qnaVO);
			if (result == 0) {
				System.out.println("답변 실패");
				return null;
			}
			System.out.println("답변 성공");
			qnaActionCommand.setRedirect(true);
			qnaActionCommand.setPath("./QnaDetail.qa?num=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return qnaActionCommand;
	}

}
