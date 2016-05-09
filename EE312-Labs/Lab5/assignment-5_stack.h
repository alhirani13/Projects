/* Use the following stucts for your stack and node */
typedef struct Node_s {
  double data;
  struct Node_s* next;
}Node_s;

typedef struct Stack {
  int size;
  Node_s* top;
}Stack;


/*These functions should be implemented in assignment-5_stack.c */

/**
* Allocates memory for an empty Stack object using malloc, initializes it,
* and returns a pointer to the created object.
*/ 
Stack* initialize_stk();

/**
* Returns the size of the stack
*/
int size(Stack* stk);

/**
* Adds a new node to the stack. The new node should be dynamically created first
* using malloc.
*/ 
void push(Stack* stk, double data);

/**
* Removes the last added node from the stack, and returns its value
*/ 
double pop(Stack* stk);

/**
* Returns the value of the last added node
*/
double peek(Stack* stk);

/**
* Returns the position where data was found in the array (integer within [1 ... size]), 
* or 0 if not found.
*/
int search(Stack* stk, double data);

/**
* Prints the stack as a comma-separated list (check PDF for format)
*/
void printStack(Stack* stk);