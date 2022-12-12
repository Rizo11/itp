import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Set<Dog> dogs = new HashSet<>();
        dogs.add(new Dog("barbie", 12));
        dogs.add(new Dog("barbie", 13));
        dogs.add(new Dog("reks", 12));

        Set<Cat> cats = new HashSet<>();
        cats.add(new Cat("nick", 11));
        cats.add(new Cat("nick1", 11));
        cats.add(new Cat("nick", 11));

        displayAnimals(cats);
        displayAnimals(dogs);

        makeTalk(cats);
        makeTalk(dogs);
    }

    public static void displayAnimals (Set<? extends Animal> animals)  {
        for (var b: animals) {
            System.out.println(b.nickname);
        }
    }

    public static void makeTalk (Set<? extends Animal> animals)  {
        for (var b: animals) {
            b.voice();
        }
    }
}

class Dog extends Animal {

    int barkingLoudness;
    public Dog(String Nickname, int barkingLoudness) {
        super(Nickname);
        this.barkingLoudness = barkingLoudness;
    }

    @Override
    void voice() {
        System.out.println("Gav-gav-gav");
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Dog)) {
            return false;
        }
        Dog dog = (Dog) o;
        return Objects.equals(nickname, dog.nickname) &&
                Objects.equals(barkingLoudness, dog.barkingLoudness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, barkingLoudness);
    }
}

class Cat extends Animal {

    int purrLaudness;

    public Cat(String Nickname, int purrLaudness) {
        super(Nickname);
        this.purrLaudness = purrLaudness;
    }

    @Override
    void voice() {
        System.out.println("Miyauv-miyauv-miyauv");
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Cat)) {
            return false;
        }
        Cat cat = (Cat) o;
        return Objects.equals(nickname, cat.nickname) &&
                Objects.equals(purrLaudness, cat.purrLaudness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, purrLaudness);
    }

}


abstract class Animal {
    public Animal(String Nickname) {
        this.nickname = Nickname;
    }
    String nickname;
    abstract void voice();
}