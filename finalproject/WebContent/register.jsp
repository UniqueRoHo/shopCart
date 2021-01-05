<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
</head>
<style>
   input{margin-top:10px;}
   select{margin-top:10px;}
</style>
<body>
<script type="text/javascript"> 
function setRole(){ 
  var id = new Array("general","admin"); 
  var value = new Array("顾客","管理员"); 
  var select = document.getElementById("role"); 
  select.length = 1;
  select.options[0].selected = true;
  for(var x = 0;x<id.length;x++){ 
    var option = document.createElement("option"); 
    option.setAttribute("value",id[x]);
    option.appendChild(document.createTextNode(value[x])); 
    select.appendChild(option);
  } 
} 
window.onload=function(){
  setRole()  
}
</script>
   <script type="text/javascript" src="registerUser.js"></script>
   
     <script>
    var xmlHttp;
    var flag;
    function createXMLHttp(){
        if (window.XMLHttpRequest) {
            xmlHttp =  new XMLHttpRequest();
        } else {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }   
    }
    function checkUserid(email){
    	var email = document.getElementById("email").value 
    	    if(!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email)){
    	    	document.getElementById("msg").innerHTML = "请输入正确的邮箱格式";
    	    } else {
    	    createXMLHttp();
            xmlHttp.open("POST", "CheckServlet?email="+email);
            xmlHttp.onreadystatechange = checkUseridCallBack;
            xmlHttp.send(null);
            document.getElementById("msg").innerHTML = "正在验证...";
    	    }
           
      }
    function checkUseridCallBack() {
            if (xmlHttp.readyState == 4){
                if (xmlHttp.status == 200) {
                    var text = xmlHttp.responseText;
                    if (text == "true") {
                        flag = false;
                        document.getElementById("msg").innerHTML = "该邮箱已被注册";
                    } else {
                        flag = true;
                        document.getElementById("msg").innerHTML = "改邮箱可以注册";
                    }
                }
            }
        }
        function checkForm() {
            return flag;
        }
  </script>
<h3>注册</h3>

    <form action="registerServlet" method="POST" name="addEmp" id="addEmp">
      ID：<input style="text" name="ID" id="ID"><label id="errID"></label><br/>
      密码：<input style="text" name="password" id="password"><label id="errPassword"></label><br/>
      邮箱：<input style="text" name="email" id="email" onblur="checkUserid(this.value)"><label id="msg"></label><br/>
      注册类型：<select name="role" id="role">
        <option value="0" selected="selected">请选择 </option>
      </select><label id="errorRole"></label><br/>
    <input type="button" value="注册" onclick="add()">
    <input type="reset"> <br>
    <input type="button" value="已有账号点击登陆" onclick="javascript:window.location.href='login.jsp'">
    </form>
     <c:if test="${!empty requestScope.info}">
      <c:out value="${requestScope.info}"></c:out>
     </c:if>
        <% 
       if (session.getAttribute("info") != null) {
         out.print(session.getAttribute("info"));
         session.removeAttribute("info");
     }%>
</body>
</html>