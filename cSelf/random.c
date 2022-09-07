#include <stdio.h>
#include <time.h>
#include <stdlib.h>



int main()
{
    // ===========> current time <===========
    //time_t is long
    /* time_t timeInSeconds;
    long s; */

    // returns seconds since January 1, 1970
    // if seconds values is not null, puts current time to seconds
    /* time(&timeInSeconds);
    s = timeInSeconds;

    printf("%ld\n%ld\n", timeInSeconds, s); */


    // ===========> generating random <===========

    // scrand() should be used before rand()
    /* srand(time(NULL));
    int newRandom = rand()%100;
    printf("%d\n", newRandom); */


    // ===========> Advanced random <============
    // LINK: http://www.azillionmonkeys.com/qed/random.html
}