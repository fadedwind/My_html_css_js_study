import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;
import java.util.Calendar;
import org.xml.sax.SAXParseException;

/**
 * Servlet-based web application component for accepting a new
 * blog entry and either creating a preview of it or
 * adding it to the blog and returning the blog home page.
 */
public class AddOrPreview extends HttpServlet
{
    /**
     * Initialize the data store when this component is instantiated,
     * since this might be the first component instantiated.
     */
    public void init()
    {
        try {
            // Initialize the data store
            DataStore.initialize();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Process HTTP POST request.
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
        throws ServletException, IOException
    {
        // If session does not contain "loggedIn" attribute,
        // user is not logged in (session may have expired).
        // Redirect to error page.
        HttpSession session = request.getSession();
        if (session.getAttribute("loggedIn") == null) {
            response.sendRedirect("/MOBFiles/loginRequired.html");
        }

        // User is logged in.  If doPreview is false, add an entry 
        // to the blog and display the updated blog.
        else if (request.getParameter("doPreview") != null &&
                 request.getParameter("doPreview").equals("false")) {

            try {
                Entry entry = 
                    new Entry(request.getParameter("title"),
                              request.getParameter("entry"));
                DataStore.addEntry(entry);
                response.sendRedirect("ViewBlog");
            }
            catch (SAXParseException spe) {
                displaySAXException(response, spe);
            }
            catch (Exception e) {
                throw new ServletException(e);
            }
        }

        // If doPreview is missing (it shouldn't be...) or has 
        // value true, create a preview response.
        else {

            try {
                // Create a new temporary entry.
                Entry entry = 
                    new Entry(request.getParameter("title"),
                              request.getParameter("entry"));
                displayOneEntry(response, entry);
            }
            catch (SAXParseException spe) {
                displaySAXException(response, spe);
            }
            catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return;
    }

    private void displayOneEntry(HttpServletResponse response,
                                 Entry entry) 
        throws IOException
    {
        // Initialize response
        response.setContentType("text/html; charset=\"UTF-8\"");
        PrintWriter servletOut = response.getWriter();
        
        // Display this blog entry only.
        Vector oneEntry = new Vector();
        oneEntry.add(entry);
        DisplayBlog.display(servletOut, oneEntry, null);
        
        // Clean up
        servletOut.close();
        return;
    }

    private void displaySAXException(HttpServletResponse response,
                                     SAXParseException spe)
        throws IOException
    {
        // Initialize response
        response.setContentType("text/html; charset=\"UTF-8\"");
        PrintWriter servletOut = response.getWriter();
        
        servletOut.println(
"<!DOCTYPE html \n" +
"        PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n" +
"        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n" +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> \n" +
"    <title>Error in My Own Blog entry</title> \n" +
"    <link rel='stylesheet' href='/MOBFiles/style.css' \n" +
"          type='text/css' /> \n" +
"  </head> \n" +
"  <body> \n" +
"    <h1>Error Parsing Blog Text</h1> \n" +
"    <p>");
        servletOut.println(
"      An error occurred at line " + spe.getLineNumber() +
" column " + spe.getColumnNumber() + ": ");
        if (spe.getException() != null) {
            servletOut.println(
                WebTechUtil.escapeXML(spe.getException().toString()));
        }
        else {
            servletOut.println(
                WebTechUtil.escapeXML(spe.toString())); 
        }
        servletOut.println(
"    </p> \n" +
"  </body> \n" +
"</html>\n");
        
        // Clean up
        servletOut.close();
        return;
    }
}
