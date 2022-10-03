#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define INPUT "input.txt"
#define OUTPUT "output.txt"
//FILE* output;
FILE* input;
typedef enum {name, action, visib} string_type;
typedef enum {false = 0, true = 1} bool;

typedef struct {
    char name[21];
    int team_number;
    int power;
    int visibility;
    bool exists;
} member;

typedef struct {
    char head[21];
    int team_total;
    int team_number;
    int size;
    member mebers[100];
} team;


void read_inputs(team* teams);
void write_to_file(char* message, int mode);
int validate_string(char* str, string_type type);
int checkName(char name[]);

void read_actions(team* teams);

int main() {

    team teams[10];

    read_inputs(teams);
    read_actions(teams);

    fclose(input);
    return 0;
}

void write_to_file(char* message, int mode) {
    FILE* output;
    // open output.txt
    output = fopen("output.txt", "w");
    if(mode == -1) {
        fprintf(output, "Invalid inputs\n");
        fclose(output);
        return;
    }
    fclose(output);
}

int checkName(char name[])
{
    // check for "\n"
    if(!strcmp(name, "\n")) {
        return 0;
    }

    // first letter uppercase check
    if(!(name[0] >= 'A' && name[0] <= 'Z'))
    {
        return 0;
    }
//    char *letter = name;
//    letter++;
//    // check [a-z]
//    while (*letter != '\0')
//    {
//        if(*letter < 'a' || *letter > 'z')
//        {
//            return 0;
//        }
//        letter++;
//    }
    int length = strlen(name);
    for (int i = 1; i < length; ++i) {
        if(name[i] < 'a' || name[i] > 'z') {
            return 0;
        }
    }

    // test 3
    if(length > 20 || length < 2) {
        return 0;
    }
    return 1;
}

int validate_string(char* str, string_type type) {
    switch (type) {
        case name:
            if(!checkName(str)) {
                write_to_file(NULL, -1);
                return -1;
            }
            return 0;
            break;
        case action:
            if(!check_flip(str)) {
                write_to_file(NULL, -1);
                return -1;
            }
            return 1;
            break;
        case visib:
            // error might be here "True\n"
            if(!strcmp(str, "True")) {
                return 0;
            } else if(!strcmp(str, "False")) {
                return 0;
            }
            return  -1;
        default:
            return -2;
    }
}

void read_inputs(team* teams) {
//    FILE* input;
    input = fopen("input.txt", "r");

    int n_of_teams = 0;
    fscanf(input, "%d", &n_of_teams);

    // test 1
    if(n_of_teams < 1 || n_of_teams > 10) {
        write_to_file(NULL, -1);
        return;
    }

    // read team heads
    int n_of_heads = 0;
    for (int i = 0; i < n_of_teams; i++) {
        char head[1000];
        fscanf(input, "%s", head);

        if(validate_string(head, name) == -1) {
            return;
        }
        strcpy(teams[i].head, head);
        n_of_heads += 1;
    }

    // n of teams must be equal to actual n of team heads
    if(n_of_teams != n_of_heads) {
        write_to_file(NULL, -1);
        return;
    }

    int m_of_members = 0;
    fscanf(input, "%d", &m_of_members);

    // test 19
    if(m_of_members < n_of_teams || m_of_members > 100) {
        write_to_file(NULL, -1);
        return;
    }

    // read players
    int n_of_players = 0;
    for (int i = 0; i < m_of_members; i++) {
        char player_name[1000] = {'\0'};
        int team_number = 0;
        int player_power = 0;
        char visibility[50] = {'\0'};

        // read player's name
        fscanf(input, "%s", player_name);
        if(validate_string(player_name, name) == -1) {
            return;
        }

        // read player's team number
        fscanf(input, "%d", &team_number);
        if(team_number < 0 || team_number >= n_of_teams) {
            write_to_file(NULL, -1);
            return;
        }

        // read player's power
        fscanf(input, "%d", &player_power);
        if(player_power < 0 || player_power > 1000) {
            write_to_file(NULL, -1);
            return;
        }

        // read player's visibility
        fscanf(input, "%s", visibility);
        if(validate_string(visibility, visib) == -1) {
            return;
        }

        teams[team_number].mebers[teams[team_number].size].team_number = team_number;
        teams[team_number].mebers[teams[team_number].size].power = player_power;
        strcpy(teams[team_number].mebers[teams[team_number].size].name, player_name);
        // visibility mapper
        if(!strcmp(visibility, "True")) {
            teams[team_number].mebers[teams[team_number].size].visibility = true;
        } else if(!strcmp(visibility, "False")) {
            teams[team_number].mebers[teams[team_number].size].visibility = false;
        }
        teams[team_number].size += 1;
        teams[team_number].mebers[teams[team_number].size].exists = true;
        n_of_players += 1;
    }
    if(n_of_players != m_of_members) {
        write_to_file(NULL, -1);
        return;
    }

//    fclose(input);
}

void read_actions(team* teams) {
    char player_action[1000] = {'\0'};
    while (fscanf(input, "%s", action) == 1) // expect 1 successful conversion
    {
        if(validate_string(player_action, action) == -1) {
            return;
        }
        if(!strcmp(player_action, "flip_visibility")) {
            char player_name[1000] = {'\0'};
            fscanf(input, "%s", player_name);

            if(validate_string(player_name, name) == -1) {
                return;
            }

        }
        // process buffer
    }
    if (feof(input))
    {
        // hit end of file
    }
}
