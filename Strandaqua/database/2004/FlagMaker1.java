import java.awt.*;
import java.awt.event.*;

class FlagMaker1 extends Frame
{

    /* Flag drawing program       J M Bishop April 2000
     * --------------------
     * Illustrates colour and simple graphic output
     */
    static int screenx = 800, screeny = 600;

    FlagMaker1 ()
    {
	add ("Center", new Flag ());
	// Enable the program to end when the window is closed
	addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent e)
	    {
		System.exit (0);
	    }
	}
	);

	// Set the frame's title and size and activate the drawing
	// described by the paint method.
	setTitle ("A Flag");
	setSize (screenx, screeny);
	setVisible (true);
    }


    public static void main (String [] args)
    {
	new FlagMaker1 ();
    }
}

class Flag extends Canvas
{
    public void paint (Graphics g)
    {
	// Draw the flag using coloured rectangles
	g.setColor (Color.black);
	g.fillRect (40, 40, (FlagMaker1.screenx - 80) / 3, FlagMaker1.screeny - 140);
	g.setColor (Color.red);
	g.fillRect (40 + ((FlagMaker1.screenx - 80) / 3), 40, ((FlagMaker1.screenx - 80) / 3), (FlagMaker1.screeny - 140));
	g.setColor (Color.yellow);
	g.fillRect (40 + ((FlagMaker1.screenx - 80) / 3) * 2, 40, ((FlagMaker1.screenx - 80) / 3), (FlagMaker1.screeny - 140));
	g.setColor (Color.blue);
	g.fillOval (60 + ((FlagMaker1.screenx - 80) / 3), ((FlagMaker1.screeny - 80) / 2) - ((((FlagMaker1.screenx - 80) / 3) - 40) / 2), ((FlagMaker1.screenx - 80) / 3) - 40, ((FlagMaker1.screenx - 80) / 3) - 40);
	// Label the drawing
	g.setColor (Color.black);
	g.drawString ("Germany", FlagMaker1.screenx / 2, FlagMaker1.screeny - 80);
    }
}
