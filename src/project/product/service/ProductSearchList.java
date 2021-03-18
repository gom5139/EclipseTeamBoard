package project.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.product.action.ProductAction;
import project.product.command.ProductActionCommand;
import project.product.dao.ProductDAO;

public class ProductSearchList implements ProductAction{

	@Override
	public ProductActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
String category = request.getParameter("category");

		
		ProductDAO productDAO = new ProductDAO();
		List<?> searchBoardlist = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		  int searchlistcount = productDAO.getSearchListConut(category);    
		  System.out.println(searchlistcount);
		  searchBoardlist = productDAO.getSearchList(category, page, limit);    
		  int maxpage = (int) ((double) searchlistcount / limit + 0.95);    
		  int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;    
		  int endpage = startpage + 10 - 1;      
		  if (endpage > maxpage) {        
			  endpage = maxpage;      
			  }    
		  request.setAttribute("page", page);    
		  request.setAttribute("maxpage", maxpage);    
		  request.setAttribute("startpage", startpage);    
		  request.setAttribute("endpage", endpage);    
		  request.setAttribute("searchlistcount", searchlistcount);    
		  request.setAttribute("searchBoardlist", searchBoardlist);    
		  request.setAttribute("category", category);
  
		  ProductActionCommand productActionCommand = new ProductActionCommand( );    
		  productActionCommand.setRedirect(false);    
		  productActionCommand.setPath("./product/product_search_list.jsp");    
		  return productActionCommand; 
	}
	

}
