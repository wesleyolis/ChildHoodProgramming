// The "Inoutfile" class.
import java.awt.*;
import hsa.*;
import java.io.*;
public class Inoutfile
{
    static Console c;           // The output console
    
    public static void main (String [] args) throws IOException
    {
	c = new Console ();
	BufferedReader i = new BufferedReader (new FileReader ("SNAKES_RE.srf"));


       
       



	c.setColor (color(0,i));
	
	c.fillRect (50, 50, 30, 30);
	c.setColor (color(1,i));
	c.fillRect (100, 100, 30, 30);
	
       
	// Place your program here.  'c' is the output console
    }
    public static Color color (int s,BufferedReader i) throws IOException
	{
	    i.read ();
	    int r = (100 * (i.read () - 48)) + (10 * (i.read () - 48) + (i.read () - 48));
	    i.read ();
	    int g = (100 * (i.read () - 48)) + (10 * (i.read () - 48) + (i.read () - 48));
	    i.read ();
	    int b = (100 * (i.read () - 48)) + (10 * (i.read () - 48) + (i.read () - 48));
	    i.read ();
	    return new Color (r, g, b);
	} // main method
} // Inoutfile class
