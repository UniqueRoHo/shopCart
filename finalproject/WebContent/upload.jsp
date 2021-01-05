<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上传文件</title>
</head>
<style>
input{margin:10px;}
</style>
<body>
    <div style="margin:0 auto;width:200px">
    <% 
       if (request.getAttribute("info") != null) {
         out.print(request.getAttribute("info"));
     }%>
    
    <c:choose>
      <c:when test="${userRole =='general'}">
      <form action="UpLoadServlet" method="POST" name="addEmp" id="addEmp" enctype="multipart/form-data">
              选择文件：<input type="file" name="files" id="files" ><br/>
    <input type="submit" value="上传" >
    <input type="reset">
    </form>
      </c:when>
      <c:otherwise>
        <h1>无权使用该功能</h1>
    </c:otherwise>
    </c:choose>
    <a href="menu.jsp">返回主菜单</a>
    </div>
</body>
</html>