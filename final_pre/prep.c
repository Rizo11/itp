#include <stdio.h>

void swap(int *a, int *b) {
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

int main() {
	int a, b;
    printf("enter 2 nums: ");
	scanf("%d %d", &a, &b);
	swap(&a, &b);
	printf("swapped: %d %d\n", a, b);

    char name[10] = "10";
    scanf("%s", &name);
    printf("%s\n");

	return 0;

}