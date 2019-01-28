import java.io.*;
import java.lang.*;
public class Input
{
    static InputStream c = System.in;
    static BufferedReader s = new BufferedReader (new InputStreamReader (c));
    public static String readStr ()
    {
	String str = "";
	try
	{
	    str = s.readLine ();
	}
	catch (Exception e)
	{
	    str = "Error on String Input";

	}
	return str;
    }


    public static char readChar ()
    {
	char ch = '#';
	try
	{
	    ch = (char) c.read ();

	}
	catch (Exception e)
	{
	    System.out.println ("Error on Character Input");
	}
	return ch;

    }
    
    

    public static int readInt ()
    {
	int i;
	i = Integer.parseInt (readStr ());
	return i;
    }
}
