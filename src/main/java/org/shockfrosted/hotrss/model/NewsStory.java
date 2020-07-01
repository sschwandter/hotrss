package org.shockfrosted.hotrss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class NewsStory {
    @Id
    @GeneratedValue
    private Long id;

    private String headline;
    @Column(length = 2000)
    private String link;

    public NewsStory() {
    }

    public NewsStory(String headline, String link) {
        this.headline = headline;
        this.link = link;
    }

    public String getHeadline() {
        return headline;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsStory newsStory = (NewsStory) o;
        return headline.equals(newsStory.headline) &&
                link.equals(newsStory.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, link);
    }

    @Override
    public String toString() {
        return "NewsStory{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
