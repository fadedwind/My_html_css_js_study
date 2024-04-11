import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet that prints the value of a parameter,
 * optionally in color.
 */
public class PrintThis extends HttpServlet
{ 
    /**
     * Respond to any HTTP GET request with an
     * page displaying the value of the "arg"
     * parameter.
     */
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) 
      throws ServletException, IOException
        {
          // Set the HTTP content type in response header.
          response.setContentType("text/html; charset=\"UTF-8\"");
          
          // Obtain a PrintWriter object for creating the body
          // of the response
          PrintWriter servletOut = response.getWriter();

          // Create the first part of the body of the response
          servletOut.println(
"<!DOCTYPE html \n" +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n" +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n" +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> \n" +
"    <title> \n" +
"      PrintThis.java \n" +
"    </title> \n" +
"  </head> \n" + 
"  <body> \n" +
"    <p>Query string: " + 
     WebTechUtil.escapeXML(request.getQueryString()) + "</p>" );

          // Decide whether or not to set color
          String color = request.getParameter("color");
          if (color == null) {
              servletOut.println(
"    <p> " );
          } else {
              servletOut.println(
"    <p style='color:" + 
             WebTechUtil.escapeQuotes(WebTechUtil.escapeXML(color)) +
"'> " );
          }

          // Decide which string to output
          String arg = request.getParameter("arg");
          if (arg == null) {
              arg = "Hello World!";
          }

          // Create remainder of response body
          servletOut.println(
"     " +  WebTechUtil.escapeXML(arg) + "\n" +
"    </p> \n" +
"  </body> \n" +
"</html> ");
          servletOut.close();
        }
}
