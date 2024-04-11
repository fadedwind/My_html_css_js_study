import java.util.Calendar;

/**
 * Class representing a single blog entry.
 */
class Entry implements java.io.Serializable
{
    /** Allows additions to class without breaking external
     * serialized objects. */
    static final long serialVersionUID = 8764229244229850397L;
    /** Unique identifier for this entry. */
    private int entryID;
    /** Title as entered by user. */
    private String rawTitle;
    /** Title after taking care of XML special characters. */
    private String cookedTitle;
    /** Text as entered by user. */
    private String rawText;
    /** Text after taking care of special tags and XML special
        characters. */
    private String cookedText;
    /** Date and time the entry was created. */
    private Calendar createDateTime;

    /**
     * Construct a blog entry from user data.
     */
    Entry(String rawTitle, String rawText) 
        throws Exception
    {
        this.entryID = -1; // Valid ID assigned when stored
        this.rawTitle = rawTitle;
        this.cookedTitle = cookTitle(rawTitle);
        this.rawText = rawText;
        this.cookedText = cookText(rawText);
        this.createDateTime = Calendar.getInstance();
    }

    /**
     * Cook title: escape all XML special characters.
     */
    private String cookTitle(String rawTitle) {
        return WebTechUtil.escapeXML(rawTitle);
    }

    /**
     * Cook text: Process certain tags in the text,
     * turn others into plain text.
     */
    private String cookText(String rawText) 
        throws Exception
    {
        return (new TextCooker()).cook(rawText);
    }

    /**
     * Return cooked title for display.
     */
    public String getTitle() {
        return cookedTitle;
    }

    /**
     * Return cooked text for display.
     */
    public String getText() {
        return cookedText;
    }

    /**
     * Return raw title for editing.
     */
    public String getRawTitle() {
        return rawTitle;
    }

    /**
     * Return raw text for editing.
     */
    public String getRawText() {
        return rawText;
    }

    /**
     * Return creation date/time.
     */
    public Calendar getCreateDateTime() {
        return createDateTime;
    }

    /**
     * Set unique ID for this entry.  This is set by DataStore when
     * the entry is added to the blog (temporary entries are not
     * assigned ID's).
     */
    public void setID(int id) {
        entryID = id;
        return;
    }

    /**
     * Get this entry's ID.
     */
    public int getID() {
        return entryID;
    }
}
