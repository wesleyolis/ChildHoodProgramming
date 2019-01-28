// The "ToolsTest" class.
import java.io.*;
public class ToolsTest
{
    public static void main (String [] args) throws IOException
    {
	BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
	System.out.print("Enter a word\n>>");
	String str = in.readLine ();
	System.out.println ("The word backwards is :\n" + StringTools.backwards (str));
	System.out.println ("The word down is :\n" + StringTools.down (str));
	System.out.println ("The word diagonal is :\n" + StringTools.diagonal (str));
	System.out.println ("The word middles is : " + StringTools.middles (str));
	 System.out.println ("The word vowels is : " + StringTools.vowels (str));
	 System.out.println ("The number of spaces is : " + StringTools.spaces(str));
	 System.out.println ("The words nextchars : " + StringTools.nextchars(str));
	 System.out.println ("The number of words : " + StringTools.words(str));
	 System.out.println ("Is there a hyphen : " + StringTools.inString(str,'-'));
	 System.out.println ("the word with vowels remove : " + StringTools.removeVowels(str));
    } // main method
} // ToolsTest class
