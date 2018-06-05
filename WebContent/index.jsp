<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<!-- 小狐仙书城首页 -->
<base href="./" target="_parent">
<meta charset="utf-8">
<title>小狐仙在线书店</title>
<style type="text/css" media="screen">
@import url('css/common.css');
@import url('css/index.css');
@import url('css/nav_right.css');
</style>
<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
<script type="text/javascript">
	$(function() {

		//------模拟请求操作，去数据库中查询出所有书籍显示在页面
		var pagenum = $("#lognum").text();//1.获取id为lognum的<a>的文本内容    	 	
		if (pagenum != 1) {
			//如果是第一次,则pagenum是获取不到任何值的,所以执行$("#form").submit();
			//提交<form>至IndexServlet进行处理.若不是第一次加载页面,则不执行			
			$("#form").submit();
		}

		//----分页显示
		$(".next").click(function() {
			var num = $(".next").attr("page");//获得.next标签的page属性的值,第一次获取为空
			var page = parseInt(num) + 1;

			$("#form")[0].action = "next?pagenum=" + page;
			$("#form").submit();

		});
		//--
		$(".previous").click(function() {
			var num = $(".next").attr("page");//获得.next标签的page属性的值,第一次获取为空
			var page = parseInt(num) - 1;

			$("#form")[0].action = "next?pagenum=" + page;
			$("#form").submit();

		});

		/* 页面加载时，加载购物车内容 */
		$.ajax({
			url : "loaddetailcart",
			type : "post",
			data : {
				"_bid" : ""
			},
			success : function(data) {
				//alert(data); 
				//将json数组转换成对象数组
				var arr = JSON.parse(data);
				var ul = $("#showcart").css({"font-size":"10px","color":"brown"});
				ul.text("");
				var total = 0;
				for (var i = 0; i < arr.length; i++) {
					var html = "<li>" + arr[i].book_name + "<ul>" + "<li>　编号:"
							+ "<font id='rbook_id'>"+arr[i].book_id+"</font>"
							+ "</li>" + "<li>　数量:"
							+ arr[i].quantity + "</li>" + "<li>　小记:¥"
							+ arr[i].subtotal + "</li>"
							+ "<li class='operator'><button id='rmbtn'>删除</button></li>"
							+ "</ul>" + "</li>"
					total += arr[i].subtotal;
					ul.append(html);
				}
				$("#total").text("合计:¥" + total);
			}
		});
		
		
		//rmbtn删除购物项
		$("#rmbtn").click(function(){
			alert("删除购物项");
			var book_id=$("#rbook_id").text();
			$.ajax({
				url : "rmcartitem",
				type : "post",
				data : {
					"rbook_id" : book_id
				},
				success : function(data) {
					alert(222);
				}
				});
		
		});

	});
</script>


</head>
<body>
	<c:if test="${bookList.size()>0 }">
		<a id="lognum" hidden="hidden">1</a>
	</c:if>
	<form action="next" method="post" id="form"></form>
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
	<article>
		
		<c:forEach items="${bookList }" var="book">
			<figure>
				<a href="bookdetail?id=${book.id }"><img src="${book.image }"></a>
				<footer> ${book.name }　单价:¥${book.selling_price } </footer>
				<figcaption>${book.description }<br> <a
						id="addcart" href="bookdetail?id=${book.id }"
						style="background-color: red; border-radius: 5px; color: yellow">加入购物车</a>
				</figcaption>
			</figure>
		</c:forEach>

		<!-- -->

		<nav class="list_nav">
			<div class="next" page="${pagenum }"><%-- ${pagenum } --%></div>
			<a href="index.html#top">1</a> <a href="index.html#top">2</a>
			 <a	href="index.html#top">3</a> <a href="index.html#top">4</a> 
			 <a	href="index.html#top">5</a> <a href="index.html#top">7</a> 
			 <a	href="index.html#top">20</a>
			<div class="previous"></div>
		</nav>
	</article>
	<article class="right">
		<embed src="sound/云水禅心.mp3" autostart="true" loop="-1" hidden="true" />
		<form action="#" method="get" class="search" accept-charset="utf-8">
			<input type="text" name="content">
			<button type="submit">搜索</button>
		</form>
		<nav id="manager">
			<header>管理</header>
			<ul>
				<c:choose>
				<c:when test="${logflag>0 }">
				<li><font style="color: yellow;font-family:Georgia, serif;">您好，${userMap.ACCOUNT }，欢迎来到小狐仙书城</font></li>
				<li><a href="pages/login_register.jsp" style="background: aqua;">用户注销</a></li>
				</c:when>
				<c:otherwise>
				<li><a href="pages/login_register.jsp" style="background: aqua;">登录或注册</a></li>
				</c:otherwise>
				</c:choose>
				
				<li><a href="pages/manager.html" style="background: aqua;">用户管理</a></li>
				<li><a href="pages/manager.html#conent/order/order.html" style="background: aqua;">订单管理</a>
				</li>
			</ul>
		</nav>
		<section>
			<header>购物车</header>
			<ul id="showcart">

			</ul>
			<footer>
				<font id="total"></font>
				<button
					onclick="document.location.href='pages/manager.jsp#conent/order/cart.jsp'">
					提　交</button>
			</footer>
		</section>
	</article>
	<footer id="p_footer">
		<div></div>
		@Copyright <strong>小狐仙科技有限公司</strong>
	</footer>
</body>
</html>