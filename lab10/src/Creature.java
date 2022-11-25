public abstract class Creature {
    public String name = null;
    public boolean isAlive = false;
    public abstract void bear(String Name);
    public abstract void die();

    public void shoutName() {
        if (name != null) {
            System.out.println("My name is " + name);
        } else {
            System.out.println("I haven't born yet or I am dead");
        }
    }
}
