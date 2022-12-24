import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        unary();

    }

    public static void unary() {
        UnaryOperator<Integer> square = (num) -> num * num;

        List<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(2);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(4);
        nums.add(5);
        nums.add(6);
        nums.add(6);

        nums = nums.stream()
                .map(n -> square.apply(n))
                .filter(n -> n > 10)
                .distinct()
                .toList();

        nums.forEach((num) -> square.apply(num));
        nums.forEach(System.out::println);
    }
    enum DocType {
        PASSPORT(true), SNILS(false), OTHER(false);
        boolean isFixedTerm;
    
        DocType(boolean b) {
            isFixedTerm = b;
        }
    }
    public void predicate() {
        Predicate<String> nameValidator = (name) -> {
            if (name.length() >= 13) {
                return false;
            }
            return true;
        };

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if (nameValidator.test(name)) {
            System.out.println("Valid name: " + name);
        } else {
            System.out.println("Not valid name");
        }
    }
}
