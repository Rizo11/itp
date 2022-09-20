//
// Created by MSI on 9/18/2022.
//
#ifndef LAB3_LAB3_H
#define LAB3_LAB3_H
#include "stdio.h"
#include "stdbool.h"
#include "string.h"
#include "ctype.h"
#include <time.h>
#define max(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a > _b ? _a : _b; })
#define min(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a < _b ? _a : _b; })

/* returns factorial of n */
int factorialOf(int n);

/* prints strong numbers from (int) n to (int) m */
void strongNumber();

/* prints elements [0, 999] of array without duplicates*/
void deleteDuplicate();

/* prints frequency of letters in vertical histogram */
void horizontalHistogram();

/* prints frequency of letters in vertical histogram */
void verticalHistogram();


/* checks if n is strong number or not */
bool isStrongNumber(int n);

/* calculates frequency of letters of given string */
void frequencyOf( char *str, int* occurance);

/* changes mixed string to only alphabetic string */
void alphabetic(char* str);


/* makes all charecters of string lowercase */
void lower(char* str);

/* output characters are sorted by frequencies in decreasing order.
 If characters have the same frequency, sort by ASCII
codes in increasing order.
*/
void sortedVerticalHistogram();

/* sorts letter=frequency pair by descending frequency order, if equal, in ascending alphabetic order*/
void sortFreqDesAlph(int *frequency, int* letters);

/* swaps the values of a and b*/
void swap(int* a, int* b);

/* finds 1-3 symbol long user password using brute force */
void findPassswordBF();

/* finds same string using brude force*/
void bfSearch(char userPassword[]);

/* I/O 2d 3x4 array using pointers */
void print2DArray();

/* read rows by column matrix */
void getMatrix(int* array, int rows ,int columns);

/* prints 1 - n numbers in pyramid */
void numberPyramid();

#endif //LAB3_LAB3_H
