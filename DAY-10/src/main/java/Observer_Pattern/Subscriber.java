package Observer_Pattern;

class Subscriber implements Observer {

    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println("Hello " + name + ", new video uploaded: " + videoTitle);
    }
}
