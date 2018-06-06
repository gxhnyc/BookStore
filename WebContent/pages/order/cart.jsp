<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<base href="../../" target="_parent">
<meta charset="utf-8">
<title>小狐仙在线书城</title>
<style type="text/css" media="screen">
@import url('css/common.css');

@import url('css/cart.css');
</style>
<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		//1. 注册事件
		$("input[name=quantity]").change(function() {
			//2. 验证数据的有效性
			var number = this.value; //也可以使用$(this).val();
			//isNaN(number)表示若number不是数字就返回真
			if (!isNaN(number) && parseInt(number) == number && number > 0) {
				//如果合法，同步更新的数
				$(this).attr("lang", number);
				//找到当前标签中第一个是tr的父节点，然后拿到属性为lang的值，也就是商品(书籍)的id
				var pid = $(this).parents("ul:first").attr("lang");
				//发送Ajax请求，传输当前的数量与商品的id，返回修改数量后的总价格
				$.ajax({
					url : 'modquantity',
					type : 'post',
					data : {
						'pid' : pid,
						'num' : number
					},
					success : function(data) {

						var arr = JSON.parse(data);
						alert(arr);
						var fid = arr[1];
						$("#" + fid).text(arr[0]);

					}
				});

			} else {
				//如果非法，还原为刚刚合法的数
				this.value = $(this).attr("lang");
			}

		});
	});
</script>


</head>

<body>
	<article>
		<header>提交订单</header>
		<section>
			<header>购物车信息</header>
			<ul class="caption">
				<li class="id">编号</li>
				<li class="name">名称</li>
				<li class="price">单价</li>
				<li class="quantity">数量(本)</li>
				<li class="total">小记</li>
				<li class="operator">操作</li>
			</ul>
			<!--  添加购物车信息 -->

			<c:forEach items="${cartList }" var="cart">
				<ul class="odd" lang="${cart.book_id}">
					<li class="id">${cart.book_id }</li>
					<li class="name">《${cart.book_name }》</li>
					<li class="price">¥${cart.subtotal/cart.quantity }</li>
					<li class="quantity"><input name="quantity"
						value="${cart.quantity }" lang="${cart.quantity }"></li>
					<li class="total">¥<font name="ft" id="${cart.book_id }">${cart.subtotal }</font></li>
					<li class="operator"><button id="${cart.book_id }">删除</button></li>
				</ul>
			</c:forEach>
		</section>
		<!---->
		<section>
			<header>收货人</header>
			<ul class="caption">
				<li class="check">选择</li>
				<li class="id">编号</li>
				<li class="full_name">姓名</li>
				<li class="tel">电话</li>
				<li class="operator">操作</li>
			</ul>
			<ul class="odd">
				<li class="check" value="1"><input type="radio" name="user"></li>
				<li class="id">1</li>
				<li class="full_name">莉莉</li>
				<li class="tel">1234567</li>
				<li class="operator"><a href="">删除</a></li>
			</ul>
			<ul class="even">
				<li class="check" value="2"><input type="radio" name="user"></li>
				<li class="id">2</li>
				<li class="full_name">皮皮</li>
				<li class="tel">1234567</li>
				<li class="operator"><a href="">删除</a></li>
			</ul>
			<ul class="add">
				<li class="real_name">姓名:<input type="text" name="name"></li>
				<li class="tel">联系号码:<input type="text" name="tel"></li>
				<li class="operator">
					<button>添加</button>
				</li>
			</ul>
		</section>
		<!---->
		<section>
			<header>收货地址</header>
			<ul class="caption">
				<li class="check">选择</li>
				<li class="id">编号</li>
				<li class="province">省/直辖市</li>
				<li class="city">区/市/县</li>
				<li class="street">街道/详细地址</li>
				<li class="operator">操作</li>
			</ul>
			<ul class="odd">
				<li class="check" value="1"><input type="radio" name="user"></li>
				<li class="id">1</li>
				<li class="province">北京</li>
				<li class="city">朝阳</li>
				<li class="street">xxxxxxxxxxxxxxxxxxxxxxxxx</li>
				<li class="operator"><a href="">删除</a></li>
			</ul>
			<ul class="even">
				<li class="check" value="2"><input type="radio" name="user"></li>
				<li class="id">1</li>
				<li class="province">北京</li>
				<li class="city">朝阳</li>
				<li class="street">xxxxxxxxxxxxxxxxxxxxxxxxx</li>
				<li class="operator"><a href="">删除</a></li>
			</ul>
			<ul class="add">
				<li class="province">省/直辖市: <input name="province"
					list="province_list" /> <datalist id="province_list">
						<option value="1xxxxxxxxx">
						<option value="2xxxxxxxxx">
					</datalist>
				</li>
				<li class="city">区/市/县: <input name="city" list="city_list" />
					<datalist id="city_list">
						<option value="1xxxxxxxxx">
						<option value="2xxxxxxxxx">
					</datalist>
				</li>
				<li class="street">街道/详细地址: <input name="street"></li>
				<li class="operator"><button>添加</button></li>
			</ul>
		</section>
		<footer>
			<button>提交订单</button>
		</footer>
	</article>
</body>
</html>