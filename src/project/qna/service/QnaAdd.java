package project.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.qna.action.QnaAction;
import project.qna.command.QnaActionCommand;
import project.qna.dao.QnaDAO;
import project.qna.model.QnaVO;


public class QnaAdd implements QnaAction{

	@Override
	public QnaActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaVO qnaVO = new QnaVO();
		QnaDAO qnaDAO = new QnaDAO();
		QnaActionCommand qnaActionCommand = new QnaActionCommand();
		String realFolder = "";
		String saveFolder = "./QnaUpload";
		
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		int fileSize = 10 * 1024 *1024;
		boolean result = false;
		try {
			MultipartRequest multipartRequest  = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			String subject =  multipartRequest.getParameter("category");
			subject =  subject + multipartRequest.getParameter("subject");
			
			qnaVO.setM_id(multipartRequest.getParameter("m_id"));

			qnaVO.setSubject(subject);
			qnaVO.setContent(multipartRequest.getParameter("content"));
			qnaVO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			
			result = qnaDAO.QnaInsert(qnaVO);
			if (result ==false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			qnaActionCommand.setRedirect(true);
			qnaActionCommand.setPath("./QnaList.qa");
			return qnaActionCommand;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
