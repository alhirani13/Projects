/* Use the following stucts for your queue and node */
typedef struct Node_q {
  long data;
  struct Node_q* next;
}Node_q;

typedef struct Queue {
       int size;
       Node_q* head;
       Node_q* tail;
}Queue;



/*These functions should be implemented in assignment-5_queue.c */

/**
* Allocates memory for an empty Queue object using malloc, initializes it,
* and returns a pointer to the created object.
*/ 
Queue* initialize_q();

/**
* Adds a new entry to the queue. The new node should be dynamically created first
* using malloc.
*/ 
void enqueue(Queue* my_Q, long elt);

/**
* Removes the first added node from the queue, and returns its value
*/ 
long dequeue(Queue* my_Q);

/**
* Inserts an entry at the specified index. The new node should be dynamically
* created using malloc.
*/
void insert(Queue* my_Q, long elt, int index);

/**
* Prints the queue as a comma-separated list (check PDF for format)
*/
void printQueue(Queue* my_Q);
