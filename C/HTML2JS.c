#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{

char t;

FILE *inFile, *outFile;

		


if((inFile = fopen(argv[1][1], "r")) == NULL)
				printf("\nCould not open the html file\n");
		else
		{
if((outFile = fopen(argv[1][2], "w")) == NULL)
				printf("\nCould not creat js file\n");
		else
		{
	if(!feof(inFile))
	{
	t = fgetc(inFile);
	while(!feof(inFile))
	{
		if(t == "\"")
		{
			fwrite("'",sizeof(char) , 2, outFile);
		}
		else
		{
			fwrite(&t, sizeof(char), 1, outFile);
		}
		t = fgetc(inFile);
	}
	fclose(inFile );
	fclose(outFile );


	}

}


}


}
