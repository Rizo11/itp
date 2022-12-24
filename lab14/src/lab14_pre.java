import java.util.Scanner;
import java.util.function.*;

public class lab14_pre {
  public static void main(String[] args) {
      Predicate<String> checkValidityOfName = (String name) -> {
          if(name.isEmpty() || name.isBlank() || name.length() >= 13) {
              return false;
          } else if (name.charAt(0) < 'A' || name.charAt(0) > 'Z') {
              return false;
          }
          return true;
      };
      Scanner scanner = new Scanner(System.in);
      String name = scanner.nextLine();
      if(checkValidityOfName.test(name)) {
            System.out.println(name);
      } else {
            System.out.println("Invalid name");
      }

  }
}