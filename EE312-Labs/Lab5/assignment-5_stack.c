/*
Hirani, Altamshali
AH45675
EE312
Assignment 5
*/
#include "assignment-5_stack.h"
#include <stdlib.h>
#include <stdio.h>

/* Your stack implementations here */

Stack* initialize_stk() {
	Stack* mainStk = malloc(sizeof(Stack));
	mainStk->size = 0;
	(*mainStk).top = NULL;
	return mainStk;
}

int size(Stack* stk) {
	return stk->size;
}

void push(Stack* stk, double data) {
	stk->size = stk->size + 1;
	Node_s* newS = malloc(sizeof(Node_s));
	(*newS).data = data;
	(*newS).next = (*stk).top;
	(*stk).top = newS; 

}

double pop(Stack* stk) {
	if (stk->top == NULL)
		return 0.0;
	Node_s* temp = malloc(sizeof(Node_s));
	temp = (*stk).top;
	(*stk).top = (*temp).next;
	(*stk).size--;
	double d = (*temp).data;
	free(temp);
	return d;
}

double peek(Stack* stk) {
	return stk->top->data;
	/*Node_s* temp = malloc(sizeof(Node_s));
	temp = stk->top;
	double d = temp->data;
	return d;
	*/
}

int search(Stack* stk, double data) {
	Node_s* temp = (*stk).top;
	int loc = 0;
	while ((*temp).next != NULL)
	{
		loc++;
		if ((*temp).data == data)
		{
			return loc;
		}
		temp = (*stk).top;
	}
	return 0;
}

void printStack(Stack* stk) {
	Node_s* temp = (*stk).top;
	while ((*temp).next != NULL)
	{
		printf("%f, ", (*temp).data);
		temp = (*stk).top;
	}
	printf("\b\n");
}