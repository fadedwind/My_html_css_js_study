import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;
import java.util.Calendar;

/**
 * Servlet-based web application component for creating a session
 * for a user and displaying the add-entry form.
 */
public class Login extends HttpServlet
{
    /**
     * HTTP POST request contains a username and password.  If valid,
     * create a session, mark the user and logged in, and display the
     * add-entry HTML document.  Otherwise, re-display the login HTML
     * document.
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
        throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        if (username != null && password != null &&
            username.equals("nice") &&
            password.equals("try")) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedIn", new Boolean(true));
            response.sendRedirect("/MOBFiles/addentry.html");
        }
        else {
            response.sendRedirect("/MOBFiles/login.html");
        }
    }
}
