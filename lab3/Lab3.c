//
// Created by MSI on 9/18/2022.
//

#include "Lab3.h"


int factorialOf(int n) {
    int result = 1;
    if(n >= 0) {
        for (int i = n; i > 0; i--) {
            result *= i;
        }
    } else return -1;
}


bool isStrongNumber(int n) {
    int sumOfDigits = 0, original = n;

    while (n) {
        sumOfDigits += factorialOf(n%10);
        n = n/10;
    }

    return sumOfDigits == original ? true : false;
}


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


void frequencyOf( char *str, int* occurance) {
    char* letter = str;
    while ((*letter) != '\0') {
        if(*letter-97 >= 0 && *letter-97 <= 26)
            occurance[*letter-97] += 1;
        letter++;
    }
}


void alphabetic(char* str)
{
    char onlyAphabetic[1000] = {'\0'};
    int j = 0;
    for (char *i = str; *i != '\0'; i++) {
        if((*i >= 65 && *i <= 91) || *i >= 97 && *i <= 122) onlyAphabetic[j++] = *i;
    }
    strcpy(str, onlyAphabetic);
}


void lower(char* str) {
    for (char *i = str; *i != '\0'; i++) {
        *i = tolower(*i);
    }
}


void horizontalHistogram() {
    char str[1000] = {'\0'};
    int occurance[26] = {0};
    bool printed[26] = {false};
    scanf("%[^\n]", str);

    lower(str);

    alphabetic(str);

    frequencyOf(str, occurance);

    for (char *i = str; *i != '\0'; i++) {
        if((*i-97) >= 0 && (*i-97) <= 26 && !printed[*i-97]) {
            printf("%c ", *i);
            printed[*i-97] = true;
        }
    }
    printf("\n");
    bool allZero = true;
    while (allZero) {
        allZero = false;
        bool printed[26] = {false};
        for (char *i = str; *i != '\0'; i++) {
            if(occurance[*i-97] > 0) {
                if(!printed[*i-97]){
                    printf("* ");
                    occurance[*i-97] -= 1;
                    allZero += occurance[*i-97];
                    printed[*i-97] = true;
                }
            }
            else if(occurance[*i-97] == 0 && !printed[*i-97]) {
                printf("  ");
                printed[*i-97] = true;
            }
        }
        printf("\n");
    }
}


void verticalHistogram() {
    char str[1000] = {'\0'};
    int occurance[26] = {0};

    scanf("%[^\n]", str);

    lower(str);

    alphabetic(str);

    frequencyOf(str, occurance);

    for (char *i = str; *i != '\0'; i++) {
        if((*i-97) >= 0 && (*i-97) <= 26 && occurance[*i-97] != 0) {
            printf("%c ", *i);
            for (;occurance[*i-97]!= 0; occurance[*i-97] -= 1) {
                printf("*");
            }
            printf("\n");
        }
    }
}
