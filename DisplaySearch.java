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
@WebServlet("/DisplaySearch")
@MultipartConfig
public class DisplaySearch extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out=response.getWriter();
           ServletContext context=getServletContext();
           String Fname=(String)context.getAttribute("Fname");
        JSONObject obj = new JSONObject();
        String finalLocation=(String)context.getAttribute("file");
         File file=new File(finalLocation);
         FileInputStream fstream=new FileInputStream(file);
         BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
         ArrayList<String> Contacts=new ArrayList<String>();
         String name="";
         String str;
         while((str = br.readLine()) != null){
          String key="";
        
          String value="";
            if(str.startsWith("FN")){
                //out.println(str.substring(str.lastIndexOf(":")+1,str.length())+"<br>");
                 key+=str.substring(str.lastIndexOf(":")+1,str.length());
                if(key.toLowerCase().startsWith(Fname)){
                  Contacts.add(key);
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