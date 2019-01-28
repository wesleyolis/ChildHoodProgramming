#include <stdio.h>
#include <stdlib.h>
#include "token.h"
#include "boolean.h"
#include "scanner.h"

struct Token_v			/* token type and its value */
{
	TokenType_t type;	/* SM_PROGRAM, SM_COMMA, etc */
	int disp;		    /* numbers are ints */
	char *ident;        /* identifiers are strings */
	struct Token_v *next;
	
};

extern void Free_Structs();

struct Token_v *Lower [26], *Upper[26];	/*Declare pointers to Lower, and Upper case first letters*/

extern void initST( void )
{
	int count = 0;
	
	atexit(Free_Structs);	/*Register on an Exit of program that it runs the method, Realease all
							memory reseverd by it, as perants dont know that they need to free it.*/
		
		/*initialize all arrays pointers to have null value for hash table*/
		
		while(count < 26)
		{
			Lower[count] = NULL;
			count++;
		}
		count = 0;
		while(count < 26)
		{
			Upper[count] = NULL;
			count++;
		}
}

extern boolean insert( TokenType_t token, char *name )
{
	struct Token_v* *start;
	struct Token_v *curr;
	if((name [0] > 96) && (name [0] < 123))		/*test for safty sake, in the case of it not being test before*/
		start = &Lower[name[0] - 97];
	else if((name [0] > 64) && (name [0] < 91))
			start = &Upper[name[0] - 65];
		else
		{
			printf("Error Invalied input, first charater must be a Letter\n");
			exit(EXIT_FAILURE);
		}
		
	if((*start) == NULL)		/*does an entry exist in this section list*/
	{
		
		(*start) = (struct Token_v*) malloc(sizeof(struct Token_v));	
		if((*start) != NULL)
		{
			(*start)->ident = (char*) malloc((MAX_STR_LEN) * sizeof(char));
			if((*start)->ident != NULL)
			{
				(*start)->type = token;
				(*start)->disp = 0;
				strncpy((*start)->ident, &name[1], MAX_STR_LEN);		/*Assign String less first charater to ident*/
				(*start)->next = NULL;
				
				return TRUE;
			}
			else
			{
				printf("Compiler Error Not Enough memory Avalible\n");
 	 			return EXIT_FAILURE;
			}
		}
		else
		{
			printf("Compiler Error Not Enough memory Avalible\n");
 	 		return EXIT_FAILURE;
		}
	}
	else
	{
		/*must find next insertion point in list*/
		
		curr = (*start);
		while(curr->next != NULL)
		{
			if(strcmp(curr->ident,&name[1]) == 0)
			{
				return FALSE;
			}
			curr = curr->next;
		}
		if(strcmp(curr->ident,&name[1]) == 0)
		{
			return FALSE;
		}
			
		
		curr->next = (struct Token_v*) malloc(sizeof(struct Token_v));	
		if(curr->next != NULL)
		{
			curr = curr->next;
			curr->ident = (char*) malloc((MAX_STR_LEN) * sizeof(char));
			if(curr->ident != NULL)
			{
				curr->type = token;
				(*start)->disp = 0;
				strcpy(curr->ident, &name[1]);		/*Assign String less first charater to ident*/
				curr->next = NULL;
				
				return TRUE;
			}
			else
			{
				printf("Compiler Error Not Enough memory Avalible\n");
 	 			return EXIT_FAILURE;
			}
		}
		else
		{
			printf("Compiler Error Not Enough memory Avalible\n");
 	 		return EXIT_FAILURE;
		}
	}
}

extern boolean find( char *name, TokenType_t *token, int *disp) 
{
	struct Token_v *curr;
	
	if((name [0] > 96) && (name [0] < 123))
		curr = Lower[name[0] - 97];
	else if((name [0] > 64) && (name [0] < 91))
		curr = Upper[name[0] - 65];
		else
		{
			printf("Error Invalied input, first charater must be a Letter\n");
			exit(EXIT_FAILURE);
		}
		
	while(curr != NULL)
	{
		if(strcmp(curr->ident,&name[1]) == 0)
		{
			(*token) = curr->type;
			(*disp) = curr->disp;
			return TRUE;
		}
		curr = curr->next;
	}
	return FALSE;
}

void Free_Tree(struct Token_v *curr)
{
		char ok = 1;
		struct Token_v *prev;
		while(curr != NULL)
		{
			free(curr->ident);
			prev = curr;
			curr = curr->next;	
			free(prev);
		}
}

extern void Free_Structs()
{
	int count = 0;
		while(count < 26)
		{
			Free_Tree(Lower[count]);
			Lower[count] = NULL;
			count++;
		}
		count = 0;
		while(count < 26)
		{
			Free_Tree(Upper[count]);
			Upper[count] = NULL;
			count++;
		}
		
	printf("Struct Mermory Freed\n");
}
