//Wesley Oliver
public class FriendArray
{
    private Friend array [] = new Friend [25];
    private int size = 0;
    public FriendArray ()
    {

    }


    public void input () throws IOException
    {
	BufferedReader inp = new BufferedReader (new InputStreamReader (System.in));
	String name, ageStr, ans;
	int age;
	System.out.println ("Enter a Friend's details [y/n]");
	ans = inp.readLine ();
	while (asn.equalsIgnoreCase ("y"))
	{
	    System.out.println ("Enter a Friends name and <Enter>");
	    name = inp.readLine ();
	    System.out.println ("Enter the frinds age and <Enter>");
	    ageStr = inp.readLine ();
	    age = Integer.parseInt (ageStr);
	    frArray [size] new Friend (name, age);
	    size++;
	    System.out.println ("Enter a nother Friend's details [y/n]");
	    ans = inp.readLine ();
	}
    }
}
