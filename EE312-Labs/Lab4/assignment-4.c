/*
Hirani, Altamshali
AH45675
16220
EE 312 - Assignment 4
*/

#define FALSE 0
#define TRUE 1



/************************************************************/
/* This function is called by the 'match' function.
If the passed character is one of the base set (a,c,g, or t), it 
 returns 1, else it returns 0.
*/
/************************************************************/
int isBase( char c ) {
	if( (c == 'a') || (c =='c') || (c == 'g') || (c == 't') ){
		return 1;
	}

	return 0;
}



/************************************************************/
/************************************************************/
/* 
This is a recursive function that returns 1 if the string s matches 
 the ME string r. It returns 0 otherwise.
You need to implement the following logic in this function:

1. If length(r)=length(s)=0, they match: return 1.

2. If length(r)=length(s)=1, then r matches s only if either r is '.' 
 (which matches any character), or the same character as s.

3. If length(r) > 1, then r can match s in one of these cases:

   a) If r[0] is a base character that matches s[0], and r[1] is a 
    base character or a '.', then r and s match only if r[1..end] 
    matches s[1..end] (checked by a recursive call to match).

   b) If r[0] is a base character, and r[1] is '*', then r matches s 
    only if s is composed as follows:
        0 or more occurences of r[0], followed by a string that matches 
        r[2..end]. This can be checked by calling match recursively for 
        each possible suffix of s.

   c) If r[0] is '.' and r[1]!='*', then unless s is empty, r and s match 
    only if r[1..end] matches s[1..end] (checked by a recursive call to 
    match).
   
   d) If r[0] is '.' and r[1]='*', r and s match if any suffix of s 
    matches r[2:end].
*/

int match( char *s, char *r ) {
	if (s[0] == '\0' && r[0] == '\0') //s[0] == '\0' && r[0] == '\0') // strlen(s) == 0 && strlen(r) == 0
	{
		return 1;
	}
	else if ((s[1] == '\0' && r[1] == '\0') & (r[0] == '.' || r[0] == s[0]) )
	{
		return 1;
	}
	else if ((r[0] != '\0' && r[1] != '\0'))
	{
		
		if (isBase(r[0]) == 1 && r[0] == s[0] && (isBase(r[1]) == 1 || r[1] == '.'))
		{
			return match(s + 1, r + 1);
		}
		else if (isBase(r[0]) == 1 && r[1] == '*')
		{
			if (r[0] == s[0])
			{
				return match(s + 1, r);
			}
			else 
			{
				r += 2;
				return match(s, r);
			}
		}
		else if (r[0] == '.' && r[1] != '*' && s[0] != '\0')
		{		
			return match(s + 1, r + 1);
		}
		else if (r[0] == '.' && r[1] == '*') // Still need to complete
		{
			if (r[2] == '\0')
			{
				return 1;
			}
			else
			{
				int temp = 0;
				while (s[0] != '\0')
				{
					temp++;
					s++;
				}
				for (; temp > 0; temp--)
				{
					s--;
					if (r[2] == s[0] && match(s+1 , r+3) == 1)
					{
						return 1;
					}
				}
				/*while (r[2] != s[0])
				{
					if (s[0] == '\0')
					{
						return 0;
					}
					s++;
				}
				while (s[1] == r[2])
				{
					s++;
				}
				return match(s+1, r + 3);*/
			}
		}
		else if (r[0] == s[0])
		{
			return match(s + 1, r + 1);
		}
	}

	return 0;


	
}
/************************************************************/
/************************************************************/
