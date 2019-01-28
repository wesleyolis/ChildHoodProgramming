import java.io.*;
import java.net.*;
class Ports
{
    public static void main (String [] args)
    {
	String computer;
	int port;
	if (args.length > 0)
	    computer = args [0];
	else
	    computer = "Wesley";
	if (args.length > 1)
	    port = Integer.parseInt (args [1]);
	else
	    port = 8190;      // the clock port
	System.out.println ("Accessing port " + port + " on " + computer + " \n ");
	try
	{
	    // Create a socket to the given port.
	    // Set up an input stream to read what the socket on that
	    // side provides.
	    Socket t = new Socket (computer, port);
	    BufferedReader in = new BufferedReader (new InputStreamReader (t.getInputStream ()));
	    PrintWriter out = new PrintWriter (t.getOutputStream (), true);
	    
	    // Read from the server until readLine
	    // returns no more data
	    while (true)
	    {
		String s = in.readLine ();
		if (s == null)
		{
		    break;
		}
		else
		{
		    System.out.println (s);
		}
		System.out.println("1");
		out.println ("*** Wel ***");
	    }
	}
	catch (IOException e)
	{
	    System.out.println ("Error" + e);
	}
    }
}
