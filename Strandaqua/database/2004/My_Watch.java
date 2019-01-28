// The "My_Watch" class.
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.*;
public class My_Watch
{
    public static void main (String [] args)
    {
	new Watch ();
    } // main method
} // My_Watch class

class Watch extends Frame implements ActionListener
{
    Button con = new Button ("Start");
    Button reset = new Button ("Reset");
    Display dis = new Display ();
    Time stime = new Time (new Date ().getTime ());
    Watch ()
    {
	Panel cp = new Panel ();
	con.addActionListener (this);
	cp.add (con);
	reset.addActionListener (this);
	cp.add (reset);
	add (cp, "South");
	add (dis, "Center");
	setTitle ("My Watch");
	setSize (200, 150);
	setBackground (Color.green);
	addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent e)
	    {
		System.exit (0);
	    }
	}
	);
	setVisible (true);
	dis.repaint ();
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getSource () == con)
	{
	    if (con.getLabel ().equals ("Start"))
	    {

		con.setLabel ("Stop");
	    }
	    else
	    {
		con.setLabel ("Start");
		dis.repaint ();
	    }
	}

    }


    class Display extends Canvas
    {
	public void paint (Graphics g)
	{

	    g.setColor (Color.lightGray);
	    g.fillRect (20, 40, 150, 30);
	    g.setColor (Color.black);
	    g.drawRect (20, 40, 150, 30);
	    g.setFont (new Font ("TimesRoman", 0, 30));
	    g.setColor (Color.red);
	    g.drawString (stime.getHours () + " : " + stime.getMinutes () + " : " + stime.getSeconds (), 20, 65);
	}
    }
}
