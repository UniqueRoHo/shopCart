package projectshop.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class OnlineUserList2 implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private ServletContext app =null;
    @Override
    public void contextInitialized(ServletContextEvent sce)  {
             // TODO Auto-generated method stub
        System.out.println("时间监听器启动");
        this.app = sce.getServletContext();
        this.app.setAttribute("onlineTime", new HashMap<Object, Date>());
        }

    @Override
    public void sessionDestroyed(HttpSessionEvent event)  {
         // TODO Auto-generated method stub
        HashMap<Object, Date> all = (HashMap<Object, Date>)this.app.getAttribute("onlineTime");
        all.remove(event.getSession().getAttribute("userID"));
        this.app.setAttribute("onlineTime", all);

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event)  {
        HashMap<Object, Date> all = (HashMap<Object, Date>)this.app.getAttribute("onlineTime");
        if (event.getName().equals("userID")){
            System.out.println("用户登陆"); 
            all.put(event.getValue(),new Date());
        }
        this.app.setAttribute("onlineTime", all);
    }
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event)  {
        HashMap<Object, Date> all = (HashMap<Object, Date>)this.app.getAttribute("onlineTime");
        if (event.getName().equals("userID")){
            System.out.println("用户注销");
            all.remove(event.getValue());
        }
        this.app.setAttribute("onlineTime", all);
    }
    public void sessionCreated(HttpSessionEvent event) {//只要一打开浏览器就会执行 ,没有登陆也会执行.
      System.out.println("新的session");
    }
}
