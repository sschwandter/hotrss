package org.shockfrosted.hotrss.service;

import org.shockfrosted.hotrss.domain.NewsStory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class WordMetaData {

    public int getCount() {
        return count;
    }

    public List<NewsStory> getStories() {
        return stories;
    }

    private int count;
    private List<NewsStory> stories = new ArrayList<>();

    public WordMetaData(NewsStory firstStory) {
        count = 1;
        addStory(firstStory);
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
