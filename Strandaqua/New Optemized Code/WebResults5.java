// Elna Vermaak Sept 2003.
// The "Conxtest" class.
import java.sql.*;
import java.util.*;
import java.io.*;
public class WebResults5
{
    private Connection DataBase;
    public WebResults5 ()
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
	new WebResults5 ();

    } // main method


    void prog ()
    {
	meets ();
    }


    void meets ()
    {
	System.out.println ("Compling meets");
	//Querying
	Statement DataRequest = null;
	ResultSet res = null;
	try
	{
	    DataRequest = DataBase.createStatement ();
	    res = DataRequest.executeQuery ("SELECT MEET.Meet, MEET.MName, MEET.Course, MEET.Start, MEET.End, MEET.Location FROM MEET WHERE (((Meet.Start)<Date())And ((Meet.Start)>(Date()-140))) ORDER BY MEET.Start DESC;");
	}
	catch (SQLException error)
	{
	    System.err.println ("SQL error. hh" + error);
	}
	String l1 = "";
	try
	{
	    BufferedReader in1 = new BufferedReader (new FileReader ("Meets/index.htm"));
	    String v1 = in1.readLine ();
	    while (v1 != null)
	    {
		l1 = l1 + v1;
		v1 = in1.readLine ();
	    }
	}
	catch (IOException error)
	{
	    System.err.println ("No exsisting meet index file");
	}
	file meets = new file ("Meets", "index.htm");
	try
	{

	    //creating html page
	    meets.write ("<html><head><meta http-equiv=\"Content-Language\"content=\"en - za\">");
	    meets.write ("<meta name =\"keywords\"content=\"Western Province,meets,results,swimming,gala,strand,aquatic\">");
	    meets.write ("<meta name=\"description\"content=\"Results of all swimming gala's that have been run throught the year in the Western Province.\" ");
	    meets.write ("<meta http-equiv=\"Content-Type\"content=\"text/html\"charset=\"windows-1252\">");
	    meets.write ("<title>Meet Result, of Western Province - Strand Aquatics</title>");
	    meets.write ("<script src=\"../menu_sub.js\" language=\"javascript\" type=\"Text/Javascript\"></script><link rel='stylesheet' type='text/css' href='../styles.css'></head>");
	    meets.write ("<body topmargin=\"0\" leftmargin=\"0\">");
	    meets.write ("<div align=\"center\"><table border=\"0\" width=\"795px\" cellspacing=\"1\" cellpadding=\"2\">");
	    meets.write ("<tr><td height = \"80px\" colspan=\"5\"><p align=\"center\"><b><font size=\"6\">Meet Results</font></b></td></tr>");
	    meets.write ("<tr><td width=\"370px\"><b>Meet</b></td>");
	    meets.write ("<td width=\"80px\"><b>Start Date</b></td>");
	    meets.write ("<td width=\"80px\"><b>End Date</b></td>");
	    meets.write ("<td width=\"50px\"><b>Course</b></td>");
	    meets.write ("<td><b> Location</b></td></tr><b>");
	}
	catch (Exception e)
	{
	    System.out.println ("Error on reading results: " + e);
	}
	try
	{
	    //Meets Lists
	    BufferedReader in = new BufferedReader (new FileReader ("publish meets.txt"));
	    String l = "";
	    String v = in.readLine ();
	    while (v != null)
	    {
		l = l + v;
		v = in.readLine ();
	    }
	    BufferedWriter out = new BufferedWriter (new FileWriter ("publish meets.txt"));
	    out.write (l);
	    while (res.next ())
	    {
		String link = res.getString (1);
		String Name = res.getString (2).trim ();
		if (l.indexOf (Name) == -1)
		{
		    if (createresults (link, Name))
		    {
			System.out.println ("Meet: " + Name);
			meets.write ("<tr><td><a href=" + Name.replace (' ', '_') + ".htm>" + Name + "</a></td>");
			meets.write ("<td><b>" + res.getString (4).substring (0, 10) + "</b></td>");
			meets.write ("<td><b>" + res.getString (5).substring (0, 10) + "</b></td>");
			meets.write ("<td align=\"Center\"><b>" + res.getString (3) + "</b></td>");
			meets.write ("<td><b>" + res.getString (6) + "</b></td></tr>");
			out.write (Name + " , ");
		    }
		}
	    }
	    out.close ();
	    res.close ();
	    DataRequest.close ();
	}
	catch (Exception e)
	{
	    System.out.println ("Error on reading results: " + e);
	}
	//End of page write
	if (!l1.equals (""))
	{
	    meets.write (l1.substring (l1.indexOf ("<td><b> Location</b></td></tr><b>") + 33, l1.indexOf ("</table></div><Br><Br></body></html>")));
	}
	meets.write ("</table></div><Br><Br></body></html>");
	meets.close ();
	System.out.println ("finshed proccesing");
    }


    boolean createresults (String link, String Name)
    {
	//Querying
	String event;
	String results;
	Vector Events = new Vector ();
	Vector temp = new Vector ();

	event = "events = new Array(";
	results = "results = new Array(";
	ResultSet res = null;
	Statement DataRequest = null;
	try
	{
	    DataRequest = DataBase.createStatement ();
	    res = DataRequest.executeQuery ("SELECT MTEVENT.Session, MTEVENT.MtEv, MTEVENT.Sex, MTEVENT.Stroke, MTEVENT.Distance, MTEVENT.I_R, MTEVENT.MtEvent, MTEVENT.Lo_Hi, MTEVENT.MtEvX FROM MTEVENT WHERE (((MTEVENT.Meet)=" + link + ")) ORDER BY MTEVENT.MtEv, MTEVENT.MtEvX;");
	}
	catch (SQLException error)
	{
	    System.err.println ("SQL error. event" + error);
	}
	//gets Events

	Events.removeAllElements ();
	Events.trimToSize ();

	try
	{

	    while (res.next ())
	    {
		String [] row = new String [8];


		row [0] = res.getString (1);
		row [1] = res.getString (2);
		row [2] = res.getString (3);
		row [3] = res.getString (4);
		row [4] = res.getString (5);
		row [5] = res.getString (6);
		row [6] = res.getString (7);
		row [7] = res.getString (8);

		String e = res.getString (9);
		if (!e.equals (""))
		{
		    row [1] = row [1] + " " + e;
		}
		Events.addElement (row);
	    }
	    res.close ();
	    DataRequest.close ();
	}


	catch (Exception e)
	{
	    System.out.println ("Error on reading events: " + e);
	}


	//gets the each events results Events.size ()
	for (int e = 0 ; e < Events.size () ; e++)
	{
	    String finals = "", finalsD = "", pre = "", preD = "";
	    int count [] = new int [2];
	    String eventsnum [] = (String []) Events.elementAt (e);
	    if (eventsnum [5].equals ("I"))
	    {

		temp.removeAllElements ();
		temp.trimToSize ();
		count [0] = 0;
		count [1] = 0;
		res = null;
		try
		{
		    DataRequest = DataBase.createStatement ();

		    res = DataRequest.executeQuery ("SELECT Athlete.Last, Athlete.First, TEAM.TCode, Athlete.Sex, RESULT.AGE, RESULT.SCORE, RESULT.PLACE, TEAM.LSC, RESULT.F_P FROM (RESULT INNER JOIN Athlete ON RESULT.ATHLETE = Athlete.Athlete) INNER JOIN TEAM ON RESULT.TEAM = TEAM.Team WHERE (((RESULT.MTEVENT)=" + eventsnum [6] + ") AND ((RESULT.F_P)='F' Or (RESULT.F_P)='P')) ORDER BY RESULT.F_P,RESULT.PLACE;");


		}
		catch (SQLException error)
		{
		    System.err.println ("SQL error results. " + error);
		}
		try
		{

		    while (res.next ())
		    {
			String last = format (res.getString (1));
			String first = format (res.getString (2));
			String team = res.getString (3) + "-" + res.getString (8);
			String sex = res.getString (4);
			String age = res.getString (5);
			String time = time (res.getString (6));
			String posit = res.getString (7);
			if (res.getString (9).equals ("F"))
			{
			    count [0]++;
			    if (time.equals ("no result") || posit.equals ("0"))
			    {
				finalsD = finalsD + "\"\",\"" + last + "\",\"" + first + "\",\"" + team + "\",\"" + sex + "\",\"" + age + "\",\"" + time + "\",";
			    }
			    else
			    {
				finals = finals + "\"" + posit + "\",\"" + last + "\",\"" + first + "\",\"" + team + "\",\"" + sex + "\",\"" + age + "\",\"" + time + "\",";
			    }
			}
			else
			{
			    count [1]++;
			    if (time.equals ("no result") || posit.equals ("0"))
			    {
				preD = preD + "\"\",\"" + last + "\",\"" + first + "\",\"" + team + "\",\"" + sex + "\",\"" + age + "\",\"" + time + "\",";
			    }
			    else
			    {
				pre = pre + "\"" + posit + "\",\"" + last + "\",\"" + first + "\",\"" + team + "\",\"" + sex + "\",\"" + age + "\",\"" + time + "\",";
			    }
			}

		    }
		    res.close ();
		    DataRequest.close ();

		}
		catch (Exception e2)
		{
		    System.out.println ("Error on reading results: " + e2);
		}
		results = results + finals + finalsD + pre + preD;
		event = event + "\"" + count [0] + "\",\"" + eventsnum [0] + "\",\"" + eventsnum [1] + "\",\"" + eventsnum [2] + "\",\"" + eventsnum [3] + "\",\"" + eventsnum [4] + "\",\"" + eventsnum [5] + "\",\"" + eventsnum [7] + "\",\"" + count [1] + "\",";
		//write ned of table

	    }
	    else
	    {
		temp.removeAllElements ();
		temp.trimToSize ();
		count [0] = 0;
		res = null;
		try
		{
		    DataRequest = DataBase.createStatement ();
		    res = DataRequest.executeQuery ("SELECT RESULT.PLACE, TEAM.TName,RELAY.LETTER, RELAY.SEX, RESULT.SCORE, Athlete.Last, Athlete.First, Athlete.Sex, Athlete.Age, Athlete_1.Last, Athlete_1.First, Athlete_1.Sex, Athlete_1.Age, Athlete_2.Last, Athlete_2.First, Athlete_2.Sex, Athlete_2.Age, Athlete_3.Last, Athlete_3.First, Athlete_3.Sex, Athlete_3.Age FROM (((((RELAY INNER JOIN RESULT ON RELAY.RELAY = RESULT.ATHLETE) INNER JOIN Athlete ON RELAY.[ATH(1)] = Athlete.Athlete) INNER JOIN Athlete AS Athlete_1 ON RELAY.[ATH(2)] = Athlete_1.Athlete) INNER JOIN Athlete AS Athlete_2 ON RELAY.[ATH(3)] = Athlete_2.Athlete) INNER JOIN Athlete AS Athlete_3 ON RELAY.[ATH(4)] = Athlete_3.Athlete) INNER JOIN TEAM ON RESULT.TEAM = TEAM.Team WHERE (((RESULT.MTEVENT)=" + eventsnum [6] + ")) ORDER BY RESULT.PLACE;");
		}
		catch (SQLException error)
		{
		    System.err.println ("SQL error Relay. " + error);
		}
		//write results out
		//StrokeCategory
		try
		{
		    while (res.next ())
		    {
			count [0]++;
			String restemp [] = new String [21];
			for (int c = 0 ; c < 21 ; c++)
			{
			    if (c == 5 || c == 6 || c == 8 || c == 9 || c == 12 || c == 13 || c == 16 || c == 17)
			    {
				restemp [c] = res.getString (c + 1);
			    }
			    else
			    {
				restemp [c] = format (res.getString (c + 1));
			    }
			}
			restemp [4] = time (restemp [4]);
			if (restemp [4].equals ("no result") || restemp [0].equals ("0"))
			{
			    restemp [0] = "";
			    temp.addElement (restemp);
			}
			else
			{
			    for (int c = 0 ; c < 21 ; c++)
			    {
				results = results + "\"" + restemp [c] + "\",";
			    }

			}
		    }
		    res.close ();
		    DataRequest.close ();

		}
		catch (Exception e2)
		{
		    System.out.println ("Error on reading Relay results: " + eventsnum [6] + " : " + e2);
		}

		//no shows extra
		for (int l = 0 ; l < temp.size () ; l++)
		{
		    String t [] = (String []) temp.elementAt (l);
		    for (int c = 0 ; c < 21 ; c++)
		    {
			results = results + "\"" + t [c] + "\",";
		    }
		}
		event = event + "\"" + count [0] + "\",\"" + eventsnum [0] + "\",\"" + eventsnum [1] + "\",\"" + eventsnum [2] + "\",\"" + eventsnum [3] + "\",\"" + eventsnum [4] + "\",\"" + eventsnum [5] + "\",\"" + eventsnum [7] + "\",\"\",";
	    }


	}


	//closeing events arry and meets
	event = event + "\"\");";
	results = results + "\"\");";
	if (event.length () == 23 & results.length () == 24)
	{
	    return false;
	}


	else
	{
	    String teams = "club = new Array(\"";
	    //retriving meets
	    res = null;
	    try
	    {
		DataRequest = DataBase.createStatement ();
		res = DataRequest.executeQuery ("SELECT TEAM.TName, TEAM.TCode, TEAM.LSC FROM RESULT INNER JOIN TEAM ON RESULT.TEAM = TEAM.Team GROUP BY TEAM.TName, TEAM.TCode, TEAM.LSC, RESULT.MEET HAVING (((RESULT.MEET)=" + link + ")) ORDER BY TEAM.TName;");
	    }
	    catch (SQLException error)
	    {
		System.err.println ("SQL error3. " + error);
	    }
	    try
	    {


		while (res.next ())
		{

		    teams += res.getString (1) + "\",\"";
		    teams += res.getString (2) + "-" + res.getString (3) + "\",\"";
		}
		res.close ();
		DataRequest.close ();
	    }
	    catch (Exception e2)
	    {
		System.out.println ("Error on reading resultswes: " + e2);
	    }
	    teams += "\",\"\");";
	    //write file headings
	    file meets = new file ("Meets", Name.replace (' ', '_') + ".htm");
	    meets.write ("<head><title>" + Name + " Results</title><link rel=\"stylesheet\" type=\"text/css\" href=\"../styles.css\"><script src=\"filters.js\" language=\"javascript\" type=\"text/javascript\"></script><script language=\"javascript\" type=\"text/javascript\"><!--\nwindow.status=\"Downloading.... List of Clubs\";document.write(\"<frameset cols='170,*' framespacing='0' frameborder='0' scrolling='no' noresize><frame name='l' src='menu.htm' scrolling='no' noresize><frameset rows='120,*' framespacing='0' frameborder='0' noresize><frame name='h'  scrolling='no'><frame name='m'></frameset></frameset>\");");
	    meets.write (teams);
	    meets.write ("\nwindow.status = \"Proccessing... setting up Filters\";fil();window.status=\"Downloading.... Event List          Please be patient +- 5s\";");
	    meets.write (event);
	    meets.write ("\nwindow.status = \"Downloading.... Results             +- 10s Left\";");
	    meets.write (results);
	    meets.write ("\nwindow.status = \"Processing Events And Results\";page();</script></head><body>Strand aquatic's makes heavy use of JavaScript!<br>You don't appear to have a java compatible browser!<br>Please visit one of the following sites depending<br>on your browser to update it.<br><a href=\"http://microsoft.com/windows/ie/\">Internet Explorer 6</a> or <a href=\"http://home.netscape.com/download/\">Netscape 6</a></body></html>");
	    meets.close ();
	    return true;
	}
    }


    public String format (String last)
    {
	last = last.toLowerCase ();
	last = last.substring (0, 1).toUpperCase () + last.substring (1, last.length ());
	return last;
    }


    public String time (String str)
    {
	int mi = 0, se = 0, mu = 0;
	int time = Integer.parseInt (str);
	String stime = "";
	mi = (time % 100);
	time = (int) (time / 100);
	se = (time % 60);
	time = (int) (time / 60);
	mu = (time % 60);
	time = (int) (time / 60);
	if (mu < 10)
	{
	    stime = stime + "0" + mu;
	}


	else
	{
	    stime = stime + mu;
	}


	if (se < 10)
	{
	    stime = stime + ":0" + se;
	}


	else
	{
	    stime = stime + ":" + se;
	}


	if (mi < 10)
	{

	    stime = stime + ".0" + mi;
	}


	else
	{
	    stime = stime + "." + mi;
	}


	if (mi == 0 & mu == 0 & se == 0)
	{
	    stime = "no result";
	}


	return stime;
    }


    public String type (String str)
    {
	String c = str;
	if (c.equals ("I"))
	{
	    c = "Individual";
	}


	else
	{
	    c = "Relay";
	}


	return c;
    }


    String len (String s, int t)
    {
	for (int i = s.length () ; i < t ; i++)
	{
	    s += "&nbsp;";
	}


	return s;
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


