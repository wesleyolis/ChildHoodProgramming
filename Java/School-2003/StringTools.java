// Wesley Oliver
public class StringTools
{
    static final String vowel = "aeiouAEIUO";
    public static String backwards (String str)
    {
	String back = "";
	for (int i = str.length () - 1 ; i > -1 ; i--)
	{
	    back = back + str.charAt (i);
	}

	return back;
    }


    public static String down (String str)
    {
	String down = "";
	for (int i = 0 ; i < str.length () ; i++)
	{
	    down = down + str.charAt (i) + "\n";
	}

	return down;
    }


    public static String diagonal (String str)
    {
	String diag = "", spaces = "";
	for (int i = 0 ; i < str.length () ; i++)
	{
	    spaces = "";
	    for (int s = 0 ; s < i ; s++)
	    {
		spaces = spaces + " ";
	    }
	    diag = diag + spaces + str.charAt (i) + "\n";
	}

	return diag;
    }


    public static String middles (String str)
    {
	int start = (str.length () / 2), end = 0;
	if ((str.length () % 2) == 0)
	{
	    end = 1;
	}
	str = str.substring (start - end, start + 1);
	return str;
    }


    public static String vowels (String value)
    {
	String newword = "";
	for (int i = 0 ; i < value.length () ; i++)
	{

	    if (vowel.indexOf (value.charAt (i)) > -1)
	    {
		newword = newword + value.charAt (i);
	    }

	}
	value = newword;
	return value;
    }


    public static int spaces (String value)
    {
	int count = 0;
	for (int i = 0 ; i < value.length () ; i++)
	{

	    if (value.charAt (i) == 32)
	    {
		count++;
	    }

	}
	return count;
    }


    public static String nextchars (String str)
    {
	String next = "";
	for (int i = 0 ; i < str.length () ; i++)
	{
	    next = next + (char) (str.charAt (i) + 1);
	}
	return next;
    }


    public static int words (String value)
    {
	int count = 0;
	value = value.trim ();
	if (value.length () > 0)
	{
	    count++;
	}
	for (int i = 0 ; i < value.length () ; i++)
	{
	    if (value.charAt (i) == 32)
	    {
		count++;
	    }
	}
	return count;
    }


    public static boolean inString (String value, char ch)
    {
	boolean in = false;
	if (value.indexOf (ch) > -1)
	{
	    in = true;
	}
	return in;
    }


    public static String removeVowels (String value)
    {
	String newword = "";
	for (int i = 0 ; i < value.length () ; i++)
	{

	    if (vowel.indexOf (value.charAt (i)) == -1)
	    {
		newword = newword + value.charAt (i);
	    }

	}
	value = newword;
	return value;
    }
}
