package project.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.member.action.MemberAction;
import project.member.command.MemberActionCommand;
import project.member.service.IdCheck;
import project.member.service.Login;
import project.member.service.Logout;
import project.member.service.Member;
import project.member.service.MemberDelete;
import project.member.service.MemberModify;
import project.member.service.Modify;
import project.member.service.MyPage;



public class MemberFrontController extends HttpServlet implements Servlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());

		MemberActionCommand memberActionCommand = null;
		MemberAction memberAction = null;

		if (pathURL.equals("/MyPage.me")) {
			memberAction = new MyPage();
			try {
				memberActionCommand = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberList.me")) {
			memberActionCommand = new MemberActionCommand();
			memberActionCommand.setRedirect(false);
			memberActionCommand.setPath("./member/member.jsp");

		} else if (pathURL.equals("/Member.me")) {
			memberAction = new Member();
			try {
				memberActionCommand = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/LoginService.me")) {
			memberAction = new Login();
			try {
				memberActionCommand = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/Login.me")) {
			memberActionCommand = new MemberActionCommand();
			memberActionCommand.setRedirect(false);
			memberActionCommand.setPath("./login/login.jsp");

		} else if (pathURL.equals("/Logout.me")) {
			memberAction = new Logout();
			try {
				memberActionCommand = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/Modify.me")) {
			memberAction = new Modify();
			try {
				memberActionCommand = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberModify.me")) {
			memberAction = new MemberModify();
			try {
				memberActionCommand = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/Delete.me")) {
			memberActionCommand = new MemberActionCommand();
			memberActionCommand.setRedirect(false);
			memberActionCommand.setPath("./member/member_delete.jsp");
		} else if (pathURL.equals("/MemberDelete.me")) {
			memberAction = new MemberDelete();
			try {
				memberActionCommand = memberAction.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/IdCheck.me")) {
			memberAction = new IdCheck();
			try {
				memberActionCommand = memberAction.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		if (memberActionCommand != null) {
			if (memberActionCommand.isRedirect()) {
				response.sendRedirect(memberActionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(memberActionCommand.getPath());
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
