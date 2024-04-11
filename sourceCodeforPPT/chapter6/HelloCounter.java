import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Hello World! servlet with visit counter
 */
public class HelloCounter extends HttpServlet
{ 
    // Number of times the servlet has been executed since
    // the program (web server) started
    private int visits=0;

    /**
     * Respond to any HTTP GET request with an 
     * HTML Hello World! page with visit counter.
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

          // Compute the number of visits to the URL for this servlet
          visits++;

          // Create the body of the response, including visit count
          servletOut.println(
"<!DOCTYPE html \n" +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n" +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n" +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> \n" +
"    <title> \n" +
"      HelloCounter.java \n" +
"    </title> \n" +
"  </head> \n" + 
"  <body> \n" +
"    <p> \n" + 
"       Hello World! \n" +
"    </p> \n" +
"    <p> \n" +
"       This page has been viewed \n" +
          visits +
"       times since the most recent server restart. \n" +
"    </p> \n" +
"  </body> \n" +
"</html> ");
          servletOut.close();
        }
}
