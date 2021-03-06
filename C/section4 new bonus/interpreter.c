/******************************************************************************
 * Interpreter for simple stack machine. Incorrect code may cause the		  *
 * interpreter to crash. To create a code file to test the interpreter, use   *
 * the command interpreter -m. For full details of commandline options see    *
 * the procedure helpInterpreter											  *
 ******************************************************************************/

#include <stdio.h>
#include <stdlib.h>

#include "boolean.h"		/* boolean type definitions */
#include "interpreter.h"	/* machine code constants */

/* function prototypes */
void halt( void );
void pushAddress(int );
void pushValue(int );
void pushConstant(int );
void store( void );
void minus( void );
void add( void );
void subtract( void );
void multiply( void );
void divide( void );
void jump( int );
void falseJump( int );
void equal( void );
void notEqual( void );
void greater( void );
void less( void );
void not( void );
void and( void );
void or( void );
void output( void );
void execInstr( void );
void step( void );
void run( void );
void load( void );
void makefile( void );
void menu( void );
void processStep( void );
void text2code(char []);	
void helpInterpreter( void );


/* globals */
int st[ST_SIZE];			/* store for code and data */
int p, b, s;				/* instruction pointer, stack base, stack top */
boolean busy;				/* true while machine in running */


void halt()
{
		busy = FALSE;
}

void pushAddress(int displ)
{
		s = s + 1;
		st[s] = b + displ;
		p = p + 2;
}

void pushValue(int displ)
{
		s = s + 1;
		st[s] = st[b + displ];
		p = p + 2;
}

void pushValueStack()	/*This will push the value of the address on the stack*/
{
		//s = s + 1;
		st[s] = st[st[s]];
		p = p + 1;
}

void pushConstant(int value)
{
		s = s + 1;
		st[s] = value;
		p = p + 2;
}

void store()
{
		s = s - 2;
		st[st[s + 1]] = st[s + 2];
		p = p + 1;
}

void minus()
{
		st[s] = -st[s];
		p = p + 1;
}

void add()
{
		s = s - 1;
		st[s] = st[s] + st[s + 1];
		p = p + 1;
}

void subtract()
{
		s = s - 1;
		st[s] = st[s] - st[s + 1];
		p = p + 1;
}

void multiply()
{
		s = s - 1;
		st[s] = st[s] * st[s + 1];
		p = p + 1;
}

void divide()
{
		div_t answer;
		s = s - 1;
		answer = div(st[s] , st[s + 1]);
		st[s] = answer.quot;
		p = p + 1;
}

void jump(int displ)
{
		p = p + displ;
}

void falseJump(int displ)
{
		s = s - 1;
		if(st[s + 1] == 0)
				p = p + displ;
		else
				p = p + 2;
}

void equal()
{
		s = s - 1;
		if(st[s] == st[s + 1])
				st[s] = 1;
		else
				st[s] = 0;
		p = p + 1;
}

void notEqual()
{
		s = s - 1;
		if(st[s] != st[s + 1])
				st[s] = 1;
		else
				st[s] = 0;
		p = p + 1;
}

void greater()
{
		s = s - 1;
		if(st[s] > st[s + 1])
				st[s] = 1;
		else
				st[s] = 0;
		p = p + 1;
}

void less()
{
		s = s - 1;
		if(st[s] < st[s + 1])
				st[s] = 1;
		else
				st[s] = 0;
		p = p + 1;
}

void not()
{
		if(st[s] == 0)
				st[s] = 1;
		else
				st[s] = 0;
		p = p + 1;
}

void and()
{
		s = s - 1;
		if((st[s] == 0) || (st[s + 1] == 0))
				st[s] = 0;
		else
				st[s] = 1;
		p = p + 1;
}

void or()
{
		s = s - 1;
		if((st[s] == 0) && (st[s + 1] == 0))
				st[s] = 0;
		else
				st[s] = 1;
		p = p + 1;
}

void output()
{
		s = s - 1;
		printf("\n%i", st[s + 1]);
		p = p + 1;
}

void execInstr()
{
		switch(st[p])
		{
				case HALT: halt();
						break;
				case PUSHADDRESS: pushAddress(st[p + 1]);
						break;
				case PUSHVALUE: pushValue(st[p + 1]);
						break;
				case PUSHVALUESTACK:
						pushValueStack();
						break;
				case PUSHCONSTANT: pushConstant(st[p + 1]);
						break;
				case STORE: store();
						break;
				case MINUS: minus();
						break;
				case ADD: add();
						break;
				case SUBTRACT: subtract();
						break;
				case MULTIPLY: multiply();
						break;
				case DIVIDE: divide();
						break;
				case JUMP: jump(st[p + 1]);
						 break;
				case FALSEJUMP: falseJump(st[p + 1]);
						 break;
				case EQUAL: equal();
						 break;
				case NOTEQUAL: notEqual();
						 break;
				case GREATER: greater();
						 break;
				case LESS: less();
						 break;
				case NOT: not();
						 break;
				case AND: and();
						 break;
				case OR: or();
						 break;
				case OUTPUT: output();
						 break;
				default: fprintf(stderr, "Invalid opcode\n");
						 exit(EXIT_FAILURE);
		}
}

void step()
{
		execInstr();
		if(busy == TRUE)
		{
				printf("\nStep: p = %i, b = %i, s = %i\n", p, b, s);
				printf("st[s] = %i, st[s-1] = %i\n", st[s], st[s-1]);
				printf("st[p] = %i", st[p]);
		}
}

void run()
{
		printf("Start Execution\n");
		while(busy)
				execInstr();
		printf("\n");
}

/*
 * Procedure load() is used to load a code file to execute. A code file has the
 * format: INIT CODE DATA, where INIT consists of 3 values to be loaded into the
 * p, b and s registers, COD is a sequence of instructions and DATA is the stack
 * botom. The value of in INIT should be the address of the first instruction to
 * be executed. The stack starts directly after the last instruction and b must
 * get this address in INIT. The s register is incremented before pushing a new
 * value onto the stack. The value of s should correspond to the address of the
 * last variable before execution starts. Here is an example:
 * 0 17 18
 * 1 0
 * 3 10
 * 4
 * 1 1
 * 3 11
 * 4
 * 2 1
 * 2 0
 * 9
 * 19
 * 0
 */
void load()
{
		FILE *cfPtr;	/* pointer to code file */
		int i;	/* counter */

		if((cfPtr = fopen("code", "r")) == NULL)
				printf("\nCould not open the code file\n");
		else
		{
				fread(&p, sizeof(int), 1, cfPtr);
				printf("tt%d",p);
				fread(&b, sizeof(int), 1, cfPtr);
				fread(&s, sizeof(int), 1, cfPtr);

				i = 0;
				while(!feof(cfPtr))
				{
						fread(&st[i], sizeof(int), 1, cfPtr);
						i++;
				}
				busy = TRUE;
				printf("\nCode file loaded\n");
				fclose(cfPtr);

		}
}

/*
 * creates a code file.
 * data for code file is entered on command line
 */
void makefile()
{
		FILE *cfPtr;
		int word;

		if((cfPtr = fopen("code", "wb")) == NULL)
				printf("\nCould not open file");
		else
		{
				printf("Enter the instructions\n");
				printf("Enter EOF to end input. Usually <return><ctrl>d\n");
				printf("\? ");
				fflush( stdin );
				scanf("%i", &word);
				while(!feof(stdin))
				{
						fwrite(&word, sizeof(int), 1, cfPtr);
						scanf("%i", &word);
				}
				fclose(cfPtr);
		}
}

/*
 * prints the step menu
 */
void menu()
{
		printf("\nPress 1 to step\n");
		printf("Press 2 to exit\n");
		printf(" \? "); 
}


/*
 * step through code
 */
void processStep()
{
		int option = 0;
		
		while(option != 2)
		{
				menu();
				scanf("%i", &option);
				switch(option)
				{
						case 1: step();
								break;
						case 2: printf("\nExiting...\n");
								break;
						default:printf("Invalid option\n");
				}
				
		}
}

/*
 * converts a text file to a code file.
 * char name[]:
 * 		name of text file to be converted
 */
void text2code(char name[])		
{
		FILE *cfPtr, *tfPtr;

		int word;

		if((tfPtr = fopen(name, "r")) == NULL)
				printf("interpreter: Could not open text file\n");
		else
		{
				if((cfPtr = fopen("code", "w")) == NULL)
						printf("interpreter: Could not open code file\n");
				else
				{
						while(!feof(tfPtr))
						{
							fscanf(tfPtr, "%i\n", &word);
							fwrite(&word, sizeof(int), 1, cfPtr);
						}
						fclose(tfPtr);
						fclose(cfPtr);
						printf("\nCode file generated\n");
				}
		}
}
							
/*
 * prints the commandline options to the standard out
 */
void helpInterpreter()
{
		printf("Usage: interpreter -option [textfile]\n");
		printf("\nwhere option includes\n");
		printf("\t-l\t loads the code file\n");
		printf("\t-r\t loads and executes program\n");
		printf("\t-m\t loads a code file manually\n");
		printf("\t-s\t steps through code\n");
		printf("\t-t\t creates a code file from a text file\n");
		printf("\t-h\t this information\n");
}						
						
int main(int argc, char *argv[])
{
		
		if(argc < 2)  /* process command line arguments */
		{
				helpInterpreter();
		}
		else
		{
				if(argv[1][1] == 'l')
				{
						load();
				}
				else if(argv[1][1] == 'r')
				{
						load();
						run();
				}
				else if(argv[1][1] == 'm')
				{
						makefile();
				}
				else if(argv[1][1] == 's')
				{
						load();
						processStep();
				}
				else if(argv[1][1] == 't')
				{
						if(argc < 3)
						{
								printf("interpreter: missing file argument\n");
								printf("Try `interpreter -h' for more information\n");
						}
						else
							text2code(argv[2]);
				}
				else if(argv[1][1] == 'h')
				{
						helpInterpreter();
				}
				else
				{
						printf("interpreter: invalid option\n");
						printf("Try `interpreter -h' for more information\n");
				}
		}
		return EXIT_SUCCESS;
}
				
		
