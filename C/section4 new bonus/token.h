/******************************************************************************
 * 													
 * FILE NAME: token.h									
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
#ifndef TOKEN_HDR
#define TOKEN_HDR
typedef enum			/* token types */
{
	SM_CLASS,			/* The keyword "class" */
	SM_ID,				/* Any identifier */
	SM_NUMBER,			/* Any number */
	SM_INT,
	SM_BOOLEAN,
	SM_PRINT,
	SM_IF,
	SM_WHILE,
	SM_FOR,
	SM_ELSE,
	
	/*BRACKETS SANTAX ECT*/
	SM_LT_C_BRK,
	SM_RT_C_BRK,
	SM_LT_S_BRK,
	SM_RT_S_BRK,
	SM_LT_R_BRK,
	SM_RT_R_BRK,
	SM_COMMA,
	SM_SEMI_COLON,
	
	/*COMPARISION*/
	SM_EQUALS,
	SM_NOT_EQUAL,
	SM_SMALLER,
	SM_LARGER,
	SM_E_SMALLER,
	SM_E_LARGER,
	SM_OR,
	SM_AND,
	/**ASSIGNMENT*/
	SM_EQUAL,
	SM_PLUS,
	SM_SUB,
	SM_MULTI,
	SM_DIVIDE,
	SM_TRUE,
	SM_FALSE,
	SM_NOT,
	SM_NEWLINE,
	SM_EOF,
	SM_ERROR
	
}TokenType_t;

typedef enum			/* token types */
{
	CM_STAND,CM_ARRAY
}TokenType_c;

typedef struct			/* token type and its value */
{
	TokenType_t type;	/* SM_PROGRAM, SM_COMMA, etc */
	int value;		    /* numbers are ints */
	char *ident;        /* identifiers are strings */
}Token_t;

#endif
