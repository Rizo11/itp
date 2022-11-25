public class StringCalculator extends Calculator{

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
