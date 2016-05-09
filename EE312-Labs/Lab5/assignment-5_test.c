/*
Hirani, Altamshali
AH45675
EE312
Assignment 5
*/
#include "assignment-5_calculator.h"
#include "assignment-5_logMgr.h"
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

void logMgr_tests();
void calculator_tests();

int main() {
	calculator_tests();
	logMgr_tests();
	
	return 0;
}

void calculator_tests() {
    char expr1[][MAX_EXPR_LENGTH] = {"8", "4", "-3", "*", "-", "2.5", "14", "/", "+"};
    char expr2[][MAX_EXPR_LENGTH] = {"2", "2", "+", "3", "*"};
	char expr3[][MAX_EXPR_LENGTH] = {"4.876555", "7", "3", "+", "*"};
	
	printf("expr1 = %lf \n", eval( expr1, sizeof(expr1)/sizeof(expr1[0]) ) );
		assert(eval(expr1, sizeof(expr1) / sizeof(expr1[0])) == -14.4);
	
	printf("expr2 = %lf \n", eval( expr2, sizeof(expr2)/sizeof(expr2[0]) ) );
    	assert( eval( expr2, sizeof(expr2)/sizeof(expr2[0]) ) == 12 );

	printf("expr3 = %lf \n", eval( expr3, sizeof(expr3)/sizeof(expr3[0]) ) );
    	assert( eval( expr3, sizeof(expr3)/sizeof(expr3[0]) ) == 48.76555 );
    
	printf("All tests passed successfully :-) \n");
}


#define WINDOW_SIZE 2

void logMgr_tests() {
    log_manager *log = createLogMgr( WINDOW_SIZE );
    assert( add_to_log( log, 1 ) == 1 );
    assert( add_to_log( log, 2 ) == 2 );
    assert( add_to_log( log, 3 ) == 3 );
    assert( add_to_log( log, 4 ) == 3 );
    assert( add_to_log( log, 4 ) == 4 );
    assert( add_to_log( log, 10 ) == 1 );
    
	printf("All tests passed successfully :-) \n");
	getchar();
}