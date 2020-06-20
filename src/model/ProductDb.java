package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductDb implements Serializable{
	private static final long serialVersionUID = 1L;
	private String sql;
	private ConnDB conn= new ConnDB();
	private ResultSet rs;
	private int query_count=0;
	
	public int getQuery_count() {return this.query_count;}
	public Collection<Product> getProducts() throws SQLException{
		rs=null;
		sql="SELECT * FROM teamweb2020.product WHERE prod_view=1";
		ArrayList<Product> prodList = new ArrayList<Product>();
		
		conn.setPreparedStatement(sql);
		conn.executeQuery();
		rs=conn.getRs();
		this.query_count=conn.getQuery_count();
		while(rs.next()) {
			Product product = new Product(rs.getInt(1), rs.getString(2),
					rs.getInt(3), rs.getString(4),
					rs.getInt(5), rs.getString(6));
			prodList.add(product);
		}
		return prodList;
	}
	public Product getProduct(int prod_id) throws SQLException{
		rs=null;
		sql="SELECT * FROM teamweb2020.product WHERE prod_view=1 AND prod_id=?;";
		conn.setPreparedStatement(sql);
		conn.setInt(1, prod_id);
		conn.executeQuery();
		rs=conn.getRs();
		this.query_count=conn.getQuery_count();
		Product product=null;
		if (rs.next()) {
			product = new Product(rs.getInt(1), rs.getString(2),
					rs.getInt(3), rs.getString(4),
					rs.getInt(5), rs.getString(6));
		}
		return product;
	}
	
	public Collection<Product> searchProd(String keyWord) throws SQLException{
		rs=null;
		ArrayList<Product> prodList = new ArrayList<Product>();
		sql="SELECT * FROM teamweb2020.product WHERE prod_view=1 AND prod_name LIKE ?;";
		conn.setPreparedStatement(sql);
		conn.setString(1, "%"+keyWord+"%");
		conn.executeQuery();
		rs=conn.getRs();
		while (rs.next()) {
			Product product = new Product(rs.getInt(1), rs.getString(2),
					rs.getInt(3), rs.getString(4),
					rs.getInt(5), rs.getString(6));
			prodList.add(product);
		}
		return prodList;
	}
	
	public JSONArray searchProdJSONArray(String keyWord) throws SQLException{
		ArrayList<Product> srchedProdList = null;
		try {
			srchedProdList = (ArrayList<Product>) searchProd(keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray myJsonArr=new JSONArray();
		for(int i=0;i<srchedProdList.size();i++) {
			JSONObject myJsonObj=new JSONObject();
			myJsonObj.put("prod_id", srchedProdList.get(i).getProd_id());
			myJsonObj.put("prod_name", srchedProdList.get(i).getProd_name());
			myJsonObj.put("prod_introduction", srchedProdList.get(i).getProd_introduction());
			myJsonObj.put("prod_price", srchedProdList.get(i).getProd_price());
			myJsonObj.put("prod_stocksize", srchedProdList.get(i).getProd_stocksize());
			//==
			myJsonArr.put(myJsonObj);
		}
		return myJsonArr;
	} 
	
	public boolean isAmountEnough(int prod_id,int quantity) throws SQLException{
		rs=null;
		boolean bEnough = false;
		sql="SELECT prod_stocksize FROM teamweb2020.product WHERE prod_id=?";
		conn.setPreparedStatement(sql);
		conn.setInt(1, prod_id);
		conn.executeQuery();
		rs=conn.getRs();
		
		while (rs.next()) {
			int amount = rs.getInt(1);
			if (amount >= quantity) {
				bEnough = true;
			}
		}
		return bEnough;
	}
	
	public synchronized void buyProducts(int mem_id,Cart cart,String orderList_name,String orderList_mphone,String oderList_address,String orderList_email) throws SQLException{
		ConnDB conn = new ConnDB();
		Iterator<CartItem> it = cart.getItems().iterator();
		
		//寫入orderList table 訂單主檔=====
		//先抓id製作流水號
		sql="SELECT orderList_id FROM teamweb2020.orderList ORDER BY orderList_id DESC LIMIT 1;";
		conn.setPreparedStatement(sql);
		conn.executeQuery();
		ResultSet rs=conn.getRs();
		int orderList_id;
		if(rs.next()==true) {
			orderList_id=rs.getInt(1)+1;
		}else {
			orderList_id=1;
		}
		//寫入訂單主檔
		sql="INSERT INTO orderList(orderList_id,mem_id,orderList_name,orderList_mphone,orderList_address,orderList_email) VALUES(?,?,?,?,?,?);";
		conn.setPreparedStatement(sql);
		conn.setInt(1, orderList_id);
		conn.setInt(2, mem_id);
		conn.setString(3, orderList_name);
		conn.setString(4, orderList_mphone);
		conn.setString(5, oderList_address);
		conn.setString(6, orderList_email);
		conn.executeUpdate();
		
			while (it.hasNext()) {		
				CartItem item = (CartItem) it.next();
				Product product = item.getProduct();
				int prod_id = product.getProd_id();
				int quantity = item.getQuantity();
				//每個商品庫存減少
				sql="UPDATE teamweb2020.product SET prod_stocksize=prod_stocksize-? WHERE prod_id =?;";
				conn.setPreparedStatement(sql);
				conn.setInt(1, quantity);
				conn.setInt(2, prod_id);
				conn.executeUpdate();
				
				//每個商品寫入orderListDetail table 訂單明細====
				sql="INSERT INTO orderListDetail(orderList_id,prod_id,quantity) VALUES(?,?,?);";
				conn.setPreparedStatement(sql);
				conn.setInt(1, orderList_id);
				conn.setInt(2, prod_id);
				conn.setInt(3, quantity);
				conn.executeUpdate();
			}
		
	}
}
