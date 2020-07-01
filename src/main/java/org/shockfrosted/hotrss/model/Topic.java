package org.shockfrosted.hotrss.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Topic {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private int frequency;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<NewsStory> stories;

    public Topic() {
    }

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

    public List<NewsStory> getStories() {
        return this.stories;
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

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", frequency=" + frequency +
                ", title='" + title + '\'' +
                ", stories=" + stories +
                '}';
    }
}
