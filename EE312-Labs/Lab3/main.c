/********************************************************************

********************************************************************/
#include <stdio.h>

/********************************************************************
        You need to write these functions.
        Their declaretions are in "assignment-3.c file.
        * IMPORTANT *
        DO NOT CHANGE THE INTERFACE OF THESE FUNCTIONS!!!
********************************************************************/
void isGaussianPrime(int a, int b, int print);
void PrintGaussianPrime(int m);
void PlotGaussianPrime(int m);

/********************************************************************
        Declarations for test functions
********************************************************************/
void TestPart1();
void TestPart2();
void TestPart3();

/********************************************************************
        Main Program
********************************************************************/
int main(void) {
        TestPart1();
        TestPart2();
        TestPart3();
	  getchar();
	  return 0;
}

/********************************************************************
        Main test function for operation mode #1 (isGaussianPrime).
        You can create your own test cases by changing a and b values.
********************************************************************/
void TestPart1(){
		int print = 1;
        int a;
        int b;
        printf("***** Test part #1 is started ***** \n");
        /* TEST #1: (-1 + 4i) is a Gaussian prime*/
        a = -1;
        b = 4;
        isGaussianPrime(a, b, print);
        /* TEST #2: (2 + 5i) is a Gaussian prime*/
        a = 2;
        b = 5;
        isGaussianPrime(a, b, print);
        /* TEST #3: (1 + -3i) is NOT a Gaussian prime*/
        a = 1;
        b = -3;
        isGaussianPrime(a, b, print);
        /* TEST #3: (2 + 0i) is NOT a Gaussian prime*/
        a = 2;
        b = 0;
        isGaussianPrime(a, b, print);
        printf("***** Test part #1 is finished ***** \n\n");
}

/********************************************************************
        Main test function for operation mode #2 (PrintGaussianPrime).
        You can create your own test cases by changing a and b values.
********************************************************************/
void TestPart2(){
        int m;
        printf("***** Test part #2 is started ***** \n");
        /* TEST #1 */
        m = 4;
        PrintGaussianPrime(m);
        /* TEST #2 */
        m = 8;
        PrintGaussianPrime(m);
        printf("***** Test part #2 is finished ***** \n\n");
}

/********************************************************************
        Main test function for operation mode #3 (PlotGaussianPrime).
        You can create your own test cases by changing a and b values.
********************************************************************/
void TestPart3(){
        int m;
        printf("***** Test part #3 is started ***** \n");
        /* TEST #1 */
        m = 4;
        PlotGaussianPrime(m);
        /* TEST #2 */
        m = 3;
        PlotGaussianPrime(m);
        printf("***** Test part #3 is finished ***** \n\n");
}
