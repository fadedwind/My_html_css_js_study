import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;

/**
 * Servlet to print much of the information contained in an HTTP
 * GET or POST request.
 */
public class PrintHttpInfo extends HttpServlet
{ 
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) 
        throws ServletException, IOException
    {
        // Set the HTTP content type in response header
        response.setContentType("text/html; charset=\"UTF-8\"");

        // Create the beginning of the HTML of the response
        PrintWriter servletOut = response.getWriter();
        servletOut.println(
"<!DOCTYPE html " +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" " +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> " +
"<html xmlns='http://www.w3.org/1999/xhtml'> " +
"  <head> " +
"    <title> " +
"      PrintHttpInfo.java " +
"    </title> " +
"  </head> " + 
"  <body> ");

        // Add HTTP information to response
        printInfo(request, servletOut);

        // Complete document 
        servletOut.println(
"  </body>" +
"</html>" );
        servletOut.close();
    }

    public void doPost (HttpServletRequest request,
                       HttpServletResponse response) 
        throws ServletException, IOException
    {
        // Set the HTTP content type in response header
        response.setContentType("text/html; charset=\"UTF-8\"");
        
        // Create the beginning of the HTML of the response
        PrintWriter servletOut = response.getWriter();
        servletOut.println(
"<!DOCTYPE html " +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" " +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> " +
"<html xmlns='http://www.w3.org/1999/xhtml'> " +
"  <head> " +
"    <title> " +
"      PrintHttpInfo.java " +
"    </title> " +
"  </head> " + 
"  <body> ");

        // Add HTTP information to response
        printInfo(request, servletOut);

        // Complete document 
        servletOut.println(
"  </body>" +
"</html>" );
        servletOut.close();
    }

    /** 
     * Add tables of HTTP protocol and header information to
     * servletOut object
     */
    private void printInfo(HttpServletRequest request,
                           PrintWriter servletOut) 
        throws IOException
    {

        // Output protocol information
        servletOut.println(
"    <table border='0'> " +

"      <tr><th align='right'>Client IP address:</th><td>" + 
         request.getRemoteAddr() + "</td></tr>" +
"      <tr><th align='right'>Client host name:</th><td>" + 
         request.getRemoteHost() + "</td></tr>" +
"      <tr><th align='right'>HTTP protocol:</th><td>" + 
         request.getProtocol() + "</td></tr>" +
"      <tr><th align='right'>Secure channel:</th><td>" + 
         request.isSecure() + "</td></tr>" +
"      <tr><th align='right'>Request URL:</th><td>" + 
         request.getRequestURL() + "</td></tr>" +
"    </table>" );

        // Output HTTP header fields
        String headerName;      // holds name portion of one header field
        String headerValue;     // holds value portion of one header field
        Enumeration headerNames = request.getHeaderNames();
        if (headerNames == null) {
            servletOut.println(
"    <p style='color:red'>Cannot access headers</p> " );
        } else {
            servletOut.println(
"    <table border='1'> " +
"    <caption>HTTP Header Fields</caption> " +
"      <tr><th>Name</th><th>Value</th></tr>" );
          
            while (headerNames.hasMoreElements()) {
                headerName = (String)headerNames.nextElement();
                headerValue = request.getHeader(headerName);
                servletOut.println(
"      <tr><td>" + headerName + "</td> " +
"          <td>" + headerValue + "</td></tr>");
            }
            servletOut.println(
"    </table>" );
        }
    }
}
