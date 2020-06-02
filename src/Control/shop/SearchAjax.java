package Control.shop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.Cart;
import model.Product;






@WebServlet("/doSearchAjax")
public class SearchAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		Cart cart;
		//=========
		HttpSession session=request.getSession();
		if (String.valueOf(request.getParameter("fromWho")).equals("product_details.jsp_ajax")) {
			response.setContentType("apllication/json;charset=utf-8");
			
			int prod_id = Integer.parseInt(request.getParameter("prod_id"));
			String prod_name = request.getParameter("prod_name");
			int prod_price = Integer.parseInt(request.getParameter("prod_price"));
			String prod_introduction = request.getParameter("prod_introduction");
			int prod_size_stock = Integer.parseInt(request.getParameter("prod_size_stock"));
			Product product = new Product(prod_id, prod_name, prod_price, prod_introduction, prod_size_stock);
			if (session.getAttribute("cart") == null) {
				cart = new Cart();
				session.setAttribute("cart", cart);
			} else {
				cart = (Cart) session.getAttribute("cart");
			}
			if(product.getProd_size_stock()<1) {
				JSONObject json=new JSONObject();
				json.put("msg","抱歉已售完");
				out.print(json);
			}else {
				cart.addItem(prod_id, product);
				session.setAttribute("cart", cart);
				// ====
				JSONObject json = new JSONObject();
				json.put("prod_id", prod_id);
				json.put("totalQuantity", cart.getTotalQuantity());
				json.put("totalPrice", cart.getTotalPrice());
				out.print(json);
			}
		}
	}
}
