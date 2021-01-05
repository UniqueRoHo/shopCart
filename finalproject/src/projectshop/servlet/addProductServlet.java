package projectshop.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import projectshop.model.Product;
import projectshop.service.ProductService;



@WebServlet("/addProductServlet")
public class addProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    SmartUpload smart = new SmartUpload();
        smart.initialize(getServletConfig(), request, response); //初始化上传
        smart.setAllowedFilesList("jpg,png"); //设置允许上传的格式
        String picture = "defaultPicture.jpg";
        String name = "商品";
        float price = 0;
        int total = 0;
        try {
            smart.upload(); //上传准备,存入内存
            name = smart.getRequest().getParameter("proName");
            price = Float.parseFloat(smart.getRequest().getParameter("price"));
            total = Integer.parseInt(smart.getRequest().getParameter("total"));
            //smart.save(this.getServletContext().getRealPath("/")+"/WebContent/photo"); 默认保存路径\
            if (smart.getFiles().getSize() != 0 ) {
                 String ext = smart.getFiles().getFile(0).getFileExt(); //获取文件后缀名
                 String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                 picture = timeStamp + "." + ext;  //重新设置文件名
                 smart.getFiles().getFile(0).saveAs("picture" + java.io.File.separator + picture);
            }
            Product pro = new Product();
            pro.setPic(picture);
            pro.setName(name);
            pro.setPrice(price);
            pro.setTotal(total);
            if (new ProductService().addPro(pro)) {
                request.setAttribute("info", "添加成功");
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            } else {
                System.out.println("添加失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
