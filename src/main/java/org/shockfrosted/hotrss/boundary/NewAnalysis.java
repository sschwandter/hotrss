package org.shockfrosted.hotrss.boundary;


import org.shockfrosted.hotrss.domain.Analysis;
import org.shockfrosted.hotrss.domain.NewsFeed;
import org.shockfrosted.hotrss.domain.Topic;
import org.shockfrosted.hotrss.input.RssReaderApptastic;
import org.shockfrosted.hotrss.service.TopicExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@Validated
public class NewAnalysis {

    @PersistenceContext
    private EntityManager entityManager;

    final private RssReaderApptastic rssReader;
    final private TopicExtractor topicExtractor;

    public NewAnalysis(RssReaderApptastic rssReader, TopicExtractor topicExtractor) {
        this.rssReader = rssReader;
        this.topicExtractor = topicExtractor;
    }

    @PostMapping("/analyse/new")
    public long analyse(@MinSizeConstraint @RequestParam List<String> feedUrl) {

        List<NewsFeed> feeds = new ArrayList<>();
        for (String url : feedUrl) {
            try {
                feeds.add(rssReader.readFeed(new URL(url)));
            } catch (IOException e) {
                throw new RuntimeException("Malformed URL");
            }
        }

        List<Topic> topics = topicExtractor.getTopics(feeds);

        Analysis analysis = new Analysis();
        entityManager.persist(analysis);
        return analysis.getId();
    }

}
