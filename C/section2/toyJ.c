/*******************************************************************************
*                                                                              *
* FILE: testScanner.c														   *
*                                                                              *
* PURPOSE: Driver program to test scanner.c         						   *
*                                                                              *
* EXTERNAL VARIABLES:														   *
* Source:	scanner.c ( Declared in scanner.h )								   *
* Name			Type	I/O		Description									   *
* ----			----	---		-----------									   *
* lineNumber   int		I		Current line in source file					   *
*                                                                              *
* DEVELOPMENT HISTORY														   *
* Date				Author				Description							   *
* 04/08/2005			W.W.A Oliver			NoN								   *
*                                                                              *
********************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "token.h"
#include "error.h"
#include "scanner.h"

/*define constants*/
#define true 1
#define false 0
char c;
/* function prototypes */
static void common(Token_t *sym);
static void expr(Token_t *sym);

static void cheack(Token_t *sym, TokenType_t expected)	/*method for cheacking the for the symbol*/
{
	if(sym->type != expected)
	{
		print_error(&expected,&sym->type,&lineNumber);				/*parse on whearther it is missing or expected*/
		printf("Compiling teminated\n");
		exit(EXIT_FAILURE);
	}
	else
	{
		getSym(sym);
	}
}

static char compare(Token_t *sym, TokenType_t expected)
{
	if(sym->type == expected)
	{
		getSym(sym);
		return true;
	}
	else
	return false;
}

static char relation (Token_t *sym)
{
	if(!compare(sym,SM_EQUALS))
	if(!compare(sym,SM_NOT_EQUAL))
	if(!compare(sym,SM_SMALLER))
	if(!compare(sym,SM_LARGER))
	if(!compare(sym,SM_E_SMALLER))
	if(!compare(sym,SM_E_LARGER))
	return false;
	
	return true;
}

static char mul_op (Token_t *sym)
{
	if(!compare(sym,SM_MULTI))
	if(!compare(sym,SM_DIVIDE))
	if(!compare(sym,SM_AND))
	{
		return false;
	}
	return true;
}


static char add_op (Token_t *sym)
{
	if(!compare(sym,SM_PLUS))
	if(!compare(sym,SM_SUB))
	if(!compare(sym,SM_OR))
	{
		return false;
	}
	return true;
}

static char factor (Token_t *sym)
{
	if(!compare(sym,SM_NUMBER))
	if(compare(sym,SM_ID))
	{
		if(compare(sym,SM_LT_S_BRK))
		{
			expr(sym);
			cheack(sym,SM_RT_S_BRK);
		}

	}else if(compare(sym,SM_LT_R_BRK))
	{
			expr(sym);
			cheack(sym,SM_RT_R_BRK);
	}else if(!compare(sym,SM_TRUE))
	if(!compare(sym,SM_FALSE))
	if(compare(sym,SM_NOT))
	{
		if(!factor(sym))
		{
			print_cerror("Factor composition\n",&sym->type,&lineNumber);
			exit(EXIT_FAILURE);
		}
	}
	else
	{
		return false;
	}
	
	return true;
}


static char term(Token_t *sym)
{
	if(factor(sym))
	{
		
		while(mul_op(sym))
		{
			if(!factor(sym))
			{
				print_cerror("factor composition\n",&sym->type,&lineNumber);
				exit(EXIT_FAILURE);
				break;
				
			}
		}
	}
	else
	{
		return false;
	}
	return true;

}

static char simple(Token_t *sym)
{
	char option = false;	/*option to determine weather a term is expected or a simple*/
	
	if(compare(sym,SM_PLUS) || compare(sym,SM_SUB))		/*cheack for the optional charaters*/
	{
		option = true;		/*indicated that a term is expected*/
	}
	
	if(term(sym))
	{
		
		while(add_op(sym))
		{
			if(!term(sym))
			{
				print_cerror("term composition\n",&sym->type,&lineNumber);
				exit(EXIT_FAILURE);
				break;
			}
		}
	}
	else
	{
		if(option)			/*if a term expected then return true, else return false, indicated a simple or expresion*/
		{
			print_cerror("term composition\n",&sym->type,&lineNumber);
		}
		else
		{
			return false;
		}
		
	}
	
	return true;

}

static void expr(Token_t *sym)
{
	if(simple(sym))
	{
		
		if(relation(sym))
		{
			if(!simple(sym))
			{
				print_cerror("simple composition\n",&sym->type,&lineNumber);
				exit(EXIT_FAILURE);
			}
		}
	}
	else
	{
		print_cerror("Expression composition\n",&sym->type,&lineNumber);
		exit(EXIT_FAILURE);
	}	
}


static void IF(Token_t *sym)
{
	cheack(sym,SM_LT_R_BRK);
	expr(sym);
	cheack(sym,SM_RT_R_BRK);
	common(sym);
	
	if(compare(sym,SM_ELSE))
	{
		common(sym);
	}
}

static void FOR(Token_t *sym)
{
	cheack(sym,SM_LT_R_BRK);
	cheack(sym,SM_ID);
	cheack(sym,SM_EQUAL);
	expr(sym);
	cheack(sym,SM_SEMI_COLON);
	expr(sym);
	cheack(sym,SM_RT_R_BRK);
	common(sym);
}

static void WHILE(Token_t *sym)
{
	cheack(sym,SM_LT_R_BRK);
	expr(sym);
	cheack(sym,SM_RT_R_BRK);
	common(sym);
}

static void assignment(Token_t *sym)
{
	
	if(compare(sym,SM_EQUAL))
	{
		expr(sym);
	}
	else if(compare(sym,SM_LT_S_BRK))
	{
		expr(sym);
		cheack(sym,SM_RT_S_BRK);
		cheack(sym,SM_EQUAL);
		expr(sym);
	}
	else
	{
		print_cerror("Assignment operator expected, (=,[)\n",&sym->type,&lineNumber);
		exit(EXIT_FAILURE);	
	}
	
	cheack(sym,SM_SEMI_COLON);
}

static void common(Token_t *sym)
{
	/*cheack for the matchin of everything in vardecl, command( simple,compound,block)*/
	
	if(compare(sym,SM_PRINT))
	{
		cheack(sym,SM_LT_R_BRK);
		expr(sym);
		cheack(sym,SM_RT_R_BRK);
		cheack(sym,SM_SEMI_COLON);
	}
	else if(compare(sym,SM_ID))
	{
		assignment(sym);
		
	}else if(sym->type != SM_SEMI_COLON)
	if(compare(sym,SM_IF))
	{
		IF(sym);
	}
	else if(compare(sym,SM_FOR))
	{
		FOR(sym);
	}
	else if(compare(sym,SM_WHILE))
	{
		WHILE(sym);
	}
	else if(compare(sym,SM_LT_C_BRK))
	{
		while(!compare(sym,SM_RT_C_BRK))
		{
			common(sym);
		}
	}
	else 
	{
		print_cerror("Command(Simple, Compound,Block)\n",&sym->type,&lineNumber);
		exit(EXIT_FAILURE);			/*error invaled command*/
	}
}

static void VarDecl(Token_t *sym)	/*cheaks for all type's of varible decl;arations*/
{
	cheack(sym,SM_ID);
	
	while(!compare(sym,SM_SEMI_COLON))
	{
		cheack(sym,SM_COMMA);
		cheack(sym,SM_ID);
	}
}

static void santatical(Token_t *sym)		/*Start the snatatical analissys method*/
{
	getSym(sym);				 /*get the first token*/
	
	cheack(sym, SM_CLASS);
	cheack(sym, SM_ID);
	cheack(sym, SM_LT_C_BRK);
	
	while((compare(sym,SM_INT)) || (compare(sym,SM_BOOLEAN))) 	/*if found must cheack santax against Vardecl orders*/
	{
		VarDecl(sym);	
	}
	
	while(!compare(sym, SM_RT_C_BRK))
	{
		common(sym);	/*call the next method to deal with next expected logic*/
	}
	
	//cheack(sym, SM_EOF);		/*Files with out leed char (linux)*/
}


int main( int argc, char * argv[] )
{
    Token_t sym;

   sym.ident = (char*) malloc((MAX_STR_LEN + 1) * sizeof(char));
   if(sym.ident == NULL)
   {
 	  printf("Error Not Enough memory Avalible");
 	  return EXIT_FAILURE;
   }
   else
   {
    if( argc != 2 )
    {
        printf( "Usage: testScanner <filename>\n" );
        printf( "\twhere <filename> is the file to be scanned\n" );
    }
    else
    {
        initScanner( argv[1] );
        santatical(&sym);
    }
    free( sym.ident );
    printf("Reading File has ended");
    return EXIT_SUCCESS;
    }
}


