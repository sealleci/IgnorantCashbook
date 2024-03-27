<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>
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
	if(session.getAttribute("username")==null){
		session.setAttribute("username","undefined");
	}
	System.out.println("cur page:change_pwd");
	System.out.println("-cur user:"+session.getAttribute("username"));
	String message=(String)request.getAttribute("message");
	if(message!=null){
		if(message.equals("error")){
%>
	<script type="text/javascript">
		alert("旧密码错误");
	</script>
<%
		}else if(message.equals("noerror")){
			//System.out.println("vivi");
%>
	<script type="text/javascript">
		alert("修改成功");
	</script>
<%
		}else if(message.equals("captwrong")){
%>
	<script type="text/javascript">
		alert("验证码错误");
	</script>
<%
		}else if(message.equals("fail")){
%>
	<script type="text/javascript">
		alert("修改失败");
	</script>
<%
		}else {}
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
				<span style="margin-right:100px;font-size:25px">
			      	[ <%=session.getAttribute("username")%> ]
		    	</span>
			</div>
		</div>
	    <div class="login-panel">
		    <div style="font-size:40px;text-align:center">修改密码</div>
		    <form action="${pageContext.request.contextPath }/ChangePwdServlet" method="post" >
		    	
		     	<div style="text-align:center">
			    	旧密码
			        <input id="pastpwd" style="width:300px;height:30px;text-align:center" type="password" name="pastpwd"/>
			        <br/>
			    </div>
			    <div style="text-align:center">
			    	新密码
			        <input id="newpwd" style="width:300px;height:30px;text-align:center" type="password" name="newpwd"/>
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
		   			<input type="submit" value="确认修改"/>
		   			<br/>
		   		</div>
		   	</form>
	    </div>
    </div>	
</body>
</html>