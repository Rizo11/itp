import java.util.ArrayList;

public class Demonstrate {
    public static void main(String[] args) {

        ArrayList<Creature> creatures = new ArrayList<>();
        Human h1 = new Human();
        Alien a1 = new Alien();
        Dog d1 = new Dog();
        ArrayList<String> names = new ArrayList<String>() {
            {
                add("mrizo");
                add("amrizo");
                add("dmrizo");

            }
        };
        creatures.add(h1);
        creatures.add(a1);
        creatures.add(d1);

        for (int i = 0; i < 3; i++) {
            creatures.get(i).bear(names.get(i));
            creatures.get(i).shoutName();
            creatures.get(i).die();

            if (creatures.get(i).isIns) {

            }
        }
    }
}

// ~ packatage
// # protected
// - private
// + public