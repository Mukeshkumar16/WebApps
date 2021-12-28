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
@WebServlet("/DeleteContact")
public class DeleteContact extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out=response.getWriter();
           ServletContext context=getServletContext();
          
           String ContactName=(String)context.getAttribute("ViewContact");
           HttpSession session=request.getSession();
           String finalLocation=(String)session.getAttribute("file");
           File file=new File(finalLocation);
           FileInputStream fstream=new FileInputStream(file);
           BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
           StringBuffer buffer = new StringBuffer();
           String str;
           String oldLine ="";
           while ((str=br.readLine())!=null) {
            String name="";
            name+=str.substring(str.lastIndexOf(":")+1,str.length());
         if(name.equals(ContactName)){
             oldLine+=str;
             String st;
             while((st=br.readLine())!=null){
                if(st.equals("END:VCARD")){
                      break;
                  }else{
                     continue;
                   }
             } 
          }else{
             buffer.append(str+System.lineSeparator());
             }
        
      }
      String fileContents = buffer.toString();
      FileWriter writer = new FileWriter(finalLocation);
      writer.append(fileContents);
      writer.flush();
      response.sendRedirect("ViewContactList.html");
          
           
         }
        
}