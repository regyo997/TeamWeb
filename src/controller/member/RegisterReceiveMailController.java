package controller.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemQuery;
import model.MemUpdate;


@WebServlet("/RegisterReceiveMail")
public class RegisterReceiveMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		
		if (request.getParameter("mailOK") != null) {
			String mem_email = (String)request.getParameter("mem_email");
			String mem_chkNum = (String)request.getParameter("mem_chkNum");
			
			MemQuery memQuery=new MemQuery();
			if (memQuery.queryByEmail(mem_email) // 有找到這人
					&& memQuery.getMemberObject().getMem_chkNum().equals(mem_chkNum)) {// 檢驗碼有對到
				MemUpdate memUpdate = new MemUpdate();
				try {
					memUpdate.comfirmEmail(mem_email, mem_chkNum);
					session.setAttribute("msg", "驗證成功!!歡迎成為正式會員!!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				session.setAttribute("msg", "驗證失敗，請前往 [客服中心]=>[已申請會員,重發確認信]。");
			}
			response.sendRedirect("login.jsp");
		}
	}
}
