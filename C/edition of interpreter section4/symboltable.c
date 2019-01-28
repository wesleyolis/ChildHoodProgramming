#include <stdio.h>
#include <stdlib.h>
#include "token.h"
#include "boolean.h"
#include "scanner.h"
#include "string.h"
#define hashTableSize 51
struct Token_v			/* token type and its value */
{
	TokenType_t type;	/* SM_PROGRAM, SM_COMMA, etc */
	int disp;		    /* numbers are ints */
	char *ident;        /* identifiers are strings */
	struct Token_v *next;
	
};

extern void Free_Structs();

static struct Token_v *Hash [hashTableSize];	/*Declare pointers in Hash table*/
static int DISP = 0;

extern void initST( void )
{
	int count = 0;
	
	atexit(Free_Structs);	/*Register on an Exit of program that it runs the method, Realease all
							memory reseverd by it, as perants dont know that they need to free it.*/
		
		/*initialize all arrays pointers to have null value for hash table*/
		while(count < hashTableSize)
		{
			Hash[count] = NULL;
			count++;
		}
}

int value_hash(char *name)
{
	int pos = 0, hash = 0, value;
	while(name[pos] !='\0')
	{
		hash += name[pos];
		pos++;
	}
	value = (hash % pos);
	
	return value;
}


extern boolean insert( TokenType_t token, char *name )
{
	struct Token_v *curr;
	int hashValue;
	
	hashValue = value_hash(name);	
		
	if(Hash[hashValue] == NULL)		/*does an entry exist in this section list*/
	{
		
		Hash[hashValue] = (struct Token_v*) malloc(sizeof(struct Token_v));	
		if(Hash[hashValue] != NULL)
		{
			Hash[hashValue]->ident = (char*) malloc((MAX_STR_LEN + 1) * sizeof(char));
			if(Hash[hashValue]->ident != NULL)
			{
				Hash[hashValue]->type = token;
				Hash[hashValue]->disp = DISP;
				DISP++;						/*INcrese the varible displace ment*/
				strcpy(Hash[hashValue]->ident, name);		/*Assign String less first charater to ident*/
				Hash[hashValue]->next = NULL;
				
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
		
		curr = Hash[hashValue];
		while(curr->next != NULL)
		{
			if(strcmp(curr->ident,name) == 0)
			{
				return FALSE;
			}
			curr = curr->next;
		}
		if(strcmp(curr->ident,name) == 0)
		{
			return FALSE;
		}
			
		
		curr->next = (struct Token_v*) malloc(sizeof(struct Token_v));	
		if(curr->next != NULL)
		{
			curr = curr->next;
			curr->ident = (char*) malloc((MAX_STR_LEN + 1) * sizeof(char));
			if(curr->ident != NULL)
			{
				curr->type = token;
				curr->disp =  DISP;
				DISP++;					/*increse the varible displace ment*/
				strcpy(curr->ident, name);		/*Assign String less first charater to ident*/
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
	int hashValue;
	
	hashValue = value_hash(name);
	
	curr = Hash[hashValue];
	
	while(curr != NULL)
	{
		if(strcmp(curr->ident,name) == 0)
		{
			(*token) = curr->type;
			(*disp) = curr->disp;
			return TRUE;
		}
		curr = curr->next;
	}
	return FALSE; 
}

void Free_list(struct Token_v *curr)
{
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
	
		while(count < hashTableSize)
		{
			Free_list(Hash[count]);
			Hash[count] = NULL;
			count++;
		}
		
	printf("Struct Mermory Freed\n");
}
