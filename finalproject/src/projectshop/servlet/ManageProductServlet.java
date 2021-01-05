package projectshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;

import projectshop.model.Pagination;
import projectshop.model.Product;
import projectshop.service.ProductService;


@WebServlet("/ManageProductServlet")
public class ManageProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //设置每页显示的条数
        int pageSize = 5;
        HttpSession session = request.getSession();
        if (session.getAttribute("pageSize") != null) {
            pageSize = (int)session.getAttribute("pageSize");
        } else {
            System.out.println("session 空");
        }

        String pageSizeStr = request.getParameter("pageSize");
        
        if (pageSizeStr != null) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (pageSize > 10) {
            pageSize = 10;
        }
        
        //获取当前页数
        
        int currentPage = 1;
        if (session.getAttribute("currentPage") != null) {
            currentPage = (int)session.getAttribute("currentPage");
        } else {
            System.out.println("session 空");
        }

        String currentPageStr = request.getParameter("currentPage");
        
        if (currentPageStr != null) {
            currentPage = Integer.parseInt(currentPageStr);
        }

        Pagination pagination = new Pagination();

        pagination.setCurrentPage(currentPage);
        pagination.setPageSize(pageSize);

        
        ProductService proService = new ProductService();
        int proCount = proService.getProCount();
        List<Product> list = proService.listPro(pagination);
        
        session.setAttribute("pageSize", pageSize);
        session.setAttribute("currentPage", currentPage);
        request.setAttribute("pagination", pagination);
        request.setAttribute("proCount", proCount);
		request.setAttribute("listPro", list);
		if (session.getAttribute("userRole").equals("admin")) {
		    request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
		} else {
		    request.getRequestDispatcher("listProduct.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
	}

}
