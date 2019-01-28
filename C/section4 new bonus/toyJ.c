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
#include "boolean.h"
#include "symboltable.h"
#include "interpreter.h"
#include "string.h"

/*define constants*/
#define true 1
#define false 0

int pos = 0, varibles = 0, Error = 0; 			/*Declare to global varible to store pos in code*/
int st[ST_SIZE];			/* store for code and data */


/* function prototypes */
static void common(Token_t *sym);
static void expr(Token_t *sym, TokenType_t *type);

static void cheack(Token_t *sym, TokenType_t expected)				/*method for cheacking the for the symbol*/
{
	if(sym->type != expected)
	{
		print_error(expected,&sym->type,&lineNumber);			/*parse on whearther it is missing or expected*/
		printf("Compiling teminated\n");
		exit(EXIT_FAILURE);
	}
	else
	{
		getSym(sym);
	}
}


static char compare(Token_t *sym, TokenType_t expected)				/*compares tokens, in if statments*/
{
	if(sym->type == expected)						/*if tokens equal (true) , gets the next token*/
	{	
		getSym(sym);
		return true;
	}
	else
	return false;
}

static void expr_cheack(Token_t *sym, TokenType_t expected, char* msg)		/*Method to cheack expression type*/
{
	TokenType_t TExpr;
	
	expr(sym,&TExpr);
	if(TExpr != expected)					/*Cheack to see if expression is same type as expected else error*/
	{
		print_error(expected,&TExpr,&lineNumber);	
		printf("|-> %s\n",msg);
					
		printf("Compiling teminated\n");
		Error = 1;//exit(EXIT_FAILURE);
	}
}

static void setCode(int instruction)
{
	if(pos < (ST_SIZE - varibles))
	{
		st[pos] = instruction;
		pos++;
	}
}

static void setCode2(int instruction, int value)
{
	if(pos < (ST_SIZE - varibles))
	{
		st[pos] = instruction;
		pos++;
		
		st[pos] = value;
		pos++;
	}
}

static char relation (Token_t *sym, TokenType_t *type, int *code)			/*Takes in the Expected token type*/
{
	TokenType_t T_Temp;
	
	code[1] = -1;
	
	if(compare(sym,SM_EQUALS))					/*Get the type of in terms of boolean or int*/
	{
		T_Temp = SM_BOOLEAN;
		code[0] = EQUAL;
	}
	else if(compare(sym,SM_NOT_EQUAL))
	{
		T_Temp = SM_BOOLEAN;
		code[0] = NOTEQUAL;
	}
	else if(compare(sym,SM_SMALLER))
	{
		T_Temp = SM_INT;
		code[0] = LESS;
	}
	else if(compare(sym,SM_LARGER))
	{
		T_Temp = SM_INT;
		code[0] = GREATER;
	}
	else if(compare(sym,SM_E_SMALLER))
	{
		T_Temp = SM_INT;
		code[0] = GREATER;
		code[1] = NOT;
	}
	else if(compare(sym,SM_E_LARGER))
	{
		T_Temp = SM_INT;
		code[0] = LESS;
		code[1] = NOT;
	
	}else
		return false;

	if(((*type) == SM_BOOLEAN) && (T_Temp != (*type)))		/*do boolean comparison checks*/
	{
		print_cerror("Relation of same type as  first Simple in Expr (==,!=)", &T_Temp, &lineNumber);
		Error = 1;//exit(EXIT_FAILURE);
	}
	
	return true;
}

static char mul_op (Token_t *sym, TokenType_t *type, int *code)
{
	TokenType_t T_Temp;
	
	if(compare(sym,SM_MULTI))				/*Get the type of in terms of boolean or int*/
	{
		T_Temp = SM_INT;
		(*code) = MULTIPLY;			/*Set the value for the operation*/
	}
	else if(compare(sym,SM_DIVIDE))
	{
		T_Temp = SM_INT;
		(*code) = DIVIDE;
	}
	else if(compare(sym,SM_AND))
	{	
		T_Temp = SM_BOOLEAN;
		(*code) = AND;
	}
	else
		return false;

	if(T_Temp != (*type))					/*Is same as Expected else error*/
	{
		print_cerror(" a Mul_Op of same type as first factor in Term", &T_Temp, &lineNumber);
		Error = 1;//exit(EXIT_FAILURE);
	}

	return true;
}


static char add_op (Token_t *sym, TokenType_t *type, int *code)
{
	TokenType_t T_Temp;
	
	if(compare(sym,SM_PLUS))				/*Get the type of in terms of boolean or int*/
	{
		T_Temp = SM_INT;
		(*code) = ADD;
	}
	else if(compare(sym,SM_SUB))
	{
		T_Temp = SM_INT;
		(*code) = SUBTRACT;
	}
	else if(compare(sym,SM_OR))
	{
		T_Temp = SM_BOOLEAN;
		(*code) = OR;
	}
	else	
		return false;

	if(T_Temp != (*type))					/*Is same as Expected else error*/
	{
		print_cerror("Add_Op of same type as first term in Simple", &T_Temp, &lineNumber);
		Error = 1;//exit(EXIT_FAILURE);
	}
		
	
	return true;
}

static char factor (Token_t *sym, TokenType_t *type)
{
	int Disp;
	TokenType_c extra;
	
	if(sym->type == SM_NUMBER)
	{
		(*type) = SM_INT;
		setCode2(PUSHCONSTANT,sym->value);
		getSym(sym);
	}
	else if(sym->type == SM_ID)
	{
		if(find(sym->ident, type, &Disp, &extra) == FALSE)
		{
			printf("LN %d: Error Varible Not Decalared '%s'\n",lineNumber,sym->ident);/*error vaible undecalred exit*/
			Error = 1;//exit(EXIT_FAILURE);					
		}
		
		getSym(sym);	
		
		if(compare(sym,SM_LT_S_BRK))		/*implem,entation later in code for arrays*/
		{
			if(extra == CM_ARRAY)
			{

				setCode2(PUSHADDRESS, Disp);	
				expr_cheack(sym, SM_INT, "in Factor expect 'ID[' INT ']'\n");	
				setCode(ADD);
				setCode(PUSHVALUESTACK);		
				cheack(sym,SM_RT_S_BRK);
			}
			else
			{
				printf("LN %d: Error id is ment to be of type int or boolean array\n", lineNumber);
				Error = 1;//exit(EXIT_FAILURE);	
			}

		}
		else
		{
			if(extra == CM_STAND)
			{
				setCode2(PUSHVALUE, Disp);	
			}
			else
			{
				printf("LN %d: Error id is ment to be of type int or boolean\n", lineNumber);
				Error = 1;//exit(EXIT_FAILURE);	
			}
		}

	}else if(compare(sym,SM_LT_R_BRK))
	{
		expr(sym,type);					/*Cheack, Return type of the expression composition to factor*/
		cheack(sym,SM_RT_R_BRK);
		
	}else if(compare(sym,SM_TRUE))
	{	
		setCode2(PUSHCONSTANT, TRUE);
		(*type) = SM_BOOLEAN;
		
	} else if(compare(sym,SM_FALSE))
	{
		setCode2(PUSHCONSTANT, FALSE);
		(*type) = SM_BOOLEAN;
	
	}else if(compare(sym,SM_NOT))
	{
		if(!factor(sym,type))					/*Check to see if the is a fator followed by Not*/
		{
			print_cerror("Factor composition\n",&sym->type,&lineNumber);
			Error = 1;//exit(EXIT_FAILURE);
		}
		else
		{
			if((*type) != SM_BOOLEAN)			/*Display error expected boolean factor after NOT,!*/
			{
				printf("LN %d: Error Expected Boolean function after NOT\n",lineNumber);
				Error = 1;//exit(EXIT_FAILURE);
			}
		}
		setCode(NOT);	/*change the logic on the staple to invert false becomes true, and visuversa*/
	}
	else
	{
		return false;
	}
	
	return true;
}


static char term(Token_t *sym, TokenType_t *type)
{
	TokenType_t T_Term; 						/*The overall term sensis; temp*/
	int code;
	
	if(factor(sym, type))
	{

		while(mul_op(sym, type, &code))				/*Mul_op displays the Operate error*/
		{
			if(!factor(sym,&T_Term))
			{
				print_cerror("factor composition\n",&sym->type,&lineNumber);
				Error = 1;//exit(EXIT_FAILURE);
				break;
			}
			else
			{		
				if(T_Term != (*type))		/*If fator exist cheack to see if it matchs the first factor type*/
				{
					print_cerror("Factors are not all of the same type as first Factor, in Term", &T_Term , &lineNumber);
					Error = 1;//exit(EXIT_FAILURE);
				}		
			}
			
			setCode(code);		/*Set the type of operation return by mulop*/
		}
	}
	else
	{
		return false;
	}
	return true;

}

static char simple(Token_t *sym, TokenType_t *type)
{
	TokenType_t T_Term; 					/*The overall term sensis; temp*/
	char option = false, minus = false;	
	int code;				/*option to determine weather a term is expected or a simple*/
	
	if(compare(sym,SM_PLUS))
	{
		option = true;				/*indicated that a term is expected*/
	}
	else if(compare(sym,SM_SUB))			/*cheack for the optional charaters*/
	{
		option = true;	
		minus = true;			/*indicated that a term is expected*/
	}

	if(term(sym,type))
	{

		if(option)					/*if true then term must also be of type INT*/
		{
			if((*type) != SM_INT)				/*Display error*/
			{
				print_cerror("term or terms not of type SM_INT", type, &lineNumber);
				Error = 1;//exit(EXIT_FAILURE);
			}
			else
			{
				if(minus)
				setCode(MINUS);
			}
		}			
		while(add_op(sym,type,&code))
		{
			if(!term(sym,&T_Term))
			{
				print_cerror("term composition\n",&sym->type,&lineNumber);
				Error = 1;//exit(EXIT_FAILURE);
				break;
			}
			else
			{
				if((*type) != T_Term)
				{
					print_cerror("terms are not all of the same type as first term, in Simple", &T_Term, &lineNumber);
					Error = 1;//exit(EXIT_FAILURE);
				}
			}
			setCode(code);		/*Set the type of operation return by add_op*/	
		}
	}
	else
	{
		if(option)				/*if a term expected then return true, else return false, indicated a simple or expresion*/
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

static void expr(Token_t *sym, TokenType_t *type)
{
	TokenType_t T_simple;
	int code[2];
	
	if(simple(sym, type))
	{
		if(relation(sym, type, code))
		{
			
			if(!simple(sym, &T_simple))
			{
				print_cerror("1simple composition\n",&sym->type,&lineNumber);
				Error = 1;//exit(EXIT_FAILURE);
			}
			else
			{
				if((*type) != T_simple)				/*Cheack to see if Comparing same SM type's*/
				{						/*Error type's where not the same*/
					print_cerror("2nd Simple to be of same type as first Simple in Expr",  &T_simple, &lineNumber);
					Error = 1;//exit(EXIT_FAILURE);
				
				}
				else
				{
					(*type) = SM_BOOLEAN;	/*A relationship Exits*/
				}
			}
			setCode(code[0]);		/*Set the type of operation return by add_op*/	
			if(code[1] != -1)
			setCode(code[1]);
		}
	}
	else
	{
		print_cerror("Expression composition\n",&sym->type,&lineNumber);
		Error = 1;//exit(EXIT_FAILURE);
	}	
}

static void IF(Token_t *sym)
{
	int p,p2;
	
	cheack(sym,SM_LT_R_BRK);
		expr_cheack(sym, SM_BOOLEAN, "in IF Statement");
	cheack(sym,SM_RT_R_BRK);
	
	p = pos;			/*assign the start position to calculate the jump*/
	setCode2(FALSEJUMP,0);
	common(sym);
	if(compare(sym,SM_ELSE))
	{
		p2 = pos;
		setCode2(JUMP,0);
		st[p + 1] = (pos - p);
		common(sym);
		st[p2 + 1] = (pos - p2);
	}
	else
	{
		st[p + 1] = (pos - p);
	}
}

static void FOR(Token_t *sym)
{
	TokenType_t id_type;
	TokenType_c extra;
	int p, p2, Disp;
	
	cheack(sym,SM_LT_R_BRK);
	
	if(!find(sym->ident, &id_type, &Disp, &extra))
	{
		printf("LN %d: Error Varible Not Decalared '%s'\n",lineNumber,sym->ident);
		Error = 1;//exit(EXIT_FAILURE);			/*error vaible undecalred exit*/
	}
	setCode2(PUSHADDRESS, Disp);
	getSym(sym);	
					
	if(id_type != SM_INT)	/*Cheack to see if ID is declared as integer*/
	{
		
		print_cerror("ID of Type SM_INTEGER in FOR Statement Assigmnet arg 1",  &id_type, &lineNumber);
		Error = 1;//exit(EXIT_FAILURE);

	}
	
	cheack(sym,SM_EQUAL);
		
		expr_cheack(sym, SM_INT, "in FOR Statement Assigmnet arg 1");
	setCode(STORE);
		
	cheack(sym,SM_SEMI_COLON);
	
		p = pos;
		setCode2(PUSHVALUE,Disp);
		expr_cheack(sym, SM_INT, "in FOR Statement Condition arg 2");
		setCode(GREATER);
		setCode(NOT);
		p2 = pos;
		setCode2(FALSEJUMP,0);
		
	cheack(sym,SM_RT_R_BRK);
	common(sym);
	/*Increase the value of the test varible*/
	setCode2(PUSHADDRESS, Disp);
	setCode2(PUSHVALUE, Disp);
	setCode2(PUSHCONSTANT,1);
	setCode(ADD);
	setCode(STORE);
	
	setCode2(JUMP,(p - pos));
	st[p2 + 1] = (pos - p2) ;
}

static void WHILE(Token_t *sym)
{
	int p, p2;
	
	cheack(sym,SM_LT_R_BRK);
		p = pos;
		expr_cheack(sym, SM_BOOLEAN, "in WHILE Statement Condition arg 1");
		
	cheack(sym,SM_RT_R_BRK);
	
	p2 = pos;		/*Assing the position where code starts*/
	setCode2(FALSEJUMP,0);
		
	common(sym);
	
	setCode2(JUMP, (p - pos));	/*Set the jump to the begining of the while for evaluation*/
	
	st[p2 + 1] = (pos - p2);
	
}

static void assignment(Token_t *sym)
{
	TokenType_t TExpr, id_type;					/*memorise the varible type.get next SM*/
	TokenType_c extra;
	int Disp;
	
	if(find(sym->ident, &id_type, &Disp, &extra) == FALSE)
	{
		printf("LN %d: Error Varible Not Decalared '%s'\n",lineNumber,sym->ident);
		Error = 1;//exit(EXIT_FAILURE);
	}
	
	setCode2(PUSHADDRESS, Disp);
	getSym(sym);
	
		if(compare(sym,SM_EQUAL))
		{
			if(extra == CM_STAND)
			{
				expr(sym, &TExpr);
				setCode(STORE);
			}
			else
			{
				printf("LN %d: Error id not of type int or boolean\n", lineNumber);
				Error = 1;//exit(EXIT_FAILURE);	
			}
		}
		else if(compare(sym,SM_LT_S_BRK))		/*leave for late for implementation s of arrays*/
		{
			if(extra == CM_ARRAY)
			{

				expr_cheack(sym, SM_INT, "in Assignment 'ID[' INT ']'");	/*This Should be Integer exprssion*/
				setCode(ADD);		/*advance the pointer onwards to the element to be acessed in the array*/
				
				cheack(sym,SM_RT_S_BRK);
				cheack(sym,SM_EQUAL);
				expr(sym, &TExpr);
				setCode(STORE);		
			}
			else
			{
				printf("LN %d: Error id not of type int or boolean array\n", lineNumber);
				Error = 1;//exit(EXIT_FAILURE);		
			}				
		}
		else
		{
			print_cerror("Assignment operator expected, (=,[)\n",&sym->type,&lineNumber);
			exit(EXIT_FAILURE);	
		}
		
	if(id_type != TExpr)						/*Cheack to see if assignment, is of same type's*/
	{
		printf("LN %d: Invailed Assignment, Incompatible type's\n", lineNumber);
		Error = 1;//exit(EXIT_FAILURE);	
	} 
	
	cheack(sym,SM_SEMI_COLON);
}

static void PRINT(Token_t *sym)
{
	TokenType_t T_Print;

	cheack(sym,SM_LT_R_BRK);
	expr(sym,&T_Print);
	cheack(sym,SM_RT_R_BRK);
	cheack(sym,SM_SEMI_COLON);
	
	setCode(OUTPUT);		/*Print out the value on the screen which is on the stapel*/
}

static void common(Token_t *sym)
{
	/*cheack for the matchin of everything in vardecl, command( simple,compound,block)*/
		
	
	if(compare(sym,SM_PRINT))
	{
		PRINT(sym);
	}
	else if(sym->type == SM_ID)
	{
		assignment(sym);
		
	}else if(!compare(sym,SM_SEMI_COLON))
	{
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
			while(!compare(sym,SM_RT_C_BRK))		/*multipy commands*/
			{
				common(sym);
			}
		}
		else 
		{
			print_cerror("Command(Simple, Compound, Block)\n", &sym->type, &lineNumber);
			exit(EXIT_FAILURE);
		}
	}
}

static void dec_cheack_id(Token_t *sym,TokenType_t type)		/*method declares and cheacks for an id*/
{
	char varName[MAX_STR_LEN + 1];
	int size;
	if(sym->type != SM_ID)
	{
		print_error(type,&sym->type,&lineNumber);		/*parse on whearther it is missing or expected*/
		printf("\n Compiling teminated\n");
		exit(EXIT_FAILURE);
	}
	else
	{
		strcpy(varName,sym->ident);	
		
		getSym(sym);
		if(sym->type == SM_LT_S_BRK)	/*Cheack if there may be an array decalration*/
		{
			getSym(sym);
			if(sym->type == SM_NUMBER)
			{
				size = sym->value;
				if(size > 1)
				{
				 	getSym(sym);
				 	cheack(sym, SM_RT_S_BRK);
				 	
					if(insert(type, varName, size, CM_ARRAY) == FALSE)
					{
						printf("LN %d: Error previous declaration of varible for use with array, '%s'\n",lineNumber,sym->ident);
						Error = 1;//exit(EXIT_FAILURE);
					}
					
				}
				else
				{
					printf("LN %d: Error Expected a positive Array Size larger than 1\n",lineNumber);
					Error = 1;//exit(EXIT_FAILURE);
				}
			}
			else
			{
				printf("LN %d: Error Expected type of SM_NUMBER for Array Size\n",lineNumber);
				Error = 1;//exit(EXIT_FAILURE);
			}
		
		}
		else
		{
			if(insert(type, varName,1,CM_STAND) == FALSE)
			{
				printf("LN %d: Error previous declaration of varible, '%s'\n",lineNumber,sym->ident);
				Error = 1;//exit(EXIT_FAILURE);
			}
		}
		
		
	}
}

static void VarDecl(Token_t *sym)					/*cheaks for all type's of varible decl;arations*/
{
	TokenType_t type = sym->type;
	getSym(sym);
	dec_cheack_id(sym,type);
	while(compare(sym,SM_COMMA))
	{
		dec_cheack_id(sym,type);
	}
	cheack(sym,SM_SEMI_COLON);
}

static void santatical(Token_t *sym)					/*Start the snatatical analissys method*/
{
	getSym(sym);				 			/*get the first token*/
	
	cheack(sym, SM_CLASS);
	cheack(sym, SM_ID);
	cheack(sym, SM_LT_C_BRK);
	
	while((sym->type == SM_INT) || (sym->type == SM_BOOLEAN)) 	/*if found must cheack santax against Vardecl orders*/
	{
		VarDecl(sym);	
	}
	
	varibles = getstack();
	
	while(!compare(sym, SM_RT_C_BRK))
	{
		common(sym);						/*call the next method to deal with next expected logic*/
	}
	
	//cheack(sym, SM_EOF);						/*Files with out leed char (linux)*/
	setCode(HALT);
}
void writefile()
{
	FILE *cfPtr;
	 int p = 0,b = pos,s =(pos + getstack() - 1); 	
		if((cfPtr = fopen("code", "w")) == NULL)
			printf("interpreter: Could not open code file\n");
		else
		{
			fwrite(&p, sizeof(int), 1, cfPtr);
			fwrite(&b, sizeof(int), 1, cfPtr);
			fwrite(&s, sizeof(int), 1, cfPtr);
			fwrite(st, sizeof(int), pos, cfPtr);
			
			fclose(cfPtr);
			printf("Code file generated\n");
			
		}
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
		initST();
		santatical(&sym);
	}
		free( sym.ident );
		printf("Reading File has ended\n");
		if(Error == 0)
		writefile();

		return EXIT_SUCCESS;
	}
}

