<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="./" target="_parent">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码页面</title>

<style type="text/css">
#bd {
	width: 100%;
	height: 900px;
	border: 1px solid red;
	background: url(images/findpwd_bac.jpg) fixed no-repeat;
	background-size: 100%;
}

#outdiv {
	width: 450px;
	height: 350px;
	margin: 200px auto;
	border-radius: 10px;
	text-align: center;
	font-size: 22px;
	font-family:Georgia, serif;
	font-weight: bold;
}

#fdst {
	border-radius: 5px;
	background: aqua;
}

#lgd {
	font-family: 'Arial';
	text-align: center;
	color: red;
}

#findname {
	font-size: 22px;
	margin: 40px 5px 20px 5px;
	height: 22px;
}

#findemail {
	font-size: 22px;
	margin: 20px 5px;
	height: 22px;
}

#showfd{
	color:red;
	font-family:Georgia, serif;
	}
#btn{
	margin: 20px auto;
	font-size: 18px;
	font-weight: bold;
	border-radius: 5px;
	color:aqua;
}
#btn:hover{
	margin: 20px auto;
	font-size: 18px;
	font-weight: bold;
	border-radius: 5px;
	font-family:Georgia, serif;
	color:red;
}
</style>
<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
<script type="text/javascript">
	$(function() {

		$("#btn").click(function() {
			var account = $("#findname").val();
			var email = $("#findemail").val();
			alert(1);
			$.ajax({
				url : 'login',
				type : 'post',
				data : {
					'findname' : account,
					'findemail' : email
				},
				datatype : 'json',
				success : function(data) {
					alert(data);
					//将json数组转换成对象数组
					var arr = JSON.parse(data);
					if(arr[0]==1){
						$("#showfd").text("账号或密码错误！");
					}
					else{
						window.location.href = "http://localhost:8080/BookStore_V1.1-1/sucfindpwd.jsp?account="+arr[0].ACCOUNT+"&password="+
								arr[0].PASSWORD+"&email="+arr[0].EMAIL;
					}
					
				}
			});

		});
		//当findname标签获得焦点时，showfd标签的内容设置为空
		$("#findname").focus(function() {
			if ($("#showfd").text() != "" || $("#showfd").text() != null) {
				$("#showfd").text("");
			}

		});

	});
</script>

</head>
<body>
	<div id="bd">
		<div id="outdiv">
			<fieldset id="fdst">
				<legend id="lgd">找回密码</legend>
				<form action="login" method="post">
					<p>
						用户名：<input type="text" name="findname" id="findname" /><br />
					</p>
					<p>
						邮　箱：<input type="email" name="findemail" id="findemail" />
					</p>
					<p>
						<font id="showfd" ></font>
					</p>
					<!-- <button id="btn" name="op" value="找回密码">找回密码</button> -->
					<input type="button" id="btn" value="点击找回"/>
				</form>
			</fieldset>
		</div>

	</div>
</body>
</html>
