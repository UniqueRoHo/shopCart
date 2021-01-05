<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="projectshop.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<!DOCTYPE html>

<html>
  <head>
    <meta charset="UTF-8">
    <title>购物</title>
  </head>
  <style>
   #wrapper {width:100%;height:400px;}
   #menu {margin:0 auto;width:300px;height:150px;text-align:left}
   a {text-decoration:none;}
   #out{float:right;width:100px}
   #menu li {margin-top:20px;list-style: none;font-size:20px;}
  
  </style>
  <body>
    <% if (session.getAttribute("userID") != null) {
        String name = session.getAttribute("userID").toString(); 
        String role = (String)session.getAttribute("userRole");
        if (session.getAttribute("currentPage") != null){
            session.removeAttribute("currentPage");
        }
         if (session.getAttribute("pageSize") != null){
            session.removeAttribute("pageSize");
        }%>
    <div id="wrapper">
      <div id="success">欢迎${sessionScope.userRole}进入购物系统! <br></div>
      <div id="out"><a href="LoginServlet">点击注销</a></div>
      <div id="menu">
        <h1>&nbsp;&nbsp;&nbsp;购物系统</h1>
        <ul>
            <c:if test="${userRole == 'general' }">
            <li><a href="ManageProductServlet">查看商品</a></li>
            <li><a href="MyShoppingCartServlet">我的购物车</a></li>
            <li><a href="upload.jsp">上传附件</a></li>
            </c:if>
            <c:if test="${userRole == 'admin' }">
            <li><a href="ManageProductServlet">管理商品</a></li>
            <li><a href="Online.jsp">查看在线人员</a></li>
            </c:if>
        </ul>
      </div>
    </div>

    <% } else {  %>
    <h1>非法用户， 请<a href="login.jsp">登陆</a></h1>
    <%} %>
  </body>
</html>