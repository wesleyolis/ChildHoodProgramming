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
    private static ChatHandler[] Clients = new ChatHandler [maxClients];
    ServerSocket listener;
    private static TextArea diag;
    private Button host;
    private TextField portF;
    private ServerRun run;
    public void init ()
    {
    setBackground(new Color(51,153,255));
	add (new Label ("Port"));
	portF = new TextField ("8190", 8);
	add (portF, "North");
	host = new Button ("     Host    ");
	host.addActionListener (this);
	add (host, "North");
	diag = new TextArea ("", 23, 60);
	add (diag, "Center");

    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getSource () == host || e.getSource () == portF)
	{
	    if (host.getLabel () == "     Host    ")
	    {
		//initialize hosting of server
		port = Integer.parseInt (portF.getText ());
		run = new ServerRun ();
		run.start ();
	    }
	    else
	    {
		//terminate hosting of server
		try
		{
		    Chat_Server.broadcast (String.valueOf ((char) 255));
		    run.stop ();
		    listener.close ();
		    diag.append ("\nRemoving Clients..");
		    for (int i = 0 ; i < id ; i++)
		    {

			Clients [i].stop ();
			Clients [i].closeIO ();
			try
			{
			    Clients [i].terminate ();
			}
			catch (Exception g)
			{

			}
			Clients [i] = null;
		    }
		    Clients = new ChatHandler [maxClients];
		    id = 0;
		    diag.append ("\nServer Teminated");
		    host.setLabel ("     Host    ");
		}
		catch (IOException f)
		{
		    diag.append ("\nError teminating sever");
		}
	    }

	}

    }


    static synchronized void broadcast (String message) throws IOException
    {
	for (int c = 0 ; c < id ; c++)
	{
	    try
	    {
		Clients [c].out.println (message);

	    }
	    catch (Exception e)
	    {

	    }
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


    class ServerRun extends Thread
    {
	public void run ()
	{
	    try
	    {
		listener = new ServerSocket (port);
		diag.append ("\nThe Chat Server is running on port: " + port);
		host.setLabel ("Terminate");
		while (true)
		{
		    Socket client = listener.accept ();
		    try
		    {
			Clients [id] = new ChatHandler (client);
			//Clients [id].start ();
			id++;
		    }
		    catch (Exception i)
		    {
			Clients [id] = null;
		    }
		    //Chat_Server.broadcast (String.valueOf ((char) 254) + Clients [id - 1].name + ", has joined the Chatting session!");
		    //clientList [id] = client;
		}
	    }
	    catch (IOException e)
	    {
		diag.append ("\nError Host port in uses try a nother port!!\nIf thus fails please turn down java restrictions\nGo to:>Tools>Internet Options>Sercurity>select internet as the zone\n>Custom Level select the option scripting of javaj Applets\n>Enabled");

	    }
	}
    }


    class ChatHandler extends Thread
    {
	PrintWriter out;
	private BufferedReader in;
	Socket toClient;
	String name, inValue;

	ChatHandler (Socket s) throws Exception
	{
	    toClient = s;
	    //initailize IO Streams
	    try
	    {
		diag.append ("\n" + toClient.getInetAddress () + ": Attempting to Initialize I/O Streams");

		in = new BufferedReader (new InputStreamReader (toClient.getInputStream ()));
		try
		{
		    out = new PrintWriter (toClient.getOutputStream (), true);

		    diag.append ("\n" + toClient.getInetAddress () + ": Initialized and connected on " + toClient.getPort ());

		    out.println ("*** Welcome to the Chatter ***");

		    this.start ();
		}
		catch (Exception e)
		{
		    diag.append ("\n" + toClient.getInetAddress () + ": Error on Initializing Client Output Streams");
		    diag.append ("\n" + toClient.getInetAddress () + ": Closing Opened Client Input Stream");
		    in.close ();
		    terminate ();
		    throw new Exception ("Error Initializeing Client");
		}
	    }
	    catch (Exception e)
	    {
		diag.append ("\n" + toClient.getInetAddress () + ": Error on Initializing  Client Input Streams");
		terminate ();
		throw new Exception ("Error Initializeing Client");
	    }

	}

	public void terminate () throws Exception
	{
	    String address = String.valueOf (toClient.getInetAddress ());
	    try
	    {
		toClient.close ();
	    }
	    catch (IOException e)
	    {
		diag.append ("\n" + address + ":Error Terminating Client");
	    }
	    diag.append ("\n" + address + ": Terminated");

	}

	public void closeIO ()
	{
	    String address = String.valueOf (toClient.getInetAddress ());
	    try
	    {
		in.close ();

		out.close ();

	    }
	    catch (IOException f)
	    {
		diag.append ("\n" + address + ": Error on Closing Client Input Streams");
	    }


	}

	public void run ()
	{
	    try
	    {
		name = in.readLine ().trim ();
		Chat_Server.broadcast (String.valueOf ((char) 254) + name + ", has joined the Chatting session!");

		while (true)
		{
		    inValue = in.readLine ().trim ();
		    if (inValue.length () > 0)
		    {

			switch (inValue.charAt (0))
			{
			    case 253: // Disconnect me the client
				diag.append ("\n" + toClient.getInetAddress () + ": Disconnecting Client on Request");
				closeIO ();
				try
				{
				    terminate ();
				}
				catch (Exception g)
				{

				}
				Chat_Server.remove (toClient);
				break; //disconnects client when stops


			}
			Chat_Server.broadcast (inValue);

		    }

		}


	    }
	    catch (Exception e)
	    {
		closeIO ();
		try
		{
		    terminate ();
		}
		catch (Exception g)
		{

		}
		Chat_Server.remove (toClient);
	    }

	}


    }
}

