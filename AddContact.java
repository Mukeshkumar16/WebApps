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
          HttpSession session=request.getSession();
         String finalLocation=(String)session.getAttribute("file");
         //out.println(file); 
         StringBuffer buffer=new StringBuffer(); 
         FileWriter fileWritter = new FileWriter(finalLocation,true);
         BufferedWriter bw = new BufferedWriter(fileWritter);
         File file=new File(finalLocation);
         FileInputStream fstream=new FileInputStream(file);
         BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
         String str;
          int flag=0;
         while((str=br.readLine())!=null){
             String name="";
             if(str.startsWith("FN")){
                buffer.append(str+System.lineSeparator());
                name+=str.substring(str.lastIndexOf(":")+1,str.length());
                if(name.equals(ContactName)){
                    out.println(name);
                     buffer.append("TEL:TYPE=Mobile:"+Phoneno+System.lineSeparator());
                     flag=1;
                 }
             }else{
                   buffer.append(str+System.lineSeparator());
                   }
         }
          if(flag==1){
                  String fileContents=buffer.toString();
                  FileWriter writer=new FileWriter(finalLocation);
                  writer.append(fileContents);
                  writer.flush();
          }
           else{  
         bw.write("BEGIN:VCARD\n");
         bw.write("VERSION: 3.0\n");
         bw.write("FN:"+ContactName+"\n");
         bw.write("TEL;TYPE=Mobile:"+Phoneno+"\n");
         bw.write("Email:"+Email+"\n");
         bw.write("END:VCARD");
       bw.close();
         System.out.println("Done");
        }
         } catch(IOException e){
         e.printStackTrace();
         }
         
           response.sendRedirect("ViewContactList.html");
          
         }
        
}