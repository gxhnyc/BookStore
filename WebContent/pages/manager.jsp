<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <base href="../" target="iframe_content">
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>小狐仙在线书店-管理</title>
    <style type="text/css" media="screen">
        @import url('css/common.css');
        @import url('css/manager.css');
    </style>
    <script src="./js/book_store.js">
    </script>
    <!--|　|-->
</head>
<body>
    <header id="p_header"
		style="background: url(images/header_bac.png) no-repeat 100%;
			font-family:"楷体";heiht:14%;
			text-align: center;">
		<h1 style="text-shadow:3px 3px 0px orange; 
			color:brown;
			font-size: 35px;
			margin:10px auto;
			">小狐仙书城</h1>
		<p style="color:red;
			font-size: 20px;margin-top:10px;border-radius:5px;">
			<marquee id="affiche" align="middle" behavior="alternate"  direction="right" 
			height="30" width="900" hspace="50" vspace="20" loop="-1" scrollamount="10" 
			scrolldelay="100" onMouseOut="this.start()" onMouseOver="this.stop()" 
			style="color: #000fff; font-size: 14; font-family: '楷体'">小狐仙书城欢迎您！</marquee> </p>
			</header>			
   <iframe id="iframe_content" name="iframe_content" src="pages/user/userdetail.jsp"></iframe>
    <script type="text/javascript" charset="utf-8">
        book_store.setIFrame(document.location.href);
    </script>
    <nav>
        <a id＝"index_href" href="index.jsp" target="_parent">首页</a>
       <dl>
          <dt>个人信息管理</dt>
          <dd><a href="pages/user/userdetail.jsp">查看个人信息</a></dd>
          <dd><a href="modifyinfo.jsp">修改个人信息</a></dd>
          <dd><a href="">地址管理</a></dd>
          <dt>订单管理</dt>
          <dd><a href="pages/order/cart.jsp">购物车</a></dd>
          <dd><a href="pages/order/order.jsp">已完成交易的订单</a></dd>
          <dd><a href="">取消交易的订单</a></dd>
       </dl>
    </nav>
    <footer id="p_footer">
        <div></div>
        @Copyright <strong>小狐仙科技有限公司</strong>
    </footer>
</body>
</html>