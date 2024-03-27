<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登出</title>
</head>
<body>
	<%
	System.out.println("logout success:"+request.getSession().getAttribute("username"));
	session.removeAttribute("username");
	session.invalidate();
	response.sendRedirect("index.jsp");
	%>
</body>
</html>