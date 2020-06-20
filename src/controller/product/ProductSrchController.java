package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import model.ProductDb;


@WebServlet("/ProductSrch")
public class ProductSrchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		//===========
		String keyWord = request.getParameter("keyWord");
		ProductDb prodDB=new ProductDb();
		JSONArray myJsonArr = null;
		try {
			myJsonArr = prodDB.searchProdJSONArray(keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print(myJsonArr);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
