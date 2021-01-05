package projectshop.servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectshop.model.User;
import projectshop.service.UserService;
import projectshop.util.CountUtil;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
        session.removeAttribute("userID");
        response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //BigInteger count = null;
	    HttpSession session = request.getSession();
	    String ID = request.getParameter("ID");
	    String password = request.getParameter("password");
	    User user = new UserService().selectUser(ID);
	    if ( user != null && Integer.parseInt(ID) == user.getID() && password.equals(user.getPassword())) {
	            String check[] = request.getParameterValues("remember");
	            if (check !=null && check[0].equals("on")) {
	            Cookie c1 = new Cookie("userName",ID);
	            Cookie c2 = new Cookie("password",password);
	            c1.setMaxAge(60 * 60 *24);
	            c2.setMaxAge(60 * 60 *24);
	            response.addCookie(c1);
	            response.addCookie(c2);
	            } else {
	                Cookie c1 = new Cookie("userName","");
	                Cookie c2 = new Cookie("password","");
	                c1.setMaxAge(0);
	                c2.setMaxAge(0);
	                response.addCookie(c1);
	                response.addCookie(c2);
	        }
	        user.setPassword("");
            session.setAttribute("userID", user.getID());
            session.setAttribute("userRole", user.getRole());
	        response.sendRedirect("menu.jsp");
	    } else {
	       request.setAttribute("info", "账号或密码错误");
	       request.getRequestDispatcher("/login/faillogin.jsp").forward(request, response);
	    }
	}
	}


