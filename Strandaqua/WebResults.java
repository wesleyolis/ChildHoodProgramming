// Elna Vermaak Sept 2003.
// The "Conxtest" class.
import java.sql.*;
import java.util.*;
import java.io.*;
public class WebResults
{
    private Connection DataBase;
    Vector Meets = new Vector ();
    public WebResults ()
    {
	String url = "jdbc:odbc:Swim";
	String userID = "";
	String password = "";
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
	prog ();
	try
	{
	    DataBase.close ();
	}
	catch (Exception e)
	{

	}
    }


    public static void main (String [] args)
    {
	new WebResults ();

    } // main method


    void prog ()
    {


	meets ();
    }


    void meets ()
    {
	//Querying
	Statement DataRequest = null;
	ResultSet res = null;
	try
	{
	    DataRequest = DataBase.createStatement ();
	    res = DataRequest.executeQuery ("SELECT MEET.Meet, MEET.MName, MEET.Course, MEET.Start, MEET.End, MEET.Location FROM MEET WHERE (([Start]<Date())) ORDER BY MEET.Start;");
	}
	catch (SQLException error)
	{
	    System.err.println ("SQL error. " + error);
	}
	//creating html page
	file meets = new file ("Meets", "index.htm");
	meets.write ("<html><head><meta http-equiv=\"Content-Language\"content=\"en - za\">");
	meets.write ("<meta name =\"keywords\"content=\"Western Province,meets,results,swimming,gala,strand,aquatic\">");
	meets.write ("<meta name=\"description\"content=\"Results of all swimming gala's that have been run throught the year in the Western Province.\" ");
	meets.write ("<meta http-equiv=\"Content-Type\"content=\"text/html\"charset=\"windows-1252\">");
	meets.write ("<title>Meet Result, of Western Province - Strand Aquatics</title>");
	meets.write ("<script src=\"link_bar.js\" language=\"javascript\" type=\"Text/Javascript\"></script></head>");
	meets.write ("<body background=\"images/background.jpg\" bgcolor=\"#6693B5\" topmargin=\"0\" leftmargin=\"0\">");
	meets.write ("<div align=\"center\"><table border=\"0\" width=\"795px\" cellspacing=\"1\" cellpadding=\"2\">");
	meets.write ("<tr><td height = \"80px\" colspan=\"5\"><p align=\"center\"><b><font size=\"6\">Meet Results</font></b></td></tr>");
	meets.write ("<tr><td width=\"370px\"><b>Meet</b></td>");
	meets.write ("<td width=\"80px\"><b>Start Date</b></td>");
	meets.write ("<td width=\"80px\"><b>End Date</b></td>");
	meets.write ("<td width=\"50px\"><b>Course</b></td>");
	meets.write ("<td><b> Location</b></td></tr><b>");
	//Meets Lists

	try
	{
	    res.next ();
	    while (res.next ())
	    {
		String link = res.getString (1);
		Meets.addElement (link);
		meets.write ("<tr><td><a href=" + link + ">" + res.getString (2) + "</a></td>");
		meets.write ("<td>" + res.getString (4).substring (0, 10) + "</td>");
		meets.write ("<td>" + res.getString (5).substring (0, 10) + "</td>");
		meets.write ("<td align=\"Center\">" + res.getString (3) + "</td>");
		meets.write ("<td>" + res.getString (6) + "</td></tr>");
	    }

	    res.close ();
	    DataRequest.close ();
	}
	catch (Exception e)
	{
	    System.out.println ("Error on reading results: " + e);
	}
	//End of page write
	meets.write ("</table></div><Br><Br></body></html>");
	meets.close ();
    }


    class file
    {
	BufferedWriter wr;
	file (String dir, String name)
	{
	    try
	    {
		File file = new File (dir, name);
		wr = new BufferedWriter (new FileWriter (file));
	    }
	    catch (Exception e)
	    {
	    }
	}


	void write (String str)
	{
	    try
	    {
		wr.write (str);
	    }
	    catch (Exception e)
	    {
	    }
	}


	void close ()
	{
	    try
	    {
		wr.close ();
	    }
	    catch (Exception e)
	    {
	    }
	}
    }
} // Conxtest class
