package org.shockfrosted.hotrss.service;


import org.shockfrosted.hotrss.model.Analysis;
import org.shockfrosted.hotrss.model.NewsFeed;
import org.shockfrosted.hotrss.model.Topic;
import org.shockfrosted.hotrss.input.RssReaderApptastic;
import org.shockfrosted.hotrss.repository.AnalysisRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AnalysisService {

    final private RssReaderApptastic rssReader;
    final private TopicExtractor topicExtractor;
    private AnalysisRepository repository;

    public AnalysisService(RssReaderApptastic rssReader, TopicExtractor topicExtractor, AnalysisRepository repository) {
        this.rssReader = rssReader;
        this.topicExtractor = topicExtractor;
        this.repository = repository;
    }

    public long createAnalysis(List<String> feedUrl) throws MalformedURLException {

        List<NewsFeed> feeds = new ArrayList<>();
        for (String urlString : feedUrl) {
            final URL url = new URL(urlString);
            try {
                feeds.add(rssReader.readFeed(url));
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        Set<Topic> topics = topicExtractor.getTopics(feeds);
        Analysis analysis = new Analysis(topics);
        return repository.save(analysis).getId();
    }

    public Set<Topic> getTopicsForAnalysis(long id) {
        final Analysis analysis = repository.findById(id).orElse(new Analysis(Set.of()));
        return analysis.getTopics();
    }
}

