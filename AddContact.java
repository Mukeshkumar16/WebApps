import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;
import org.json.JSONObject;
import org.json.JSONException;
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
import com.google.gson.*;
import java.util.ListIterator;
@WebServlet("/AddContact")
public class AddContact extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out=response.getWriter();
           //ServletContext context=getServletContext();
           String ContactName=request.getParameter("ContactName");
           String Phoneno=request.getParameter("Phoneno");
           String Email=request.getParameter("Email");
 try {
         
         ServletContext context=getServletContext();
         String file=(String)context.getAttribute("file");
         //out.println(file);  
         FileWriter fileWritter = new FileWriter(file,true);
         BufferedWriter bw = new BufferedWriter(fileWritter);
         bw.write("BEGIN:VCARD\n");
         bw.write("VERSION: 3.0\n");
         bw.write("FN:"+ContactName+"\n");
         bw.write("TEL;TYPE=Mobile:"+Phoneno+"\n");
         bw.write("Email:"+Email+"\n");
         bw.write("END:VCARD");
       bw.close();
         System.out.println("Done");
      } catch(IOException e){
         e.printStackTrace();
      }
           response.sendRedirect("redirect.html");
         }
        
}