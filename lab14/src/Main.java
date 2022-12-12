import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        exercise2();
    }

    public static void EnumMapper() {
        ArrayList<String> colorsStr = new ArrayList<>();
        colorsStr.add("BLACK");
        colorsStr.add("BLUE");
        colorsStr.add("RED");
        colorsStr.add("GREEN");
        colorsStr.add("WHITE");
        colorsStr.add("DEFAULT");

//        colorsStr.stream().map(c -> strToEnum(c))
    }

    public static Colors strToEnum(String str) {
        if(str.equals("RED")) {
            return Colors.RED;
        } else if(str.equals("BLACK")) {
            return Colors.BLACK;
        } else if(str.equals("BLUE")) {
            return Colors.BLUE;
        } else if(str.equals("GREEN")) {
            return Colors.GREEN;
        } else if(str.equals("WHITE")) {
            return Colors.WHITE;
        } else {
            return Colors.DEFAULT;
        }
    }

    public static void exercise1() {
        List<Integer> nums = new ArrayList<>() {};

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            nums.add(random.nextInt(-100, 100));
        }

        nums.stream()
                .filter(n -> n%3 == 0)
                .map(n -> Math.abs(n))
                .forEach(n -> System.out.println(n));

        nums.stream().filter(n -> n%3 == 0).forEach(n -> System.out.println(n));
    }
    public static void exercise2() {
        ArrayList<String> strs = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < rnd.nextInt(30); i++) {

            int strLength = rnd.nextInt(20);
            StringBuilder rndStr = new StringBuilder();
            for (int j = 0; j < strLength; j++) {
                int[][] range = new int[][] {
                    {48, 57},
                    {65, 90},
                    {97, 122}
                };
                int rangeInd = rnd.nextInt(0, 2);

                int newChar = rnd.nextInt(range[rangeInd][0], range[rangeInd][1]);
                rndStr.append((char) newChar);
            }

            strs.add(rndStr.toString());
            strs.add(rndStr.toString());
        }
        strs.stream()
                .distinct()
                .filter(c -> c.matches("[a-zA-z]+"))
                .sorted()
                .forEach(s -> System.out.println(s));
    }


}

class Counter{
    private int counter = 0;
    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
enum Colors {
    RED,
    GREEN,
    BLACK,
    BLUE,
    WHITE,
    DEFAULT
}
