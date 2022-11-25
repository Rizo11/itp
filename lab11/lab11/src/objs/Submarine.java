package objs;

import interfaces.ISwimmable;

public class Submarine implements ISwimmable {

    @Override
    public void swim() {
        System.out.println("I am " + this.getClass().getName() + " can swim");
    }

    @Override
    public void stopSwimming() {
        System.out.println("I STOP " + this.getClass().getName() + " swimming");
    }
}
