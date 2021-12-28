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
@WebServlet("/DisplayContact")
@MultipartConfig
public class DisplayContact extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out=response.getWriter();
           ServletContext context=getServletContext();
          
           String Fname=(String)context.getAttribute("ViewContact");
        JSONObject obj = new JSONObject();
        HttpSession session=request.getSession();
        final String finalLocation=(String)session.getAttribute("file");
        //out.println(finalLocation);
        context.setAttribute("file2",finalLocation);
         File file=new File(finalLocation);
         FileInputStream fstream=new FileInputStream(file);
         BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
         ArrayList<String> Contacts=new ArrayList<String>();
        
         String str;
         while((str = br.readLine()) != null){
          String key="";
          String value="";
            if(str.startsWith("FN")){
                 key+=str.substring(str.lastIndexOf(":")+1,str.length());
                 if(key.length()==Fname.length()){
                     String s="";
                     String s2="";
                     if(key.length()>4){
                      s+=key.substring(0,key.length()-3);
                     s2+=Fname.substring(0,Fname.length()-3);
                   }else{
                       s+=key;
                       s2+=Fname;
                       }
                     if(s.equals(s2)){
                        Contacts.add(Fname);
                      String st;
                       while((st=br.readLine())!=null && st.startsWith("TEL")){
                               String Mobile=st.substring(st.lastIndexOf("=")+1,st.length());
                           Contacts.add(Mobile);
                       } 
                    }  
                 }
             }
          }
         
       String json=new Gson().toJson(Contacts);
    // PrintWriter out=response.getWriter();
     response.setContentType("application/json");
     response.setCharacterEncoding("UTF-8");
      out.write(json);
      //out.println(json);
          
         }
        
}