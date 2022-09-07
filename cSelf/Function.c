#include "Function.h"
int Sum(int a, int b)
{
    return a + b;
}

int parent()
{
    printf("From parent\n");
    return 2;
}

int grandparent()
{
    printf("From grandparent\n");
    return 2;
}