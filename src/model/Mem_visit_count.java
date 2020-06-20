package model;

import java.io.Serializable;
import java.sql.SQLException;

public class Mem_visit_count implements Serializable {
	private static final long serialVersionUID = 1L;
	private int viewId;
	private String viewIp="";
	private int viewN;
	public void setViewId(int viewId){this.viewId=viewId;}
	public void setViewId(String viewId){this.viewId=Integer.parseInt(viewId);}
	public void setViewIp(String viewIp){
		if(!this.viewIp.equals(viewIp)) {//如果IP第一次進來
			this.viewIp=viewIp;
			//插入一筆訪問
			String sql="INSERT INTO TEAMWEB2020.VIEWCOUNT(VIEW_ID,VIEW_TIME,VIEW_IP) VALUES(?,NOW(),?);";
			ConnDB conn = new ConnDB();
			conn.setPreparedStatement(sql);
			try {
				conn.setInt(1, viewId);
				conn.setString(2, viewIp);
				conn.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public int getViewN() {
		//查詢現在共有幾筆
				ConnDB conn = new ConnDB(); 
				conn.setPreparedStatement("SELECT * FROM TEAMWEB2020.VIEWCOUNT;");
				try {
					conn.executeQuery();
					this.viewN = conn.getQuery_count();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return this.viewN;
	}
	
}//class
