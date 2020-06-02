<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productdb" scope="application" class=model.ProductDb/>
<jsp:useBean id="cart" scope="session" class=model.Cart />
<%
	String strProd_id = request.getParameter("add");
	if(strProd_id != null && strProd_id != ""){
		int prod_id = Integer.parseInt(strProd_id);
		Product product = productdb.getProduct(prod_id);
		cart.addItem(new Integer(prod_id), product);
	}
%>