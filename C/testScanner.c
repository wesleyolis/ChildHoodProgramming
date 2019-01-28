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
#include "scanner.h"

/* function prototypes */
void printSym( Token_t * );

int main( int argc, char * argv[] )
{
    Token_t sym;

    sym.ident = ( char * ) malloc( ( MAX_STR_LEN + 1 ) * sizeof( char ) );

    if( argc != 2 )
    {
        printf( "Usage: testScanner <filename>\n" );
        printf( "\twhere <filename> is the file to be scanned\n" );
    }
    else
    {
        initScanner( argv[1] );
        getSym( &sym );
        while( sym.type != SM_EOF )
        {
		if( sym.type != SM_NEWLINE )
		{
                	printSym( &sym );
                	printf( "\n" );
		}
            	getSym( &sym );
        }
    }
    free( sym.ident );
    printf("Reading File has ended");
    return EXIT_SUCCESS;
}

/* 
 * Prints the current token to the standard out * Parameter:
 * 		Token_t *sym - The current token
 */
void printSym( Token_t *sym )
{
    switch( sym->type )
    {
	case SM_ID:
            printf("Identifier:  %s\n", sym->ident );
            break;
	    
	case SM_NUMBER:
        	printf("Number:  %d\n", sym->value );
		break;  
	
	case SM_CLASS:
		printf("Class\n");
		break;
	
	case SM_INT:
		printf("Int\n");
		break;
	
	case SM_BOOLEAN:
		printf("Boolean\n");
		break;
	
	case SM_PRINT:
		printf("Print\n");
		break;
	
	case SM_IF:
		printf("If\n");
		break;
	
	case SM_WHILE:
		printf("While\n");
		break;
	
	case SM_FOR:
		printf("For\n");
		break;
	
	case SM_ELSE:
		printf("Else\n");
		break;
	
	case SM_LT_C_BRK:
		printf("Left Curly Bracket\n");
		break;
	
	case SM_RT_C_BRK:
		printf("Right Curly Bracket\n");
		break;
	
	case SM_LT_S_BRK:
		printf("Left Square Bracket\n");
		break;
	
	case SM_RT_S_BRK:
		printf("Right Square Bracket\n");
		break;
	
	case SM_LT_R_BRK:
		printf("Left Round Bracket\n");
		break;
	
	case SM_RT_R_BRK:
		printf("Right Round Bracket\n");
		break;
	
	case SM_COMMA:
		printf("Comma\n");
		break;
	
	case SM_SEMI_COLON:
		printf("Semi Colon\n");
		break;
	
	case SM_EQUALS:
		printf("Equals\n");
		break;
	
	case SM_NOT_EQUAL:
		printf("Not Equal\n");
		break;
	
	case SM_SMALLER:
		printf("Smaller Than\n");
		break;
	
	case SM_LARGER:
		printf("Larger Than\n");
		break;
	
	case SM_E_SMALLER:
		printf("Equal or Smaller Than\n");
		break;
	
	case SM_E_LARGER:
		printf("Equal or Larger Than\n");
		break;
	
	case SM_OR:
		printf("OR\n");
		break;
	
	case SM_AND:
		printf("And\n");
		break;
	
	case SM_EQUAL:
		printf("Equal\n");
		break;
	
	case SM_PLUS:
		printf("Plus\n");
		break;
	
	case SM_SUB:
		printf("Subtract\n");
		break;
	
	case SM_MULTI:
		printf("Multipy\n");
		break;
	
	case SM_DIVIDE:
		printf("Divide\n");
		break;
	
	case SM_TRUE:
		printf("True\n");
		break;
	
	case SM_FALSE:
		printf("False\n");
		break;
	case SM_NOT:
		printf("Not\n");
		break;
	
	case SM_NEWLINE:
		printf("New line\n");
		break;
	
	case SM_EOF:
		printf("End of File\n");
		break;
		
	default:
            printf("Impossible\n");

    }
}
