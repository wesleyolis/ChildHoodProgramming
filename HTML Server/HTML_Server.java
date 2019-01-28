// Wesley Oliver
import java.net.*;
import java.io.*;
import java.util.*;
public class HTML_Server
{
    String WebDir = "";
    int sleep = 0, bufsize = 512;
    String[] index_names = {"index.htm", "index.html"};
    public HTML_Server (int port, String Dir, int buf, int sl)
    {
	WebDir = Dir;
	bufsize = buf;
	sleep = sl;
	Listener l = new Listener (port);
	l.start ();
    }


    private class Listener extends Thread
    {
	ServerSocket listener;
	int port = 80;
	Listener (int p)
	{
	    port = p;
	}

	public void run ()
	{
	    try
	    {
		System.out.println ("Starting HTML Server");
		listener = new ServerSocket (port);
		System.out.println ("HTML Server is runing on port " + port);

		while (true)
		{
		    try
		    {
			Socket c = listener.accept ();
			// System.out.println ("Connection from " + c.getInetAddress ().getLocalHost ().getHostName () + " on port " + c.getPort ());
			new client (c).start ();
		    }
		    catch (Exception e)
		    {
			System.out.println ("Error on client connect " + e);
		    }
		}
	    }
	    catch (Exception e)
	    {
		System.out.println ("Error Hosting HTML Server: " + e);
	    }
	}

	void close ()
	{
	    while (true)
	    {
		try
		{
		    listener.close ();
		    System.out.println ("HTML Listener Closed");
		}
		catch (Exception e)
		{
		    System.out.println ("Error on closing HTML Listener\n trying again..");
		}
	    }
	}
    }


    private class client extends Thread
    {
	DataOutputStream out;
	BufferedReader in;
	Socket c;
	client (Socket t)
	{
	    c = t;
	}

	public void run ()
	{
	    //initialize all the streams
	    try
	    {
		in = new BufferedReader (new InputStreamReader (c.getInputStream ()));
		out = new DataOutputStream (c.getOutputStream ());
	    }
	    catch (Exception e)
	    {
		quit ();
		System.out.println ("Error on initializing client Buffers\nClient has been droped.." + e);
	    }

	    //runs after clients every foot step
	    try
	    {
		String c = in.readLine ();
		System.out.println (c);
		StringTokenizer st = new StringTokenizer (c);
		String com = st.nextToken ().trim ();
		String para = st.nextToken ().trim ();
		if (com.equalsIgnoreCase ("GET"))
		{
		    get (para);
		}
		else
		{
		    post (para);
		}
		quit ();

	    }
	    catch (IOException e)
	    {

	    }
	}
	void post (String para) throws IOException
	{
	    String i = in.readLine ();
	    while (i != null)
	    {
		System.out.println (i);
		i = in.readLine ();
	    }

	}

	void get (String para) throws IOException
	{
	    datafile (para);
	}


	void datafile (String f) throws IOException
	{
	    DataInputStream fin = null;
	    try
	    {
		fin = new DataInputStream (new FileInputStream (WebDir + f));
	    }
	    catch (FileNotFoundException e)
	    {

		for (int i = 0 ; i < index_names.length ; i++)
		{
		    try
		    {
			fin = new DataInputStream (new FileInputStream (WebDir + "/" + index_names [i]));
		    }
		    catch (FileNotFoundException n)
		    {
		    }
		}
	    }

	    while (fin.available () > 0)
	    {
		byte[] buf = new byte [bufsize];

		fin.read (buf);
		out.write (buf, 0, buf.length);
		try
		{
		    Thread.sleep (sleep);
		}
		catch (Exception e)
		{

		}
	    }
	    fin.close();
	}


	File getFile (String f) throws FileNotFoundException
	{
	    File file = null;
	    for (int i = 0 ; i < index_names.length ; i++)
	    {
		file = new File (WebDir + f);
	    }
	    return file;
	}


	void quit ()  //closes all streams to and form client and socket
	{
	    try
	    {
		in.close ();
	    }
	    catch (Exception e)
	    {
	    }
	    try
	    {
		out.close ();
	    }
	    catch (Exception e)
	    {
	    }
	    try
	    {
		c.close ();
	    }
	    catch (Exception e)
	    {
	    }
	    // System.out.println ("Client closed");
	}
    }
}

