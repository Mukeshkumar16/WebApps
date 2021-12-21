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
@WebServlet("/ViewContact")
@MultipartConfig
public class ViewContact extends HttpServlet{
    private static final long serialVersionVID=1L;
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out=response.getWriter();
           ServletContext context=getServletContext();
           String Fname=request.getParameter("View");
           context.setAttribute("ViewContact",Fname);
            response.sendRedirect("ViewContact.html");
           //out.println(Fname);
         }
        
}