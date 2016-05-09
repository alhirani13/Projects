/*
Hirani, Altamshali
AH45675
EE312
Assignment 5
*/
#include "assignment-5_queue.h" 
#include <stdlib.h> 
#include <stdio.h> 

/* Your queue implementations here */

Queue* initialize_q() {
	Queue* mainQueue = malloc(sizeof(Queue));
	(*mainQueue).size = 0;
	(*mainQueue).head = NULL;
	(*mainQueue).tail = NULL;
	return mainQueue;

}

void enqueue(Queue* my_Q, long elt) {
	Node_q* temp = malloc(sizeof(Node_q));
	(*temp).data = elt;
	temp->next = NULL;
	if ((*my_Q).head == NULL)
	{
		my_Q->head = temp;
		my_Q->tail = temp;
	}
	else if ((*my_Q).tail == my_Q->head)
	{
		(*(*my_Q).head).next = temp;
		my_Q->tail = temp;
	}
	else
	{
		(*(*my_Q).tail).next = temp;
		my_Q->tail = temp;
	}
	my_Q->size++;
}

long dequeue(Queue* my_Q) {
	if (my_Q->head == NULL)
		return 0;
	long l = my_Q->head->data;
	my_Q->head = my_Q->head->next;
	my_Q->size--;
	return l;
}

void insert(Queue* my_Q, long elt, int index) {
	if (index > my_Q->size)
	{
		return;
	}
	else if (index == my_Q->size)
	{
		enqueue(my_Q, elt);
		return;
	}
	else {
		Node_q* temp = my_Q->head;
		index--;
		while (index != 0)
		{
			temp = temp->next;
			index--;
		}
		Node_q* replace = malloc(sizeof(Node_q));
		replace->data = elt;
		replace->next = temp->next;
		temp->next = replace;
	}
}

void printQueue(Queue* my_Q) {
	Node_q* temp = my_Q->head;
	while (temp != NULL)
	{
		printf("%li, ", (temp->data));
		temp = temp->next;
	}
	printf("\b\n");
}
