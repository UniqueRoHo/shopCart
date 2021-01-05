package projectshop.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;

import projectshop.model.Product;
import projectshop.service.ProductService;



@WebServlet("/updateProductServlet")
public class updateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.removeAttribute("pro");
	    int productid = Integer.parseInt(request.getParameter("productid"));
	    Product pro = new Product();
	    pro.setID(productid);
	    Product p = new ProductService().selectPro(pro);
	    if(p != null) {
	        request.setAttribute("pro", p);
	        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
	    } else {
	        System.out.println("无此商品");
	    }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.removeAttribute("pro");
	    int productid = Integer.parseInt(request.getParameter("productid"));
	    SmartUpload smart = new SmartUpload();
        smart.initialize(getServletConfig(), request, response); //初始化上传
        smart.setAllowedFilesList("jpg,png"); //设置允许上传的格式
        String name = "商品";
        float price = 0;
        int total = 0;
	        try {
	            smart.upload(); //上传准备,存入内存
	            name = smart.getRequest().getParameter("proName");
	            price = Float.parseFloat(smart.getRequest().getParameter("price"));
	            total = Integer.parseInt(smart.getRequest().getParameter("total"));
	            Product pro = new Product();
	            pro.setID(productid);
	            Product p = new ProductService().selectPro(pro);
	            String picture = p.getPic();
	            System.out.println(picture);
	            //smart.save(this.getServletContext().getRealPath("/")+"/WebContent/photo"); 默认保存路径\
	            if (smart.getFiles().getSize() != 0 ) { //如果不等于0，则证明已经修改，需要直接覆盖原文件
	                 String ext = smart.getFiles().getFile(0).getFileExt(); //获取文件后缀名
	                 if (picture.equals("defaultPicture.jpg")) {
	                     String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	                     picture = timeStamp + "." + ext;
	                 }
	                smart.getFiles().getFile(0).saveAs("picture" + java.io.File.separator + picture);
	            }
	                pro.setPic(picture);
	                pro.setName(name);
	                pro.setPrice(price);
	                pro.setTotal(total);
	                if (new ProductService().updatePro(pro)) {
	                    request.setAttribute("info", "修改成功");
	                    request.setAttribute("pro", pro);
	                    request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
	                    //request.getRequestDispatcher("ManageProductServlet").forward(request, response);
	                } else {
	                    System.out.println("修改失败");
	                }
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	}

}
