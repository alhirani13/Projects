/*
Hirani, Altamshali
AH45675
EE312
Assignment 5
*/
#include "assignment-5_calculator.h"
#include <stdlib.h>
#include <stdio.h>

// code for eval should allocate an operand_stack (declared in assignment-5_calculator.h)
// and push operands into the stack, and pop arguments when operator is encountered
// e.g. 2 2 + 3 *
// [] -> [2] -> [2,2] -> [2] - > [] -> [4] -> [4,3] -> [4] -> [] -> [7]
// return the last value in the stack
// if size > 1 when done, assert an error


double eval( char expr[][MAX_EXPR_LENGTH], int N ) {
	Stack* stk = initialize_stk();
	double one = 0; double two = 0;
	for (int i = 0; i < N; i++)
	{
		if (expr[i][0] == '+' && size(stk) > 1)
		{
			one = pop(stk); two = pop(stk);
			push(stk, one + two);
		}
		else if (expr[i][0] == '-' && expr[i][1] == '\0' &&  size(stk) > 1)
		{
			one = pop(stk); two = pop(stk);
			push(stk, one - two);
		}
		else if (expr[i][0] == '*' && size(stk) > 1)
		{
			one = pop(stk); two = pop(stk);
			push(stk, one * two);
		}
		else if (expr[i][0] == '/' && size(stk) > 1)
		{
			one = pop(stk); two = pop(stk);
			push(stk, one / two);
		}
		else
		{
			push(stk, atof(expr[i]));
		}

		/*switch (expr[i][0])
		{
		case '+': case '-': case '*': case '/': one = pop(stk); two = pop(stk); break;
		default: push(stk, atof(expr[i]));break;
		}
		//if(expr[i][0] == '+')
		*/
	}
	if (stk->size > 1)
	{
		return -1.0;
	}
	return pop(stk);
}
