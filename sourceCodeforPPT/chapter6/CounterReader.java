import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Hello World! servlet with visit counter
 */
public class CounterReader extends HttpServlet
{ 
    /**
     * Respond to any HTTP GET request with an 
     * HTML Hello World! page with visit counter.
     */
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) 
      throws ServletException, IOException
    {

          // Number of times the servlet has been executed since
          // the counter was last reset
          int visits;

          // Set the HTTP content type in response header
          response.setContentType("text/html; charset=\"UTF-8\"");
          
          // Obtain a PrintWriter object for creating the body
          // of the response
          PrintWriter servletOut = response.getWriter();

          // Compute the number of visits to the URL for this servlet
          visits = CounterFile.readAndReset();

          // Create the body of the response, including visit count
          servletOut.println(
"<!DOCTYPE html " +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" " +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> " +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> " +
"    <title> " +
"      CounterReader.java " +
"    </title> " +
"  </head> " + 
"  <body> " +
"    <p> " +
"       Visits since most recent count reset: " +
          visits +
"    </p> " +
"  </body> " +
"</html> ");
          servletOut.close();
        }
}
