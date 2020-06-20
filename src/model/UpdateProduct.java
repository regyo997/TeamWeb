package model;

import java.io.Serializable;
import java.sql.SQLException;

public class UpdateProduct implements Serializable{
	private static final long serialVersionUID = 1L;
	public void update(int prod_id,String prod_name,String prod_introduction,String prod_price,String prod_stocksize){			
			String sql = "UPDATE teamweb2020.product SET prod_name=?,prod_introduction=?,prod_price=?,prod_stocksize=? WHERE prod_id = ?;";
			ConnDB conn = new ConnDB();
			conn.setPreparedStatement(sql);
			try {
				conn.setString(1, prod_name);
				conn.setString(2,prod_introduction);
				conn.setString(3, prod_price);
				conn.setString(4, prod_stocksize);
				conn.setInt(5, prod_id);
				conn.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public void delete(int prod_id){
		String sql = "UPDATE teamweb2020.product SET prod_view=0 WHERE prod_id =?;";
		ConnDB conn = new ConnDB();
		conn.setPreparedStatement(sql);
		try {
			conn.setInt(1, prod_id);
			conn.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
