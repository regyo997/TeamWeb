package controller.member;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemAdd;


@WebServlet("/RegisterSendMail")
public class RegisterSendMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/mtml;charset=utf-8");
		
		//參數
		String mem_name = request.getParameter("mem_name");
		String mem_email = request.getParameter("mem_email");
		String mem_pwd = request.getParameter("mem_pwd");
		
		//處理
		MemAdd memAdd = new MemAdd();
		memAdd.doInsertInto(mem_name, mem_email, mem_pwd);
		request.getSession().setAttribute("msg", memAdd.getMsg());
		response.sendRedirect("register.jsp");
		
	}

}