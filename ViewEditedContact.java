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
@WebServlet("/ViewEditedContact")
@MultipartConfig
public class ViewEditedContact extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
           PrintWriter out=response.getWriter();
           ServletContext context=getServletContext();
           String ContactName=(String)context.getAttribute("EditContactName");
           // out.println(ContactName);
            Object contact=context.getAttribute("List");
            HttpSession session=request.getSession();
            String finalLocation=(String)session.getAttribute("file");
            ArrayList contacts=(ArrayList)contact;
            String arr[]=new String[contacts.size()];
            int k=0;
            for(Object details:contacts){
                 arr[k++]=(String)details;
            }
            //out.println(arr[1]);
            File file=new File(finalLocation);
            FileInputStream fstream=new FileInputStream(file);
            BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
            StringBuffer buffer=new StringBuffer();
            String str="";
            int i=0;
            while((str=br.readLine())!=null){
                String name="";
                String Prefix="";
                name+=str.substring(str.lastIndexOf(":")+1,str.length());
                Prefix+=str.substring(0,str.lastIndexOf(":")+1);
                if(ContactName.equals(name)){
                    buffer.append(Prefix);
                    buffer.append(arr[i++]+System.lineSeparator());
                    String st="";
                    while((st=br.readLine())!=null){
                        if(st.startsWith("TEL")){
                            buffer.append(st.substring(0,st.lastIndexOf("=")+1));
                            buffer.append(arr[i++]+System.lineSeparator());
                         }
                         if(st.startsWith("END")){
                           buffer.append(st);
                           break;
                         }
                     }
                 }else{
                    buffer.append(str+System.lineSeparator());
                 }
            }
           String fileContents=buffer.toString();
           FileWriter writer=new FileWriter(finalLocation);
           writer.append(fileContents);
           writer.flush();
           response.sendRedirect("ViewContactList.html");
            
         }
        
}


