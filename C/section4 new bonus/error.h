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
#include "token.h"

extern  void print_error(TokenType_t type,TokenType_t *found, int *lineNumber);
extern  void print_cerror(char msg[],TokenType_t *found, int *lineNumber);
