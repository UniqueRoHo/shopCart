<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  </head>
  <body>
   <h1>当前在线人员</h1>
   <%Date nowDate = new Date();
    response.setHeader("refresh","2"); %>
   
    <%Map<Object, Date> all = (Map<Object, Date>)this.getServletContext().getAttribute("onlineTime"); 
     for(Map.Entry<Object, Date> entry : all.entrySet()) { %>
     <%=entry.getKey()%>-----------<span>登陆时间：</span><%=entry.getValue()%>--------<span>在线时长：</span><%=(nowDate.getTime() - entry.getValue().getTime())/1000%>秒<br>
   <%}%>
     <hr>
     <a href = "menu.jsp">返回上一层</a>
  </body>
</html>