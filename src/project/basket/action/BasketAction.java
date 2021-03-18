package project.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.basket.command.BasketActionCommand;

public interface BasketAction {
	public BasketActionCommand execute(HttpServletRequest request,HttpServletResponse response)
			throws Exception;
		}

