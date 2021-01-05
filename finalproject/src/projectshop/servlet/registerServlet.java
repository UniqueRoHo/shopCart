package projectshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectshop.model.User;
import projectshop.service.UserService;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String ID = request.getParameter("ID");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        String role = request.getParameter("role");
	        System.out.println(role);

	        User user = new User();
	        user.setID(Integer.parseInt(ID));
	        user.setPassword(password);
	        user.setEmail(email);
	        user.setRole(role);
	        boolean flag = new UserService().insertUser(user);
	        if (flag) {
	            request.setAttribute("info", "注册成功");
	            request.getRequestDispatcher("register.jsp").forward(request, response);
	        }
	        else {
	            request.setAttribute("info", "用户已存在，添加失败");
	            request.getRequestDispatcher("/login/failRegister.jsp").forward(request, response);
	        }
	    }

	}


