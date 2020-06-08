package control.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Product;
import model.ProductBean;
import model.ProductDb;


@WebServlet("/productSrch")
public class productSrch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		//===========
		String keyWord = request.getParameter("keyWord");
		System.out.println("keyWord: "+keyWord);//測試用
		ProductDb prodDB=new ProductDb();
//		ProductBean prodBn=new ProductBean();
//		prodBn.serchProducts(keyWord);
//		System.out.println(prodBn.getNameList().get(0));//測試用
		ArrayList<Product> SrchedProdList = null;
		try {
			SrchedProdList = (ArrayList<Product>) prodDB.searchProd(keyWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HashMap<String, ArrayList<?>> myMap=new HashMap<String, ArrayList<?>>();
		JSONArray myJsonArr=new JSONArray();
		
		for(int i=0;i<SrchedProdList.size();i++) {
			JSONObject myJsonObj=new JSONObject();
			
			myJsonObj.put("prod_id", SrchedProdList.get(i).getProd_id());
			myJsonObj.put("prod_name", SrchedProdList.get(i).getProd_name());
			myJsonObj.put("prod_prod_introduction", SrchedProdList.get(i).getProd_introduction());
			myJsonObj.put("prod_price", SrchedProdList.get(i).getProd_price());
			myJsonObj.put("prod_size_stock", SrchedProdList.get(i).getProd_size_stock());
			
			myJsonArr.put(myJsonObj);
		}
		out.print(myJsonArr);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
