package control.shop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;






@WebServlet("/doPlusMinusDel")
public class CartServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Cart cart;
		//=========
		HttpSession session=request.getSession();
		if(request.getParameter("del_id")!=null) {
			int prod_id=Integer.parseInt(request.getParameter("del_id"));
			cart=(Cart)session.getAttribute("cart");
			cart.deleteItem(prod_id);
			session.setAttribute("cart", cart);
			response.sendRedirect("product_summary.jsp");
		}else if(request.getParameter("minus_id")!=null) {
			int prod_id=Integer.parseInt(request.getParameter("minus_id"));
			cart=(Cart)session.getAttribute("cart");
			cart.setItemNum(prod_id, cart.getItemQuantity(prod_id)-1);
			session.setAttribute("cart", cart);
			response.sendRedirect("product_summary.jsp");
		}else if(request.getParameter("plus_id")!=null) {
			int prod_id=Integer.parseInt(request.getParameter("plus_id"));
			int prod_stock_size=Integer.parseInt(request.getParameter("plus_size_stock"));
			cart=(Cart)session.getAttribute("cart");
			if(cart.getItemQuantity(prod_id)+1<=prod_stock_size) {
				cart.setItemNum(prod_id, cart.getItemQuantity(prod_id)+1);
				session.setAttribute("cart", cart);
			}
			response.sendRedirect("product_summary.jsp");
		}
	}

}
