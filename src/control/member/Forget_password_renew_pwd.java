package control.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConnDB;


@WebServlet("/doForgetPasswordRenewPwd")
public class Forget_password_renew_pwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//====
		String email=request.getParameter("email");
		String sql="SELECT * FROM TEAMWEB2020.MEMBER WHERE mem_mail=?;";
		ConnDB conn =new ConnDB();
		conn.setPreparedStatement(sql);
		conn.setString(1, email);
		conn.executeQuery();
		//====
		try {
			if (conn.getQuery_count() > 0) {
				ResultSet rs = conn.getRs();
				rs.first();
				//===
				String id = String.valueOf(rs.getInt(1));
				int encDB = Integer.parseInt((rs.getString(6)));
				int encPara = Integer.parseInt(request.getParameter("enc"));
				if (encPara==encDB) {
					String pwd = request.getParameter("inputpwd");
					sql = "UPDATE TEAMWEB2020.MEMBER SET mem_pwd = ? WHERE mem_id = ?;";
					conn = new ConnDB();
					conn.setPreparedStatement(sql);
					conn.setString(1, pwd);
					conn.setString(2, id);
					conn.executeUpdate();
					if (conn.getUpdate_count() > 0) {
						response.sendRedirect("login.jsp?msg=5");
					} else {
						out.print("出問題啦1");
					}
				}else {out.print("出問題啦2");}
			} else {
				out.print("出問題啦3");
			}
		} catch (SQLException e) {
			System.out.print(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
