/*
Hirani, Altamshali
AH45675
16220
EE 312 - Assignment 6
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_URL_LENGTH 100

 struct tnode { /* the tree node: */
   char *key; /* points to the url string */
   int count; /* number of occurrences */
   struct tnode *left; /* left child */
   struct tnode *right; /* right child */
 };


 /* add_to_tree:  add a node with url, at or below p 
  * p: pointer to the root of the (sub)tree where you must add a value
  * url: the URL to be added
  */
 struct tnode* add_to_tree(struct tnode* p, char* url)
 {
	 if (p == NULL)
	 { 
		struct tnode *newNode = malloc(sizeof(struct tnode));
		newNode->count = 1;
		newNode->key = malloc(sizeof(char[MAX_URL_LENGTH]));
		strcpy(newNode->key, url);
		newNode->left = NULL; newNode->right = NULL;
		return newNode;
	 }
	 else
	 {
		 int temp = strcmp(p->key, url);
		 if (temp == 0)
		 {
			 p->count++;
			 //printf("done!");
		 }
		 else if (temp > 0)
		 {
			 if (p->left == NULL)
			 {
				 p->left = add_to_tree(p->left, url);
			 }
			 else
			 {
				 add_to_tree(p->left, url);
			 }
		 }
		 else
		 {
			 if (p->right == NULL)
			 {
				 p->right = add_to_tree(p->right, url);
			 }
			 else
			 {
				 add_to_tree(p->right, url);
			 }
		 }
		 return p;
	 }
 }


 /* populate_tree: repeatedly call add_to_tree on urls from a file 
  * url_file: name of file to read URLs from
  * R: pointer to the root pointer of the tree
  */
 void populate_tree(char *url_file, struct tnode** R)
 {

	 //int counter = 0; 
	 FILE *fp = fopen(url_file, "r");
	 char c[MAX_URL_LENGTH + 1];
	 while (!feof(fp))
	 {
		 fgets(c, sizeof(c), fp);
		 strtok(c, "\n");
		 if (strcmp(c, "") != 0)
		 {
			 add_to_tree(*R, c);
			 for (int a = 0; a < MAX_URL_LENGTH + 1; a++)
			 {
				 c[a] = NULL;
			 }
		 }
		 else
		 {
			 fclose(fp);
			 return;
		 }
	 }
	 fclose(fp);

 }





 /* lookup:  look up a url in the tree rooted at p, return the frequency of that url
  * p: pointer to the root of the (sub)tree
  * url: url to look for
  */
 int lookup(struct tnode* p, char* url) // Return the frequency of the url
 {
	 if (p == NULL)
	 {
		 return 0;
	 }
	 int temp = strcmp(p->key, url);
	 if (temp == 0)
	 {
		 return p->count;
	 }
	 else if (temp > 0)
	 {
		 return lookup(p->left, url);
	 }
	 return lookup(p->right, url);

 }


 /* treeprint:  in-order print of tree p
  * Set the urls in the passed in array in alphabetical fashion. Also set their respective frequencies in a second int array.
  * Return the number of elements set
  * size: how many elements you have currently inserted into the array(s)
  * p: pointer to the root of the (sub)tree
  * URL_array: array to store URLs in
  * n: integer array to store frequencies in
  */ 
int treeprint(int size, struct tnode* p, char URL_array[][MAX_URL_LENGTH], int *n)
{
	//if (p == NULL)
		//return 0;
	if(p->left != NULL)
		size = treeprint(size, p->left, URL_array, n);
	if (size < 10)
	{
		strcpy(URL_array[size], p->key);//	*URL_array[size] = p->key;
		n[size] = p->count;
	}
	else
	{
		return 10;
	}
	//printf("%s %d\n", URL_array[size], n[size]);
	size++;
	if(p->right != NULL)
		size = treeprint(size, p->right, URL_array, n);
	//printf("%d %s\n", size, p->key);
	return size;
	

}

