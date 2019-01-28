import java.sql.*;
class query
{
    Statement request;
    void setConnection (Connection con) throws SQLException
    {
	request = con.createStatement ();
    }


    ResultSet excute (String query) throws SQLException
    {
	return request.executeQuery (query);

    }


    void update (String query) throws SQLException
    {
	request.executeUpdate (query);
    }
}
