import java.io.*;
public class LearnerApp
{
    public static void main (String [] args)
    {
	new learner ();
    }
}

class learner
{
    int order [] [];
    Input in = new Input ();
    int max = -1, index = 0;
    pupil learners [];
    learner ()
    {
	System.out.println ("Please Enter the Maxium number of leaners you intend to have, then <ENTER>");
	max = in.readInt ();
	learners = new pupil [max];
	menu ();
    }


    void clear ()
    {
	System.out.println ("\n\n");
	for (int i = 0 ; i < 78 ; i++)
	{
	    System.out.print ("-");
	}
	for (int i = 0 ; i < 5 ; i++)
	{
	    System.out.println ();
	}


    }


    void menu ()
    {
	clear ();
	while (true)
	{

	    System.out.println ("-------- Menu --------\n");
	    System.out.println ("Please an option, then <Enter>\n");
	    System.out.println ("A Input Learner data");
	    if (index > 0)
	    {
		System.out.println ("B Display each learner's average");
		System.out.println ("C Display each learner's best results");
		System.out.println ("D Sort learner's alphabetically");
		System.out.println ("E Rank leaner's in descending oder of average");
		System.out.println ("F Search for a learner's");
		System.out.println ("G Display All learner's");
	    }
	    System.out.println ("H Exit");
	    char option = Character.toUpperCase (in.readChar ());

	    switch (option)
	    {
		case 65:
		    if (index < max)
		    {
			input ();
		    }
		    else
		    {
			System.out.println ("The Maxium number of leaners has been reached");
		    }
		    break;
		case 66:
		    if (index > 0)
		    {
			average ();
		    }

		    break;
		case 67:
		    if (index > 0)
		    {
			highest ();
		    }

		    break;
		case 70:
		    if (index > 0)
		    {
			search ();
		    }
		    break;
		case 71:
		    if (index > 0)
		    {
			display ();
		    }
		    break;
		case 72:

		    System.exit (0);
		    break;
	    }
	    clear ();
	}
    }


    void input ()
    {
	;
	System.out.println ("Please enter the following information about the learner");
	System.out.print ("Surname ");
	String sur = in.readLine ();
	System.out.print ("Name ");
	String name = in.readLine ();
	System.out.print ("Term 1 ");
	int t1 = in.readInt ();
	System.out.print ("Term 2 ");
	int t2 = in.readInt ();
	System.out.print ("Term 3 ");
	int t3 = in.readInt ();
	learners [index] = new pupil (sur, name, t1, t2, t3);
	index++;
	menu ();
    }


    void average ()
    {
	System.out.println ("------- Learner's Averages ------");
	System.out.println ("Surname             Name                AVG\n");
	for (int l = 0 ; l < index ; l++)
	{
	    System.out.println (learners [l].getSurname () + learners [l].getName () + learners [l].getAverage ());
	}
    }


    void highest ()
    {
	System.out.println ("------- Learner's Best Mark ------");
	System.out.println ("Surname             Name                Mark\n");
	for (int l = 0 ; l < index ; l++)
	{
	    System.out.println (learners [l].getSurname () + learners [l].getName () + learners [l].getHighestMark ());
	}
    }


    void display ()
    {
	System.out.println ("------- All Learner's ------");
	System.out.println ("Surname             Name                T1   T2   T3\n");
	for (int l = 0 ; l < index ; l++)
	{
	    System.out.println (learners [l].tostring ());
	}
    }


    void search ()
    {
	System.out.println ("Enter the Surname to search for, then <ENTER>");
	String search = in.readLine ();
	System.out.println ("\nSurname             Name                T1   T2   T3\n");
	for (int s = 0 ; s < index ; s++)
	{
	    if (learners [s].getSurname ().trim ().equalsIgnoreCase (search))
	    {
		System.out.println (learners [s].tostring ());
	    }
	}
    }
}

class Input
{
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    Input ()
    {

    }


    int readInt ()
    {
	int num = 0;
	String str = readLine ();
	num = Integer.parseInt (str);
	return num;
    }


    String readLine ()
    {
	String i = null;
	while (i == null)
	{
	    System.out.print (">> ");
	    try
	    {
		i = in.readLine ().trim ();
	    }
	    catch (IOException e)
	    {
		i = null;
	    }
	    if (i.length () == 0)
	    {
		i = null;
	    }
	}
	return i;
    }


    char readChar ()
    {
	char c = 0;
	boolean ok = false;
	while (!ok)
	{
	    System.out.print (">>");
	    try
	    {
		c = (char) System.in.read ();
		in.readLine ();
		ok = true;
	    }
	    catch (Exception e)
	    {
	    }
	}


	return c;
    }
}

