<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
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
	<script type="text/javascript">alert("该用户名已被使用，请重试");</script>
<%
		}else if(message.equals("noerror")){
%>
	<script type="text/javascript">
		alert("注册成功");
	</script>
<%
		}else if(message.equals("captwrong")){
%>
	<script type="text/javascript">
		alert("验证码错误");
		$(function () {
			$('#username').val("<%=(String)request.getAttribute("username")%>");
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
    <div class="register-panel">
	    <div style="font-size:40px;text-align:center">注册界面</div>
	    <form action="${pageContext.request.contextPath }/RegisterServlet" method="post">
		    <div style="text-align:center">
		   		用户名
		   		<input type="text" style="width:300px;height:30px;text-align:center" name="uname"/>
		   		<br/>
		    </div>
		    <div style="text-align:center">
		   		密&nbsp;&nbsp;&nbsp;码
		   		<input type="password" style="width:300px;height:30px;text-align:center" name="upwd"/>
		   		<br/>
		    </div>
		    <div style="text-align:center">
			    <input type="radio" name="usex" value="male"/>男	
			    <input type="radio" name="usex" value="female"/>女
			    <input type="radio" name="usex" value="other" checked="checked"/>其他
		    </div>
		    <div style="text-align:center">
		    	验证码
		        <input id="the-captcha" style="width:300px;height:30px;text-align:center" type="text" name="captcha"/>
		        <div>
		       		<img alt="验证码" src="${pageContext.request.contextPath }/kaptcha.jpg" id = "kaptchaImage"/>
		    	</div>
		    </div>
		    <div style="text-align:center">
		    	<input type="submit"  value="注册"/>
		    	<br/>
		    </div>
		    <div style="text-align:center">
		    	<a href="${pageContext.request.contextPath }/login.jsp" >已有账号，立即去登录</a>
		    </div>
	    </form>
    </div>
</div>
</body>
</html>