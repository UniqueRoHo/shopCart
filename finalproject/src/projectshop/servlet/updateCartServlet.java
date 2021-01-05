package projectshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectshop.service.CartService;

/**
 * Servlet implementation class updateCartServlet
 */
@WebServlet("/updateCartServlet")
public class updateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int userid = Integer.parseInt((String)request.getParameter("userid"));
	    int productid = Integer.parseInt((String)request.getParameter("productid"));
	    int total = Integer.parseInt((String)request.getParameter("total"));
	    if (total == 0 ) {
	        this.doPost(request, response);
	    }
	    CartService cartService = new CartService();
	    boolean flag = cartService.updateCart(userid, productid, total);
	    if (flag) {
	        request.getRequestDispatcher("MyShoppingCartServlet").forward(request, response);
	    }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int userid = Integer.parseInt((String)request.getParameter("userid"));
        int productid = Integer.parseInt((String)request.getParameter("productid"));
        System.out.println("sssss1");
        CartService cartService = new CartService();
        boolean flag = cartService.deleteCart(userid, productid);
        System.out.println("sssss2");
        System.out.println(flag);
        if (flag) {
            request.getRequestDispatcher("MyShoppingCartServlet").forward(request, response);
        } else {
            request.getRequestDispatcher("MyShoppingCartServlet").forward(request, response);
        }
	}

}
