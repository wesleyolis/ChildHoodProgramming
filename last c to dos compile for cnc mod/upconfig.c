#include <stdio.h>

int main (int argc, char * argv[] )
{
FILE  *fPtr;
if(argc ==5){
if((fPtr = fopen("../CAD.cfg","w")) ==NULL)
{
printf("Error Writing config file\n");
}
else
{

fprintf(fPtr,"D %s\\\n",argv[2]);
fprintf(fPtr,"P %s\\\n",argv[1] );
fprintf(fPtr,"M C:\\CNC\\\n");
fprintf(fPtr,"E C:\\CNC\\PRG\\\n");
fprintf(fPtr,"A C:\\CNC\\GRF\\\n");
fprintf(fPtr,"G 1\n");
fprintf(fPtr,"S 5 38 38 500\n");
fprintf(fPtr,"F 640 320 0 16\n");
fprintf(fPtr,"C SYSTEM16.fnt\n");
fprintf(fPtr,"X 50\n");
fprintf(fPtr,"B 5 4\n");
fprintf(fPtr,"T A\n");
fprintf(fPtr,"O 1\n");
fprintf(fPtr,"H 3\n");
fprintf(fPtr,"N \"%s   %s\"\n",argv[3] ,argv[4] );
fprintf(fPtr,"K 1\n");
fclose(fPtr);

printf("Config writting sucessfully");

}
}
else
{
printf("Error incorrect parameters");
}
return 0;
}

