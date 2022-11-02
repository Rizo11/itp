public class Main {
    public static void main(String[] args) {
        Animal cow=new Cow("soso",78,666,"black");
        cow.Eat("grass");
    }
}

class Animal {
    public String name;
    public int weight;
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