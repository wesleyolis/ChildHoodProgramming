//Wesley Oliver
public class Friend
{
    String name;
    int age;
    public Friend (String n, int a)
    {
	name = n;
	age = a;
    }


    public String getName ()
    {
	return name;
    }


    public int getAge ()
    {
	return age;
    }


    public void setName (String n)
    {
	name = n;
    }


    public void setAge (int a)
    {
	age = a;
    }


    public String toString ()
    {
	String temp;
	temp = name + " " + age;
	return temp;
    }
}
