<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑消费记录</title>
<!-- 采用绝对路径导入css文件 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- 采用绝对路径导入jquery文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
		function checknum(){
		    var nMax = 200;
		    var len =$("#text-in").val().length;    
		    if(len>nMax){
		    	$("#text-in").val($("#text-in").val().substring(0,nMax));
		        return;
		    }
		    $("#prompt").val("你还可以输入"+(nMax-len)+"个字");
		}
		
        $(function () {
            //提示用户添加失败，删除失败，修改失败
            if(!${ empty requestScope.msg }){
                alert("${requestScope.msg}");
            }
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
 					location.href = "${pageContext.request.contextPath }/userServlet?action=query&keyword="+ keyword+"&currentPage=1&pageSize=6";
 				}
 			});
            //验证输入框非空
            $("#tablesubmit").click(function(){
                //验证输入框是否为空
                var name = $("#uname").val();
                var pwd = $("#upwd").val();
                if(name == null || name ==""){
                    alert("消费名称不能为空");
                    $("#uname").focus();
                    return false;
                }
                if(pwd == null || pwd ==""){
                    alert("消费金额不能为空");
                    $("#upwd").focus();
                    return false;
                }
            });
            
            $("#text-in").val("${requestScope.homeCost.remark}");
            checknum();
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
	System.out.println("cur page:cost edit");
	System.out.println("-cur user:"+session.getAttribute("username"));
	
    String message=(String)request.getAttribute("message");

    if((message!=null&&message.equals("noerror")&&(String)session.getAttribute("username")!="undefined")||(String)session.getAttribute("username")!="undefined"){
 		System.out.println("-user session exist");
%>
   <script type="text/javascript">
   	$(function () {
		  var splogin = document.getElementById("login");
		   splogin.style.display="none";
		   var spreg=document.getElementById("register");
		   spreg.style.display="none";
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
		<span class="wel_word">编辑用户记录</span>
		<div id="rlist">
			<span>
				<a href="${pageContext.request.contextPath }/userServlet?action=list&currentPage=1&pageSize=6">返回</a>
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
			  		<c:if test="${adm}"><a href="#">用户管理</a></c:if>
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
		<form
			action="${pageContext.request.contextPath }/userServlet"
			method="get">
			<!-- hidden隐藏域,判断是添加请求还是修改请求：param.id取id的值,id为空是添加；id非空是修改 -->
			<input type="hidden" id="action" name="action"
				value="update" />
			<table>
				<tr>
					<td></td>
					<td>用户名称</td>
					<td>用户密码</td>
					<td>用户性别</td>
					<td></td>
					<td colspan="2">操作</td>
				</tr>
				<tr>
					<!-- 使用el表达式注意在jsp页面(如本页面第一行)导入相应的包 -->
					<td><input type="hidden" id="uid" name="uid"
						value="${requestScope.userinfo.uid}"></td>					
					<td><input id="uname" name="uname" type="text"
						placeholder="请输入名称" value="${requestScope.userinfo.uname}" /></td>
					<td><input id="upwd" name="upwd" type="text"
						placeholder="请输入密码" value="${requestScope.userinfo.upwd}" /></td>
					<td><input id="usex" name="usex" type="text"
						placeholder="请输入性别" value="${requestScope.userinfo.usex}" /></td>
					<td><input type="hidden" id="is_adm" name="is_adm"
						value="${requestScope.userinfo.is_adm}"></td>
					<td><input id="tablesubmit" type="submit" value="提交" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>