/******************************************************************************
* 		
* FILE NAME: symboltable.h						
*								
* PURPOSE: Definitions of exported variables and functions
*							
* 						
* GLOBAL TYPE AND EXTERNAL VARIABLE DECLARATIONS:
* Name			Type				Description	
* ----			----				-----------
* none								
*							
* NOTES:					
* 		Needs boolean.h and token.h
*					
*				
* DEVELOPMENT HISTORY:	
* Date				Name		Description
* ----				----		-----------
*  
******************************************************************************/
#ifndef SYMBOLTABLE_HDR
#define SYMBOLTABLE_HDR

#include "boolean.h"
#include "token.h"
/*
 * Initialize the symboltable
 * Takes no parameters
 * Must be called the first time the symboltable is being used
 */
extern void initST( void );

/*
 * To insert a new entry in the symboltable
 * Parameters:
 * 		TokenType_t type - The type of the variable inserted
 * 		char * name - Name of the variable to be inserted
 * 	Return:
 * 		FALSE - The variable does already exist
 * 		TRUE - The variable was added successfully
 */
extern boolean insert( TokenType_t, char * ); 

/*
 * To find an entry in the symboltable
 * Parameters:
 * 		char * name - The name of the variable to find
 *		TokenType_t *vType - The type of the variable (call by reference parameter)
 *		int *vDisplace - The displacement of the variable relative to the first
 *						 variable declared (call by reference)
 * Return:
 * 		FALSE - The entry was not found
 * 		TRUE - The entry was found
 */
extern boolean find( char *, TokenType_t *, int *);
/*
*	This method will free all malloc memory
*/
extern void Free_Structs();

#endif
