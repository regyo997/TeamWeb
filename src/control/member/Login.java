package control.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConnDB;

/**
 * Servlet implementation class mem_login
 */
@WebServlet("/doLogin")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		
		PrintWriter out= response.getWriter();
		//找人
		String login_email = request.getParameter("login_email");
		String login_password = request.getParameter("login_password");
		String sql = "SELECT * FROM teamweb2020.member WHERE mem_mail=? AND mem_pwd=? AND mem_level>0;";
		String msgStr="";
		ConnDB cn=new ConnDB();
		cn.setPreparedStatement(sql);
		cn.setString(1, login_email);
		cn.setString(2, login_password);
		cn.executeQuery();
		int num = cn.getQuery_count();
		ResultSet rs = cn.getRs();
		try {
			rs.first();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		if(num>=1) {//有找到這人
			int level=0;
			try {
				level=rs.getInt(5);
			} catch (SQLException e1) {
				System.out.println("rs問題");
			}
			if (level >= 2) {//註冊且驗證
				
				try {
					session.setAttribute("mem_id", rs.getInt(1));
					session.setAttribute("mem_name", rs.getString(2));
					session.setAttribute("mem_level", rs.getInt(5));
					response.sendRedirect("index.jsp");
				} catch (SQLException e) {
					// session失敗
					System.out.println("ResultSet問題");
					System.out.println(e.getMessage());
				}
			}else if(level==1){//有註冊但未驗證
				session.setAttribute("msg", "帳號未驗證，請先驗證再行登入！");
				msgStr="帳號未驗證，請先驗證再行登入！";
				response.sendRedirect("login.jsp");
			}
		}else {//沒有找到的話
			request.setAttribute("msg", "輸入的帳號或密碼有誤，請重新登入！");
			msgStr="輸入的帳號或密碼有誤，請重新登入！";
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
