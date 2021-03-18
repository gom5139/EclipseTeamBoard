package project.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.member.command.MemberActionCommand;


public interface MemberAction {
	public MemberActionCommand execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}
