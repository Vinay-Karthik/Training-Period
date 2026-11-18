package Observer_Pattern.Without;

import java.util.ArrayList;
import java.util.List;

// Without Observer Pattern: Tightly coupled to concrete Subscriber class
public class YouTubeChannel {
    private final List<Subscriber> subscribers = new ArrayList<>();
    private String latestVideo;

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadVideo(String videoTitle) {
        this.latestVideo = videoTitle;
        System.out.println("New video uploaded: " + videoTitle);
        // Direct coupling: Cannot notify any other type of listener/device without modifying this class
        for (Subscriber subscriber : subscribers) {
            subscriber.update(videoTitle);
        }
    }
}
