package objs;

import interfaces.ILiving;
import interfaces.ISwimmable;

public class Penguin implements ISwimmable, ILiving {

    @Override
    public void swim() {
        System.out.println("I " + this.getClass().getName() + " can swim");
    }

    @Override
    public void stopSwimming() {
        System.out.println("I " + this.getClass().getName() + " STOP swimming");
    }
}
