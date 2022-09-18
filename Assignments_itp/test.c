#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>

int main(void)
{
   FILE *outFile;
    outFile = fopen("input.txt", "w");
   for(int i=1;i<=200;i++)
      fprintf(outFile, "A\n");
    fclose(outFile);
}
//getline gets line with \n