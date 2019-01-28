//By Wesley Oliver 15 October 2002
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.lang.Integer.*;
public class Snake extends Frame implements ActionListener, KeyListener, ItemListener
{
    Image map;
    addin a;
    boolean board [] [] = new boolean [80] [56];
    private Button StartEndB, add;
    private Choice speed, player;
    private TextField n, u, r, d, l;
    private Canvas area;
    int maxs = 4, numS = 0;
    private newSnake [] nSnake = new newSnake [4];
    String namescoreH [] = new String [40];
    int scoreH [] = new int [40];
    char dkeys [] = new char [16];
    Toolkit tools = Toolkit.getDefaultToolkit ();
    public Snake () throws IOException
    {
	setTitle ("Snake");
	area = new Canvas ();
	add ("Center", area);
	Panel buttons = new Panel ();
	add = new Button ("Add");
	add.addActionListener (this);
	buttons.add (add);
	player = new Choice ();
	player.addItemListener (this);
	buttons.add (player);
	buttons.add (new Label ("Name"));
	n = new TextField ("player 1", 8);
	n.addActionListener (this);
	buttons.add (n);
	buttons.add (new Label ("Up"));
	u = new TextField ("", - 1);
	u.addActionListener (this);
	buttons.add (u);
	buttons.add (new Label ("Right"));
	r = new TextField ("", - 1);
	r.addActionListener (this);
	buttons.add (r);
	buttons.add (new Label ("Down"));
	d = new TextField ("", - 1);
	d.addActionListener (this);
	buttons.add (d);
	buttons.add (new Label ("Left"));
	l = new TextField ("", - 1);
	l.addActionListener (this);
	buttons.add (l);
	buttons.add (new Label ("Game Speed"));
	speed = new Choice ();
	speed.addItem ("Tortes");
	speed.addItem ("Snail");
	speed.addItem ("Ant");
	speed.addItem ("Hare");
	speed.addItem ("Elephant");
	speed.addItem ("Buffalo");
	speed.addItem ("Cheetah");
	buttons.add (speed);
	StartEndB = new Button ("Go!");
	StartEndB.addActionListener (this);
	StartEndB.addKeyListener (this);
	buttons.add (StartEndB);
	add ("North", buttons);

	buttons.setBackground (new Color (186, 253, 179));
	area.setBackground (new Color (193, 209, 253));
	area.setBackground (Color.black);
	BufferedReader rr = new BufferedReader (new FileReader ("SNAKES.srf"));
	for (int n = 0 ; n < 40 ; n++)
	{
	    namescoreH [n] = rr.readLine ();
	    scoreH [n] = Integer.parseInt (rr.readLine ());
	}
	BufferedReader dk = new BufferedReader (new FileReader ("dkey.srf"));
	for (int n = 0 ; n < 16 ; n++)
	{
	    dkeys [n] = dk.readLine ().charAt (0);
	}
	nSnake [numS] = new newSnake (area, numS);
	numS++;
	player.addItem ("player " + numS);
	n.setText (nSnake [player.getSelectedIndex ()].name);
	u.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4)]));

	r.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 1]));

	d.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 2]));

	l.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 3]));

	map = Toolkit.getDefaultToolkit ().getImage ("pool.jpg");
    }


    boolean keych (char k)
    {
	for (int c = 0 ; c < 16 ; c++)
	{

	    if (dkeys [c] == k)
	    {
		return true;

	    }
	}
	return false;
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getSource () == add)
	{
	    if (numS < maxs)
	    {
		nSnake [numS] = new newSnake (area, numS);
		numS++;
		player.addItem ("player " + numS);
		nSnake [numS - 1].keys [0] = dkeys [((numS - 1) * 4)];
		nSnake [numS - 1].keys [1] = dkeys [((numS - 1) * 4) + 1];
		nSnake [numS - 1].keys [2] = dkeys [((numS - 1) * 4) + 2];
		nSnake [numS - 1].keys [3] = dkeys [((numS - 1) * 4) + 3];
	    }
	}
	else
	{
	    if (e.getSource () == n)
	    {
		if (n.getText ().length () < 11)
		{
		    nSnake [player.getSelectedIndex ()].name = n.getText ();
		}
		else
		{
		    n.setText (nSnake [player.getSelectedIndex ()].name);
		}
	    }
	    else
	    {
		if (e.getSource () == u)
		{
		    if (keych (u.getText ().charAt (0)) == false)
		    {
			nSnake [player.getSelectedIndex ()].keys [0] = u.getText ().charAt (0);
			dkeys [player.getSelectedIndex () * 4] = u.getText ().charAt (0);
		    }
		    else
		    {
			u.setText (String.valueOf (dkeys [player.getSelectedIndex () * 4]));
		    }
		}
		else
		{
		    if (e.getSource () == r)
		    {
			if (keych (r.getText ().charAt (0)) == false)
			{
			    nSnake [player.getSelectedIndex ()].keys [1] = r.getText ().charAt (0);
			    dkeys [(player.getSelectedIndex () * 4) + 1] = r.getText ().charAt (0);
			}
			else
			{
			    r.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 1]));
			}
		    }
		    else
		    {
			if (e.getSource () == d)
			{
			    if (keych (d.getText ().charAt (0)) == false)
			    {
				nSnake [player.getSelectedIndex ()].keys [2] = d.getText ().charAt (0);
				dkeys [(player.getSelectedIndex () * 4) + 2] = d.getText ().charAt (0);
			    }
			    else
			    {
				d.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 1]));
			    }
			}
			else
			{
			    if (e.getSource () == l)
			    {
				if (keych (l.getText ().charAt (0)) == false)
				{
				    nSnake [player.getSelectedIndex ()].keys [3] = l.getText ().charAt (0);
				    dkeys [(player.getSelectedIndex () * 4) + 3] = l.getText ().charAt (0);
				}
				else
				{
				    l.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 1]));
				}
			    }
			    else
			    {
				try
				{

				    BufferedWriter kr = new BufferedWriter (new FileWriter ("dkey.srf"));
				    for (int n = 0 ; n < 16 ; n++)
				    {
					kr.write (String.valueOf (dkeys [n]));
					kr.newLine ();
				    }
				    kr.close ();

				}
				catch (IOException h)
				{

				}
				a = new addin (area);
			  

				for (int n = 0 ; n < numS ; n++)
				{

				    nSnake [n].level = (40 - (speed.getSelectedIndex () * 5));
				    nSnake [n].start ();
				}
			     
			    }
			}
		    }
		}
	    }
	}
    }


    public void itemStateChanged (ItemEvent e)
    {
	n.setText (nSnake [player.getSelectedIndex ()].name);

	u.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4)]));

	r.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 1]));

	d.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 2]));

	l.setText (String.valueOf (dkeys [(player.getSelectedIndex () * 4) + 3]));


    }


    public void keyPressed (KeyEvent e)
    {

    }


    public void keyReleased (KeyEvent e)
    {
    }


    public void keyTyped (KeyEvent e)
    {
	for (int nSnakes = 0 ; nSnakes < numS ; nSnakes++)
	{
	    for (int key = 0 ; key < 4 ; key++)
	    {
		if (nSnake [nSnakes].keys [key] == e.getKeyChar ())
		{
		    nSnake [nSnakes].dirw = nSnake [nSnakes].dir;
		    nSnake [nSnakes].dir = key + 1;

		}
	    }
	}
    }








    public static void main (String [] args) throws IOException
    {

	Frame f = new Snake ();
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


    class newSnake extends Thread
    {
	int x [] = new int [1000];
	int y [] = new int [1000];
	int sLong = 30;
	int dir = 4, dirw = 0;
	private Canvas area;
	private int level = 40;
	char keys [] = {'w', 'd', 's', 'a'};
	String name = "playerlert";
	Color me = (Color.blue);
	int score = 0, life = 3;
	int snakenum = 0;
	int pos = 1, cur = 0;
	newSnake (Canvas c, int s)
	{
	    area = c;
	    snakenum = s;
	    x [0] = ((int) Math.round ((Math.random () * 71) + 1) * 10) + 20;
	    y [0] = ((int) Math.round ((Math.random () * 41) + 1) * 10) + 20;
	}


	public void run ()
	{

	    a.score ();

	    while (true)
	    {
		if ((dir == 1 & dirw == 3) || (dir == 3 & dirw == 1) || (dir == 4 & dirw == 2) || (dir == 2 & dirw == 4))
		{
		    dir = dirw;
		}
		switch (dir)
		{
		    case 1:
			x [pos] = x [cur];
			y [pos] = y [cur] - 10;
			break;
		    case 2:
			x [pos] = x [cur] + 10;
			y [pos] = y [cur];
			break;
		    case 3:
			x [pos] = x [cur];
			y [pos] = y [cur] + 10;
			break;
		    case 4:
			x [pos] = x [cur] - 10;
			y [pos] = y [cur];
			break;
		}

		if (x [pos] < 0)
		{
		    x [pos] = 780;
		}
		if (x [pos] > 780)
		{
		    x [pos] = 0;
		}
		if (y [pos] < 20)
		{
		    y [pos] = 520;
		}
		if (y [pos] > 520)
		{
		    y [pos] = 20;
		}
		cur = pos;
		pos++;
		if (pos > (sLong - 1))
		{
		    pos = 0;
		}
		if (board [(x [cur] / 10)] [(y [cur] / 10)] == true)
		{
		    if (y [cur] != 0)
		    {
			a.die (snakenum);
		    }
		}
		else
		{
		    board [(x [cur] / 10)] [(y [cur] / 10)] = true;
		    if (x [pos] == a.xD & y [pos] == a.yD)
		    {
			a.dot ();
			score = score + (7 - ((level - 10) / 5));
			if (sLong > 100)
			{
			    if (level > 100)
			    {
				level = level - 5;
			    }
			}
			else
			{
			    sLong++;
			}
			a.score ();
		    }
		    draw ();
		}
		try
		{
		    sleep (level);
		}
		catch (InterruptedException e)
		{
		}
	    }
	}


	void draw ()
	{
	    //System.out.println ("draw");
	    Graphics g = area.getGraphics ();

	    if (y [pos] != 0)
	    {
		g.drawImage (map, x [pos], y [pos], x [pos] + 10, y [pos] + 10, x [pos], y [pos], x [pos] + 10, y [pos] + 10, null);

		// g.clearRect (x [pos], y [pos], 10, 10);
		board [(x [pos] / 10)] [(y [pos] / 10)] = false;
	    }
	    g.setColor (Color.blue);
	    g.fillRect (x [cur], y [cur], 10, 10);

	}


    }


    class addin
    {
	private Canvas area;
	int xD, yD, de = 0;
	boolean ok = true, di = true;

	addin (Canvas a)
	{
	    area = a;
	    dot ();

	}


	synchronized void dot ()
	{
	    for (int i = 0 ; i < 20 ; i++)
	    {
		tools.beep ();
	    }
	    ok = true;
	    while (ok)
	    {
		ok = false;
		xD = ((int) Math.round ((Math.random () * 71) + 1) * 10) + 20;
		yD = ((int) Math.round ((Math.random () * 41) + 1) * 10) + 20;

		for (int nSnakes = 0 ; nSnakes < numS ; nSnakes++)
		{
		    for (int lon = 1 ; lon < nSnake [nSnakes].sLong ; lon++)
		    {
			if (nSnake [nSnakes].x [lon] == xD & nSnake [nSnakes].y [lon] == yD)
			{
			    ok = true;
			    nSnakes = numS;
			    break;
			}
		    }
		}
	    }
	    Graphics g = area.getGraphics ();
	    g.setColor (Color.yellow);
	    g.fillOval (xD, yD, 9, 9);
	}


	synchronized void score ()
	{
	    Graphics g = area.getGraphics ();
	    g.setColor (new Color (193, 197, 133));
	    g.fillRect (0, 0, 800, 19);
	    for (int nSnakes = 0 ; nSnakes < numS ; nSnakes++)
	    {
		g.setColor (nSnake [nSnakes].me);
		if (nSnake [nSnakes].life < 0)
		{
		    g.drawString (nSnake [nSnakes].name + " | " + nSnake [nSnakes].score + " | Game Over |", (nSnakes * 170) + 5, 15);

		}
		else
		{
		    g.drawString (nSnake [nSnakes].name + " | " + nSnake [nSnakes].score + " | " + nSnake [nSnakes].life + " |", (nSnakes * 170) + 5, 15);
		}
	    }

	}
	synchronized void die (int s)
	{
	    Graphics g = area.getGraphics ();
	    if (nSnake [s].life != -1)
	    {
		nSnake [s].life = nSnake [s].life - 1;
	    }
	    g.setColor (new Color (193, 209, 203));
	    for (int d = 0 ; d < nSnake [s].sLong ; d++)
	    {
		if (nSnake [s].cur != d)
		{
		    board [(nSnake [s].x [d] / 10)] [(nSnake [s].y [d] / 10)] = false;
		    g.fillRect (nSnake [s].x [d], nSnake [s].y [d], 10, 10);
		    nSnake [s].x [d] = 0;
		    nSnake [s].y [d] = 0;
		}
	    }
	    if (nSnake [s].life == -1)
	    {
		de = de + 1;
		score ();
		if (de == numS)
		{

		    try
		    {
			top20 ();
		    }

		    catch (IOException e)
		    {

		    }
		}
		nSnake [s].stop ();


	    }
	    nSnake [s].sLong = 10;
	    nSnake [s].pos = 1;
	    nSnake [s].cur = 0;
	    nSnake [s].x [0] = ((int) Math.round ((Math.random () * 71) + 1) * 10) + 20;
	    nSnake [s].y [0] = ((int) Math.round ((Math.random () * 41) + 1) * 10) + 20;


	    score ();

	}
	synchronized void top20 () throws IOException
	{
	    Graphics g = area.getGraphics ();
	    g.setFont (new Font ("TimesRoman", 0, 21));
	    for (int s = 0 ; s < numS ; s++)
	    {
		for (int a = 0 ; a < 40 ; a++)
		{
		    if (nSnake [s].score > scoreH [a])
		    {
			for (int b = 38 ; b > a - 1 ; b--)
			{
			    scoreH [b + 1] = scoreH [b];
			    namescoreH [b + 1] = namescoreH [b];
			}
			scoreH [a] = nSnake [s].score;
			namescoreH [a] = nSnake [s].name;
			break;
		    }
		}
	    }
	    g.clearRect (0, 0, 800, 500);
	    g.drawString ("Top 40 score's", 345, 40);
	    g.drawLine (345, 43, 460, 43);
	    for (int x = 70, a = 0, y = 150 ; scoreH [a] != 0 & a < 40 ; a++, x += 24)
	    {
		g.drawString ("" + (a + 1), y, x);
		g.drawString ("" + namescoreH [a], y + 50, x);
		g.drawString ("" + scoreH [a], y + 180, x);
		if (a == 19)
		{
		    y = 440;
		    x = 46;
		}
	    }

	    BufferedWriter wr = new BufferedWriter (new FileWriter ("SNAKES.srf"));
	    for (int n = 0 ; n < 40 ; n++)
	    {
		wr.write (namescoreH [n]);
		wr.newLine ();
		wr.write (Integer.toString (scoreH [n]));
		wr.newLine ();
	    }
	    wr.close ();
	}


    }
}


