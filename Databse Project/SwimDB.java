import java.sql.*;
class SwimDB
{
    private Connection DataBase;
    private Statement Request = null;
    private ResultSet res = null;
    private DatabaseMetaData dma;
    SwimDB (String url, String userID, String pass) throws SQLException, ClassNotFoundException
    {
	Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
	DataBase = DriverManager.getConnection (url, userID, pass);
	Request = DataBase.createStatement ();
	dma = DataBase.getMetaData ();
    }


    void close ()  //close and relesing of all resorces
    {
	DataBase.close ();
    }


    boolean valied
    {
    String tables
	String type = {"Table"};
	res = dma.getTables (null, null, "%", type);
	while(res.next())
	{
	
	}
	getString("TABLE_NAME");
	try
	{
	    res = Request.executeQuery ("SELECT MEET.Meet, MEET.MName, MEET.Course, MEET.Start, MEET.End, MEET.Location FROM MEET WHERE (((Meet.Start)<Date())And ((Meet.Start)>(Date()-140))) ORDER BY MEET.Start DESC;");
	}
	catch (SQLException error)
	{
	    System.err.println ("SQL error. hh" + error);
	}
    }
}
