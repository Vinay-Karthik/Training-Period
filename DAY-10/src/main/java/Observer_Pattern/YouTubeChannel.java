package Observer_Pattern;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel implements Subject {

    private final List<Observer> subscribers = new ArrayList<>();

    private String latestVideo;

    @Override
    public void registerObserver(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : subscribers) {
            observer.update(latestVideo);
        }
    }

    public void uploadVideo(String videoTitle) {
        this.latestVideo = videoTitle;
        System.out.println("New video uploaded: " + videoTitle);
        notifyObservers();
    }
}
