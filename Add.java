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
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
@WebServlet("/Add")
@MultipartConfig
public class Add extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html");
         PrintWriter out=response.getWriter();
         String Fname=request.getParameter("cname");
         String Mobile=request.getParameter("mobileno");
         String Email=request.getParameter("email");
         out.println("<html>");
         out.println("<style>");
         out.println(".container{max-width:428px;margin: 0 auto;background-color:#313438;height:500px;}");
         out.println("input[type=submit]{display:flex;width:30%;margin:0px auto;align-items:center;padding:1em 2em;margin-top:2em;border-radius:20px;background:Linear-gradient(90deg, rgba(66,159,124,1) 0%,rgba(161,193,78,1) 100%);align:center;}");
         out.println(".header{display:flex;justify-content:space-evenly;color:#fff;background:Linear-gradient(90deg, rgba(66,159,124,1) 0%,rgba(161,193,78,1) 100%);padding:2em 0;align-items:center;}");
         out.println("</style>");
         out.println("<body style='margin:0;color:#fff;'>");
         out.println("<div class=Container");
          out.println("<header class=header><h1 style='color:white;'>Contact created</h1></header><br>");
          out.println("<form method=Post action=Show>");
          out.println("<input type=submit value='view Contacts'>");
          out.println("</form>");
          out.println("<div></body></html>");
     try {
         
         ServletContext context=getServletContext();
         String file=(String)context.getAttribute("file");
         //out.println(file);  
         FileWriter fileWritter = new FileWriter(file,true);
         BufferedWriter bw = new BufferedWriter(fileWritter);
         bw.write("BEGIN:VCARD\n");
         bw.write("VERSION: 3.0\n");
         bw.write("FN:"+Fname+"\n");
         bw.write("TEL;TYPE=Mobile:"+Mobile+"\n");
         bw.write("Email:"+Email+"\n");
         bw.write("END:VCARD");
       bw.close();
         System.out.println("Done");
      } catch(IOException e){
         e.printStackTrace();
      }
         }
        
}