public enum Drinks {
    COKACOLA(10, "Coke-Cola"),
    SPRITE(10, "Sprite"),
    FANTA(10, "Fanta");

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    private final int price;
    private final String name;

    Drinks(int price, String name) {
        this.price = price;
        this.name = name;
    }
}
