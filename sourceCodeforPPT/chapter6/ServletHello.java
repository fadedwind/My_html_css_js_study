import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Hello World! servlet
 */
public class ServletHello extends HttpServlet
{ 
    /**
     * Respond to any HTTP GET request with an 
     * HTML Hello World! page.
     */
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) 
      throws ServletException, IOException
        {
          // Set the HTTP content type in response header
          response.setContentType("text/html; charset=\"UTF-8\"");
          
          // Obtain a PrintWriter object for creating the body
          // of the response
          PrintWriter servletOut = response.getWriter();

          // Create the body of the response
          servletOut.println(
"<!DOCTYPE html \n" +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n" +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n" +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> \n" +
"    <title> \n" +
"      ServletHello.java \n" +
"    </title> \n" +
"  </head> \n" + 
"  <body> \n" +
"    <p> \n" + 
"       Hello World! \n" +
"    </p> \n" +
"  </body> \n" +
"</html> ");
          servletOut.close();
        }
}
