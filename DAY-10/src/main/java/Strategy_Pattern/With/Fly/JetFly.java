package Strategy_Pattern.With.Fly;

public class JetFly implements Flyable {

    @Override
    public void fly() {
        System.out.println("Flying using a jet");
    }
}
