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


@WebServlet("/UpLoadServlet")
public class UpLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
	    SmartUpload smart = new SmartUpload();
        smart.initialize(getServletConfig(), request, response); //初始化上传
        //smart.setAllowedFilesList("jpg,png"); //设置允许上传的格式
        int nameID = (int)session.getAttribute("userID");
        String name = String.valueOf(nameID);
        try {
            smart.upload(); //上传准备,存入内存
            //smart.save(this.getServletContext().getRealPath("/")+"/WebContent/photo"); 默认保存路径\
            if (smart.getFiles().getSize() != 0 ) {
                 String ext = smart.getFiles().getFile(0).getFileExt(); //获取文件后缀名
                 String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                 name = name +"general" + timeStamp +"." + ext;  //重新设置文件名
                 smart.getFiles().getFile(0).saveAs("files" + java.io.File.separator + name);
                 request.setAttribute("info", "上传成功");
                 request.getRequestDispatcher("upload.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
