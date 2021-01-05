<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="projectshop.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="projectshop.model.Pagination" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商城</title>
</head>
<script type="text/javascript" src="photo.js"></script>
<body>
       <%
      List<Product> list = (List<Product>)request.getAttribute("listPro");
      Pagination pagination  = (Pagination)request.getAttribute("pagination");
    %>
    <div style="margin:0 auto;text-align:center;width:600px;border:1px solid">
               <%
           String successFlashMessage = (String)request.getAttribute("info");
           successFlashMessage = successFlashMessage == null ? "" : successFlashMessage;
           String isDisplayFlashMessage = "";
           if (successFlashMessage.equals("")) {
               isDisplayFlashMessage = "style='display:none'";
           }
       %>
       <div id="successFlashMessage" <%=isDisplayFlashMessage%>>
         <%
           out.write(successFlashMessage);
           if (!successFlashMessage.equals("")) {
               %>
               <script>
                 setTimeout(function()  {
                     document.getElementById("successFlashMessage").style.display = "none";
                 }, 2000)
               </script>
               <% 
           }
          %>
       </div>
      <table border=1 style="margin: 0 auto">
        <tr>
          <td>商品预览</td>
          <td>商品名称</td>
          <td>商品价格</td>
          <td>添加数量</td>
          <td>添加至购物车</td>
        </tr>
        <%if (list != null) {   
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                pageContext.setAttribute("pro", iter.next());%>
                 <tr>
                     <form action="addToCartServlet?productid=${pro.ID}&userid=${sessionScope.userID}" method="POST" name="addCart" id="addCart">
                     <td><img src="picture/${pro.pic}" id = "head" width="100px" height="100px"></td>
                     <td>${pro.name}</td>
                     <td>${pro.price}</td>
                     <td><input type="text" size="2" name="total" id="total" value="1"></td>
                     <td><input type="submit" value="添加至购物车"></td>
                     </form>
                </tr>
          <% }%>
        <% }%>
       </table>
       <form action="ManageProductServlet" name="setPageSize" id="setPageSize">
                                 每页显示<input type="text" value="${sessionScope.pageSize}" size="1" name="pageSize" id="pageSize">条
            <input type="submit" value="保存">
       </form><br>
       <a href = "ManageProductServlet?currentPage=1&pageSize=${pageSize}" >首页</a>
        <%if (pagination.getCurrentPage() == 1) {%>
                    上一页
        <%} else {%>
          <a href = "ManageProductServlet?currentPage=${pagination.currentPage - 1}&pageSize=${pageSize}" >上一页</a>
        <% }%>
         <%if (pagination.getCurrentPage() == pagination.getPageCount()) {%>
                  下一页
        <%} else {%>
        <a href = "ManageProductServlet?currentPage=${pagination.currentPage + 1}&pageSize=${pageSize}" >下一页</a>
        <% }%>
        <a href = "ManageProductServlet?currentPage=${pagination.pageCount}&pageSize=${pageSize}" >尾页</a>
                第${pagination.currentPage}页/共${pagination.pageCount}页
       <a href="menu.jsp">返回上一层</a>
        </div>
  </body>
</html>