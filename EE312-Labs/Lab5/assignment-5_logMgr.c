/*
Hirani, Altamshali
AH45675
EE312
Assignment 5
*/
#include "assignment-5_logMgr.h"
#include <stdlib.h>

/* Your log manager implementations here */
int add_to_log(log_manager* mgr, long timestamp) {
	Node_q* temp = malloc(sizeof(Node_q));
	temp = mgr->q->head;
	while (temp != NULL)
	{
		long dat = temp->data;
		temp = temp->next;
		if (timestamp - (dat) > 2)
		{
			dequeue(mgr->q);
		}
	}
	enqueue(mgr->q, timestamp);
	return mgr->q->size;
}

log_manager* createLogMgr(int delta) {
	log_manager* manager = malloc(sizeof(log_manager));
	manager->q = initialize_q();
	manager->window_duration = delta;
	return manager;
}