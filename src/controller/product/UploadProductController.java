package controller.product;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.UploadProduct;

@MultipartConfig
@WebServlet("/UploadProduct")
public class UploadProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		request.setCharacterEncoding("utf-8");
    		response.setContentType("text/html;charset=utf-8");
    		//===
    		response.setHeader("Pragma", "no-cache");
    		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    		response.setDateHeader("Expires", -1);
    		//====
    		String prod_name=request.getParameter("productName");
    		int prod_stocksize=Integer.parseInt(request.getParameter("productLeftNum"));
    		int prod_price=Integer.parseInt(request.getParameter("productPrice"));
    		String prod_introduction=request.getParameter("productIntro");
    		Part img=request.getPart("imgFile");
//    		String imgOldName=img.getSubmittedFileName();。
    		String mypath=getServletContext().getInitParameter("myProjectLocation");
    		String tmppath=request.getServletContext().getRealPath("/");
    		//===
    		UploadProduct uploadProduct =new UploadProduct();
    		try {
				uploadProduct.upload(prod_name, prod_price, prod_introduction, prod_stocksize, mypath, img, tmppath);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
    		//===
    		response.sendRedirect("admin_images.jsp");
    	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
