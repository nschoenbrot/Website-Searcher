package com.schoenbrot.website.searcher.service;

/**
 * Search a web page for a given keyword.
 */
public interface KeywordSearcher {
    boolean wasKeywordFound(String url, String keyword);
}
