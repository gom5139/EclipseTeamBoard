package project.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.member.action.MemberAction;
import project.member.command.MemberActionCommand;

public class Logout implements MemberAction{

	@Override
	public MemberActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			session.removeAttribute("m_id");
			out.println("<script>");
			out.println("alert('로그아웃 되셨습니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
		} catch (Exception e) {
			
		}
		return null;
	}

}
