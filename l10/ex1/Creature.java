package ru.makhmutov.lab.task2;

public abstract class Creature {

    private String name = null;
    private boolean isAlive = false;

    public abstract void bear();

    public abstract void die();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * This method prints out the name of a Creature
     */
    void shoutName() {
        if (name != null) {
            System.out.println("My name is " + name);
        } else {
            System.out.println("Error: the name is not assigned");
            System.exit(1);
        }
    }
}
