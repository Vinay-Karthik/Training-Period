package Web_Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UrlFetcher {
    public Set<String> fetchUrl(String url){
        Set<String> urls = new HashSet<>();
        Document document = null;
        try {
            document= Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(document.text());
        Elements elements = document.select("a[href]");
        for (Element element : elements) {
            urls.add(element.absUrl("href"));

        }
        System.out.println(urls);
        return urls;
    }
}
