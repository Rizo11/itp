import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StringFunction exclaim = (s) -> s + "!";
        StringFunction ask = (s) -> s + "?";

        printFormatted("Hello", exclaim);
        printFormatted("Hello", ask);
        printFormatted("Me", (s) -> s + "?");

    }
    static void printFormatted(String str, StringFunction format) {
        String result = format.run(str);
        System.out.println(result);
    }
    interface StringFunction {
        String run(String str);
    }
}









class Main1 {
    public static void main1(String[] args) {

        System.out.println("Hello world!");

//        List<Integer> a = new List<Number>();

        System.out.println();

        Func fun = (int x, int y) -> {return  x + y; };

        MyPrinter p = s -> { System.out.println(s);};
    }

}
interface Func {
    int action(int x, int y);
}

interface MyPrinter {
    public void print(String s);
}

class MyList<T> {
    private List<T> dbForTAndDerived = new ArrayList<>();
    private List<T> dbForTAndSuper = new ArrayList<>();

    public void addAnotherList (ArrayList<? extends T> newList) {

        //add T or derived from T
        for (int i = 0; i < newList.size(); i++) {

        }
    }

    public void addAnotherList2 (List<? super T> newList) {
        //add T or supertype of T
    }
}

@FunctionalInterface
interface Consumer<T> {
    void accept(T t);
}

