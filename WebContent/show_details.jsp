<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sd" uri="./WEB-INF/custom.tld"%>
<%@ page import="com.rat.pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>消费记录管理</title>
<!-- 采用绝对路径导入css文件 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- 采用绝对路径导入jquery文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    
    $(function () {    	
        //提示用户添加失败，删除失败，修改失败
        if(!${empty requestScope.msg}){
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
 					location.href = "${pageContext.request.contextPath }/manager/homeCostServlet?action=query&keyword="+ keyword+"&currentPage=1&pageSize=6";
 				}
 		});
        
        $("a.deleteClass").click(function () {
            return confirm("你确定要删除【"+ $("#cost_name").text()+"】?");
        });
        
        var len = 15;      //默认显示字数
	    $(".remark").each(function(){
			var content = $(this).html();                   //获取div里的内容
		    var span = document.createElement("span");     //创建<span>元素
		    var a = document.createElement("a");           //创建<a>元素
		    a.style.color="#A8A8A8";
		    span.innerHTML = content.substring(0,len);     //span里的内容为content的前len个字符
		
		    a.innerHTML = content.length>len?"...展开":"";  ////判断显示的字数是否大于默认显示的字数    来设置a的显示        
		    a.href = "javascript:void(0)";//让a链接点击不跳转
		
		    a.onclick = function(){
		        if(a.innerHTML.indexOf("展开")>0){      //如果a中含有"展开"则显示"收起"
		          a.innerHTML = "<收起";
		          span.innerHTML = content;
		        }else{
		            a.innerHTML = "...展开";
		            span.innerHTML = content.substring(0,len);
		        }
		    }
		    // 设置div内容为空，span元素 a元素加入到div中
		    $(this).html("");
		    $(this).append(span);
		    $(this).append(a);
		    
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
	System.out.println("cur page:manage");
	System.out.println("-cur user:"+session.getAttribute("username"));
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
		<span class="wel_word">消费记录详情页面</span>
		<div id="rlist">
			<span>
				<a href="${pageContext.request.contextPath }/manager/homeCostServlet?action=list&currentPage=1&pageSize=6">返回历史纪录</a>
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
		<table>
			<!-- 使用el表达式注意在jsp页面(如本页面第一行)导入相应的包 -->
			<tr>
				<td></td>
				<td style="width: 500px" colspan="2"><div style="display:none;">${requestScope.homeCost.id}</div></td>
			</tr>
			<tr>
				<td>用户名称</td>
				<td colspan="2"><%=session.getAttribute("username")%></td>
			</tr>
			<tr>
				<td>消费名称</td>
				<td id="cost_name" colspan="2">${requestScope.homeCost.name}</td>
			</tr>
			<tr>
				<td>消费金额</td>
				<td colspan="2"><fmt:formatNumber value="${requestScope.homeCost.money}" type="currency"/></td>
			</tr>
			<tr>
				<td>登记日期</td>
				<td colspan="2"><sd:sbw>${requestScope.homeCost.date}</sd:sbw></td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan="2">
					<div class="remark" >${requestScope.homeCost.remark}</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><a href="${pageContext.request.contextPath }/manager/homeCostServlet?action=getHomeCostById&id=${requestScope.homeCost.id}">
				修改
				</a></td>
				<td><a class="deleteClass" href="${pageContext.request.contextPath }/manager/homeCostServlet?action=delete&id=${requestScope.homeCost.id}">
				删除
				</a></td>
			</tr>
		</table>
	</div>
</body>

</html>