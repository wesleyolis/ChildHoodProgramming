import java.net.URL;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
public class dbFrame extends Frame
{
    Database db;
    Vector Tables;

    public dbFrame ()
    {
	db = new Database ("sun.jdbc.odbc.JdbcOdbcDriver");
	db.Open ("jdbc:odbc:Swim", "c:\\projects\\chapter19\\WPA 2003-2004.mdb");
	/* resultSet rs = db.Execute ("SELECT MEET.Meet, MEET.MName, MEET.Course, MEET.Start, MEET.End, MEET.Location FROM MEET;");
	 queryDialog q = new queryDialog (this, rs);
	 q.show ();*/
	getMeets ();
	meets ();
	/*for (int i = 0 ; i < Tables.size () ; i++)
	{
	    results (i);
	}*/
    }


    static public void main (String argv [])
    {
	new dbFrame ();
    }


    void getMeets ()
    {
	resultSet rs = db.Execute ("SELECT MEET.Meet, MEET.MName, MEET.Course, MEET.Start, MEET.End, MEET.Location FROM MEET WHERE (([Start]<Date())) ORDER BY MEET.Start;");
	Tables = new Vector ();
	while (rs.hasMoreElements ())
	{
	    Tables.addElement (rs.nextElement ());
	}


    }


    void meets ()
    {
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
	meets.write ("<td><b> Location</b></td></tr>");
	//Meets Lists

	String s [];
	for (int i = 0 ; i < Tables.size () ; i++)
	{
	    //gets elements
	    s = (String []) Tables.elementAt (i);
	    String Link = "MR" + s [0];
	    String Meetname = s [1];
	    String Course = s [2];
	    String DateStart = s [3].substring (0, 10);
	    String DateEnd = s [4].substring (0, 10);
	    String Location = s [5];
	    //writes elements out
	    meets.write ("<tr><td><a href=" + Link + ">" + Meetname + "</a></td>");
	    meets.write ("<td>" + DateStart + "</td>");
	    meets.write ("<td>" + DateEnd + "</td>");
	    meets.write ("<td align=\"Center\">" + Course + "</td>");
	    meets.write ("<td>" + Location + "</td></tr>");
	}

	//End of page write
	meets.write ("</table></div><Br><Br></body></html>");
	meets.close ();
    }


    void results (int i)
    {
	Vector swimrs = new Vector ();
	Vector Events = new Vector ();
	String [] eventnum;
	String [] meetnum;
	resultSet res = null;

	res = null;
	Events.removeAllElements ();
	//gets Events
	meetnum = (String []) Tables.elementAt (i);
	System.out.println ("meet [" + i + "/ " + Tables.size () + "]:" + meetnum [1]);
	res = db.Execute ("SELECT MTEVENT.Session, MTEVENT.MtEv, MTEVENT.Distance, MTEVENT.Stroke, MTEVENT.Sex, MTEVENT.I_R, MTEVENT.MtEvent FROM MTEVENT WHERE (((MTEVENT.Meet)=" + meetnum [0] + ")) ORDER BY MTEVENT.MtEv;");
	while (res.hasMoreElements ())
	{
	    Events.addElement (res.nextElement ());
	}


	//Write out header code here



	//gets the each events results Events.size ()
	for (int e = 0 ; e < Events.size () ; e++)
	{
	    System.out.println (">>event [" + e + "/ " + Events.size () + "]");
	    swimrs.removeAllElements ();
	    //gets Events
	    eventnum = (String []) Events.elementAt (e);
	    res = db.Execute ("SELECT TEAM.TCode, RESULT.I_R, RESULT.SCORE, Athlete.Last, Athlete.First, Athlete.Sex, Athlete.Age FROM (RESULT INNER JOIN Athlete ON RESULT.ATHLETE = Athlete.Athlete) INNER JOIN TEAM ON RESULT.TEAM = TEAM.Team WHERE (((RESULT.MTEVENT)=" + eventnum [6] + ")) ORDER BY RESULT.SCORE;");

	    while (res.hasMoreElements ())
	    {
		swimrs.addElement (res.nextElement ());
		//gets the results
	    }
	    res = null;

	}


    }
}


class Database
{
    Connection con;
    resultSet results;
    ResultSetMetaData rsmd;
    DatabaseMetaData dma;
    String catalog;
    String types [];

    public Database (String driver)
    {
	types = new String [1];
	types [0] = "TABLES";
	try
	{
	    Class.forName (driver);
	}
	catch (Exception e)
	{
	    System.out.println ("driver load failed:" + e.getMessage ());
	}
    }


    //-----------------------------------
    public void Open (String url, String cat)
    {
	catalog = cat;
	try
	{
	    con = DriverManager.getConnection (url);
	    dma = con.getMetaData ();
	    results = new resultSet (dma.getCatalogs ());
	    String s [];
	    while (results.hasMoreElements ())
	    {
		s = results.nextElement ();
		//System.out.println (s [0]);
	    }
	}
	catch (Exception e)
	{
	    System.out.println ("open failed: " + e.getMessage ());
	}
    }


    //-----------------------------------
    /* public String [] getTableNames ()
     {
	 String [] tbnames = null;
	 Vector tname = new Vector ();

	 try
	 {
	     results = new resultSet (dma.getTables (catalog, null, "%", types));

	     while (results.hasMoreElements ())
		 tname.addElement (results.getColumnValue ("TABLE_NAME"));

	 }
	 catch (Exception e)
	 {
	     System.out.println (e);
	 }
	 tbnames = new String [tname.size ()];
	 for (int i = 0 ; i < tname.size () ; i++)
	     tbnames [i] = (String) tname.elementAt (i);
	 return tbnames;
     }*/


    //-----------------------------------
    /* public String [] getTableMetaData ()
     {
	 results = null;
	 try
	 {
	     results = new resultSet (dma.getTables (catalog, null, "%", types));
	 }
	 catch (Exception e)
	 {
	     System.out.println (e.getMessage ());
	 }
	 return results.getMetaData ();
     }*/

    /*
	//-----------------------------------
	public String [] getColumnMetaData (String tablename)
	{
	    results = null;
	    try
	    {
		results = new resultSet (dma.getColumns (catalog, null, tablename, null));
	    }
	    catch (Exception e)
	    {
		System.out.println (e.getMessage ());
	    }
	    return results.getMetaData ();
	}


	//-----------------------------------
	public String [] getColumnNames (String table)
	{
	    String [] tbnames = null;
	    Vector tname = new Vector ();

	    try
	    {
		results = new resultSet (dma.getColumns (catalog, null, table, null));
		while (results.hasMoreElements ())
		    tname.addElement (results.getColumnValue ("COLUMN_NAME"));
	    }
	    catch (Exception e)
	    {
		System.out.println (e);
	    }

	    tbnames = new String [tname.size ()];
	    for (int i = 0 ; i < tname.size () ; i++)
		tbnames [i] = (String) tname.elementAt (i);
	    return tbnames;
	}


	//-----------------------------------
	public String getColumnValue (String table, String columnName)
	{
	    String res = null;
	    try
	    {
		if (table.length () > 0)
		    results = Execute ("Select " + columnName + " from " + table + " order by " + columnName);
		res = results.getColumnValue (columnName);
	    }
	    catch (Exception e)
	    {
		System.out.println ("Column value error" + columnName + e.getMessage ());
	    }

	    return res;
	}


	//-----------------------------------
	public String getNextValue (String columnName)
	{
	    String res = "";
	    try
	    {
		if (results.hasMoreElements ())
		    res = results.getColumnValue (columnName);
	    }
	    catch (Exception e)
	    {
		System.out.println ("next value error" + columnName + e.getMessage ());
	    }

	    return res;
	}
    */

    //-----------------------------------
    public resultSet Execute (String sql)
    {
	results = null;
	try
	{
	    Statement stmt = con.createStatement ();
	    results = new resultSet (stmt.executeQuery (sql));
	}
	catch (Exception e)
	{
	    System.out.println (e.getMessage ());
	}
	return results;
    }
}


//==========================================
class resultSet
{
    ResultSet rs;
    ResultSetMetaData rsmd;
    int numCols;

    public resultSet (ResultSet rset)
    {
	rs = rset;
	try
	{
	    rsmd = rs.getMetaData ();
	    numCols = rsmd.getColumnCount ();
	}
	catch (Exception e)
	{
	    System.out.println (e.getMessage ());
	}
    }


    //-----------------------------------
    public String [] getMetaData ()
    {
	String md [] = new String [numCols];
	try
	{
	    for (int i = 1 ; i <= numCols ; i++)
		md [i - 1] = rsmd.getColumnLabel (i);
	}
	catch (Exception e)
	{
	    System.out.println (e.getMessage ());
	}
	return md;
    }


    //-----------------------------------
    public boolean hasMoreElements ()
    {
	try
	{
	    return rs.next ();
	}
	catch (Exception e)
	{
	    return false;
	}
    }


    //-----------------------------------
    public String [] nextElement ()
    {
	String [] row = new String [numCols];
	try
	{
	    for (int i = 1 ; i <= numCols ; i++)
		row [i - 1] = rs.getString (i);
	}
	catch (Exception e)
	{
	    System.out.println (e.getMessage ());
	}
	return row;
    }


    //-------------------------------------
    public String getColumnValue (String columnName)
    {
	String res = "";
	try
	{
	    res = rs.getString (columnName);
	}
	catch (Exception e)
	{
	    System.out.println (e.getMessage ());
	}
	return res;
    }


    //----------------------------------------------
    public void finalize ()
    {
	try
	{
	    rs.close ();
	}
	catch (Exception e)
	{
	    System.out.println (e.getMessage ());
	}
    }
}


//=====================================================
class queryDialog extends Dialog
    implements ActionListener
{
    resultSet results;
    Button OK;
    textPanel pc;
    Vector tables;

    public queryDialog (Frame f, resultSet r)
    {
	super (f, "Query Result");
	results = r;
	setLayout (new BorderLayout ());
	OK = new Button ("OK");
	Panel p = new Panel ();
	add ("South", p);
	p.add (OK);
	OK.addActionListener (this);

	pc = new textPanel ();
	pc.setBackground (Color.white);
	add ("Center", pc);
	makeTables ();
	setBounds (100, 100, 500, 300);
	setVisible (true);
	repaint ();
    }


    //-------------------------------------
    private void makeTables ()
    {
	tables = new Vector ();
	String t [] = results.getMetaData ();
	tables.addElement (t);
	while (results.hasMoreElements ())
	{
	    tables.addElement (results.nextElement ());
	}
    }


    //-------------------------------------
    public void actionPerformed (ActionEvent e)
    {
	setVisible (false);
    }


    //-------------------------------------
    class textPanel extends Panel
    {
	public void paint (Graphics g)
	{
	    String s [];
	    int x = 0;
	    int y = g.getFontMetrics ().getHeight ();
	    int deltaX = (int) 1.5f * (g.getFontMetrics ().stringWidth ("wwwwwwwwwwwwwwwwwwwwww"));
	    for (int i = 1 ; i < tables.size () ; i++)
	    {
		s = (String []) tables.elementAt (i);
		String line = s [1] + s [2] + s [3].substring (0, 10) + s [4].substring (0, 10) + s [5];

		System.out.println (line);

		for (int j = 0 ; j < s.length ; j++)
		{
		    String st = s [j];
		    g.drawString (st, x, y);
		    x += deltaX;
		}
		x = 0;
		y += g.getFontMetrics ().getHeight ();
		if (i == 0)
		    y += g.getFontMetrics ().getHeight ();

	    }
	}
    }
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


