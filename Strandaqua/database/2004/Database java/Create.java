//Wesley Oliver
import java.io.*;
public class Create
{
    public static void main (String [] args) throws IOException
    {
	PrintWriter out = new PrintWriter (new FileWriter ("Unsorted.txt")); // Place your code here
	for (int i = 0 ; i < 10 ; i++)
	{
	    out.println (String.valueOf ((int) (Math.random () * 10)));
	}
	out.close ();
    } // main method
} // Create class
