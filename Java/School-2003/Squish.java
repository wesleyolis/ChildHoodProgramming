// Wesley Oliver
import java.io.*;
public class Squish
{
    static String word, newword;
    static final String vowels = "aeiouAEIUO";
    public static void main (String [] args) throws IOException
    {
	BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

	System.out.println ("Enter a series of words, end with '*' followed by <ENTER>");
	System.out.print ("Type in a word then <ENTER>\n>>");
	word = in.readLine ();
	while (!(word.equals ("*")))
	{
	    System.out.println ("The word with out vowels is " + (remove (word)));
	    System.out.print ("\nType in a word ('*' to stop) and press <ENTER>\n>>");
	    word = in.readLine ();
	}
	System.out.println ("End of Session");
    }


    public static String remove (String value)
    {
	newword = "";
	for (int i = 0 ; i < word.length () ; i++)
	{

	    if (vowels.indexOf (word.charAt (i)) == -1)
	    {
		newword = newword + word.charAt (i);
	    }

	}
	value = newword;
	return value;
    }
}
