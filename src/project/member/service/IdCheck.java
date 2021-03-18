package project.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.member.action.MemberAction;

import project.member.command.MemberActionCommand;
import project.member.dao.MemberDAO;

public class IdCheck implements MemberAction{

	@Override
	public MemberActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberActionCommand memberActionCommand = new MemberActionCommand();
		MemberDAO memberDAO = new MemberDAO();
		
		boolean result = false;
		
		try {
			String m_id = request.getParameter("id");
			System.out.println(m_id);
			if (m_id.equals("")) {
				System.out.println("아이디가 담기지 않았습니다.");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디를 입력해주세요.');");
				out.println("location.href='./MemberList.me';");
				out.println("</script>");
				out.close();
				return null;
			}
			
			result = memberDAO.IdCheck(m_id);
			String check="";
			if (result == false) {
				check = "false";
				request.setAttribute("check", check);
				System.out.println("이미 있는 아이디입니다.");
				memberActionCommand.setRedirect(false);
				memberActionCommand.setPath("./member/member.jsp");
				return memberActionCommand;
			}
			request.setAttribute("checkId", m_id);
			System.out.println("중복체크 완료되었습니다.");
			check = "true";
			request.setAttribute("check", check);
			memberActionCommand.setRedirect(false);
			memberActionCommand.setPath("./member/member.jsp");
			return memberActionCommand;
		} catch (Exception e) {
			
		}
	return null;
	}

}
