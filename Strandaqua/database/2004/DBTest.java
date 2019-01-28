//WEl;ey Oliver
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
public class DBTest
{
    private JTable DataTable;
    private Connection Database;
    public DBTest ()
    {
	String url = "jdbc:odbc:SADMDATA";
	String userID = "s20142";
	String password = "s20142";
	Statement DataRequest;
	ResultSet Results;

	try
	{
	    Class.forName ("sun.jdbc.odbc.Jdbc0dbcDriver");
	    Database = DriverManager.getConnection (url, userID, password);
	}
	catch (ClassNotFoundException error)
	{
	    System.err.println ("Unable to load the JDBC/ODNC brige " + error);
	    //System.exit (1);
	}
	catch (SQLException error)
	{
	    System.err.println ("CannotConnet to the database " + error);
	    //System.exit (2);
	}
	System.out.println ("Succes");
    }


    public static void main (String [] args)
    {
	final DBTest link = new DBTest ();
    }
}
