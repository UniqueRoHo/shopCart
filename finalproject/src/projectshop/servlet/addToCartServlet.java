package projectshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectshop.service.CartService;
import projectshop.service.ProductService;


@WebServlet("/addToCartServlet")
public class addToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession();
	      int userid = (int) session.getAttribute("userID");
          int productid = Integer.parseInt(request.getParameter("productid"));
          int total = Integer.parseInt(request.getParameter("total"));
          CartService cartService = new CartService();
          if (new ProductService().click(productid)) {
              System.out.println(productid+"点击量增加");
          }
          boolean flag = cartService.addPro(userid, productid, total);
          if (flag) {
              request.setAttribute("info", "添加成功");
              request.getRequestDispatcher("ManageProductServlet").forward(request, response);
          } else {
              System.out.println("错了111111");
          }
	}

}
