package org.shockfrosted.hotrss.service;

import org.shockfrosted.hotrss.domain.NewsFeed;
import org.shockfrosted.hotrss.domain.NewsStory;
import org.shockfrosted.hotrss.domain.Topic;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TopicExtractor {

    /**
     * Returns list of topics that are contained in a list of news feeds. A topic is a word that appears at least
     * twice in the headlines of the news items in the news feeds.
     *
     * @param newsFeeds list of news feeds
     * @return          list of topics
     */
    public List<Topic> getTopics(List<NewsFeed> newsFeeds) {

        Map<String,WordMetaData> wordCounts = getWordCounts(newsFeeds);

        List<Topic> topics = new ArrayList<>();
        for (Map.Entry<String,WordMetaData> entry : wordCounts.entrySet()) {
            final int count = entry.getValue().getCount();
            if (count > 1) {
                topics.add(new Topic(entry.getKey(), count, entry.getValue().getStories()));
            }
        }
        return topics;
    }

    Map<String,WordMetaData> getWordCounts(List<NewsFeed> feeds) {
        Map<String,WordMetaData> words = new HashMap<>();
        for (var feed : feeds) {
            for (var story : feed.getStories()) {
                for (var word : splitIntoWords(story.getHeadline())) {
                    final WordMetaData wordMetaData = words.get(word);
                    if (wordMetaData !=null) {
                        wordMetaData.increaseCount();
                        wordMetaData.addStory(story);
                    } else {
                        words.put(word, new WordMetaData(story));
                    }
                }
            }
        }
        return words;
    }

    private String[] splitIntoWords(String line) {
        return line.split("\\W");
    }
}
