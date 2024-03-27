<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.wang.pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询消费记录</title>
<!-- 采用绝对路径导入css文件 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- 采用绝对路径导入jquery文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
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
		//删除提示
		$("a.deleteClass").click(
			function() {
				//在事件fuction函数中有一个this对象，即当前响应事件的dom对象

				/**
				 * confirm是确认提示框函数
				 * 参数是提示内容
				 * 两个按钮：确认和取消
				 * 返回true表示点击确认
				 */
				return confirm("你确定要删除【"
						+ $(this).parent().parent().find("td:first").text()
						+ "】?");
			});
		
		//跳转页数
        $("#goto").click(function () {
            //验证输入框是否为空
            var keyword = <%=request.getParameter("keyword")%>;
            var pageSize = <%=request.getParameter("pageSize")%>;
            var gotoPage = $("#page").val();
            var maxPage=${pageBean.totalPage};
            if(gotoPage ==""||gotoPage<=0||gotoPage>maxPage){
                return false;
            }else {
                location.href="${pageContext.request.contextPath }/userServlet?action=query&keyword="+keyword+"&currentPage="+gotoPage+"&pageSize="+pageSize;
            }
        });
		
      //页数尺寸改变
        $("#pagesize").change(function(){
        	var keyword = <%=request.getParameter("keyword")%>;
            var pageSize = this.options[this.selectedIndex].innerHTML;
        	location.href="${pageContext.request.contextPath }/userServlet?action=query&keyword="+keyword+"&currentPage=1&pageSize="+pageSize;
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
	System.out.println("cur page:user query");
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
		<span class="wel_word">查询用户记录</span>
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
		<c:choose>
		<c:when test="${requestScope.pageBean.getList().size()>0}">
			<table style="margin-top: 30px">
				<tr>
					<td class="costname" style="width: 150px">用户名</td>
					<td>密码</td>
					<td>性别</td>
					<td>是否为管理员</td>
					<td colspan="2">操作</td>
				</tr>
				<!-- 使用el表达式注意在jsp页面(如本页面第一行)导入相应的包 -->
				<c:forEach items="${requestScope.pageBean.getList()}" var="item">
					<tr>
						<td>${item.uname}</td>
						<td>${item.upwd}</td>
						<td>${item.usex}</td>
						<td>${item.is_adm}</td>
						<td><a
							href="${pageContext.request.contextPath }/userServlet?action=getUserById&uid=${item.uid}">修改</a></td>
						<td><a class="deleteClass"
							href="${pageContext.request.contextPath }/userServlet?action=delete&uid=${item.uid}">删除</a></td>				
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6">
					<div style="line-height: 20px; height: 20px;">
						共[<b>${pageBean.totalCount}</b>]条记录，共[<b>${pageBean.totalPage}</b>]页
						，当前第[<b>${pageBean.currentPage}</b>]页 ,每页显示
                    	<select id="pagesize" name="pageSize">
                    		<option value="4" <c:if test="${pageBean.pageSize==4 }">selected="selected"</c:if>>4</option>
                        	<option value="6" <c:if test="${pageBean.pageSize==6 }">selected="selected"</c:if>>6</option>
                        	<option value="12" <c:if test="${pageBean.pageSize==12 }">selected="selected"</c:if>>12</option>
                      	</select> 条
						<c:if test="${pageBean.currentPage!=1}">
		    				[<a href="${pageContext.request.contextPath }/userServlet?action=list&currentPage=${pageBean.currentPage-1}&pageSize=${pageBean.pageSize}">
		    					前一页
							</a>]
						</c:if>
						<c:if test="${pageBean.currentPage!=pageBean.totalPage}">
		    				[<a href="${pageContext.request.contextPath }/userServlet?action=list&currentPage=${pageBean.currentPage+1}&pageSize=${pageBean.pageSize}">
		    					后一页
		    				</a>]
						</c:if>
						到第<input type="text" size="3" id="page" name="page" /> 页 <input id="goto" type="button" value="Go"/>
					</div>
				</td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
				<h1>无相关查询结果</h1>
		</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
