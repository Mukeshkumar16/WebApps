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
@WebServlet("/View")
//@MultipartConfig
public class View extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html");
         PrintWriter out=response.getWriter();
         ServletContext context=getServletContext(); 
         String name=request.getParameter("view");
         String file=(String)context.getAttribute("file"); 
         File files=new File(file);
         FileInputStream fstream=new FileInputStream(files);
         BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
         out.println("<html><style>");
         out.println(".container{max-width:428px;margin:0 auto;background-color:#313438;height:1000px}");
          out.println(".header{display:flex;justify-content:space-evenly;background:Linear-gradient(90deg, rgba(66,159,124,1) 0%,rgba(161,193,78,1) 100%);}");
         out.println(".image{text-align:right;}");
         out.println(".contact-info{padding:3emn 1.5em;}");
         out.println(".info-line{display:flex;width:70%;margin:0 auto;color:white;align-items:center;padding:1em 2em;margin-top:2em;border-radius:20px;box-shadow:6px 6px 12px #2d2f33,-6px -6px 12px #35393d;}");
          out.println("</style>");
          out.println("<body style='background-color:lightblue;text-align:center'>");
          out.println("<div class=container>");
         out.println("<header class=header><input type=image src=profile.png height=200px width=200px></header>");
         String str;
         out.println("<form method=Post action=Edit>");
         out.println("<div class=image><input type=image src=edit.png height=50 width=40></div></form>");
         out.println("<div class=contents>");
         while((str = br.readLine()) != null){
          String key="";
          String value="";
            if(str.startsWith("FN")){
                 key+=str.substring(str.lastIndexOf(":")+1,str.length());
                 if(key.length()==name.length()){
                    String Key="";
                    String Name="";
                    if(key.length()>4){
                     Key+=key.substring(0,key.length()-3);
                     Name+=name.substring(0,name.length()-3); 
                     }else{
                       Key+=key;
                       Name+=name;
                     }  
                 if(Key.equals(Name)){
                     out.println("<section class=contact-info><div class=info-line>");
                     out.println("<p>"+key+"</p></div>");
                      context.setAttribute("name",key);
                      String st;
                       while((st=br.readLine())!=null && st.startsWith("TEL")){
                           out.println("<div class=info-line><p>"+st.substring(st.lastIndexOf(":")+1,st.length())+"</p><br></div>");
                       }
                 }
                }
             }
          }
          out.println("</section></div></body></html>");
         }
        
}