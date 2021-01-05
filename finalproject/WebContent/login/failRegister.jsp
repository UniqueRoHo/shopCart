<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
 <%
  session.setAttribute("info","用户已存在");
  response.setHeader("refresh","2;URL=login.jsp"); 
  
  //session.invalidate();
  %>
  <h3>注册失败，${info}，两秒后跳转回注册页页</h3>
  <h3>如果没有跳转，请按<a href="register.jsp">这里</a>退出</h3>
</body>
</html>