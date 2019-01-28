import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.net.*;
public class ClientSever extends Frame implements ActionListener
{
    private Button send, connect;
    boolean connection = false;
    //private Choice speed, player;
    private TextField sms, hostName, portName, name;
    private List clientList;
    private static TextArea diag;
    private Socket sever;
    static PrintWriter out;
    private BufferedReader in;
    //String computer;
    //int port;
    public ClientSever ()
    {
	setTitle ("Client Chatting");
	setBackground (new Color (248, 185, 143));
	//Message Bar
	Panel south = new Panel ();
	south.add (new Label ("Message"));
	sms = new TextField ("", 90);
	sms.addActionListener (this);
	south.add (sms, "Center");
	send = new Button ("Send");
	send.addActionListener (this);
	south.add (send);
	add ("South", south);
	//Message Bar End

	Panel center = new Panel ();
	diag = new TextArea ("", 31, 90);
	center.add (diag, "Center");
	//host Bar
	center.add (new Label ("Name"));
	name = new TextField ("Jo Mack", 20);
	center.add (name);
	center.add (new Label ("Host Name"));
	hostName = new TextField ("Wesley", 15);
	center.add (hostName);
	center.add (new Label ("Port"));
	portName = new TextField ("8190", 4);
	center.add (portName);
	connect = new Button ("Connect");
	connect.addActionListener (this);
	center.add (connect);
	//host Bar End
	add ("Center", center);
	//Right info Coloum
	Panel East = new Panel ();
	clientList = new List (10, false);
	East.add (clientList);
	add ("East", East);
    }


    private void connect ()
    {
	new connection ().start ();
    }


    private class connection extends Thread
    {
	public void run ()
	{
	    try
	    {
		diag.append ("Connecting\n");
		sever = new Socket (hostName.getText (), Integer.parseInt (portName.getText ()));
		out = new PrintWriter (sever.getOutputStream (), true);
		in = new BufferedReader (new InputStreamReader (sever.getInputStream ()));
		send (name.getText ());
		while (true)
		{
		    String s = in.readLine ().trim ();
		    if (s.charAt (0) > 126)
		    {
			if (s.charAt (0) == 254)
			{
			    clientList.add (s.substring (1, s.length ()));
			}
			if (s.charAt (0) == 255)
			{
			    diag.append ("Disconnected from " + hostName.getText () + " on port " + portName.getText ());
			    break;
			}
		    }
		    diag.append (s + "\n");

		}
		in.close ();
		out.close ();
	    }
	    catch (Exception e)
	    {
		diag.append ("Host Not Found!!\n");
	    }
	}
    }


    static synchronized void send (String message)
    {
	try
	{
	    out.println (message);
	}
	catch (Exception d)
	{
	}

    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getSource () == send || e.getSource () == sms)
	{
	    send (sms.getText ());
	    sms.setText ("");
	}
	if (e.getSource () == connect)
	{
	    if (hostName.getText ().length () > 0 & portName.getText ().length () > 0)
	    {
		if (connection == false)
		{
		    connect ();
		}
		else
		{
		    send (String.valueOf ((char) 255));
		}
	    }
	}
    }


    public static void main (String [] args) throws IOException
    {

	Frame f = new ClientSever ();
	f.addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent e)
	    {
		System.exit (0);
	    }
	}
	);
	f.setSize (800, 600);
	f.setVisible (true);
    }
}


