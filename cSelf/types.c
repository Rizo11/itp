#include <stdio.h>

int main(int argc, char **argv)
{
	/* printf("%ld %ld %ld\n", sizeof(int), sizeof(float), sizeof(double));
	int a = 3, b = 2;
	printf("%ld\n", sizeof(a + b));*/

	/* int a1 = 6;
	// printf("%d %d\n", (a1++), a1);

	a1 += ++a1 + a1;
	printf("%d\n", a1); */

	/*int a = 0, b = 1, c = -1;
	printf("%d\n", (--a*(5+b)/2-c++ * b));*/
	// ans = -2
	// explaination- firsty bracket will be evaluated
	// so it will give 6 now evaluate --a, it will give -1 since we are pre decrementing 0
	// now, -1*6 = -6 now -6/2=-3 then -3 - (-1)*b(due to post fix the value of c  will remain same )
	// will give -3 +1*1(since b is given as 1) hence -3+1=-2 ans
};