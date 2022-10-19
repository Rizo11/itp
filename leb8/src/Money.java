public enum Money {
    HUNDRED(10),
    TWO_HUNDRED(200),
    FIVE_THOUSAND(5000);

    private final int denomination;

    Money(int denomination) {
        this.denomination = denomination;
    }
}
