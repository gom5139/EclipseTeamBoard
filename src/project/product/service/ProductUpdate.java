package project.product.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.product.action.ProductAction;
import project.product.command.ProductActionCommand;
import project.product.dao.ProductDAO;
import project.product.model.ProductVO;

public class ProductUpdate implements ProductAction{

	@Override
	public ProductActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductActionCommand productActionCommand = new ProductActionCommand();
		ProductDAO DAO = new ProductDAO();
		ProductVO VO = new ProductVO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./productimage";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {
			//System.out.println("이곳은 업데이트 서비스");
			MultipartRequest multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			int products_num = Integer.parseInt(multipartRequest.getParameter("products_num"));
			
			VO.setProducts_num(products_num);
			VO.setProducts_name(multipartRequest.getParameter("products_name"));
			VO.setCategory(multipartRequest.getParameter("category"));
			VO.setEnergy_efcnc(multipartRequest.getParameter("energy_efcnc"));
			VO.setLiter(multipartRequest.getParameter("liter"));
			VO.setPrice(multipartRequest.getParameter("price"));
			VO.setImage(multipartRequest.getFilesystemName((String) multipartRequest.getFileNames().nextElement()));
			VO.setOld_file(multipartRequest.getParameter("old_file"));			
			result = DAO.ProductUpdate(VO);
			
			if (result == false) {
				System.out.println("상품 수정 실패");
				return null;
			}
			System.out.println("상품 수정 완료");
			productActionCommand.setRedirect(true);
			productActionCommand.setPath("./ProductDetail.pr?products_num=" + VO.getProducts_num());
			return productActionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
