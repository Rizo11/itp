#include <stdio.h>
#include <stdlib.h>
#include "../Random/library.h"
#include <time.h>
#define BOOK_NAME "../Inno Recipe.txt"

typedef struct {
    char ingr_name[30];
    char ingr_unit[15];
    float intr_amount;
}ingredient;


typedef struct {
    char recipe_name[30];
    ingredient recipe_ingredients[10];
    float rating;
    char type[20];
    short int n_of_ingredients;
}recipe;


typedef struct {
    char bookAuthor[40];
    recipe recipes [10];
    short int n_of_recipe;
}cook_book;


void save_book(cook_book book);
void read_book_from_console(cook_book* book);


int main() {
    // TEST
//    srand(time(NULL));


    cook_book new_cook_book;
    read_book_from_console(&new_cook_book);
    save_book(new_cook_book);

    return 0;
}


void read_book_from_console(cook_book* book){
    // PRODUCTION
    printf("Enter the author of the book: \n");
    scanf("%s", book->bookAuthor);

//     TEST
//    srand(time(NULL));
//    rand_str(book->bookAuthor, random_number(5, 15));

    short n_of_recipes = 0;

    // PRODUCTION
    printf("Enter the number of recipes: ");
    scanf("%hi", &n_of_recipes);
    book->n_of_recipe = n_of_recipes;

    // TEST
//    n_of_recipes = random_number(2, 10);
//    book->n_of_recipe = n_of_recipes;


    for (int i = 0; i < n_of_recipes; i++) {
        // PRODUCTION
        printf("Enter the name of the %d meal: ", i+1);
        scanf("%s", book->recipes[i].recipe_name);

        printf("Enter the type of the %s meal: ", book->recipes[i].recipe_name);
        scanf("%s", book->recipes[i].type);

        printf("Enter the rating of the %s meal: ", book->recipes[i].recipe_name);
        scanf("%f", &book->recipes[i].rating);

        short n_of_ingreds = 0;
        printf("Enter the number of ingredients of %s: ", book->recipes[i].recipe_name);
        scanf("%hi", &n_of_ingreds);
        book->recipes[i].n_of_ingredients = n_of_ingreds;

        // TEST
//        rand_str( book->recipes[i].recipe_name, random_number(4, 10));
//        rand_str(book->recipes[i].type, random_number(4, 8));
//        book->recipes[i].rating = (float)random_number(1, 100)/10;
//
//        short n_of_ingreds = 0;
//        n_of_ingreds = random_number(2, 10);
//        book->recipes[i].n_of_ingredients = n_of_ingreds;

        // PRODUCTION
        for (int j = 0; j < n_of_ingreds; j++) {
            printf("Enter the name of the %d ingredient: ", j+1);
            scanf("%s", book->recipes[i].recipe_ingredients[j].ingr_name);

            printf("Measuring unit of %s: ", book->recipes[i].recipe_ingredients[j].ingr_name);
            scanf("%s", book->recipes[i].recipe_ingredients[j].ingr_unit);

            printf("Amount of %s in %s: ", book->recipes[i].recipe_ingredients[j].ingr_name, book->recipes[i].recipe_ingredients[j].ingr_unit);
            scanf("%f", &book->recipes[i].recipe_ingredients[j].intr_amount);
        }

        // TEST
//        for (int j = 0; j < n_of_ingreds; j++) {
//            rand_str(book->recipes[i].recipe_ingredients[j].ingr_name, random_number(4, 10));
//            rand_str(book->recipes[i].recipe_ingredients[j].ingr_unit, random_number(2, 6));
//            book->recipes[i].recipe_ingredients[j].intr_amount = random_number(1, 100)/10;
//        }
    }
}


void save_book(cook_book book) {
    FILE* output;
    output = fopen(BOOK_NAME, "w");

    fprintf(output, "=========== %s's cook book ===========\n", book.bookAuthor);
    for (short i = 0;  i < book.n_of_recipe; i++) {
        fprintf(output, "%d. %s_____%s_____%.1f\n", i+1, book.recipes[i].recipe_name, book.recipes[i].type, book.recipes[i].rating);
        for (short j = 0;  j < book.recipes[i].n_of_ingredients; j++) {
            fprintf(output, "\t%-2d|%-30s | %5.1f %-15s\n", j+1, book.recipes[i].recipe_ingredients[j].ingr_name, book.recipes[i].recipe_ingredients[j].intr_amount, book.recipes[i].recipe_ingredients[j].ingr_unit);
        }
    }

    fclose(output);
}
