package controller.member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemQuery;


@WebServlet("/isEmailAlreadyRegister")
public class IsEmailAlreadyRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//基本設定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/mtml;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//Field
		String email = request.getParameter("email");
		
		/// vvv Driver Connect Statement excuteQuery() 
		MemQuery memQuery=new MemQuery();
		Boolean alreadyRegister=memQuery.queryByEmail(email);
		out.print(alreadyRegister);
	}


}