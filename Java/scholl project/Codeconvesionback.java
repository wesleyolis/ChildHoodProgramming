// The "Char" class.
import java.awt.*;
import hsa.Console;

public class Codeconvesionback
{
    static Console c;           // The output console

    public static void main (String [] args)
    {
	c = new Console ();
	for (;;)
	{
	    String binary = "";
	    c.println ("Please enter a integer");
	    int num = c.readInt ();
	    while (num > 0)
	    {
		if (num % 16 < 10)
		{
		    binary = (num % 16) + binary;
		    num = num / 16;
		    
		}
		if(num % 16 > 9 )
		{

		    char num7 = (char) ((num % 16) - 9 + 64);
		    char num1 [] = {num7};
		    binary = new String (num1) + binary;
		    num = num / 16;
		    
		}
		
	    }
	    while (binary.length () < 16)
	    {
		binary = "0" + binary;
	    }
	    if (binary.length () > 16)
	    {
		c.println ("this number is to big for hexidecimal code");
	    }
	    if (binary.length () == 16)
	    {
		c.println (binary);
	    }
	}
    } // main method
} // Char class
