import java.util.Vector;
import java.util.Iterator;
import java.util.Calendar;
import java.io.*;

/**
 * This class implements a file-based data store for the
 * "My Own Blog" web application.
 */

class DataStore {

    /** Flag indicating whether or not initialization has
     *  occurred. */
    private static boolean initialized = false;

    /** Data store file. */
    private static File dataStore = new File("MyOwnBlog.ser");

    /** Vector of all blog entries, most recent first. */
    private static Vector entries = null;

    /**
     * Open the data store file and input the blog entries.
     */
    synchronized public static void initialize() 
        throws IOException, ClassNotFoundException
    {
        if (!initialized) {
            if (dataStore.exists()) {
                ObjectInputStream inobj = 
                    new ObjectInputStream(
                        new BufferedInputStream(
                            new FileInputStream(dataStore)));
                entries = (Vector)inobj.readObject();
                inobj.close();
            }
            else {
                entries = new Vector();
            }
            initialized = true;
        }
    }

    /**
     * Get blog entries for the specified month and year.
     */
    synchronized public static Vector getEntries(int month, int year) {

        // Simple but inefficient implementation
        Iterator entryGetter = entries.iterator();
        Vector returnEntries = new Vector();
        while (entryGetter.hasNext()) {
            Entry entry = (Entry)entryGetter.next();
            Calendar entryDate = entry.getCreateDateTime();
            if (entryDate.get(Calendar.YEAR) == year &&
                entryDate.get(Calendar.MONTH)+1 == month) {
                returnEntries.add(entry);
            }
        }
        return returnEntries;
    }

    /**
     * Get a vector of all of the months for which there
     * are entries.  Each element of the vector is a
     * Calendar object; only the month and year are relevant.
     * Each month/year appears once, from most recent to
     * earliest.
     */
    synchronized public static Vector getAllMonths() {

        // Simple but inefficient implementation
        int month = -1;         // initialize with impossible values
        int year = -1;
        Vector months = new Vector();
        Iterator entryGetter = entries.iterator();
        while (entryGetter.hasNext()) {
            Entry entry = (Entry)entryGetter.next();
            Calendar entryDate = entry.getCreateDateTime();
            if (entryDate.get(Calendar.YEAR) != year ||
                entryDate.get(Calendar.MONTH) != month) {
                months.add(entryDate);
                year = entryDate.get(Calendar.YEAR);
                month = (entryDate.get(Calendar.MONTH));
            }
        }
        return months;
    }

    /**
     * Add an entry to the blog.  This also assigns a
     * unique ID to the entry.
     */
    synchronized public static void addEntry(Entry entry) 
        throws IOException
    {
        entry.setID(entries.size());
        // Add to beginning (latest entry first)
        entries.add(0, entry);
        DataStore.writeBlog();
    }

    /**
     * Output the blog entries to data store.
     */
    synchronized private static void writeBlog()
        throws IOException
    {
        ObjectOutputStream outobj = 
            new ObjectOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(dataStore)));
        outobj.writeObject(entries);
        outobj.close();
        return;
    }
}
