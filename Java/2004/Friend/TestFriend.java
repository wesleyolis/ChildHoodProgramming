// Wesley Oliver
public class TestFriend
{
    public static void main (String [] args)
    {
	Friend myFriend = new Friend ("Wesley", 18);
	System.out.println (myFriend.toString ());
	System.out.println ("Name is " + myFriend.getName ());
	System.out.println ("His Age is " + myFriend.getAge ());
	myFriend.setName ("Ashley");
	myFriend.setAge (16);
	System.out.println (myFriend.toString ());
    }
}
