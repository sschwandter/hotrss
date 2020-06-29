package org.shockfrosted.hotrss.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shockfrosted.hotrss.domain.NewsFeed;
import org.shockfrosted.hotrss.domain.NewsStory;
import org.shockfrosted.hotrss.domain.Topic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopicExtractorTest {

    TopicExtractor extractor;

    @Before
    public void setup() {
        extractor = new TopicExtractor();
    }

    @Test
    public void noTopicsFromNewsFeedsWithoutItems() {
        NewsFeed newsFeed1 = new NewsFeed(Collections.emptyList());
        NewsFeed newsFeed2 = new NewsFeed(Collections.emptyList());

        List<NewsFeed> newsFeeds = List.of(newsFeed1, newsFeed2);

        Assert.assertTrue(extractor.getTopics(newsFeeds).isEmpty());
    }

    @Test
    public void oneWordInThreeFeedsCreatesFrequencyThree() {
        List<NewsFeed> newsFeeds = createSampleFeeds();
        List<NewsStory> stories = createSampleStories();

        List<Topic> topics = extractor.getTopics(newsFeeds);

        Assert.assertEquals(List.of(new Topic("Trump", 3, stories)), topics);
    }

    private List<String> createSampleHeadlines() {
        return List.of("Trump is a good guy", "All well with Trump, White house officials say",
                "Trump trumps again");
    }

    private List<NewsStory> createSampleStories() {
        List<NewsStory> stories = new ArrayList<>();
        stories.add(new NewsStory("Trump is a good guy", "http://couldthisbetrue.com/item1"));
        stories.add(new NewsStory("All well with Trump", "http://donttrustthepress.com/item56"));
        stories.add(new NewsStory("Trump trumps again", "http://imnotserious.com/item99"));
        return stories;
    }

    private List<NewsFeed> createSampleFeeds() {
        List <NewsStory> stories = createSampleStories();

        NewsFeed newsFeed1 = new NewsFeed(List.of(stories.get(0)));
        NewsFeed newsFeed2 = new NewsFeed(List.of(stories.get(1)));
        NewsFeed newsFeed3 = new NewsFeed(List.of(stories.get(2)));

        return List.of(newsFeed1, newsFeed2, newsFeed3);
    }

}