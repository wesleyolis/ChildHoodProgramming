import java.io.*;
public class PrintTelList
{
    public static void main (String [] args)
    {
	new Input ();
    }
}


class Input
{
    BufferedReader in;
    BufferedWriter out;

    Input ()
    {
	in = new BufferedReader (new InputStreamReader (System.in));

	try
	{
	    out = new BufferedWriter (new FileWriter ("Friends.dat"));


	    while (true)
	    {
		System.out.println ("Please Enter the following inforamtion");
		System.out.println ("Name ");
		out.write (readLine (30));
		out.newLine ();
		System.out.println ("Tel ");
		out.write (readTel ());
		out.newLine ();
		System.out.println ("Do You want to Contine N for no");
		if (readLine ().equalsIgnoreCase ("N"))
		{
		    break;
		}
	    }
	    out.close ();
	    BufferedReader in = new BufferedReader (new FileReader ("Friends.dat"));
	    BufferedWriter out = new BufferedWriter (new FileWriter ("Friends tels.txt"));
	    out.write ("Name                            Tel");
	    out.newLine ();
	    out.write ("---------------------------------------");
	    out.newLine ();
	    String temp = in.readLine ();
	    while (temp != null)
	    {
		out.write (format (temp, 32));
		out.write (format (in.readLine (), 7));
		out.newLine ();
		temp = in.readLine ();
	    }
	    out.close ();



	    private String format (String str, int len)
	    {
		for (int i = str.length () ; i < len ; i++)
		{
		    str += " ";
		}
		return str;
	    }
	}
	catch (IOException e)
	{
	    System.out.println ("Eoor on File erorr: " + e);
	}



    }


    String readTel ()
    {
	boolean ok = false;
	String tel = "";
	while (!ok)
	{
	    ok = true;
	    tel = readLine (7);
	    for (int i = 0 ; i < tel.length () ; i++)
	    {
		if (!Character.isDigit (tel.charAt (i)))
		{
		    ok = false;
		}
	    }
	}
	return tel;
    }


    String readLine (int max)
    {
	int len = max + 1;
	String str = "";
	while (len > max)
	{
	    str = readLine ();
	    len = str.length ();
	    if (len > max)
	    {
		System.out.println ("Enter a short string please limit is " + max);
	    }
	}
	return str;
    }


    String readLine ()
    {
	String i = null;
	while (i == null)
	{
	    System.out.print (">> ");
	    try
	    {
		i = in.readLine ().trim ();
	    }
	    catch (IOException e)
	    {
		i = null;
	    }
	    if (i.length () == 0)
	    {
		i = null;
	    }
	}
	return i;
    }
}
