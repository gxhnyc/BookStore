<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <base href="../pages" target="_parent">
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>小狐仙在线书店V1.1-用户注册、登录</title>
    <style type="text/css" media="screen">
        @import url('css/common.css');
        @import url('css/login_register.css');
    </style>
    <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    <script type="text/javascript">
    $(function(){
    	
    		$("#username").focus(function(){ 
    			$("#f1").text("");
    			if( $("#username").val() == "请输入用户名..."){    	      		
    				$("#username").css("color","#000000");
    	          	$("#username").val("");
    	       		}
    		});	
    		
    		//----用户名验证
    		$("#username").blur(function(){
    			
    			var data=$("#username").val();
    			$.ajax({
        				url:"validate",
        				type:"post",
        				data:{"name":data},
        				success:function(data){
        					if(data==0){
        						$("#f1").css("color","red").text("用户名已存在!");
        					}else{
        						$("#f1").css("color","yellow").text("用户名可用!");
        						
        					}
        				}
    			}
        		);    			
    		});
    		//--密码验证,两次输入密码是否一致
    		$("#password2").blur(function(){
    			var data1=$("#password").val();
    			var data2=$("#password2").val();
    			if(data1 != data2){
    				$("#f2").text("密码不一致!");
    				$("#password").val("")
    				$("#password2").val("")
    			}else{
    				$("#f2").text("");
    			}    			
    		});
    		
    		//----邮箱验证
    		$("#email").blur(function(){
    			var data=$("#email").val();
    			var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;      
    			var num=reg.test(data);
    			if(num>0){//num用来验证是否有匹配的数据,若没有,则返回-1,若有,则返回正数
    				$("#f3").text("");    				
    			}else{
    				$("#f3").text("格式不正确!");
    			}    			
    		});
    		
    		//--注册按钮验证(通过判断密码验证是否正确来确认是否可以提交注册))
    		$("#btn").click(function(){
    			var txt = $("#f2").text();
    			if(txt == null || txt ==""){
    				$("#register").submit();
    			}
    		});
    		//用户登录,点击“登录”按钮
    		$("#logbtn").click(function(){    			
    				$("#login").submit();
    		});
    		//当logname标签获得焦点时，logfont标签的内容设置为空
    		$("#logname").focus(function(){     			
    			if($("#logfont").text()!=""||$("#logfont").text()!=null){
    			$("#logfont").text("");
    			}
    	       		
    		});
    		//找回密码，点击“找回密码”按钮
    		$("#fdpwd").click(function(){
    			window.location.href = "http://localhost:8080/BookStore_V1.1-1/findpwd.jsp";
    		});
    		

    	});
    </script>
</head>
<body>
    <header id="p_header">
        小狐仙在线书店
        <p style="border-radius:5px;font-size:20px;">
			<marquee id="affiche" align="middle" behavior="alternate"  direction="right" 
			height="20" width="900" hspace="50" vspace="20" loop="-1" scrollamount="10" 
			scrolldelay="100" onMouseOut="this.start()" onMouseOver="this.stop()" 
			style="color:pink; font-size: 14; font-family: '楷体'">小狐仙书城欢迎您！</marquee> 
		</p>
    </header>
    <embed src="sound/高山流水.mp3" autostart="true" loop="true" hidden="true" />
    <article class="register">
        <form action="register" id="register" method="post">
            <header>用户注册</header>
            <p><label>用户名:</label><input name="username" id="username" value="请输入用户名..."
             style="color:#999"/><font id="f1" style="color:red"></font></p>
            <p><label>密　码:</label><input name="password" type="password" id="password"/></p>
            <p><label>验　证:</label><input name="password2" type="password" id="password2"/><font id="f2"  style="color:red"></font></p>
            <p><label>邮　箱:</label><input name="email" id="email"/><font id="f3"  style="color:red"></font></p>
            <p id="bp"><button type="button" id="btn">注　册</button></p>
        </form>
    </article>
    
    
    
    <article class="login">
        <form action="login" method="post" id="login">
             <header>用户登录</header>
             <p><label>用户名:</label><input name="logname" id="logname"/></p>
             <p><label>密　码:</label><input name="logpwd"  type="password"/></p>
             <p id="bp2"><button type="button" id="fdpwd" name="findpwd">找回密码</button><button type="submit" id="logbtn" name="op" value="登录">登　录</button></p>             
             <c:if test="${logflag<=0 }">
             	<font id="logfont" style="color:red">　　　　　　　用户名不存在,请重新登录!</font>
             </c:if>
             
        </form>
    </article>
 
    <footer id="p_footer">
        <div></div>
        @Copyright <strong>小狐仙科技有限公司</strong>
    </footer>
</body>
</html>
