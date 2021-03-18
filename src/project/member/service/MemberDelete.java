package project.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.member.action.MemberAction;
import project.member.command.MemberActionCommand;
import project.member.dao.MemberDAO;

public class MemberDelete implements MemberAction{

	@Override
	public MemberActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean result = false;
		boolean usercheck = false;
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		MemberDAO memberDAO = new MemberDAO();
		usercheck = memberDAO.PasswordCheck(m_id, request.getParameter("password"));
		if (usercheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("location.href='./Delete.me';");
			out.println("</script>");
			out.close();
			return null;
		} 
		result = memberDAO.Delete(m_id);
		
		if (result == false) {
			System.out.println("회원탈퇴 실패");
			return null;
		}
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 탈퇴하였습니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			System.out.println("회원탈퇴 성공");
		return null;
	}

}
