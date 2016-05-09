#include "assignment-5_queue.h"

typedef struct log_manager {
    int window_duration;
    struct Queue *q;
}log_manager;


/*Implement these functions in assignment-5_logMgr.c */
int add_to_log(log_manager*, long);
log_manager* createLogMgr(int);

