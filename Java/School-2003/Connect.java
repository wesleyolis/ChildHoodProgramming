import java.io.*;
import java.lang.*;
public class Connect
{
    static InputStream c = System.in;
    static BufferedReader s = new BufferedReader (new InputStreamReader(c));
    public static String readline ()
    {
	String str = "";
	try
	{
	    str = s.readLine ();
	}
	catch (Exception e)
	{
	    str = "Error on Input";

	}
	return str;
    }


    public static char read ()
    {
	char ch = '*';
	try
	{
	    ch = (char) c.read ();
	}
	catch (Exception e)
	{
	    System.out.println ("Error on Input");

	}
	return ch;
    }
}



