//
// Created by MSI on 9/18/2022.
//
#ifndef LAB3_LAB3_H
#define LAB3_LAB3_H
#include "stdio.h"
#include "stdbool.h"
#include "string.h"
#include "ctype.h"
#define max(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a > _b ? _a : _b; })
#define min(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a < _b ? _a : _b; })

/* prints strong numbers from (int) n to (int) m */
void strongNumber();

/* prints elements [0, 999] of array without duplicates*/
void deleteDuplicate();

/* prints frequency of letters in vertical histogram */
void horizontalHistogram();

/* prints frequency of letters in vertical histogram */
void verticalHistogram();

/* returns factorial of n */
int factorialOf(int n);

/* checks if n is strong number or not */
bool isStrongNumber(int n);

/* calculates frequency of letters of given string */
void frequencyOf( char *str, int* occurance);

/* changes mixed string to only alphabetic string */
void alphabetic(char* str);


/* makes all charecters of string lowercase */
void lower(char* str);

#endif //LAB3_LAB3_H
