/********************************************************************
Name:Al Hirani
UT EID:AH45675
Section:
EE312-Assignment 3

********************************************************************/
#include <stdio.h>
/********************************************************************
isGaussianPrime:

Inputs: 	(int a) and (int b) to create z = a + bi
int print: a flag indicating whether this function
should print to stdout or not.

Flow:		Take a Gaussian integer z as an argument, and
determine if it’s a Gaussian prime. If it is not a
Gaussian prime, factor it into Gaussian primes.
If print=1, write the result to stdout.

Output: 	Check the lab document for output format

Returns: This function should return 1 if the input is a Gaussian prime,
and 0 otherwise.

To do: 	Explained briefly within the function

*********************************************************************/
int isGaussianPrime(int a, int b, int print) {

	int prime = 0;
	int range = (a*a) + (b*b);
	if (range >= 6)
	{
		range /= 2;
	}
	
	for (int aa = -range; aa <= range; aa++)
	{
		for (int bb = -range; bb <= range; bb++)
		{
			if ((aa == 0 & bb == 1) || (aa == 1 & bb == 0) || (aa == 0 & bb == -1) || (aa == -1 & bb == 0))
			{
				bb++;
			}
			for (int cc = -range; cc <= range; cc++)
			{
				for (int dd = -range; dd <= range; dd++)
				{
					if ((cc == 0 & dd == 1) || (cc == 1 & dd == 0) || (cc == 0 & dd == -1) || (cc == -1 & dd == 0))
					{
						dd++;
					}
					if ((aa*cc - bb*dd) == a & (aa*dd + bb*cc) == b & !(aa==0 & bb==0) & !(cc==0 & dd==0))
					{
						if(print == 1)
							printf("%d + %di is not a Gaussian prime. Factorization = (%d + %di)(%d + %di)\n", a, b, aa, bb, cc, dd);
						return 0;
					}
				}
			}
		}
	}
	if(print == 1)
		printf("%d + %di is a Gaussian prime.\n", a, b); 
	return 1;

	/****************************************************************
	TO DO:

	You will need to write nested loops to use brute force in
	order to find if the input is a Gaussian prime or not. If not,
	you should print one of the factorization of the input (only if print=1).
	To find that the input is not a Gaussian prime, you need to find 2
	complex number where multiplication of these two complex
	numbers should be equal to your input (check the definition
	described in lab document for more detail about Gaussian primes).
	That is, for input z where z = (a + bi)(c + di), you need to
	iterate over a, b, c, and d values to prove that z is not a
	Gaussian prime number.

	SAMPLE OUTPUT:

	-1 + 3i is not a Gaussian prime. Factorization = (1+2i)(1+i)

	1 + 4i is a Gaussian prime.

	WARNING:

	Your output should be exactly in the form described in the lab
	document. Otherwise, you may lose points. Print only one
	factorization of the input if it is not a Gaussian Prime.
	****************************************************************/
}


/********************************************************************
PrintGaussianPrime:

Inputs: 	(int m) where m is magnitude

Flow:		Take a magnitude M and compute all Gaussian primes
a + bi such that 0 <= a <= M and 0<= b <= M.
Write the primes to stdout.

Output: 	Check the lab document for output format

To do: 	Explained briefly within the function

********************************************************************/
void PrintGaussianPrime(int m) {
	printf("Primes with real and imaginary parts having magnitude less than or equal to %d = {", m);
	for (int a = 0; a <= m; a++)
	{
		for (int b = 0; b <= m; b++)
		{
			if ((a == 0 & b == 1) || (a == 1 & b == 0) || (a == 0 & b == -1) || (a == -1 & b == 0))
			{
				b++;
			}
			if (isGaussianPrime(a, b, 0) == 1 & !(a == 0 & b ==0))
			{
				printf("%d + %di, ", a, b);
			}
		}
	}
	printf("\b\b}\n");
	

	/****************************************************************
	TO DO:

	You should iterate over 0..M for both a value and b values
	where z = a + bi. For each a, b values, you need to check if
	it is a Gaussian prime or not. If so, you should print the
	result. For better performance, you can use 2D array. You
	can iterate over cell indexes, and put 1 if it is a Gaussian
	prime, 0 if it is not. Then, you can print your 2D array as
	described in lab document.

	SAMPLE OUTPUT:

	Primes with real and imaginary parts having magnitude less than
	or equal to 4 = {0 + 3i, 1 + 1i, 1 + 2i, 1 + 4i, 2 + 1i, 2 + 3i,
	3 + 0i, 3 + 2i, 4 + 1i}

	WARNING:

	Your output should be exactly in the form described in the lab
	document. Otherwise, you may lose points. Pay attention to the
	order of sequence and the commas! You should not put a comma at
	the end of the list. You will need to find a way to handle it.
	Printing Gaussian primes in the positive-positive quadrant is
	enough for correct result.
	****************************************************************/
}

/********************************************************************
PlotGaussianPrime:

Inputs: 	(int m) where m is magnitude

Flow:		Take a magnitude M and draw a plot showing all the
Gaussian primes that are computed as in Part 2.
The plot should be in the form of text that you
print to stdout.

Output: 	Check the lab document for output format

To do: 	Explained briefly within the function

********************************************************************/
void PlotGaussianPrime(int m) {
	printf("  For M = %d;\n", m);
	
	for (int a = m; a >= 0; a--)
	{
		printf("  %d  ", a);
		for(int b = 0; b <= m; b++)
		{
			int temp = 0;
			if ((a == 0 & b == 1) || (a == 1 & b == 0) || (a == 0 & b == -1) || (a == -1 & b == 0) || (a == 0 & b == 0))
			{
				temp = 1;
			}
			if (isGaussianPrime(a, b, 0) == 1 & temp != 1) {
				printf("X");
			}
			else
				printf("0");
		}
		printf("\n");
	}
	printf("     ");
	for (int i = 0; i <= m; i++)
	{
		printf("%d", i);
	}
	printf("\n\n");
/****************************************************************	
	TO DO:

	You should do the same thing as you do for Mode 2. However,
	this time you will plot your result as described in previous
	section. If it is  a Gaussian prime, you should print X,
	otherwise 0 (zero). Your output should be exactly in the
	form described in lab document.

	SAMPLE OUTPUT:

	For M = 4;
	4 0X000
	3 X0X00
	2 0X0X0
	1 0XX0X
	0 000X0
	01234

	WARNING:

	For z = a + bi, y-axis represents b values, and x-axis represents
	a values. Your output should be exactly in the form described
	in the lab document. Otherwise, you may lose points. Plotting
	Gaussian primes in the positive-positive quadrant is enough for
	correct result.
	****************************************************************/
}
