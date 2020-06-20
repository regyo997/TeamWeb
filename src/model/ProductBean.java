package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int queryCount;
	private ArrayList<Integer> idList=new ArrayList<Integer>();
	private ArrayList<Integer> priceList=new ArrayList<Integer>();
	private ArrayList<Integer> leftNumList=new ArrayList<Integer>();
	private ArrayList<String> nameList=new ArrayList<String>();
	private ArrayList<String> introList=new ArrayList<String>();
	private int id;
	private int price;
	private int leftNum;
	private String name;
	private String intro;
	//回傳所有產品的List
	public void AllProducts(){
		String sql = "SELECT * FROM TEAMWEB2020.PRODUCT WHERE prod_view=1;";
		ConnDB conn = new ConnDB();
		conn.setPreparedStatement(sql);
		try {
			conn.executeQuery();
			queryCount = conn.getQuery_count();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = conn.getRs();
		
		try {
			rs.first();
			for (int i = 0; i < queryCount; i++) {

				idList.add(i, rs.getInt(1));// id
				priceList.add(i, rs.getInt(3));// price
				leftNumList.add(i, rs.getInt(5));// leftNum
				nameList.add(i, rs.getString(2));// name
				introList.add(i, rs.getString(4));// intro

				rs.next();
			}
			rs.first();
		} catch (SQLException e) {
			System.out.print(e);
		}
	}
	
	//指定某id的產品
	public void ProductById(int id){
		String sql = "SELECT * FROM TEAMWEB2020.PRODUCT WHERE prod_id=? AND prod_view=1;";
		ConnDB conn = new ConnDB();
		conn.setPreparedStatement(sql);
		try {
			conn.setInt(1, id);
			conn.executeQuery();
			queryCount = conn.getQuery_count();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet rs = conn.getRs();
		
		try {
				rs.first();
				id=rs.getInt(1);// id
				price=rs.getInt(3);// price
				leftNum=rs.getInt(5);// leftNum
				name=rs.getString(2);// name
				intro=rs.getString(4);// intro

		} catch (SQLException e) {
			System.out.print(e);
		}
	}
	
	//搜尋產品名稱關鍵字====
	public void serchProducts(String keyWord) {
		String sql = "SELECT * FROM TEAMWEB2020.PRODUCT WHERE prod_view=1 AND prod_name LIKE '%?%';";
		ConnDB conn = new ConnDB();
		conn.setPreparedStatement(sql);
		try {
			conn.setString(1, keyWord);
			conn.executeQuery();
			queryCount = conn.getQuery_count();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet rs = conn.getRs();
		
		try {
			rs.first();
			for (int i = 0; i < queryCount; i++) {

				idList.add(i, rs.getInt(1));// id
				priceList.add(i, rs.getInt(3));// price
				leftNumList.add(i, rs.getInt(5));// leftNum
				nameList.add(i, rs.getString(2));// name
				introList.add(i, rs.getString(4));// intro

				rs.next();
			}
			rs.first();
		} catch (SQLException e) {
			System.out.print(e);
		}
	}
	
	//vvv set get
		public ArrayList<Integer> getIdList() {return this.idList;}
		public ArrayList<Integer> getPriceList() {return this.priceList;}
		public ArrayList<Integer> getLeftNumList() {return this.leftNumList;}
		public ArrayList<String> getNameList() {return this.nameList;}
		public ArrayList<String> getIntroList() {return this.introList;}
		public int getQueryCount() {return this.queryCount;}
		public int getId() {return this.id;}
		public int getPrice() {return this.price;}
		public int getLeftNum() {return this.leftNum;}
		public String getName() {return this.name;}
		public String getIntro() {return this.intro;}
			//^^^ set get
}
