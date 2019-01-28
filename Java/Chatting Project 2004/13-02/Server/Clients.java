import java.awt.*;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;
public class Clients
{
    private static Client start = null, pre = null, curr = null;
    private static int max = 0, count = 0;

    Clients ()
    {
    }


    Clients (int maxclients)
    {
	max = maxclients + 1;
    }


    synchronized public static void add (Socket s)
    {
	reset ();
	pre = curr;
	if (start == null)
	{
	    start = new Client (s);
	    curr = start;
	}
	else
	{

	    Client temp = new Client (s);
	    temp.sub = curr.sub;
	    curr.sub = temp;
	    curr = temp;
	}
	count++;
    }


    synchronized public static void remove (Socket s, String reason)
    {
	select (s);
	remove (reason);
    }


    synchronized public static void remove (String reason)
    {
	if (start != null || curr != null)
	{
	    System.out.println ("Droped Client " + curr.Hlink.soc.getInetAddress () + " from server\n>>Reason: " + reason);
	    curr.Hlink.exit ();
	    count--;
	    if (pre == null)
	    {
		start = curr.sub;
	    }
	    else
	    {
		pre.sub = curr.sub;
		curr = curr.sub;
	    }

	}
    }


    synchronized public static void next ()
    {
	pre = curr;
	curr = curr.sub;
    }


    synchronized public static void select (Socket s)
    {
	for (reset () ; iscurr () ; next ())
	{
	    if (curr.Hlink.soc == s)
	    {
		break;
	    }
	}

    }


    synchronized public static void reset ()
    {
	curr = start;
	pre = null;
    }


    synchronized public static boolean isnext ()
    {
	return curr.sub != null;
    }


    synchronized public static boolean iscurr ()
    {

	return curr != null;
    }


    private static class Client
    {
	Client sub;
	Handler Hlink;
	Client (Socket s)
	{

	    Hlink = new Handler (s);
	    Hlink.start ();

	}
    }


    public static synchronized void broadcast (String message)
    {
	for (reset () ; iscurr () ; next ())
	{
	    if (curr.Hlink.connected == true)
	    {
		curr.Hlink.out.println (message);
	    }
	}

    }


    String [] userslist ()
    {
	String [] Users = new String [count];
	int pos = 0;
	for (reset () ; iscurr () ; next (), pos++)
	{
	    if (curr.Hlink.connected == true)
	    {
		Users [pos] = new String ("\n>> " + curr.Hlink.name);
	    }
	}
	return Users;
    }


    private static class Handler extends Thread
    {
	//Users data
	boolean gender;
	String userID = "";
	int age;
	Image pic;
	Date signon;
	String name;

	//program data
	boolean connected = false, running = true;
	Socket soc;
	BufferedReader in;
	PrintWriter out;
	Handler (Socket s)
	{
	    soc = s;
	}


	public void run ()
	{
	    init (); //innitializes client needs - further down
	    try
	    {
		while (running)
		{
		    String data = in.readLine ().trim ();
		    if (data.length () > 2)
			if (data.charAt (0) == 'B' & data.charAt (1) == 'Y' & data.charAt (2) == 'E')
			{
			    broadcast (name + " has left the discussion.");
			    remove (soc, "Client Exited");
			}
		    broadcast (name + ", " + data);
		}
	    }
	    catch (Exception e)
	    {
		broadcast (name + " has left the discussion.");
		remove (soc, "Client Exited");
	    }

	}

	void exit ()
	{
	    running = false;
	    try
	    {
		soc.close ();
	    }
	    catch (Exception e)
	    {
		System.out.println ("Error on exit client: " + e);
	    }

	}

	void init ()  //initializing methods
	{

	    if (setupIO () & maxCount () & verifyUser () & userProfile ())
	    { //comunications medium
		//Registered User and password check
		//Loads the users profile of all his criteria

		try
		{
		    out.println ("*** Welcome to the Chatter ***");
		    out.println ("Type BYE to end");
		    out.println ("What is your name?");
		    name = in.readLine ().trim ();
		    // Read lines and send them off for broadcasting.
		}
		catch (Exception e)
		{
		    System.out.println ("Error on client data input");
		}

		connected = true; //tells the list of clients that the client is setup finshed initailizing
		broadcast (name + " has joined the discussion");
	    }

	}


	boolean setupIO ()  //Setups up the comunications medium!
	{
	    boolean ok = false;
	    for (int trys = 1 ; trys < 4 ; trys++)
	    {
		try
		{
		    in = new BufferedReader (new InputStreamReader (soc.getInputStream ()));
		    out = new PrintWriter (soc.getOutputStream (), true);
		    trys = 4;
		    ok = true;
		}
		catch (Exception e)
		{
		    remove (soc, "Error on: initailizing of comunication buffers failed!");
		}
	    }
	    return ok;
	}

	boolean maxCount ()  //checks to see weather there are to many clients if drops him
	{
	    boolean ok = true;
	    if (max != 0)
	    {
		if (count >= max)
		{
		    ok = false;
		    out.println ("Server is Full can not accept anymore clients!");
		    remove (soc, "Server is Full");
		}
	    }
	    return ok;
	}

	boolean verifyUser ()
	{

	    /*for (int p = 0 ; p < 3 ; p++)
	    {
		out.println ("");

	    }*/
	    return true;

	}


	boolean userProfile ()
	{
	    return true;
	}
    }
}


/*
private class ChatHandler extends Thread
{
    PrintWriter out;
    BufferedReader in;
    Socket toClient;
    String name;
    private String spam;
    private int times, command;
    private String inValue;
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
		Clients (out, id);
		while (true)
		{
		    out.println (NAME);
		    inValue = in.readLine ().trim ();
		    if (inValue.length () > 0)
		    {
			if (!inValue.equals ("") & inValue.length () > 5 & inValue.length () < 15)
			{
			    name = inValue;
			    System.out.println ("New client, " + name + " from " + toClient.getInetAddress ().getLocalHost () + " on client's port " + toClient.getPort ());
			    out.println ("*** Welcome to the Chatter ***");
			    Server.broadcast (CLIENT + name);
			    Server.broadcast (name + ", has joined the Chatting session!");
			}
		    }
		}
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
/*
switch(command)
{
caseBROADCAST:
inValue=Server.language(inValue);
Server.broadcast(name+":"+inValue);
break;

caseEXIT:
System.out.println("ClientDroped,"+name+"from"+toClient.getInetAddress().getLocalHost()+"onclient'sport"+toClient.getPort());
Server.broadcast(name+",haslefttheChattingsession!");
Server.remove(toClient);
toClient.close();
run=false;
break;

caseNAME:
if(!inValue.equals("")&inValue.length()>5&inValue.length()<15)
{
System.out.println("client,"+name+"changenameto,"+inValue+"from"+toClient.getInetAddress().getLocalHost()+"onclient'sport" + toClient.getPort ());
Server.broadcast(name+",haschangenameto,"+inValue);
name=inValue;
}


break;

default:
break;
}

}
}
//Server.remove(toClient);
//toClient.close();
}
catch(Exceptione)
{
try
{
System.out.println("ClientDroped"+toClient.getInetAddress()+"onclient'sport"+toClient.getPort());
Server.broadcast("\n"+name+",haslefttheChattingsession!");
Server.remove(toClient);
toClient.close();
}


catch(Exceptiond)
{
System.out.println("Chattererror:"+d);
}
}

}
}
*/
/*
public static void remove (Socket s)
{
    if (start != null || curr != null)
    {
	if (pre == null)
	{
	    start = curr.sub;
	}
	else
	{
	    pre.sub = curr.sub;
	    curr = curr.sub;
	}
    }
}


public void reset ()
{
    curr = start;
    pre = null;
}


private static class Client
{
    Client sub;
    Client (Socket s)
    {
	Handler Hlink = new Handler (s);
	Hlink.start ();
    }
}


private static class Handler extends Thread
{
    //Users data
    boolean gender;
    String userID = "";
    int age;
    Image pic;
    Date signon;

    //program data
    boolean connected = false;
    Socket soc;
    BufferedReader in;
    PrintWriter out;
    Handler (Socket s)
    {
	soc = s;
    }


    public void run ()
    {
	init ();
	while (true)
	{

	}
    }


    void init ()  //initializing methods
    {

	if (setupIO () & verifyUser () & userProfile ())
	{ //comunications medium
	    //Registered User and password check
	    //Loads the users profile of all his criteria
	    connected = true;
	}
	else
	{
	    try
	    {
		soc.close ();
	    }
	    catch (Exception e)
	    {

	    }
	    remove (soc);
	}
    }


    boolean setupIO ()
    {
	boolean ok = false;
	for (int trys = 1 ; trys < 4 ; trys++)
	{
	    try
	    {
		in = new BufferedReader (new InputStreamReader (soc.getInputStream ()));
		out = new PrintWriter (soc.getOutputStream (), true);
		trys = 4;
		ok = true;
	    }
	    catch (Exception e)
	    {
		System.out.println ("Error on: initailizing of comunication buffers failed! attempt " + trys + "/3");
		if (trys == 3)
		{
		    System.out.println ("Client " + soc.getInetAddress () + "is being droped"); //teminate client
		}
	    }
	}
	return ok;
    }


    boolean verifyUser ()
    {
	/*for (int p = 0 ; p < 3 ; p++)
	{
	    out.println ("");

	}*/
/*
returntrue;

}


booleanuserProfile()
{
returntrue;
}
}




/*
privateclassChatHandlerextendsThread
{
PrintWriterout;
BufferedReaderin;
SockettoClient;
Stringname;
privateStringspam;
privateinttimes,command;
privateStringinValue;
privatebooleanrun=true;
ChatHandler(Sockets)
{
toClient=s;
times=0;
}


publicvoidrun()
{
try
{
 out = new PrintWriter (toClient.getOutputStream (), true);
 in = new BufferedReader (new InputStreamReader (toClient.getInputStream ()));
 if (Server.Banded (toClient.getInetAddress ()) == false & Password (out, in) == true)
 {
     Clients (out, id);
     while (true)
     {
	 out.println (NAME);
	 inValue = in.readLine ().trim ();
	 if (inValue.length () > 0)
	 {
	     if (!inValue.equals ("") & inValue.length () > 5 & inValue.length () < 15)
	     {
		 name = inValue;
		 System.out.println ("New client, " + name + " from " + toClient.getInetAddress ().getLocalHost () + " on client's port " + toClient.getPort ());
		 out.println ("*** Welcome to the Chatter ***");
		 Server.broadcast (CLIENT + name);
		 Server.broadcast (name + ", has joined the Chatting session!");
	     }
	 }
     }
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
/*
switch(command)
{
caseBROADCAST:
inValue=Server.language(inValue);
Server.broadcast(name+":"+inValue);
break;

caseEXIT:
System.out.println("ClientDroped,"+name+"from"+toClient.getInetAddress().getLocalHost()+"onclient'sport"+toClient.getPort());
Server.broadcast(name+",haslefttheChattingsession!");
Server.remove(toClient);
toClient.close();
run=false;
break;

caseNAME:
if(!inValue.equals("")&inValue.length()>5&inValue.length()<15)
{
System.out.println("client,"+name+"changenameto,"+inValue+"from"+toClient.getInetAddress().getLocalHost()+"onclient'sport" + toClient.getPort ());
Server.broadcast(name+",haschangenameto,"+inValue);
name=inValue;
}


break;

default:
break;
}

}
}
//Server.remove(toClient);
//toClient.close();
}
catch(Exceptione)
{
try
{
System.out.println("ClientDroped"+toClient.getInetAddress()+"onclient'sport"+toClient.getPort());
Server.broadcast("\n"+name+",haslefttheChattingsession!");
Server.remove(toClient);
toClient.close();
}


catch(Exceptiond)
{
System.out.println("Chattererror:"+d);
}
}

}
}
*/
