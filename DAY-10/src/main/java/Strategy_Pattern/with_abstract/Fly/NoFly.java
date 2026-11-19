package Strategy_Pattern.with_abstract.Fly;

public class NoFly extends Flyable {

    @Override
    public void fly() {
        System.out.println("Cannot fly");
    }
}
