package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemQuery;
import model.MemberObject;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String mem_email = request.getParameter("mem_email");
		String mem_pwd = request.getParameter("mem_pwd");
		MemQuery memQuery=new MemQuery();
		MemberObject memberObject;
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//動作
		memQuery.setEmail(mem_email);
		memberObject=memQuery.getMemberObject();
		if(mem_pwd.equals(memberObject.getMem_pwd())) {//有找到這人
			if(memberObject.getMem_level()>1) {//1是未驗證　>1才有登入權限
				session.setAttribute("mem_id", memberObject.getMem_id());
				session.setAttribute("mem_name", memberObject.getMem_name());
				response.sendRedirect("index.jsp");
			}else {//==1 還沒驗證
				session.setAttribute("msg", "帳號未驗證，請先驗證再行登入！");
				response.sendRedirect("login.jsp");
			}
		}else {//帳密對不起來
			session.setAttribute("msg", "輸入的帳號或密碼有誤，請重新登入！");
			response.sendRedirect("login.jsp");
		}
	}
}
