package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Part;

public class UploadProduct implements Serializable{
	private static final long serialVersionUID = 1L;

	public void upload(String prod_name,int prod_price,String prod_introduction,int prod_stocksize,String mypath,Part img,String tmppath) throws SQLException, IOException {
		int prod_id=1;
		String imgNewName;
		
		//===
		ConnDB conn= new ConnDB();
		//===DB_Query 抓出自動產生的id是多少
		conn=new ConnDB();
		conn.setPreparedStatement("Select prod_id from teamweb2020.product;");
			conn.executeQuery();
			ResultSet rs = conn.getRs();
			rs.last();
			prod_id=rs.getInt("prod_id")+1;
		imgNewName=String.valueOf(prod_id);
		//===
		//===DB Update
			String sql="INSERT INTO teamweb2020.product(prod_id,prod_name,prod_price,prod_introduction,prod_stocksize,prod_img,prod_view) VALUE(?,?,?,?,?,?,1);";
			
			conn.setPreparedStatement(sql);
				conn.setInt(1, prod_id);
				conn.setString(2, prod_name);
				conn.setInt(3, prod_price);
				conn.setString(4, prod_introduction);
				conn.setInt(5, prod_stocksize);
				conn.setString(6, mypath);
				conn.executeUpdate();
			
		//檔案I/O
			InputStream is=img.getInputStream();
			OutputStream os=new FileOutputStream(mypath+"\\uploadedIMG\\"+imgNewName+".jpg");
			OutputStream ostmp=new FileOutputStream(tmppath+"\\uploadedIMG\\"+imgNewName+".jpg");//tomcat用不到，eclipse測試會用到
			byte [] byteArr=new byte[(int) img.getSize()];
			is.read(byteArr);
			os.write(byteArr);
			is.close();
			os.close();
			//===
			ostmp.write(byteArr);
			ostmp.close();
		
	}

}
