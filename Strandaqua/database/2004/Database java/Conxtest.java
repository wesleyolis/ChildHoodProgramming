// Elna Vermaak Sept 2003.
// The "Conxtest" class.
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.lang.*;


public class Conxtest extends JFrame
{
    private JTable DataTable;
    private Connection DataBase;

    public Conxtest ()
    {
	String url = "jdbc:odbc:SADMDATA";
	String userID = "s20142";
	String password = "s20142";
	Statement DataRequest;
	ResultSet Results;
	try
	{
	    Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
	    DataBase = DriverManager.getConnection (url, userID, password);
	}
	catch (ClassNotFoundException error)
	{
	    System.err.println ("Unable to load the JDBC/ODBC bridge. " + error);
	    //System.exit (1);
	}
	catch (SQLException error)
	{
	    System.err.println ("Cannot connect to the database. " + error);
	    //System.exit (2);

	}
	try
	{
	    String query = "Select * FROM Player ";
	    DataRequest = DataBase.createStatement ();
	    Results = DataRequest.executeQuery (query);
	    System.out.println (Results);
	    DisplayResults (Results); //display results on window
	    DataRequest.close ();
	}
	catch (SQLException error)
	{
	    System.err.println ("SQL error. " + error);
	    //System.exit (3);
	}
	setSize (500, 200);  //500 pixels wide and 200 pixels high
	show ();  //builds window on screen
    }


    private void DisplayResults (ResultSet DisplayResults) throws SQLException
    {

	boolean Records = DisplayResults.next ();
	if (!Records)
	{
	    JOptionPane.showMessageDialog (this, "End of data.");
	    setTitle ("Process Completed");
	    return;
	}
	setTitle ("Customer Names");
	Vector ColumnNames = new Vector ();
	Vector rows = new Vector ();

	try
	{
	    ResultSetMetaData MetaData = DisplayResults.getMetaData ();
	    for (int x = 1 ; x <= MetaData.getColumnCount () ; x++)
		ColumnNames.addElement (MetaData.getColumnName (x));
	    do
	    {
		rows.addElement (DownRow (DisplayResults, MetaData));
	    }
	    while (DisplayResults.next ());

	    DataTable = new JTable (rows, ColumnNames);
	    JScrollPane scroller = new JScrollPane (DataTable);
	    getContentPane ().add (scroller, BorderLayout.CENTER);
	    validate ();
	}
	catch (SQLException error)
	{
	    System.err.println ("Data display error. " + error);
	    //System.exit (4);
	}
    }


    private Vector DownRow (ResultSet DisplayResults, ResultSetMetaData MetaData) throws SQLException
    {
	Vector currentRow = new Vector ();
	for (int x = 1 ; x <= MetaData.getColumnCount () ; x++)
	{
	    switch (MetaData.getColumnType (x))
	    {
		case Types.VARCHAR:
		    currentRow.addElement (DisplayResults.getString (x));
		    break;
	    }
	}
	return currentRow;
    }


    public void Disconnect ()
    {
	try
	{
	    DataBase.close ();
	}
	catch (SQLException error)
	{
	    System.err.println ("Cannot break connection. " + error);
	    //System.exit (5);
	}
    }


    public static void main (String [] args)
    {
	final Conxtest link = new Conxtest ();
	link.addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent WinEvent)
	    {
		link.Disconnect ();
		System.exit (0);
	    }
	}
	);
	System.out.println ("Success");

    } // main method
} // Conxtest class


