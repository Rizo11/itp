#include <stdio.h>
#include <time.h>


// simple macro
// #define MAX(a, b) (a > b ? a : b)


//compound macro
#define MAX_COMPOUND(A, B)({ \
					typeof(A) _a = (A); \
					typeof(B) _b = (B); \
					_a > _b ? _a : _b; \
				})

int getNextNum()
{
	int nextnum = rand() % 100;
	printf("next: %d\n", nextnum);
	return nextnum;
}

int main()
{
	//case 1
	// printf("%d\n", MAX(4, 4));
	// int main()
	// {
	//     printf("%d\n", (4 > 4 ? 4 : 4));
	// }


	//case 2
	// int a, b;
	// int one = MAX(a = getNextNum(), b = getNextNum());
	// printf("%d\n", one);
	// int one = (a = getNextNum() > b = getNextNum() ? a = getNextNum() : b = getNextNum());
	// solution: changing MAX (a, b) ((a) > (b) ? (a) : (b)) or int one = MAX((a = getNextNum()), (b = getNextNum()));


	// case 3
	// MAX(4, "abc");
	// (4 > "abc" ? 4 : "abc");

	
	//case 4
	srand(time(NULL));
	int randomMax = MAX_COMPOUND(getNextNum(), getNextNum());
	printf("max of 2 rands: %d\nnew random: %d\nnew random: %d\n", randomMax, getNextNum(), getNextNum());
	// int rnadomMax = ({ typeof(getNextNum()) _a = (getNextNum()); typeof(getNextNum()) _b = (getNextNum()); _a < _b ? _a : _b; });

}