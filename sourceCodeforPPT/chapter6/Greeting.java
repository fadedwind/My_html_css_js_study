import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Ask user to sign in on first visit.
 * Welcome user back, using sign-in name, on later visits.
 */
public class Greeting extends HttpServlet
{ 
    /**
     * Respond to any HTTP GET request with either a sign-in or a
     * welcome-back page, depending on whether or not this is the first
     * visit.
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
        
        // If first visit by user, display sign-in form.  Otherwise,
        // welcome the returning visitor back using name stored in the
        // session object on the first visit.
        HttpSession session = request.getSession();
        String signIn = (String)session.getAttribute("signIn");
        if (session.isNew() || (signIn.length() <=0)) {  //changed by zql
        //if (session.isNew() || (signIn.equals(""))) {    //changed by zql        	       
        //if (session.isNew() || (signIn == null)) { 
            printSignInForm(servletOut, "Greeting");
        } else {
            printWelcomeBack(servletOut, signIn);
        }
        
        // Clean up and return
        servletOut.close();
    }

    /**
     * Respond to any HTTP POST response with a thank you, and store
     * the sign-in name supplied in the session object.
     */
    public void doPost (HttpServletRequest request,
                       HttpServletResponse response) 
        throws ServletException, IOException
    {
        // Set the HTTP content type in response header
        response.setContentType("text/html; charset=\"UTF-8\"");
        
        // Obtain a PrintWriter object for creating the body
        // of the response
        PrintWriter servletOut = response.getWriter();
        
        // If signIn parameter present in request, output "thank you"
        // message and store sign-in within session object.
        // Otherwise, an unexpected behavior has occurred; display
        // signIn form again.
        String signIn = request.getParameter("signIn");
        HttpSession session = request.getSession();
        if (signIn.length() >0 ) {   //changed by zql
        //if (!signIn.equals("")) {    //changed by zql
        //if (signIn != null) {        	        	
            printThanks(servletOut, signIn, "Greeting");
            session.setAttribute("signIn", signIn);
        } else {
            printSignInForm(servletOut, "Greeting");
        }

        // Clean up and return
        servletOut.close();
    }

    /**
     * Create page containing form requesting user to sign in.
     */
    private void printSignInForm(PrintWriter servletOut,
                                 String action)
    {
        printStart(servletOut);
        servletOut.println(
"    <form method='post' action='" + action + "'><div> \n" +
"      <label> \n" +
"        Please sign in: <input type='text' name='signIn' /> \n" +
"      </label> \n" +
"      <br /> \n" +
"      <input type='submit' name='doit' value='Sign In' /> \n" +
"    </div></form> \n");
        printEnd(servletOut);
    }

    /**
     * Create a page thanking the user for signing in
     */
    private void printThanks(PrintWriter servletOut,
                             String signIn,
                             String href)
    {
        printStart(servletOut);
        servletOut.println(
"    <p> \n" +
"      Thanks for signing in, " + WebTechUtil.escapeXML(signIn) + ". \n" +
"    </p> \n" +
"    <p> \n" +
"      Please <a href='" + href + "'>visit again</a>! \n" +
"    </p> \n");
        printEnd(servletOut);
    }

    /**
     * Create a page welcoming the user back by name
     */
    private void printWelcomeBack(PrintWriter servletOut,
                                  String signIn)
    {
        printStart(servletOut);
        servletOut.println(
"    <p> \n" +
"      Hey, you're " + WebTechUtil.escapeXML(signIn) + ", aren't you? \n" +
"      Welcome back!! \n" +
"    </p> \n");
        printEnd(servletOut);
    }

    /**
     * Output initial portion of response
     */
    private void printStart(PrintWriter servletOut)
    {
          servletOut.println(
"<!DOCTYPE html \n" +
"    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n" +
"    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n" +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> \n" +
"    <title> \n" +
"      Greeting.java \n" +
"    </title> \n" +
"  </head> \n" + 
"  <body> \n");
    }

    /**
     * Output closing portion of response
     */
    private void printEnd(PrintWriter servletOut)
    {
          servletOut.println(
"  </body> \n" +
"</html> ");
    }
}
