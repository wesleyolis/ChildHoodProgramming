// The "Password" class.
import java.lang.*;
import java.io.*;
public class Password
{
    public static void main (String [] args) throws IOException
    {
	PrintWriter out = new PrintWriter (new FileWriter ("pass.txt"));
	//inclusive 33-126 = 94
	String str = "      " + String.valueOf ((char) 32);
	for (int c = 0 ; c < 70000 ; c++)
	{

	    if (str.charAt (str.length () - 1) == 126)
	    {
		//str = String.valueOf ((char) 33) + String.valueOf (((char) (str.charAt (1) + 1))) + str.substring (2, str.length ());
		for (int i = str.length () - 1 ; i > 0 ; i--)
		{
		    if (str.charAt (i) == 126)
		    {
			str = str.substring (0, i - 2) + String.valueOf (((char) (str.charAt (i - 1) + 1))) + String.valueOf ((char) 33);
		    }
		    else
		    {
			break;
		    }
		}
	    }
	    else
	    {
		str = str.substring (0, str.length () - 1) + String.valueOf (((char) (str.charAt (str.length () - 1) + 1)));
	    }
	    out.println (str);

	}
	out.close ();
    }



} // main method


// Password class


