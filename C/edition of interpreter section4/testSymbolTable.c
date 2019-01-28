/******************************************************************************
 *												
 * FILE: testSymbolTable.c								
 *										
 * PURPOSE: Driver program to test symboltable			  	
 *								
 * 							
 * EXTERNAL VARIABLES:				
 * Source:				
 * Name			Type	I/O		Description	
 * ----			----	---		-----------
 * none							
 * 						
 * ABNORMAL TERMINATION, ERROR AND WARNING MESSAGES:	
 * 						
 *					
 * ASSUMPTIONS, CONSTRAINTS AND RESTRICTIONS:
 *					
 * NOTES:			
 *		 	
 * DEVELOPMENT HISTORY
 * 		
 * Date				Author				Description
 * ----				------				-----------		
 * 												
 ******************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "boolean.h"
#include "token.h"
#include "symboltable.h"

#define MAX_ID_LEN 32	/* maximum length of ID */

int main()
{
		char name[MAX_ID_LEN], *p;
		TokenType_t type;
		int disp;
		
		initST();		/* initialize symboltable */
		
		printf("\fEnter name of variable\n");
		printf("Enter EOF to end input. Usually <return><ctrl>d\n");
		printf("\? ");
		
		fgets(name, sizeof name , stdin);	/* read in name using fgets */
		if((p = strchr(name, '\n')) != NULL)	/* delete trailing '\n' */
				*p = '\0';
		
		while(!feof( stdin ))			/* put values into symboltable */
		{
				if(insert(SM_INT, name))
						printf("Value inserted\n");
				else
						printf("Variable does exist\n");
				fgets(name, sizeof name , stdin);
				if((p = strchr(name, '\n')) != NULL)
						*p = '\0';
		}
		
		printf("Enter the word to find\n");
		printf("To exit, type quit\n");
		fgets(name, sizeof name , stdin);
		if((p = strchr(name, '\n')) != NULL)
				*p = '\0';
		while( strcmp(name, "quit"))	/* find values in symboltable */
		{
				if(find(name, &type, &disp))
				{
						printf("Variable was found\t displacement: %i\n", disp);
				}
				else
				{
						printf("Variable not in symboltable\n");
				}
				fgets(name, sizeof name , stdin);
				if((p = strchr(name, '\n')) != NULL)
						*p = '\0';
		}

		return EXIT_SUCCESS;
}
						
				

		
