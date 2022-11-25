package objs;

import interfaces.IFlyable;
import interfaces.ILiving;
import interfaces.ISwimmable;

public class Duck implements ISwimmable, IFlyable, ILiving {
    @Override
    public void fly() {
        System.out.println("I " + this.getClass().getName() + " can fly");
    }

    @Override
    public void stopFlying() {
        System.out.println("I " + this.getClass().getName() + " STOP flying");
    }

    @Override
    public void swim() {
        System.out.println("I " + this.getClass().getName() + " can swim");
    }

    @Override
    public void stopSwimming() {
        System.out.println("I " + this.getClass().getName() + " STOP swimming");
    }
}
