/*
AL HIRANI
AH45675
16220
EE 312- Assignment 8
*/
#include "SquareMatrix.h"
#include <stdio.h>
#include <stdlib.h>
using namespace std;

int main()
{
	/* 
		Add code here to test the functionality of your
		classes.
	*/
	int d;
	MathMatrix* a = new MathMatrix(3,2,4);
	SquareMatrix* c = new SquareMatrix(3,2);
	printf("rows %d cols %d\n", a->numRows(), a->numCols());
	printf("%d value\n", a->getVal(1, 1));
	*a = a->add(*a);
	MathMatrix b = a->scale(3);
	printf("new value after adding %d\n",a->getVal(1, 1));
	printf("scaled mat value %d\n", b.getVal(1, 1));
	c->changeElement(0, 0, 5);
	c->changeElement(1, 2, 1);
	printf("square matrix \n%d%d%d\n%d%d%d\n%d%d%d\n", c->getVal(0, 0), c->getVal(0,1), c->getVal(0, 2), c->getVal(1, 0), c->getVal(1, 1), c->getVal(1, 2), c->getVal(2, 0), c->getVal(2, 1), c->getVal(2, 2));
	printf("%d\n", c->getDeterminant());
	scanf("%d", &d);
	return 0;
}