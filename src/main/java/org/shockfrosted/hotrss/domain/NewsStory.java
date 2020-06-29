package org.shockfrosted.hotrss.domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class NewsStory {
    final private String headline;
    final private String link;

    public NewsStory(String headline, String link) {
        this.headline = headline;
        this.link = link;
    }

    public String getHeadline() {
        return headline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsStory newsStory = (NewsStory) o;
        return headline.equals(newsStory.headline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline);
    }
}
