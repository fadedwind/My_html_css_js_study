import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Hello World! servlet with visit counter and updater
 */
public class VisitCountUpdate extends HttpServlet
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

	  // Turn caching off so that browser will re-visit us
	  // when a POST occurs (works around IE6 problem)
	  response.setHeader("Cache-Control", "no-cache");

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
"      VisitCountUpdate.java \n" +
"    </title> \n" +
"    <script type='text/javascript' src='http://localhost:8084/autoupdate/VisitCountUpdate.js'> \n" +
"    </script> \n" +
"    <meta http-equiv='Content-Script-Type' content='text/javascript' /> \n" +
"  </head> \n" + 
"  <body onload='init();'> \n" +
"    <p> \n" + 
"       Hello World! \n" +
"    </p> \n" +
"    <p> \n" +
"       This page has been viewed \n" +
"       <span id='visits'>" + visits + "</span> \n" +
"       times since the most recent server restart. \n" +
"    </p> \n" +
"  </body> \n" +
"</html> ");
          servletOut.close();
        }

    /**
     * Respond to any HTTP POST request with an 
     * XML document containing the current visit count.
     */
    public void doPost (HttpServletRequest request,
                        HttpServletResponse response) 
      throws ServletException, IOException
        {
          // Set the HTTP content type in response header
          response.setContentType("application/xml; charset=\"UTF-8\"");
          
          // Obtain a PrintWriter object for creating the body
          // of the response
          PrintWriter servletOut = response.getWriter();

	  // Output the count
	  servletOut.println(
"<?xml version='1.0' encoding='UTF-8'?> \n" +
"<count>" + visits + "</count>");
	  servletOut.close();
	}

}
