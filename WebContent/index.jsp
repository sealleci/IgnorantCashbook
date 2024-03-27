<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家庭记账本主页</title>
<!-- 采用绝对路径导入css文件 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- 采用绝对路径导入jquery文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    $(function () {
        //验证非空，并提交查询请求
    	$("#submit").click(
  			function() {
 				//验证输入框是否为空
 				var keyword = $("#input-grid").val();
 				if (keyword == "") {
 					alert("请输入关键字");
 					return false;
 				} else {
 					//javascript语言提供了一个location地址栏对象
 					//它有一个属性href,可以获取浏览器中地址栏地址
 					location.href = "${pageContext.request.contextPath }/manager/homeCostServlet?action=query&keyword="+ keyword+"&currentPage=1&pageSize=6";
 				}
 		});
    });
    function showList() {
	  document.getElementById("myDropdown").classList.toggle("show");
	}
</script>
<%
    request.setCharacterEncoding("utf-8");
	if(session.getAttribute("username")==null){
		session.setAttribute("username","undefined");
	}
	System.out.println("cur page:index");
	System.out.println("-cur user:"+session.getAttribute("username")+","+session.getAttribute("is_adm"));
    String message=(String)request.getAttribute("message");
    //System.out.println(message);
    if((message!=null&&message.equals("noerror")&&(String)session.getAttribute("username")!="undefined")||(String)session.getAttribute("username")!="undefined"){
 		System.out.println("-user session exist");
%>
   <script type="text/javascript">
	   $(function () {
		  var splogin = document.getElementById("login");
		   splogin.style.display="none";
		   var spreg=document.getElementById("register");
		   spreg.style.display="none";
		   /*
			   var splogin = document.getElementById("login");
			   splogin.style.display="none";
			   var spreg=document.getElementById("register");
			   spreg.style.display="none";
			   var rlist=document.getElementById("rlist");
		       var usernode=document.createElement("span");
		       var logoutnode=document.createElement("button");
		       var beforenode=document.getElementById("input-grid");
		       usernode.innerText= "<%=session.getAttribute("username")%>";
		       usernode.setAttribute('style','font-size: 25px;');
		       logoutnode.innerText= "登出";
		       logoutnode.setAttribute('onclick',
		    		   'window.location.replace("${pageContext.request.contextPath }/logout.jsp");');
		       rlist.insertBefore(usernode,beforenode);
		       rlist.insertBefore(logoutnode,beforenode);
	       */
	   });
   </script>
<%
 	}else{
 		System.out.println("-user session not exist");
 	}
%>
</head>
<body>
	<div id="header">
		<span class="wel_word">家庭记账本</span>
		<div id="rlist">		
			<span>
				<a href="${pageContext.request.contextPath }/manager/homeCostServlet?action=list&currentPage=1&pageSize=6">往期消费记录</a>
			</span>
			<span>
				<a href="${pageContext.request.contextPath }/cost_edit.jsp">新增消费记录</a>
			</span>
			<span id="login">
				<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
			</span>
			<span id="register">
				<a href="${pageContext.request.contextPath }/register.jsp">注册</a>
			</span>
			<c:set var="curname" scope="session" value="<%=session.getAttribute(\"username\")%>"/>
			<c:if test="${curname!='undefined'}">
			<span class="dropdown" id="dropdown">
				<button onclick="showList()" class="dropbtn">
			    	<%=session.getAttribute("username")%><i class="fa fa-caret-down"></i>
			  	</button>
			  	<span id="myDropdown" class="dropdown-content">
			  		<c:set var="adm" scope="session" value="<%=session.getAttribute(\"is_adm\")%>"/>
			  		<c:if test="${adm}"><a href="${pageContext.request.contextPath }/userServlet?action=list&currentPage=1&pageSize=6">用户管理</a></c:if>
			    	<a href="${pageContext.request.contextPath}/change_pwd.jsp">修改密码</a>
			    	<a href="${pageContext.request.contextPath}/logout.jsp">登出</a>
			  	</span>
			</span>
			</c:if>
			<input id="input-grid" style="margin-left: 20px" id="keyword" name="keyword" type="text" placeholder="请输入关键字" value="" />
			<input id="submit" type="submit" value="查询" />
		</div>
	</div>
	<div id="main">
		<h1>欢迎进入家庭记账本系统</h1>
	</div>
</body>

</html>