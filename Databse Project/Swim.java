// wesley oliver
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
public class Swim
{
    public static void main (String [] args)
    {
	new mainWin ();

    }
}
class mainWin extends Frame implements ActionListener
{
    MenuBar menu;
    Menu file, Import, Export, athlete, team, meet, report, manage;
    MenuItem open, close, Ievents, Iresults, Eresults, Eevents, compact, backup, exit;
    Image back;
    Toolkit toolkit;
    Frame sub;
    mainWin ()
    {

	setTitle ("Swim Manager");
	toolkit = Toolkit.getDefaultToolkit ();
	setIconImage (toolkit.getImage ("icon.gif"));
	back = toolkit.getImage ("pool.jpg");
	MediaTracker mediaTracker = new MediaTracker (this);
	mediaTracker.addImage (back, 0);
	try
	{
	    mediaTracker.waitForID (0);
	}
	catch (InterruptedException ie)
	{
	}
	//menus
	menu = new MenuBar ();
	crateMenu (menu);
	setMenuBar (menu);
	setBackground (Color.blue);
	setSize (toolkit.getScreenSize ().width, toolkit.getScreenSize ().height);
	setVisible (true);
	addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent e)
	    {
		System.exit (0);
	    }
	}
	);
	sub = new Teams ();

    }


    public void paint (Graphics graphics)
    {

	graphics.drawImage (back, 0, 50, getSize ().width, getSize ().height, null);
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getSource () == exit)
	{
	    System.exit (0);
	}
	else if (e.getSource () == open)
	{
	    sub.setVisible (true);
	    sub.toFront ();
	    setSize (toolkit.getScreenSize ().width, toolkit.getScreenSize ().height - 50);
	}
    }


    void crateMenu (MenuBar menu)
    {
	file = new Menu ("Manager");
	open = new MenuItem ("Open Database");
	open.addActionListener (this);
	file.add (open);
	close = new MenuItem ("Close Database");
	close.addActionListener (this);
	file.add (close);
	file.add ("-");
	Import = new Menu ("Import Meet");
	Ievents = new MenuItem ("Events");
	Ievents.addActionListener (this);
	Import.add (Ievents);
	Iresults = new MenuItem ("Results");
	Iresults.addActionListener (this);
	Import.add (Iresults);
	file.add (Import);
	Export = new Menu ("Export Meet");
	Eevents = new MenuItem ("Events");
	Eevents.addActionListener (this);
	Export.add (Eevents);
	Eresults = new MenuItem ("Results");
	Eresults.addActionListener (this);
	Export.add (Eresults);
	file.add (Export);
	file.add ("-");
	compact = new MenuItem ("Compact And Repair");
	compact.addActionListener (this);
	file.add (compact);
	backup = new MenuItem ("Back Up");
	backup.addActionListener (this);
	file.add (backup);
	file.add ("-");
	exit = new MenuItem ("Exit");
	exit.addActionListener (this);
	file.add (exit);
	menu.add (file);
	athlete = new Menu ("Athlete's");
	athlete.addActionListener (this);
	menu.add (athlete);
	team = new Menu ("Teams");
	team.addActionListener (this);
	menu.add (team);
	meet = new Menu ("Meets");
	meet.addActionListener (this);
	menu.add (meet);
	report = new Menu ("Reports");
	report.addActionListener (this);
	menu.add (report);
	manage = new Menu ("Manage Meet");
	menu.add (manage);
    }
}
class Teams extends Frame implements ActionListener
{
    MenuBar menu;
    Menu file, Import, Export, athlete, team, meet, report, manage;
    MenuItem open, close, Ievents, Iresults, Eresults, Eevents, compact, backup, exit;
    Image back;
    Toolkit toolkit;
    Teams ()
    {

	setTitle ("Teams");
	toolkit = Toolkit.getDefaultToolkit ();
	setIconImage (toolkit.getImage ("icon.gif"));
	//menus
	menu = new MenuBar ();
	crateMenu (menu);
	setMenuBar (menu);
	setBackground (Color.gray);
	setSize (toolkit.getScreenSize ().width, toolkit.getScreenSize ().height - 50);
	setVisible (true);
	setLocation (0, 50);
	addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent e)
	    {
		setVisible (false);
	    }
	}
	);

    }


    public void actionPerformed (ActionEvent e)
    {

    }


    void crateMenu (MenuBar menu)
    {
	file = new Menu ("Manager");
	open = new MenuItem ("Open Database");
	open.addActionListener (this);
	file.add (open);
	close = new MenuItem ("Close Database");
	close.addActionListener (this);
	file.add (close);
	file.add ("-");
	Import = new Menu ("Import Meet");
	Ievents = new MenuItem ("Events");
	Ievents.addActionListener (this);
	Import.add (Ievents);
	Iresults = new MenuItem ("Results");
	Iresults.addActionListener (this);
	Import.add (Iresults);
	file.add (Import);
	Export = new Menu ("Export Meet");
	Eevents = new MenuItem ("Events");
	Eevents.addActionListener (this);
	Export.add (Eevents);
	Eresults = new MenuItem ("Results");
	Eresults.addActionListener (this);
	Export.add (Eresults);
	file.add (Export);
	file.add ("-");
	compact = new MenuItem ("Compact And Repair");
	compact.addActionListener (this);
	file.add (compact);
	backup = new MenuItem ("Back Up");
	backup.addActionListener (this);
	file.add (backup);
	file.add ("-");
	exit = new MenuItem ("Exit");
	exit.addActionListener (this);
	file.add (exit);
	menu.add (file);
	athlete = new Menu ("Athlete's");
	athlete.addActionListener (this);
	menu.add (athlete);
	team = new Menu ("Teams");
	team.addActionListener (this);
	menu.add (team);
	meet = new Menu ("Meets");
	meet.addActionListener (this);
	menu.add (meet);
	report = new Menu ("Reports");
	report.addActionListener (this);
	menu.add (report);
	manage = new Menu ("Manage Meet");
	menu.add (manage);
    }
}


