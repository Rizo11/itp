public class IntegerCalculator extends Calculator{
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
