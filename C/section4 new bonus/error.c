/******************************************************************************
 * 													
 * FILE NAME: error.h									
 *													
 * PURPOSE: Data type definitions for the lexical scanner			
 *														
 * 													
 * GLOBAL TYPE DECLARATIONS:									
 * Name			Type				Description				
 * ----			----				-----------				
 * tokenType    		enum		Defines symbolic constants for compiler	
 *													
 * token        		struct      Defines token type for lexical scanner	
 * 													
 * 													
 * DEVELOPMENT HISTORY:										
 * Date				Name						Description	
* 04/08/2005			W.W.A Oliver					NoN			
 *  													
 ******************************************************************************/
#include <stdio.h>
#include "token.h"      /* type definitions */
  
static void printSym(TokenType_t type)
{
    switch(type)
    {
	case SM_ID:
            printf("Identifier\n");
            break;
	    
	case SM_NUMBER:
        	printf("Number\n");
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
		printf("End of File after 'class{}'\n");
		break;
		
	default:
            printf("Impossible\n");

    }	
}

 void print_error(TokenType_t type,TokenType_t *found, int *lineNumber)
 {
 	printf("LN %d Expected type: ",(*lineNumber));
 	printSym(type);
 	printf("|--> Not type: ");
 	printSym((*found));
 }
 
 
 void print_cerror(char msg[],TokenType_t *found, int *lineNumber)
 {
 	printf("LN %d: Error Expected %s\n",(*lineNumber),msg);
	printf("|--> Not of type: ");
	printSym((*found));
 }
 


