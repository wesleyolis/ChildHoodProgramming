#include <stdio.h>

int main (int argc, char * argv[] )
{
FILE  *fPtr;
if(argc ==3 && argv[1] != NULL){
if((fPtr = fopen((argv[1]),"w")) ==NULL)
{
printf("Error Writing folder auto launch bat file\n\n");
}
else
{

fprintf(fPtr,"config.bat %s\n",argv[2]);

fclose(fPtr);

printf("auto launch writting sucessfully\n\n");

}
}
else
{
printf("Error incorrect parameters\n\n");
}
return 0;
}

