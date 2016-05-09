/*Hirani, Altamshali
 *AH45675
 *16220
 *EE312 - Assignment 2
*/
#include <stdio.h>;
#include "assignment-2.h";
#include <math.h>;
#include <string.h>;
#include <stdlib.h>;

#define PREVIOUS "previous";
#define SAME "same";
#define NEXT "next";
#define USTOEU .74;
#define EUTOUS 1.36;
#define KGTOLBS 2.20462;
#define LBSTOKG .45359237;
#define KMTOMI .06213712;
#define MITOKM 1.60934;
double kmToMi(double km)
{
	return km * MITOKM;
}
double miToKm(double mi)
{
	return mi / KMTOMI;
}
int main(void)
{
	int true = 1; 
	while (true) {
		printf("Conversion Menu\n	1. Convert a given Austin time to Irish time\n	2. Convert a given Irish time to Austin time\n	3. Convert a given USD value to EUR\n	4. Convert a given EUR value to USD value\n	5. Convert a given Fahrenheit temperature to Celsius\n	6. Convert a given Celsius temperature to Fahrenheit\n	7. Convert a given weight in kg to pounds, ounces\n	8. Convert a given weight in pounds, ounces to kg\n	9. Convert a given distance in km to miles\n	10. Convert a given distance in miles to km\n	11. Stop doing conversions and quit the program\n");
		printf("Enter a number from the menu (1-11) to select a specific conversion to perform or to quit:  ");
		int convMenuItem;
		int a, b, c;
		int x, y, z;
		double d;
		scanf("%d,", &convMenuItem);
		switch (convMenuItem)
		{
			case 1 :printf("Enter an Austin time to be converted, expressed in hours and minutes:  ");
					scanf("%d %d", &a, &b);
					AuTimeToIr(a, b, &x, &y, &z);
					char s[9] = SAME;
					if (z == 1)
						strcpy(s, "next");
					s[4] = 0;
					printf("The time in Ireland equivalent to %d:%02d in Austin is %d:%02d of the %s day.\n", a, b, x, y, s);
					break;

			case 2 :printf("Enter an Irish time to be converted, expressed in hours and minutes:  "); 
					scanf("%d %d", &a, &b);
					IrTimeToAu(a, b, &x, &y, &z);
					strcpy(s, "same");
					s[4] = 0;
					if (z == -1) {
						strcpy(s, "previous");
						s[8] = 0;
					}
					printf("The time in Austin equivalent to %d:%02d in Ireland is %d:%02d of the %s day.\n", a, b, x, y, s);
					break;

			case 3 :printf("Enter a US Dollar amount to be converted, expressed in dollars and cents:  ");
					scanf("%d %d", &a, &b);
					printf("%lf Euros is equivalent to $%d.%d in US Dollars.\n", USDToEUR(a,b), a, b);
					break;

			case 4 :printf("Enter a Euro amount to be converted:  ");
					scanf("%lf", &d);
					EURToUSD(d, &a, &b);
					printf("$%d.%d in US Dollars is equivalent to %lf Euros.\n", a, b, d);
					break;

			case 5 :printf("Enter an Fahrenheit temperature to be converted:  ");
					scanf("%d", &a);
					printf("%lf Celsius is equivalent to %d in Fahrenheit.\n", fahrenheitToCelsius(a), a);
					break;

			case 6 :printf("Enter an Celsius temperature to be converted:  ");
					scanf("%lf", &d);
					printf("%d Fahrenheit is equivalent to %lf in Celsius.\n", celsiusToFahrenheit(d), d);
					break;

			case 7 :printf("Enter a weight measurement to be converted, expressed in kilograms:  ");
					scanf("%lf", &d);
					kgToLbOz(d, &a, &b);
					printf("%d lb %d oz is equivalent to %lf kg.\n", a, b, d);
					break;

			case 8 :printf("Enter a weight measurement to be converted, expressed in pounds and ounces:  ");
					scanf("%d %d", &a, &b);
					printf("%lf kg is equivalent to %d lb %d oz.\n", lbOzToKg(a, b), a, b);
					break;

			case 9 :printf("Enter a distance measurement to be converted, expressed in kilometers:  ");
					scanf("%lf", &d);
					printf("%lf mi is equivalent to %lf km.\n", kmToMi(d), d);
					break;

			case 10:printf("Enter a distance measurement to be converted, expressed in miles:  ");
					scanf("%lf", &d);
					printf("%lf km is equivalent to %lf mi.\n", miToKm(d), d);
					break;

			case 11: printf("Good Bye"); true = 0; break;
			default: printf("Invalid Entry");
		}
	}
	return 0;
}
void AuTimeToIr(int hour, int minute, int* IrHour, int* IrMinute, int* IrDay)
{
	*IrHour = hour + 6;
	if (*IrHour >= 24)
	{
		*IrDay = 1;
		*IrHour = *IrHour % 24;
	}
	else
	{
		*IrDay = 0;
	}
	*IrMinute = minute;
}
void  IrTimeToAu(int hour, int minute, int* AuHour, int* AuMinute, int* AuDay)
{
	*AuHour = hour - 6;
	if (*AuHour < 0)
	{
		*AuDay = -1;
		*AuHour = 24 - (*AuHour * -1);
	}
	else
	{
		*AuDay = 0;
	}
	*AuMinute = minute;
}
double USDToEUR(int dollars, int cents)
{
	return ((double)dollars + (double)cents/100) * .74;
}
void EURToUSD(double euros, int* dollars, int* cents)
{
	euros = euros * EUTOUS;
	*dollars = (int)(truncl(euros));
	*cents = (lroundl(euros * 100) % 100);
}
double fahrenheitToCelsius(int fahrenheit)
{
	return (5.0/9.0) * (double)(fahrenheit-32);
}
int celsiusToFahrenheit(double celsius)
{
	return (int)lroundl(((9.0/5.0)*celsius + 32));
}
void kgToLbOz(double kg, int* lb, int* oz)
{
	kg = kg*KGTOLBS;
	*lb = (int)(kg);
	*oz = lroundl((kg - *lb) * 16);
}
double lbOzToKg(int lb, int oz)
{
	return (double)(lb + ((double)oz/16)) * LBSTOKG;
}