//By Wesley Oliver 15 October 2002
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.lang.Integer.*;
public class Snake extends Frame implements ActionListener, KeyListener
{
    addin a;
    private Button StartEndB;
    private Canvas area;
    int maxs = 4, numS = 0;
    private newSnake [] nSnake = new newSnake [4];
    String namescoreH [] = new String [20];
    int scoreH [] = new int [20];
    
    
    


    

    public Snake () throws IOException,NumberFormatException
    {
	setTitle ("Snake");
	area = new Canvas ();
	add ("Center", area);
	Panel buttons = new Panel ();
	StartEndB = new Button ("Go!");
	StartEndB.addActionListener (this);
	StartEndB.addKeyListener (this);
	buttons.add (StartEndB);
	add ("North", buttons);
	buttons.setBackground (new Color (186, 253, 179));
	area.setBackground (new Color (193, 209, 253));
	BufferedReader rr = new BufferedReader (new FileReader ("snake.txt"));


	for (int n = 0 ; n < 20 ; n++)
	{
	    namescoreH [n] = rr.readLine ();
	    scoreH [n] = Integer.parseInt(rr.readLine ());
	}


    }


    public void actionPerformed (ActionEvent e)
    {
	if (numS == 0)
	{
	    a = new addin (area);
	}
	nSnake [numS] = new newSnake (area, 40, numS);
	nSnake [numS].start ();
	if (numS == 1)
	{
	    nSnake [numS].keys [0] = 'i';
	    nSnake [numS].keys [1] = 'l';
	    nSnake [numS].keys [2] = 'k';
	    nSnake [numS].keys [3] = 'j';
	    nSnake [numS].me = (Color.red);
	}
	numS++;

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


    void s ()
    {

    }




    public static void main (String [] args)throws IOException,NumberFormatException
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


	int sLong = 10;
	int dir = 4, dirw = 0;
	private Canvas area;
	private int level = 40;
	char keys [] = {'w', 'd', 's', 'a'};
	String name = "player";
	Color me = (Color.blue);
	int score = 0, life = 3;
	int snakenum = 0;
	newSnake (Canvas c, int lev, int s)
	{
	    area = c;
	    level = lev;
	    snakenum = s;

	    x [2] = ((int) Math.round ((Math.random () * 71) + 1) * 10) + 20;
	    y [2] = ((int) Math.round ((Math.random () * 41) + 1) * 10) + 20;


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
			x [1] = x [2];
			y [1] = y [2] - 10;
			break;
		    case 2:
			x [1] = x [2] + 10;
			y [1] = y [2];
			break;
		    case 3:
			x [1] = x [2];
			y [1] = y [2] + 10;
			break;
		    case 4:
			x [1] = x [2] - 10;
			y [1] = y [2];
			break;
		}

		if (x [1] < 0)
		{
		    x [1] = 780;
		}

		if (x [1] > 780)
		{
		    x [1] = 0;
		}
		if (y [1] < 20)
		{
		    y [1] = 530;
		}
		if (y [1] > 530)
		{
		    y [1] = 20;
		}
		for (int l = 2 ; l < sLong ; l++)
		{
		    if (x [1] == x [l] & y [1] == y [l])
		    {
			a.die (snakenum);
		    }
		}
		draw ();
		try
		{
		    sleep (level);
		}
		catch (InterruptedException e)
		{
		}
		if (x [1] == a.xD & y [1] == a.yD)
		{
		    a.dot ();
		    score += 10;
		    sLong++;
		    a.score ();
		}
		for (int loop = sLong - 1 ; loop > 0 ; loop--)
		{
		    y [loop] = y [loop - 1];
		    x [loop] = x [loop - 1];
		}

	    }
	}


	void draw ()
	{
	    Graphics g = area.getGraphics ();
	    if (y [sLong - 1] != 0)
	    {
		g.clearRect (x [sLong - 1], y [sLong - 1], 10, 10);
	    }
	    g.setColor (me);
	    g.fillRect (x [1], y [1], 10, 10);

	}
    }


    class addin
    {
	private Canvas area;
	int xD, yD;
	boolean ok = true, di = true;

	addin (Canvas a)
	{
	    area = a;
	    dot ();

	}


	synchronized void dot ()
	{
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
		    g.drawString (nSnake [nSnakes].name + " | " + nSnake [nSnakes].score + " | Game Over |", (nSnakes * 150) + 5, 15);

		}
		else
		{
		    g.drawString (nSnake [nSnakes].name + " | " + nSnake [nSnakes].score + " | " + nSnake [nSnakes].life + " |", (nSnakes * 150) + 5, 15);
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
	    for (int d = 1 ; d < nSnake [s].sLong + 1 ; d++)
	    {
		g.clearRect (nSnake [s].x [d], nSnake [s].y [d], 10, 10);
		nSnake [s].x [d] = 0;
		nSnake [s].y [d] = 0;
	    }
	    if (nSnake [s].life == -1)
	    {
		score ();
		nSnake [s].stop ();
	    }
	    nSnake [s].sLong = 10;
	    nSnake [s].x [1] = ((int) Math.round ((Math.random () * 71) + 1) * 10) + 20;
	    nSnake [s].y [1] = ((int) Math.round ((Math.random () * 41) + 1) * 10) + 20;

	    score ();

	}
    }
}


