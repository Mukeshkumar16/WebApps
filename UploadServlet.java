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
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out=response.getWriter();
         String path="C:\\Users\\mukesh-pt4612\\Desktop\\Tomcat";
         Part part=request.getPart("file1");
         String fileName=part.getSubmittedFileName();
         String finalLocation = path + File.separator + fileName;
         File file=new File(finalLocation);
         FileInputStream fstream=new FileInputStream(file);
         BufferedReader br=new BufferedReader(new InputStreamReader(fstream));
         ServletContext context=getServletContext();  
         context.setAttribute("file",finalLocation);
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.sendRedirect("redirect.html"); 
         }
        
}