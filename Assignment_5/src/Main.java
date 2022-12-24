import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static final String INPUT = "input.txt";
    public static int days;
    private static float field = 0;
    private static final int LENGTHLIMIT = 1;
    private static final float HUNDRED = 100;
    private static final int DAYSLIMIT = 30;
    private static final int ANIMALSLIMIT = 20;
    private static final int LINELIMIT = 4;
    private static final float WEIGHT_AND_SPEED_MIN = 5;
    private static final float WEIGHT_MAX = 200;
    private static final float SPEED_MAX = 60;
    private static ArrayList<String> ANIMAL_TYPES = new ArrayList<String>() {
        {
            add("Lion");
            add("Zebra");
            add("Boar");
        }
    };

    public static void main(String[] args) throws FileNotFoundException {
        List<Animal> animals = readAnimals();
        RunSimulation(field, animals);
        printAnimals(animals);

    }

    private static List<Animal> readAnimals() throws FileNotFoundException {
        File file = new File(INPUT);
        Scanner scanner = new Scanner(file);
        String[] line = null;
        ArrayList<Animal> animals = new ArrayList<>();

        // Reading number of Days
        line = scanner.nextLine().split(" ");
        if (line.length != LENGTHLIMIT) {
            Exceptions.invalidInputs();
        }
        try {
            days = Integer.parseInt(line[0]);
            if (days < LENGTHLIMIT || days > DAYSLIMIT) {
                Exceptions.invalidInputs();
            }
        } catch (Exception ex) {
            Exceptions.invalidInputs();
        }


        // Reading size of the grass
        line = scanner.nextLine().split(" ");
        if (line.length > LENGTHLIMIT) {
            Exceptions.invalidInputs();
        }
        try {
            float grassSize = Float.parseFloat(line[0]);
            if (grassSize < 0 || grassSize > HUNDRED) {
                Exceptions.grassOutOfBound();
            }
            field = grassSize;
        } catch (Exception ex) {
            Exceptions.invalidInputs();
        }


        // Read number of animals
        line = scanner.nextLine().split(" ");
        if (line.length > LENGTHLIMIT) {
            Exceptions.invalidInputs();
        }

        int nOfAnimals = Integer.parseInt(line[0]);
        try {
            if (nOfAnimals < 1 || nOfAnimals > ANIMALSLIMIT) {
                Exceptions.invalidAnimalParams();
            }
        } catch (Exception e) {
            Exceptions.invalidInputs();
        }

        // Read animals
        for (int i = 0; i < nOfAnimals; i++) {
            line = scanner.nextLine().split(" ");
            if (line.length > LINELIMIT) { Exceptions.invalidAnimalParams(); }
            Animal newAnimal = receiveAnimal(line);
            animals.add(newAnimal);
        }
        scanner.close();

        return animals;
    }

    private static void RunSimulation(float grass, List<Animal> animals) {
        final List<Animal> deadAnimals = new ArrayList<>();
        animals.forEach(a -> {
            if (a.getEnergy() <= 0) {
                deadAnimals.add(a);
            }
        });

        deadAnimals.forEach(a -> animals.remove(a));

        Field field = new Field(grass);
        for (int i = 0; i < days; i++) {
            int nOfAnimals = animals.size();

            for (int j = 0; j < nOfAnimals; j++) {
                Animal currentAnimal = animals.get(j);
                if (currentAnimal.getEnergy() <= 0) {continue;}
                currentAnimal.eat(animals, field);
            }

            field.grassGrow();
            animals.forEach(a -> {
                a.setEnergy(1);
                if (a.getEnergy() <= 0) {
                    deadAnimals.add(a);
                }
            });

            deadAnimals.forEach(a -> animals.remove(a));
        }

    }

    private static void printAnimals(List<Animal> animals) {
        animals.forEach(a -> a.makeSound());
    }

    private static Animal receiveAnimal(String[] animalData) {
        Animal newAnimal = null;
        float weight = 0;
        float speed = 0;
        float energy = 0;
        try {
            weight = Float.parseFloat(animalData[1]);
            speed = Float.parseFloat(animalData[2]);
            energy = Float.parseFloat(animalData[3]);
        } catch (Exception ex) {
            Exceptions.invalidInputs();
        }
        if (!ANIMAL_TYPES.contains(animalData[0])) { Exceptions.invalidInputs();}

        if (weight < WEIGHT_AND_SPEED_MIN || weight > WEIGHT_MAX) {
            Exceptions.weightOutOfNumbers();
        } else if (speed < WEIGHT_AND_SPEED_MIN || speed > SPEED_MAX) {
            Exceptions.speedOutOfBounds();
        } else if (energy < 0 || energy > HUNDRED) {
            Exceptions.energyOutOfBounds();
        }

        if (animalData[0].equals("Lion")) {
            newAnimal = new Lion(weight, speed, energy, animalData[0]);
        } else if (animalData[0].equals("Zebra")) {
            newAnimal = new Zebra(weight, speed, energy, animalData[0]);
        } else {
            newAnimal = new Boar(weight, speed, energy, animalData[0]);
        }
        return newAnimal;
    }
    public static class Exceptions {
        public static void grassOutOfBound() {
            System.out.println("The grass is out of bounds");
            System.exit(0);
        }
        public static void invalidAnimalParams() {
            System.out.println("Invalid number of animal parameters");
            System.exit(0);
        }
        public static void invalidInputs() {
            System.out.println("Invalid inputs");
            System.exit(0);
        }
        public static void weightOutOfNumbers() {
            System.out.println("The weight is out of bounds");
            System.exit(0);
        }
        public static void speedOutOfBounds() {
            System.out.println("The speed is out of bounds");
            System.exit(0);
        }
        public static void energyOutOfBounds() {
            System.out.println("The energy is out of bounds");
            System.exit(0);
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
        Animal prey = animals.get((huntedIndex + 1)%animals.size());
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
        prey.setEnergy(prey.getEnergy());
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
            field.setGrassAmount(gain);
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
            field.setGrassAmount(gain);
        }
    }

    @Override
    public <T extends Animal> Animal choosePrey(List<Animal> animals, T hunter) {
        int huntedIndex = animals.indexOf(hunter);
        Animal prey = animals.get((huntedIndex + 1)%animals.size());
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
        prey.setEnergy(prey.getEnergy());
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setEnergy(float energy) {
        this.energy = this.energy - energy <= 0 ? 0 : this.energy - energy;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

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


class Field {
    private float grassAmount;

    public Field(float grassAmount) {
        this.grassAmount = grassAmount;
    }

    public void grassGrow() {
        this.grassAmount = this.grassAmount + this.grassAmount > 100F ? 100F : this.grassAmount + this.grassAmount;
    }

    public float getGrassAmount() {
        return grassAmount;
    }

    public void setGrassAmount(float grassAmount) {

        this.grassAmount = this.grassAmount - grassAmount <= 0 ? 0 : this.grassAmount - grassAmount;
    }
}