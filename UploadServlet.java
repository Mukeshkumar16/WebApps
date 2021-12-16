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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.ListIterator;
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html");
         PrintWriter out=response.getWriter();
         String path="C:\\Users\\mukesh-pt4612\\Desktop\\Tomcat";
         Part part=request.getPart("file1");
         String fileName=part.getSubmittedFileName();
         String finalLocation = path + File.separator + fileName;
         part.write(finalLocation);
         File file=new File(finalLocation);
         //Scanner sc=new Scanner(file);
         FileInputStream fstream=new FileInputStream(file);
         BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
         ServletContext context=getServletContext();  
         context.setAttribute("file",finalLocation);  
         out.println("<html>");
         out.println("<head><script src='https://kit.fontawesome.com/cb0c36ad73.js' crossorigin=anonymous></script></head>");
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
         out.println("<div class=container><form style='rigth-align:auto;left-align:auto;' method=Post action=Display>");
         out.println("<header class=header>");
         out.println("<input type=text name=text placeholder=Search class=contact-search required>");
         out.println("</form>");
         out.println("<form method=Post action=AddContact>");
         out.println("<input type=image src=adds.png height=30 width=30 style='padding:0.3em;'align=center><br><br>");
         out.println("</form>");
         out.println("</header>");
         String name="";
         TreeMap<String,ArrayList<String>> m1= new TreeMap<String,ArrayList<String>>();
         String str;
         while((str = br.readLine()) != null){
          String key="";
        ArrayList<String> b=new ArrayList<String>();
          String value="";
            if(str.startsWith("FN")){
                //out.println(str.substring(str.lastIndexOf(":")+1,str.length())+"<br>");
                 key+=str.substring(str.lastIndexOf(":")+1,str.length());
                      String st;
                       while((st=br.readLine())!=null && st.startsWith("TEL")){
                           //out.println(st.substring(st.lastIndexOf("=")+1,st.length())+"<br>");
                           b.add(st.substring(st.lastIndexOf("=")+1,st.length()));
                       }
                      m1.put(key,b);
             }
          }
          out.println("<section class=contact-library><ul class=contacts-library>");
          out.println("<form style='right-align:auto;left-align:auto;' method=Post action=View>");
          for(Map.Entry<String,ArrayList<String>> entry:m1.entrySet()){
             out.println("<div class=contact-section>");
             String s=entry.getKey();
             out.println("<p><input type=submit style='color:white;background-color:#313438;' class=contact-name name=view value='"+s+"'></p>");
             out.println("<input type=image src=phone.png height=30 width=30>");
             out.println("</div><hr>");
           }            
          out.println("</ul></form></section></div></body></html>");
         }
        
}