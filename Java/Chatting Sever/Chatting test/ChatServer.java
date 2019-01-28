import java.io.*;
import java.net.*;
public class ChatServer
{

    //private static Socket clientList [] = new Socket [64];
    private static int id = 0;
    private static final int maxClients = 64;
    private static ChatHandler [] Clients = new ChatHandler [maxClients];
    public static void main (String [] args) throws IOException
    {
	int port = 8190;
	if (args.length > 0)
	    port = Integer.parseInt (args [0]);
	ServerSocket listener = new ServerSocket (port);
	System.out.println ("The Chat Server is running on port " + port);
	while (true)
	{
	    Socket client = listener.accept ();
	    Clients [id] = new ChatHandler (client);
	    Clients [id].start ();
	    System.out.println ("New client from " + client.getInetAddress () + " on client's port " + client.getPort ());
	    //clientList [id] = client;
	    id++;

	}
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
}


class ChatHandler extends Thread
{
    PrintWriter out;
    private BufferedReader in;
    Socket toClient;
    private String spam;
    private int times;
    private String name, inValue;
    ChatHandler (Socket s)
    {
	toClient = s;
	times = 0;
	try
	{
	    in = new BufferedReader (new InputStreamReader (toClient.getInputStream ()));
	    out = new PrintWriter (toClient.getOutputStream (), true);
	    out.println ("*** Welcome to the Chatter ***");
	    out.println ("Type 'exit' to end");
	    out.println ("What is your name? ");
	    //out.flush ();
	}
	catch (Exception e)
	{
	    System.out.println ("Chatter error: " + e);
	}
    }


    public void run ()
    {
	try
	{
	    name = in.readLine ().trim ();
	    ChatServer.broadcast (name + ", has joined the Chatting session!");
	    while (true)
	    {
		inValue = in.readLine ().trim ();
		if (inValue.equals (spam))
		{
		    times++;
		    ChatServer.broadcast ("SPAMMING WILL NOT BE TOLERATED BY USERS!\n  Violation by: " + name + " for the " + times + "/6");
		    if (times > 5)
		    {
			ChatServer.broadcast ("\n" + name + ", has left the Chatting session!");
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
			ChatServer.broadcast ("\n" + name + ", has left the Chatting session!");
			break;
		    }
		}
		//System.out.println (inValue);
		spam = inValue;
		ChatServer.broadcast (name + ": " + inValue);
	    }
	    ChatServer.remove (toClient);
	    toClient.close ();
	}
	catch (Exception e)
	{
	    try
	    {
		System.out.println ("Client Droped " + toClient.getInetAddress () + " on client's port " + toClient.getPort ());
		ChatServer.broadcast ("\n" + name + ", has left the Chatting session!");
		ChatServer.remove (toClient);
		toClient.close ();
	    }
	    catch (Exception d)
	    {
		System.out.println ("Chatter error: " + d);
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
	    ChatServer.broadcast (name + " has joined the discussion.",
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
		    ChatServer.broadcast (name + " has left the discussion.",
			    "Chatter");
		    break;
		}
		ChatServer.broadcast (s, name);
	    }
	    ChatServer.remove (toClient);
	    toClient.close ();
	}
	catch (Exception e)
	{
	    System.out.println ("Chatter error: " + e);
	}
    }
}*/
