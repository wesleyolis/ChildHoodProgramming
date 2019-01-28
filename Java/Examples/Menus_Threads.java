// By Wesley Oliver
import java.awt.event.*;
import java.awt.*;
public class Menus_Threads
{
    public static void main (String [] args)
    {
	new mainWin ();
    }
}
class mainWin extends Frame implements ActionListener
{
    static ActionEvent Action;
    mainWin ()
    {
	setTitle ("Menu And Threads Example");
	setVisible (true);
	setSize (800, 600);
	MenuBar menu = new MenuBar ();
	Menu file = new Menu ("File");
	MenuItem nRobot = new MenuItem ("New Robot");
	nRobot.addActionListener (this);
	file.add (nRobot);
	file.add ("-");
	Menu config = new Menu ("Configuration");
	config.add ("Speed");
	config.add ("Colors");
	file.add (config);
	file.add ("-");
	file.add ("Exit");
	menu.add (file);
	Amenu test = new Amenu ("Test");
	test.addActionListener (this);
	menu.add (test);
	setMenuBar (menu);
    }


    public void actionPerformed (ActionEvent e)
    {
	System.exit (0);
    }
}

class Amenu extends Menu implements ActionListener
{
    Amenu (String name)
    {
	setLabel (name);
	setEnabled (true);
	enableEvents (Event.ACTION_EVENT);
	//new AWTEvent (new Event (this, Event.ACTION_EVENT, this))

    }


    public void actionPerformed (ActionEvent e)
    {
	System.exit (0);
    }
}

