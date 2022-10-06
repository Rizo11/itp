import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        FtoC();
    }

    public static void readCharprintAscii() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input a character: ");
        // reading a character
        char c = sc.next().charAt(0);
        //prints the character
        System.out.println("You have entered "+ (int)c);
    }

    public static void printLAphabet() {
        // method 1
//        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        for (int letter: alphabet.toCharArray()) {
//            System.out.println(letter);
//        }
        // method 2
        for (int i = 'a'; i <= 'z'; i++) {
            System.out.println(i);
        }
    }

    public static void Compare() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input a character: ");
        String str1 = sc.next();
        System.out.print("Input next character: ");
        String str2 = sc.next();

        if(str1.equals(str2)) {
            System.out.print("Equal");
        } else {
            System.out.println("Not equal");
        }
    }

    public static void CountVovels() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input a character: ");
        String str1 = sc.next().toLowerCase();

        int counter = 0;
        for (int i: str1.toCharArray()) {
            if(i == 'a'  || i == 'e' || i == 'u' || i == 'o' || i == 'i') {
                counter++;
            }
        }

        System.out.println(str1.replaceAll("(?i)[^aieou]", "").length());
    }

    public static void FtoC(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input a temerature: ");
        double t = sc.nextDouble();
        Temperature tmp = new Temperature(t);
        System.out.println(tmp.convert());
    }
}

class Temperature {
    private double temp;
    public Temperature(double t) {
        this.temp = t;
    }

    public double convert() {
        return (temp-35)/9;
    }
}