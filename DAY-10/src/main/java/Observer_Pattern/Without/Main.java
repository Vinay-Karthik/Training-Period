package Observer_Pattern.Without;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== YouTube Channel (Without Observer Pattern) ===");
        YouTubeChannel channel = new YouTubeChannel();

        Subscriber aman = new Subscriber("Aman");
        Subscriber raman = new Subscriber("Raman");
        Subscriber sandeep = new Subscriber("Sandeep");

        channel.addSubscriber(aman);
        channel.addSubscriber(raman);
        channel.addSubscriber(sandeep);

        channel.uploadVideo("Learn Design Patterns");

        channel.removeSubscriber(aman);

        channel.uploadVideo("Learn Java");
    }
}
