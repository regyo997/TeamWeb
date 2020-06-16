<%@page import="java.sql.*" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.*"%>
<%
	if (session.getAttribute("mem_level") != null) {
		String mem_name = (String) session.getAttribute("mem_name");
		int mem_level = Integer.parseInt(session.getAttribute("mem_level").toString());
	
		if (mem_name == "" || mem_level < 9) {
			session.setAttribute("msg", "非管理者不能進入管理介面！");
			response.sendRedirect("login.jsp");
		}
	}else{
		session.setAttribute("msg", "請先登入會員再進入！");
		response.sendRedirect("login.jsp");
	}

	String sql = "SELECT * FROM teamweb2020.member ORDER BY mem_id ASC";
	ConnDB cn = new ConnDB();
	cn.setPreparedStatement(sql);
	cn.executeQuery();
	ResultSet rs=cn.getRs();
	rs.first();
	int query_count = cn.getQuery_count();
	
	if(request.getParameter("mem_level")!=null){
		ConnDB conn = new ConnDB();
		int mem_id=Integer.parseInt(request.getParameter("mem_id"));
		int mem_level=Integer.parseInt(request.getParameter("mem_level"));		
		sql="UPDATE teamweb2020.member SET mem_level=? WHERE mem_id = ?;";
		conn.setPreparedStatement(sql);
		conn.setInt(1, mem_level);
		conn.setInt(2, mem_id);
		conn.executeUpdate();
		response.sendRedirect("admin_member.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootshop online Shopping cart</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/layout.css">
</head>
<body>
	<h1><strong>會員管理</strong></h1>
<p class="w3-center">會員總人數：<%=query_count %>人</p>
<form method="post" action="admin_index.jsp">
	<input type="submit" id="btnQuit" value="離開" style="float:right;font-family:DFKai-sb;width:3%;">
</form>
<div class="w3-row w3-gray textCenter">
  <div class="w3-col m3">會員[id]名稱</div>
  <div class="w3-col m4">會員帳號</div>
  <div class="w3-col m4">會員權限等級</div>
  <div class="w3-col m1">刪除</div>
</div>


<% while(rs.next()){ %>
<div class="w3-row w3-border-bottom textCenter">
  <div class="w3-col m3 textLeft overflowH">
	[<%=rs.getInt(1)%>]<%=rs.getString(2)%>
  </div>
  <div class="w3-col m4 textLeft overflowH">
    <%=rs.getString(3)%>
  </div>
  <div class="w3-col m4"> 
    <form name="update_mem_level_form" method="post" action="admin_member.jsp">
      <%=rs.getInt(5)%>
      <select name="mem_level">
        <option value="1" <% if(rs.getInt(5)==1) out.print("selected"); %>>1: 申請未驗證</option>
        <option value="2" <% if(rs.getInt(5)==2) out.print("selected"); %>>2: 一般會員</option>
        <option value="9" <% if(rs.getInt(5)==9) out.print("selected"); %>>9: 系統管理者</option>
      </select>
      <input type="submit" value="確定更新">
      <input type="hidden" name="mem_id" value="<%=rs.getInt(1) %>">
    </form>
  </div>
  <div class="w3-col m1 w3-center">
    <a class="deleData" href="#">刪除</a>
  </div>
</div>

<% }
%>


</body>
</html>