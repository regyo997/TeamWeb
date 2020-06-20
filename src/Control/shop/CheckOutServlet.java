package control.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import model.Cart;
import model.CartItem;
import model.Product;
import model.ProductDb;

@WebServlet("/doCheckOut")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//=======
		String orderList_name=request.getParameter("orderList_name");
		String orderList_mphone=request.getParameter("orderList_mphone");
		String orderList_address=request.getParameter("orderList_address");
		String orderList_email=request.getParameter("orderList_email");
		HttpSession session=request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Collection<CartItem> cartItems =cart.getItems();
		//=======
		boolean size_stock_enough = true;
		String sql;
		ProductDb productDb = new ProductDb();
		Iterator<CartItem> iter = cartItems.iterator();
		while (iter.hasNext()) {
			Product tmpProd = iter.next().getProduct();
			int tmp_prod_id = tmpProd.getProd_id();
			int tmp_prod_quantity = tmpProd.getProd_size_stock();
			try {
				if (!productDb.isAmountEnough(tmp_prod_id, tmp_prod_quantity)) {// 該商品庫存不夠
					size_stock_enough = false;
					int prod_size_stock=cart.renewSizeStock(tmp_prod_id);
					cart.setItemNum(tmp_prod_id, prod_size_stock);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (size_stock_enough) {//庫存足
			try {
				int mem_id=(int) session.getAttribute("mem_id");
				productDb.buyProducts(mem_id,cart,orderList_name,orderList_mphone,orderList_address,orderList_email);
				session.setAttribute("cart", null);
				response.sendRedirect("index.jsp?msg=1");
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {//庫存不足
			session.setAttribute("cart", cart);
			response.sendRedirect("product_summary.jsp?msg=1");
		}
			

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
