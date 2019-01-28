import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.lang.*;
import java.io.*;
import java.net.*;
public class ClientSever extends Applet implements ActionListener
{
    private Button send, connect;
    boolean connection = false;
    //private Choice speed, player;
    private TextField sms, hostName, portName, name;
    public static List clientList;
    private static TextArea diag;
    private Socket sever;
    static PrintWriter out;
    private BufferedReader in;
    //String computer;
    //int port;
    public void init ()
    {
	setBackground (new Color (248, 185, 143));
	setLayout (new BorderLayout ());
	//Message Bar
	Panel south = new Panel ();
	south.add (new Label ("Message"));
	sms = new TextField ("", 88);
	sms.addActionListener (this);
	south.add (sms, "Center");
	send = new Button ("Send");
	send.addActionListener (this);
	south.add (send);
	add (south, "South");
	//Message Bar End

	Panel center = new Panel ();
	diag = new TextArea ("", 22, 88);
	center.add (diag, "Center");
	//host Bar
	center.add (new Label ("Name"));
	name = new TextField ("Jo Mack", 20);
	center.add (name);
	center.add (new Label ("Host Name"));
	hostName = new TextField ("Pc-025", 15);
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


    public void destroy ()
    {
	send ("Exit");
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
		diag.append ("Connecting. . .\n");
		sever = new Socket (hostName.getText (), Integer.parseInt (portName.getText ()));
		out = new PrintWriter (sever.getOutputStream (), true);
		in = new BufferedReader (new InputStreamReader (sever.getInputStream ()));
		try
		{
		    send (name.getText ());
		    while (true)
		    {
			String s = in.readLine ().trim ();
			if (s.length () > 3)
			{

			    if (s.toLowerCase ().equals ("Exit"))
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
		catch (Exception f)
		{
		    diag.append ("Disconected from host!!\n");
		}
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
		    connection = true;
		    connect.setLabel ("Disconnect");
		}
		else
		{
		    send ("Exit");
		    connection = false;
		    connect.setLabel ("Connect");
		}
	    }
	}
    }
}


