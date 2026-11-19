package Strategy_Pattern.with_abstract.Fly;

public class NormalFly extends Flyable {

    @Override
    public void fly() {
        System.out.println("Flying with wings");
    }
}
