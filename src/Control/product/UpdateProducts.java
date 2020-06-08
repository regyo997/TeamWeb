package control.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConnDB;


@WebServlet("/doUpdateProducts")
public class UpdateProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ===
		String doWhat = request.getParameter("updateBtn");
		if (doWhat.equals("刪除")) {
			int imgId = Integer.parseInt(request.getParameter("imgId"));
			String sql = "UPDATE teamweb2020.product SET prod_view=0 WHERE prod_id =?;";
			ConnDB conn = new ConnDB();
			conn.setPreparedStatement(sql);
			conn.setInt(1, imgId);
			conn.executeUpdate();
			response.sendRedirect("admin_images.jsp");
		}else if(doWhat.equals("修改")) {
			int imgId = Integer.parseInt(request.getParameter("imgId"));
			String prodName = request.getParameter("prodName");
			String prodIntro = request.getParameter("prodIntro");
			String prodPrice = request.getParameter("prodPrice");
			String prodLeftNum = request.getParameter("prodLeftNum");
			String sql = "UPDATE teamweb2020.product SET prod_name=?,prod_introduction=?,prod_price=?,prod_size_stock=? WHERE prod_id = ?;";
			ConnDB conn = new ConnDB();
			conn.setPreparedStatement(sql);
			conn.setString(1, prodName);
			conn.setString(2,prodIntro);
			conn.setString(3, prodPrice);
			conn.setString(4, prodLeftNum);
			conn.setInt(5, imgId);
			conn.executeUpdate();
			response.sendRedirect("admin_images.jsp");
		}else {
			response.sendRedirect("admin_images.jsp");
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
