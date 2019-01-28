import java.awt.*;
import hsa.Console;

public class Project7
{
    static Console c;           // The output console

    public static void main (String [] args)
    {

	//because of the loop for headings this have seemd to have to a pear in bit of back wards order <<
	//but achully run in the right direction if you follow the loop <<
	c = new Console ();
	String Resof = "", DisplayRes = "",ResCode = "";
	int loop2 = 0, loop_times, disamount = 0, where = 0,Rescal = 0;
	int Resaultnum = 0, Resnum = 0, Dot = 0, Start = 0, Finish = 0;
	int option_base1 = 0, option_base2 = 0, option_base3 = 0, option_base4 = 0, base = 0, baseI = 0; //declaring base and option bases
	//decalring
	int loop = 0, numberI = 1, num_char1, num_char2; //number value of char
	int Display = 1; //what area of the resaults to display
	int option_main = 9, option_second = 3, option_third = 8, option_four = 4, option_five = 3; //option variables,main menu,second menu
	String op1 = "Binary", op2 = "Octal", op3 = "Hexadecimal";                                 //}>Option menu declared
	String op4 = "Decimal", op5 = "Number", op6 = "Any other base";                 //}> so option can be
	String op7 = "Extra's , Advanced", op8 = "Exit";                                            //} > qucikly convayed
	String heading1 = " ", heading = "Number Converter"; //heading varible for menus which <<     > to menu heading and
	//are initialized with programe name <<     > accessed quickly
	//> with out repeating
	String ops1 = "", ops2 = "", ops3 = "", ops4 = ""; //op for option, s for second(second menu)
	String code = "", codeback = ""; //hold the code
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

	    if (option_third < 7)                                                                               //top in border
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

	    if ((option_main < 6 & option_main != 0 & option_third < 7 & option_second < 3 & option_five > 2) || (option_main == 6 & option_third < 7 & option_second > 2 & option_five < 3))
	    {
		c.println ("\n\n");
		if (option_third == 6)
		{
		    if (numberI < 3)
		    {
			disamount = numberI;
		    }
		    else
		    {
			disamount = 3;
		    }
		}
		else
		{
		    if (numberI < 4)
		    {
			disamount = numberI;
		    }
		    else
			disamount = 4;
		    c.println ("");
		}


		DisplayRes = "Resaults: " + Display + " - " + (Display + disamount - 1) + " of " + numberI;
		c.drawString (DisplayRes, 280, 70);
		where = Display;
		for (int dofor = 0 ; dofor < disamount ; dofor++)
		{
		    if (option_main != 5 & option_main != 8 & option_main != 7)
		    {
			loop2 = 0;
			Start = 0;
			Finish = 0;
			Dot = 0;
			Resof = "";
			Resnum = 0;
			Resaultnum = 0;

			for (loop2 = codeback.length () - 1 ; loop2 > -1 ; loop2--)
			{
			    if (codeback.charAt (loop2) == ':')
			    {
				Dot++;
			    }
			    if (codeback.charAt (loop2) == ':' & Dot == where + 1)
			    {

				Start = loop2 + 1;

			    }
			    else
			    {
				if (codeback.charAt (loop2) == ':' & Dot == where)
				{

				    Finish = loop2;

				}
			    }


			}



			loop_times = 0;
			for (loop = Start ; loop < Finish ; loop++)
			{
			    Resof = codeback.charAt (loop) + Resof;
			    num_char2 = ((int) (codeback.charAt (loop)));

			    if (num_char2 < 58)
			    {
				num_char2 = num_char2 - 48;
			    }
			    if (num_char2 < 91 & num_char2 > 64)
			    {
				num_char2 = num_char2 - 55;
			    }
			    if (num_char2 < 123 & num_char2 > 96)
			    {
				num_char2 = num_char2 - 62;
			    }

			    Resnum = (int) (num_char2 * (Math.pow (baseI, loop_times)));
			    Resaultnum = (Resaultnum + Resnum);
			    loop_times++;

			}
			c.println ("\tCode: " + Resof);
		    }
		    else
		    {

			Resaultnum = codenum;
			c.println ("\tCode: " + codenum);
		    }
		    Rescal = Resaultnum;
		    ResCode = "";
		    while (Rescal > 0)
		    {
			if (Rescal % base < 10)
			{
			    ResCode = (Rescal % base) + ResCode;
			    Rescal = Rescal / base;

			}
			if (Rescal % base > 9 & Rescal % base < 36)
			{

			    char single = (char) ((Rescal% base) - 9 + 64);
			    char more [] = {single};
			    ResCode = new String (more) + ResCode;
			    Rescal = Rescal / base;

			}

		    }
		    c.println ("\tCode Value:" + ResCode);
		    c.println ("\tNumber Value:" + Resaultnum);
		    c.println ("");













		    where++;

		}

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

		c.fillRect (595, 103 + (int) ((Display - 1) * (280 / numberI)), 12, (int) ((280 / numberI) * disamount));
		option_four = c.readInt ();

		c.clear ();


	    }



	    if ((option_main < 7 & option_main != 0 & option_third > 7 & option_second < 3 & option_five > 2) || (option_main < 7 & option_main != 0 & option_third > 7 & option_second > 2 & option_five < 3))
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
		if (option_third == 5)
		{
		    c.println ("\tPlease enter a base between 0 - 63 as a positive integer");
		    c.print ("\n\t ~> ");
		    base = c.readInt ();
		    if (base > 0 & base < 63)
		    {
			
		    }
		    else
		    {
		    option_third = 8;
		    }
		}
		c.clear ();
		Display = 1;
	    }

	    if (option_main < 6 & option_main != 0 & option_third > 7 & option_second > 2)
	    {
		if (option_main < 5)
		{
		    c.println ("\n\tEnter NOT MORE THAN '9' code's to convert, separated by\n\t':' (i.e '1001:11101:1010')");
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
			numberI = 1;
			for (loop = 0 ; loop < (code.length ()) ; loop++)
			{
			    num_char1 = (int) (code.charAt (loop));

			    if (code.charAt (loop) == ':' & loop != 0 & loop != code.length () - 1)
			    {

				numberI++;
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

			codeback = "";
			for (loop = 0 ; loop < code.length () ; loop++)
			{
			    if ((code.charAt (loop) == ':' & loop == 0) || (code.charAt (loop) == ':' & loop == code.length () - 1))
			    {
			    }
			    else
			    {
				codeback = (code.charAt (loop) + codeback);
			    }

			}
			codeback = ":" + codeback + ":";
		    }

		}
		else
		{
		    c.println ("\n\tEnter a positve numbers no bigger than 9999999\n\t to convert");
		    c.print ("\n\t ~> ");
		    codenum = c.readInt ();


		    numberI = 1;
		    option_second = 1;
		    c.clear ();

		}


	    }


	    if (option_main == 6 & option_five > 2)
	    {
		c.println ("\tPlease enter a base between 0 - 63 as a positive integer) ,then the code \n\t( NUMBERS 0 - 9 & letters A - Z & a - z");
		c.print ("\n\tBase  ~> ");
		baseI = c.readInt ();
		if (baseI < 63 & baseI != 0)
		{
		    c.print ("\n\tCode  ~> ");
		    code = c.readString ();
		    c.clear ();
		    numberI = 1;
		    option_five = 1;
		    for (loop = 0 ; loop < (code.length ()) ; loop++)
		    {
			num_char1 = (int) (code.charAt (loop));


			if (baseI < 11)
			{

			    if ((num_char1 > 47 & num_char1 < 48 + baseI))
			    {
			    }
			    else
			    {
				option_five = 3;
				break;
			    }
			}

			if (baseI < 37 & baseI > 10)
			{

			    if ((num_char1 > 47 & num_char1 < 58) || (num_char1 > 64 & num_char1 < 65 - 10 + baseI))
			    {
			    }
			    else
			    {
				option_five = 3;
				break;
			    }
			}
			if (baseI < 63 & baseI > 36)
			{

			    if ((num_char1 > 47 & num_char1 < 58) || (num_char1 > 64 & num_char1 < 91) || (num_char1 > 96 & num_char1 < 97 - 36 + baseI))
			    {
			    }
			    else
			    {
				option_five = 3;
				break;
			    }
			}
		    }

		    codeback = "";
		    for (loop = 0 ; loop < code.length () ; loop++)
		    {
			codeback = (code.charAt (loop) + codeback);
		    }

		}
		else
		{
		    option_five = 3;
		    c.clear ();
		}
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


	    //main menu options
	    if (option_main < 10)
	    {
		if (option_main == 1)
		{
		    heading = op1;
		    baseI = 2;
		}
		if (option_main == 2)
		{
		    heading = op2;
		    baseI = 8;
		}
		if (option_main == 3)
		{
		    heading = op3;
		    baseI = 16;
		}
		if (option_main == 4)
		{
		    heading = op4;
		    baseI = 10;
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


	    //thirdmenu options
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
		    option_third = 8;
		    option_five = 3;
		    Display = 1;
		    numberI = 1;
		}
	    }
	    if (option_four < 4)
	    {
		if (option_four == 1 & Display > 1)
		{
		    Display--;
		}
		if (option_four == 2 & (Display + disamount - 1) < numberI)
		{
		    Display++;
		}


		if (option_four == 3)
		{
		    option_main = 9;
		    option_second = 3;
		    option_third = 8;
		    option_four = 4;
		    option_five = 3;
		    heading = "Main Menu";
		}
	    }
	} //programe loop and heading loop 'End'




    } // main method
} // Project2 class


