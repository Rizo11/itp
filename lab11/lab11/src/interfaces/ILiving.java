package interfaces;

public interface ILiving {
    default void live() {
        System.out.println("I " + this.getClass().getName() + " live");
    }
}
