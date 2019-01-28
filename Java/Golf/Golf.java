//By Wesley Oliver 15 October 2002
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.lang.Integer.*;
public class Golf extends Frame implements ActionListener, KeyListener
{

    private static Canvas area;
    private static Graphics g;
    private static int strength = 0;
    Toolkit tools = Toolkit.getDefaultToolkit ();







    public Golf ()
    {
	tools.sync ();
	setTitle ("Golf");
	area = new Canvas ();
	area.processKeyEvent (new KeEventHandler ());
	add ("Center", area);
	area.setBackground (new Color (204, 255, 182));
	g = area.getGraphics ();
	test ();
    }


    public void test ()
    {

    }


    public void actionPerformed (ActionEvent e)
    {

    }


    public void keyPressed (KeyEvent e)
    {
	bar power = new bar (100, 100, 200, 20);
    }


    public void keyReleased (KeyEvent e)
    {
    }


    public void keyTyped (KeyEvent e)
    {

    }


    public static void main (String [] args) throws IOException
    {

	Frame f = new Golf ();
	f.addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent e)
	    {
		System.exit (0);
	    }
	}


	);

	f.setSize (800, 600);
	f.setVisible (true);
	f.setBackground (new Color (248, 185, 143));

    }


    class bar extends Thread
    {
	int x, y, len, hght;
	bar (int xc, int yc, int length, int height)
	{
	    x = xc;
	    y = yc;
	    len = length;
	    hght = height;
	    g.drawRect (x + hght, y + hght, len - (hght * 2), hght);
	    g.fillArc (x, y, hght, hght, 45, 180);

	}
	public void run ()
	{

	}
    }
}


