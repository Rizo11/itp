public class DoubleCalculator extends Calculator{
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
