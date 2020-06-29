package org.shockfrosted.hotrss.input;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import org.shockfrosted.hotrss.domain.NewsFeed;
import org.shockfrosted.hotrss.domain.NewsStory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RssReaderApptastic {

    final RssReader rssReader = new RssReader();

    public NewsFeed readFeed(URL url) throws IOException {
        final Stream<Item> items = rssReader.read(url.toExternalForm());
        List<NewsStory> stories = items
                .filter(item -> item.getTitle().isPresent())
                .filter(item -> item.getLink().isPresent())
                .map(item -> new NewsStory(item.getTitle().get(), item.getLink().get()))
                .collect(Collectors.toList());
        return new NewsFeed(stories);
    }

}
