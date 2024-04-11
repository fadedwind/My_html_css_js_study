import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;
import java.util.Calendar;

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
            
            // Create and store a new entry.
            Entry entry = new Entry(request.getParameter("title"),
                                    request.getParameter("entry"));
            DataStore.addEntry(entry);

            // Display the updated blog.
            // The redirect avoids the problem of duplicate
            // entries if the user clicks the browser's Refresh
            // tool button, since the refreshed page will
            // be the view-blog page, not the add-entry page.
            response.sendRedirect("ViewBlog");
        }

        // If doPreview is missing (it shouldn't be...) or has 
        // value true, create a preview response.
        else {

            // Initialize response
            response.setContentType("text/html; charset=\"UTF-8\"");
            PrintWriter servletOut = response.getWriter();

            // Create a new temporary entry.
            Entry entry = new Entry(request.getParameter("title"),
                                    request.getParameter("entry"));
            
            // Display this blog entry only.
            Vector oneEntry = new Vector();
            oneEntry.add(entry);
            DisplayBlog.display(servletOut, oneEntry, null);

            // Clean up
            servletOut.close();
        }
        return;
    }
}
