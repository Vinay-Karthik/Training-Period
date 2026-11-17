package Strategy_Pattern.With.walk;

public class NoWalk implements Walkable {

    @Override
    public void walk() {
        System.out.println("Cannot walk");
    }
}
