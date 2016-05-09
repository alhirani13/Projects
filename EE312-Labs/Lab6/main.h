#define MAX_URL_LENGTH 100
struct tnode { /* the tree node: */
    char *key; /* points to the url string */
    int count; /* number of occurrences */
    struct tnode *left; /* left child */
    struct tnode *right; /* right child */
};

void populate_tree(char *url_file, struct tnode**);
struct tnode* add_to_tree(struct tnode* , char* );
int lookup(struct tnode* , char* ); // Give the frequency of the url
void test_print(struct tnode* , int, char [][MAX_URL_LENGTH], int *);
int treeprint(int size, struct tnode*, char [][MAX_URL_LENGTH], int *);

char Expected_URLs[10][MAX_URL_LENGTH]= {"amazon.com","apple.com", "boardingarea.com", "dealsofamerica.com", "facebook.com", "frys.com", "gmail.com", "google.com", "homeroomedu.com", "ideone.com"};
char Expected_URLs_small[7][MAX_URL_LENGTH]= {"amazon.com", "dealsofamerica.com","frys.com","google.com", "homeroomedu.com", "ideone.com", "mail.yahoo.com"};

int Expected_Url_Freq[10] = {7225, 597, 7112, 3636, 7029, 4361, 7030, 753, 6474, 3672};
int Expected_Url_Freq_small[7] = {5, 12, 15, 29, 3, 19, 20};
 