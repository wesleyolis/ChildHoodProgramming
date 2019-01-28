/******************************************************************************
 * 																			  *
 * FILE NAME: interpreter.h													  *
 *																			  *
 * PURPOSE: Defines mnemonics for interpreter's instruction set 			  *
 *																			  *
 *																			  *
 * DEVELOPMENT HISTORY:														  *
 * Date				Name						Description					  *
 * ----				----						-----------					  *
 *  																		  *
 ******************************************************************************/
#ifndef INTERPRETER_HDR
#define INTERPRETER_HDR

/* Defines the constants */
#define HALT 0
#define PUSHADDRESS 1
#define PUSHVALUE 2
#define PUSHCONSTANT 3
#define STORE 4
#define MINUS 5		/* The unary minus */
#define ADD 6
#define SUBTRACT 7
#define MULTIPLY 8
#define DIVIDE 9
#define JUMP 10
#define FALSEJUMP 11
#define EQUAL 12
#define NOTEQUAL 13
#define GREATER 14
#define LESS 15
#define NOT 16
#define AND 17
#define OR 18
#define OUTPUT 19
#define ERROR 20	/* interpreter will generates an error message */

#define ST_SIZE 1024		/* size of store */

#endif
