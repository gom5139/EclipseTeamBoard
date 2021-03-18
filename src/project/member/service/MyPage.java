package project.member.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.member.action.MemberAction;
import project.member.command.MemberActionCommand;
import project.member.dao.MemberDAO;
import project.member.model.MemberVO;

public class MyPage implements MemberAction{

	@Override
	public MemberActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<MemberVO> arrayList = new ArrayList<MemberVO>();
		MemberDAO memberDAO = new MemberDAO();
		
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		System.out.println(m_id);
		arrayList = memberDAO.MyPage(m_id);
		
		request.setAttribute("member", arrayList);
		MemberActionCommand memberActionCommand = new MemberActionCommand();
		memberActionCommand.setRedirect(false); 
		memberActionCommand.setPath("./member/mypage.jsp");
		return memberActionCommand;
	}

}
