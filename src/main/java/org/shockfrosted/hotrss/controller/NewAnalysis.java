package org.shockfrosted.hotrss.controller;


import org.shockfrosted.hotrss.service.AnalysisService;
import org.shockfrosted.hotrss.service.FeedReadException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@Transactional
public class NewAnalysis {

    final private AnalysisService analysisService;

    public NewAnalysis(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/analyse/new")
    public long analyse(@RequestParam List<String> feedUrl) {
        if (feedUrl.size() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide at least two feed URLs");
        }
        long analysisId;
        try {
            analysisId = analysisService.createAnalysis(feedUrl);
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not a valid URL", e);
        } catch (FeedReadException ie) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error reading feed", ie);
        }
        return analysisId;
    }

}
