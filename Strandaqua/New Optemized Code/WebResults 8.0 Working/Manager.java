// Manager By Wesley Oliver Main Class
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.sql.*;
import javax.swing.*;
import java.io.*;
import java.util.prefs.Preferences;
public class Manager extends Frame
{
    static Frame driver, popup;
    static Dialog DrvSettings;
    Toolkit toolkit;
    Image back;
    private static Connection DataBase, info;
    private static String resultsUrl = "", infoUrl = ""; //jdbc:odbc:
    private static String resultsUser = "", infoUser = "";
    private static String resultsPassword = "", infoPassword = "";
    static String Settings[] = new String [7];   //sorts settings,db results,db info,index templete
    static Runtime run = Runtime.getRuntime ();
    public Manager ()
    {
	setTitle ("Web Results Manager");
	toolkit = Toolkit.getDefaultToolkit ();
	setIconImage (toolkit.getImage ("icon.gif"));
	back = toolkit.getImage ("pool.jpg");
	driver = new Frame ();
	popup = new Frame ();
	DrvSettings = new driver (driver);
	MediaTracker mediaTracker = new MediaTracker (this);
	mediaTracker.addImage (back, 0);
	try
	{
	    mediaTracker.waitForID (0);
	}
	catch (InterruptedException ie)
	{
	}

	setSize (toolkit.getScreenSize ().width, toolkit.getScreenSize ().height);
	setVisible (true);
	addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent e)
	    {

		close ();
	    }
	}
	);
	//loads the previous settings
	try
	{
	    BufferedReader in = new BufferedReader (new FileReader ("Settings.set"));
	    for (int s = 0 ; s < 7 ; s++)
	    {
		String temp = in.readLine ();
		if (temp != null)
		{
		    Settings [s] = temp;
		}
		else
		{
		    Settings [s] = "";
		}

	    }
	    // if(!Settings.equals(""))
	    // {
	    // connectres ();
	    // }
	}
	catch (FileNotFoundException e)
	{
	    JOptionPane.showMessageDialog (popup, "Error on Load Previous Settings,File Not Found\nSettings will Default", "Error", JOptionPane.ERROR_MESSAGE);
	    DrvSettings.setVisible (true);
	}
	catch (IOException e)
	{
	    JOptionPane.showMessageDialog (popup, "Error on Load Previous Settings, General Error\nData may have become corrupt.\nSettings will Default", "Error", JOptionPane.ERROR_MESSAGE);
	    DrvSettings.setVisible (true);
	}
	DrvSettings.setVisible (true);

    }


    public static void close ()
    {
	int option = JOptionPane.showConfirmDialog (popup,
		"Are you sure you want to exit?", "Exit",
		JOptionPane.YES_NO_OPTION);
	if (option == JOptionPane.YES_OPTION)
	{
	    try
	    {
		if (DataBase != null)
		{
		    DataBase.close ();
		}

	    }
	    catch (SQLException e)
	    {
		JOptionPane.showMessageDialog (popup, "Error on Cloasing Database Connection,\nwill now continue to exit", "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    try
	    {
		BufferedWriter out = new BufferedWriter (new FileWriter ("Settings.Set"));
		for (int s = 0 ; s < 7 ; s++)
		{
		    out.write (Settings [s]);
		    out.newLine ();
		}
		out.close ();
	    }
	    catch (IOException f)
	    {
		JOptionPane.showMessageDialog (popup, "Error on Saving Settings,\nwill now continue to exit", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	    System.exit (0);
	}
    }


    public void connect () throws SQLException
    {
	try
	{
	    Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
	    DataBase = DriverManager.getConnection ("jdbc:odbc:" + resultsUrl, resultsUser, resultsPassword);
	    info = DriverManager.getConnection ("jdbc:odbc:" + infoUrl, infoUser, infoPassword);
	}
	catch (ClassNotFoundException error)
	{
	    JOptionPane.showMessageDialog (popup, "Error Could not find the 'JdbcOdbc' Driver class", "Error", JOptionPane.ERROR_MESSAGE);
	}

    }


    public void paint (Graphics graphics)
    {
	graphics.drawImage (back, 0, 50, getSize ().width, getSize ().height, null);
    }


    public static void main (String[] args)
    {
	new Manager ();
    }


    class driver extends Dialog implements ActionListener
    {
	TextField results, info;
	Button ok, cancel, odbc;
	public driver (Frame f)
	{
	    super (f);
	    setTitle ("Driver Connection");
	    setSize (400, 200);
	    setLocation ((toolkit.getScreenSize ().width - 400) / 2, (toolkit.getScreenSize ().height - 200) / 2);
	    setBackground (new Color (0, 104, 204));
	    addWindowListener (new WindowAdapter ()
	    {
		public void windowClosing (WindowEvent e)
		{
		    close ();
		}
	    }
	    );
	    setLayout (new BorderLayout ());
	    setFont (new Font ("Helvetica", Font.BOLD, 14));
	    Panel Top = new Panel ();
	    Top.setLayout (new GridLayout (3, 1, 0, 0));
	    Top.add (new Label ("Welcome to Database link Manager,"));

	    odbc = new Button ("ODBC Manager");
	    odbc.addActionListener (this);
	    Panel b = new Panel ();
	    b.setLayout (new BorderLayout ());
	    b.add (new Label ("Please spessify ODBC names"), BorderLayout.WEST);
	    b.add (odbc, BorderLayout.EAST);
	    Top.add (b);
	    add (Top, BorderLayout.NORTH);

	    Panel Center = new Panel ();
	    Center.setLayout (new GridLayout (3, 2, 5, 10));
	    add (Center, BorderLayout.CENTER);
	    Center.add (new Label ("Swim Results"));
	    results = new TextField ();
	    Center.add (results);
	    Center.add (new Label ("info"));
	    info = new TextField ();
	    // Center.add (new Label (""));
	    // Center.add (new Label (""));
	    Center.add (info);

	    Panel South = new Panel ();
	    South.setLayout (new GridLayout (1, 2, 10, 10));
	    ok = new Button ("OK");
	    ok.addActionListener (this);
	    South.add (ok);
	    cancel = new Button ("CANCEL");
	    cancel.addActionListener (this);
	    South.add (cancel);
	    add (South, BorderLayout.SOUTH);





	}
	public void actionPerformed (ActionEvent e)
	{
	    if (e.getSource () == cancel)
	    {
		close ();
	    }
	    else
	    {
		if (e.getSource () == odbc)
		{
		    try
		    {

			Manager.run.exec ("C:/Windows/system32/odbcad32.exe");
		    }
		    catch (IOException f)
		    {

		    }
		}
	    }
	}
	private void close ()
	{
	    if (DataBase == null)
	    {
		Manager.close ();
	    }
	    else
	    {
		setVisible (false);
	    }
	}

	protected void makebutton (String name,
		GridBagLayout gridbag,
		GridBagConstraints c)
	{
	    Button button = new Button (name);
	    gridbag.setConstraints (button, c);
	    add (button);
	}


    }
}
