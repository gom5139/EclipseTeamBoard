package project.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.product.action.ProductAction;
import project.product.command.ProductActionCommand;
import project.product.service.ProductAdd;
import project.product.service.ProductDelete;
import project.product.service.ProductDetail;
import project.product.service.ProductList;
import project.product.service.ProductSearchList;
import project.product.service.ProductUpdate;
import project.product.service.ProductUpdateDetail;



public class ProductFrontController extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requstURL = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requstURL.substring(contextPath.length());

		ProductActionCommand productActionCommand = null;
		System.out.println(contextPath);
		System.out.println(pathURL);
		ProductAction productAction = null;

		if (pathURL.equals("/ProductList.pr")) {
			productAction = new ProductList();
			
			try {
				productActionCommand = productAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/ProductWrite.pr")) {
			productActionCommand = new ProductActionCommand();
			productActionCommand.setRedirect(false);
			productActionCommand.setPath("./product/product_write.jsp");

		} else if (pathURL.equals("/ProductAdd.pr")) {
			productAction = new ProductAdd();
			//System.out.println("11111");
			try {
				productActionCommand = productAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/ProductDetail.pr")) {
			productAction = new ProductDetail();
			try {
				productActionCommand = productAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/ProductModify.pr")) {
			productAction = new ProductUpdateDetail();
			try {
				productActionCommand = productAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/ProductModifyService.pr")) {
			productAction = new ProductUpdate();
			try {
				productActionCommand = productAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if(pathURL.equals("/ProductDeleteService.pr")) {
			productAction = new ProductDelete();		
			try {
				productActionCommand = productAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/ProductSearchList.pr")) {
			productAction = new ProductSearchList();
			try {
				productActionCommand = productAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(productActionCommand != null) {
			if(productActionCommand.isRedirect()) {
				response.sendRedirect(productActionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(productActionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}
}
