static void cheack_dec_id(Token_t *sym)	/*method declares and cheacks for an id*/
{
	extern boolean find( sym->ident, TokenType_t *token, int *disp)


	if(sym->type != SM_ID)
	{
		print_error(SM_ID,&sym->type,&lineNumber);				/*parse on whearther it is missing or expected*/
		printf("Compiling teminated\n");
		exit(EXIT_FAILURE);
	}
	else
	{
		if(insert(type, sym->ident) == FALSE)
		{
			printf("LN %d: Error previous declaration of varible, '%s'\n",lineNumber,sym->ident);
		}
		getSym(sym);
	}
}