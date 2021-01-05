<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="projectshop.util.CountUtil"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>登陆页面</title>
  </head>
  <style>
    label {color:red}
    input {margin-top:15px;}
  </style>
  <script>
  function login() {
      var loginFormObj = document.getElementById("loginForm");
      var isSubmitForm = true;
      var IDValue = document.getElementById("ID").value;
      if (!/^[0-9]*$/.test(IDValue)  || IDValue =="") {
          document.getElementById("errID").innerHTML = "只能是数字！";
          isSubmitForm = false;  //这样就不会把请求给服务端
      } else {
          document.getElementById("errID").innerHTML = "";
      }
      var passwordValue = document.getElementById("password").value;
      if (!passwordValue) {
          document.getElementById("errPassword").innerHTML = "请输入密码";
          isSubmitForm = false;  //这样就不会把请求给服务端
      } else {
          document.getElementById("errPassword").innerHTML = "";
      }
      
      if (isSubmitForm) {
          loginFormObj.submit();
      }      
  }
  </script>
  <%!BigInteger count = null; %>
<%! public BigInteger load(File file){
    BigInteger count = null;
    try{
       if(file.exists()){
        Scanner scan = null;
        scan = new Scanner(new FileInputStream(file));
        if(scan.hasNext()){
            count = new BigInteger(scan.next());
        }
        scan.close();
    } else {
        count = new BigInteger("0");
        save(file, count);
    }
    } catch (Exception e){
        e.printStackTrace();
    }
    return count;
}
   public void save(File file, BigInteger count){
       try{
           PrintStream ps = null;
           ps = new PrintStream(new FileOutputStream(file));
           ps.println(count);
           ps.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
%>
<%
  String fileName = this.getServletContext().getRealPath("/") + "count.txt";
   File file = new File(fileName);
   if(session.isNew()){
    synchronized(this){
        count = load(file);
        count = count.add(new BigInteger("1"));
        save(file, count);
    }
}
%>
<%
        String userName="";
        String password="";
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
            //从cookie中获取用户名和密码
                if(cookie.getName().equals("userName")){
                   userName=cookie.getValue();
            }
                if(cookie.getName().equals("password")){
                    password=cookie.getValue();
                    }
            }
            }else{
              System.out.println("null");
            }
        //判断记住密码复选框的状态
        String check="";
        if(!userName.equals("")&&!password.equals("")){
          check="checked";
        }
%>
  <body>
    <h3>购物系统</h3>
    <form action="LoginServlet" method="POST" id="loginForm">
      ID&nbsp;: <input type="text" name="ID" id="ID" value="<%=userName%>"/><label id="errID"></label><br/>
            密码: <input type="password" name="password" id="password" value="<%=password%>"/><label id="errPassword"></label><br/>
            <span>记住密码<input type="checkbox" name="remember" value="on" <%=check%>/></span><br/>
      <input type="button" value="登陆" onclick="login()"/>
      <input type="button" value="注册" onclick="javascript:window.location.href='register.jsp'">
    </form>
<% 
       if (session.getAttribute("info") != null) {
         out.print(session.getAttribute("info"));
         session.removeAttribute("info");
     }%>
     <h2>您是第<%=count==null?0:count%>位访客</h2>
  </body>
</html>