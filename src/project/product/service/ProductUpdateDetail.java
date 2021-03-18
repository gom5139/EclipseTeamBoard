package project.product.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.product.action.ProductAction;
import project.product.command.ProductActionCommand;
import project.product.dao.ProductDAO;
import project.product.model.ProductVO;

public class ProductUpdateDetail implements ProductAction{

	@Override
	public ProductActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductActionCommand productActionCommand = new ProductActionCommand();
		ProductDAO DAO = new ProductDAO();
		ProductVO VO = new ProductVO();
		
		int products_num =Integer.parseInt(request.getParameter("products_num"));
		VO=DAO.getDetail(products_num);
		if (VO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세 보기 성공");
		request.setAttribute("VO", VO);
		productActionCommand.setRedirect(false);
		productActionCommand.setPath("./product/product_update.jsp");
		
		return productActionCommand;
	}

}
