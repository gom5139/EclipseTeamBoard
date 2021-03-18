package project.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.qna.command.QnaActionCommand;



public interface QnaAction {
	public QnaActionCommand execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}
