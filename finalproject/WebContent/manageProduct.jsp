<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="projectshop.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="projectshop.model.Pagination" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>管理商品</title>
  </head>
  <body>
  <%List<Product> list = (List<Product>)request.getAttribute("listPro"); 
    Pagination pagination  = (Pagination)request.getAttribute("pagination");
    %>
    <div style="margin:0 auto;text-align:center;width:465px;border:1px solid">
      <a href="addProduct.jsp">增加商品</a><br>
         <%if (request.getAttribute("info") != null) {
        out.print(request.getAttribute("info"));
        }%>
      <table border=1>
        <tr>
          <td>商品预览</td>
          <td>商品名称</td>
          <td>商品价格</td>
          <td>商品库存</td>
          <td>商品点击量</td>
          <td>删除商品</td>
          <td>修改商品</td>
        </tr>
        <%if (list != null) {   
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                pageContext.setAttribute("pro", iter.next());%>
                 <tr>
			         <td><img src="picture/${pro.pic}" id = "head" width="100px" height="100px"></td>
			         <td>${pro.name}</td>
			         <td>${pro.price}</td>
			         <td>${pro.total}</td>
			         <td>${pro.click}</td>
			         <td><a href="updateProductServlet?productid=${pro.ID}">修改商品</a></td>
			         <td><a href="deleteProductServlet?productid=${pro.ID}">删除商品</a></td>
                </tr>
          <% }%>
        <% }%>
       </table>
       <form action="ManageProductServlet?" name="setPageSize" id="setPageSize">
                                 每页显示<input type="text" value="${pageSize}" size="1" name="pageSize" id="pageSize">条
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