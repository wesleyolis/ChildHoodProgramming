// Wesley Oliver Grd 12E1 Parel Vallei High School
import java.io.*;
public class WebCompiler
{
    public static void main (String [] args)
    {
	new Menus ();
    }



}
class Menus
{

    Menus ()
    {
	menu ();
    }


    void menu ()
    {
	String option;
	while (true)
	{
	    option = -1;
	    System.out.println ("-------- Menu --------");
	    System.out.println ("1 Open 'Team Manager 2' DataBase");
	    System.out.println ("2 Meets");
	    System.out.println ("3 Compile Meets");
	    System.out.println ("0 Exit");

	    switch (option)
	    {
		case 0:
		    break;
		case 1:
		    break;
		case 2:
		    break;
		case 3:
		    break;
		default:
		    break;
	    }

	}
    }


    void meets ()
    {
	System.out.println ("-------- Meets -------");
	System.out.println ("1 List All");
	System.out.println ("2 List Previously Compiled");
	System.out.println ("3 List Previosly not Compiled");
	System.out.println ("4 Reset Complied List");
	System.out.println ("0 Back");
    }


    void compile ()
    {
	System.out.println ("------- Compile -------");
	System.out.println ("1 All, but Previously Compiled");
	System.out.println ("2 Select a Meet");
	System.out.println ("3 All, but Previously Compiled and don't update compiled list");
	System.out.println ("4 All with Results and don't Update Compiled List");
	System.out.println ("0 Back");
    }
}
class Input
{
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

    boolean confirm (String msg)
    {
    System.out.println(Confirm);
	try
	{
	    option = Integer.pareseInt (in.readLine ());
	}
	catch (Exception e)
	{

	}
    }
}
