package model;

import java.sql.SQLException;

public class MemUpdate {
	
	public void comfirmEmail(String mem_email,String mem_chkNum) throws SQLException {
		String sql="UPDATE teamweb2020.member SET mem_level=2 WHERE mem_email=? AND mem_chknum=?;";
		ConnDB conn =new ConnDB();
			conn.setPreparedStatement(sql);
			conn.setString(1, mem_email);
			conn.setString(2, mem_chkNum);
			conn.executeUpdate();
	}
	
	public boolean forgotPwdSendMail(String mem_email){
		MemQuery memQuery=new MemQuery();
		if(memQuery.queryByEmail(mem_email)) {//如果有這人才做後面
			MemberObject memberObject=memQuery.getMemberObject();
			int mem_id=memberObject.getMem_id();
			String mem_name=memberObject.getMem_name();
			int salt=(int)(Math.random()*100000);
			String saltStr=String.format("%06d", salt);
			String sql="UPDATE TEAMWEB2020.MEMBER SET mem_chkNum = ? WHERE mem_id = ?;";
			ConnDB conn=new ConnDB();
			conn.setPreparedStatement(sql);
			try {
				conn.setString(1, saltStr);
				conn.setInt(2, mem_id);
				conn.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// ===
			String subject = "重設密碼";
			String content = String.format("%s您好!! 請您點選下述連結重設密碼<br>"
					+ "<a href='http://localhost:8080/TeamWeb/forgetpassReceiveMail.jsp?email=%s&enc=%s'>點此連結重設密碼</a>",
					mem_name, mem_email, saltStr);

			SendMail sMail = new SendMail();
			sMail.setTo(mem_email);
			sMail.setSubject(subject);
			sMail.setContent(content);
			sMail.execute();
			return true;
		}else {
			return false;
		}	
	}
	
	public boolean updatePwd(String mem_email,String mem_pwd,String mem_chkNum) {

		MemQuery memQuery=new MemQuery();
		
		//====
		if(memQuery.queryByEmail(mem_email)) {
			MemberObject memberObject=memQuery.getMemberObject();
			int mem_id=memberObject.getMem_id();
			System.out.println(mem_chkNum.equals(memberObject.getMem_chkNum()));
			if(mem_chkNum.equals(memberObject.getMem_chkNum())) {
				String sql = "UPDATE TEAMWEB2020.MEMBER SET mem_pwd = ? WHERE mem_id = ?;";
				ConnDB conn = new ConnDB();
				conn.setPreparedStatement(sql);
				try {
					conn.setString(1, mem_pwd);
					conn.setInt(2, mem_id);
					conn.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(conn.getUpdate_count()>0) {
					return true;
				}
			}
		}else {
			System.out.print("找不到該帳號");
		}
		return false;
		
	}
}
