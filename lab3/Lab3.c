//
// Created by MSI on 9/18/2022.
//

#include "Lab3.h"


/* returns factorial of n */
int factorialOf(int n) {
    int result = 1;
    if(n >= 0) {
        for (int i = n; i > 0; i--) {
            result *= i;
        }
    } else return -1;
}


/* checks if n is strong number or not */
bool isStrongNumber(int n) {
    int sumOfDigits = 0, original = n;

    while (n) {
        sumOfDigits += factorialOf(n%10);
        n = n/10;
    }

    return sumOfDigits == original ? true : false;
}


/* print strong numbers from (int) n to (int) m */
void strongNumber() {
    int n, m;
    printf("Starting range  of number: ");
    scanf("%d", &n);
    printf("Ending range  of number: ");
    scanf("%d", &m);

    n = min(n, m);
    m = max(n, m);
    if(m <= 0 && n <= 0) {
        printf("0 strong numbers\n");
        return;
    }
    for (int i = n; i <= m; ++i) {
        if(isStrongNumber(i)) {
            printf("%d is strong number\n", i);
        }
    }

}
/* prints elements [0, 999] of array without duplicates*/
void deleteDuplicate() {
    int size = 0;

    printf("Size of array: ");
    scanf("%d", &size);

    int array[1000] = {[0 ... 999] = 0};
//    int array[1000] = {0};
//    int array[1000];
//    memset(array, 0, size);
    for (int i = 0; i < size; ++i) {
        int input = 0;
        scanf("%d", &input);
        if(array[input] == 0) {
            printf("%d ", input);
            array[input] = 1;
        }
    }
}
