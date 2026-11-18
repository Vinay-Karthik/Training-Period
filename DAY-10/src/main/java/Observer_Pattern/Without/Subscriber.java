package Observer_Pattern.Without;

public class Subscriber {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String videoTitle) {
        System.out.println(name + " received notification: New video uploaded - " + videoTitle);
    }

    public String getName() {
        return name;
    }
}
