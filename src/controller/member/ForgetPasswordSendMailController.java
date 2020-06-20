package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemUpdate;


@WebServlet("/ForgetPasswordSendMail")
public class ForgetPasswordSendMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		//=======
		String mem_email=request.getParameter("mem_email");
		//===
		MemUpdate memUpdate = new MemUpdate();
		if(memUpdate.forgotPwdSendMail(mem_email)) {
			session.setAttribute("msg", "請至信箱收信，以便重設密碼");
		}else {
			session.setAttribute("msg", "寄信失敗，請確認郵件地址正確");
		}
		response.sendRedirect("forgetpass.jsp");
	}
}
