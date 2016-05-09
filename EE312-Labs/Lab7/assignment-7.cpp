#define CRTDBG_MAP_ALLOC
#include <stdlib.h>
//#include <crtdbg.h>
#include "poly.h"
#include <stdio.h>
#include <string.h>
#include <math.h>

#define SIZE 100

Poly* p;

// Main function of your program - called by main
void runProgram() {
	int quit = 0;
	int numberchoice, numofpolys, poly1, poly2;
	float evalpoint;
	FILE *fp;
	char str[SIZE], polystring[SIZE];						//string for each polynomial
	char yesorno[2] = "N";
	char yesornofile[2] = "N";
	while (quit == 0)
	{
		/*if (p != NULL)
		{
			free(p);
		}
		*/
		if (yesorno[0] == 'N')
		{
			printf("Enter the file you would like to work with: ");
			scanf("%s", str);
		}
		p = (Poly*)malloc(sizeof(Poly));
		fp = fopen(str, "r");
		numofpolys = getc(fp) - 48;
		getc(fp);
		for (int x = 0; x < numofpolys; x++)
		{
			fgets(polystring, sizeof(polystring), fp);
			strtok(polystring, "\n");
			p[x] = Poly(polystring);
			//p[x] = *(new Poly(polystring));
		}
		//_CrtDumpMemoryLeaks();
		//fclose(fp);
		printf("The polynomials available for operation are:\n");
		for (int x = 0; x < numofpolys; x++)
		{
			printf("%d. ", x + 1);
			Poly Meiru = p[x];
			Meiru.print();
			printf("\n");
		}
		printf("What operation would you like to perform?\n1. ADD polynomials\n2. MULTIPLY polynomials\n3. EVALUATE polynomial\n\nEnter choice #: ");
		scanf("%d", &numberchoice);
		if (numberchoice == 1)
		{
			printf("Enter the 2 polynomials that you would like to work with: ");
			scanf("%d,%d", &poly1,&poly2);
			Poly Meiru = p[poly1-1];
			Poly Al = *(Meiru.add(p[poly2-1]));
			printf("The symbolic sum of the 2 polynomials is:\n");
			Al.print();
			printf("\n");
		}
		else if (numberchoice == 2)
		{
			printf("Enter the 2 polynomials that you would like to work with: ");
			scanf("%d,%d", &poly1, &poly2);
			printf("The symbolic product of the 2 polynomials is:\n");
			Poly Meiru = p[poly1 - 1];
			Poly Al = *(Meiru.multiply(p[poly2 - 1]));
			Al.print();
			printf("\n");
		}
		else if(numberchoice == 3)
		{
			printf("Enter the polynomial that you would like to work with: ");
			scanf("%d", &poly1);
			printf("Enter the evalulation point: ");
			scanf("%f", &evalpoint);
			printf("Value of that polynomial at %.1lf is: \n", evalpoint);
			Poly Meiru = p[poly1-1];
			printf("%.1lf\n", Meiru.eval(evalpoint));
		}
		printf("Do you want to perform additional operations on the existing file(Y/N)? ");
		scanf("%s", &yesorno);
		if (yesorno[0] == 'N')
		{
			printf("Do you want to work with another file (Y/N)? ");
			scanf("%s", &yesornofile);
			if (yesornofile[0] == 'N')
			{
				quit = 1;
			}
		}	
	}
		//delete[] p;// delete
 }


Poly::Poly(char* str) {
	base = (Node*)malloc(sizeof(Node));
	base = NULL;
	int temp = 0;
	double coeff = 0; double exp = 0;
	bool var = false;
	char c[100];
	c[0] = NULL;
	while (str[temp] != NULL)
	{
		if ((str[temp] >= '0' && str[temp] <= '9') || str[temp] == '-' || str[temp] == '.')
		{
			if (str[temp] == '-')
			{
				strncat(c, &(str[temp]), 1);
				temp += 1;
			}
			while (str[temp] != ' ' && str[temp] != 'x' && str[temp] != NULL)
			{
				strncat(c, &(str[temp]), 1);	
				temp++;
			}
			if (var)
				exp = atof(c);
			else
				coeff = atof(c);
			c[0] = NULL;
		}
		if (str[temp] == 'x' || str[temp] == '^')
		{
			temp++;
			var = true;
		}
		if (str[temp] == ' ' || str[temp] == NULL)
		{	
			if (var == true && exp == 0)
				exp = 1;
			newNode = (Node*)malloc(sizeof(Node));
			newNode->coeff = coeff;
			newNode->degree = (int)exp;
			newNode->next = NULL;
			Node* fred = this->base;
			if (fred == NULL)
				this->base = newNode;
			else
			{
				while (fred->next != NULL)
					fred = fred->next;
				fred->next = newNode;
			}
			coeff = 0;
			exp = 0;
			var = false;
			c[0] = 0;
			if (str[temp] == ' ')
				temp++;
		}
		if (str[temp] == '+')
			temp += 2;
	}
	
}

Poly::~Poly() {
	/*printf("hello");
	if(base != NULL)
		free(base);
	if (temp != NULL)
		free(temp);
	if (newNode != NULL)
		free(newNode);*/
//	if (multList[0] != NULL)
	//	free(multList);
}

Poly* Poly::add(Poly& otherPoly){
	temp = (Poly*)malloc(sizeof(Poly));
	temp->base = NULL;
	Node* node1 = this->base;
	Node* node2 = otherPoly.base;
	
	while (node1 != NULL || node2 != NULL)
	{
		if (node2 == NULL)
		{
			while (node1 != NULL)
			{
				if (temp->base == NULL)
				{
					temp->base = (Node*)malloc(sizeof(Node));
					temp->base->coeff = node1->coeff;
					temp->base->degree = node1->degree;
					temp->base->next = NULL;
					//temp->base = node1;
					node1 = node1->next;
					newNode = temp->base;
				}
				else {

					//newNode->next = node1;
					newNode->next = (Node*)malloc(sizeof(Node));
					newNode->next->coeff = node1->coeff;
					newNode->next->degree = node1->degree;
					newNode->next->next = NULL;
					node1 = node1->next;
					newNode = newNode->next;
				}

			}
		}
		else if (node1 == NULL)
		{
			while (node2 != NULL)
			{
				if (temp->base == NULL)
				{
					temp->base = (Node*)malloc(sizeof(Node));
					temp->base->coeff = node2->coeff;
					temp->base->degree = node2->degree;
					temp->base->next = NULL;
					//temp->base = node2;
					node2 = node2->next;
					newNode = temp->base;
				}
				else {
					//newNode->next = node2;
					newNode->next = (Node*)malloc(sizeof(Node));
					newNode->next->coeff = node2->coeff;
					newNode->next->degree = node2->degree;
					newNode->next->next = NULL;
					node2 = node2->next;
					newNode = newNode->next;
				}
			}
		}
		else
		{
			
			if (temp->base == NULL)
			{
				temp->base = (Node*)malloc(sizeof(Node));
				if (node1->degree > node2->degree)
				{
					//temp->base = node1;
					temp->base->coeff = node1->coeff;
					temp->base->degree = node1->degree;
					temp->base->next = NULL;
					node1 = node1->next;
				}
				else if (node2->degree > node1->degree)
				{
					//temp->base = node2;
					temp->base->coeff = node2->coeff;
					temp->base->degree = node2->degree;
					temp->base->next = NULL;
					node2 = node2->next;
				}
				else
				{
					//newNode = (Node*)malloc(sizeof(Node));
					temp->base->coeff = node1->coeff + node2->coeff;
					temp->base->degree = node1->degree;
					temp->base->next = NULL;
					//temp->base = newNode;
					node1 = node1->next;
					node2 = node2->next;
				}
				newNode = temp->base;
			}
			else {
				newNode->next = (Node*)malloc(sizeof(Node));
				if (node1->degree > node2->degree)
				{
					//newNode->next = node1;
					newNode->next->coeff = node1->coeff;
					newNode->next->degree = node1->degree;
					newNode->next->next = NULL;
					node1 = node1->next;
					//newNode->next->next = NULL;
				}
				else if (node2->degree > node1->degree)
				{
					//newNode->next = node2;
					newNode->next->coeff = node2->coeff;
					newNode->next->degree = node2->degree;
					newNode->next->next = NULL;
					node2 = node2->next;
					//newNode->next->next = NULL;
				}
				else
				{
					
					newNode->next->coeff = node1->coeff + node2->coeff;
					newNode->next->degree = node1->degree;
					newNode->next->next = NULL;
					node1 = node1->next;
					node2 = node2->next;
				}
				newNode = newNode->next;
			}
		}
	}
	//free(node1); free(node2);
	//free(this->base); free(otherPoly.base);
	return temp;
}

Poly* Poly::multiply(Poly& otherPoly) {
	multList = (Poly*)malloc(sizeof(Poly));
	Node* node1 = this->base;
	Node* node2;
	node2 = otherPoly.base;
	Node* newNode;
	int index = 0;
	if (node1->coeff == 0 && node1->next == NULL)
		return this;
	if (node2->coeff == 0 && node2->next == NULL)
		return &otherPoly;
	while (node1 != NULL)
	{
		node2 = otherPoly.base;
		p = new Poly(" ");
		p->base = NULL;
		while (node2 != NULL)
		{
			if (p->base == NULL)
			{
				newNode = (Node*)malloc(sizeof(Node));
				newNode->coeff = node1->coeff * node2->coeff;
				newNode->degree = node1->degree + node2->degree;
				newNode->next = NULL;
				p->base = newNode;
			}
			else
			{
				newNode->next = (Node*)malloc(sizeof(Node));
				newNode->next->coeff = node1->coeff * node2->coeff;
				newNode->next->degree = node1->degree + node2->degree;
				newNode->next->next = NULL;
				newNode = newNode->next;
			}
			node2 = node2->next;
		}
		multList[index] = *p;
		index++;
		node1 = node1->next;
	}
	for (int i = 1; i < index; i++)
	{
		multList[0] = *(multList[0].add(multList[i]));
	}
	Poly* finalMult = &multList[0];
	return finalMult;
}

double Poly::eval(double x) {
	double sum = 0;
	Node* node = this->base;
	while (node != NULL)
	{
		sum += node->coeff * (pow((double)x, (double)node->degree));
		//printf("%.1lf ", node->coeff);
		node = node->next;
		//printf("%.1lf\n", sum);
	}
	return sum;
}

void Poly::print() {
	Node* temp = this->base;
	while (temp != NULL)
	{
		if (temp->next == NULL && temp == this->base && temp->coeff == 0)
		{
			printf("0.0");
			break;
		}
		else if (temp->next == NULL)
		{
			if (temp->coeff == 0)
			{
				
			}	
			else if (temp->degree == 1)
				printf("%.1lfx", temp->coeff);
			else if (temp->degree == 0)
				printf("%.1lf", temp->coeff);
			else
				printf("%.1lfx^%d", temp->coeff, temp->degree);
		}
		else
		{
			if (temp->coeff == 0)
			{

			}
			else if (temp->next->coeff == 0 && temp->next->next == NULL)
			{
				if (temp->degree == 1)
					printf("%.1lfx", temp->coeff);
				else if (temp->degree == 0)
					printf("%.1lf", temp->coeff);
				else
					printf("%.1lfx^%d", temp->coeff, temp->degree);
			}
			else if (temp->degree == 1)
				printf("%.1lfx + ", temp->coeff);
			else if (temp->degree == 0)
				printf("%.1lf + ", temp->coeff);
			else
				printf("%.1lfx^%d + ", temp->coeff, temp->degree);
		}
		temp = temp->next;
	}
	//free(temp);
}

bool Poly::equals(Poly& otherPoly) {
	Node* node1 = this->base;
	Node* node2 = otherPoly.base;
	while (node1 != NULL && node2 != NULL)
	{
		if (node1->coeff == node2->coeff && node1->degree == node2->degree)
		{
			node1 = node1->next;
			node2 = node2->next;
		}
		else
			return false;
	}
	if (node1 == NULL && node2 == NULL)
		return true;
	return false;
}

Poly* Poly::operator+(Poly& otherpoly)
{
	return this->add(otherpoly);
}

Poly* Poly::operator*(Poly& otherpoly)
{
	return this->multiply(otherpoly);
}

double Poly::operator()(double x)
{
	return this->eval(x);
}

bool Poly::operator==(Poly& otherpoly)
{
	return this->equals(otherpoly);
}
