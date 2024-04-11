import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.InputSource;
import java.io.StringReader;

/**
 * This class can be used to process ("cook") an XML document,
 * allowing certain elements to pass through and
 * converting the tags of other elements to plain text.
 * It also converts a "displayquote" element into a floating
 * span followed by the same text.
 */
class TextCooker {

    /** Result ("cooked") document. */
    StringBuffer cookedText = new StringBuffer();

    /** Method for "cooking" raw text. */
    public String cook(String rawText)
        throws Exception
    {
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        XMLReader parser = saxFactory.newSAXParser().getXMLReader();
        parser.setContentHandler(new CookHelper());
        String rootedText = "<root>" + rawText + "</root>";
        parser.parse(new InputSource(new StringReader(rootedText)));
        return cookedText.toString();
    }

    /** SAX event handling class. */
    private class CookHelper extends DefaultHandler {
        
        /** Whether or not we are in a displayquote element. */
        boolean inQuote = false;

        /** Character data collected for current displayquote 
            element. */
        StringBuffer charData;
            
        CookHelper() {
            super();
        }

        public void startElement(String namespaceURI, 
                                 String localName, String qname,
                                 Attributes atts) 
            throws SAXException
        {
            if (qname.equals("b") || qname.equals("i") ||
                qname.equals("p")) {
                cookedText.append("<" + qname + ">");
            }
            else if (qname.equals("a")) {
                cookedText.append(
                    "<a href='" + 
		    WebTechUtil.escapeQuotes(
                    WebTechUtil.escapeXML(atts.getValue("href"))) +
                    "'>");
            }
            else if (qname.equals("displayquote") && !inQuote) {
                inQuote = true;
                charData = new StringBuffer();
            }
            else if (qname.equals("root")) {
                // discard
            }
            // Unrecognized element: escape < and >
            else { 
                cookedText.append("&lt;" + qname);
                for (int i=0; i<atts.getLength(); i++) {
                    cookedText.append(
                        " " + atts.getQName(i) + "='" +
                        WebTechUtil.escapeXML(atts.getValue(i)) +
                        "'");
                }
                cookedText.append("&gt;");
            }
            return;
        }

        public void characters(char chars[], 
                               int firstChar, int nChars)
            throws SAXException
        {
            String escapedChars = 
                WebTechUtil.escapeXML(
                    new String(chars, firstChar, nChars));
            if (inQuote) {
                charData.append(escapedChars);
            }
            else {
                cookedText.append(escapedChars);
            }
            return;
        }

        public void endElement(String namespaceURI, 
                               String localName, String qname)
            throws SAXException
        {
            if (qname.equals("b") || qname.equals("i") ||
                qname.equals("p") || qname.equals("a")) {
                cookedText.append("</" + qname + ">");
            }
            else if (qname.equals("displayquote") && inQuote) {
                inQuote = false;
                cookedText.append("<span class='dquote'>" +
                                  charData +
                                  "</span>" +
                                  charData);
            }
            else if (qname.equals("root")) {
                // discard
            }
            else {
                cookedText.append("&lt;/" + qname + "&gt;");
            }
            return;
        }
    }
}
