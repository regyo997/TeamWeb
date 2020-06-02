package Control.shop;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;






@WebServlet("/doProductSummery")
public class ProductSummery extends HttpServlet {
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
		if(String.valueOf(request.getParameter("fromWho")).equals("product_summary.jsp")) {
			cart=(Cart)session.getAttribute("cart");
			Collection<CartItem> cartItems=cart.getItems();
			Iterator<CartItem> iter = cartItems.iterator();
			while(iter.hasNext()) {
				int prod_id=iter.next().getProduct().getProd_id();
				int quantity=Integer.parseInt((String) request.getParameter("quantity_"+prod_id));
				cart.setItemNum(prod_id, quantity);
			}
			session.setAttribute("cart", cart);
			response.sendRedirect("product_summary.jsp");
		}
	}

}
