package project.product.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.product.action.ProductAction;
import project.product.command.ProductActionCommand;
import project.product.dao.ProductDAO;
import project.product.model.ProductVO;

public class ProductDetail implements ProductAction{

	@Override
	public ProductActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = new ProductVO();
	
		System.out.println(request.getParameter("products_num"));
		
		int num = Integer.parseInt(request.getParameter("products_num"));
		productVO = productDAO.getDetail(num);
		if(productVO== null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("productVO", productVO);
		System.out.println(productVO);
		ProductActionCommand productActionCommand = new ProductActionCommand();
		productActionCommand.setPath("./product/product_view.jsp");
		return productActionCommand;
	}

}
