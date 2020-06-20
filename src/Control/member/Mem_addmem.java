package control.member;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConnDB;
import model.SendMail;


@WebServlet("/doRegister")
public class Mem_addmem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/mtml;charset=utf-8");
		//vvv參數
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String passwd = request.getParameter("pw");
		//^^^參數
		//vvv產生驗證Email用的驗證碼
		String mem_chkcode="";//Email驗證碼
		int mem_chkcode_len=6;//驗證碼長度
		String stuff="0123456789";//宇集合
		int stuff_len = stuff.length();//宇集長度 10
		for(int i=0;i<mem_chkcode_len;i++) {//跑5次randon
			int location=(int)(Math.random()*stuff_len);//產生隨機位置
			mem_chkcode+=stuff.substring(location, location+1);//取宇集中該位置
		}
		//^^^產生驗證Email用的驗證碼
		
		int n = 0;
		String sql="INSERT INTO teamweb2020.member(mem_name,mem_mail,mem_pwd,mem_chkcode) VALUES(?,?,?,?);";
		ConnDB connDB=new ConnDB();
		connDB.setPreparedStatement(sql);
		connDB.setString(1, name);
		connDB.setString(2, email);
		connDB.setString(3, passwd);
		connDB.setString(4, mem_chkcode);
		n = connDB.executeUpdate();
		if (n>=1) {
			SendMail sMail = new SendMail();
			sMail.setTo(email);
			sMail.setSubject("網站會員功能啟用通知");
			sMail.setContent(name+"您好，感謝申請會員!!<br>"+"會員帳號功能啟用通知<br>"+
					"請點選<a href='http://localhost:8080/TeamWeb/login?mailOK=1&mem_mail="+email
					+"&mem_chkcode="+mem_chkcode+"'>此連結回覆確認信箱</a><br>"+
					"此信件為系統自動發送, 請勿點選回覆信件");
			sMail.execute();
			String msg=sMail.getMsg();
			if (!msg.equals("3") && !msg.equals("2")) {
				msg="1";
			}
			response.sendRedirect("register.jsp?msg="+msg);
		}else {
			System.out.println("未註冊完成"+n);
		}
		
	}

}