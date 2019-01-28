// The "Basicthread" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class Basicthread extends Applet implements ActionListener
{
    private Button start;
    private newSnake [] nSnake;
    public void init ()
    {
	newSnake [] nSnake = new newSnake [4];
	start = new Button ("Add player");
	start.addActionListener (this);
	add ("North", start);
    } // init method


    public void actionPerformed (ActionEvent e)
    {
	nSnake [0] = new newSnake ();
	nSnake [0].start ();
    }


    class newSnake extends Thread
    {
	public void run ()
	{
	    try
	    {
		sleep (3000);
	    }
	    catch (InterruptedException e)
	    {
	    }
	   Graphics g = getGraphics ();
	   g.setColor(new Color(50,80,90));
	   g.fillRect(0,0,100,100);

	}
    }


   
	public void paint (Graphics g)
	{
	    // Place the body of the drawing method here
	} // paint method
    
} // Basicthread class
