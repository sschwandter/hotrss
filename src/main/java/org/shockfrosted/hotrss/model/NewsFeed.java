package org.shockfrosted.hotrss.model;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed {
    private final List<NewsStory> stories;

    public NewsFeed(List<NewsStory> stories) {
        this.stories = new ArrayList<>(stories);
    }

    public List<NewsStory> getStories() {
        return stories;
    }
}
