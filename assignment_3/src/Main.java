import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CalculatorType calculatorType = null;

        calculatorType = readCalculator();
        if(calculatorType == calculatorType.INCORRECT) {
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
