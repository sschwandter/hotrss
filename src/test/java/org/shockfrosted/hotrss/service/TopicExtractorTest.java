package org.shockfrosted.hotrss.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shockfrosted.hotrss.model.NewsFeed;
import org.shockfrosted.hotrss.model.NewsStory;
import org.shockfrosted.hotrss.model.Topic;

import java.util.List;
import java.util.Set;

public class TopicExtractorTest {

    TopicExtractor extractor;

    @Before
    public void setup() {
        extractor = new TopicExtractor(Set.of("is"));
    }

    @Test
    public void oneWordInThreeFeedsCreatesTopicWithFrequencyThree() {
        //Given
        List<NewsStory> stories = List.of(
                new NewsStory("Trump is a good guy", "http://couldthisbetrue.com/item1"),
                new NewsStory("All is well with Trump", "http://donttrustthepress.com/item56"),
                new NewsStory("Trump trumps again", "http://imnotserious.com/item99"));

        NewsFeed newsFeed1 = new NewsFeed(List.of(stories.get(0)));
        NewsFeed newsFeed2 = new NewsFeed(List.of(stories.get(1)));
        NewsFeed newsFeed3 = new NewsFeed(List.of(stories.get(2)));

        List<NewsFeed> newsFeeds = List.of(newsFeed1, newsFeed2, newsFeed3);

        //When
        Set<Topic> topics = extractor.getTopics(newsFeeds);

        //Then
        Assert.assertEquals(Set.of(new Topic("Trump", 3, stories)), topics);
    }

    @Test
    public void differentWordCasesCreateSameTopic() {
        //Given
        NewsStory story1 = new NewsStory("Microservices are everywhere", "http://couldthisbetrue.com/item1");
        NewsStory story2 = new NewsStory("Everywhere is where I want to be says Germans next topmodel",
                "http://donttrustthepress.com/item56");
        NewsStory story3 = new NewsStory("Trump trumps again", "http://imnotserious.com/item99");
        List<NewsFeed> newsFeeds = List.of(new NewsFeed(List.of(story1)), new NewsFeed(List.of(story2)),
                new NewsFeed(List.of(story3)));

        //When
        Set<Topic> topics = extractor.getTopics(newsFeeds);

        //Then
        Assert.assertEquals(Set.of(new Topic("Everywhere", 2, List.of(story1, story2))), topics);
    }

    @Test
    public void newsFeedsWithoutItemsCreateNoTopics() {
        //Given
        NewsFeed newsFeed1 = new NewsFeed(List.of());
        NewsFeed newsFeed2 = new NewsFeed(List.of());
        List<NewsFeed> newsFeeds = List.of(newsFeed1, newsFeed2);

        //When
        Set<Topic> topics = extractor.getTopics(newsFeeds);

        //Then
        Assert.assertTrue(topics.isEmpty());
    }

    @Test
    public void emptyHeadlinesCauseNoProblem() {
        //Given
        NewsFeed newsFeed1 = new NewsFeed(List.of(
                new NewsStory("", "http://www.google.com")));
        NewsFeed newsFeed2 = new NewsFeed(List.of(
                new NewsStory("Other headline", "http://www.google.com")));
        List<NewsFeed> newsFeeds = List.of(newsFeed1, newsFeed2);

        //When
        Set<Topic> topics = extractor.getTopics(newsFeeds);

        //Then
        Assert.assertTrue(topics.isEmpty());
    }

    @Test
    public void emptyUrlsCauseNoProblem() {
        //Given
        NewsFeed newsFeed1 = new NewsFeed(List.of(
                new NewsStory("A headline", "")));
        NewsFeed newsFeed2 = new NewsFeed(List.of(
                new NewsStory("Other", "http://www.google.com")));
        List<NewsFeed> newsFeeds = List.of(newsFeed1, newsFeed2);

        //When
        final Set<Topic> topics = extractor.getTopics(newsFeeds);

        //Then
        Assert.assertTrue(topics.isEmpty());
    }
}