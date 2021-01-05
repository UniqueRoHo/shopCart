<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="projectshop.model.Product" %>
<%@ page import="projectshop.model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的购物车</title>
</head>
<style>a {text-decoration:none;}</style>
<body>
    <%Cart cart = (Cart)request.getAttribute("myCart"); 
     List<Product> list = cart.getList();%>
      <table border=1>
        <tr>
          <td>商品预览</td>
          <td>商品名称</td>
          <td>商品价格</td>
          <td>当前数量</td>
          <td>删除商品</td>
        </tr>
        <%if (list != null) {   
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                pageContext.setAttribute("pro", iter.next());%>
                 <tr>
                     <form action="updateCartServlet?productid=${pro.ID}&userid=${sessionScope.userID}" method="POST" name="updateCart" id="updateCart">
                     <td><img src="picture/${pro.pic}" id = "head" width="100px" height="100px"></td>
                     <td>${pro.name}</td>
                     <td>${pro.price}</td>
                     <td><a href="updateCartServlet?productid=${pro.ID}&userid=${sessionScope.userID}&total=${pro.total - 1}"> - </a>
                         <input type="text" size="1" name="total" id="total" value="${pro.total}" readonly>
                         <a href="updateCartServlet?productid=${pro.ID}&userid=${sessionScope.userID}&total=${pro.total + 1}"> + </a>
                     </td>
                     <td><input type="submit" value="删除该商品"></td>
                     </form>
                </tr>
          <% }%>
        <% } %>
       </table>
       <a href="menu.jsp">返回上一层</a>
        </div>
</body>
</html>