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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ListIterator;
@WebServlet("/UploadContacts")
@MultipartConfig
public class UploadContacts extends HttpServlet{
    private static final long serialVersionVID=1L;
     int counter=0;
      public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       response.setContentType("text/html");
         PrintWriter out=response.getWriter();
           ServletContext context=getServletContext();
         String path="C:\\Users\\mukesh-pt4612\\Desktop\\Tomcat";
         Part part=request.getPart("file1");
         String fileName=part.getSubmittedFileName();
         String finalLocation = path + File.separator + fileName;
         File file=new File(finalLocation);
         if(!file.exists()){
             part.write(finalLocation);
         } else{
             counter++;
              finalLocation=path+File.separator+counter+fileName;
              part.write(finalLocation);
         }
           HttpSession session=request.getSession();
           session.setAttribute("file",finalLocation);
            response.sendRedirect("ViewContactList.html");
            out.close();
         }        
}