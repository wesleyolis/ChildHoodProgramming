import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.lang.*;
import java.awt.event.*;
public class Chat_Server extends Applet implements ActionListener
{
    private static int id = 0;
    public static int port = 8190;
    private static final int maxClients = 64;
    private static ChatHandler [] Clients = new ChatHandler [maxClients];
    ServerSocket listener;
    private static TextArea diag;
    private Button host;
    private TextField portF;
    public void init ()
    {
	add (new Label ("Port"));
	portF = new TextField ("8190", 8);
	add (portF, "North");
	host = new Button ("Host");
	host.addActionListener (this);
	add (host, "North");
	diag = new TextArea ("", 23, 60);
	add (diag, "Center");

    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getSource () == host || e.getSource () == portF)
	{
	    port = Integer.parseInt (portF.getText ());
	    new ServerRun ().start ();

	}

    }


    static synchronized void broadcast (String message) throws IOException
    {
	for (int c = 0 ; c < id ; c++)
	{
	    Clients [c].out.println (message);
	}
    }


    static synchronized void writeserver (String message)
    {

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


    static synchronized void clients (PrintWriter e)
    {
	for (int c = 0 ; c < id ; c++)
	{
	    e.println (Clients [c].name);
	}
    }


    static synchronized void meclient (String client)
    {
       broadcast(client); 
    }


    class ServerRun extends Thread
    {
	public void run ()
	{
	    try
	    {
		listener = new ServerSocket (port);
		diag.append ("\nThe Chat Server is running on port " + port);
		while (true)
		{
		    Socket client = listener.accept ();
		    Clients [id] = new ChatHandler (client);
		    Clients [id].start ();
		    id++;
		    //clientList [id] = client;
		}
	    }
	    catch (Exception e)
	    {
		diag.append ("\nError Host port in uses try a nother port!!\nIf thus fails please turn down java restrictions\nGo to:>Tools>Internet Options>Sercurity>select internet as the zone\n>Custom Level select the option scripting of javaj Applets\n>Enabled");
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
    String name
	private inValue;
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
	    diag.append ("Chatter error: " + e);
	}
    }


    public void run ()
    {
	try
	{
	    name = in.readLine ().trim ();
	    Chat_Server.broadcast (name + ", has joined the Chatting session!");
	    while (true)
	    {
		inValue = in.readLine ().trim ();
		if (inValue.equals (spam))
		{
		    times++;
		    Chat_Server.broadcast ("SPAMMING WILL NOT BE TOLERATED BY USERS!\n  Violation by: " + name + " for the " + times + "/6");
		    if (times > 2)
		    {
			diag.append ("Client Droped " + toClient.getInetAddress () + " on client's port " + toClient.getPort ());
			Chat_Server.broadcast ("\n" + name + ", has left the Chatting session!");
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
			diag.append ("Client Droped " + toClient.getInetAddress () + " on client's port " + toClient.getPort ());
			Chat_Server.broadcast ("\n" + name + ", has left the Chatting session!");
			break;
		    }
		}
		// diag.append (inValue);
		spam = inValue;
		Chat_Server.broadcast (name + ": " + inValue);
	    }
	    Chat_Server.remove (toClient);
	    toClient.close ();
	}
	catch (Exception e)
	{
	    try
	    {
		diag.append ("Client Droped " + toClient.getInetAddress () + " on client's port " + toClient.getPort ());
		Chat_Server.broadcast ("\n" + name + ", has left the Chatting session!");
		Chat_Server.remove (toClient);
		toClient.close ();
	    }
	    catch (Exception d)
	    {
		diag.append ("Chatter error: " + d);
	    }
	}

    }
}

