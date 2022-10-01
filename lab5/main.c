#include <stdio.h>
#include <string.h>

#define BASE_YEAR 1990

typedef struct {
    unsigned short day : 5;
    unsigned short month : 4;
    unsigned short year : 7;
}b_day;


typedef union {
    unsigned int message;
    struct {
        unsigned char version : 4;
        unsigned char IHL : 4;
        unsigned char DSCP : 6;
        unsigned char ECN : 2;
        unsigned char LENGTH;
    } parsed;
} IPv4;


typedef enum{
    mon = 1,
    tue = 2,
    wed = 3,
    thu = 4,
    fri = 5,
    sat = 6,
    sun = 7
} days;

int main() {
    // printf("Hello, World!\n");
//    b_day my_b_day;
//    my_b_day.year = 2002 - BASE_YEAR;
//    my_b_day.month = 10;
//    my_b_day.day = 11;

    // IPv4 newIP;
    // unsigned int num = 4294967295;
    // newIP.message = num;
    // printf("%u %u %u %u %u\n", newIP.parsed.version, newIP.parsed.IHL, newIP.parsed.DSCP, newIP.parsed.ECN, newIP.parsed.LENGTH);
    
//    days user = 0;
//    char day_name[10] = {'\0'};
//    printf("Enter num of the weekday: ");
//    scanf("%d", &user);
//    switch (user) {
//        case mon:
//            strcpy(day_name, "Mon");
//            break;
//        case tue:
//            strcpy(day_name, "Tue");
//            break;
//        case wed:
//            strcpy(day_name, "Wed");
//            break;
//        case thu:
//            strcpy(day_name, "Thu");
//            break;
//        case fri:
//            strcpy(day_name, "Fri");
//            break;
//        case sat:
//            strcpy(day_name, "Sat");
//            break;
//        case sun:
//            strcpy(day_name, "Sun");
//            break;
//    }
//
//    printf("%d is %s\n", user, day_name);

    return 0;
}
