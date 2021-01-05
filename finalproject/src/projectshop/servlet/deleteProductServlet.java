package projectshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectshop.model.Product;
import projectshop.service.ProductService;


@WebServlet("/deleteProductServlet")
public class deleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int productid = Integer.parseInt(request.getParameter("productid"));
	    Product pro = new Product();
	    pro.setID(productid);
	    if (new ProductService().deletePro(pro)) {
	        request.setAttribute("info", "删除成功");
	        request.getRequestDispatcher("ManageProductServlet").forward(request, response);
	    } else {
	        request.setAttribute("info", "删除失败");
            request.getRequestDispatcher("ManageProductServlet").forward(request, response);
	    }

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
