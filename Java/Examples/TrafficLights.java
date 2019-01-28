// Wesley Oliver
public class TrafficLights
{
    public static void main (String[] args)
    {
    
    } 
}  
class singes
{
	Color col = Color.green;

	int dir = 0, x = 20, y = 20;
	int tri [] [] = new int [2] [3];
	for (; dir < 4 ; dir++, x += 50)
	{
	    g.setColor (col);
	    switch (dir)
	    {
		case 0:
		    tri [0] [0] = x + 2;
		    tri [0] [1] = x - 8;
		    tri [0] [2] = x + 11;
		    tri [1] [0] = y - 16;
		    tri [1] [1] = y;
		    tri [1] [2] = y;
		    g.fillRect (x, y, 4, 30);
		    break;
		case 1:
		    tri [0] [0] = x + 30;
		    tri [0] [1] = x + 30;
		    tri [0] [2] = x + 46;
		    tri [1] [0] = y - 8;
		    tri [1] [1] = y + 11;
		    tri [1] [2] = y + 2;
		    g.fillRect (x, y, 30, 4);
		    break;
		case 2:
		    tri [0] [0] = x + 2;
		    tri [0] [1] = x - 8;
		    tri [0] [2] = x + 11;
		    tri [1] [0] = y + 46;
		    tri [1] [1] = y + 30;
		    tri [1] [2] = y + 30;
		    g.fillRect (x, y, 4, 30);
		    break;
		case 3:
		    tri [0] [0] = x - 16;
		    tri [0] [1] = x;
		    tri [0] [2] = x;
		    tri [1] [0] = y + 2;
		    tri [1] [1] = y - 8;
		    tri [1] [2] = y + 11;
		    g.fillRect (x, y, 30, 4);
		    break;
	    }
	    g.fillPolygon (tri [0], tri [1], 3);





}
