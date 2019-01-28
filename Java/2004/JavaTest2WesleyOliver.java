// WesleyOliver
import java.io.*;
import java.util.*;
public class JavaTest2WesleyOliver
{
    public static void main (String [] args)
    {
	new Prog ();
    }


    static class Prog
    {
	Input in = new Input ();
	amicable am;
	Prog ()
	{
	    while (true)
	    {
		System.out.println ("Please Enter two numbers larger than 0");
		int num = in.readInt ();
		int num2 = in.readInt ();
		am = new amicable (new int []
		{
		    num, num2
		}
		);
		if (am.are ())
		{
		    System.out.println (num + " And " + num2 + " are amicable numbers");
		}
		else
		{
		    System.out.println (num + " And " + num2 + " are not amicable numbers");
		}
		System.out.println (am.details (0));
		System.out.println (am.details (1));
		in.any ();

	    }
	}
    }


    static class amicable
    {
	String detail [];
	int sum [], len;
	int org [];
	amicable (int num [])
	{
	    org = num;
	    len = num.length;
	    detail = new String [len];
	    sum = new int [len];
	    for (int c = 0 ; c < len ; c++)
	    {
		detail [c] = "1";
		sum [c] = 1;
		for (int i = 2 ; i < (num [c] / 2) + 1 ; i++)
		{
		    if ((num [c] % i) == 0)
		    {
			sum [c] = sum [c] + i;
			detail [c] = detail [c] + " + " + i;
		    }
		}
		detail [c] = detail [c] + " = " + sum [c];
	    }
	}


	boolean are ()
	{
	    boolean is = true;
	    for (int c = 0 ; c < len - 1 ; c++)
	    {
		for (int p = c + 1 ; p < len ; p++)
		{
		    if (sum [c] != org [p])
		    {
			is = false;
			break;
		    }
		}

	    }
	    return is;
	}

	String details (int which)
	{
	    return detail [which];
	}

    }
}

class Input
{
    InputStreamReader c = new InputStreamReader (System.in);
    BufferedReader in = new BufferedReader (c);

    int readInt ()
    {
	while (true)
	{
	    try
	    {
		System.out.print (">>");
		return Integer.parseInt (in.readLine ());
	    }
	    catch (NumberFormatException e)
	    {
		System.out.println ("\nInvailed input for an integer try again!!");
	    }
	    catch (IOException e)
	    {
		System.out.println ("\nInvailed input try again!!");
	    }
	}
    }


    void any ()
    {
	try
	{
	    System.out.println ("\n\nPress Enter Key to Continue");
	    in.readLine ();
	}
	catch (IOException e)
	{

	}

    }
}


