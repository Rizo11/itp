public class Human extends Creature{
    @Override
    public void bear(String Name) {
        isAlive = true;
        name = Name;
        System.out.println("The " + this.getClass().getSimpleName() + " " + name + " was born");
    }

    @Override
    public void die() {
        isAlive = false;
        System.out.println("This " + this.getClass().getSimpleName() + " " + name + " has died");
        name = null;
    }
}
