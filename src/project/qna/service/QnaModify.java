package project.qna.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.qna.action.QnaAction;
import project.qna.command.QnaActionCommand;
import project.qna.dao.QnaDAO;
import project.qna.model.QnaVO;


public class QnaModify implements QnaAction{

	@Override
	public QnaActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaActionCommand qnaActionCommand = new QnaActionCommand();
		QnaDAO qnaDAO = new QnaDAO();
		QnaVO qnaVO = new QnaVO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./qnaUpload";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {
			MultipartRequest multiRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multiRequest.getParameter("num"));
			System.out.println(multiRequest.getParameter("m_id"));
			boolean usercheck = qnaDAO.isQnaWriter(num, multiRequest.getParameter("m_id"));

			if (usercheck == false) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정할 권한이 없습니다.');");
				out.println("location.href='./QnaList.qa';");
				out.println("</script>");
				out.close();
				return null;
			}
			qnaVO.setNum(num);
			qnaVO.setM_id(multiRequest.getParameter("m_id"));
			qnaVO.setSubject(multiRequest.getParameter("subject"));
			qnaVO.setContent(multiRequest.getParameter("content"));
			qnaVO.setAttached_file(
					multiRequest.getFilesystemName((String) multiRequest.getFileNames().nextElement()));
			qnaVO.setOld_file(multiRequest.getParameter("old_file"));

			result = qnaDAO.QnaModify(qnaVO);
			if (result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");

			qnaActionCommand.setRedirect(true);
			qnaActionCommand.setPath("./QnaDetail.qa?num=" + qnaVO.getNum());
			return qnaActionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
