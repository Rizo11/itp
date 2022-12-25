import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public final class Main {
    private Main() { }
    public static final String INPUT = "input.txt";
    private static int days;
    private static float field = 0;
    private static final int LENGTH_LIMIT = 1;
    private static final float HUNDRED = 100;
    private static final int DAYS_LIMIT = 30;
    private static final int ANIMAL_LIMIT = 20;
    private static final int LINE_LIMIT = 4;
    private static final float WEIGHT_AND_SPEED_MIN = 5;
    private static final float WEIGHT_MAX = 200;
    private static final float SPEED_MAX = 60;
    private static final ArrayList<String> ANIMAL_TYPES = new ArrayList<String>() {
        {
            add("Lion");
            add("Zebra");
            add("Boar");
        }
    };

    public static void main(String[] args) throws FileNotFoundException {
        List<Animal> animals = readAnimals();
        runSimulation(field, animals);
        printAnimals(animals);

    }

    private static List<Animal> readAnimals() throws FileNotFoundException {
        File file = new File(INPUT);
        Scanner scanner = new Scanner(file);
        String[] line;
        ArrayList<Animal> animals = new ArrayList<>();

        // Reading number of Days
        line = scanner.nextLine().split(" ");
        if (line.length != LENGTH_LIMIT) {
            Exceptions.invalidInputs();
        }
        try {
            days = Integer.parseInt(line[0]);
            if (days < LENGTH_LIMIT || days > DAYS_LIMIT) {
                Exceptions.invalidInputs();
            }
        } catch (Exception ex) {
            Exceptions.invalidInputs();
        }


        // Reading size of the grass
        line = scanner.nextLine().split(" ");
        try {
            float grassSize = Float.parseFloat(line[0]);
            if (grassSize < 0 || grassSize > HUNDRED) {
                Exceptions.grassOutOfBound();
            }
            field = grassSize;
        } catch (Exception ex) {
            Exceptions.grassOutOfBound();
        }


        // Read number of animals
        line = scanner.nextLine().split(" ");
        if (line.length > LENGTH_LIMIT) {
            Exceptions.invalidInputs();
        }

        int nOfAnimals = 0;
        try {
            nOfAnimals = Integer.parseInt(line[0]);
            if (nOfAnimals < 1 || nOfAnimals > ANIMAL_LIMIT) {
                Exceptions.invalidInputs();
            }
        } catch (Exception e) {
            Exceptions.invalidInputs();
        }

        // Read animals
        for (int i = 0; i < nOfAnimals; i++) {
            if (!scanner.hasNext()) {
                Exceptions.invalidInputs();
            }
            line = scanner.nextLine().split(" ");
//            if(line.length == 0) { Exceptions.invalidInputs(); }
            if (line.length != LINE_LIMIT) {
                Exceptions.invalidAnimalParams();
            }
            Animal newAnimal = receiveAnimal(line);
            animals.add(newAnimal);
        }
        animals.forEach(a -> {
            if (a.getWeight() < WEIGHT_AND_SPEED_MIN || a.getWeight() > WEIGHT_MAX) {
                Exceptions.weightOutOfNumbers();
            }
        });
        animals.forEach(a -> {
            if (a.getSpeed() < WEIGHT_AND_SPEED_MIN || a.getSpeed() > SPEED_MAX) {
                Exceptions.speedOutOfBounds();
            }
        });
        animals.forEach(a -> {
            if (a.getEnergy() < 0 || a.getEnergy() > HUNDRED) {
                Exceptions.energyOutOfBounds();
            }
        });
        scanner.close();

        return animals;
    }

    private static void runSimulation(float grass, List<Animal> animals) {
        final List<Animal> deadAnimals = new ArrayList<>();
        animals.forEach(a -> {
            if (a.getEnergy() <= 0) {
                deadAnimals.add(a);
            }
        });

        deadAnimals.forEach(animals::remove);

        Field fieldVar = new Field(grass);
        for (int i = 0; i < days; i++) {
            int nOfAnimals = animals.size();

            for (int j = 0; j < nOfAnimals; j++) {
                Animal currentAnimal = animals.get(j);
                if (currentAnimal.getEnergy() <= 0) {
                    continue;
                }
                currentAnimal.eat(animals, fieldVar);
            }

            fieldVar.grassGrow();
            animals.forEach(a -> {
                a.setEnergy(1);
                if (a.getEnergy() <= 0) {
                    deadAnimals.add(a);
                }
            });

            deadAnimals.forEach(animals::remove);
        }

    }

    private static void printAnimals(List<Animal> animals) {
        animals.forEach(Animal::makeSound);
    }

    private static Animal receiveAnimal(String[] animalData) {
        Animal newAnimal;
        float weight = 0;
        float speed = 0;
        float energy = 0;
        int weightI = 1;
        int speedI = 2;
        final int energyI = 3;
        try {
            weight = Float.parseFloat(animalData[weightI]);
            speed = Float.parseFloat(animalData[speedI]);
            energy = Float.parseFloat(animalData[energyI]);
        } catch (Exception ex) {
            Exceptions.invalidInputs();
        }
        if (!ANIMAL_TYPES.contains(animalData[0])) {
            Exceptions.invalidInputs();
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

    Lion(float weight, float speed, float energy, String sound) {
        super(weight, speed, energy, sound);
    }

    @Override
    void eat(List<Animal> animals, Field field) {
        Animal prey = choosePrey(animals, this);
        if (prey != null) {
            huntPrey(this, prey);
        }
    }

    @Override
    public <T extends Animal> Animal choosePrey(List<Animal> animals, T hunter) {
        int huntedIndex = animals.indexOf(hunter);
        Animal prey = animals.get((huntedIndex + 1) % animals.size());
        if (hunter == prey) {
            Main.Exceptions.selfHunting();
            return null;
        } else if (hunter.getClass().equals(prey.getClass())) {
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

    Zebra(float weight, float speed, float energy, String sound) {
        super(weight, speed, energy, sound);
    }

    @Override
    void eat(List<Animal> animals, Field field) {
        grazeInTheField(this, field);
    }

    @Override
    public void grazeInTheField(Animal eater, Field field) {
        float gain = eater.getWeight() / TEN;
        if (field.getGrassAmount() > gain) {
            eater.documentEnergy(gain);
            field.setGrassAmount(gain);
        }
    }
}

class Boar extends Animal implements Omnivore {
    Boar(float weight, float speed, float energy, String sound) {
        super(weight, speed, energy, sound);
    }

    @Override
    void eat(List<Animal> animals, Field field) {
        grazeInTheField(this, field);
        Animal prey = choosePrey(animals, this);
        if (prey != null) {
            huntPrey(this, prey);
        }
    }

    @Override
    public void grazeInTheField(Animal eater, Field field) {
        float gain = eater.getWeight() / TEN;
        if (field.getGrassAmount() > gain) {
            eater.documentEnergy(gain);
            field.setGrassAmount(gain);
        }
    }

    @Override
    public <T extends Animal> Animal choosePrey(List<Animal> animals, T hunter) {
        int huntedIndex = animals.indexOf(hunter);
        Animal prey = animals.get((huntedIndex + 1) % animals.size());
        if (hunter == prey) {
            Main.Exceptions.selfHunting();
            return null;
        } else if (hunter.getClass().equals(prey.getClass())) {
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
    private final float speed;
    private float energy;
    protected static final float HUNDRED = 100F;

    public void setEnergy(float energyArg) {
        this.energy = this.energy - energyArg <= 0 ? 0 : this.energy - energyArg;
    }

    private final String sound;
    protected static final int TEN = 10;

    Animal(float weightArg, float speedArg, float energyArg, String soundArg) {
        this.weight = weightArg;
        this.speed = speedArg;
        this.energy = energyArg;
        if (soundArg.equals("Lion")) {
            this.sound = "Roar";
        } else if (soundArg.equals(("Zebra"))) {
            this.sound = "Ihoho";
        } else {
            this.sound = "Oink";
        }
    }

    public void makeSound() {
        System.out.println(this.sound);
    }
    public void documentEnergy(float energyArg) {
        this.energy = Math.min(this.energy + energyArg, HUNDRED);
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
    protected static final float HUNDRED = 100F;
    private float grassAmount;

    Field(float grassAmountArg) {
        this.grassAmount = grassAmountArg;
    }

    public void grassGrow() {
        this.grassAmount = Math.min(this.grassAmount + this.grassAmount, HUNDRED);
    }

    public float getGrassAmount() {
        return grassAmount;
    }

    public void setGrassAmount(float grassAmountArg) {

        this.grassAmount = this.grassAmount - grassAmountArg <= 0 ? 0 : this.grassAmount - grassAmountArg;
    }
}
