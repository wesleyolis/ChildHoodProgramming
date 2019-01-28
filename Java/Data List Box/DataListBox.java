// The "DataListBox" class.
import java.awt.*;
import java.util.*;
public class DataListBox extends Component
{
    public static void main (String[] args)
    {
	Frame f = new Frame ();
	DataListBox l = new DataListBox (3);
	l.add(
	f.add (l);
	f.setSize (300, 300);
	f.setBackground (Color.red);
	f.setVisible (true);

	// Place your code here
    } // main method


    int coloms;
    Vector data[];
    DataListBox (int col)
    {
	coloms = col;
	data = new Vector [coloms];
	for (int c = 0 ; c < coloms ; c++)
	{
	    data [c] = new Vector (10, 5);
	}

	setSize (200, 200);
	setVisible (true);
    }


    public void paint (Graphics g)
    {
	g.setColor (Color.gray);
	g.fillRect (0, 0, 200, 200);


    }




} // DataListBox class
