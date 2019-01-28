import java.io.*;
import java.net.*;
import java.awt.*;
import java.lang.Object;
public class Server
{ //private static Socket clientList [] = new Socket [64];
    private static int port = 8190, maxClients = 64, id = 0, ba = 0;
    private static ChatHandler [] Clients = new ChatHandler [maxClients];
    private static String password;
    private static final char True = 0, False = 1, PASSWORD = 2, NAME = 3, EXIT = 4, BROADCAST = 5,CLIENT = 6;
    private static InetAddress band [];
    private static String words [] = {"fuck", "dick", "kock", "up yours", "prick", "wanker"};
    Server ()
    {
	new Open ().start ();
    }


    Server (int prt)
    {
	port = prt;
	new Open ().start ();
    }


    Server (int prt, String pass)
    {
	port = prt;
	password = pass.trim ();
	new Open ().start ();
    }


    private class Open extends Thread
    {
	public void run ()
	{
	    try
	    {
		ServerSocket listener = new ServerSocket (port);
		System.out.println ("The Chat Server is running on port " + port);
		while (true)
		{
		    Socket client = listener.accept ();
		    Clients [id] = new ChatHandler (client);
		    Clients [id].start ();
		    System.out.println ("Connection from " + client.getInetAddress ().getLocalHost ().getHostName () + " on port " + client.getPort ());
		    id++;
		}
	    }
	    catch (Exception e)
	    {
		System.out.println ("Chatter error: " + e);
	    }
	}
    }


    private static boolean Password (PrintWriter out, BufferedReader in)
    {
	boolean pass = true;
	if (password != null)
	{
	    try
	    {
		for (int c = 0 ; c < 3 ; c++)
		{
		    out.println (PASSWORD);
		    if (in.readLine ().trim ().equals (password))
		    {
			pass = true;
			break;
		    }
		    else
		    {
			pass = false;
		    }
		}
	    }
	    catch (Exception e)
	    {

	    }
	}
	return pass;
    }


    private static boolean Banded (InetAddress client)
    {
	boolean OK = false;
	try
	{
	    for (int i = 0 ; i < ba ; i++)
	    {
		if ((band [i].getLocalHost ().getHostName () == client.getLocalHost ().getHostName ()) & (band [i].getLocalHost ().getHostAddress () == client.getLocalHost ().getHostAddress ()))
		{
		    OK = true;
		    break;
		}


	    }
	}
	catch (Exception e)
	{

	}
	return OK;
    }


    private static void Band (InetAddress client)
    {
    }


    /*static synchronized void broadcast (String message, String name)
	throws IOException
    {
	// Sends the message to every client including the sender.
	Socket s;
	PrintWriter p;
	for (int c = 0 ; c < id ; c++)
	{

	    s = (Socket) clientList [c];
	    p = new PrintWriter (s.getOutputStream (), true);
	    p.println (name + ": " + message);
	}
    }*/


    static synchronized void broadcast (String message) throws IOException
    {
	for (int c = 0 ; c < id ; c++)
	{
	    Clients [c].out.println (message);
	}
    }


    static synchronized void remove (Socket Client)
    {
	for (int c = 0 ; c < id ; c++)
	{
	    if (Clients [c].toClient == Client)
	    {
	    
		for (int ac = c ; ac < maxClients - 1 ; ac++)
		{
		    Clients [ac] = Clients [ac + 1];
		}
		id--;
	    }
	}
    }


    static String language (String val)
    {
	String hashes = "";
	for (int i = 0 ; i < 6 ; i++)
	{
	    hashes = "";
	    for (int h = 0 ; h < words [i].length () ; h++)
	    {
		hashes = hashes + "#";
	    }

	    val = replace (words [i], hashes, val);
	}
	return val;
    }


    static String replace (String what, String with, String str)
    {
	String search = str.toLowerCase ();
	int first = 0, end = 0;
	for (int i = 0 ; i < str.length () ;)
	{
	    first = search.indexOf (what, first);
	    if (first == -1)
	    {
		break;
	    }
	    end = first + what.length ();
	    str = str.substring (0, first) + with + str.substring (end, str.length ());
	    search = str.toLowerCase();
	    first = end;
	}


	return str;
    }


    private class ChatHandler extends Thread
    {
	PrintWriter out;
	BufferedReader in;
	Socket toClient;
	private String spam;
	private int times, command;
	private String name, inValue;
	private boolean run = true;
	ChatHandler (Socket s)
	{
	    toClient = s;
	    times = 0;
	}


	public void run ()
	{
	    try
	    {
		out = new PrintWriter (toClient.getOutputStream (), true);
		in = new BufferedReader (new InputStreamReader (toClient.getInputStream ()));
		if (Server.Banded (toClient.getInetAddress ()) == false & Password (out, in) == true)
		{
		    out.println (NAME);
		}
		else
		{
		    Server.remove (toClient);
		    toClient.close ();
		}

		while (run)
		{
		    inValue = in.readLine ().trim ();
		    if (inValue.length () > 0)
		    {
			command = (int) inValue.charAt (0);
			inValue = inValue.substring (1);
			/* if (inValue.equals (spam))
			 {
			     times++;
			     Server.broadcast ("SPAMMING WILL NOT BE TOLERATED BY USERS!\n  Violation by: " + name + " for the " + times + "/6");
			     if (times > 5)
			     {
				 Server.broadcast ("\n" + name + ", has left the Chatting session!");
				 break;
			     }
			 }
			 else
			 {
			     times = 0;
			 }
			 if (inValue.length () > 3)
			 {
			     if ((inValue.substring (0, 4).toLowerCase ()).equals ("exit"))
			     {
				 System.out.println ("Client Droped " + toClient.getInetAddress () + " on client's port " + toClient.getPort ());
				 Server.broadcast ("\n" + name + ", has left the Chatting session!");
				 break;
			     }
			 }
			 System.out.println (inValue);
			 spam = inValue;*/
			switch (command)
			{
			    case BROADCAST:
				inValue = Server.language (inValue);
				Server.broadcast (name + ": " + inValue);
				break;

			    case EXIT:
				System.out.println ("Client Droped, " + name + " from " + toClient.getInetAddress ().getLocalHost () + " on client's port " + toClient.getPort ());
				Server.broadcast (name + ", has left the Chatting session!");
				Server.remove (toClient);
				toClient.close ();
				run = false;
				break;

			    case NAME:
				if (!inValue.equals ("") & inValue.length () > 5 & inValue.length () < 15)
				{
				    name = inValue;
				    System.out.println ("New client, " + name + " from " + toClient.getInetAddress ().getLocalHost () + " on client's port " + toClient.getPort ());
				    out.println ("*** Welcome to the Chatter ***");
				    Server.broadcast (name + ", has joined the Chatting session!");
				    break;
				}
				break;

			    default:
				break;
			}

		    }
		}
		//Server.remove (toClient);
		//toClient.close ();
	    }
	    catch (Exception e)
	    {
		try
		{
		    System.out.println ("Client Droped " + toClient.getInetAddress () + " on client's port " + toClient.getPort ());
		    Server.broadcast ("\n" + name + ", has left the Chatting session!");
		    Server.remove (toClient);
		    toClient.close ();
		}
		catch (Exception d)
		{
		    System.out.println ("Chatter error: " + d);
		}
	    }

	}
    }
}





/*class ChatHandler extends Thread
{

    private BufferedReader in;
    private PrintWriter out;
    private Socket toClient;
    private String name;
    ChatHandler (Socket s)
    {
	toClient = s;
    }


    public void run ()
    {
	try
	{

	    in = new BufferedReader (new InputStreamReader (
			toClient.getInputStream ()));
	    out = new PrintWriter (toClient.getOutputStream (), true);
	    out.println ("*** Welcome to the Chatter ***");
	    out.println ("Type BYE to end");
	    out.print ("What is your name? ");
	    out.flush ();
	    String name = in.readLine ();
	    Server.broadcast (name + " has joined the discussion.",
		    "Chatter");
	    // Read lines and send them off for broadcasting.
	    while (true)
	    {
		String s = in.readLine ().trim ();
		//Check first three characters for BYE.
		//Avoids problems with different line end characters.
		if (s.length () > 2 & s.charAt (0) == 'B' &
			s.charAt (1) == 'Y' && s.charAt (2) == 'E')
		{
		    Server.broadcast (name + " has left the discussion.",
			    "Chatter");
		    break;
		}
		Server.broadcast (s, name);
	    }
	    Server.remove (toClient);
	    toClient.close ();
	}
	catch (Exception e)
	{
	    System.out.println ("Chatter error: " + e);
	}
    }
}*/
