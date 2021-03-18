package project.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.member.action.MemberAction;
import project.member.command.MemberActionCommand;
import project.member.dao.MemberDAO;
import project.member.model.MemberVO;

public class Member implements MemberAction{

	@Override
	public MemberActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO memberDAO = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		MemberActionCommand memberActionCommand = new MemberActionCommand();

		boolean result = false;

		try {
			memberVO.setM_name(request.getParameter("name"));
			memberVO.setM_id(request.getParameter("id"));
			memberVO.setM_password(request.getParameter("password"));
			memberVO.setM_passwordcheck(request.getParameter("passwordcheck"));
			memberVO.setM_email(request.getParameter("email"));
			memberVO.setM_phonenumber(request.getParameter("phonenumber"));
			memberVO.setM_address(request.getParameter("address"));

			result = memberDAO.Member(memberVO);

			if (result == false) {
				System.out.println("회원가입 실패");
				return null;
			}
			System.out.println("회원가입 완료");
			memberActionCommand.setRedirect(false);
			memberActionCommand.setPath("./main.jsp");
			return memberActionCommand;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
