// Wesley Oliver
import java.io.*;
public class Seating_plan
{
    static int row, col;
    static boolean seats [] [];
    static BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    public static void main (String [] args) throws IOException
    {
	System.out.print ("Enter the size of seating plan\nRows>>");
	row = Integer.parseInt (in.readLine ());
	System.out.print ("Columns>>");
	col = Integer.parseInt (in.readLine ());
	seats = new boolean [col] [row];
	display ();

    }


    public static int find (String s, String what)
    {
	int r = 0;
	for (int c = 0 ; c < s.length () ; c++)
	{
	    if (what.indexOf (pos.charAt (c)) > -1)
	    {
		r = c;
	    }
	}
	return r;
    }


    public static void input ()
    {
	String num = "0123456789";
	String num = "qwertyuioplkjhgfdsazxcvbnm";
	while (true)
	{
	    System.out.println ("Enter the position where to sit (e.g 'A1'");
	    String pos = in.readLine ();
	    if ((find (pos, num) != -1)&(find (pos, num) != -1))
	    {
	    


	    }
	}
    }


    public static void display ()
    {
	int let = 26;
	String r2 = "";
	print ("Screen", (((col * 3) - 3) / 2), 6);
	System.out.println ();
	for (int i = 0 ; i < (col * 3) + 3 ; i++)
	{
	    System.out.print ("-");
	}
	System.out.println ();
	for (int r = row ; r > 0 ; r--)
	{
	    print (alpha (r), 0, 3);
	    for (int c = 0 ; c < col ; c++)
	    {
		if (!seats [c] [(row - r)])
		{
		    print (String.valueOf ((c + 1)), 0, 3);
		}
		else
		{
		    print ("", 0, 3);
		}
	    }
	    System.out.println ();
	}
    }



    public static void print (String str, int s, int size)
    {
	int p = str.length ();
	if (size < str.length ())
	{
	    p = size;
	}


	str = str.substring (0, p);
	for (int i = 0 ; i < s ; i++)
	{
	    str = " " + str;
	}


	for (int i = 0 ; i < (size - p) ; i++)
	{
	    str = str + " ";
	}


	System.out.print (str);
    }


    public static String alpha (int i)
    {
	String str = ""; // String.valueOf(i % 26);
	int a = 0;
	while (i > 0)
	{
	    a = ((i % 26) + 64);
	    if (a == 64)
	    {
		a = 90;
		i -= 1;
	    }
	    str = String.valueOf ((char) (a)) + str;
	    i = (i / 26);
	}


	return str;
    }
}
