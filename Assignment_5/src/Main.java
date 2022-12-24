import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    private List<Animal> readAnimals() {
        return null;
    }

    private void RunSimulation(int days, float grass, List<Animal>) {

    }

    private void printAnimals(List<Animal> animals) {

    }

    private Animal receiveAnimal(String[] animalData) {
        return null;
    }
    public static class Exceptions {
        public static void grassOutOfBound() {
            System.out.println("The grass is out of bounds");
        }
        public static void invalidAnimalParams() {
            System.out.println("Invalid number of animal parameters");
        }
        public static void invalidInputs() {
            System.out.println("Invalid inputs");
        }
        public static void weightOutOfNumbers() {
            System.out.println("The weight is out of bounds");
        }
        public static void speedOutOfBounds() {
            System.out.println("The speed is out of bounds");
        }
        public static void energyOutOfBounds() {
            System.out.println("The energy is out of bounds");
        }
        public static void selfHunting() {
            System.out.println("Self-hunting is not allowed");
        }
        public static void cannibalism() {
            System.out.println("Cannibalism is not allowed");
        }
        public static void tooStrongPrey() {
            System.out.println("The prey is too strong or too fast to attack");
        }
    }

}


interface Herbivore {
    void grazeInTheField(Animal eater, Field field);
}
interface Omnivore extends Herbivore, Carnivore {

}
interface Carnivore {
    <T extends Animal> Animal choosePrey(List<Animal> animals, T hunter);
    void huntPrey(Animal hunter, Animal prey);
}

abstract class Animal {
    private final float weight;
    private float speed;
    private float energy;

    private String sound;

    public Animal(float weight, float speed, float energy, String sound) {
        this.weight = weight;
        this.speed = speed;
        this.energy = energy;
        if (sound.equals("Lion")) {
            this.sound = "Roar";
        } else if (sound.equals(("Zebra"))) {
            this.sound = "Ihoho";
        } else {
            this.sound = "Oink";
        }
    }

    public void makeSound() {
        System.out.println(this.sound);
    }
    public void documentEnergy(float energy) {
        this.energy = this.energy + energy > 100F ? 100F : this.energy + energy;
    }

    abstract void eat(List<Animal> animals, Field field);

    public float getWeight() {
        return weight;
    }

    public float getSpeed() {
        return speed;
    }

    public float getEnergy() {
        return energy;
    }
}

class Lion extends Animal implements Carnivore {

    public Lion(float weight, float speed, float energy, String sound) {
        super(weight, speed, energy, sound);
    }

    @Override
    void eat(List<Animal> animals, Field field) {
        Animal prey = choosePrey(animals, this);
        if ( prey != null) {
            huntPrey(this, prey);
        }
    }

    @Override
    public <T extends Animal> Animal choosePrey(List<Animal> animals, T hunter) {
        int huntedIndex = animals.indexOf(hunter);
        Animal prey = animals.get(huntedIndex + 1);
        if (hunter == prey) {
            Main.Exceptions.selfHunting();
            return null;
        }
        else if (hunter.getClass().equals(prey.getClass())) {
            Main.Exceptions.cannibalism();
            return null;
        } else {
            if (hunter.getSpeed() > prey.getSpeed() || this.getEnergy() > prey.getEnergy()) {
                return prey;
            }
            Main.Exceptions.tooStrongPrey();
            return null;
        }
    }

    @Override
    public void huntPrey(Animal hunter, Animal prey) {
        hunter.documentEnergy(prey.getWeight());
    }
}

class Zebra extends Animal implements Herbivore {

    public Zebra(float weight, float speed, float energy, String sound) {
        super(weight, speed, energy, sound);
    }

    @Override
    void eat(List<Animal> animals, Field field) {
        grazeInTheField(this, field);
    }

    @Override
    public void grazeInTheField(Animal eater, Field field) {
        float gain = (float) (eater.getWeight()/10);
        if (field.getGrassAmount() > gain) {
            eater.documentEnergy(gain);
        }
    }
}

class Boar extends Animal implements Herbivore, Carnivore {

    public Boar(float weight, float speed, float energy, String sound) {
        super(weight, speed, energy, sound);
    }

    @Override
    void eat(List<Animal> animals, Field field) {
        grazeInTheField(this, field);
        Animal prey = choosePrey(animals, this);
        if ( prey != null) {
            huntPrey(this, prey);
        }
    }

    @Override
    public void grazeInTheField(Animal eater, Field field) {
        float gain = (float) (eater.getWeight()/10);
        if (field.getGrassAmount() > gain) {
            eater.documentEnergy(gain);
        }
    }

    @Override
    public <T extends Animal> Animal choosePrey(List<Animal> animals, T hunter) {
        int huntedIndex = animals.indexOf(hunter);
        Animal prey = animals.get(huntedIndex + 1);
        if (hunter == prey) {
            Main.Exceptions.selfHunting();
            return null;
        }
        else if (hunter.getClass().equals(prey.getClass())) {
            Main.Exceptions.cannibalism();
            return null;
        } else {
            if (hunter.getSpeed() > prey.getSpeed() || this.getEnergy() > prey.getEnergy()) {
                return prey;
            }
            Main.Exceptions.tooStrongPrey();
            return null;
        }
    }

    @Override
    public void huntPrey(Animal hunter, Animal prey) {
        hunter.documentEnergy(prey.getWeight());
    }
}

class Field {
    private float grassAmount;

    public Field(float grassAmount) {
        this.grassAmount = grassAmount;
    }

    public void grassGrow() {
        this.grassAmount = this.grassAmount + this.grassAmount > 100F ? 100F : this.grassAmount + this.grassAmount+this.grassAmount;
    }

    public float getGrassAmount() {
        return grassAmount;
    }
}