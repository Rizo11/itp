#include <stdio.h>


int factorial(int n);
void func();
void readStudent();
void encryptint();

typedef struct {
    int day;
    int year;
    char month[10];
} exam_day;

typedef struct {
    char name[50];
    char surname[50];
    int group_number;
    exam_day exam_date;
} student;


typedef union {
    char bin[8];
    unsigned long long int number;
} encrypt;

int main() {
//    readStudent();
//    encryptint();
    return 0;
}

// recursive functions
// each call has its own stack frame, local vars be declared again
// in for loops you can't make multiple conditional iteration
// RFs are more flexible
//             O
//        /    |     \
//      /      |      \
//     O       O       O
//   / | \   / | \   / | \
//
// e.g: factorials
// n! = n(n-1)...1
// base case = stopping condition
int factorial(int n) {
    if(n == 1) {
        return 1;
    }

    return factorial(n-1)*n;
}

//factorial(4) = 4 * factorial(3) = 24
//        factorial(3) = 3 * factorial(2) = 6
//                factorial(2) = 2 * factorial(1) = 2
//                        factorial(1) = 1 * factorial(0) = 1
//                                factorial(0) = 1

void func() {
    static int x = 5;
    int y = 5;
    while (y < 10 && x < 10) {
        printf("x = %d, y = %d\n", x, y);
        x++;
        y++;
        func();
    }
}

// static will not change its value when called once more. everytime when
// function is called static member will not be declared again. static is initialized
// only once.
// can be used to count how many times f() was called


//==========Structs==========
// used to store different values in one variable
struct Student {
    char name[50];
    int groupNumber;
    char grade;
};

// to access struct members you will use .


void readStudent()
{
    student newStudent;

    // read name
    printf("Name: ");
    scanf("%s", newStudent.name);

    // read surname
    printf("Surname: ");
    scanf("%s", newStudent.surname);

    // read group
    printf("Group: ");
    scanf("%d", &newStudent.group_number);

    // read exam data
    printf("Exam day dd month year: ");
    scanf("%d %s %d", &newStudent.exam_date.day, newStudent.exam_date.month, &newStudent.exam_date.year);

    // print newStudent data
    printf("Name: %s Surname: %s Group: %d\n", newStudent.name, newStudent.surname, newStudent.group_number);
    printf("Exam date: %d %s %d\n", newStudent.exam_date.day, newStudent.exam_date.month, newStudent.exam_date.year);
}


//=========Unions==========
union Car {
    int year;
    char name[40];
    int price;
};

void encryptint()
{
    printf("Enter a long long int: ");
    encrypt num;
    scanf("%lld", &num.number);
    printf("%lld\n", num.number);

    for (int i = 0; i < 8; i += 2) {
        char tmp = num.bin[i];
        num.bin[i] = num.bin[i+1];
        num.bin[i+1] = tmp;
    }
    printf("%lld\n", num.number);
}
