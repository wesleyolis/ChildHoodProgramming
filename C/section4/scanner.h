/******************************************************************************
 * 																			  *
 * FILE NAME: scanner.h														  *
 *																			  *
 * PURPOSE: Definitions of exported variables and functions					  *
 *																			  *
 * 																			  *
 * GLOBAL TYPE AND EXTERNAL VARIABLE DECLARATIONS:							  *
 * Name			Type				Description							      *
 * ----			----				-----------								  *
 * lineNumber	int					Current line in source file				  *
 *																		      *
 * NOTES:															          *
 * source file: The file being scanned										  *
 * 																		      *
 * DEVELOPMENT HISTORY:														  *
 * Date				Name						Description					  *
 * ----				----						-----------					  *
 ******************************************************************************/

#ifndef SCANNER_HDR	        /* prevents multiple inclusions */
#define SCANNER_HDR

#define MAX_STR_LEN 32		/* maximum length of a variable */

#include "token.h"

/*
 * External variable declarations
 */
extern int lineNumber;  /* current line number in source file */


/* external function prototypes */

/*
 * Initialize globals in scanner.
 * Must be called before start using the scanner.
 * Parameter:
 * 		char *file: file that scanner will scan
 */
extern void initScanner( char * );


/*
 * Get the next token in source file
 * Parameter:
 * 		Token_t *sym - The next symbol (call by reference)
 */
extern void getSym( Token_t * );

#endif
