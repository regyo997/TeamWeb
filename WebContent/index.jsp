<%@page import="model.*"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Locale,java.util.ResourceBundle" %>
<%
Cart cart;
if(session.getAttribute("cart")==null){
	cart=new Cart();
	session.setAttribute("cart", cart);
}else{
	cart=(Cart)session.getAttribute("cart");
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
<!--Less styles -->
<!-- Other Less css file //different less files has different color scheam
	<link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
	-->
<!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
	<script src="themes/js/less.js" type="text/javascript"></script> -->

<!-- Bootstrap style -->
<link id="callCss" rel="stylesheet"
	href="themes/bootshop/bootstrap.min.css" media="screen" />
<link href="themes/css/base.css" rel="stylesheet" media="screen" />
<!-- Bootstrap style responsive -->
<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="themes/css/font-awesome.css" rel="stylesheet"
	type="text/css">
<!-- Google-code-prettify -->
<link href="themes/js/google-code-prettify/prettify.css"
	rel="stylesheet" />
<!-- fav and touch icons -->
<link rel="shortcut icon" href="themes/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="themes/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="themes/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="themes/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="themes/images/ico/apple-touch-icon-57-precomposed.png">
<style type="text/css" id="enject"></style>
<script src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
		if(new URLSearchParams(window.location.search).get('msg')==1){
			alert("購買完成!!謝謝光臨!");
		}
	});
</script>
</head>
<body>
	<div id="header">
		<div class="container">
			<div id="welcomeLine" class="row">
				<div class="span6">
					<%-- vvv插入歡迎使用者名稱 --%>
					<%
						if(session.getAttribute("mem_id")!=null){
					%>
					<%
						Locale locale=Locale.getDefault(); 
						ResourceBundle res=ResourceBundle.getBundle("msg",locale);
					%>
					<%=res.getString("wel") %><strong> <%=(String)session.getAttribute("mem_name")%>!!</strong>
					<%
						}else{
					%>
					Sign in! Hurry!!
					<%
						}
					%>
					<%-- ^^^插入歡迎使用者名稱 --%>
					&nbsp;&nbsp;
					<%-- vvv插入受訪人數 --%>
					<% String ip=request.getRemoteAddr(); 
					%>
					<%
						//useBean scope=session
									model.Mem_visit_count viewCount;
									if(session.getAttribute("viewCount")==null){
										viewCount = new model.Mem_visit_count();
										session.setAttribute("viewCount", viewCount);
									}else{
										viewCount=(model.Mem_visit_count)session.getAttribute("viewCount");
									}
					%>
					<%  if(session.getAttribute("mem_id")!=null){
							int id=(int)session.getAttribute("mem_id");
							viewCount.setViewId(id);
							viewCount.setViewIp(ip);
					}   
					%>
					本站受訪次數:
					<%=viewCount.getViewN()%>
					<%-- ^^^插入受訪人數 --%>
				</div>
				<div class="span6">
					<div class="pull-right">
						<%
							if (session.getAttribute("mem_level") != null) {
							int mem_level = Integer.parseInt(session.getAttribute("mem_level").toString());
								if (mem_level == 9) {
						%>
						<a href="admin_index.jsp"><span class="">#### 後台管理 ####</span></a>
						<%
								}
							}
						%>
						<span class="btn btn-mini">En</span> <a href="product_summary.jsp"><span>&pound;</span></a>
						<span class="btn btn-mini">$155.00</span> <a
							href="product_summary.jsp"><span class="">$</span></a> <a
							href="product_summary.jsp"><span
							class="btn btn-mini btn-primary"><i
								class="icon-shopping-cart icon-white"></i> [ <%=cart.getTotalQuantity() %> ] Itemes in your
								cart </span> </a>
					</div>
				</div>
			</div>
			<!-- Navbar ================================================== -->
			<div id="logoArea" class="navbar">
				<a id="smallScreen" data-target="#topMenu" data-toggle="collapse"
					class="btn btn-navbar"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				<div class="navbar-inner">
					<a class="brand" href="index.jsp"><img
						src="themes/images/logo.png" alt="Bootsshop" /></a>
					<form class="form-inline navbar-search" method="get"
						action="products.jsp">
						<input id="srchFld" class="srchTxt" type="text" name="keyWord"/> <select
							class="srchTxt">
							<option>All</option>
							<option>CLOTHES</option>
							<option>FOOD AND BEVERAGES</option>
							<option>HEALTH & BEAUTY</option>
							<option>SPORTS & LEISURE</option>
							<option>BOOKS & ENTERTAINMENTS</option>
						</select>
						<button  type="submit" id="submitButton" class="btn btn-primary">Go</button>
					</form>
					<ul id="topMenu" class="nav pull-right">
						<li class=""><a href="mem_index.jsp">會員中心</a></li>
						<li class=""><a href="#">商品評價</a></li>
						<li class=""><a href="contact.jsp">聯絡我們</a></li>
						<li class="">
							<%
								String mem_name = (String) session.getAttribute("mem_name");
								if (mem_name != null) {
							%>
								<a href="/TeamWeb/Logout" role="button" data-toggle="modal"
							style="padding-right: 0"><span
								class="btn btn-large btn-success">登出</span></a> 
							<%
								} else {
							%> 
									<a	href="#login" role="button" data-toggle="modal"
							style="padding-right: 0"><span
								class="btn btn-large btn-success">登入</span></a> 
							<%
								}
							%>
							<div id="login" class="modal hide fade in" tabindex="-1"
								role="dialog" aria-labelledby="login" aria-hidden="false">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">×</button>
									<h3>Login Block</h3>
								</div>
								<div class="modal-body">
									<form class="form-horizontal loginFrm" method="post"
										action="doLogin">
										<div class="control-group">
											<input name="login_email" type="text" id="inputEmail" placeholder="Email">
										</div>
										<div class="control-group">
											<input name="login_password" type="password" id="inputPassword"
												placeholder="Password">
										</div>
										<div class="control-group">
											<label class="checkbox"> <input type="checkbox">
												記住我
											</label>
										</div>
										<button type="submit" class="btn btn-success">登入</button>
										<button class="btn" data-dismiss="modal" aria-hidden="true">關閉</button>
									</form>
									
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Header End====================================================================== -->
	
	<!--vvv 使用Bean查詢商品數量 -->
	
	<%
			model.ProductDb myBean=new ProductDb();
			ArrayList<Product> arrP = (ArrayList)myBean.getProducts();
			if(myBean.getQuery_count()>0){
		%>
	<!--^^^ 使用Bean查詢商品數量 -->	
	<div id="carouselBlk">
		<div id="myCarousel" class="carousel slide">
			<div class="carousel-inner">
				<div class="item active">	
					<div class="container">
					<!-- vvv等著被抽換 -->
						<a href="register.jsp">
						<img style="width: 40%; float:left;overflow:hidden;" src="uploadedIMG/<%=arrP.get(myBean.getQuery_count()-1).getProd_id()%>.jpg?sa=<%=(int)(Math.random()*10000)%>" alt="" /></a>
						<div style="font-family: 'Noto Serif TC', serif;margin-left: 50%;max-width:40%;">
							<h4 style="font-size:250%;text-align:left;padding-top: 5%;padding-bottom: 5%;"><%=arrP.get(myBean.getQuery_count()-1).getProd_name()%></h4>
							<p style="font-size:150%;text-align:left;text-indent:2em;line-height:2em;"><%=arrP.get(myBean.getQuery_count()-1).getProd_introduction()%></p>
						</div>
					<!-- ^^^等著被抽換 -->
					</div>
				</div>
				<!---->
				
					<%
						for(int i=myBean.getQuery_count()-2;i>=0;i--){//要改用Array[Qurery數量]
					%>
						<div class="item">
							<div class="container">
								<a href="register.jsp">
								<img style="width: 40%; float:left;overflow:hidden;" src="uploadedIMG/<%=arrP.get(i).getProd_id()%>.jpg?sa=<%=(int)(Math.random()*10000)%>" alt="" /></a>
								<div style="font-family: 'Noto Serif TC', serif;margin-left: 50%;max-width:40%;">
									<h4 style="font-size:250%;text-align:left;padding-top: 5%;padding-bottom: 5%;"><%=arrP.get(i).getProd_name()%></h4>
									<p style="font-size:150%;text-align:left;text-indent:2em;line-height:2em;"><%=arrP.get(i).getProd_introduction()%></p>
								</div>
							</div>
						</div>
<%
						//^^^呈現圖片
						}
	}
%>
				
				<!--^^^-->
				
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			<a class="right carousel-control" href="#myCarousel"
				data-slide="next">&rsaquo;</a>
		</div>
	</div>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<div id="sidebar" class="span3">
					<div class="well well-small">
						<a id="myCart" href="product_summary.jsp"><img
							src="themes/images/ico-cart.png" alt="cart"><%=cart.getTotalQuantity() %> Items in your
							cart <span class="badge badge-warning pull-right">$<%=cart.getTotalPrice()%></span></a>
					</div>
					<ul id="sideManu" class="nav nav-tabs nav-stacked">
						<li class="subMenu open"><a> ELECTRONICS [230]</a>
							<ul>
								<li><a class="active" href="products.jsp"><i
										class="icon-chevron-right"></i>Cameras (100) </a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Computers, Tablets & laptop
										(30)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Mobile Phone (80)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Sound & Vision (15)</a></li>
							</ul></li>
						<li class="subMenu"><a> CLOTHES [840] </a>
							<ul style="display: none">
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Women's Clothing (45)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Women's Shoes (8)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Women's Hand Bags (5)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Men's Clothings (45)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Men's Shoes (6)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Kids Clothing (5)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Kids Shoes (3)</a></li>
							</ul></li>
						<li class="subMenu"><a>FOOD AND BEVERAGES [1000]</a>
							<ul style="display: none">
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Angoves (35)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Bouchard Aine & Fils (8)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>French Rabbit (5)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Louis Bernard (45)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>BIB Wine (Bag in Box) (8)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Other Liquors & Wine (5)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Garden (3)</a></li>
								<li><a href="products.jsp"><i
										class="icon-chevron-right"></i>Khao Shong (11)</a></li>
							</ul></li>
						<li><a href="products.jsp">HEALTH & BEAUTY [18]</a></li>
						<li><a href="products.jsp">SPORTS & LEISURE [58]</a></li>
						<li><a href="products.jsp">BOOKS & ENTERTAINMENTS [14]</a></li>
					</ul>
					<br />
					<div class="thumbnail">
						<img src="themes/images/products/panasonic.jpg"
							alt="Bootshop panasonoc New camera" />
						<div class="caption">
							<h5>Panasonic</h5>
							<h4 style="text-align: center">
								<a class="btn" href="product_details.jsp"> <i
									class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i
									class="icon-shopping-cart"></i></a> <a class="btn btn-primary"
									href="#">$222.00</a>
							</h4>
						</div>
					</div>
					<br />
					<div class="thumbnail">
						<img src="themes/images/products/kindle.png"
							title="Bootshop New Kindel" alt="Bootshop Kindel">
						<div class="caption">
							<h5>Kindle</h5>
							<h4 style="text-align: center">
								<a class="btn" href="product_details.jsp"> <i
									class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i
									class="icon-shopping-cart"></i></a> <a class="btn btn-primary"
									href="#">$222.00</a>
							</h4>
						</div>
					</div>
					<br />
					<div class="thumbnail">
						<img src="themes/images/payment_methods.png"
							title="Bootshop Payment Methods" alt="Payments Methods">
						<div class="caption">
							<h5>Payment Methods</h5>
						</div>
					</div>
				</div>
				<!-- Sidebar end=============================================== -->
				<!--vvv產品=====================================-->
				<div class="span9">
					
					<h4>Latest Products</h4>
					<ul class="thumbnails">
					<%
						if(myBean.getQuery_count()>0){
						for(int i=myBean.getQuery_count()-1;i>=0;i--){
					%>
						<li class="span3">
							<div class="thumbnail">
								<a href="product_details.jsp?prod=<%=arrP.get(i).getProd_id()%>"
								>
									<img src="uploadedIMG/<%=arrP.get(i).getProd_id()%>.jpg?sa=<%=(int)(Math.random()*10000)%>" alt="" /></a>
								<div class="caption">
									<h5><%=arrP.get(i).getProd_name()%> &nbsp&nbsp $<%=arrP.get(i).getProd_price()%></h5> 
									<!--  <p><%=arrP.get(i).getProd_introduction()%></p>-->  

									<h4 style="text-align: center">
<!--										<a class="btn" href="product_details.jsp?prod=<%=arrP.get(i).getProd_id()%>"> 
											<i class="icon-zoom-in"></i>
 										 </a> 
										<a class="btn" href="#">Add to
											<i class="icon-shopping-cart"></i>
										</a> 
										<a class="btn btn-primary" href="#">
 											$<%=arrP.get(i).getProd_price()%>
										</a>
-->									</h4>
								</div>
							</div>
						</li>

					<%
						}
					}
					%>
						
					</ul>

				</div>
				<!--^^^產品========================================-->
			</div>
		</div>
	</div>
	<!-- Footer ================================================================== -->
	<div id="footerSection">
		<div class="container">
			<div class="row">
				<div class="span3">
					<h5>ACCOUNT</h5>
					<a href="login.jsp">YOUR ACCOUNT</a> <a href="login.jsp">PERSONAL
						INFORMATION</a> <a href="login.jsp">ADDRESSES</a> <a href="login.jsp">DISCOUNT</a>
					<a href="login.jsp">ORDER HISTORY</a>
				</div>
				<div class="span3">
					<h5>INFORMATION</h5>
					<a href="contact.jsp">CONTACT</a> <a href="register.jsp">REGISTRATION</a>
					<a href="legal_notice.jsp">LEGAL NOTICE</a> <a href="tac.jsp">TERMS
						AND CONDITIONS</a> <a href="faq.jsp">FAQ</a>
				</div>
				<div class="span3">
					<h5>OUR OFFERS</h5>
					<a href="#">NEW PRODUCTS</a> <a href="#">TOP SELLERS</a> <a
						href="special_offer.jsp">SPECIAL OFFERS</a> <a href="#">MANUFACTURERS</a>
					<a href="#">SUPPLIERS</a>
				</div>
				<div id="socialMedia" class="span3 pull-right">
					<h5>SOCIAL MEDIA</h5>
					<a href="#"><img width="60" height="60"
						src="themes/images/facebook.png" title="facebook" alt="facebook" /></a>
					<a href="#"><img width="60" height="60"
						src="themes/images/twitter.png" title="twitter" alt="twitter" /></a> <a
						href="#"><img width="60" height="60"
						src="themes/images/youtube.png" title="youtube" alt="youtube" /></a>
				</div>
			</div>
			<p class="pull-right">&copy; Bootshop</p>
		</div>
		<!-- Container End -->
	</div>
	<!-- Placed at the end of the document so the pages load faster ============================================= -->
	<script src="themes/js/jquery.js" type="text/javascript"></script>
	<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="themes/js/google-code-prettify/prettify.js"></script>

	<script src="themes/js/bootshop.js"></script>
	<script src="themes/js/jquery.lightbox-0.5.js"></script>

	<!-- Themes switcher section ============================================================================================= -->
	<div id="secectionBox">
		<link rel="stylesheet" href="themes/switch/themeswitch.css"
			type="text/css" media="screen" />
		<script src="themes/switch/theamswitcher.js" type="text/javascript"
			charset="utf-8"></script>
		<div id="themeContainer">
			<div id="hideme" class="themeTitle">Style Selector</div>
			<div class="themeName">Oregional Skin</div>
			<div class="images style">
				<a href="themes/css/#" name="bootshop"><img
					src="themes/switch/images/clr/bootshop.png"
					alt="bootstrap business templates" class="active"></a> <a
					href="themes/css/#" name="businessltd"><img
					src="themes/switch/images/clr/businessltd.png"
					alt="bootstrap business templates" class="active"></a>
			</div>
			<div class="themeName">Bootswatch Skins (11)</div>
			<div class="images style">
				<a href="themes/css/#" name="amelia" title="Amelia"><img
					src="themes/switch/images/clr/amelia.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="spruce" title="Spruce"><img
					src="themes/switch/images/clr/spruce.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="superhero" title="Superhero"><img
					src="themes/switch/images/clr/superhero.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="cyborg"><img src="themes/switch/images/clr/cyborg.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="cerulean"><img
					src="themes/switch/images/clr/cerulean.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="journal"><img src="themes/switch/images/clr/journal.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="readable"><img
					src="themes/switch/images/clr/readable.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="simplex"><img src="themes/switch/images/clr/simplex.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="slate"><img src="themes/switch/images/clr/slate.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="spacelab"><img
					src="themes/switch/images/clr/spacelab.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="united"><img src="themes/switch/images/clr/united.png"
					alt="bootstrap business templates"></a>
				<p
					style="margin: 0; line-height: normal; margin-left: -10px; display: none;">
					<small>These are just examples and you can build your own
						color scheme in the backend.</small>
				</p>
			</div>
			<div class="themeName">Background Patterns</div>
			<div class="images patterns">
				<a href="themes/css/#" name="pattern1"><img
					src="themes/switch/images/pattern/pattern1.png"
					alt="bootstrap business templates" class="active"></a> <a
					href="themes/css/#" name="pattern2"><img
					src="themes/switch/images/pattern/pattern2.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern3"><img
					src="themes/switch/images/pattern/pattern3.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern4"><img
					src="themes/switch/images/pattern/pattern4.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern5"><img
					src="themes/switch/images/pattern/pattern5.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern6"><img
					src="themes/switch/images/pattern/pattern6.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern7"><img
					src="themes/switch/images/pattern/pattern7.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern8"><img
					src="themes/switch/images/pattern/pattern8.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern9"><img
					src="themes/switch/images/pattern/pattern9.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern10"><img
					src="themes/switch/images/pattern/pattern10.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern11"><img
					src="themes/switch/images/pattern/pattern11.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern12"><img
					src="themes/switch/images/pattern/pattern12.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern13"><img
					src="themes/switch/images/pattern/pattern13.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern14"><img
					src="themes/switch/images/pattern/pattern14.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern15"><img
					src="themes/switch/images/pattern/pattern15.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern16"><img
					src="themes/switch/images/pattern/pattern16.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern17"><img
					src="themes/switch/images/pattern/pattern17.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern18"><img
					src="themes/switch/images/pattern/pattern18.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern19"><img
					src="themes/switch/images/pattern/pattern19.png"
					alt="bootstrap business templates"></a> <a href="themes/css/#"
					name="pattern20"><img
					src="themes/switch/images/pattern/pattern20.png"
					alt="bootstrap business templates"></a>

			</div>
		</div>
	</div>
	<span id="themesBtn"></span>
</body>
</html>