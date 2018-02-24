package com.schoenbrot.website.searcher.service;

import com.schoenbrot.website.searcher.service.util.UrlRetriever;

import java.util.HashMap;
import java.util.Map;

/**
 * Runs the keyword search on all web pages.
 */
public class Runner {
    private final UrlRetriever urlRetriever;
    private final KeywordSearcher keywordSearcher;

    public Runner(UrlRetriever urlRetriever, KeywordSearcher keywordSearcher) {
        this.urlRetriever = urlRetriever;
        this.keywordSearcher = keywordSearcher;
    }

    public Map<String, Boolean> doWebPagesContainKeyword(final String keyword) {
        final Map<String, Boolean> wasKeywordFound = new HashMap<>();
        for (final String url : urlRetriever.getUrls()) {
            wasKeywordFound.put(url, keywordSearcher.wasKeywordFound(url, keyword));
        }
        return wasKeywordFound;
    }
}
