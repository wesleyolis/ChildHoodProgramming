//Wesley Oliver
import java.io.*;
public class DeskLayout
{
    String [] [] classPlan = new String [3] [2];
    DeskLayout () throws IOException
    {
	input ();
	display ("Class plan");
    }


    void input () throws IOException
    {
	BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
	System.out.println ("Type in 20 names <Enter> after each");
	for (int r = 0 ; r < 3 ; r++)
	{
	    for (int c = 0 ; c < 2 ; c++)
	    {
		classPlan [r] [c] = input.readLine ();
	    }
	}
    }


    void display (String h)
    {
	System.out.println ("\n" + h);
	for (int r = 0 ; r < 3 ; r++)
	{
	    for (int c = 0 ; c < 2 ; c++)
	    {
		System.out.print (classPlan [r] [c] + "  ");
	    }
	    System.out.println ();
	}
	System.out.println ("\nThe name at Position 0,0 is " + classPlan [0] [0]);
	System.out.println ("\nThe name at Position 2,1 is " + classPlan [2] [1]);
    }


    public static void main (String args []) throws IOException
    {
	new DeskLayout ();
    }
}
