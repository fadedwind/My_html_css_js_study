import java.io.*;

/** 
 * This class uses synchronized class methods to synchronize thread
 * access to a file containing a counter.
*/

public class CounterFile
{ 

    private static final String filename = new String("count.dat");

    /** Increment the counter, creating a file if it does not exists */

    synchronized public static void incr() 
        throws IOException 
    {
        int count;              // Value stored in file

        // Create File object associated with the count file
        File countFile = new File(filename);

        // Get count from file if it exists, and use default value 0 o.w.
        count = readCount(countFile);

        // Update the count and output it to the file
        count++;
        writeCount(countFile, count);
    }

    /** Return current value of the counter and reset to 0 */

    synchronized public static int readAndReset() 
        throws IOException 
    {
        int count;              // Value stored in file

        // Create File object associated with the count file
        File countFile = new File(filename);
        
        // Get count from file if it exists, and use default value 0 o.w.
        count = readCount(countFile);

        // Output reset value to the file
        writeCount(countFile, 0);

        // Return the count read from the file
        return count;
    }

    /** Create output stream for countFile and output count. */

    private static void writeCount(File countFile, int count)
        throws IOException 
    {
        DataOutputStream outstream = 
            new DataOutputStream(new FileOutputStream(countFile));
        outstream.writeInt(count);
        outstream.close();
    }

    /** Input count from file, returning 0 if file does not exist */

    private static int readCount(File countFile)
        throws IOException 
    {
        int count = 0;          // Default value if file does not exist
        
        if (countFile.exists()) {
            DataInputStream instream = 
                new DataInputStream(new FileInputStream(countFile));
            count = instream.readInt();
            instream.close();
        }
        return count;
    }
}
