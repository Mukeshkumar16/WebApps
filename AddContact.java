import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
@WebServlet("/AddContact")
public class AddContact extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html");
         PrintWriter out=response.getWriter();
         out.println("<html><style>");
         out.println(".container{max-width:428px;margin:0 auto;background-color:#313438;height:700px;}");
         out.println(".header{display:flex;justify-content:space-evenly;background:Linear-gradient(90deg, rgba(66,159,124,1) 0%,rgba(161,193,78,1) 100%);}");
         out.println(".info-line{display:flex;width:70%;margin:0 auto;align-items:center;padding:1em 2em;margin-top:2em;border-radius:20px;box-shadow:6px 6px 12px #2d2f33,-6px -6px 12px #35393d;}");
         out.println("input[type=submit]{display:flex;width:30%;margin:0px auto;align-items:center;padding:1em 2em;margin-top:2em;border-radius:20px;background:Linear-gradient(90deg, rgba(66,159,124,1) 0%,rgba(161,193,78,1) 100%);align:center;}");
         out.println("</style>");
         out.println("<body style='margin:0;color:#fff;'>");
         out.println("<div class=container>");
         out.println("<header class=header><input type=image src=profile.png height=200px width=200px></header>");
         out.println("<form method=Post action=Add>");
         out.println("<div class=info-line>Contact Name :<input type=text name=cname required ></div><br>");
         out.println("<div class=info-line>Mobile No :<input type=text name=mobileno ></div><br>");
         out.println("<div class=info-line>Email :<input type=text name=email ></div><br>"); 
         out.println("<div class=info-lines><input type=submit value='Save Contact'></div>");
          out.println("</form></div></body></html>");
         }
        
}