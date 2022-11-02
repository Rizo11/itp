import static java.lang.Math.PI;

public class Main {
    public static void main(String[] args) {
        // Animal
//        Animal cow=new Cow("soso",78,666,"black");
//        cow.Eat("grass");
//        Animal animal=new Animal(
//                "unk",0,0,"koko");
//
//        animal.Eat("kij");
    }
}

abstract class IShape {
   abstract double area();
   abstract   double perimeter();
}

class circle extends IShape {

    int a;
    int b;
    int ar
    @Override
    double area() {

        return 0;
    }

    @Override
    double perimeter() {
        return 0;
    }
}



class Animal {
    String name;
    int weight;
    int height;
    public String color;

    public Animal(String name,int height, int weight, String color) {
        this.name = name;
        this.height=height;
        this.weight = weight;
        this.color = color;
    }



    public void Eat(String food) {
        System.out.println("I eat " + food);
    }
    public void Sleep() {
        System.out.println("I sleep");
    }
    public void makeSound(String noice) {
        System.out.println("I " + noice);
    }
}

class Cow extends Animal {
    public Cow(String name,int height, int weight, String color) {
        super(name,height, weight, color);
    }
    public void Eat(String food) {
        System.out.println("the cow is eating " + food);
    }
}