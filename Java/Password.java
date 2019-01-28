// The "Password" class.
import java.lang.*;
import java.io.*;
public class Password
{
    public static void main (String [] args) throws IOException
    {
	PrintWriter out = new PrintWriter (new FileWriter ("pass.txt"));
	//inclusive 33-126 = 94
	int depth = 3;
	depth = depth + 1;
	StringBuffer pass = new StringBuffer (depth);
	String chars = "";
	for (int i = 33 ; i < 127 ; i++)
	{
	    chars += String.valueOf ((char) i);
	}
	pass.append (chars.charAt (0));
	while (pass.length () < depth)
	{
	    out.println (pass.toString ());

	    pass.setCharAt (0, (char) (pass.charAt (0) + 1));
	    if (pass.charAt (0) == (chars.charAt (chars.length () - 1) + 1))
	    {
		{

		    out.println ("______________________________________________________");
		    for (int i = 1 ; i < pass.length () ; i++)
		    {
			if (pass.charAt (i - 1) == (chars.charAt (chars.length () - 1) + 1))
			{
			    if (i == pass.length ())
			    {
				pass.append (chars.charAt (0));
			    }
			    else
			    {
				pass.setCharAt (i, (char) (pass.charAt (i) + 1));
			    }
			}
			else
			{
			    break;
			}

		    }
		    pass.setCharAt (0, chars.charAt (0));

		}


	    }
	}
	out.close ();
    }
} // main method
// Password class


