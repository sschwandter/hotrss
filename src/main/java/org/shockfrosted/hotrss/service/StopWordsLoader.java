package org.shockfrosted.hotrss.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StopWordsLoader {

    @Bean(name = "stopwords")
    public Set<String> loadStopWords(@Value("classpath:stop-words_english_3_en.txt") Resource stopWordsFile) throws IOException {
        String stopwordsFileContent = tryReadFile(stopWordsFile);
        String[] words = stopwordsFileContent.split("\\R");
        String[] wordsLowercase = Arrays.stream(words).map(String::toLowerCase).toArray(String[]::new);
        return new HashSet<>(Arrays.asList(wordsLowercase));
    }

    private String tryReadFile(Resource stopWordsFile) throws IOException {
        String stopwords;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(stopWordsFile.getInputStream()))) {
            stopwords = reader.lines()
                    .collect(Collectors.joining("\n"));
        }
        return stopwords;
    }
}
