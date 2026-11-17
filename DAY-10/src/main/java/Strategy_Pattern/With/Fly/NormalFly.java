package Strategy_Pattern.With.Fly;

public class NormalFly implements Flyable {

    @Override
    public void fly() {
        System.out.println("Flying with wings");
    }
}
