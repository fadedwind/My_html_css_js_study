import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Iterator;
import java.util.Calendar;
import java.util.Vector;
import java.text.SimpleDateFormat;

/* Methods for generating pages containing blog entries. */
class DisplayBlog { 

    /**
     * Display a complete HTML document containing the blog entries
     * represented by the given Vector argument in their order of
     * appearance in the Vector.  The document will also contain
     * navigation links.
     */
    static void display(PrintWriter servletOut,
                        Vector entries, Vector months)
    {
        displayProlog(servletOut);
        displayEntries(servletOut, entries);
        if (months != null) { // null means previewing an entry
            displaySideInformation(servletOut, months);
        }
        displayEpilog(servletOut);
        return;
    }

    /**
     * Display the static initial portion of the blog-viewing
     * HTML document.
     */
    private static void displayProlog(PrintWriter servletOut)
    {
        servletOut.println(
"<!DOCTYPE html \n" +
"        PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n" +
"        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n" +
"<html xmlns='http://www.w3.org/1999/xhtml'> \n" +
"  <head> \n" +
"    <title>My Own Blog!</title> \n" +
"    <link rel='stylesheet' href='/MOBFiles/style.css' \n" +
"          type='text/css' /> \n" +
"    <script type='text/javascript' \n" +
"            src='/MOBFiles/viewblog.js'></script> \n" +
"    <meta http-equiv='Content-Script-Type' \n" + 
"          content='text/javascript' /> \n" +
"  </head> \n" +
"  <body onload='init();'> \n" +
"    <div class='imgcentered'> \n" +
"      <!-- Banner image --> \n" +
"      <img src='/MOBFiles/banner.gif' width='438' height='120' \n" +
"          alt='\"My Own Blog!\" Banner' /> \n" +
"    </div> \n" +
"    <div class='bodycentered'> ");
        return;
    }

    /**
     * Display blog entries.
     */
     private static void displayEntries(PrintWriter servletOut, 
                                        Vector entries)
    {
        // Output div containing the entries (used for
        // formatting).
        servletOut.println(
"      <!-- Blog entries --> \n" +
"      <div class='leftbody'> ");

        // Output each entry, following all but the last with
        // a separator.
        Iterator entryGetter = entries.iterator();
        while (entryGetter.hasNext()) {
            Entry entry = (Entry)entryGetter.next();
            servletOut.println(
"       <div class='entry'> \n" +
"         <div class='datetime'>" +
            dateFormat(entry.getCreateDateTime()) + "</div> \n" +
"         <div class='entrytitle'>" +
            entry.getTitle() + "</div> \n" +
"         <div class='entrybody'> \n" +
"           " + entry.getText() + "\n" +
"         </div> \n" +
"       </div>" );
            if (entryGetter.hasNext()) {
                servletOut.println(
"       <hr />" );
            }
        }

        // Close the containing div
        servletOut.println(
"      </div> ");

        return;
    }

    /**
     * Format a date/time for display preceding a blog entry.
     */
    private static String dateFormat(Calendar dateTime) {
        SimpleDateFormat formatter = 
            new SimpleDateFormat("MMMMM d, yyyy, h:mm a z");
        return formatter.format(dateTime.getTime()).toUpperCase();
    }

    /**
     * Format a month/year for display as an archive link.
     */
    private static String monthYearFormat(Calendar monthYear) {
        SimpleDateFormat formatter =
            new SimpleDateFormat("MMMMM'&nbsp;'yyyy");
        return formatter.format(monthYear.getTime());
    }

    /**
     * Display the "side" information (basically navbar).
     */
    private static void displaySideInformation(PrintWriter servletOut,
                                               Vector months)
    {
        // Output initial fixed markup
        servletOut.println(
"      <!-- Side information --> \n" +
"      <div class='rightbody'> \n" +
"       <ul> \n" +
"         <li><a href='ViewBlog'>Home</a></li> \n" +
"         <li>Archives \n" +
"           <ul>");

        // Output archive links
        Iterator monthGetter = months.iterator();
        while (monthGetter.hasNext()) {
            Calendar monthYear = (Calendar)monthGetter.next();
            servletOut.println(
"             <li><a href='ViewBlog?month=" +
                Integer.toString(monthYear.get(Calendar.MONTH)+1) +
                "&amp;year=" +
                Integer.toString(monthYear.get(Calendar.YEAR)) +
                "' \n" +
"                 >" + 
                 monthYearFormat(monthYear) +
                "</a></li>");
        }

        // Ouput final fixed markup
        servletOut.println(
"           </ul> \n" +
"         </li> \n" +
"       </ul> \n" +
"      </div>");
        return;
    }

    /**
     * Finish the HTML document.
     */
    private static void displayEpilog(PrintWriter servletOut)
    {
        servletOut.println(
"    </div> \n" +
"  </body> \n" +
"</html> ");
        return;
    }
}
