package org.shockfrosted.hotrss.domain;

import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Entity
public class Topic {

    final private int frequency;
    final private String title;
    final private List<NewsStory> stories;

    public Topic(String title, int frequency, List<NewsStory> stories) {
        this.frequency = frequency;
        this.title = title;
        this.stories = stories;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return frequency == topic.frequency &&
                title.equals(topic.title) &&
                stories.equals(topic.stories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency, title, stories);
    }

    public List<String> getHeadlines() {
        return stories.stream()
                .map(NewsStory::getHeadline)
                .collect(toList());
    }

}
