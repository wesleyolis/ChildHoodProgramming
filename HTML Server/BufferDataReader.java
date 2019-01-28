import java.lang.*;
import java.lang.Thread;
import java.nio.Buffer;
import java.io.*;
class BufferDataReader
{
    private static StringBuffer out = new StringBuffer (56);
    private InputStream in;
    private static fullbuffer full;
    BufferDataReader (InputStream in)
    {
	in = this.in;
	full = new fullbuffer ();
	full.start ();
    }


    private class fullbuffer extends Thread
    {
	fullbuffer ()
	{
	    refresh ();
	}

	void refresh ()
	{

	}

    }



    static String read ()
    {
	String data = out.toString ();
	full.refresh ();
	return data;
    }
}

