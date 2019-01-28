import java.awt.*;
import hsa.Console;

public class Project5
{
    static Console c;           // The output console

    public static void main (String [] args)
    {

	//because of the loop for headings this have seemd to have to a pear in bit of back wards order <<
	//but achully run in the right direction if you follow the loop <<
	c = new Console ();
	int option_base1 = 0, option_base2 = 0, option_base3 = 0, option_base4 = 0, base; //declaring base and option bases
	//decalring
	int loop = 0, numberI = 1, num_char1; //number value of char
	int Display = 1; //what area of the resaults to display
	int option_main = 9, option_second = 3, option_third = 8, option_four = 3, option_five = 5; //option variables,main menu,second menu
	String op1 = "Binary", op2 = "Octal", op3 = "Hexadecimal";                                 //}>Option menu declared
	String op4 = "Decimal", op5 = "Number", op6 = "Any other base";                 //}> so option can be
	String op7 = "Numbers to Words", op8 = "Exit";                                            //} > qucikly convayed
	String heading1 = " ", heading = "Number Converter"; //heading varible for menus which <<     > to menu heading and
	//are initialized with programe name <<     > accessed quickly
	//> with out repeating
	String ops1 = "", ops2 = "", ops3 = "", ops4 = ""; //op for option, s for second(second menu)
	String code = ""; //hold the code
	int codenum = 0; //hold the code
	for (;;) //initializing programe loop and heading loop 'Start'
	{
	    c.print ("********************************************************************************");  //prints heading
	    if (option_main < 7 & option_main != 0 & ((option_second > 2 & option_third > 6) || (option_second < 3 & option_third > 6)))
	    {
		heading1 = heading + " to ...";
	    }
	    else
	    {
		heading1 = heading;
	    }

	    if (option_third < 6)                                                                               //top in border
	    {
		c.print ("", (70 - heading1.length ()) / 2);                                                    //of menus on
		c.print (heading1);
		c.print (" (Resault)");
	    }
	    else
	    {
		c.print ("", (80 - heading1.length ()) / 2);                                                    //of menus on
		c.print (heading1);
	    }
	    c.println ("\n********************************************************************************"); //

	    if (option_main == 8)
	    {
		c.print ("\n\t\t\t\t   Click Close");
		break;
	    }

	    if (option_main < 6 & option_main != 0 & option_third < 7 & option_second < 3 & option_four > 2 & option_five > 4)
	    {
		c.println ("\n\tResaults\n");


		c.setColor (Color.black);
		c.drawRect (595, 100, 12, 285);
		c.fillPolygon (new int []
		{
		    596, 601, 606
		}
		, new int []
		{
		    98, 88, 98
		}
		, 3);
		c.drawString ("Page up", 580, 70);
		c.drawString ("Press 1", 580, 85);
		c.fillPolygon (new int []
		{
		    596, 601, 606
		}
		, new int []
		{
		    388, 398, 388
		}
		, 3);
		c.drawString ("Page down", 570, 410);
		c.drawString (" Press 2", 575, 425);
		for (;;)
		{

		    break;
		}
		break;
	    }



	    if (option_main < 6 & option_main != 0 & option_third > 7 & option_second < 3 & option_four > 2)
	    {
	    
		
		c.println ("\n\tEnter your selection from the options!\n");
		c.print ("\t1. " + heading + " convert to ");
		if (option_main > 1)
		{
		    c.println (op1);
		    option_base1 = 2;
		    ops1 = op1;
		}
		else
		{
		    c.println (op2);
		    option_base1 = 8;
		    ops1 = op2;
		}
		c.print ("\t2. " + heading + " convert to ");
		if (option_main > 2)
		{
		    c.println (op2);
		    option_base2 = 8;
		    ops2 = op2;
		}
		else
		{
		    c.println (op3);
		    option_base2 = 16;
		    ops2 = op3;
		}
		c.print ("\t3. " + heading + " convert to ");
		if (option_main > 3)
		{
		    c.println (op3);
		    option_base3 = 16;
		    ops3 = op3;
		}
		else
		{
		    c.println (op4);
		    option_base3 = 10;
		    ops3 = op4;
		}
		c.print ("\t4. " + heading + " convert to ");

		if (option_main > 4)
		{
		    c.println (op4);
		    option_base4 = 10;
		    ops3 = op3;
		}
		else
		{
		    c.println (op5);
		    option_base4 = 0;
		    ops4 = op5;
		}
		c.println ("\t5. " + heading + " any other base");
		c.println ("\t6. " + heading + " convert to all , expect any other base");
		c.println ("\t7. Return to 'main menu'");
		c.print ("\n\t ~> ");
		option_third = c.readInt ();
		if(option_third == 5)
		{
		c.println("\tPlease enter a base smaller than 62");
		c.print ("\n\t ~> ");
		base = c.readInt();
		}
		c.clear ();
	    }

	    if (option_main < 6 & option_main != 0 & option_third > 7 & option_second > 2 & option_four > 2)
	    {
		if (option_main < 5)
		{
		    c.println ("\n\tEnter NOT MORE THAN '9' code's to convert, separated by\n\t':' (i.e '1001:1110:1010')");
		    c.print ("\n\t ~> ");
		    code = c.readString ();

		    if (code.equals (":"))
		    {
			
			option_second = 3;
			c.clear ();
		    }
		    else
		    {
			option_second = 1;
			c.clear ();
			
			for (loop = 0 ; loop < (code.length ()) ; loop++)
			{
			    num_char1 = (int) (code.charAt (loop));
			    if(num_char1 == 58 & (loop + 1) != code.length() )
			    {
			    
			    numberI ++;
			    }
			    switch (option_main)
			    {
			    case 4:
				    if ((num_char1 > 47 & num_char1 < 58) || code.charAt (loop) == ':')
				    {
				    }
				    else
				    {
					option_second = 3;
				    }
				    break;
				case 3:
				    if ((num_char1 > 47 & num_char1 < 58) || (num_char1 > 64 & num_char1 < 71) || code.charAt (loop) == ':')
				    {
				    }
				    else
				    {
					option_second = 3;
				    }
				    break;
				case 2:
				    if ((num_char1 > 47 & num_char1 < 56) || code.charAt (loop) == ':')
				    {
				    }
				    else
				    {
					option_second = 3;
				    }
				    break;
				case 1:
				    if (code.charAt (loop) != '0' & code.charAt (loop) != '1' & code.charAt (loop) != ':')
				    {
					option_second = 3;
				    }
				    break;



			    }
			  
			}

		    }

		}
		else
		{
		    c.println ("\n\tEnter NOT MORE THAN '9' positve numbers to convert, separated by\n\t':' (i.e '1001:1110:1010')");
		    c.print ("\n\t ~> ");
		    codenum = c.readInt ();
		    
			option_second = 1;
			c.clear ();
		    
		}


	    }
	    
	    if(option_main == 6)
	    {
	    c.println("\tPlease enter the base Smaller than 62, then the code");
	    c.print("\n\tBase ~> ");
	    base = c.readInt();
	    c.print("\n\tCode ~> ");
	    code = c.readString ();

	    }
	    if (option_main > 8 || option_main == 0)
	    {
		c.println ("\n\tPlease enter the number of the following option you want to use!\n");
		c.print ("\t1. " + op1);
		c.println (" to ...");
		c.print ("\t2. " + op2);
		c.println (" to ...");
		c.print ("\t3. " + op3);
		c.println (" to ...");
		c.print ("\t4. " + op4);
		c.println (" to ...");
		c.print ("\t5. " + op5);
		c.println (" to ...");
		c.print ("\t6. " + op6);
		c.println (" to ...");
		c.println ("\t7. " + op7);
		c.println ("\t8. " + op8);
		c.print ("\n\t ~> ");
		option_main = c.readInt ();
		c.clear ();
	    }
	    //second menu options
	    if (option_third < 8)
	    {
		if (option_third == 1)
		{
		    base = option_base1;
		    heading = (heading + " to " + ops1);

		}
		if (option_third == 2)
		{
		    base = option_base2;
		    heading = (heading + " to " + ops2);

		}
		if (option_third == 3)
		{
		    base = option_base3;
		    heading = (heading + " to " + ops3);

		}
		if (option_third == 4)
		{

		    base = option_base4;
		    heading = (heading + " to " + ops4);

		}
		if (option_third == 5)
		{


		    heading = (heading + " any other base");

		}
		if (option_third == 6)
		{


		    heading = (heading + " to all");

		}
		if (option_third == 7)
		{
		    option_main = 9;
		    heading = "Main Menu";
		    option_second = 3;
		    option_third = 7;
		    Display = 1;
		    numberI = 1;
		}
	    }
	    //main menu options
	    if (option_main < 10)
	    {
		if (option_main == 1)
		{
		    heading = op1;
		}
		if (option_main == 2)
		{
		    heading = op2;
		}
		if (option_main == 3)
		{
		    heading = op3;
		}
		if (option_main == 4)
		{
		    heading = op4;
		}
		if (option_main == 5)
		{
		    heading = op5;
		}
		if (option_main == 6)
		{
		    heading = op6;
		}
		if (option_main == 7)
		{
		    heading = op7;
		}
		if (option_main == 8)
		{
		    heading = "Developed by:   Wesley Oliver Grd 10E1";
		    c.print ("\n\n\n\n\n\n\n\n\n\n");
		}
		if (option_main == 9)
		{
		    heading = "Main Menu";
		}
	    }
	    
	    
	} //programe loop and heading loop 'End'




    } // main method
} // Project2 class


