package project.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.qna.action.QnaAction;
import project.qna.command.QnaActionCommand;
import project.qna.service.QnaAdd;
import project.qna.service.QnaDelete;
import project.qna.service.QnaDetail;
import project.qna.service.QnaDownload;
import project.qna.service.QnaList;
import project.qna.service.QnaModify;
import project.qna.service.QnaModifyDetail;
import project.qna.service.QnaReply;
import project.qna.service.QnaReplyMove;
import project.qna.service.QnaSearchList;


public class QnaFrontController extends HttpServlet implements Servlet{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requstURL = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requstURL.substring(contextPath.length());

	QnaActionCommand actionCommand = null;
		System.out.println(contextPath + "// qnaboard");
		System.out.println(pathURL + "// qnaboard");
		QnaAction action = null;
		
		if (pathURL.equals("/QnaList.qa")) {
			action = new QnaList();
			
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/QnaWrite.qa")) {
			actionCommand = new QnaActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./qnaboard/qna_write.jsp");

		} else if (pathURL.equals("/QnaAdd.qa")) {
			action = new QnaAdd();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/QnaDetail.qa")) {
			action = new QnaDetail();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/QnaDownload.qa")) {
			action = new QnaDownload();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/QnaReply.qa")) {
			action = new QnaReply();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/QnaReplyMove.qa")) {
			action = new QnaReplyMove();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/QnaModify.qa")) {
			action = new QnaModifyDetail();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/QnaModifyService.qa")) {
			action = new QnaModify();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		} 
//		else if(pathURL.equals("/QnaDelete.qa")) {
//			qnaActionCommand = new QnaActionCommand();
//			qnaActionCommand.setRedirect(false);
//			qnaActionCommand.setPath("./qnaboard/qna_delete.jsp");
//			
		} else if(pathURL.equals("/QnaDelete.qa")) {
			action = new QnaDelete();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/QnaSearchList.qa")) {
			action = new QnaSearchList();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(actionCommand != null) {
			if(actionCommand.isRedirect()) {
				response.sendRedirect(actionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}
}
