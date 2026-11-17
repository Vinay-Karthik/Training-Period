package Strategy_Pattern.With.Fly;

public class NoFly implements Flyable {

    @Override
    public void fly() {
        System.out.println("Cannot fly");
    }
}
