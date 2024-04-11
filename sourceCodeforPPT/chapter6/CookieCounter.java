import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet that keeps count of the number of visits
 * to a site using a cookie.
 */
public class CookieCounter extends HttpServlet
{ 
    private static final int oneYear = 60*60*24*365;

    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) 
        throws ServletException, IOException
    {
        // Get count from cookie if available, otherwise
        // use initial value.
        int count = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i=0; (i<cookies.length) && (count==0); i++) {
                if (cookies[i].getName().equals("COUNT")) {
                    count = Integer.parseInt(cookies[i].getValue());
                }
            }
        }

        // Increment the count and add request to client to store it
        // for one year.
        count++;
        Cookie cookie = new Cookie("COUNT", 
                                   new Integer(count).toString());
        cookie.setMaxAge(oneYear); 
        response.addCookie(cookie);

        // Set the HTTP content type in response header
        response.setContentType("text/html; charset=\"UTF-8\"");

        // Create the HTML of the response
        PrintWriter servletOut = response.getWriter();
        servletOut.println(
"<!DOCTYPE html \n" +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n" +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n" +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> \n" +
"    <title> \n" +
"      CookieCounter.java \n" +
"    </title> \n" +
"  </head> \n" + 
"  <body> \n" +
"    <p>You have visited this page " + count + " time(s) \n" +
"       in the past year, or since clearing your cookies.</p> \n" +
"  </body> \n" +
"</html>" );
        servletOut.close();
    }
}
