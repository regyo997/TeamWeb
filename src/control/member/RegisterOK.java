package control.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConnDB;


@WebServlet("/doRegisterOK")
public class RegisterOK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String url="login.jsp";
		if (request.getParameter("mailOK") != null) {
			String mem_mail = (String)request.getParameter("mem_mail");
			String mem_chkcode = (String)request.getParameter("mem_chkcode");
			String sql="UPDATE teamweb2020.member SET mem_level=2 WHERE mem_mail=? AND mem_chkcode=?;";
			ConnDB conn =new ConnDB();
			conn.setPreparedStatement(sql);
			conn.setString(1, mem_mail);
			conn.setString(2, mem_chkcode);
			conn.executeUpdate();
			int n=conn.getUpdate_count();
			if (n>=1) {
				response.sendRedirect(url);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
