<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="projectshop.model.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <form action="updateProductServlet?productid=${pro.ID}" method="POST" id="productForm" enctype="multipart/form-data">
   <img src="picture/${pro.pic}" id = "head" width="100px" height="100px">
   <br>
      商品预览：<input type="file" name="pic" id="pic" ><br/>
      商品名称：<input style="text" name="proName" id="proName" value="${pro.name}"><label id="errProName"></label><br/>
      商品单价：<input style="text" name="price" id="price" value="${pro.price}"><label id="errPrice"></label><br/>
      商品库存：<input style="text" name="total" id="total" value="${pro.total}"><label id="errTotal"></label><br/>
    <input type="button" value="修改" onclick="finish()">
    <input type="reset"><br>
   </form>
<script type="text/javascript" src="photo.js"></script>
    <%if (request.getAttribute("info") != null) {
        out.print(request.getAttribute("info"));
    }%>
    <a href="ManageProductServlet">返回上一层</a>
</body>
<script type="text/javascript" src="register.js"></script>
</html>