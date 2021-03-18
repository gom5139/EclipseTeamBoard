package project.product.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.product.action.ProductAction;
import project.product.command.ProductActionCommand;
import project.product.dao.ProductDAO;

public class ProductDelete implements ProductAction{

	@Override
	public ProductActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductActionCommand productActionCommand = new ProductActionCommand();
		boolean result = false;
		
		int products_num = Integer.parseInt(request.getParameter("products_num"));
		ProductDAO DAO = new ProductDAO();

		
		result= DAO.ProductDelete(products_num);

		if(result == false) {
			System.out.println("상품 삭제 실패");
			return null;
		}
		System.out.println("상품 삭제 성공");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제하였습니다.');");
		out.println("location.href='./ProductList.pr'");
		out.println("</script>");
		out.close();
		productActionCommand.setRedirect(true);
		productActionCommand.setPath("./ProductList.do");
		return null;
	}

}
