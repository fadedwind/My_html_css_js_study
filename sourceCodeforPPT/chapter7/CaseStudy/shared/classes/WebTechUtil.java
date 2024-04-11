import java.util.regex.*;

/**
 * Utilities to support examples in "Web Technologies" textbook
 */
public class WebTechUtil {

    /**
     * Ampersand pattern used by escapeXML
     */
    static private Pattern pAmp = Pattern.compile("&");
    /**
     * Less-than pattern used by escapeXML
     */
    static private Pattern pLT =  Pattern.compile("<");
    /**
     * Greater-than pattern used by escapeXML
     */
    static private Pattern pGT =  Pattern.compile(">");
    /**
     * Double-quote pattern used by escapeQuotes
     */
    static private Pattern pDQ = Pattern.compile("\"");
    /**
     * Single-quote pattern used by escapeQuotes
     */
    static private Pattern pSQ = Pattern.compile("'");


    /**
     * Return input string with ampersands (&), 
     * less-than signs (<), and greater-than signs (>)
     * replaced with character entity references.
     */
    static public String escapeXML(String inString)
    {
        Matcher matcher = pAmp.matcher(inString);
        String modified = matcher.replaceAll("&amp;");
        matcher = pLT.matcher(modified);
        modified = matcher.replaceAll("&lt;");
        matcher = pGT.matcher(modified);
        modified = matcher.replaceAll("&gt;");
        return modified;
    }

    /**
     * Return input string with all quotes replaced
     * with references.  Use character reference for single quotes
     * because IE6 does not support &apos; entity reference.
     */
    static public String escapeQuotes(String inString)
    {
	Matcher matcher = pDQ.matcher(inString);
	String modified = matcher.replaceAll("&quot;");
	matcher = pSQ.matcher(modified);
	modified = matcher.replaceAll("&#39;");
	return modified;
    }
}
