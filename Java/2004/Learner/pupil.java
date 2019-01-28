class pupil
{
    String surname, name;
    int term [] = new int [3];
    pupil (String s, String n, int t1, int t2, int t3)
    {
	surname = s;
	name = n;
	term [0] = t1;
	term [1] = t2;
	term [2] = t3;
    }


    String getName ()
    {
	return format (name, 20);
    }


    String getSurname ()
    {
	return format (surname, 20);
    }


    int getTerm (int t)
    {
	if (t < 3)
	{
	    return term [t];
	}
	else
	{
	    return -1;
	}
    }


    void setName (String n)
    {
	name = n;
    }


    void setSurname (String s)
    {
	surname = s;
    }


    void setTerm (int t, int mark)
    {
	term [t] = mark;
    }


    String tostring ()
    {
	return format (surname, 20) + format (name, 20) + format (String.valueOf (term [0]), 5) + format (String.valueOf (term [1]), 5) + format (String.valueOf (term [2]), 5);
    }


    String format (String str, int len)
    {
	for (int i = str.length () ; i < len ; i++)
	{
	    str += " ";
	}
	return str;
    }


    int getAverage ()
    {
	return (int) Math.rint ((term [0] + term [1] + term [2]) / 3);
    }


    int getHighestMark ()
    {
	int highest = 0;
	if (term [0] > term [1])
	{
	    highest = term [0];
	}
	else
	{
	    highest = term [1];
	}
	if (term [2] > highest)
	{
	    highest = term [2];
	}
	return highest;
    }


    int getHighesterm ()
    {
	int highest = 0;
	if (term [0] > term [1])
	{
	    highest = 1;
	}
	else
	{
	    highest = 2;
	}
	if (term [2] > term [highest - 1])
	{
	    highest = 3;
	}
	return highest;
    }
}
