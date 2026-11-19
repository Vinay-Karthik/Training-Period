package Strategy_Pattern.with_abstract.Fly;

public class JetFly extends Flyable {

    @Override
    public void fly() {
        System.out.println("Flying using a jet");
    }
}
