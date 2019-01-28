// The "Combinations_Arrays" class.
public class Combinations_Arrays
{
    public static void main (String[] args)
    {
	int count = 0;
	int numbers[] = {0, 1, 2, 3};
	int bsize = 4;
	int arrange[] = {0, 1, 2, 3};
	for (int i = 0 ; i < bsize ; i++)
	{
	    arrange [0] = i;
	    for (int i2 = 0 ; i2 < bsize ; i2++)
	    {
		if (i2 == i)
		{
		    i2++;
		}
		arrange [1] = i2;
		//System.out.println (arrange [0] + "" + arrange [1] + "" + arrange [2] + "" + arrange [3] + "\n---");

		for (int i3 = 0 ; i3 < bsize ; i3++)
		{
		    if (i3 == i)
		    {
			i3++;

		    }
		    if (i3 == i2)
		    {
			i3++;

		    }

		    arrange [2] = i3;
		    //System.out.println (arrange [0] + "" + arrange [1] + "" + arrange [2] + "" + arrange [3] + "\n---");

		    for (int i4 = 0 ; i4 < bsize ; i4++)
		    {
			if (i4 == i)
			{
			    i4++;

			}
			if (i4 == i2)
			{
			    i4++;

			}
			if (i4 == i3)
			{
			    i4++;

			}
			arrange [3] = i4;
			if (arrange [3] < 4)
			{
			    
			    System.out.println (count + ": " + arrange [0] + "" + arrange [1] + "" + arrange [2] + "" + arrange [3] + "\n---");
			    count++;
			}

		    }

		}
	    }


	}

	// Place your code here
    } // main method


    void arrange (int pos, int current)
    {

    }
} // Combinations_Arrays class
