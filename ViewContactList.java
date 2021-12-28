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
@WebServlet("/ViewContactList")
public class ViewContactList extends HttpServlet{
    private static final long serialVersionVID=1L;
     protected synchronized void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject obj = new JSONObject();
        PrintWriter out=response.getWriter();
        ServletContext context=getServletContext(); 
       HttpSession session=request.getSession();
       final String finalLocation=(String)session.getAttribute("file");
         File file=new File(finalLocation);
         //Scanner sc=new Scanner(file);
         FileInputStream fstream=new FileInputStream(file);
         BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
      String name="";
         TreeMap<String,String> options= new TreeMap<String,String>();
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
                          value+=st.substring(st.lastIndexOf("=")+1,st.length())+",";
                       }
                      options.put(key,value);
             }
          }
        context.setAttribute("Map",options);
      String json=new Gson().toJson(options);
     response.setContentType("application/json");
     response.setCharacterEncoding("UTF-8");
      out.write(json);     
}
        
}