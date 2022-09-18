//
// Created by Rizo11 on 9/18/2022.
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


void frequencyOf( char *str, int* occurrence) {
    char* letter = str;
    while ((*letter) != '\0') {
        if(*letter-97 >= 0 && *letter-97 <= 26)
            occurrence[*letter-97] += 1;
        letter++;
    }
}


void alphabetic(char* str)
{
    char onlyAlphabetic[1000] = {'\0'};
    int j = 0;
    for (char *i = str; *i != '\0'; i++) {
        if((*i >= 65 && *i <= 91) || *i >= 97 && *i <= 122) onlyAlphabetic[j++] = *i;
    }
    strcpy(str, onlyAlphabetic);
}


void lower(char* str) {
    for (char *i = str; *i != '\0'; i++) {
        *i = tolower(*i);
    }
}


void horizontalHistogram() {
    char str[1000] = {'\0'};
    int occurrence[26] = {0};
    bool printed[26] = {false};
    scanf("%[^\n]", str);

    lower(str);

    alphabetic(str);

    frequencyOf(str, occurrence);

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
            if(occurrence[*i-97] > 0) {
                if(!printed[*i-97]){
                    printf("* ");
                    occurrence[*i-97] -= 1;
                    allZero += occurrence[*i-97];
                    printed[*i-97] = true;
                }
            }
            else if(occurrence[*i-97] == 0 && !printed[*i-97]) {
                printf("  ");
                printed[*i-97] = true;
            }
        }
        printf("\n");
    }
}


void verticalHistogram() {
    char str[1000] = {'\0'};
    int occurrence[26] = {0};

    scanf("%[^\n]", str);

    lower(str);

    alphabetic(str);

    frequencyOf(str, occurrence);

    for (char *i = str; *i != '\0'; i++) {
        if(occurrence[*i-97] != 0) {
            printf("%c ", *i);
            for (;occurrence[*i-97]!= 0; occurrence[*i-97] -= 1) {
                printf("*");
            }
            printf("\n");
        }
    }
}


void swap(int* a, int* b)
{
    int tmp = *a;
    *a = *b;
    *b = tmp;
}


void sortFreqDesAlph(int *frequency, int* letters)
{
    for (int i = 0; i < 25; i++) {
        for (int j = 0; j < 25-i; j++) {
            if(frequency[j] < frequency[j+1]) {
                swap(&frequency[j], &frequency[j+1]);
                swap(&letters[j], &letters[j+1]);
            } else if(frequency[j] == frequency[j+1]) {
                if(letters[j] > letters[j+1]) {
                    swap(&frequency[j], &frequency[j+1]);
                    swap(&letters[j], &letters[j+1]);
                }
            }
        }
    }
}


void sortedVerticalHistogram()
{
    char str[1000] = {'\0'};
    int occurrence[26] = {0}, letters[26] = {0-25};

    for (int i = 0; i < 26; i++) {
        letters[i] = i;
    }
    scanf("%[^\n]", str);

    lower(str);

    alphabetic(str);

    frequencyOf(str, occurrence);

    sortFreqDesAlph(occurrence, letters);

    for (int i = 0; i < 26; i++) {
        if(occurrence[i]) {
            printf("%c ", letters[i]+97);
            for (int j = 0; j < occurrence[i]; j++) {
                printf("*");
            }
            printf("\n");
        }
    }
}
