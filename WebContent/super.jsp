<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="../" target="iframe_content">
<meta charset="utf-8">
<style>


</style>
<title>在线订餐-管理</title>
<style type="text/css" media="screen">


</style>
<script src="js/book_store.js">
    </script>
<!--|　|-->
</head>
<body>
	<header id="p_header">
		在线书城
		<div></div>
	</header>
	<iframe id="iframe_content" name="iframe_content"
		src="${empty manager_iframe ? 'pages/user/detail.jsp': manager_iframe}"></iframe>
	<script type="text/javascript" charset="utf-8">
        book_store.setIFrame(document.location.href);
    </script>
	<nav>
		<a id＝"index_href" href="../Hotel/index2.jsp" target="_parent">返回点餐</a>
		<dl>
			<dt>个人信息管理</dt>
			<dd>
				<a href="pages/user/detail.jsp">查看个人信息</a>
			</dd>
			<dd>
				<a href="">修改个人信息</a>
			</dd>
			<dd>
				<a href="">地址管理</a>
			</dd>
			<dt>订单管理</dt>
			<dd>
				<a href="pages/order/cart.jsp">购物车</a>
			</dd>
			<dd>
				<a href="pages/order/order.jsp">已完成交易的订单</a>
			</dd>
			<dd>
				<a href="">取消交易的订单</a>
			</dd>
		</dl>
	</nav>
	<footer id="p_footer">
		<div></div>
		@Copyright <strong>One.在线订餐</strong>
	</footer>
</body>
</html>