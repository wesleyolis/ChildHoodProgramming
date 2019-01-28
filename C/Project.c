#include <stdio.h>
#define Color1 '*'
#define Color2 '.'
#define Size 4

FILE *infile;

int DFA [21][4];

int event = 0;
int colom = 0,row = 0;
char col;
void add_DFA()
{
	printf("DFA,%d,%d\n",colom,row);
	row = row + 1;
	if(row > (Size-1))
	{
		colom = colom + 1;
		row = 0;
	}
	



}


void readFile ()
{
    

    if (((infile = fopen ("image.txt","r")) == NULL))
    {
		printf ("Error Opening File..");
	}
	else
	{
	    //read data
		
	    while ((!feof(infile)))
	    {
		
		
	    printf("\n%d,",feof(infile));	
		fscanf (infile,"%c", &col);
	printf("%c\n",col);
			switch (col)
			{
				case Color1:
					add_DFA();
					break;
				
				case Color2:
					add_DFA();
					break;
				
				default:
				
					break;	
			}
	    }

		//check number of pixels read more or less
		printf("\nData%d,%d\n",colom,row);
		
		printf("\n%d\n",feof(infile));

		if(((colom == Size) && (row == 0)))
		{
				//contine and display DFA and reconstruct image
		printf("Image Data Consistant\nDisplaying DFA\n");
		}
		else
		{
			printf("Error Inconsistant Image Data");
	
		}

	}
}




    int main ()
    {

	readFile ();

	return 0;
    }


