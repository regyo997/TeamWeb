package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemUpdate;


@WebServlet("/ForgetPasswordRenewPwd")
public class ForgetPasswordRenewPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		//====
		String mem_email=request.getParameter("mem_email");
		String mem_chkNum = request.getParameter("enc");
		String mem_pwd = request.getParameter("inputpwd");		
		//====
		MemUpdate memUpdate=new MemUpdate();
		if(memUpdate.updatePwd(mem_email, mem_pwd, mem_chkNum))
			session.setAttribute("msg", "密碼已變更，請用新密碼登入!");
		else
			session.setAttribute("msg", "密碼變更程序有誤，請重新申請密碼變更");
		response.sendRedirect("login.jsp");
	}

}
