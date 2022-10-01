#include <stdio.h>
#include <string.h>
#define MAX_MEMBER_COUNT 100

typedef enum {Student = 0, TA, Professor} role;
typedef enum {Secondary = 0, Bachelor, Master, PhD} degree;


typedef struct {
    char Name[30];
    degree Degree;
    role Role;
} member;


const char* role_string[] = {"Student", "TA", "Professor"};
const char* degree_string[] = {"Secondary", "Bachelor", "Master", "PhD"};


role role_parser(char* member_role);

degree degree_parser(char* member_degree);

void sort_members(member* members, size_t size);

void swap_members(member* m1, member* m2);

void print_members(member members[], size_t size);

int main() {

    size_t n_of_members = 0;
    scanf("%d", &n_of_members);

    member members[MAX_MEMBER_COUNT];
    char role_buffer[12], degree_buffer[12];
    for (size_t i = 0; i < n_of_members; i++) {
        scanf("%s", members[i].Name);
        scanf("%s", role_buffer);
        scanf("%s", degree_buffer);
        members[i].Role = role_parser(role_buffer);
        members[i].Degree = degree_parser(degree_buffer);
    }

    sort_members(&members, n_of_members);
    print_members(members, n_of_members);
    return 0;
}


void sort_members(member* members, size_t size) {
    for (member* m = members; m < members+size; m++) {
        for (member* m2 = m + 1; m2 < members+size; m2++) {
            if(m->Role > m2->Role) {
                swap_members(m, m2);
            } else if(m->Degree == m2->Degree) {
                if(m->Degree > m2->Degree) {
                    swap_members(m, m2);
                }
            }
        }
    }
}


void swap_members(member* m1, member* m2) {
    member tmp = *m1;
    *m1 = *m2;
    *m2 = tmp;
}


void print_members(member members[], size_t size) {
    for (size_t i = 0; i < size; i++) {
        printf("%s %s %s\n", members[i].Name, role_string[members[i].Role], degree_string[members[i].Degree]);
    }
}


role role_parser(char* member_role)
{
    if(!strcmp(member_role, role_string[Student]))
    {
        return Student;
    } else if(!strcmp(member_role, role_string[TA])) {
        return TA;
    } else {
        return Professor;
    }
}


degree degree_parser(char* member_degree)
{
    if(!strcmp(member_degree, degree_string[Secondary]))
    {
        return Secondary;
    } else if(!strcmp(member_degree, degree_string[Bachelor])) {
        return Bachelor;
    } else if(!strcmp(member_degree, degree_string[Master])){
        return Master;
    } else {
        return PhD;
    }
}
