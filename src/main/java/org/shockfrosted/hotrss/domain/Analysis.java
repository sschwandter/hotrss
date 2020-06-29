package org.shockfrosted.hotrss.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Analysis {

    @Id
    @GeneratedValue
    private Long id;
    private List<Topic> topics;

    public Long getId() {
        return id;
    }
}
