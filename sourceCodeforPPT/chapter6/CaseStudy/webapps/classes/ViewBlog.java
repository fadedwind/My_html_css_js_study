import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Calendar;
import java.util.Vector;

/**
 * Servlet-based web application component for viewing a blog.
 */
public class ViewBlog extends HttpServlet
{
    /**
     * Initialize the data store when this component is instantiated.
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
     * Respond to HTTP GET request with the home page or an archive
     * page, depending on the form of the request.
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException
    {
        // Initialize response
        response.setContentType("text/html; charset=\"UTF-8\"");
        PrintWriter servletOut = response.getWriter();

        // From what month and year should blog entries be displayed?
        // If there is no "month" or "year" parameter present in the
        // request, use the current month and year.  Otherwise, decode
        // month/year information from the appropriate parameter.
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;  
        String monthParam = request.getParameter("month");
        if (monthParam != null) {
            try {
                month = Integer.parseInt(monthParam);
            }
            catch (NumberFormatException nfe) {
                // Use default month if this fails
            }
        }
        String yearParam = request.getParameter("year");
        if (yearParam != null) {
            try {
                year = Integer.parseInt(yearParam);
            }
            catch (NumberFormatException nfe) {
                // Use default year if this fails
            }
        }
        
        // Retrieve the appropriate entries from the data store.
        Vector entries = DataStore.getEntries(month, year);

        // Retrieve months for which blog entries are available
        Vector months = DataStore.getAllMonths();

        // Display the blog entries for the selected month/year.
        DisplayBlog.display(servletOut, entries, months);

        // Clean up
        servletOut.close();
    }
}
