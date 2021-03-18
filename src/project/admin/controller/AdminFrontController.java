package project.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.admin.action.AdminAction;
import project.admin.command.AdminActionCommand;
import project.admin.service.AdminMember;


public class AdminFrontController extends HttpServlet implements Servlet{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		AdminActionCommand adminActionCommand = null;
		AdminAction adminAction = null;
		
		if (pathURL.equals("/AdminMember.ad")) {
			adminAction = new AdminMember();
			try {
				adminActionCommand = adminAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
    
	if (adminActionCommand != null) {
		if (adminActionCommand.isRedirect()) {
			response.sendRedirect(adminActionCommand.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(adminActionCommand.getPath());
			dispatcher.forward(request, response);
		}
	}
}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service(request, response);
	}
}
