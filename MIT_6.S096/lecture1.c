#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
// Two random functions
int foo(int i)
{
    printf("Foo is called: %d\n", i);
    return 0;
}
int bar(int i)
{
    printf("Bar is called: %d\n", i);
    return 0;
}

// define a function pointer type
typedef int (* func)(int);

int binary_search(int *data, int searchedValue, size_t size)
{
    size_t lo = 0, hi = size-1;
    size_t steps = 0;

    while (lo <= hi)
    {
        steps++;
        size_t mid = (lo + hi)/2;

        if (data[mid] < searchedValue)
        {
            lo = mid+1;
        }
        else if (data[mid] > searchedValue)
        {
            hi = mid;
        }
        else
        {
            // printf("steps: %zu\n", steps);
            return steps;
        }
        
    }

    // printf("steps: %zu\n", steps);
    return steps;
    
}

int binary_search_mit(int*data, int value, size_t N)
{
    size_t lo = 0, hi = N- 1, steps = 0;
    while( lo < hi )
    {
        steps++;
        size_t mid = lo + (hi - lo)/2;
        // printf("s: %d lo: %d hi: %d mid: %d\n", steps, lo, hi, mid);
        if( data[mid] < value )
        {
            lo = mid+1;
        }
        else
        {
            hi = mid;
        }
    }
    // printf("steps for MIT: %zu\n", steps);
    return (hi == lo && data[lo] == value )? steps : -1;
}

int main()
{
    // //arrya of funcions with function pointer + typedef
    // func arOfFunctions[] = {foo, bar};

    // //arrya of funcions with function pointer
    // int(*fPointer[])(int) = {foo, bar};
    // // fPointer[0](99);
    // for (size_t i = 0; i < 2; i++)
    // {
    //     arOfFunctions[i](i);
    //     (*fPointer[i])(i); //same as fPointer[i](i);
    // }

    size_t array_size = 16;
    int a[] = {-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    int b[100] = {0};
    // int searchedValue = 0;
    // printf("Enter value to seach: ");
    // scanf("%d", &searchedValue);
    size_t mit_step = 0, my_step = 0;
    for (size_t i = 0; i < array_size; i++)
    {
        my_step += binary_search(a, a[i], array_size);
        mit_step += binary_search_mit(a, a[i], array_size);
    }
    printf("MIT = %zu\nMy = %zu\nMIT faster = %f\n", mit_step, my_step, 100*my_step/mit_step - 100.0);
    return 0;
}