<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="././" target="_parent">
<meta charset="utf-8">
<title>小狐仙在线书店-详细信息</title>
<style type="text/css" media="screen">
@import url('css/common.css');

@import url('css/book_detail.css');

@import url('css/nav_right.css');
</style>
<script type="text/javascript" src="././js/jquery-2.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		//var bid=$("#fid").text();
		$.ajax({
			url : "loaddetailcart",
			type : "post",
			data : {
				"bid" : ""
			},
			success : function(data) {
				//alert(data); 
				//将json数组转换成对象数组
				var arr = JSON.parse(data);
				var ul = $("#showcart").css({
					"font-size" : "10px",
					"color" : "brown"
				});
				ul.text("");
				var total = 0;
				for (var i = 0; i < arr.length; i++) {
					var html = "<li>" + arr[i].book_name + "<ul>" + "<li>　编号:"
							+ arr[i].book_id + "</li>" + "<li>　数量:"
							+ arr[i].quantity + "</li>" + "<li>　小记:¥"
							+ arr[i].subtotal + "</li>"
							+ "<li class='operator'><button>删除</button></li>"
							+ "</ul>" + "</li>";
					total += arr[i].subtotal;
					ul.append(html);
				}
				$("#total").text("合计:¥" + total);
			}
		});

		$("#joinbt")
				.click(
						function() {
							var bid = $("#fid").text();
							$
									.ajax({
										url : "joincart",
										type : "post",
										data : {
											"bid" : bid
										},
										success : function(data) {
											//alert(data); 
											//将json数组转换成对象数组
											var arr = JSON.parse(data);
											if (arr.length > 1) {
												var ul = $("#showcart").css({
													"font-size" : "10px",
													"color" : "brown"
												});
												ul.text("");
												var total = 0;
												for (var i = 0; i < arr.length; i++) {
													var html = "<li>"
															+ arr[i].book_name
															+ "<ul>"
															+ "<li>  编号:"
															+ arr[i].book_id
															+ "</li>"
															+ "<li>　数量:"
															+ arr[i].quantity
															+ "</li>"
															+ "<li>　小记:¥"
															+ arr[i].subtotal
															+ "</li>"
															+ "<li class='operator'><button>删除</button></li>"
															+ "</ul>" + "</li>"
													total += arr[i].subtotal;
													ul.append(html);
												}
												$("#total")
														.text("合计:¥" + total);
											} else {
												window.location.href = "http://localhost:8080/BookStore_V1.1-1/pages/login_register.jsp";//需要跳转的地址
											}
										}
									});
						});
	});
</script>


<!--|　|-->
</head>
<body>
	<header id="p_header">
		小狐仙在线书店
		<div></div>
	</header>
	<article class="left">
		<header>
			图书详细信息<a style="margin-left: 80%" href="././index.jsp">首页</a>
		</header>
		<section class="detail">
			<figure>
				<img src="${book.IMAGE }">
				<figcaption>${book.DESCRIPTION }</figcaption>
			</figure>
			<ul>
				<li>书 名: 《${book.NAME}》</li>
				<li>单 价: ¥${book.SELLING_PRICE}</li>
				<li>销售数量: 1000本</li>
				<li>作 者: ${book.AUTHOR }</li>
				<li>出 版 社: ${book.BOOK_CONCERN }</li>
				<li>出版时间: ${book.PUBLISHING_DATE }</li>
				<li>ＩＳＢＮ: ${book.ISBN }</li>
				<li>图书编号: <font id="fid" name="bid">${book.ID }</font></li>
			</ul>
			<button id="joinbt">加入购物车</button>
		</section>
		<section class="description">
			<header>内容介绍</header>
			<div>${book.INSTRODUCTION }</div>
		</section>
		<section class="description">
			<header>作者简介</header>
			<div>${book.AUTHOR_INTRODUCTION }</div>
		</section>
		<section class="cakalog">
			<header>目录</header>
			<pre>
            第一章：爱人与仇人都会老去
            第二章：每一个大人都是小孩变的
            第三章：世界上那些最亲近的别人
            第四章：水泥森林里终究相遇
            第五章：白马总在黑夜抵达
            </pre>
		</section>
	</article>
	<article class="right">
		<form action="#" method="get" class="search" accept-charset="utf-8">
			<input type="text" name="content">
			<button type="submit">搜索</button>
		</form>
		<nav>
			<header>管理</header>
			<ul>
				<c:if test="${logflag > 0 }">
					<li><font style="color: red;">用户:${userMap.ACCOUNT}</font></li>
				</c:if>
				<c:if test="${logflag<=0 }">
					<li><a href="pages/login_register.jsp">登录或注册</a></li>
				</c:if>
				<li><a href="pages/manager.html">用户管理</a></li>
				<li><a href="pages/manager.html#conent/order/order.html">订单管理</a>
				</li>
			</ul>
		</nav>
		<section class="carts">
			<header>购物车</header>
			<ul id="showcart">
			</ul>
			<footer>
				<font id="total"></font>
				<button
					onclick="document.location.href='pages/manager.jsp#conent/order/cart.jsp'">
					提交</button>
			</footer>
		</section>
	</article>
	<footer id="p_footer">
		<div></div>
		@Copyright <strong>小狐仙科技有限公司</strong>
	</footer>
</body>
</html>
