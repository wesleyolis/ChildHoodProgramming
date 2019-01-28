import java.awt.Toolkit;
public class Beep
{
    public static void main (String [] args)
    {
	Toolkit wes = Toolkit.getDefaultToolkit ();
	System.out.println ("Hallo");
	for (int i = 0 ; i < 30000 ; i++)
	{
	    wes.beep ();
	    wes.sync ();
	}
	System.out.println ("Hallo");
	// Place your code here
    } // main method
} // Beep class
