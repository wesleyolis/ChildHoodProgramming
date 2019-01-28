//Old Methods


/*class clientslist
{
    private Client start = null, curr = null, prev = null;

    public void add (Socket s, Client c)
    {
	prev = curr;
	if (start == null)
	{
	    start = new Client (s, null);
	    curr = start;
	}
	else
	{
	    Client temp = new Client (s, curr.link);
	    curr.link = temp;
	    curr = temp;
	}
    }


    public void remove ()
    {
	if (isempty () || eol ())
	{
	    return;
	}
	else
	{
	    if (prev == null)
	    {
		start = curr.link;
	    }
	    else
	    {
		prev.link = curr.link;
		curr = curr.link;
	    }
	}
    }


    public boolean isempty ()
    {
	return start == null;
    }


    public Object current ()
    {
	return curr.socket;
    }


    public void reset ()
    {
	curr = start;
	prev = null;
    }


    public boolean eol ()
    {
	return curr == null;
    }


    public void succ ()
    {
	curr = curr.link;
	if (prev == null)
	    prev = start;
	else
	    prev = prev.link;
    }


    class Client extends Thread
    {
	String userID;
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	ChatHandler handler;
	Client link;

	Client (Socket s, Client c)
	{
	    socket = s;
	    link = c;
	}

	public void run ()
	{
	    setupIO (); //initialization
	    verifyUser (); //initialization
	    handler = new ChatHandler (s);
	    handler.start ();
	}

	void setupIO ()
	{
	    for (int trys = 1 ; trys < 4 ; trys++)
	    {
		try
		{
		    in = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
		    out = new PrintWriter (socket.getOutputStream (), true);
		}
		catch (Exception e)
		{
		    System.out.println ("Error on: initailizing of comunication buffers failed! attempt " + trys + "/3");
		    if (trys == 3)
		    {
			System.out.println ("Client " + socket.getInetAddress ()); //teminate client
		    }
		}
	    }

	}
	void verifyUser ()
	{
	    for (int p = 0 ; p < 3 ; p++)
	    {
		out.println (PASSWORD);

	    }

	}
    }
}


/*class Client
 {
     private Socket socket;
     private String name;
     private int age;
     private boolean gender;
     private Image picture;
     BufferedReader in;
     PrintWriter out;

     Client (Socket socket)
     {
	 socket = this.socket;
	 for (int trys = 1 ; trys < 4 ; trys++)
	 {
	     try
	     {
		 in = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
		 out = new PrintWriter (socket.getOutputStream (), true);
	     }
	     catch (Exception e)
	     {
		 System.out.println ("Error on: initailizing of comunication buffers failed! attempt " + trys + "/3");
		 if (trys == 3)
		 {
		     System.out.println ("Client " + socket.getInetAddress ());
		 }
	     }
	 }
     }


 }*/
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
	search = str.toLowerCase ();
	first = end;
    }


    return str;
}


static synchronized void Clients (PrintWriter out, int num)
{
    for (int c = 0 ; c < num ; c++)
    {
	if (Clients [c].name != null)
	{
	    Clients [c].out.println (Clients [c].name);
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
