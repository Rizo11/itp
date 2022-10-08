#include <stdio.h>
#include <string.h>

#define INPUT "input.txt"
#define OUTPUT "output.txt"
FILE* input;
FILE* output = NULL;
int n_teams = 0, S_k = 0, n_of_actions = 0;
typedef enum {name = 1, action, visib, flip, attack, heal, super} string_type;
typedef enum {false = 0, true = 1} bool;
bool error = false;

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
    member mebers[2000];
} team;
void read_inputs(team* teams);
void write_to_file(char* message, int mode);
int validate_string(char* str, string_type type);
int checkName(char name[]);
void read_actions(team* teams);
int check_action(char str[]);
member* is_exists(char player_name[], team* teams);

void calculate_winner(team* teams);

int main() {

    team teams[10];
    input = fopen(INPUT, "r");
    read_inputs(teams);
    read_actions(teams);
    calculate_winner(teams);
    fclose(input);
    return 0;
}

void write_to_file(char* message, int mode) {

    if(mode == -1) {
        if(output != NULL) {
            fclose(output);
            output = NULL;
        }
        output = fopen("output.txt", "w");
        fprintf(output, "Invalid inputs\n");
        fclose(output);
        output = NULL;
        return;
    }
    if(output == NULL) {
        output = fopen(OUTPUT, "w");
    }

    if(mode == 1) {
        fprintf(output, message);
        return;
    } else if(mode == 0) {
        fprintf(output, message);
        fclose(output);
    }
}
// validates a name
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
    int length = strlen(name);
    for (int i = 1; i < length; i++) {
        if(!((name[i] >= 'a' && name[i] <= 'z') || (name[i] >= 'A' && name[i] <= 'Z'))) {
            return 0;
        }
    }

    // test length
    if(length > 19 || length < 2) {
        return 0;
    }
    return 1;
}

// validates action
int check_action(char str[]) {
    if(!strcmp(str, "flip_visibility")) {
        return flip;
    } else if(!strcmp(str, "attack")) {
        return attack;
    } else if(!strcmp(str, "heal")) {
        return heal;
    } else if(!strcmp(str, "super")) {
        return super;
    }
    // doesn't match
    return 0;
}

// validates string
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
            if(!check_action(str)) {
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

// read data
void read_inputs(team* teams) {

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
        teams[i].team_number = i;
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

        if(n_of_players > m_of_members) {
            write_to_file(NULL, -1);
            return;
        }
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
        teams[team_number].mebers[teams[team_number].size].exists = true;
        teams[team_number].size += 1;
        teams[team_number].team_total += player_power;
        n_of_players += 1;
    }

    if(n_of_players != m_of_members) {
        write_to_file(NULL, -1);
        return;
    }
    n_teams = n_of_heads;
}

// checks existance of a member
member* is_exists(char player_name[], team* teams) {
    for (team* t = teams; t < (teams+n_teams); t++) {
        for (member* m = t->mebers;  m < t->mebers + t->size ; m++) {
            if(m->exists == true && !strcmp(player_name, m->name)) {
                return m;
            }
        }
    }
    return NULL;
//    maybe its made to super?
//    player.exists == true
//    return [exist, member*]
}

// reads actions and performs those actions
void read_actions(team* teams) {
    char player_action[1000] = {'\0'};
    while (fscanf(input, "%s", player_action) == 1) // expect 1 successful conversion
    {

        // invalid action
        if(validate_string(player_action, action) == -1) {
            error = true;
            return;
        }

        // attack action
        if(check_action(player_action) == attack) {
            n_of_actions += 1;
            // read predator's, prey's names
            char predator_name[1000] = {'\0'};
            char prey_name[1000] = {'\0'};
            fscanf(input, "%s %s", predator_name, prey_name);

            // invalid names
//            if(validate_string(predator_name, name) == -1 || validate_string(prey_name, name) == -1) {
//                error = true;
//                return;
//            }

            member* predator = NULL;
            member* prey = NULL;
            predator = is_exists(predator_name, teams);
            prey = is_exists(prey_name, teams);

            // if players doesn't exist
            if(predator == NULL || prey == NULL) {
                write_to_file(NULL, -1);
                error = true;
                return;
            }

            // if predator is not visible
            if(predator->visibility == false) {
                write_to_file("This player can't play\n", 1);
                continue;
            }

            // if predator is frozen
            if(predator->power == 0) {
                write_to_file("This player is frozen\n", 1);
                continue;
            }

            // if prey is not visible
            if(prey->visibility == false) {
                teams[predator->team_number].team_total -= predator->power;
                predator->power = 0;
                continue;
            }

            // if prey and predator from same team -> this shouldn't be checked
            //      close old file and "Invalid inputs"
            if(prey->power == predator->power) {
                teams[prey->team_number].team_total -= prey->power;
                prey->power = 0;
                teams[predator->team_number].team_total -= predator->power;
                predator->power = 0;
                continue;
            }
            //      froze both
            //      powers = 0 // maybe pow should not be 0 ?

            // perform attack action what if same person
            if(predator->power > prey->power) {
                int gain = 2*predator->power - prey->power;
                teams[predator->team_number].team_total -= predator->power;
                predator->power = gain >= 1000 ? 1000 : gain;
                teams[predator->team_number].team_total += predator->power;

                teams[prey->team_number].team_total -= prey->power;
                prey->power = 0;
            } else if(predator->power < prey->power) {
                int gain = 2*prey->power - predator->power;
                teams[prey->team_number].team_total -= prey->power;
                prey->power = gain >= 1000 ? 1000 : gain;
                teams[prey->team_number].team_total += prey->power;


                teams[predator->team_number].team_total -= predator->power;
                predator->power = 0;
            }
            continue;

        }

        // flip action
        if(check_action(player_action) == flip) {
            n_of_actions += 1;
            char player_name[1000] = {'\0'};
            fscanf(input, "%s", player_name);

            // invalid name "Invalid inputs"
//            if(validate_string(player_name, name) == -1) {
//                error = true;
//                return;
//            }

            member* player = NULL;
            player = is_exists(player_name, teams);

            // if player doesn't exist close old file and "Invalid inputs"
            if(player == NULL) {
                error = true;
                write_to_file(NULL, -1);
                return;
            }

            // frozen player
            if((*player).power == 0) {
                write_to_file("This player is frozen\n", 1);
                continue;
            }

            // flip its visibility
            if(player->visibility == true) {
                player->visibility = false;
            } else if(player->visibility == false) {
                player->visibility = true;
            }
            continue;
        }

        // heal action
        if(check_action(player_action) == heal) {
            n_of_actions += 1;
            // read donor's, patient's names
            char donor_name[1000] = {'\0'};
            char patient_name[1000] = {'\0'};
            fscanf(input, "%s %s", donor_name, patient_name);

            // validate donor, patient's names
//            if(validate_string(donor_name, name) == -1 || validate_string(patient_name, name) == -1) {
//                error = true;
//                return;
//            }
            member* donor = NULL;
            member* patient = NULL;
            donor = is_exists(donor_name, teams);
            patient = is_exists(patient_name, teams);

            //if donor or player DNE
            if(donor == NULL || patient == NULL) {
                write_to_file(NULL, -1);
                error = true;
                return;
            }

            // if donor is not visible -> "This player can't play"
            if(donor->visibility == false) {
                write_to_file("This player can't play\n", 1);
                continue;
            }

            // if donor is frozen -> "This player is frozen"
            if(donor->power == 0) {
                write_to_file("This player is frozen\n", 1);
                continue;
            }

            // if(players not from the same team) -> "Both players should be from the same team"
            if(donor->team_number != patient->team_number) {
                write_to_file("Both players should be from the same team\n", 1);
                continue;
            }

            // if(players are one person) -> "The player cannot heal itself"
            if(!strcmp(donor->name, patient->name)) {
                write_to_file("The player cannot heal itself\n", 1);
                continue;
            }

            // donor heals the patient
            int given_pow = donor->power % 2 == 0 ? donor->power/2 : (donor->power/2) + 1;

            teams[donor->team_number].team_total -= donor->power;
            donor->power = given_pow;
            teams[donor->team_number].team_total += donor->power;

            teams[patient->team_number].team_total -= patient->power;
            if(patient->power + given_pow >= 1000) {
                patient->power = 1000;
            } else {
                patient->power += given_pow;
            }
            teams[patient->team_number].team_total += patient->power;
            continue;
        }

        // super action
        if(check_action(player_action) == super) {
            n_of_actions += 1;
            char player1_name[1000] = {'\0'};
            char player2_name[1000] = {'\0'};
            fscanf(input, "%s %s", player1_name, player2_name);

            // validate players' names
//            if(validate_string(player1_name, name) == -1 || validate_string(player2_name, name) == -1) {
//                error = true;
//                return;
//            }
            member* player1 = NULL;
            member* player2 = NULL;
            player1 = is_exists(player1_name, teams);
            player2 = is_exists(player2_name, teams);

            //if players DNE
            if(player1 == NULL || player2 == NULL) {
                error = true;
                write_to_file(NULL, -1);
                return;
            }

            // if 1st person is not visible -> "This player can't play"
            if(player1->visibility == false) {
                write_to_file("This player can't play\n", 1);
                continue;
            }
            // if 1st person is frozen -> "This player is frozen"
            if(player1->power == 0) {
                write_to_file("This player is frozen\n", 1);
                continue;
            }

            // if from different teams -> "Both players should be from the same team"
            if(player1->team_number != player2->team_number) {
                write_to_file("Both players should be from the same team\n", 1);
                continue;
            }

            // if same person -> "The player cannot do super action with itself"
            if(!strcmp(player1->name, player2->name)) {
                write_to_file("The player cannot do super action with itself", 1);
                continue;
            }

            //perform super
            char super_name[10] = {'\0'};
            sprintf(super_name, "S_%d", S_k);
            strcpy(player1->name, super_name);
            int total_pow = player1->power + player2->power;
            teams[player1->team_number].team_total -= player1->power;
            player1->power = total_pow >= 1000 ? 1000 : total_pow;
            player1->visibility = true;
            teams[player1->team_number].team_total += player1->power;
            S_k++;

            teams[player2->team_number].team_total -= player2->power;
            player2->exists = false;
            continue;
        }
    }
    if(n_of_actions > 1000) {
        error = true;
        write_to_file(NULL, -1);
    }
}

// calculates the winner
void calculate_winner(team* teams) {
    if(error) {
        return;
    }
    int max = -1;
    team result;
    bool tie = false;

    for (team* t = teams; t < teams+n_teams; t++) {
        if(t->team_total > max) {
            max = t->team_total;
            result = (*t);
        }

    }
    for (team* t = teams; t < teams+n_teams; t++) {
        if(t->team_total == max && (strcmp(result.head, t->head) != 0)) {
            tie = true;
        }
    }

    if(tie) {
        write_to_file("It's a tie\n", 0);
    } else {
        char res[200] = "The chosen wizard is ";
        strcat(res, result.head);
        strcat(res, "\n");
        write_to_file(res, 0);

    }
}