//By Wesley Oliver 15 October 2002
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.lang.Integer.*;

public class Snake
{
    public static void main (String [] args)
    {
	window w = new window ();
    }
}

class window
{
    Frame f = new Frame ();
    MenuBar bar = new MenuBar ();
    window ()
    {
	new Menus ();
	f.setMenuBar (bar);
	f.addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent e)
	    {
		System.exit (0);

	    }
	}
	);
	f.setBackground (new Color (50, 0, 50));
	f.setSize (640, 480);
	f.setTitle ("Snake");
	f.setVisible (true);
    }


    class Menus implements ActionListener
    {
	Menu menu = new Menu ("File");
	Menu con = new Menu ("Configuration");
	Menus ()
	{
	    //Menu Items
	    menu.add("New Game");
	    menu.add("Restart");
	    menu.add ("World");
	    menu.add ("Speed");
	    menu.add ("Top Score's");
	    menu.add ("-");
	    menu.add (con);
	    con.add ("Controls");
	    menu.add ("-");
	    menu.add ("Exit");
	    bar.add(menu);
	}

	public void actionPerformed (ActionEvent e)
	{

	}
    }
}





