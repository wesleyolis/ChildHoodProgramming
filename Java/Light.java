public class Light
{
    public static void main (String [] args)
    {
	float sum = 0, user = (1000000000 / 1024) * 16;
	for (int i = 1 ; i < 17 ; i++)
	{
	    sum = sum + (float) (Math.pow (255, i));
	}
	System.out.println (String.valueOf (((((((((sum / 2)) / 8) / 1024) / 1024) / 1024) / 1024) / 1024) / 1024) + "PB");
	System.out.println (user + "PB");
    }
}
