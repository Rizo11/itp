#include <stdio.h>
#define BOOK_NAME "Inno Recipe"


typedef struct {
    char ingr_name[30];
    char ingr_unit[5];
    float intr_amount;
}ingredient;


typedef struct {
    char recipe_name[30];
    ingredient recipe_ingredients[10];
    float rating;
    char type[20];
}recipe;


typedef struct {
    char bookAuthor[50];
    recipe recipes [10];
}cook_book;


void save_book(cook_book book);
void read_book_from_console(cook_book* book);


int main() {
    cook_book new_cook_book;
    read_book_from_console(&new_cook_book);
    save_book(new_cook_book);

    return 0;
}


void read_book_from_console(cook_book* book){
    printf("Enter the author of the book: \n");
    scanf("%s", book->bookAuthor);

    int n_of_recipes = 0;
    printf("Enter the number of recipes: ");
    scanf("%d", &n_of_recipes);

    for (int i = 0; i < n_of_recipes; i++) {
        printf("Enter the name of the %d meal: ", i+1);
        scanf("%s", book->recipes[i].recipe_name);

        printf("Enter the type of the %d meal: ", i+1);
        scanf("%s", book->recipes[i].type);

        printf("Enter the rating of the %d meal: ", i+1);
        scanf("%f", &book->recipes[i].rating);

        int n_of_ingreds = 0;
        printf("Enter the number of ingredients: ");
        scanf("%d", &n_of_ingreds);

        for (int j = 0; j < n_of_ingreds; j++) {
            printf("Enter the name of the %d ingredient: ", j+1);
            scanf("%s", book->recipes[i].recipe_ingredients[j].ingr_name);

            printf("Enter the measuring unit of the %d ingredient: ", j+1);
            scanf("%s", book->recipes[i].recipe_ingredients[j].ingr_unit);

            printf("Enter the amount of the %d ingredient in %s: ", j+1, book->recipes[i].recipe_ingredients[j].ingr_unit);
            scanf("%f", &book->recipes[i].recipe_ingredients[j].intr_amount);
        }
    }
}


void save_book(cook_book book) {
    FILE* output;
    output = fopen(BOOK_NAME, "w");

    int i = 0, j = 0;
    fprintf(output, "===========%s===========\n", book.bookAuthor);
    for (recipe* r = book.recipes;  r != NULL; r++) {
        i++;
        fprintf(output, "%d. %s....%s....%.2f\n", i, r->recipe_name, r->type, r->rating);
        for (ingredient* ingr = r->recipe_ingredients;  ingr != NULL ; ingr++) {
            fprintf(output, "\t%d.%d %s %-15f %s\n", i, j, ingr->ingr_name, ingr->intr_amount, ingr->ingr_unit);
        }
        j = 0;
    }

    fclose(output);
}
