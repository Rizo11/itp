import interfaces.ILiving;
import objs.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ArrayList<ILiving> livings = new ArrayList<>();
        Penguin p = new Penguin();
        Duck d = new Duck();

        livings.add(d);
        livings.add(p);

        for (ILiving l : livings) {
            l.live();
        }
    }
}