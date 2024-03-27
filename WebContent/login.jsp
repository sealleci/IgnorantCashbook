<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	//点击刷新验证码图片
	$(function(){
	    $('#kaptchaImage').click(function () { $(this).attr('src', '${pageContext.request.contextPath }/kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); });
	}); 
</script>
<%
	request.setCharacterEncoding("utf-8");
	String message=(String)request.getAttribute("message");
	if(message!=null){
		if(message.equals("error")){
%>
	<script type="text/javascript">
		alert("用户名或密码错误");
		$(function () {
			$('#username').val("<%=(String)request.getAttribute("username")%>");	
		});
	</script>
<%
		}else if(message.equals("noerror")){
			//System.out.println("vivi");
%>
	<script type="text/javascript">
		alert("注册成功");
		$(function () {
			$('#username').val("<%=(String)request.getAttribute("username")%>");
			$('#userpwd').val("<%=(String)request.getAttribute("userpwd")%>");	
		});
	</script>
<%
		}else if(message.equals("captwrong")){
%>
	<script type="text/javascript">
		alert("验证码错误");
		$(function () {
			$('#username').val("<%=(String)request.getAttribute("username")%>");
			$('#userpwd').val("<%=(String)request.getAttribute("userpwd")%>");	
		});
	</script>
<%
		}else if(message.equals("change_suc")){
%>
	<script type="text/javascript">
		alert("修改成功");
		$(function () {
			$('#username').val("<%=(String)request.getAttribute("username")%>");
			$('#userpwd').val("<%=(String)request.getAttribute("userpwd")%>");	
		});
	</script>
<%
		}else{}
	}
%>
</head>
<body>
    <div class="outer-wrap">
	    <div id="header">
			<span class="wel_word">家庭记账本</span>
			<div id="rlist">
				<span>
					<a href="${pageContext.request.contextPath }/index.jsp">返回</a>
				</span>
			</div>
		</div>
	    <div class="login-panel">
		    <div style="font-size:40px;text-align:center">登陆界面</div>
		    <form action="${pageContext.request.contextPath }/LoginServlet" method="post" >
		    	<div style="text-align:center">
			      	用户名
			    	<input id="username" type="text" style="width:300px;height:30px;text-align:center" name="uname" />
			    	<br/>
		    	</div>
		     	<div style="text-align:center">
			    	密&nbsp;&nbsp;&nbsp;码
			        <input id="userpwd" style="width:300px;height:30px;text-align:center" type="password" name="upwd"/>
			        <br/>
			    </div>
			    <div style="text-align:center">
			    	验证码
			        <input id="the-captcha" style="width:300px;height:30px;text-align:center" type="text" name="captcha"/>
			        <div>
			       		<img alt="验证码" src="${pageContext.request.contextPath }/kaptcha.jpg" id = "kaptchaImage"/>
			    	</div>
			    </div>
		   		<div style="text-align:center">
		   			<input type="submit" value="登录"/>
		   			<br/>
		   		</div>
		      	<div style="text-align:center">
		      		<a href="${pageContext.request.contextPath }/register.jsp" >没有账号，立即去注册</a>
		      	</div>
		   	</form>
	    </div>
    </div>	
</body>
</html>