package org.shockfrosted.hotrss.service;

import org.shockfrosted.hotrss.model.NewsFeed;
import org.shockfrosted.hotrss.model.NewsStory;
import org.shockfrosted.hotrss.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TopicExtractor {

    private final Set<String> stopWords;

    public TopicExtractor(@Qualifier("stopwords") Set<String> stopWords) {
        this.stopWords = stopWords;
    }

    /**
     * Returns list of topics that are contained in a list of news feeds. A topic is a word that appears at least
     * twice in the headlines of the news items in the news feeds. Topics are normalized to have an initial uppercase
     * letter followed by only lowercase letters.
     *
     * @param newsFeeds list of news feeds
     * @return list of topics
     */
    public Set<Topic> getTopics(List<NewsFeed> newsFeeds) {

        Map<String, WordMetaData> wordCounts = getWordCounts(newsFeeds);

        return wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue().getCount() > 1)
                .map(entry -> new Topic(entry.getKey(), entry.getValue().getCount(), entry.getValue().getStories()))
                .collect(Collectors.toSet());
    }

    private Map<String, WordMetaData> getWordCounts(List<NewsFeed> feeds) {
        final Map<String, WordMetaData> words = new HashMap<>();
        for (var feed : feeds) {
            for (var story : feed.getStories()) {
                for (var word : getNormalizedWordsFromHeadline(story)) {
                    final WordMetaData wordMetaData = words.get(word);
                    if (wordMetaData != null) {
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

    /**
     * Filter out stop words and normalize different word casings
     */
    private String[] getNormalizedWordsFromHeadline(NewsStory story) {
        final String[] headline = splitIntoWords(story.getHeadline());
        return Arrays
                .stream(headline)
                .map(this::firstLetterUppercaseRestLowercase)
                .filter(word -> !stopWords.contains(word.toLowerCase()))
                .toArray(String[]::new);
    }

    private String[] splitIntoWords(String line) {
        return "".equals(line) ? new String[0] : line.split("\\W+");
    }

    private String firstLetterUppercaseRestLowercase(String word) {
        return "".equals(word) ? "" : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }

    private static class WordMetaData {

        private int count;
        private List<NewsStory> stories = new ArrayList<>();

        public WordMetaData(NewsStory firstStory) {
            count = 1;
            addStory(firstStory);
        }

        public int getCount() {
            return count;
        }

        public List<NewsStory> getStories() {
            return stories;
        }

        public void increaseCount() {
            count++;
        }

        public void addStory(NewsStory story) {
            stories.add(story);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WordMetaData that = (WordMetaData) o;
            return count == that.count &&
                    stories.equals(that.stories);
        }

        @Override
        public int hashCode() {
            return Objects.hash(count, stories);
        }
    }
}
