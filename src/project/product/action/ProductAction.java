package project.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.product.command.ProductActionCommand;


public interface ProductAction {
	public ProductActionCommand execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
			
}
