package com.schoenbrot.website.searcher.service;

import org.apache.http.client.fluent.Request;

import java.io.IOException;

/**
 * A naive implementation for a key word searcher.
 * Will simply check if the page contains a keyword, will not differentiate between markup, code, content, etc.
 * Will ignore case.
 */
public class NaiveKeywordSearcher implements KeywordSearcher {

    @Override
    public boolean wasKeywordFound(final String url, final String keyword) {
        try {
            System.out.println("Checking website " + url);
            return Request.Get("https://" + url).execute().returnContent().asString().toLowerCase().contains(keyword);
        } catch (IOException e) {
            System.out.println(url + " generated an exception " + e.getMessage());
            return false;
        }
    }
}
