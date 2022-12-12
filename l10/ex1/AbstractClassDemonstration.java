package ru.makhmutov.lab.task2;

public class AbstractClassDemonstration {

    /**
     * The entry point of the Creatures program.
     * Iw allows creation of creatures and showing
     * their results of overridden methods bear(),
     * shoutName() and die()
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        Creature[] creatures = addCreatures();
        runCreatureMethods(creatures);
    }

    /**
     * This method allows seeing how creatures are born,
     * shout their names and die
     *
     * @param creatures the array of creatures
     */
    private static void runCreatureMethods(Creature[] creatures) {
        for (Creature creature : creatures) {
            creature.bear();
            creature.shoutName();
            creature.die();
            System.out.println();
        }
    }

    /**
     * This method allows creating creatures of different types
     * and adding them to the array
     *
     * @return the array with creatures
     */
    private static Creature[] addCreatures() {
        Creature[] creatures = new Creature[3];

        Creature human = new Human();
        creatures[0] = human;

        Creature dog = new Dog();
        creatures[1] = dog;

        Creature alien = new Alien();
        creatures[2] = alien;

        return creatures;
    }
}
