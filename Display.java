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
import java.io.FileNotFoundException;
import java.util.Scanner;
@WebServlet("/Display")
//@MultipartConfig
public class Display extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html");
         PrintWriter out=response.getWriter();
         ServletContext context=getServletContext(); 
         String name=request.getParameter("text"); 
         String file=(String)context.getAttribute("file"); 
         File files=new File(file);
         FileInputStream fstream=new FileInputStream(files);
         BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
         out.println("<html>");
         out.println("<style>");
         out.println(".container{max-width:428px;margin: 0 auto;background-color:#313438;}");
         out.println(".header{display:flex;justify-content:space-evenly;color:#fff;background:Linear-gradient(90deg, rgba(66,159,124,1) 0%,rgba(161,193,78,1) 100%);padding:2em 0;align-items:center;}");
         out.println(".contact-search{font-size:1.2em;padding:.3em;border-radius:100px;border:1px solid #fff;}");
         out.println(".contact-name{font-size:1.2em;}");
         out.println("i{ font-size:1.5em;}");
         out.println("ul{margin:0;padding:0;width:100%;}");
         out.println("hr{border:1px solid #494a4b;}");
         out.println(".contact-section{display:flex;justify-content:space-between;align-items:center;padding: 0 1.5em;transition:0.3s;}");
         out.println(".contact-section:hover{background-color:#48494a;}");
         out.println("</style>");
         out.println("<body style='margin:0;color:#fff;'>");
         out.println("<div class=container>");
         out.println("<header class=header>");
         out.println("<input type=text name=text placeholder='"+name+"' class=contact-search required>");
         out.println("</header>");
         String str;
          int flag=0; 
         out.println("<section class=contact-library><ul class=contacts-library>");
         out.println("<form style='right-align:auto;left-align:auto;' method=Post action=View>"); 
         while((str = br.readLine()) != null){
          String key="";
          String value="";
            if(str.startsWith("FN")){
                //out.println(str.substring(str.lastIndexOf(":")+1,str.length())+"<br>");
                 key+=str.substring(str.lastIndexOf(":")+1,str.length());
                 if(key.toLowerCase().startsWith(name)){
                    out.println("<div class=contact-section>");
                    out.println("<p><input type=submit style='color:white;background-color:#313438;' class=contact-name name=view value='"+key+"'></p>");
                    out.println("<input type=image src=phone.png height=30 width=30>");
                    out.println("</div><hr>");
                    flag=1;
                 }
             }
          }
           if(flag==0){
               out.println("No Such contacts found <br>");
           }
          out.println("</ul></form></section></div></body></html>");
         }
        
}