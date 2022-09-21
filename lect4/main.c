#include <stdio.h>
#include <string.h>

typedef struct {
    char title[50];
    char author[50];
    char subject[100];
    int bookId;
} book;

union Car {
    char name[50];
    int carId;
};

void modify(book* newBook);

int main() {

    //====Structs====
//    int size = sizeof (book);
//
//    book newBook;
//    strcpy(newBook.title, "C for Dummies");
//    strcpy(newBook.author, "Alaa din Hajjar");
//    strcpy(newBook.subject, "programming");
//    newBook.bookId = 1253;
//
//    printf("\"%s\" by %s %s %d\n", newBook.title, newBook.author, newBook.subject, newBook.bookId);
//    modify(&newBook);
//    printf("\"%s\" by %s %s %d\n", newBook.title, newBook.author, newBook.subject, newBook.bookId);


    //====Union=====
    union Car newCar;
    printf("%d\n", sizeof(newCar));

    newCar.carId = 10;
    strcpy(newCar.name, "Ferrari");

    printf("%d\n", sizeof(newCar));
    printf("%s %d\n", newCar.name, newCar.carId);

    return 0;
}


void modify(book *newBook)
{
    strcpy((*newBook).subject, "newSubject");
    strcpy(newBook->author, "newAuthor");
    strcpy(newBook->title, "newTitle");
    newBook->bookId = 1000;
}
