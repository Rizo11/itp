import java.util.Scanner;

public class Main {

    /**
     * used for reading input from console.
     */
    private static final Scanner reader = new Scanner(System.in);

    /**
     * maximum number of commands.
     */
    private static final int MAXCOMMANDCOUNT = 51;

    /**
     * maximum number of expressions per line.
     */
    private static final int MAXEXPRESSIONCOUNT = 3;

    public static void main(String[] args) {

        CalculatorType calculatorType = null;

        // read calculator type
        calculatorType = readCalculator();
        if (calculatorType == CalculatorType.INCORRECT) {
            reportFatalError("Wrong calculator type");
        }


        // choose calculator
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


        // read number of commands
        int commandsNumber = readCommandsNumber();
        if (commandsNumber == -1) {
            reportFatalError("Amount of commands is Not a Number");
        } else if (commandsNumber < 0 || commandsNumber > MAXCOMMANDCOUNT) {
            return;
        }

        // read expressions
        while (commandsNumber-- != 0) {

            String[] expression = reader.nextLine().split(" ");

            OperationType operationType = parseOperation(expression[0]);

            if (expression.length != MAXEXPRESSIONCOUNT) {
                System.out.println("Wrong operation type");
                continue;
            }
            String result = null;

            switch (operationType) {
                case ADDITION :
                    result = calculator.add(expression[1], expression[2]);
                    System.out.println(result);
                    break;

                case SUBTRACTION :
                    if (calculator.getClass().getName().compareTo("StringCalculator") == 0) {
                        System.out.println("Unsupported operation for strings");
                        break;
                    }
                    result = calculator.subtract(expression[1], expression[2]);
                    System.out.println(result);
                    break;
                case MULTIPLICATION :
                    result = calculator.multiply(expression[1], expression[2]);
                    System.out.println(result);
                    break;
                case DIVISION :
                    if (calculator.getClass().getName().compareTo("StringCalculator") == 0) {
                        System.out.println("Unsupported operation for strings");
                        break;
                    }
                    result = calculator.divide(expression[1], expression[2]);
                    System.out.println(result);
                    break;
                case INCORRECT :
                    System.out.println("Wrong operation type");
                    break;
                default :
                    break;
            }
        }

    }

    /**  reads from console type of the calculators [INTEGER, DOUBLE, STRING, INCORRECT (for incorrect inputs)].
     * @return : CalculatorType
     * */
    private static CalculatorType readCalculator() {
        String type = reader.nextLine();

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


    /** reads from console the number of commands to be calculated for given calculator.
     * @return nOfCommands int
     * */
    private static int readCommandsNumber() {
        String[] command = reader.nextLine().split(" ");


        if (command.length > 1 || command[0].contains(".") || command[0].contains("-")) {
            return -1;
        }
        try {
            Integer.parseInt(command[0]);
        } catch (Exception e) {
            return -1;
        }

        return Integer.parseInt(command[0]);
    }


    /**
     * prints the given <i>error</i> to console and exits the program with 0 exit code.
     * @param error String
     */
    private static void reportFatalError(String error) {
        System.out.println(error);
        System.exit(0);
    }


    /** parses given String <i>operation</i> to OperationType.
     *
     * @param operation String
     * @return OperationType
     */
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


/**
 * abstract class <i>Calculator</i>.
 * <ul>
 *     <li>abstract String add(String a, String b);</li>
 *     <li>abstract String subtract(String a, String b);</li>
 *     <li>abstract String multiply(String a, String b);</li>
 *     <li>abstract String divide(String a, String b);</li>
 * </ul>
 */
 abstract class Calculator {
    public abstract String add(String a, String b);
    public abstract String subtract(String a, String b);
    public abstract String multiply(String a, String b);
    public abstract String divide(String a, String b);
}


/**
 * contains enum of calculator types.
 * <ul>
 *     <li>INTEGER</li>
 *     <li>DOUBLE</li>
 *     <li>STRING</li>
 *     <li>INCORRECT</li>
 * </ul>
 */
enum CalculatorType {
    /**
     * calculator for integer expressions.
     */
    INTEGER {
        @Override
        public String toString() {
            return "INTEGER";
        }
    },

    /**
     * calculator for double expressions.
     */
    DOUBLE {
        @Override
        public String toString() {
            return "DOUBLE";
        }
    },

    /**
     * calculator for string expressions.
     */
    STRING {
        @Override
        public String toString() {
            return "STRING";
        }
    },

    /**
     * incorrect calculator type.
     */
    INCORRECT {
        @Override
        public String toString() {
            return "INCORRECT";
        }
    }
}


/**
 * used to operations with doubles.
 * <ul>
 *     <li>add</li>
 *     <li>subtract</li>
 *     <li>multiply</li>
 *     <li>divide</li>
 *     <li>division by 0 is handled</li>
 * </ul>
 */
 class DoubleCalculator extends Calculator {

    /**
     * @param a String double
     * @param b String double
     * @return a + b String double
     */
    @Override
    public String add(String a, String b) {
        if (isDouble(a, b)) {
            return "Wrong argument type";
        }
        double aDouble = Double.parseDouble(a);
        double bDouble = Double.parseDouble(b);
        double resultDouble = aDouble + bDouble;
        return Double.toString(resultDouble);
    }


    /**
     *
     * @param a String double
     * @param b String double
     * @return a - b String double
     */
    @Override
    public String subtract(String a, String b) {
        if (isDouble(a, b)) {
            return "Wrong argument type";
        }
        double aDouble = Double.parseDouble(a);
        double bDouble = Double.parseDouble(b);
        double resultDouble = aDouble - bDouble;
        return Double.toString(resultDouble);
    }


    /**
     * @param a String double
     * @param b String double
     * @return a * b String double
     */
    @Override
    public String multiply(String a, String b) {
        if (isDouble(a, b)) {
            return "Wrong argument type";
        }
        double aDouble = Double.parseDouble(a);
        double bDouble = Double.parseDouble(b);
        double resultDouble = aDouble * bDouble;
        return Double.toString(resultDouble);
    }


    /**
     * @param a String double
     * @param b String double
     * @return a / b String double
     * @note division by 0 is handled
     */
    @Override
    public String divide(String a, String b) {
        if (isDouble(a, b)) {
            return "Wrong argument type";
        }
        double aDouble = Double.parseDouble(a);
        double bDouble = Double.parseDouble(b);
        if (bDouble == 0) {
            return "Division by zero";
        }
        double resultDouble = aDouble / bDouble;
        return Double.toString(resultDouble);
    }


    /**
     * @param a String double
     * @param b String double
     * @return <i>true</i> if <i>a</i> and <i>b</i> are double, else <i>false</i>
     */
    public boolean isDouble(String a, String b) {
        try {
            Double.parseDouble(a);
            Double.parseDouble(b);

            return false;
        } catch (Exception e) {
            return true;
        }
    }
}


/**
 * used to operations with integers.
 * <ul>
 *     <li>add</li>
 *     <li>subtract</li>
 *     <li>multiply</li>
 *     <li>divide</li>
 *     <li>division by 0 is handled</li>
 * </ul>
 */
 class IntegerCalculator extends Calculator {

    /**
     * @param a String int
     * @param b String int
     * @return a + b
     */
    @Override
    public String add(String a, String b) {
        if (isInteger(a, b)) {
            return "Wrong argument type";
        }
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        int resultInt = aInt + bInt;
        return Integer.toString(resultInt);
    }


    /**
     * @param a String int
     * @param b String int
     * @return a - b String int
     */
    @Override
    public String subtract(String a, String b) {
        if (isInteger(a, b)) {
            return "Wrong argument type";
        }
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        int resultInt = aInt - bInt;
        return Integer.toString(resultInt);
    }


    /**
     * @param a String int
     * @param b String int
     * @return a*b String int
     */
    @Override
    public String multiply(String a, String b) {
        if (isInteger(a, b)) {
            return "Wrong argument type";
        }
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        int resultInt = aInt * bInt;
        return Integer.toString(resultInt);
    }


    /**
     * @param a String int
     * @param b String int
     * @return result of a/b String int
     * @note division by 0 is taken into account
     */
    @Override
    public String divide(String a, String b) {
        if (isInteger(a, b)) {
            return "Wrong argument type";
        }
        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        if (bInt == 0) {
            return "Division by zero";
        }
        int resultInt = aInt / bInt;
        return Integer.toString(resultInt);
    }


    /**
     * @param a String int
     * @param b String int
     * @return <i>true</i> if <i>a</i> and <i>b</i> are integers, otherwise <i>false</i>
     */
    public boolean isInteger(String a, String b) {
        try {
            Integer.parseInt(a);
            Integer.parseInt(b);

            return a.contains(".") || b.contains(".");
        } catch (Exception e) {
            return true;
        }
    }
}


/***
 * Operation signs [+, -, *, /] as enums.
 */
 enum OperationType {
     /** +. */
    ADDITION {
        @Override
        public String toString() {
            return "ADDITION";
        }
    },
     /** -. */
    SUBTRACTION {
        @Override
        public String toString() {
            return "SUBTRACTION";
        }
    },
     /** *. */
    MULTIPLICATION {
        @Override
        public String toString() {
            return "MULTIPLICATION";
        }
    },
     /** /. */
    DIVISION {
        @Override
        public String toString() {
            return "DIVISION";
        }
    },
     /** anything which is not * or + or - or /.*/
    INCORRECT {
        @Override
        public String toString() {
            return "INCORRECT";
        }
    }
}


/**
 * used to operations with strings.
 * <ul>
 *     <li>add</li>
 *     <li></li>
 *     <li>multiply string by int</li>
 *     <li></li>
 * </ul>
 */
 class StringCalculator extends Calculator {

    /**
     * @param a String
     * @param b  intString
     * @return <i>a</i> concatenated with <i>b</i>
     */
    @Override
    public String add(String a, String b) {
        return a + b;
    }


    /** Unsupported operation for strings.*/
    @Override
    public String subtract(String a, String b) {

        return "Unsupported operation for strings";
    }


    /**
     * @param a String
     * @param b  intString
     * @return <i>a</i> repeated <i>b</i> times
     */
    @Override
    public String multiply(String a, String b) {
        int intB = 0;

        if (!isIntegerNumber(b)) {
            return "Wrong argument type";
        }
        intB = Integer.parseInt(b);
        if (intB == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < intB; i++) {
            result.append(a);
        }
        return result.toString();
    }


    /**
     * Unsupported Operation for strings.
     * @param a String
     * @param b String
     * @return String
     */
    @Override
    public String divide(String a, String b) {
        return "Unsupported operation for strings";
    }


    /**
     * returns true if number <i> intStr</i> is positive integer.
     * @param  intStr String
     * @return boolean
     */
    public static boolean isIntegerNumber(String intStr) {
        try {
            Integer.parseInt(intStr);

            // check where str is double or negative
            return !intStr.contains(".") && !intStr.contains("-");
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
