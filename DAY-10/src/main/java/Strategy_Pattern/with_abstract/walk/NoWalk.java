package Strategy_Pattern.with_abstract.walk;

public class NoWalk extends Walkable {

    @Override
    public void walk() {
        System.out.println("Cannot walk");
    }
}
