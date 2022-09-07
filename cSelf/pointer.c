#include <stdio.h>

int* p;

void fun()
{
    int local;
    printf("%d\n", local);
    p = &local;
}

struct myStruct
{
    int a;
    int b
};

typedef struct
{
    int a;
    int b;
} myNewStruct;



int main()
{
    fun();
    *p = 777;
    printf("%d\n", *p);
    // dangling pointer

    
    myNewStruct mnstrct;
    mnstrct.a = 1;
    mnstrct.b = 2;
    printf("%d %d\n", mnstrct.a, mnstrct.b);
}