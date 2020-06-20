package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnDB {
	private String sDriver = "com.mysql.cj.jdbc.Driver";
	private String user = "root";
	private String password = "1234";
	private String url = "jdbc:mysql://localhost:8888/teamweb2020?serverTimezone=CST";
	private Connection conn = null;
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	private int query_count;
	private int update_count;
	public ConnDB(){}
	
	public PreparedStatement setPreparedStatement(String sql) {
		try {
			Class.forName(sDriver);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return stmt;
	}
	
	public void setInt(int parameterIndex, int x) throws SQLException {
			stmt.setInt(parameterIndex, x);
	}
	
	public void setString (int parameterIndex, String x) throws SQLException {
			stmt.setString(parameterIndex, x);
	}
	
	public int getQuery_count() throws SQLException {
			query_count=0;
			rs.beforeFirst();
			while(rs.next())query_count++;
			rs.beforeFirst();
		return query_count;
	}
	public int getUpdate_count() {
		return update_count;
	}
	
	public ResultSet executeQuery() throws SQLException {
			rs=stmt.executeQuery();
		return rs;
	}
	
	public int executeUpdate() throws SQLException {
			update_count=stmt.executeUpdate();
		return update_count;
	}
	
	public ResultSet getRs() {
		return rs;
	}
}
