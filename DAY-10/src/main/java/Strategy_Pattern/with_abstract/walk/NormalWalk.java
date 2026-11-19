package Strategy_Pattern.with_abstract.walk;

public class NormalWalk extends Walkable {

    @Override
    public void walk() {
        System.out.println("Walking normally");
    }
}
