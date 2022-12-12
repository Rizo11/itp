import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CalculatorType calculatorType = null;

        calculatorType = readCalculator();
        if(calculatorType == calculatorType.INCORRECT) {
            reportFatalError("Wrong calculator type");
            return;
        }

        Calculator calculator = null;
        switch (calculatorType) {
            case DOUBLE :
                calculator = new DoubleCalculator();
                break;
            case STRING:
                calculator = new StringCalculator();
                break;
            case INTEGER:
                calculator = new IntegerCalculator();
                break;
            default:
                break;
        }

        int commandsNumber = readCommandsNumber();
        if(commandsNumber == -1) {
            reportFatalError("Amount of commands is Not a Number");
            return;
        } else if(commandsNumber < 0) {
            return;
        }

        while (commandsNumber-- != 0) {
            Scanner reader = new Scanner(System.in);
            String type = reader.next();
            OperationType operationType = parseOperation(type);

            switch (operationType) {
                case ADDITION :
                    System.out.println(calculator.add(reader.next(), reader.next()));
                    break;
                case SUBTRACTION:
                    if (calculator.getClass().getName().compareTo("StringCalculator") == 0) {
                        reportFatalError("Unsupported operation for strings");
                        break;
                    }
                    System.out.println(calculator.subtract(reader.next(), reader.next()));
                    break;
                case MULTIPLICATION:
                    System.out.println(calculator.multiply(reader.next(), reader.next()));
                    break;
                case DIVISION:
                    if (calculator.getClass().getName().compareTo("StringCalculator") == 0) {
                        reportFatalError("Unsupported operation for strings");
                        break;
                    }
                    System.out.println(calculator.divide(reader.next(), reader.next()));
                    break;
                case INCORRECT:
                    reportFatalError("Wrong operation type");
                    break;
                default:
                    break;
            }
        }

    }

    private static CalculatorType readCalculator() {
        System.out.println("Enter the calculator type: ");
        Scanner reader = new Scanner(System.in);
        String type = reader.next();

        if (type.compareTo(CalculatorType.INTEGER.toString()) == 0) {
            return CalculatorType.INTEGER;
        } else if (type.compareTo(CalculatorType.DOUBLE.toString()) == 0) {
            return CalculatorType.DOUBLE;
        } else if (type.compareTo(CalculatorType.STRING.toString()) == 0) {
            return CalculatorType.STRING;
        } else {
            return CalculatorType.INCORRECT;
        }

    }

    private static int readCommandsNumber() {
        Scanner reader = new Scanner(System.in);
        String[] command = reader.nextLine().split(" ");

        // command[0].contains("-") MIGHT BE USELESS
        if(command.length > 1 || command[0].contains(".") || command[0].contains("-")) {
            return -1;
        }

        return Integer.parseInt(command[0]);
    }

    private static void reportFatalError(String error) {
        System.out.println(error);
    }

    private static OperationType parseOperation(String operation) {

        if (operation.compareTo("+") == 0) {
            return OperationType.ADDITION;
        } else if (operation.compareTo("-") == 0) {
            return OperationType.SUBTRACTION;
        } else if (operation.compareTo("*") == 0) {
            return OperationType.MULTIPLICATION;
        } else if (operation.compareTo("/") == 0) {
            return OperationType.DIVISION;
        } else {
            return OperationType.INCORRECT;
        }
    }

}
 abstract class Calculator {
    public abstract String add(String a, String b);
    public abstract String subtract(String a, String b);
    public abstract String multiply(String a, String b);
    public abstract String divide(String a, String b);
}
 enum CalculatorType {
    INTEGER {
        @Override
        public String toString() {
            return "INTEGER";
        }
    },
    DOUBLE {
        @Override
        public String toString() {
            return "DOUBLE";
        }
    },
    STRING {
        @Override
        public String toString() {
            return "STRING";
        }
    },
    INCORRECT {
        @Override
        public String toString() {
            return "INCORRECT";
        }
    }
}

 class DoubleCalculator extends Calculator{
    @Override
    public String add(String a, String b) {
        if(!isDouble(a, b)) {
            return "Wrong argument type";
        }
        double aDouble = Double.parseDouble(a);
        double bDouble = Double.parseDouble(b);
        double resultDouble = aDouble + bDouble;
        return Double.toString(resultDouble);
    }

    @Override
    public String subtract(String a, String b) {
        if(!isDouble(a, b)) {
            return "Wrong argument type";
        }
        double aDouble = Double.parseDouble(a);
        double bDouble = Double.parseDouble(b);
        double resultDouble = aDouble - bDouble;
        return Double.toString(resultDouble);
    }

    @Override
    public String multiply(String a, String b) {
        if(!isDouble(a, b)) {
            return "Wrong argument type";
        }
        double aDouble = Double.parseDouble(a);
        double bDouble = Double.parseDouble(b);
        double resultDouble = aDouble * bDouble;
        return Double.toString(resultDouble);
    }

    @Override
    public String divide(String a, String b) {
        if(!isDouble(a, b)) {
            return "Wrong argument type";
        }
        double aDouble = Double.parseDouble(a);
        double bDouble = Double.parseDouble(b);
        if(bDouble == 0) {
            return "Division by zero";
        }
        double resultDouble = aDouble / bDouble;
        return Double.toString(resultDouble);
    }

    public boolean isDouble(String a, String b) {
        try {
            Double.parseDouble(a);
            Double.parseDouble(b);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

 class IntegerCalculator extends Calculator{
    @Override
    public String add(String a, String b) {
        if(!isInteger(a, b)) {
            return "Wrong argument type";
        }
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        int resultInt = aInt + bInt;
        return Integer.toString(resultInt);
    }

    @Override
    public String subtract(String a, String b) {
        if(!isInteger(a, b)) {
            return "Wrong argument type";
        }
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        int resultInt = aInt - bInt;
        return Integer.toString(resultInt);
    }

    @Override
    public String multiply(String a, String b) {
        if(!isInteger(a, b)) {
            return "Wrong argument type";
        }
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        int resultInt = aInt * bInt;
        return Integer.toString(resultInt);
    }

    @Override
    public String divide(String a, String b) {
        if(!isInteger(a, b)) {
            return "Wrong argument type";
        }
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        if(bInt == 0) {
            return "Division by zero";
        }
        int resultInt = aInt / bInt;
        return Integer.toString(resultInt);
    }

    public boolean isInteger(String a, String b) {
        try {
            Integer.parseInt(a);
            Integer.parseInt(b);

            return !a.contains(".") && !b.contains(".");
        } catch (Exception e) {
            return false;
        }
    }
}
 enum OperationType {
    ADDITION {
        @Override
        public String toString() {
            return "ADDITION";
        }
    },
    SUBTRACTION {
        @Override
        public String toString() {
            return "SUBTRACTION";
        }
    },
    MULTIPLICATION {
        @Override
        public String toString() {
            return "MULTIPLICATION";
        }
    },
    DIVISION {
        @Override
        public String toString() {
            return "DIVISION";
        }
    },
    INCORRECT {
        @Override
        public String toString() {
            return "INCORRECT";
        }
    }
}
 class StringCalculator extends Calculator{

    /* adds two strings*/
    @Override
    public String add(String a, String b) {
        return a+b;
    }

    /* Unsupported operation for strings */
    // but this message must be returned by enums
    @Override
    public String subtract(String a, String b) {

        return "Unsupported operation for strings";
    }

    @Override
    public String multiply(String a, String b) {
        Integer intB = 0;

        // if b is not integer number return Wrong argument type
        // what about 0
        if(!isIntegerNumber(b)) {
            return "Wrong argument type";
        }
        intB = Integer.parseInt(b);
        if(intB == 0) {
            return "";
        }
        return a.repeat(intB);
    }

    // but this message must be returned by enums
    @Override
    public String divide(String a, String b) {
        return "Unsupported operation for strings";
    }

    /* Returns true is given string is positive integer, otherwise false */
    public static boolean isIntegerNumber(String input_str) {
        try {
            Integer.parseInt(input_str);

            // check where str is double or negative
            return !input_str.contains(".") && !input_str.contains("-");
        } catch(NumberFormatException e){
            return false;
        }
    }
}
