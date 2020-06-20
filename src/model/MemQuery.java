package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemQuery implements Serializable{
	private static final long serialVersionUID = 1L;
	private String sql = "SELECT * FROM teamweb2020.member WHERE mem_email = ?";
	private MemberObject memberObject=null;
	
	public void setEmail(String mem_email) {
		ConnDB connDB=new ConnDB();
		
		connDB.setPreparedStatement(sql);
		
		ResultSet rs=connDB.getRs();
		try {
			rs.next();
			memberObject.setMem_id(rs.getInt("mem_id"));
			memberObject.setMem_name(rs.getString("mem_name"));
			memberObject.setMem_pwd(rs.getString("mem_pwd"));
			memberObject.setMem_level(rs.getInt("mem_level"));
			memberObject.setMem_email(rs.getString("mem_email"));
			memberObject.setMem_cellphone(rs.getString("mem_cellphone"));
			memberObject.setMem_address(rs.getString("mem_address"));
			memberObject.setMem_chkNum(rs.getString("mem_chkNum"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MemberObject getMemberObject() {
		return memberObject;
	}
	
	public boolean isExist() {
		if(memberObject!=null)
			return true;
		else
			return false;
	}
	
	
}
