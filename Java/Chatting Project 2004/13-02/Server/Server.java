import java.io.*;
import java.net.*;
import java.awt.*;
import java.lang.Object;
public class Server
{
    private final int port;
    private static String password;
    private static int id = 0;
    private static Clients clients;

    Server (int maxcl)
    {
	clients = new Clients (maxcl);
	port = 8190;
	new listener ().start ();
    }


    Server (int prt, int maxcl)
    {
	clients = new Clients (maxcl);
	port = prt;
	new listener ().start ();
    }


    Server (int prt, String pass, int maxcl)
    {
	clients = new Clients (maxcl);
	port = prt;
	password = pass.trim ();
	new listener ().start ();
    }


    private class listener extends Thread
    {
	ServerSocket listsening;
	Socket client;
	BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
	public void run ()
	{
	    try
	    {
		listsening.setSoTimeout (3000);
	    }
	    catch (Exception e)
	    {
		System.out.println ("Error: failed to set server connecting timeout");
	    }

	    try
	    {
		System.out.println ("System is trying to.. initialize Server on port " + port);
		listsening = new ServerSocket (port);
	    }
	    catch (Exception e)
	    {
		System.out.println ("Error on: Initializing Server port!\n>>Port is unavalible or currently in use!\nTry a nother port!");
		System.out.println ("Press Any key to continue..");
		try
		{
		    in.readLine ();
		}
		catch (Exception r)
		{
		    System.exit (0);
		}
		System.exit (0);

	    }
	    System.out.println ("Server is listening for connections, attempting to connect on port " + port);
	    while (true)
	    {
		try
		{
		    client = listsening.accept ();
		    System.out.println ("Attempting to connect client");
		    System.out.println ("Client Accepted " + client.getInetAddress ().getLocalHost () + " on port " + client.getPort ());
		}
		catch (Exception e)
		{
		    System.out.println ("Error on: Attempting to connect client failed!");
		}
		clients.add (client); //add client
	    }

	}
    }
}
