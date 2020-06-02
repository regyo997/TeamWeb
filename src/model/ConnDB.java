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
	
	public void setInt(int parameterIndex, int x) {
		try {
			stmt.setInt(parameterIndex, x);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void setString (int parameterIndex, String x) {
		try {
			stmt.setString(parameterIndex, x);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public int getQuery_count() {
		try {
			query_count=0;
			rs.beforeFirst();
			while(rs.next())query_count++;
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return query_count;
	}
	public int getUpdate_count() {
		return update_count;
	}
	
	public ResultSet executeQuery() {
		try {
			rs=stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return rs;
	}
	
	public int executeUpdate() {
		try {
			update_count=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return update_count;
	}
	
	public ResultSet getRs() {
		return rs;
	}
}
