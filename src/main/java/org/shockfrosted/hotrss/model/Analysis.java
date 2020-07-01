package org.shockfrosted.hotrss.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Analysis {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "analysis_id")
    private Set<Topic> topics;

    public Analysis() {
    }

    public Analysis(Set<Topic> topics) {
        this.topics = topics;
    }

    public Long getId() {
        return id;
    }

    public Set<Topic> getTopics() {
        return topics;
    }
}
