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
            final boolean contains = Request.Get("https://" + url)
                    .connectTimeout(100)
                    .socketTimeout(100)
                    .execute()
                    .returnContent()
                    .asString()
                    .toLowerCase()
                    .contains(keyword);
            System.out.println(url + " contains " + keyword + "?" + " " + contains);
            return contains;
        } catch (IOException e) {
            System.out.println(url + " generated an exception " + e.getMessage());
            return false;
        }
    }
}
