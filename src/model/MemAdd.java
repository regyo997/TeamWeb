package model;

import java.io.Serializable;
import java.sql.SQLException;

public class MemAdd implements Serializable{
	private static final long serialVersionUID = 1L;
	private String msg="尚未註冊完成";
	
	public void doInsertInto(String mem_name,String mem_email,String mem_pwd) {
		// vvv產生驗證Email用的驗證碼
		String mem_chkNum = "";// Email驗證碼
		int mem_chkcode_len = 6;// 驗證碼長度
		String stuff = "0123456789";// 宇集合
		int stuff_len = stuff.length();// 宇集長度 10
		for (int i = 0; i < mem_chkcode_len; i++) {// 跑5次randon
			int location = (int) (Math.random() * stuff_len);// 產生隨機位置
			mem_chkNum += stuff.substring(location, location + 1);// 取宇集中該位置
		}
		
		//寫入資料庫
		String sql = "INSERT INTO teamweb2020.member(mem_name,mem_email,mem_pwd,mem_chknum) VALUES(?,?,?,?);";
		ConnDB connDB = new ConnDB();
		connDB.setPreparedStatement(sql);
		try {
			connDB.setString(1, mem_name);
			connDB.setString(2, mem_email);
			connDB.setString(3, mem_pwd);
			connDB.setString(4, mem_chkNum);
			connDB.executeUpdate();
			
			SendMail sMail = new SendMail();
			sMail.setTo(mem_email);
			sMail.setSubject("網站會員功能啟用通知");
			sMail.setContent(mem_name + "您好，感謝申請會員!!<br>" + "會員帳號功能啟用通知<br>"
					+ "請點選<a href='http://localhost:8080/TeamWeb/RegisterReceiveMail?mailOK=OK&mem_email=" + mem_email
					+ "&mem_chkNum=" + mem_chkNum + "'>此連結回覆確認信箱</a><br>" + "此信件為系統自動發送, 請勿點選回覆信件");
			if(sMail.execute())
				msg="感謝您註冊新會員成功！請前往郵件信箱收信！點選驗證連結再回到網站！";
			else
				msg="目前系統可能因忙碌而產生發送確認信件失敗，請過些時候前往 [客服中心]=>[已申請會員,重發確認信]。";
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public String getMsg() {
		return this.msg;
	}
}
