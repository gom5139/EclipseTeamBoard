package project.basket.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.basket.action.BasketAction;
import project.basket.command.BasketActionCommand;
import project.basket.dao.BasketDAO;

public class BasketDelete implements BasketAction{

	@Override
	public BasketActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BasketActionCommand actionCommand = new BasketActionCommand();
		boolean result = false;
		int basketid = Integer.parseInt(request.getParameter("basketID"));
		//System.out.println(request.getParameter("basketID"));
		
		BasketDAO DAO= new BasketDAO();
		
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		
		result = DAO.basketDelete(basketid , m_id);
		
		if (result == false) {
			System.out.println("장바구니 삭제 실패");
			return null;
		} 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('장바구니 아이템을 삭제 하였습니다.');");
		out.println("location.href='./BasketList.bs';");
		out.println("</script>");
		System.out.println("장바구니 삭제 성공");
		actionCommand.setRedirect(true);
		actionCommand.setPath("./BasketList.bs");
		//return actionCommand;
		return null;
	}

}
