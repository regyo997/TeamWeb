package controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UpdateProduct;


@WebServlet("/UpdateProduct")
public class UpdateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ===
		String doWhat = request.getParameter("updateBtn");
		if (doWhat.equals("刪除")) {
			int prod_id = Integer.parseInt(request.getParameter("imgId"));
			UpdateProduct updateProduct = new UpdateProduct();
			updateProduct.delete(prod_id);
		}else if(doWhat.equals("修改")) {
			int prod_id = Integer.parseInt(request.getParameter("imgId"));
			String prod_name = request.getParameter("prodName");
			String prod_introduction = request.getParameter("prodIntro");
			String prod_price = request.getParameter("prodPrice");
			String prod_stocksize = request.getParameter("prodLeftNum");
			UpdateProduct updateProduct = new UpdateProduct();
			updateProduct.update(prod_id, prod_name, prod_introduction, prod_price, prod_stocksize);
		}
			response.sendRedirect("admin_images.jsp");
	}

	
}
