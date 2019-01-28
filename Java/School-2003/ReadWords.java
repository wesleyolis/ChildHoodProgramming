// Wesley Oliver
import java.io.*;
public class ReadWords
{
    public static void main (String [] args) throws IOException
    {

	read ();

    }


    public static void read () throws IOException
    {
	BufferedReader in = new BufferedReader (new FileReader ("specail.txt"));
	String str = in.readLine ();
	while (str != null)
	{
	    str = in.readLine ();
	    System.out.println (str);
	}
    }
}

