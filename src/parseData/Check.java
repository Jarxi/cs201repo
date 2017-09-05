package parseData;
import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
   
   
 public class Check extends HttpServlet {  
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
       //解决返回中文乱码问题  
       response.setCharacterEncoding("utf-8");  
       String user = request.getParameter("username");  
       //解决接收中文乱码问题  
       user = new String(user.getBytes("iso-8859-1"),"gb2312");  
       System.out.println(user);  
       String msg = null;  
       if("许老师".equals(user))  
       {  
           msg = "用户名已经被占用!";  
       }else  
       {  
           msg = "用户名可以使用!";  
       }  
       response.getWriter().println(msg);  
        
    }   
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
       doGet(request,response);  
    }                
}  