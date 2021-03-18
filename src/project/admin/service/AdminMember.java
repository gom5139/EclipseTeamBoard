package project.admin.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.admin.action.AdminAction;
import project.admin.command.AdminActionCommand;
import project.admin.dao.AdminDAO;
import project.admin.model.AdminVO;

public class AdminMember implements AdminAction{

	@Override
	public AdminActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<AdminVO> arrayList = new ArrayList<AdminVO>();
		AdminDAO adminDAO = new AdminDAO();

		arrayList = adminDAO.AdminMember();

		request.setAttribute("arrayList", arrayList);
		AdminActionCommand adminActionCommand = new AdminActionCommand();
		adminActionCommand.setRedirect(false);
		adminActionCommand.setPath("./admin/admin_member.jsp");
		return adminActionCommand;
	}

}
