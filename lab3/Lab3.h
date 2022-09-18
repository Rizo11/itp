//
// Created by MSI on 9/18/2022.
//
#ifndef LAB3_LAB3_H
#define LAB3_LAB3_H
#include "stdio.h"
#include "stdbool.h"
#include "string.h"
#define max(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a > _b ? _a : _b; })
#define min(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a < _b ? _a : _b; })

void strongNumber();

void deleteDuplicate();

#endif //LAB3_LAB3_H
