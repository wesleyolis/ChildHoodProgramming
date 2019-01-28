// Elna Vermaak Sept 2003.
// The "Conxtest" class.
import java.sql.*;

public class Createtb
{
    private Connection DataBase;

    public Createtb ()
    {
	String url = "jdbc:odbc:HouseDB";
	String userID = "s20142";
	String password = "s20142";
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

	Statement DataRequest;
	try
	{
	    String query = "Create Table address(CustomerNumber CHAR(30),CustomerStreet CHAR(30),"
		+ "CustomerCity CHAR(30),CustomerZip CHAR(30))";
	    DataRequest = DataBase.createStatement ();
	    DataRequest.executeQuery (query);
	    DataRequest.close ();
	    DataBase.close ();
	}
	catch (SQLException error)
	{
	    System.err.println ("SQL error. " + error);
	    //System.exit (3);
	    //System.exit (3);
	} //builds window on screen
    }


    public static void main (String [] args)
    {
	final Createtb link = new Createtb ();
	System.out.println ("Success");

    } // main method
} // Conxtest class


