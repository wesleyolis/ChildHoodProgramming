// Wesley Oliver
import java.io.*;
import java.util.*;
import java.lang.*;
public class Read
{
    public static void main (String [] args) throws IOException
    {
	BufferedReader in = new BufferedReader (new FileReader ("Unsorted.txt"));
	PrintWriter out = new PrintWriter (new FileWriter ("Sorted.txt"));
	int unsort [] = new int [10];
	for (int i = 0 ; i < 10 ; i++)
	{
	    unsort [i] = Integer.parseInt (in.readLine ());
	}
	int temp = 0, start = 0, end = 9;
	for (int x = start ; x < end ; x++)
	{
	    for (int y = (x + 1) ; y < (end + 1) ; y++)
	    {
		if (unsort [x] < unsort [y])
		{
		    temp = unsort [x];
		    unsort [x] = unsort [y];
		    unsort [y] = temp;
		}
	    }
	}

	for (int o = 0 ; o < 10 ; o++)
	{
	    out.println (unsort [o]);
	}
	out.close ();
	// Place your code here
    } // main method
} // Create class
