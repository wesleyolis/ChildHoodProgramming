// The "Wes" class.
import java.awt.*;
import hsa.Console;

public class Codeincripter
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	String num1 = "";
       c.println("Please enter a word!");
       String word = c.readString();
       for(int loop = word.length();loop > 0 ;loop --)
       {
       char char1 = word.charAt(loop - 1);
       int num = ((int) char1) - 64;
       num1 = num + num1;
       
       }
       c.println(num1);
    } // main method
} // Wes class
