package Observer_Pattern.With;

public class Main {

    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        Observer aman = new Subscriber("Aman");
        Observer raman = new Subscriber("Raman");
        Observer sandeep = new Subscriber("Sandeep");

        // Subscribe
        channel.registerObserver(aman);
        channel.registerObserver(raman);
        channel.registerObserver(sandeep);

        // Upload a video
        channel.uploadVideo("Learn Design Patterns");

        // Aman unsubscribes
        channel.removeObserver(aman);

        // Upload another video
        channel.uploadVideo("Learn Java");
    }
}
