package org.shockfrosted.hotrss.controller;

import org.shockfrosted.hotrss.model.Topic;
import org.shockfrosted.hotrss.service.AnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class Frequency {

    private final AnalysisService analysisService;

    public Frequency(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping("/frequency/{id}")
    public List<Topic> getFrequencies(@PathVariable long id) {

        return analysisService.getTopicsForAnalysis(id)
                .stream().sorted(Comparator.comparing(
                        Topic::getFrequency).reversed())
                .limit(3)
                .collect(toList());
    }
}
