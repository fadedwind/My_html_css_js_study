import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Hello World! servlet saving visit counter to file
 */
public class CounterWriter extends HttpServlet
{ 
    // Number of times the servlet has been executed since
    // the program (web server) started
    private static int visits=0;

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

          // Increment the number of visits to the URL for this servlet
          CounterFile.incr();

          // Create the body of the response
          servletOut.println(
"<!DOCTYPE html " +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" " +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> " +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> " +
"    <title> " +
"      CounterWriter.java " +
"    </title> " +
"  </head> " + 
"  <body> " +
"    <p> " + 
"       Hello World! " +
"    </p> " +
"  </body> " +
"</html> ");
          servletOut.close();
        }
}
