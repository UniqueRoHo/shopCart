package projectshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectshop.model.Cart;
import projectshop.model.Product;
import projectshop.service.CartService;


@WebServlet("/MyShoppingCartServlet")
public class MyShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    int userid =  (int)session.getAttribute("userID");
	    CartService cartService = new CartService();
	    Cart cart = cartService.listCart(userid);
	    request.setAttribute("myCart", cart);
	    request.getRequestDispatcher("myCart.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
	}

}
