#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define INPUT_FILE_NAME "input.txt"
#define OUTPUT_FILE_NAME "output.txt"
#define MAX_ARRAY_SIZE 1000
#define MESSAGE "Error in the input.txt"

// writes data to file
void writeToFile(char names[][MAX_ARRAY_SIZE], int size);

// validates name
int checkString(char* name);

// sorts names in ascending order using bubble sort
void sort(char names[][MAX_ARRAY_SIZE], int size);

int main()
{
    // output file
    FILE* file;
    file = freopen(INPUT_FILE_NAME, "r" , stdin);

    // reading number of names from input.txt
    int nOfNames = 0, nOfNamesInFile = 0;
    char names_db[MAX_ARRAY_SIZE][MAX_ARRAY_SIZE];
    fscanf(file, "%d", &nOfNames);

    // validation for N
    if(nOfNames > 100)
    {
        writeToFile(NULL, -1);
        return 0;
    }
    
    // reading names from input.txt
    while (nOfNamesInFile < nOfNames)
    {
        fscanf(file, "%s", names_db[nOfNamesInFile]);
        if(names_db[nOfNamesInFile][0] == '\0')
        {
            writeToFile(NULL, -1);
            return 0;
        }
        else if(checkString(names_db[nOfNamesInFile]))
        {
            writeToFile(NULL, -1);
            return 0;
        }
        nOfNamesInFile++;
    }

    // validation for extra names
    fscanf(file, "%s", names_db[nOfNamesInFile]);
    if (names_db[nOfNamesInFile][0] != '\0')
    {
        writeToFile(NULL, -1);
        return 0;
    }

    //Sort names
    sort(names_db, nOfNames);

    //write sorted names to file
    writeToFile(names_db, nOfNames);

    //Closing input.txt
    fclose(file);
}

void writeToFile(char names[][MAX_ARRAY_SIZE], int size)
{
    // open output.txt file
    FILE *outFile;
    outFile = fopen(OUTPUT_FILE_NAME, "w");

    // write error message
    if(size == -1)
    {
        fprintf(outFile, "%s\n", MESSAGE);
    }
    // write real names
    else
    {
        for (int i = 0; i < size; i++)
        {
            fprintf(outFile, "%s\n", names[i]);
        }
    }

    fclose(outFile);

    return;
}

int checkString(char name[])
{
    // firt letter uppercase check
    if(!(name[0] >= 'A' && name[0] <= 'Z'))
    {
        return 1;
    }
    char *letter = name;
    letter++;
    // check [a-z]
    while (*letter != '\0')
    {
        if(*letter < 'a' || *letter > 'z')
        {
            return 1;
        }
        letter++;
    }
    
    return 0;
}

void sort(char names[][MAX_ARRAY_SIZE], int size)
{
    char tempname[MAX_ARRAY_SIZE];
    for (int k = 0; k < size - 1; k++)
    {
        for (int j = 0; j < size - 1 - k; j++)
        {
            if (strcmp(names[j], names[j + 1]) > 0)
            {
                strcpy(tempname, names[j]);
                strcpy(names[j], names[j + 1]);
                strcpy(names[j + 1], tempname);
            }
        }
    }
}
