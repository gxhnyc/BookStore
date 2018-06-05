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
	$(function(){
		alert(123);
		/* 页面加载时，加载购物车内容 */
		$.ajax({
			url : "loaddetailcart",
			type : "post",
			data : {
				"_bid" : ""
			},
			success : function(data) {
				/* //将json数组转换成对象数组 */
				var arr = JSON.parse(data);
				/* alert(arr[0].book_name); */
				/* alert(arr[0].book_id); */

				var ul = $(".caption");
				/* ul.text(""); */
				var total = 0;
				for (var i = 0; i < arr.length; i++) {
					var uldata = "<ul id='itemul'>"
					+"<li class='id'>" + "<font id='bid'>"+arr[i].book_id +"</font>"
					+ "</li>"
					+"<li class='name'>"
					+ arr[i].book_name +"</li>"+"<li class='price'>¥"
					+ arr[i].subtotal/arr[i].quantity
					+ "</li>"+"<li class='quantity'>"+"<input name='quantity' value='"+arr[i].quantity+"'></li>"
					+ "<li class='total'>¥"+"<font name='subtotal'>"+ arr[i].subtotal+"</font>"+"</li>"
					+ "<li class='operator'>"+"<a href=''>删除</a>"+"</li>"
					+"</ul>";
		          			
					total += arr[i].subtotal;
					ul.append(uldata);
				}
				$("#showtotal").text("合计:¥" + total);
			}
		});
		
	/* 购物项数量标签（input）失去焦点后，更改数据库对应表的数量 */
	$("body").on('blur', 'input[name=quantity]', function(){
		console.log("进入modquantity");
		var num=$("input[name=quantity]").val();
		var bid=$("#bid").text();
		$.ajax({
			url:'modquantity',
			type:'post',
			data:{"num":num,"bid":bid},
			success:function(data){
				/* 数组转换成对象数组 */
				var arr = JSON.parse(data);
				alert(arr+"modquantity");
				$("font[name=subtotal]").text(arr);
			}
			
		});
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
       <!--   <ul id="odd">
         </ul> -->
          
          <!--  <ul class="even">
               <li class="id">2</li>
               <li class="name">Android</li>
               <li class="price">¥90.50</li>
               <li class="quantity"><input name="quantity" value="2"></li>
               <li class="total">¥181.00</li>
               <li class="operator"><a href="">删除</a></li>
             </ul> -->
             <footer id="showtotal"></footer>
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
            <li class="check" value="1"><input type="radio" name="user" ></li>
            <li class="id">1</li>
            <li class="full_name">莉莉</li>
            <li class="tel">1234567</li>
            <li class="operator"><a href="">删除</a></li>
          </ul>
          <ul class="even">
              <li class="check" value="2"><input type="radio" name="user" ></li>
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
           <li class="check" value="1"><input type="radio" name="user" ></li>
           <li class="id">1</li>
           <li class="province">北京</li>
           <li class="city">朝阳</li>
           <li class="street">xxxxxxxxxxxxxxxxxxxxxxxxx</li>
           <li class="operator"><a href="">删除</a></li>
         </ul>
         <ul class="even">
             <li class="check" value="2"><input type="radio" name="user" ></li>
             <li class="id">1</li>
             <li class="province">北京</li>
             <li class="city">朝阳</li>
             <li class="street">xxxxxxxxxxxxxxxxxxxxxxxxx</li>
             <li class="operator"><a href="">删除</a></li>
           </ul>
           <ul class="add">
               <li class="province">省/直辖市: 
                   <input name="province" list="province_list" />
                   <datalist id="province_list">
                       <option value="1xxxxxxxxx">
                       <option value="2xxxxxxxxx">
                   </datalist>
               </li>
               <li class="city">区/市/县: 
               <input name="city" list="city_list" />
               <datalist id="city_list">
                   <option value="1xxxxxxxxx">
                   <option value="2xxxxxxxxx">
               </datalist>
               </li>
               <li class="street">街道/详细地址: <input name="street"></li>
               <li class="operator"><button>添加</button></li>
            </ul>
 </section>
 <footer><button>提交订单</button></footer>
</article>
</body>
</html>