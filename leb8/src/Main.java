import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }

    public static void printMenu() {
        System.out.println("[0] exit");
        System.out.println("[1] print current string");
        System.out.println("[2] append string");
        System.out.println("[3] insert the string to the current");
        System.out.println("[4] reverse current string");
        System.out.println("[5] delete substring from the current string");
        System.out.println("[6] replace substring int the current string");
        System.out.println("Enter a number: ");
    }

    public static void task1() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        StringBuilder str = new StringBuilder("");
        while (true) {
            printMenu();
            int n = reader.nextInt();

            if(n == 0) break;
            else if (n == 1) {
                System.out.println(str);
            } else if (n == 2) {
                String s = reader.next();
                str.append(s);
            } else if (n == 3) {
                int offset = reader.nextInt();
                String s = reader.next();
                str.insert(offset, s);
            } else if (n == 4) {
                str.reverse();
            } else if (n == 5) {
                int start = 0, end = 0;
                start = reader.nextInt();
                end = reader.nextInt();
                str.delete(start, end);
            } else if (n == 6) {
                int start = reader.nextInt();
                int end = reader.nextInt();
                String newStr = reader.next();
                str.replace(start, end, newStr);
            } else {
                System.out.println("Invalid input");
            }
        }
    }

}

class Task2 {
    int n_of_coke = 10;
    int n_of_fanta = 10;
    int n_of_sprite = 10;

    int choice = 0;
    while(true) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        for (Drinks i : Drinks.values()) {
            System.out.println(i.getName() + " " + i.getPrice());
        }

        choice = reader.nextInt();

        switch (choice) {
            case 1:
                getMoney();
        }
    }

    getMoney()
}

