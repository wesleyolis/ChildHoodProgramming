// The "Server" class.
import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;
public class Server extends Applet
{
    private static Socket clientList [] = new Socket [64];
    private static String Names [] = new String [64];
    private static int id = 0;

    public void init ()
    {
	int port = 8190;
	//if (args.length > 0)
	// port = Integer.parseInt (args [0]);
	try
	{
	    ServerSocket listener = new ServerSocket (port);
	    //System.out.println ("The Chat Server is running on port " + port);
	    while (true)
	    {
		Socket client = listener.accept ();
		//System.out.println ("New client from " + listener.getInetAddress () +
		//" on client's port " + client.getPort ());
		new ChatHandler1 (client, id).start ();
		clientList [id] = client;
		id++;
	    }
	}
	catch (Exception e)
	{
	    //System.out.println ("Chatter error: " + e);
	}
    }


    static synchronized void broadcast (String message)
	throws IOException
    {
	// Sends the message to every client including the sender.
	Socket s;
	PrintWriter p;
	for (int c = 0 ; c < id ; c++)
	{

	    s = (Socket) clientList [c];
	    p = new PrintWriter (s.getOutputStream (), true);
	    p.println (Names [c] + ": " + message);
	}
    }


    static synchronized void remove (Socket s)
    {
	/* Finds the client on the list (by comparing socket
	* references) and removes it.
	*/
	Socket clientList2 [] = new Socket [64];
	String Names2 [] = new String [64];
	Socket t;
	int c2 = 0;
	for (int c = 0 ; c < id ; c++)
	{
	    t = (Socket) clientList [c];
	    if (t.equals (s))
	    {
		for (int r = (c + 1) ; r < id ; r++)
		{
		    clientList2 [c2] = clientList [r];
		    Names2 [c2] = Names [r];
		    c2++;
		}
		break;

	    }
	    clientList2 [c2] = clientList [c];
	    Names2 [c2] = Names [c];
	    c2++;
	}

	for (int c = 0 ; c < 64 ; c++)
	{
	    clientList [c] = clientList2 [c];
	    Names2 [c2] = Names [c];
	}
	id--;
    }


    class ChatHandler1 extends Thread
    {
	/* The Chat Handler class is called from the Chat Server:
	* one thread for each client coming in to chat.
	*/
	private BufferedReader in;
	private PrintWriter out;
	private Socket toClient;
	private String name;

	ChatHandler1 (Socket s, int id)
	{
	    toClient = s;

	}


	public void run ()
	{
	    try
	    {
		/* Create i-o streams through the socket we were
		* given when the thread was instantiated
		* and welcome the new client.
		*/
		out = new PrintWriter (toClient.getOutputStream (), true);
		in = new BufferedReader (new InputStreamReader (toClient.getInputStream ()));
		out.println ("*** Welcome to the Chatter 1***");
		out.println ("*** Welcome to the Chatter 1***");
		out.println ("*** Welcome to the Chatter 1***");
		out.println ("*** Welcome to the Chatter 1***");
		out.flush ();
		//  for (int c = 0 ; c < Server.id ; c++)
		//{
		//  Names [id] = in.readLine ();
		//}


		// for (int c = 0 ; c < id ; c++)
		//{
		//    out.println (String.valueOf ((char) 254) + Names [id]);
		//}
		

		//Server.broadcast (" has joined the discussion");
		// Read lines and send them off for broadcasting.
		while (true)
		{
		    String s = in.readLine ();
		    //Check first three characters for BYE.
		    //Avoids problems with different line end characters.
		    if (s.length () > 2 & s.charAt (0) == 'B' &
			    s.charAt (1) == 'Y' && s.charAt (2) == 'E')
		    {
			//  Server.broadcast (" has left the discussion.");
			break;
		    }
		    Server.broadcast (s);
		}
		Server.remove (toClient);
		toClient.close ();
	    }
	    catch (Exception e)
	    {
		// System.out.println ("Chatter error: " + e);
	    }
	}
    }


    public void paint (Graphics g)
    {

    }
}
