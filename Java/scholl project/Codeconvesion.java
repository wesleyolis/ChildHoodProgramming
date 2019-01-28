// The "Char" class.
import java.awt.*;
import hsa.Console;

public class Codeconvesion
{
    static Console c;           // The output console

    public static void main (String [] args)
    {
	c = new Console ();
	for (;;)
	{
	    String binary = "";
	    c.println ("Please enter a integer");
	    double num = c.readInt ();
	    while ((int) (num) > 0)
	    {
		if (num % 16 < 10)
		{   int numint =  (int) Math.round((num % 16));
		    binary =  (numint) + binary;
		    num = (int)(num / 16);
		    
		}
		if(num % 16 > 9 )
		{

		    char num7 = (char) ((num % 16) - 9 + 64);
		    char num1 [] = {num7};
		    binary = new String (num1) + binary;
		    num = (int) (num / 16);
		    
		}
		
	    }
	    c.println (binary);
	}
    } // main method
} // Char class
