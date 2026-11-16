package Web_Crawler;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class UrlStore {
    private final ConcurrentHashMap<String, Boolean> urlMap = new ConcurrentHashMap<>();
    private  final BlockingDeque<String> urlQueue = new LinkedBlockingDeque<>();

    public boolean addUrl(String url) {
         if(urlMap.putIfAbsent(url, true) == null){
             urlQueue.add(url);
             return true;
         }
         return false;
    }

    public String getUrl() {
        return urlQueue.poll();
    }

    public boolean isQueueEmpty() {
        return urlQueue.isEmpty();
    }
}
