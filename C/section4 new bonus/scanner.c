/*******************************************************************************
* FILE NAME: scanner.c                                                         *
* 														                       *
* PURPOSE:	Lexical Scanner for ToyJ Compiler.		                           *
* 							                                                   *
* NOTES:																	   *
* Before using the scanner, the function initScanner must be called.		   *
*                                                                              *
* DEVELOPMENT HISTORY														   *
* Date				Author				Description							   *
* 04/08/2005			W.W.A Oliver			NoN							   *
*                                                                              *
*********************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <limits.h>

#include "token.h"      /* type definitions */
#include "scanner.h"	/* exported functions and variables */

/* globals */
static char ch;      /* Current character being scanned */
static FILE * ofPtr; /* Pointer to the file to be scanned */
int lineNumber;      /* Current position in file while we are scanning */

/* function prototypes */
void initScanner( char * );
void getSym ( Token_t * );
static void getChar( void );
static void get_number( Token_t * sym );
static void get_alpha_numeric( Token_t * sym );


static void line_comments()
{
	getChar();
	while((!feof(ofPtr)))
	{
		getChar();
		if(ch =='\n')
		{
			break;
		}
	}
}


static void multi_comments() /*Destroy multi line comments*/
{
	while(!feof(ofPtr))
	{
		getChar();
		if(ch =='*')
		{
			getChar();
			if(ch == '/') /*Else it is the end of the comments*/
			{	
				getChar();
				break;
			}
		}
		else
		if(ch == '/')
		{
			getChar();
			if(ch == '*') /*If there are comments in sdie comments, hacery*/
			{
				multi_comments(); /*Call the method to recusivley to eliminate comments*/
			}
		}
	}
	if(feof(ofPtr))
	{
		printf("Error multi line comments not closed\n");
		exit(EXIT_FAILURE);
	}
}


/*
 * Initialize globals in scanner.
 * Must be called before start using the scanner.
 * Parameter:
 * 		char *file: file that scanner will scan
 */
void initScanner( char *file )
{
	if((ofPtr = fopen(file,"r")) == NULL)
	{
		printf("Error File '%s' couldn't be opened!\n",file);
		exit(EXIT_FAILURE);
	}
	else
	{
		lineNumber = 1;
		getChar();
	}
    /* Initialise globals */
    /* Open the file to be scanned */
}


/*
 * Get the next token in source file
 * Parameter:
 * 		Token_t *sym - The next token (call by reference)
 */
void getSym( Token_t * sym )
{
	if(!feof( ofPtr ))		/* test for EOF */
	{	
		while((isspace(ch)) && (!feof(ofPtr)))
		{
			getChar();		/* First remove all the whitespace. Take special care of the \n 
			character */
		}
			
			if(isalpha(ch)) 	/* get the keyword or variable name */
        		{
        			get_alpha_numeric(sym);
        						
			}
        		else if(isdigit(ch))	/* process the number */
        		{
				get_number(sym);
											/*todo: process the number*/
        		}
        		else	/* handle special characters */
        		{
			switch(ch)	/*cheack all single charaters*/
				{
				case '\n':
                    		sym->type = SM_NEWLINE;
				getChar();
                    		break;
				
				case '{':
                    		sym->type = SM_LT_C_BRK;
				getChar();
                    		break;
				
				case '}':
                    		sym->type = SM_RT_C_BRK;
				getChar();
                    		break;
				
				case '[':
                    		sym->type = SM_LT_S_BRK;
				getChar();
                    		break;
				
				case ']':
                    		sym->type = SM_RT_S_BRK;
				getChar();
                    		break;
				
				case '(':
                    		sym->type = SM_LT_R_BRK;
				getChar();
                    		break;
				
				case ')':
                    		sym->type = SM_RT_R_BRK;
				getChar();
                    		break;
				
				case ',':
                    		sym->type = SM_COMMA;
				getChar();
                    		break;
				
				case ';':
                    		sym->type = SM_SEMI_COLON;
				getChar();
                    		break;
				
				case '+':
                    		sym->type = SM_PLUS;
				getChar();
                    		break;
				
				case '-':
                    		sym->type = SM_SUB;
				getChar();
                    		break;
				
				case '*':
                    		sym->type = SM_MULTI;
				getChar();
                    		break;
				
				case '/':	/*Cheack to see if it is comments*/
				getChar();
				if(ch == '/')
				{
						/*dump contents till end of line*/
						line_comments();
						getSym(sym);		/*Recall the method because it must return a new token*/
				}
				else if(ch =='*')
				{		/*dump contents of multi line comments*/
						multi_comments();
						getSym(sym);		/*Recall the method because it must return a new token*/
				}
				else
				{	/*it was not  comments is a Division operator*/
						sym->type = SM_DIVIDE;
				}
				break;
				
				case '!':
				/*Cheack, it because it mabey a nother symbol*/
				getChar();
				if(ch == '=')
				{
					sym->type = SM_NOT_EQUAL;
					getChar();
				}
				else
				{
					sym->type = SM_NOT;
				}
				break;
					
				case '<':
				/*Cheack, it because it mabey a nother symbol*/
				getChar();
				if(ch == '=')
				{
					sym->type = SM_E_SMALLER;
					getChar();
				}
				else
				{
					sym->type = SM_SMALLER;
				}
				break;
					
				case '>':
				/*Cheack, it because it mabey a nother symbol*/
				getChar();
				if(ch == '=')
				{
					sym->type = SM_E_LARGER;
					getChar();
				}
				else
				{
					sym->type = SM_LARGER;
				}
				break;
                    		
				case '=':
				/*Cheack, it because it mabey a nother symbol*/
				getChar();
				if(ch == '=')
				{
					sym->type = SM_EQUALS;
					getChar();
				}
				else
				{
					sym->type = SM_EQUAL;
				}
				break;
					
				case '&':
				/*Cheack, it because it mabey a nother symbol*/
				getChar();
				if(ch == '&')
				{
					sym->type = SM_AND;
					getChar();
				}
				else
				{
					printf("Error Invailed Charater '&' LN:%d\n",lineNumber);
					getSym(sym);
				}
				break;
					
				case '|':
				/*Cheack, it because it mabey a nother symbol*/
				getChar();
				if(ch == '|')
				{
					sym->type = SM_OR;
					getChar();
				}
				else
				{
					printf("Error Invailed Charater '%c' LN:%d\n",ch,lineNumber);
					getChar();
					getSym(sym);
				}
				break;
				
				default:
				printf("Error Invailed Charater '%c' LN:%d\n",ch,lineNumber);
				getChar();
				getSym(sym);
				break;
				
					/* Remaining symbols */		
				} /* END CASE: special characters */   
         			   
        		} /* END ELSE: special characters */
	}
	else
	{
		sym->type = SM_EOF;
		/*free( filename );*/
		fclose( ofPtr );
	}
}


/*
 * Get the next character from the source file.
 * Update the line numbers
 */
 
static void getChar()
{
    if(!feof(ofPtr))
	ch = fgetc(ofPtr);
	
    if(ch == '\n')
      lineNumber++;
}

static void get_number( Token_t * sym ) 	/* read and constructs the number value*/
{
	char digit,negative;
	if(sym->type == SM_SUB)		/*this cheack to see if the previous token is a minus before the number*/	
	{negative = 1;}				/*If it is a minus then we change the parameters*/
	else
	{negative = 0;} /*positive*/
	
	sym->type = SM_NUMBER;			/*Set the token identifier*/
	sym->value = (ch - '0');		/*get the number value of the digit*/
	getChar();
	while((!feof(ofPtr)) && (isdigit(ch)))
	{
		digit = (ch - '0');
		if(((SHRT_MAX + negative - digit)/10)>= sym->value)	/*cheack to see the if number is too large*/
		{
			sym->value = (sym->value * 10) + digit; /*value is 10times bigger, so encrease it and number value*/
			getChar();
		}
		else
		{
			while((!feof(ofPtr)) && (isdigit(ch)))
			{
				getChar();
			}
			printf("Error number out of bounds LN:%d\n",lineNumber);
			getSym(sym);
			break;
		}
	}
}


static void get_alpha_numeric( Token_t * sym )	/*Read the alpha charaters and digits*/
{
	int num_chars = 1;
	sym->ident[0] = ch;
	getChar();
	
	while((!feof(ofPtr)) && (num_chars < MAX_STR_LEN)) /* Pre construct the string of charaters*/
	{ 
		if( (isalpha(ch)) || (isdigit(ch)) )
		{
			sym->ident[num_chars] = ch;
			num_chars++;
			getChar();	
		}
		else
		{
			break;
		}
			
	}
	sym->ident[num_chars] = '\0';	/*assign the end of line charater*/
	
	if( (isalpha(ch)) || (isdigit(ch)) )	/*cheack are there more charaters, if so too long*/
	{
		if(num_chars >= MAX_STR_LEN)
		{
			printf("Error identifier '%s",sym->ident);
			while(!feof(ofPtr))
			{
				if((isalpha(ch)) || (isdigit(ch)) )
				{
					printf("%c",ch);
					getChar();
				}
				else
				break;
			}
			printf("' Name too long LN:%d\n",lineNumber);
			getSym(sym);
		}
	}
	else if(num_chars <= 7)	/*skip reserved names, because it's too long for any of them*/
	{
		if(strcmp(sym->ident,"if") == 0)
		{
		sym->type = SM_IF;
		}else if(strcmp(sym->ident,"else") == 0)
		{
		sym->type = SM_ELSE;
		}else if(strcmp(sym->ident,"for") == 0)
		{
		sym->type = SM_FOR;
		}else if(strcmp(sym->ident,"while") == 0)
		{
		sym->type = SM_WHILE;
		}else if(strcmp(sym->ident,"true") == 0)
		{
		sym->type = SM_TRUE;
		}else if(strcmp(sym->ident,"false") == 0)
		{
		sym->type = SM_FALSE;
		}else if(strcmp(sym->ident,"int") == 0)
		{
		sym->type = SM_INT;
		}else if(strcmp(sym->ident,"boolean") == 0)
		{
		sym->type = SM_BOOLEAN;
		}else if(strcmp(sym->ident,"print") == 0)
		{
		sym->type = SM_PRINT;
		}else if(strcmp(sym->ident,"class") == 0)
		{
		sym->type = SM_CLASS;
		}else
		{
		sym->type = SM_ID;
		}
	}
	else
	{
		sym->type = SM_ID;
	}
}


