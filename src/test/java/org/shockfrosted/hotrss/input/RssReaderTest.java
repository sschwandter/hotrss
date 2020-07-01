package org.shockfrosted.hotrss.input;

import org.junit.Assert;
import org.junit.Test;
import org.shockfrosted.hotrss.model.NewsFeed;

import java.net.URL;

// TODO: this is an integration test, should not be run together with unit tests. And: should not rely on random server
// TODO: should be possible to run integration tests separately, and the test should spawn it's own http server
public class RssReaderTest {

    @Test
    public void canReadNewsFeed() throws Exception {
        RssReaderApptastic rssReader = new RssReaderApptastic();
        URL feedUrl = new URL("https://news.google.com/news?cf=all&hl=en&pz=1&ned=us&output=rss");
        NewsFeed feed = rssReader.readFeed(feedUrl);
        Assert.assertTrue(feed.getStories().size() > 0);
    }
}