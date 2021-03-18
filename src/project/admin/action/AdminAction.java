package project.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.admin.command.AdminActionCommand;



public interface AdminAction {
	public AdminActionCommand execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}

