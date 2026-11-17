package Strategy_Pattern.With;

class NoFly implements Flyable {

    @Override
    public void fly() {
        System.out.println("Cannot fly");
    }
}
