<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>成功找回密码页面</title>
<style type="text/css">
body{
	background:url(images/sucfindpwd_bac.jpg) fixed no-repeat;
	background-size:100%;
	}
#outdiv{
	width:450px;	
	margin-top:185px;
	margin-left:485px;
	border-radius:15px;
	box-shadow:0px 0px  10px 5px #aaa;
	text-align:center;
	font-size:22px;
	font-family:Georgia, serif;
	font-weight:bold;
	background-color:#AFEEEE;
	
}
input{
	font-size:20px;
	font-family:Georgia, serif;
	font-weight:bold;
	}
#fdst{
	border:none;
	
	}

#lgd{
	font-family:'Arial';
	text-align:center;
	color:red;
}
#account{
	margin:40px 5px 20px 5px;
	font-family:Georgia, serif;
}
#password{margin:20px 5px;}
#findemail{margin:20px 5px;}
#submit{margin:20px auto;border-radius:10px;color:#BA55D3;}
#submit:hover{
	background-color:#FF00FF;
	color:white;}

</style>
</head>
<body>
<div id="outdiv">
	<fieldset id="fdst">
		<legend id="lgd">恭喜您，密码找回成功！</legend>
		<form action="pages/login_register.jsp">
			您的账号：<input type="text" name="account" id="account" value="<%=request.getParameter("account") %>" readonly="readonly"/><br/>
			您的密码：<input type="text" name="password" id="password" value="<%=request.getParameter("password") %>" readonly="readonly"/><br/>
			您的邮箱：<input type="email" name="findemail" id="findemail" value="<%=request.getParameter("email") %>" readonly="readonly"/><br/>
			<input type="submit" value="点击登录" id="submit"/>
		</form>
	</fieldset>


</div>
</body>
</html>
