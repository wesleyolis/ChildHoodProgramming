#include <stdio.h>

FILE *infile;
int col = 1;
char car;
void readFile ()
{
    

    if (((infile = fopen ("image.txt","r")) == NULL))
    {
		printf ("Error Opening File..");
	}
	else
	{
	    //read data
		
	    while (!(feof(infile)))
	    {
		
		
	 
		fscanf (infile,"%c", &car);
		if((car == '.') || (car == '*'))
		{
	printf("%c,%d\n",car,col);


		col = col +1;
		}
	    }

		

	}
}




    int main ()
    {

	readFile ();

	return 0;
    }


