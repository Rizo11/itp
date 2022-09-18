#include <stdio.h>
#include <string.h>

int factorial(int n)
{
    int res = 1;
    for (int i = 1; i <= n; i++)
    {
        res *= i;
    }
    
    return res;
}

int check(int n) //145
{
    int result = 0, original = n;
    while(n)
    {
        result += factorial(n%10);
        n /= 10;
    }

    return original == result ? 1 : 0;
}

void findStrongNum()
{
    int start = 0, end = 0;
    printf("start range: ");
    scanf("%d", &start);
    printf("eng range: ");
    scanf("%d", &end);

    for (int i = start; i < end; i++)
    {
        if(check(i))
        {
            printf("%d\n", i);
        }
    }
}

void printWithoutDuplicate()
{
    int size = 0;
    int freq[1000] = {0};

    scanf("%d", &size);
    
    for (int i = 0; i < size; i++)
    {
        int num = 0;
        scanf("%d", &num);
        if(freq[num] == 0) printf("%d ", num);
        freq[num] = 1;
    }
    printf("\n");

}

void printHistogram()
{
    char c;
    int freq[26];
    memset(freq, 0, 26);

    while ((c = getchar()) != '\n')
    {
        freq[c-97] += 1;
    }
    printf("between\n");
    for (int i = 0; i < 26; i++)
    {
        if(freq[i])
        {
            printf("%c ", i+97);
            for (int j = 0; j < freq[i]; j++)
            {
                // printf("*");
            }
            printf("\n");
        }
    }
    
    
}

void findPassword()
{
    char userp[3];
    scanf("%s\n", &userp);
    
    for (int i = 32; i <= 126; i++)
    {
        /* code */
    }
    
}

int main()
{
    printHistogram();
}
// 