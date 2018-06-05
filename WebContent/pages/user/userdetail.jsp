<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- debugger -->
	<base href="../../" target="_parent">
    <meta charset="utf-8">
    <title>小狐仙在线书店</title>
    <style type="text/css" media="screen">
       body{
		background:url(images/sucfindpwd_bac.jpg) fixed no-repeat;
		background-size:100%;
		font-family:Georgia, serif;
		}
		#art{
			width:400px;
			height:400px;
			margin-top:135px;
			margin-left:285px;
			}
		#hd{
			font-size:30px;
			font-weight:bold;
			color:brown;
			text-align:center;
			}
			#sec{
				font-size:20px;
				font-weight:bold;
				
			}
		
    </style>
</head>
<body>	

    <article id="art">
        <header id="hd">我的个人信息</header>
        <section id="sec">
            <p>账号:${userMap.ACCOUNT }</p>
            <p>邮箱:${userMap.EMAIL }</p>
            <p>积分:1000</p>
            <p>活跃度:A</p>
            <p>最近一次访问时间:2014-03-10 11:11:11</p>
       </section>
    </article>
</body>
</html>