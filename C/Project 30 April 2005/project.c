#include<stdio.h>
#include<math.h>
#define States 3
char Color1 = '*',Color2 = '.'; 

void initarray(int *arr,int s)		/*Initailize the hori,verti arrays to 0*/
{
int i;
	for(i=0;i<s;i++)
	{
	arr[i]=0;
	}
}

void initDFA(int *DFA,int DFA_Size)		/*initailize the DFA States all to -1*/
{
	int c,r;
	for(r = 0;r < DFA_Size;r++)
	{
		for(c = 0;c < 4;c++)
		{
			DFA[(r * 4) + c] = -1;
		}
	}
}

void add(int *data,int pos)		/*Recusion method for adding 1 in binary to verti or hori array*/
{

	if(data[pos] == 0)
	{
		data[pos] = 1;
								/*this code would have been use when method change to norecusion and highest verti implemented*/
								/*As record how many quad componets the computer needs to add explained in Read Me*/
		/*if(verti == data)			
		{
			if(high_verti > pos)
			{
				high_verti = pos;
			}
		}*/
	}
	else
	{
		data[pos] = 0;
		add(data,pos-1);
	}

}



int cord(int *h, int *v)		/*Adds the quad verti, and hori cords to geather*/
{
	if(*v==0)
	{
		if(*h==0)
		{	
			return 0;

		}
		else
		{
			return 1;
		}
	}
	else
	{
		if(*h==0)
		{
			return 2;
		}
		else
		{
			return 3;
		}
	}
}

int ispixel(int *P_DFA, int *hori, int *verti, int *high_verti)		/*Cheack if pixel Exists in DFA*/
{
	int st_count = 0;		/*Runs Throught the Quad Cordinates*/
	int DFA_Row = 0;
	long int position;
	
	while(st_count < States)
	{
		position = (DFA_Row * 4) + cord(&hori[st_count],&verti[st_count]);
		if(P_DFA[position] == -1)
		{
			return -1;					/*If no state teminate becuase can't be a pixel*/
		}
		DFA_Row = P_DFA[position];
		st_count++;
	}
	return 1;							/*Has right number of States in DFA so it must be a pixel*/
}

void Display_Image(int *P_DFA,int Size)		/*Display the image from DFA by Calclulating all the quad components,*/
{											/*Then Submits them to ispixel() which returns weather that pixel exists*/
	int hori[States];
	int verti[States];
	
	int coloms = 0,rows = 0,high_verti = 0;
	initarray(hori,States);
	initarray(verti,States);
		
	printf("\nDisplay Image\n");
	
	printf("\n-------------------------------------\n");
	printf("|      Display Image from DFA       |\n");
	printf("-------------------------------------\n\n");
	
	for(rows = 0;rows < Size;rows++)
	{
		for(coloms = 0; coloms < Size-1; coloms++)
		{
			if(ispixel(P_DFA,hori,verti,&high_verti) == 1)
			{
				printf("%c",Color1);
			}
			else
			{
				printf("%c",Color2);
			}
			add(hori,States-1);
		}
		
		if(ispixel(P_DFA,hori,verti,&high_verti) == 1)
		{
			printf("%c",Color1);
		}
		else
		{
			printf("%c",Color2);
		}

		printf("\n");
		if(rows < Size-1)
		{
		initarray(hori,States);
		add(verti,States-1);
		}
	}	
}

void DFA_Cascade(int *DFA,int Row,int statecount,int dis)		/*Recusion method for cascade display*/
{

	int cord = 0, s, count = 3, disnxt = dis;
	statecount++;
	
	for(s=0;s<statecount;s++)
	{
		printf("|  ");
	}
	printf("\n");
	
	for(;cord < 4;cord++)
	{
		if(DFA[(Row * 4) + cord] != -1)
		{
		
			for(s=0;s<statecount-(dis+1);s++)
			{
				printf("|  ");
			}
			for(;s < statecount - 1;s++)
			{
				printf("   ");
			}
			
			printf("|--%d  State:%d\n",cord,DFA[(Row * 4) + cord]);
				
			if(statecount != States)
			{
				DFA_Cascade(DFA,DFA[(Row * 4) + cord],statecount,disnxt);
			}
		}
	}
	for(s=0;s<statecount-1-dis;s++)
	{
		printf("|  ");
	}
printf("\n");
}

void Display_DFA_Cascade(int *DFA)						/*Starts The recusion mehthod to display Cascade DFA*/
{
	printf("\n-------------------------------------\n");
	printf("|       Display Cascade DFA         |\n");
	printf("-------------------------------------\n\n");
	printf("Start\n");
	DFA_Cascade(DFA,0,0,0);
}

void Display_DFA(int *DFA, int Highest_DFA_Row)		/*Runs thourght rows printing DFA, replace -1 with .,and uses formating*/
{
	int r,c,s;
	printf("\n-------------------------------------\n");
	printf("|            Display DFA            |\n");
	printf("-------------------------------------\n\n");
	printf("\n---------------------------------------\n");
	printf("quad     0       1       2       3\n");
	printf("---------------------------------------\n");
	
	for(r = 0;r <= Highest_DFA_Row ;r++)
	{
		printf("%-8d ",r);
		
		for(c = 0;c < 4;c++)
		{
			s = DFA[(r * 4) + c];
			if(s !=-1)
			{
				printf("%-7d ",s);			
			}
			else
			{
			printf(".       ");
			}
		}
		printf("\n");
	}
}

void add_DFA(int *P_DFA, int *P_Highest_DFA_Row, int *P_DFA_State, int *hori,int *verti,int high_verti)
{
	int DFA_Row = 0,DFA_c;		/* Start Row at 0 allways, DFA_c is quad cord to be read*/
	long int position;			/*Tempareally holds Position*/
	
	for(DFA_c = 0;DFA_c < States;DFA_c++)
	{
		position = (DFA_Row * 4) + cord(&hori[DFA_c],&verti[DFA_c]);	/*Use method cord to return quad from is components*/
		
		if(P_DFA[position] == -1)				/*If no State in DFA Make it one, Assing P_DFA_State then move to that row*/
		{										/*Increase the P_DFA_State by 1*/
			P_DFA [position] = *P_DFA_State;
			DFA_Row = *P_DFA_State;
			*P_DFA_State = (*P_DFA_State + 1);
		}
		else
		{
			DFA_Row = P_DFA[position];			/*in DFA State Exsits so move to that state*/
		}
	}
	if(*P_Highest_DFA_Row < DFA_Row )		/*Records the highest acessed row in DFA*/
	{
		*P_Highest_DFA_Row = DFA_Row;
	}
}

								/*Reads in the image calulates dfa and does error cheacking*/

void ReadImage_CreateDFA(int *P_DFA,int *P_Highest_DFA_Row, int N,int DFA_Size,int *DFA_Constructed)
{
	FILE *infile;				/*File varible*/
	char F_Name [51];			/*File Name*/
 	char car;					/*temp storage of the readin charater from array*/
 	
 	int Row = 0, Colom = 0,high_verti = 0, DFA_State = 1;		/*Row, Colom for traking image size,DFA_State used to hold States*/
 																/*High_verti is for an optermization method, not implemented yet*/
 	int hori[States];			/*initialize two arrays to hold the Quadrant components*/
	int verti[States];
	
	*P_Highest_DFA_Row = 1;		/*Assing default value for comparison*/
	
	initDFA(P_DFA,DFA_Size);	/*Initailize all values in DFA to -1, and hori and verti array to 0*/
	initarray(hori,States);
	initarray(verti,States);

	
	printf("\n-------------------------------------\n");
	printf("|      Read Image & Create DFA      |\n");
	printf("-------------------------------------\n\n");
	
	printf("Please Enter the name of the Image File (Max 50 char) <Enter>\ninclude extension i.e .'txt'\nNo Filename a default 'image.txt' will be used\n>>");
	if(getStr(F_Name,50) == 0)
	{
 		sprintf(F_Name,"image.txt");		/*No File name use default name*/
 		printf("\nUsing image.txt\n");
	}
	printf ("Opening %s File..\n",F_Name);
	if (((infile = fopen (F_Name,"r")) == NULL))		/*Try open file*/
    {
		printf ("\nError Opening File..\n");
	}
	else
	{
		printf ("\nReading Image..\n");
		*DFA_Constructed = 1;						/*Say DFA is constructed, but change it on error*/
		
 		while (((feof(infile))== 0) && Row < N)
	    {  
	    	fscanf (infile,"%c", &car);
			if((car == Color1) || (car == Color2))
			{
				if((car == Color1))			/*Is a DFA Color then Add it to DFA using add_DFA*/
				{					
					add_DFA(P_DFA, P_Highest_DFA_Row, &DFA_State, hori, verti, high_verti);
				}
				Colom++;
			
				if(Colom >= N)		/*if Colom execed image width rest it to 0 and increment Row*/
				{
					Colom = 0;
					Row++;
					initarray(hori,States);		/*Rest the hori Quad componet*/
					add(verti,States-1);		/*Incease verti Quad*/
				}
				else
				{	
					add(hori,States-1);			/*Incease hori Quad*/
				}

			}
			else
			{
				if(!isspace(car))				/*Test if ignore charater else Terminate, Display Error*/
				{
					printf("Error invailed charaters, '%c'\n",car);
					*DFA_Constructed = -1;
				}
			}
	    }
	
	    if(*DFA_Constructed  != -1)		/*If no Errors yet Contine error Cheacking, Error Teminating*/
	    {
		    if((Row < 8) && (Colom < 8))			/*Test to see if there leas than the required pixels*/
		    {
		    	printf("Error too little pixles\n");
		   	 *DFA_Constructed = -1;
		    }
		    else
		    {
		    	/*Cheack rest of the files contents*/
		    	
		    	fscanf (infile,"%c", &car); 		/*if using file with an end caharater i.e windows*/
		    	
		    	while (((feof(infile))== 0)) 		/*Cheack the remain craters if there any invalid charaters or more pixels*/
		   		{ 
		   		fscanf (infile,"%c", &car);
		   		 	if(!isspace(car))
					{
						printf("Error too many pixels or invailed charaters, '%c'\n", car);
						*DFA_Constructed = -1;
						break;
					}
		   	 	
			   	 }
		   	 }
	    }
	    
	    if(*DFA_Constructed == 1)		/*DFA was constructed Sucessfully unlock menu options*/
	    printf("DFA Constructed\n");
	    	    
	}
	
}

int getStr(char *array,int amount)		/*Reads a String with spaces to given array, adds the oppriate terminating */
{										/*string caharater slash null, for string printing*/
	int pos=0;
	while(pos < amount-1)
	{
		scanf("%c",&array[pos]);
		if(array[pos] =='\n')
		{
			array[pos] = '\0';
			return pos;
		}
		pos++;
	}
	
	if(amount !=0)//returns the origanol array
	{
		array[amount-1] = '\0';
	}
	
	return amount;
}


char getcharClear()				/*Method for reading in a first chrater and dumping rest of jarble input*/
{
	char c;
	scanf("%c",&c);
	char after = -1;
	while(after != '\n'){scanf("%c", &after);}
	return c;
}


void Color_Settings()			/*Change the Color Charaters method*/
{
		printf("\n-------------------------------------\n");
		printf("|          Color Settings           |\n");
		printf("-------------------------------------\n\n");
		printf("DFA Color1 '%c' and Color2 '%c'",Color1,Color2);
		printf("\nPlease Enter to Diffrent Charaters for\nDFA, Color1 >>");
		Color1 = getcharClear();
		do
		{
			printf("Color2 >>");
			Color2 = getcharClear();
		}
		while(Color1 == Color2);
}

int getDFA_Size()				/*Calculates number DFA Rows needed*/
{
	int i,size = 0;
	for(i = 0;i<=States;i++)
	{
	size = size +  pow(4,i);	
	}
	return size;
}

void menu()
{
	int N = ((int)pow(2,States));		 /*Calculate the size of the image*/
	int DFA_Size = getDFA_Size(); 		/*Calculate number of rows to have in DFA*/
	
										/*These varibles are acesses thought pointers in all methods*/
	int DFA [DFA_Size][4];				/*initialize the DFA Array*/
	int Highest_DFA_Row = 1;			/*This Hold the highest Row in DFA that has been acessed*/
	int DFA_Constructed = -1;			/*weather or not the DFA was constructed or is*/
	
	char option = -1;					/*Holds Menu option*/
	while(option !='0')
	{
		option = -1;
		printf("\n-------------------------------------\n");
		printf("|            Main Menu              |\n");
		printf("-------------------------------------\n\nPlease Enter an option\n\n");
		printf("1.  Read Image & Create DFA\n");
		
		if(DFA_Constructed == 1)			/*only displays when DFA is constructed*/
		{
			printf("2.  Display DFA Matrix\n");
			printf("3.  Display DFA Cascade (Tree like)\n");
			printf("4.  Display Image, using DFA\n");
	
		}
		printf("\n    Settings\n\n");
		printf("5.  Change Image Colors\n");
		printf("0.  Exit\n\n>>");
		
		option = getcharClear();			/*Read in menu Option*/
		
		switch(option)						/*Call the appropriate method, for selected option*/
		{
			case '1':
				ReadImage_CreateDFA(&DFA[0][0],&Highest_DFA_Row,N,DFA_Size,&DFA_Constructed);
			break;

			case '2':
				if(DFA_Constructed == 1)
				{
				Display_DFA(&DFA[0][0],Highest_DFA_Row);
				}
			break;

			case '3':
				if(DFA_Constructed == 1)
				{
				Display_DFA_Cascade(&DFA[0][0]);
				}
			break;

			case '4':
				if(DFA_Constructed == 1)
				{
				Display_Image(&DFA[0][0], N);
				}
			break;

			case '5':
				Color_Settings();
			break;
		}
	}	
}


int main()
{
	menu();		 /*Call the Menu*/
	return 0; 	/*Terminate the program*/	
}
