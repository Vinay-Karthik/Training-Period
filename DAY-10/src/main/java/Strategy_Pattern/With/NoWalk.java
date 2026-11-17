package Strategy_Pattern.With;

class NoWalk implements Walkable {

    @Override
    public void walk() {
        System.out.println("Cannot walk");
    }
}
